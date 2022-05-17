package utility;

import java.util.ArrayList;
import java.util.List;
import data.Produkt;

/**
 * Klasse um das Volumen zu berechnen
 * @author Lukas
 *
 */
public class CalcVolumen {
	
	/**
	 * 
	 * @param produktDaten
	 * @param bestandList
	 * @return Volumen pro Produkt in einer Liste
	 */
	
	public List<String[]> calcVolumen(List<Produkt> produktDaten, List<String[]> bestandList) {
		List<String[]> vol = new ArrayList<>();
		
		/**
		 * Iteriere durch die Bestandsliste des Studenten
		 * und Iteriere anschliessend durch die produktDaten der Probleminstanz
		 * Vergleiche die Namen von Produktdaten mit denen der Bestandsliste des Studenten
		 * Falls beide Namen gleich sind, Berechne das Volumen und speicher es in Liste
		 */
		
		for(int j = 0; j<bestandList.size();j++) {
			for(int i = 0; i<produktDaten.size();i++) {				
				Produkt p = produktDaten.get(i);					
				int bestand = Integer.parseInt(bestandList.get(j)[1]);	
				if(produktDaten.get(i).getName().equals(bestandList.get(j)[0])) {
					double volumen = bestand * p.getvProdukt();
					String[] s = { p.getName(), Double.toString(volumen), bestandList.get(j)[6]};
					vol.add(s);						
					break;
				}				
			}			
		}	
		return vol;
	}
	
	
	public List<String[]> calcHighVolumen(List<Produkt> produktDaten, List<String[]> bestandList) {
		List<String[]> vol = new ArrayList<>();
		
		/**
		 * Iteriere durch die Bestandsliste des Studenten
		 * und Iteriere anschliessend durch die produktDaten der Probleminstanz
		 * Vergleiche die Namen von Produktdaten mit denen der Bestandsliste des Studenten
		 * Falls beide Namen gleich sind, Berechne das Volumen und speicher es in Liste
		 */
		
		for(int j = 0; j<bestandList.size();j++) {
			for(int i = 0; i<produktDaten.size();i++) {				
				Produkt p = produktDaten.get(i);					
				int bestand = Integer.parseInt(bestandList.get(j)[1]);	
				if(produktDaten.get(i).getName().equals(bestandList.get(j)[0])) {
					double volumen = bestand * p.getvProdukt();
					String[] s = { p.getName(), Double.toString(volumen), bestandList.get(j)[3]};
					vol.add(s);						
					break;
				}				
			}			
		}	
		return vol;
	}
	
}
