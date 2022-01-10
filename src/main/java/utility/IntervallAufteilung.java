package utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IntervallAufteilung {		
		
		public static List<Integer> teileIntervall(int perioden, int zahl) {
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
				for(int j = 0; j< fehlt; j++) {
					aufteilung[j] += 1;
				}			
			}
			generiereZahl(aufteilung, intervall, zahl, zahlen);	
			sortiereListe(zahlen, verbrauchsListe);
			return verbrauchsListe;
		}
		
		
		public static void generiereZahl(int[] aufteilung, double[] intervall, int zahl, List<Integer> zahlen) {
			Random random = new Random();
								
			
			for(int i = 0; i<aufteilung.length; i++) {
				for(int j = 0; j<aufteilung[i]; j++) {
					
					int verbrauch;
					double test;
									
					switch(i) {
						case(0): 						
							test = zahl * intervall[i];							
							verbrauch = random.nextInt((int) test)+0;						
							break;
						case(1):
							test = zahl * intervall[i];
							verbrauch = random.nextInt((int) test)+0;
							
							break;
						default: 
							test = zahl * intervall[i];
							verbrauch = random.nextInt((int) test)+0;						
							break;
					}				
					
					zahlen.add(verbrauch);
				}
				
			}		
						 
		}
		
		public static List<Integer> sortiereListe(List<Integer> zahlen, List<Integer> verbrauchsListe) {
			List<Integer> tmp1 = new ArrayList<Integer>();
			List<Integer> tmp2 = new ArrayList<Integer>();
			
			int tmp;
			
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
			System.out.println(Arrays.asList(tmp1));
			System.out.println(Arrays.asList(tmp2));
			verbrauchsListe.addAll(tmp1);
			verbrauchsListe.addAll(tmp2);		
			System.out.println(Arrays.asList(verbrauchsListe));
			return verbrauchsListe;
		}
		
		
	}

