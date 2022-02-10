package utility;

import java.util.ArrayList;
import java.util.List;

import data.Produkt;
import datenbank.Datenbank;

public class CalcBestandsVerlauf {
	
	public List<String[]> bestandsVerlauf(List<String[]> userData,List<Produkt> produktDaten) {
		List<String[]> verlauf = new ArrayList<>();
		Datenbank db = new Datenbank();
		int perioden = db.getPeriodenAnzahl();
		
		for(int i = 0; i<perioden;i++) {
			int bestand = 0;
			for(int j = 0; j<userData.size();j++) {
				if(userData.get(i)[6].equals(userData.get(j)[6])) {
					bestand += Integer.parseInt(userData.get(j)[1]);
				}
			}
			String[] s = { userData.get(i)[6], Integer.toString(bestand)};
			verlauf.add(s);
			
		}
		return verlauf;
	}
		
}


