package produkterstellung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.opencsv.exceptions.CsvValidationException;

import data.Lager;
import data.Produkt;
import utility.CSVRead;
import utility.IntervallAufteilung;

public class CProdukt {
	
public Produkt generiereProduktC(Produkt p, boolean saisonal, int i, int perioden, Lager lager) throws CsvValidationException, IOException {
	Random rand = new Random();
	List<Integer> verbrauchsListe = new ArrayList<Integer>();
	CSVRead csv = new CSVRead();

	
	String artikel = "Roccat Kone, Roccat Pyro, Sharkoon SKILLER, Razer Huntsman, Logitech G502 HERO, Roccat Kain 120, "
			+ "Microsoft Xbox Wireless Controller, ASUS ROG Chakram, Keychron K3, EVGA Z15, Logitech PRO X, "
			+ "Razer Mamba, Logitech MX Master, Corsair K57, Roccat Vulcan, Logitech G815, Sony Pulse 3D, Logitech G935,"
			+ " SteelSeries Archtis, Turtle Beach Recon 500, Logitech G PRO X, Razer Kraken, Shure SM7B, Creative SoundBlaster X4, "
			+ "Cooler Master MH670, Elgato Wave:3, HP X1000, SteelSeries Arctis 9, Rode NT-USB Mini, Razer Blackshart V2, "
			+ "Jabra Evolve2 75, Sony WF-1000XM4";
	
	List<String> namenListe = new ArrayList<String>();
	
	namenListe = csv.lese(artikel, namenListe);	
	
	int selector = rand.nextInt(namenListe.size())+0;		
	
	String name = namenListe.get(selector);		
	
	p.setName(name);
	p.setBestellfix((double) Math.round((rand.nextDouble() + 0)*100)/100);		
	p.setEinstand((double) Math.round(((rand.nextInt(200) + 3) + (rand.nextFloat()+0))*100)/100);
	p.setFehlmengenkosten((double) Math.round((rand.nextDouble() + 0)*100)/100);
	p.setLagerkostensatz((double) Math.round((rand.nextDouble() + 0)*100)/100);
	p.setMaxBestand(rand.nextInt() + 0);
	p.setMinBestand(rand.nextInt() + 0);
	p.setvProdukt((double) Math.round((rand.nextDouble() + 0)*100)/100);
	if(saisonal) {
		int verbrauch = rand.nextInt(70000)+0;
		verbrauchsListe = IntervallAufteilung.teileIntervall(perioden, verbrauch);
	}else {
		
		for(int n = 0; n < perioden; n++) {
			p.setVerbrauch(rand.nextInt(100000)+ 0);
			verbrauchsListe.add(p.getVerbrauch());
		}
	}
	p.setVerbraeuche(verbrauchsListe);	
	return p;
		
	}

	/**
	 * Saisonaler Verbrauch Generieren
	 * Erstelle einen Verbrauchswert
	 * Aufteilen der Quartilen anhand der Periodenanzahl
	 * 3 Quartile:
	 * 		0,05 Quatil * 2 10% der Perioden 
	 * 		0,15 Quartil * 2 30% der Perioden
	 * 		0,3 Quartil * 2 60% der Perioden
	 * Multipliziere Verbrauchswert mit Quartilen
	 * Füge den WErt der Liste hinzu für das Produkt
	 */


}