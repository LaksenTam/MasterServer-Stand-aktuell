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
	CSVRead csv = new CSVRead();
	Random rand = new Random();
	
	String artikel = "Roccat Kone, Roccat Pyro, Sharkoon SKILLER, Razer Huntsman, Logitech G502 HERO, Roccat Kain 120, "
			+ "Microsoft Xbox Wireless Controller, ASUS ROG Chakram, Keychron K3, EVGA Z15, Logitech PRO X, "
			+ "Razer Mamba, Logitech MX Master, Corsair K57, Roccat Vulcan, Logitech G815, Sony Pulse 3D, Logitech G935,"
			+ " SteelSeries Archtis, Turtle Beach Recon 500, Logitech G PRO X, Razer Kraken, Shure SM7B, Creative SoundBlaster X4, "
			+ "Cooler Master MH670, Elgato Wave:3, HP X1000, SteelSeries Arctis 9, Rode NT-USB Mini, Razer Blackshart V2, "
			+ "Jabra Evolve2 75, Sony WF-1000XM4";

	
	public Produkt generiereProduktC(Produkt p, boolean saisonal, int i, int perioden, Lager lager) throws CsvValidationException, IOException {
	
		List<Integer> verbrauchsListe = new ArrayList<Integer>();
		List<String> namenListe = new ArrayList<String>();	
		namenListe = csv.lese(artikel, namenListe);	
	
		int selector = rand.nextInt(namenListe.size())+0;		
	
		String name = namenListe.get(selector);		
	
		p.setName(name);
		p.setBestellfix((rand.nextInt(15000)+1000) + (double) Math.round((rand.nextDouble()+0)*100)/100);		
		p.setEinstand((rand.nextInt(200) + 3) + (double) Math.round((rand.nextFloat()+0)*100)/100);
		p.setFehlmengenkosten((rand.nextInt(50)+1) + ((double) Math.round((rand.nextDouble() + 0)*100)/100));
		p.setLagerkostensatz((rand.nextInt(10)+0) + (double) Math.round((rand.nextDouble() + 0)*100)/100);
		p.setMinBestand(rand.nextInt(10) + 0);
		p.setMaxBestand(rand.nextInt(5000) + p.getMinBestand());
		p.setvProdukt((double) Math.round((rand.nextDouble() + 0)*100)/100);
		if(saisonal) {
			int verbrauch = rand.nextInt(1000)+0;
			verbrauchsListe = IntervallAufteilung.teileIntervall(perioden, verbrauch);
		}else {		
			for(int n = 0; n < perioden; n++) {
				p.setVerbrauch(rand.nextInt(1000)+ 0);
				verbrauchsListe.add(p.getVerbrauch());
			}
		}
		p.setVerbraeuche(verbrauchsListe);	
		return p;		
	}

	public String doppelterName() {	
		List<String> namenListe = new ArrayList<String>();			
		namenListe = csv.lese(artikel, namenListe);				
		int selector = rand.nextInt(namenListe.size())+0;		
		String name = namenListe.get(selector);			
		return name;		
	}
		
	public List<String> getProduktNamen(){
		List<String> produktNamen = new ArrayList<String>();
		produktNamen = csv.lese(artikel, produktNamen);
		System.out.println("C" + produktNamen.size());
		return produktNamen;
	}
}