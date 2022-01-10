package produkterstellung;

import data.Lager;

public class LagerGenerierung {
	
	public void erstelleLager(Lager lager, String kap, String vol) {
		switch(kap) {
		case "wenig":
			lager.setKbindung(100);
			break;
		case "medium":
			lager.setKbindung(1000);
			break;
		default:
			lager.setKbindung(100000);
			break;
		}
		
		switch(vol) {
			case "klein":
				lager.setLagerVol(100000);
				break;
			case "medium":
				lager.setLagerVol(10000000);
				break;
			default:
				lager.setLagerVol(1000000000);
				break;
		}
		
	}

}