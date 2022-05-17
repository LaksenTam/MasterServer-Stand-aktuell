package utility;

import java.util.List;

public class DrawChart {
	
	public String drawKosten(List<String[]> kostenList) {
		String kosten ="";
		for(int i = 0; i<kostenList.size();i++) {			
				kosten += "['" + kostenList.get(i)[0] + "'," + kostenList.get(i)[2] + "],";					
		}
		
		return kosten;
	}
	
	public String drawBestandsVerlauf(List<String[]> bestandList) {
		String bestand = "";
		for(int i = 0; i<bestandList.size();i++) {
			bestand += "['" + bestandList.get(i)[0] + "'," + bestandList.get(i)[1] + "],";			
		}
		return bestand;
	}

	public String drawProdukteChart(List<String> produktNamen) {
		String proNamen = "";
		for(int i = 0;i<produktNamen.size();i++) {
			proNamen += "'" + produktNamen.get(i) + "',";
		}
		return proNamen;
	}

	public String drawVolumenVerlauf(List<String[]> volumen) {
		String vol = "";
		for(int i = 0; i<volumen.size();i++) {
			vol += "['" + volumen.get(i)[0] + "'," + volumen.get(i)[1] + "],";			
		}
		return vol;
	}

	public String drawScores(List<String> hsData) {
		String score ="";
		score = "['Highscore Übersicht'," +hsData.get(0) + "," + hsData.get(1) + "," + hsData.get(2) + "],"; 
	
		return score;
	}
	
	public String drawHighScoreLeader(List<String[]> leader) {
		String leaderScore = "";
		for(int i = 0; i<leader.size();i++) {
			leaderScore = "['Highscore Übersicht'," + leader.get(i)[2] + "," + leader.get(i)[1] + "," + leader.get(i)[3] + "],"; 
		}
		
		return leaderScore;
	}

	public String drawGesamtBestandVerlauf(List<String[]> bestandsverlauf) {
		String verlauf = "['0', 0]";		
		for(int i = 0; i<bestandsverlauf.size();i++) {
			verlauf += ",['" + bestandsverlauf.get(i)[0] + "'," + bestandsverlauf.get(i)[1] + "]";
		}
		verlauf +=",['" + (bestandsverlauf.size() +1) + "',0]";
	
		return verlauf;
	}
	
	public String drawStackedKosten(List<String[]> kostenList, int perioden) {
		String kosten = "";
		for(int i = 0; i<perioden;i++) {
			kosten +="['Periode" + (i +1) + "'";			
			for(int j = 0; j<kostenList.size();j++) {				
				if(kostenList.get(j)[6].equals(Integer.toString(i+1))) {
					kosten +=  ","+kostenList.get(j)[2] ;
				}
			}
			kosten +="],";			
		}		
		return kosten;
	}
	
	public String produktname(List<String[]> namenList) {
		String namen = "['Produktnamen'";
		for(int i = 0; i<namenList.size();i++) {
			if(namenList.get(i)[6].equals("1")) {
				namen += ", '"+ namenList.get(i)[0] + "'"; 
			}
		}
		namen +="],\n";	
		return namen;
	}
	
	public String drawStackedBestand(List<String[]> bestandsList, int perioden) {
		String bestandVerlauf = "";
		for(int i = 0;i < perioden; i++ ) {
			bestandVerlauf += "['Periode" + (i+1) + "'";
			for(int j = 0; j<bestandsList.size();j++) {
				if(bestandsList.get(j)[6].equals(Integer.toString(i+1))) {
					bestandVerlauf += ","+bestandsList.get(j)[1];
				}
			}
			bestandVerlauf += "],\n";
		}		
		return bestandVerlauf;
	}
	
	public String drawStackedVolumen(List<String[]> volumen, int perioden) {
		String volVerlauf = "";
		for(int i=0; i<perioden;i++) {
			volVerlauf += "['Periode" + (i+1) + "'";
			for(int j = 0; j<volumen.size();j++) {
				if(volumen.get(j)[2].equals(Integer.toString(i+1))) {
					volVerlauf += ","+volumen.get(j)[1];
				}
			}
			volVerlauf +="],\n";
		}		
		System.out.println(volVerlauf);
		return volVerlauf;
	}
	
	public String drawHighStackedBestand(List<String[]> bestandsList, int perioden) {
		String bestandVerlauf = "";
		for(int i = 0;i < perioden; i++ ) {
			bestandVerlauf += "['Periode" + (i+1) + "'";
			for(int j = 0; j<bestandsList.size();j++) {
				if(bestandsList.get(j)[3].equals(Integer.toString(i+1))) {
					bestandVerlauf += ","+bestandsList.get(j)[1];
				}
			}
			bestandVerlauf += "],\n";
		}		
		return bestandVerlauf;
	}

	public String drawHighStackedKosten(List<String[]> kostenList, int perioden) {
		String kosten = "";
		for(int i = 0; i<perioden;i++) {
			kosten +="['Periode" + (i +1) + "'";			
			for(int j = 0; j<kostenList.size();j++) {				
				if(kostenList.get(j)[3].equals(Integer.toString(i+1))) {
					kosten +=  ","+kostenList.get(j)[2] ;
				}
			}
			kosten +="],";			
		}		
		return kosten;
	}
}
