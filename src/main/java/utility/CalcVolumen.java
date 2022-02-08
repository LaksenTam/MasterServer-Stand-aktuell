package utility;

import java.util.ArrayList;
import java.util.List;

import data.Produkt;

public class CalcVolumen {
	
	public List<String[]> calcVolumen(List<Produkt> produktDaten, List<String[]> bestandList) {
		List<String[]> vol = new ArrayList<>();
		
		
		for(int j = 0; j<bestandList.size();j++) {
			for(int i = 0; i<produktDaten.size();i++) {				
				Produkt p = produktDaten.get(i);					
				int bestand = Integer.parseInt(bestandList.get(j)[1]);	
				if(produktDaten.get(i).getName().equals(bestandList.get(j)[0])) {
					double volumen = bestand * p.getvProdukt();
					String[] s = { p.getName(), Double.toString(volumen)};
					vol.add(s);	
					break;
				}				
			}			
		}		
		return vol;
	}

}
