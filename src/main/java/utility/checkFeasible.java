package utility;

import java.util.List;

import data.Lager;
import data.Produkt;
import data.Produktergebnis;

public class CheckFeasible {
	
	public boolean isFeasible(List<Produktergebnis> pList, Lager lager, List<Produkt> produktdaten) {
		boolean status = false;
		status = checkMinBestand(pList,produktdaten);
		status = checkMaxBestand(pList, produktdaten);
		status = checkKapitalbindung(pList,  lager);
		status = checkLagerSize( pList,lager, produktdaten);
		return status;
	}
	
	public boolean checkMinBestand(List<Produktergebnis> pList, List<Produkt> produktdaten) {
		boolean status = false;
		System.out.println("Prüfe Min Bestand");
		
		for(int i = 0; i<pList.size();i++) {
			int checkBestand = pList.get(i).getBestellmenge() - produktdaten.get(i).getVerbrauch();
			if(checkBestand>=produktdaten.get(i).getMinBestand()) {				
				status = true;
			}else {
				status = false;
				break;
			}
		}		
		return status;
	}
	
	public boolean checkMaxBestand(List<Produktergebnis> pList, List<Produkt> produktdaten) {
		boolean status = false;
		System.out.println("Prüfe MaxBestand");
		for(int i = 0; i<pList.size();i++) {
			for(int j = 0; j<produktdaten.size();j++) {
				if(pList.get(i).getProduktName().equals(produktdaten.get(j).getName())) {
					int checkBestand = produktdaten.get(j).getMaxBestand() - pList.get(i).getBestellmenge();
					if(checkBestand<=0) {
						status = false;						
					}else {
						status = true;
						System.out.println(status);
					}
				}
			}			
		}		
		return status;
	}
	
	public boolean checkKapitalbindung(List<Produktergebnis> pList, Lager lager) {
		boolean status = false;
		double kapUser = 0.00;
		
		for(int i = 0; i<pList.size();i++) {
			kapUser += pList.get(i).getKosten();
		}
		if(kapUser<lager.getKbindung()) {
			status = true;
		}
		
		return status;
	}
	
	public boolean checkLagerSize(List<Produktergebnis> pList, Lager lager, List<Produkt> produktdaten) {
		boolean status = false;
		double checkSize = 0.00;
		for(int i = 0; i<pList.size();i++) {
			for(int j = 0; j<produktdaten.size();j++) {
				if(pList.get(i).getProduktName().equals(produktdaten.get(j).getName())) {
					checkSize += pList.get(i).getBestellmenge() * produktdaten.get(j).getvProdukt();	
					System.out.println(pList.get(i).getBestellmenge() +  "*" + produktdaten.get(j).getvProdukt() +"=" + checkSize);

				}
			}					
		}
		if(checkSize< lager.getLagerVol()) {
			status = true;
			System.out.println(status);
		}
		
		return status;
	}

}

