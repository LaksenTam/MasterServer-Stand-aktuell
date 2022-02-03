package produkterstellung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.opencsv.exceptions.CsvValidationException;

import data.Produkt;
import utility.CSVRead;
import utility.IntervallAufteilung;
import data.Lager;

/**
 * Klasse zur Erstellung eines Produktes
 * Hier werden hochwertige Produkte generiert
 * @author puken
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
	
	//String artikel = "Huawei P30, Google Pixel 6, Samsung Galaxy S21";
	
	public Produkt generiereProduktA(Produkt p, boolean saisonal, int i, int perioden, Lager lager) throws CsvValidationException, IOException {
		
		List<Integer> verbrauchsListe = new ArrayList<Integer>();		
		
		List<String> namenListe = new ArrayList<String>();
		
		namenListe = csv.lese(artikel, namenListe);	
		
		int selector = rand.nextInt(namenListe.size())+0;		
		
		String name = namenListe.get(selector);		
		
		p.setName(name);		
		
		p.setBestellfix((rand.nextInt(30000)+1000) + (double) Math.round((rand.nextDouble()+0)*100)/100);	
		System.out.println(p.getBestellfix());
		p.setEinstand((rand.nextInt(2000) + 700) + (double) Math.round((rand.nextFloat()+0)*100)/100);
		p.setFehlmengenkosten((rand.nextInt(100) + 5)+(double) Math.round((rand.nextDouble() + 0)*100)/100);
		p.setLagerkostensatz((rand.nextInt(50) + 0) +(double) Math.round( (rand.nextDouble() + 0)*100)/100);
		p.setMinBestand(rand.nextInt(10) + 0);
		p.setMaxBestand(rand.nextInt(1000) + p.getMinBestand());		
		p.setvProdukt((double) Math.round((rand.nextDouble() + 0)*100)/100);
		if(saisonal) {
			int verbrauch = rand.nextInt(100)+0;
			verbrauchsListe = IntervallAufteilung.teileIntervall(perioden, verbrauch);
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
