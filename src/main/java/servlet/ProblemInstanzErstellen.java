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

import data.Lager;
import data.Produkt;
import manager.DatenManager;
import produkterstellung.AProdukt;
import produkterstellung.BProdukt;
import produkterstellung.CProdukt;
import produkterstellung.LagerGenerierung;
import utility.CheckName;

/**
 * Servlet implementation class TestServ
 */
@WebServlet("/ProblemInstanzErstellen")
public class ProblemInstanzErstellen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProblemInstanzErstellen() {
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
		String vol = request.getParameter("lager");
		String kap = request.getParameter("kapital");
		String konstA = request.getParameter("kostantA");
		String konstB = request.getParameter("kostantB");
		String konstC = request.getParameter("kostantC");

		
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

		List<Produkt> produkte = new ArrayList<Produkt>();
		Lager lager = new Lager();
		LagerGenerierung lagerGen = new LagerGenerierung();
		lagerGen.erstelleLager(lager, kap, vol);

		boolean saison = false;
		boolean konstant = false;
		int counter = 0;

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
				ap.generiereProduktA(p, saison,konstant,counter, perioden, lager);
				saisonalA -= 1;
			}else if(konstantA>0) {
				saison = false;
				konstant = true;
				ap.generiereProduktA(p, saison,konstant,counter, perioden, lager);
				konstantA -=1;
			}else {
				saison =false;
				konstant = false;
				ap.generiereProduktA(p, saison,konstant, i, perioden, lager);
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
		
		for(int j = 0; j<createBProdukte;j++) {
			Produkt p = new Produkt();
			BProdukt bp = new BProdukt();
			if(saisonalB>0) {
				saison = true;
				konstant = false;
				bp.generiereProduktB(p, saison,konstant, saisonalC, perioden, lager);
				saisonalB -=1;
			}else if(konstantB>0) {
				konstant = true;
				saison = false;
				bp.generiereProduktB(p, saison,konstant, saisonalC, perioden, lager);
				konstantB -=1;
			}else {
				saison = false;
				konstant = false;
				bp.generiereProduktB(p, saison,konstant, saisonalC, perioden, lager);
			}
			checkName.add(p);
		}
		
		/**
		 * Prüfe Namen
		 */
		
		BProdukt nameCheckB = new BProdukt();
		produktNamen = nameCheckB.getProduktNamen();
		checkName = dupe.checkNameForDup(produktNamen, checkName);
		produkte.addAll(checkName);
		checkName.clear();
		produktNamen.clear();
		
		/**
		 * Erstelle C Produkte
		 */
		for(int k = 0;k<createCProdukte;k++) {
			Produkt p = new Produkt();
			CProdukt cp = new CProdukt();
			if(saisonalC>0) {
				saison = true;
				konstant = false;
				cp.generiereProduktC(p, saison,konstant, saisonalC, perioden, lager);
				saisonalC -=1;
			}else if(konstantC>0) {
				konstant = true;
				saison = false;
				cp.generiereProduktC(p, saison,konstant, saisonalC, perioden, lager);
				konstantC -=1;
			}else {
				saison =false;
				konstant = false;
				cp.generiereProduktC(p, saison,konstant, saisonalC, perioden, lager);
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
		
		String identifier = UUID.randomUUID().toString();
		daten.produktSpeichern(produkte, identifier);
		daten.problemInstanzDatenSpeichern(produkte.size(), perioden, identifier, lager);
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
}
