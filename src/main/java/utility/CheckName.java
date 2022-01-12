package utility;

import java.util.List;
import java.util.Random;

import data.Produkt;

public class CheckName {
	
	
	public List<Produkt> checkNameForDup(List<String> produktNamen, List<Produkt> checkName){
		Random rand = new Random();
		
		for(int i= 0; i<checkName.size();i++) {
			Produkt p1 = checkName.get(i);				
			for(int j = 0; j<produktNamen.size();j++) {
				if(p1.getName().equals(produktNamen.get(j))) {					
					produktNamen.remove(j);						
				}					
			}
		}
		
		for(int k = 0; k<checkName.size();k++) {
			Produkt p1 = checkName.get(k);
			for(int n = 0; n<checkName.size();n++) {
				if(k!=n) {
					Produkt p2 = checkName.get(n);
					if(p1.getName().equals(p2.getName())) {
						int selector = rand.nextInt(produktNamen.size())+0;
						p2.setName(produktNamen.get(selector));
						produktNamen.remove(selector);
					}
				}
		}
		}
		return checkName;
	
	}

}
