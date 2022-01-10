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

public class BProdukt {
	
	public Produkt generiereProduktB(Produkt p, boolean saisonal, int i, int perioden, Lager lager) throws CsvValidationException, IOException {
		Random rand = new Random();
		List<Integer> verbrauchsListe = new ArrayList<Integer>();
		CSVRead csv = new CSVRead();
		
		String artikel = "ASUS ROG STRIX B550, ASUS ROB STRIX Z690, MSI MEG Z690, GeForce RTX 3060, GeForce RTX 3070, "
				+ "XFX Radeon RX 6600, Corsair DIMM 32GB, G.Skill 32GB, BenQ EW3270, MSI Optix, AORUS FI27Q, "
				+ "SAMSUNG Odyssey, BenQ Zowie, GIGAByte G27F, iiyama G-Master, Acer Nitro, Alienware AW2720, "
				+ "SAMSUNG Galaxy S20, ASUS Zenfone, OnePlus 8, Xiaomi 11T, Xiaomi Mi 11, SAMSUNG GU-50, "
				+ "Sony Bravia KD50, Hisense 40A, Grundig 49, LG Eletronics 43UP, Nokia 4300A, Panasonic TX-58, Hitachi U50, "
				+ "Xiaomi Mi TV, ChiQ U55, Toshiba 50U, Roborock S5 Max, Dyson V11, Xiaomi Mi Cleaner, EXOVACS DEEBOT OZMO, "
				+ "Xiaomi Dreame D9, Xiaomi Mi Mop, Xiaomi ROIDMI EVE, iRobot Roomba i3, Canon EOS 2000D, Olympus E-M10";

		
		List<String> namenListe = new ArrayList<String>();
		
		namenListe = csv.lese(artikel, namenListe);
		
		int selector = rand.nextInt(namenListe.size())+0;		
		
		String name = namenListe.get(selector);		
		
		p.setName(name);
		p.setBestellfix((double) Math.round((rand.nextDouble() + 0)*100)/100);		
		p.setEinstand((double) Math.round(((rand.nextInt(700) + 200) + (rand.nextFloat()+0))*100)/100);
		p.setFehlmengenkosten((double) Math.round((rand.nextDouble() + 0)*100)/100);
		p.setLagerkostensatz((double) Math.round((rand.nextDouble() + 0)*100)/100);
		p.setMaxBestand(rand.nextInt() + 0);
		p.setMinBestand(rand.nextInt() + 0);
		p.setvProdukt((double) Math.round((rand.nextDouble() + 0)*100)/100);
		if(saisonal) {
			int verbrauch = rand.nextInt(30000)+0;
			verbrauchsListe = IntervallAufteilung.teileIntervall(perioden, verbrauch);
		}else {
			
			for(int n = 0; n < perioden; n++) {
				p.setVerbrauch(rand.nextInt(20000) + 0);
				verbrauchsListe.add(p.getVerbrauch());
			}
		}
		p.setVerbraeuche(verbrauchsListe);		
		return p;
	}

}