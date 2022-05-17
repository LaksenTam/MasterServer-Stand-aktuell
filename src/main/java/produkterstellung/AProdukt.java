package produkterstellung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.opencsv.exceptions.CsvValidationException;

import data.Produkt;
import utility.CSVRead;
import utility.IntervallAufteilung;
import data.ErweiterteProdukte;
import data.Lager;

/**
 * Klasse zur Erstellung eines Produktes
 * Hier werden hochwertige Produkte generiert
 * 
 *
 */
public class AProdukt {
	CSVRead csv = new CSVRead();
	Random rand = new Random();

	String artikel = "Huawei P30, Google Pixel 6, Samsung Galaxy S21, Apple IPhone 13, Samsung Galaxy Fold, "
			+ "Apple MacBook Air, Samsung Galaxy Book, GeForce RTX 3080, GeForce RTX 3090, Radeon RX 6900XT, Radeon RX 6800XT, "
			+ "Radeon RX 6700XT, ASUS ROG MAXIMUS Z690, GigaByte Z490, Acer Predator, ASUS ProArt, ASUS ROG Strix XG43UQ, "
			+ "HP ProBook, Lenovo ThinkPad, HP ZBook Firefly, Acer Nitro 5, HP EliteBook, HP Spectre, Huawei MateBook, "
			+ "Lenovo IdeaPad, MSI GF65, HP ENVY, Microsoft Surface, Dell Precision, Dell Latitude, XMG Neo, GIGABYTE AERO, "
			+ "MSI Creator, ASUS VivoBook, Razer Blade, XMG Core, Dell XPS, Canon EOS R, Sony Alpha 6600, DJI Mavic 3, "
			+ "SAMSUNG NEO QLED, SAMSUNG GQ, LG 86NANO, SAMSUNG The Frame, Optoma UHZ, Acer L811, iRobot Roomba S9, ";
	
	
	/**
	 * 
	 * @param p = Produkt
	 * @param saisonal = true wenn saisonaler Verbrauch erstellt wird
	 * @param konstant = true wenn konstanter Verbrauch erstellt wird
	 * @param perioden = Anzahl der zu generierden Verbrauchsperioden
	 * @param lager = Lagerklasse
	 * @param erweitert = true wenn ueber das erweiterte Formular generiert wurde
	 * @param prod = ??
	 * @return
	 * @throws CsvValidationException
	 * @throws IOException
	 */
	
	public Produkt generiereProduktA(Produkt p, boolean saisonal, boolean konstant, int perioden, Lager lager, boolean erweitert, 
			ErweiterteProdukte prod) 
					throws CsvValidationException, IOException {
		
		List<Integer> verbrauchsListe = new ArrayList<Integer>();		
		
		List<String> namenListe = new ArrayList<String>();
		
		namenListe = csv.lese(artikel, namenListe);	
		
		int selector = rand.nextInt(namenListe.size())+0;		
		
		String name = namenListe.get(selector);		
		
		p.setName(name);
		
		//Unterscheidung zwischen der erweiterten Generierung und der normalen
		if(erweitert) {
			p.setBestellfix((rand.nextInt(prod.getBestellMaxRange())+prod.getBestellMinRange()) + (double) Math.round((rand.nextDouble()+0)*100)/100);	
			p.setEinstand((rand.nextInt(prod.getEinstandmaxRange()) + prod.getEinstandminRange()) + (double) Math.round((rand.nextFloat()+0)*100)/100);
			p.setFehlmengenkosten((rand.nextInt(prod.getFehlkostenMaxRange()) + prod.getFehlkostenMinRange())+(double) Math.round((rand.nextDouble() + 0)*100)/100);
			p.setLagerkostensatz((double) Math.round(p.getEinstand()*prod.getLagersatz()));
			p.setMinBestand(rand.nextInt(10) + 0);
			p.setMaxBestand(rand.nextInt(1000) + p.getMinBestand());		
			p.setvProdukt((double) Math.round((rand.nextDouble() + 0)*100)/100);
		}		
		else {
			p.setBestellfix((rand.nextInt(30000)+1000) + (double) Math.round((rand.nextDouble()+0)*100)/100);	
			p.setEinstand((rand.nextInt(2000) + 700) + (double) Math.round((rand.nextFloat()+0)*100)/100);
			float fk = rand.nextInt((90 - 40) + 1) + 40;		
			p.setFehlmengenkosten((double) Math.round((fk/100)*p.getEinstand()*100)/100);
			float lk = rand.nextInt((15-10)+1)+10;
			p.setLagerkostensatz((double) Math.round((lk/100)*p.getEinstand()*100)/100);
			p.setMinBestand(rand.nextInt(10) + 0);
			p.setMaxBestand(rand.nextInt(1000) + p.getMinBestand());		
			p.setvProdukt((double) Math.round((rand.nextDouble() + 0)*100)/100);
		}
		
		//Verbrauchsgenerierung
		if(saisonal) {
			int verbrauch = rand.nextInt(200)+70;
			verbrauchsListe = IntervallAufteilung.teileIntervall(perioden, verbrauch);
		}else if(konstant) {
			int verbrauch = rand.nextInt(100) +0;
			for(int k = 0; k<perioden;k++) {
				p.setVerbrauch(verbrauch);
				verbrauchsListe.add(p.getVerbrauch());
			}
		}else {			
			for(int n = 0; n < perioden; n++) {
				p.setVerbrauch(rand.nextInt(100) + 0);
				verbrauchsListe.add(p.getVerbrauch());
			}
		}		
		p.setVerbraeuche(verbrauchsListe);		
		return p;
	}
	
	
	public List<String> getProduktNamen(){
		List<String> produktNamen = new ArrayList<String>();
		produktNamen = csv.lese(artikel, produktNamen);
		return produktNamen;
	}
	
	
}
