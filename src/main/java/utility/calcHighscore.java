package utility;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import data.Produkt;
import data.Produktergebnis;
import data.Highscore;
import datenbank.Datenbank;

public class CalcHighscore {
	Datenbank db = new Datenbank();
	
	/**
	 * Hole dir volle Ergebnisse (alle Ergebnisse für die Perioden) aus der DB und berechne den Highscore
	 * Berechnung: Niedrige Kosten müssen zu höheren Werte führen als niedrige Kosten Fehlmengen sollten einen negativen Effekt haben
	 * Wenn die letze Periode abgegeben wurde sollte an diese Klasse weitergeleitet werden, sodass alle Perioden von dem Benutzer abgerufen werden
	 * @return
	 * @throws SQLException 
	 */
	
	public Highscore berechneHighscore(List<Produktergebnis> ergebnis) throws SQLException {	
		Highscore score = new Highscore();
		List<Produkt> produktListe = db.getVerbrauchsListe();
		List<Produkt> pInfo = new ArrayList<Produkt>();
		db.getStartProblem(pInfo);
		
		int fehl = berechnefehlMengenKosten(ergebnis, produktListe, pInfo);
		double kosten = berechneKosten(ergebnis,pInfo);
		
		double averageElo = 1000;
		double highscore = averageElo;
		
		double vergleichsWert = eloVergleichsWert();
		
		
		double userKosten = kosten + fehl;
		System.out.println("Kosten User: " +userKosten);
		System.out.println("Kosten System: " +vergleichsWert);
		
		double prozent = 100*userKosten/vergleichsWert;
		
		if(vergleichsWert>userKosten) {		
			highscore = averageElo + ((averageElo*(100 - prozent))/100);
		}else {			
			highscore = averageElo - ((averageElo*(prozent-100))/100);
		}		
		
		highscore = (double) Math.round((highscore*100)/100);
		System.out.println("Highscore: " + highscore);
		
		score.setScore(highscore);
		score.setKosten(userKosten);		
		score.setFehlmengen(fehl);
		
		calcKostenAufteilung(ergebnis, pInfo, produktListe);

		return score;
	}
	
	public int berechnefehlMengenKosten(List<Produktergebnis> ergebnis,List<Produkt> produktListe, List<Produkt> pInfo ) {
		int fehlmengenKosten = 0;			
		List<Produkt> pro = new ArrayList<>();		
		
		int menge = 0;
		for(int i =0; i<produktListe.size();i++) {		
			Produkt p = new Produkt();
			menge += produktListe.get(i).getVerbrauch();
			if(i != (produktListe.size()-1)) {			
				if(!produktListe.get(i).getName().equals(produktListe.get(i+1).getName())) {						
					p.setVerbrauch(menge);
					p.setName(produktListe.get(i).getName());
					pro.add(p);					
					menge = 0;
				}
			}else {				
				p.setVerbrauch(menge);
				p.setName(produktListe.get(i).getName());
				pro.add(p);
			}		
		}		
				
		for(int j=0;j<pro.size();j++) {
			int checkfehl = pro.get(j).getVerbrauch();			
			for(int k =0; k<ergebnis.size();k++) {
				if(pro.get(j).getName().equals(ergebnis.get(k).getProduktName())) {
					checkfehl -=ergebnis.get(k).getBestellmenge();
				}				
			}
			if(checkfehl<0) {
				checkfehl = 0;
			}
		fehlmengenKosten +=checkfehl *pInfo.get(j).getFehlmengenkosten();			
		}	
		return fehlmengenKosten;
	}
	
	public double berechneKosten(List<Produktergebnis> ergebnis,List<Produkt> pInfo)  {
		double kosten =0.00;		
						
		for(int i =0; i<ergebnis.size();i++) {				
			kosten += ergebnis.get(i).getKosten();						
		}
//		double bestellkosten = 0.00;
//		for(int j = 0; j<pInfo.size(); j++) {				
//			for(int i = 0; i<ergebnis.size();i++) {				
//				if(pInfo.get(j).getName().equals(ergebnis.get(i).getProduktName())) {
//					bestellkosten += ergebnis.get(i).getKosten() * ergebnis.get(i).getBestellmenge();	
//					
//				}
//			}
		//}
				
		//kosten += bestellkosten; 		
		kosten = ((double) Math.round(kosten*100)/100);
		
		return kosten;
	}
	
	public double eloVergleichsWert() throws SQLException {
		double vergleichsWert = 0;
		List<Produkt> produkte = db.getVerbrauchsListe();
		for(int i =0; i<produkte.size();i++) {
			vergleichsWert += produkte.get(i).getBestellfix() + (produkte.get(i).getEinstand()*produkte.get(i).getVerbrauch());
		}
		return vergleichsWert;
	}
	
	public List<Double> calcKostenAufteilung(List<Produktergebnis> ergebnis, List<Produkt> pinfo, List<Produkt> verbrauch) {
		List <Double> kosten = new ArrayList<Double>();
		double bf = calcBestellfix(ergebnis,pinfo);
		kosten.add(bf);
		double einstand = calcEinstand(ergebnis, pinfo);
		kosten.add(einstand);
		double lagerkosten = calcLagerkosten(ergebnis, pinfo, verbrauch);
		
		return null;
		
	}
	
	public double calcBestellfix(List<Produktergebnis> ergebnis, List<Produkt> pinfo) {
		double bf = 0.00;
		for(int i = 0; i<ergebnis.size();i++) {
			if(ergebnis.get(i).getBestellmenge() >0) {
				for(int j = 0; j< pinfo.size();j++) {
					if(ergebnis.get(i).getProduktName().equals(pinfo.get(j).getName())) {
						bf += pinfo.get(j).getBestellfix();
					}
				}				
			}
		}
		System.out.println(bf);
		return bf;
	}
	
	public double calcEinstand(List<Produktergebnis> ergebnis, List<Produkt> pinfo) {
		double einstand = 0.00;
		for(int i = 0; i<ergebnis.size();i++) {
			if(ergebnis.get(i).getBestellmenge()>0) {
				for(int j = 0; j<pinfo.size();j++) {
					if(ergebnis.get(i).getProduktName().equals(pinfo.get(j).getName())) {
						einstand += ergebnis.get(i).getBestellmenge()* pinfo.get(j).getEinstand();
					}
				}
			}
		}
		System.out.println(einstand);
		return einstand;
	}
	
	public double calcLagerkosten(List<Produktergebnis> ergebnis, List<Produkt> pinfo, List<Produkt> verbrauch) {
		double lagerkosten = 0.00;
		for(int i = 0; i<ergebnis.size(); i++) {
			int per = 0;
			if(ergebnis.get(i).getBestellmenge()>0) {
				double besteller = ergebnis.get(i).getBestellmenge();
				for(int j = 0; j<verbrauch.size(); j++) {
					if(ergebnis.get(i).getProduktName().equals(verbrauch.get(j).getName())){						
						besteller -= verbrauch.get(j).getVerbrauch(); 
						System.out.println("besteller: " + besteller);
						lagerkosten += verbrauch.get(j).getVerbrauch() * per * verbrauch.get(j).getLagerkostensatz();
						per++;
						if(besteller <= 0) {
							break;
						}
					}
				}
				
			}
		}
		System.out.println(lagerkosten);
		return lagerkosten;
		
	}

}
