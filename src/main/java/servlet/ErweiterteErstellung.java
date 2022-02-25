package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ErweiterteProdukte;
import data.Lager;
import data.Produkt;
import data.Spielregeln;
import manager.DatenManager;
import produkterstellung.AProdukt;
import produkterstellung.BProdukt;
import produkterstellung.CProdukt;
import utility.CheckName;

/**
 * Servlet implementation class ErweiterteErstellung
 */
@WebServlet("/ErweiterteErstellung")
public class ErweiterteErstellung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErweiterteErstellung() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String anzPerioden = request.getParameter("perioden");
		
		String aPro = request.getParameter("aprodukt");
		String bPro = request.getParameter("bprodukt");
		String cPro = request.getParameter("cprodukt");
		
		String saiA = request.getParameter("saisonalA");
		String saiB = request.getParameter("saisonalB");
		String saiC = request.getParameter("saisonalC");
	
		String konstA = request.getParameter("konstantA");
		String konstB = request.getParameter("konstantB");
		String konstC = request.getParameter("konstantC");
		System.out.println(konstB);
		
		String lagerKostenA = request.getParameter("lagerKostenA");
		String lagerKostenB = request.getParameter("lagerKostenB");
		String lagerKostenC = request.getParameter("lagerKostenC");
		
		String einstandMinA = request.getParameter("einstandMinA");
		String einstandMinB = request.getParameter("einstandMinB");
		String einstandMinC = request.getParameter("einstandMinC");
		
		String einstandMaxA = request.getParameter("einstandMaxA");
		String einstandMaxB = request.getParameter("einstandMaxB");
		String einstandMaxC = request.getParameter("einstandMaxC");
		
		String bestellFixMinA = request.getParameter("bestellFixMinA");
		String bestellFixMinB = request.getParameter("bestellFixMinB");
		String bestellFixMinC = request.getParameter("bestellFixMinC");
		
		String bestellFixMaxA = request.getParameter("bestellFixMaxA");
		String bestellFixMaxB = request.getParameter("bestellFixMaxB");
		String bestellFixMaxC = request.getParameter("bestellFixMaxC");
		
		String fehlkostenMinA = request.getParameter("fehlkostenMinA");
		String fehlkostenMinB = request.getParameter("fehlkostenMinB");
		String fehlkostenMinC = request.getParameter("fehlkostenMinC");
		
		String fehlkostenMaxA = request.getParameter("fehlkostenMaxA");
		String fehlkostenMaxB = request.getParameter("fehlkostenMaxB");
		String fehlkostenMaxC = request.getParameter("fehlkostenMaxC");
		
		String kapBindung = request.getParameter("kapBindung");
		String lagerVol = request.getParameter("lagerVol");
		String zeit = request.getParameter("zeit");
		String sammelB = request.getParameter("sammelB");
		String sammel = request.getParameter("isSammel");
		
		boolean isSammel =false;
		double sammelKosten = 0.00;
		
		if(sammel !=null) {
			isSammel = true;
			sammelKosten = Double.parseDouble(sammelB);
		}

		
		try {
		
		//Parse Strings to Integer
		int perioden = Integer.parseInt(anzPerioden);
		int aProdukt = 0;
		int bProdukt = 0;
		int cProdukt = 0;
		int saisonalA = 0;
		int saisonalB = 0;
		int saisonalC = 0;
		int konstantA = 0;
		int konstantB = 0;
		int konstantC = 0;
		
		
		if(aPro != "") {
			aProdukt = Integer.parseInt(aPro);
		}
		if(bPro != "") {
			bProdukt = Integer.parseInt(bPro);
		}
		if(cPro != "") {
			 cProdukt = Integer.parseInt(cPro);
		}
		if(saiA != "") {
			saisonalA = Integer.parseInt(saiA);
		}
		if(saiB != "") {
			saisonalB = Integer.parseInt(saiB);
		}
		if(saiC != "") {
			saisonalC = Integer.parseInt(saiC);
		}
		
		if(konstA != "") {
			konstantA = Integer.parseInt(konstA);
		}
		if(konstB != "") {
			konstantB = Integer.parseInt(konstB);
		}
		if(konstC != "") {
			konstantC = Integer.parseInt(konstC);
		}		
	
		DatenManager daten = new DatenManager();
		ErweiterteProdukte prod = new ErweiterteProdukte();
		
		if(lagerKostenA != "" && bestellFixMinA !="" && bestellFixMaxA !="" && einstandMinA !="" && einstandMaxA != "" && fehlkostenMinA != "" && fehlkostenMaxA != "") {
			prod.setLagersatz(Double.parseDouble(lagerKostenA));
			prod.setBestellMinRange(Integer.parseInt(bestellFixMinA));
			prod.setBestellMaxRange(Integer.parseInt(bestellFixMaxA));
			prod.setEinstandminRange(Integer.parseInt(einstandMinA));
			prod.setEinstandmaxRange(Integer.parseInt(einstandMaxA));	
			prod.setFehlkostenMinRange(Integer.parseInt(fehlkostenMinA));
			prod.setFehlkostenMaxRange(Integer.parseInt(fehlkostenMaxA));
			System.out.println("A-Produkt");
			System.out.println(prod.toString());
		}		

		List<Produkt> produkte = new ArrayList<Produkt>();
		Lager lager = new Lager();
		//LagerGenerierung lagerGen = new LagerGenerierung();
		//lagerGen.erstelleLager(lager, kap, vol);
		lager.setKbindung(Float.parseFloat(kapBindung));
		lager.setLagerVol(Float.parseFloat(lagerVol));
		lager.setPer(Integer.parseInt(anzPerioden));
		//lager.setSammelbkosten(Float.parseFloat(sammelB));
		boolean saison = false;
		boolean konstant = false;
		

		int createAProdukte = aProdukt + saisonalA + konstantA;
		int createBProdukte = bProdukt + saisonalB + konstantB;
		int createCProdukte = cProdukt + saisonalC + konstantC;
		
		List<Produkt> checkName = new ArrayList<Produkt>();
		//Erstelle A Produkte
		
		for(int i=0; i<createAProdukte; i++) {
			Produkt p = new Produkt();
			AProdukt ap = new AProdukt();
			if(saisonalA>0) {
				saison = true;
				konstant = false;
				ap.generiereProduktA(p, saison,konstant, perioden, lager,true, prod);
				saisonalA -= 1;
			}else if(konstantA>0) {
				saison = false;
				konstant = true;
				ap.generiereProduktA(p, saison,konstant, perioden, lager,true, prod);
				konstantA -=1;
			}else {
				saison =false;
				konstant = false;
				ap.generiereProduktA(p, saison,konstant, perioden, lager,true, prod);
			}
			checkName.add(p);
		}
		
		AProdukt nameCheckA = new AProdukt();
		
		List<String> produktNamen = nameCheckA.getProduktNamen();
		CheckName dupe = new CheckName();
		checkName = dupe.checkNameForDup(produktNamen, checkName);
		produkte.addAll(checkName);
		checkName.clear();
		produktNamen.clear();
		
		/**
		 * Erstelle B-Produkte
		 */
		
		if(lagerKostenB != "" && bestellFixMinB !="" && bestellFixMaxB !="" && einstandMinB !="" && 
				einstandMaxB != "" && fehlkostenMinB != "" && fehlkostenMaxB != "") {
			prod.setLagersatz(Integer.parseInt(lagerKostenB));
			prod.setBestellMinRange(Integer.parseInt(bestellFixMinB));
			prod.setBestellMaxRange(Integer.parseInt(bestellFixMaxB));
			prod.setEinstandminRange(Integer.parseInt(einstandMinB));
			prod.setEinstandmaxRange(Integer.parseInt(einstandMaxB));		
			prod.setFehlkostenMinRange(Integer.parseInt(fehlkostenMinB));
			prod.setFehlkostenMaxRange(Integer.parseInt(fehlkostenMaxB));
			System.out.println("B-Produkt");
			System.out.println(prod.toString());
		}
		
		for(int j = 0; j<createBProdukte;j++) {
			Produkt p = new Produkt();
			BProdukt bp = new BProdukt();
			if(saisonalB>0) {
				saison = true;
				konstant = false;
				bp.generiereProduktB(p, saison,konstant, saisonalC, perioden, lager, true, prod);
				saisonalB -=1;
			}else if(konstantB>0) {
				konstant = true;
				saison = false;
				bp.generiereProduktB(p, saison,konstant, saisonalC, perioden, lager, true, prod);
				konstantB -=1;
			}else {
				saison = false;
				konstant = false;
				bp.generiereProduktB(p, saison,konstant, saisonalC, perioden, lager,true ,prod);
			}
			checkName.add(p);
			
		}
		
		/**
		 * Prüfe Namen
		 */
		
		BProdukt nameCheckB = new BProdukt();
		System.out.println(checkName.size());
		for(Produkt name: checkName) {
			System.out.println(name.getName());
		}
		produktNamen = nameCheckB.getProduktNamen();
		
		checkName = dupe.checkNameForDup(produktNamen, checkName);
		produkte.addAll(checkName);
		checkName.clear();
		produktNamen.clear();
		
		/**
		 * Erstelle C Produkte
		 */
		if(lagerKostenC != "" && bestellFixMinC !="" && bestellFixMaxC !="" && einstandMinC !="" && einstandMaxC != "" && fehlkostenMinC != "" && fehlkostenMaxC != "") {
			prod.setLagersatz(Integer.parseInt(lagerKostenC));
			prod.setBestellMinRange(Integer.parseInt(bestellFixMinC));
			prod.setBestellMaxRange(Integer.parseInt(bestellFixMaxC));
			prod.setEinstandminRange(Integer.parseInt(einstandMinC));
			prod.setEinstandmaxRange(Integer.parseInt(einstandMaxC));		
			prod.setFehlkostenMinRange(Integer.parseInt(fehlkostenMinC));
			prod.setFehlkostenMaxRange(Integer.parseInt(fehlkostenMaxC));
			System.out.println("C-Produkt");
			System.out.println(prod.toString());
		}

		for(int k = 0;k<createCProdukte;k++) {
			Produkt p = new Produkt();
			CProdukt cp = new CProdukt();
			if(saisonalC>0) {
				saison = true;
				konstant = false;
				cp.generiereProduktC(p, saison,konstant, saisonalC, perioden, lager, true, prod);
				saisonalC -=1;
			}else if(konstantC>0) {
				konstant = true;
				saison = false;
				cp.generiereProduktC(p, saison,konstant, saisonalC, perioden, lager, true, prod);
				konstantC -=1;
			}else {
				saison =false;
				konstant = false;
				cp.generiereProduktC(p, saison,konstant, saisonalC, perioden, lager, true, prod);
			}
			checkName.add(p);
		}
		/**
		 * Prüfe Namen
		 */
		
		CProdukt nameCheckC = new CProdukt();
		produktNamen = nameCheckC.getProduktNamen();
		checkName = dupe.checkNameForDup(produktNamen, checkName);
		produkte.addAll(checkName);
		checkName.clear();
		produktNamen.clear();
		
		Spielregeln spiel = new Spielregeln();
		spiel.setZeit(Long.parseLong(zeit));
		spiel.setSammelbestellung(isSammel);
		spiel.setSammelKosten(sammelKosten);
		
		String identifier = UUID.randomUUID().toString();
		daten.produktSpeichern(produkte, identifier);
		daten.problemInstanzDatenSpeichern(produkte.size(), perioden, identifier, lager, spiel);
		System.out.println(Arrays.asList(produkte));		
		response.sendRedirect("ProblemInstanzAnzeigen?");
		
		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Ein unerwarteter Fehler ist aufgetreten");
			String fehler = "Ein unerwarteter Fehler ist aufgetreten";
			request.setAttribute("fehler", fehler);
			request.getRequestDispatcher("adminpanel.jsp").forward(request, response);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}