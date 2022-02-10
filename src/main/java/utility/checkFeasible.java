package utility;

import java.util.List;

import data.Lager;
import data.Produkt;
import data.Produktergebnis;

public class checkFeasible {
	
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
		for(int i = 0; i<pList.size();i++) {
			int checkBestand = produktdaten.get(i).getMaxBestand() - pList.get(i).getBestellmenge();
			if(checkBestand<=0) {
				status = false;
				break;
			}else {
				status = true;
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
			checkSize += pList.get(i).getBestellmenge() * produktdaten.get(i).getvProdukt();			
		}
		if(checkSize< lager.getLagerVol()) {
			status = true;
		}
		
		return status;
	}

}
