package utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IntervallAufteilung {		
		
	/**
	 * Methode, um die Intervalle zu bestimmen,
	 * sowie die Anzahl der entsprechend zu generieren
	 * Werte für die einzelnen Intervalle
	 * @param perioden
	 * @param zahl = Zufallszahl aus Produtgenerierung
	 * @return
	 */
		public static List<Integer> teileIntervall(int perioden, int zahl) {
			System.out.println("Input:");
			System.out.println("Anzahl der Perioden: " + perioden);
			System.out.println("Zufällig generierte Zahl: " + zahl);
			List<Integer> verbrauchsListe = new ArrayList<Integer>();
			List<Integer> zahlen = new ArrayList<Integer>();
			double[] intervall = {0.2, 0.3, 0.5};
			int[] aufteilung = new int[3];
			int counter = 0;
			
			for(int i = 0; i< intervall.length;i++ ) {
				double sum = perioden * intervall[i];
				int runden  = (int) sum;			
				aufteilung[i] = runden;		
				counter+= runden;
			}	
			
			if(perioden != counter) {
				int fehlt = perioden - counter;	
				System.out.println("Es fehlen: " + fehlt + "Perioden");
				for(int j = 0; j< fehlt; j++) {
					aufteilung[j] += 1;
				}			
			}
			
			System.out.println("Anzahl der zu erstellenden Werte: " + Arrays.asList(aufteilung));
			generiereZahl(aufteilung, intervall, zahl, zahlen);	
			sortiereListe(zahlen, verbrauchsListe);
			return verbrauchsListe;
		}
		
		/**
		 * Methode um fuer die verschiedenen Intervalle Verbraeuche zu erzeugen
		 * @param aufteilung
		 * @param intervall
		 * @param zahl
		 * @param zahlen
		 */
		public static void generiereZahl(int[] aufteilung, double[] intervall, int zahl, List<Integer> zahlen) {
			Random random = new Random();
								
			
			for(int i = 0; i<aufteilung.length; i++) {
				for(int j = 0; j<aufteilung[i]; j++) {
					
					int verbrauch;
					double maxRange;
									
					switch(i) {
						case(0): 						
							maxRange = zahl * intervall[i];							
							verbrauch = random.nextInt((int) maxRange)+0;						
							break;
						case(1):
							maxRange = zahl * intervall[i];
							verbrauch = random.nextInt((int) maxRange)+0;							
							break;
						default: 
							maxRange = zahl * intervall[i];
							verbrauch = random.nextInt((int) maxRange)+5;						
							break;
					}							
					zahlen.add(verbrauch);
				}				
			}							 
		}
		
		/**
		 * Methode um die Verbrauchsliste zu sortieren
		 * @param zahlen
		 * @param verbrauchsListe
		 * @return
		 */
		public static List<Integer> sortiereListe(List<Integer> zahlen, 
				List<Integer> verbrauchsListe) {
			List<Integer> tmp1 = new ArrayList<Integer>();
			List<Integer> tmp2 = new ArrayList<Integer>();
			
			int tmp;
			/**
			 * Iteriere durch die Verbrauchsliste und 
			 * weise abwechselnd Werte den temporaeren 
			 * Listen zu
			 */
			for(int i = 0; i<zahlen.size();i++) {
				if(i%2 !=0) {
					tmp = zahlen.get(i);
					tmp2.add(tmp);				
				}else {
					tmp = zahlen.get(i);
					tmp1.add(tmp);
				}
			}		
			Collections.sort(tmp1);
			Collections.sort(tmp2, Collections.reverseOrder());			
			verbrauchsListe.addAll(tmp1);
			verbrauchsListe.addAll(tmp2);			
			return verbrauchsListe;
		}		
	}

