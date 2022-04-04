package utility;

import java.util.List;

import data.Lager;
import data.Produkt;
import data.Produktergebnis;

public class CheckFeasible {
	
	public boolean isFeasible(List<Produktergebnis> pList, Lager lager, List<Produkt> produktdaten) {
		boolean[] status = new boolean[4];	
		boolean feasible = false;
		status[0] = checkMinBestand(pList,produktdaten);
		status[1] = checkMaxBestand(pList, produktdaten);
		status[2] = checkKapitalbindung(pList,  lager);
		status[3] = checkLagerSize( pList,lager, produktdaten);
		for(int i = 0; i<status.length;i++) {
			System.out.println(i);
			if(!status[i]) {
				feasible = false;
			}else {
				feasible = true;
			}
		}
		return feasible;
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
	
	/**
	 * Prüfen wie viel noch auis der Periode zuvor im Lager liegt einfpgen
	 * @param pList
	 * @param lager
	 * @param produktdaten
	 * @return
	 */
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

