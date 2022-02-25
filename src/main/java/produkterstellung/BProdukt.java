package produkterstellung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data.ErweiterteProdukte;
import data.Lager;
import data.Produkt;
import utility.CSVRead;
import utility.IntervallAufteilung;

public class BProdukt {
	
	CSVRead csv = new CSVRead();
	Random rand = new Random();
	
	

	String artikel = "ASUS ROG STRIX B550, ASUS ROB STRIX Z690, MSI MEG Z690, GeForce RTX 3060, GeForce RTX 3070, "
			+ "XFX Radeon RX 6600, Corsair DIMM 32GB, G.Skill 32GB, BenQ EW3270, MSI Optix, AORUS FI27Q, "
			+ "SAMSUNG Odyssey, BenQ Zowie, GIGAByte G27F, iiyama G-Master, Acer Nitro, Alienware AW2720, "
			+ "SAMSUNG Galaxy S20, ASUS Zenfone, OnePlus 8, Xiaomi 11T, Xiaomi Mi 11, SAMSUNG GU-50, "
			+ "Sony Bravia KD50, Hisense 40A, Grundig 49, LG Eletronics 43UP, Nokia 4300A, Panasonic TX-58, Hitachi U50, "
			+ "Xiaomi Mi TV, ChiQ U55, Toshiba 50U, Roborock S5 Max, Dyson V11, Xiaomi Mi Cleaner, EXOVACS DEEBOT OZMO, "
			+ "Xiaomi Dreame D9, Xiaomi Mi Mop, Xiaomi ROIDMI EVE, iRobot Roomba i3, Canon EOS 2000D, Olympus E-M10";

	
	public Produkt generiereProduktB(Produkt p, boolean saisonal,boolean konstant, int i, int perioden,
			Lager lager, boolean erweitert, ErweiterteProdukte prod) throws IOException {
		
		List<Integer> verbrauchsListe = new ArrayList<Integer>();
		
		
		
		List<String> namenListe = new ArrayList<String>();
		
		namenListe = csv.lese(artikel, namenListe);
		
		int selector = rand.nextInt(namenListe.size())+0;		
		
		String name = namenListe.get(selector);	
		
		p.setName(name);
		if(erweitert) {
			p.setBestellfix((rand.nextInt(prod.getBestellMaxRange())+prod.getBestellMinRange()) + (double) Math.round((rand.nextDouble()+0)*100)/100);	
			System.out.println(p.getBestellfix());
			p.setEinstand((rand.nextInt(prod.getEinstandmaxRange()) + prod.getEinstandminRange()) + (double) Math.round((rand.nextFloat()+0)*100)/100);
			p.setFehlmengenkosten((rand.nextInt(prod.getFehlkostenMaxRange()) + prod.getFehlkostenMinRange())+(double) Math.round((rand.nextDouble() + 0)*100)/100);
			p.setLagerkostensatz((double) Math.round(p.getEinstand()*prod.getLagersatz()));
			p.setMinBestand(rand.nextInt(10) + 0);
			p.setMaxBestand(rand.nextInt(4000) + p.getMinBestand());		
			p.setvProdukt((double) Math.round((rand.nextDouble() + 0)*100)/100);
		}else {
			
			p.setBestellfix((rand.nextInt(20000)+1000) + (double) Math.round((rand.nextDouble()+0)*100)/100);	
			p.setEinstand((rand.nextInt(700) + 200) + (double) Math.round((rand.nextDouble()+0)*100)/100);
			p.setFehlmengenkosten((rand.nextInt(70) + 0)+ (double) Math.round((rand.nextDouble()+0)*100)/100);		
			p.setLagerkostensatz((rand.nextInt(10) + 0)+ (double) Math.round((rand.nextDouble()+0)*100)/100);
			System.out.println(p.getLagerkostensatz());
			p.setMinBestand(rand.nextInt(10) + 0);
			p.setMaxBestand(rand.nextInt(4000) + p.getMinBestand());
			p.setvProdukt((double) Math.round((rand.nextDouble() + 0)*100)/100);
		}
		
		if(saisonal) {
			int verbrauch = rand.nextInt(700)+0;
			verbrauchsListe = IntervallAufteilung.teileIntervall(perioden, verbrauch);
		}else if(konstant) {
			int verbrauch = rand.nextInt(700) +0;
			for(int k = 0;k<perioden;k++) {
				p.setVerbrauch(verbrauch);
				verbrauchsListe.add(p.getVerbrauch());
			}
		}else {			
			for(int n = 0; n < perioden; n++) {
				p.setVerbrauch(rand.nextInt(700) + 0);
				verbrauchsListe.add(p.getVerbrauch());
			}
		}
		p.setVerbraeuche(verbrauchsListe);		
		return p;
	}
	
	public List<String> getProduktNamen(){
		List<String> produktNamen = new ArrayList<String>();
		produktNamen = csv.lese(artikel, produktNamen);
		System.out.println(produktNamen.size());
		return produktNamen;
	}

}