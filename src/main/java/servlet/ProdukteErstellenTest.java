package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Lager;
import data.Produkt;
import produkterstellung.*;

/**
 * Servlet implementation class ProdukteErstellenTest
 */
@WebServlet("/ProdukteErstellenTest")
public class ProdukteErstellenTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdukteErstellenTest() {
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
		
		try {
		
		//Parse Strings to Integer
		int perioden = Integer.parseInt(anzPerioden);
		int aProdukt = 0;
		int bProdukt = 0;
		int cProdukt = 0;
		int saisonalA = 0;
		int saisonalB = 0;
		int saisonalC = 0;
		
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
		
		System.out.println(vol);
		System.out.println(kap);
		
		List<Produkt> produkte = new ArrayList<Produkt>();		
		Lager lager = new Lager();
		LagerGenerierung lagerGen = new LagerGenerierung();
		lagerGen.erstelleLager(lager, kap, vol);
	
		System.out.println("Volumen: " + lager.getLagerVol() +" Kapitalbindung: " + lager.getKbindung());
		
		//Generierung des Lagers
		
		int counter = 0;
		boolean saison = false;		
			for(int j = 0; j < aProdukt;j++) {				
				Produkt p = new Produkt();
				AProdukt ap = new AProdukt();
				if(saisonalA>0) {
					saison = true;
					ap.generiereProduktA(p, saison, counter, perioden, lager);
					saisonalA -= 1;
					System.out.println("Saisonales A Produkt erstellt!");

				}else {
					saison = false;
					ap.generiereProduktA(p, saison, counter, perioden, lager);
				}				
				produkte.add(p);				
				counter++;
				System.out.println("A: " + counter);
			}
			for(int n = 0; n< bProdukt; n++) {
				Produkt p = new Produkt();
				BProdukt bp = new BProdukt();
				if(saisonalB>0) {
					saison = true;
					bp.generiereProduktB(p,saison, counter, perioden,lager);
					saisonalB -=1;
					System.out.println("Saisonales B Produkt erstellt!");

				}else {
					saison = false;
					bp.generiereProduktB(p,saison, counter, perioden,lager);

				}
				produkte.add(p);				
				counter++;
				System.out.println("B: " + counter);
			}
			for(int c = 0; c<cProdukt; c++) {
				Produkt p = new Produkt();
				CProdukt cp = new CProdukt();
				if(saisonalC>0) {
					saison = true;
					cp.generiereProduktC(p,saison, counter, perioden,lager);
					saisonalC -=1;
					System.out.println("SaisonalesC Produkt erstellt!");

				}else {
					saison = false;
					cp.generiereProduktC(p,saison, counter, perioden,lager);
				}
				
				produkte.add(p);
				counter++;
				System.out.println("C: " + counter);			
			}
		
		System.out.println(Arrays.asList(produkte));
		request.getRequestDispatcher("adminpanel.jsp").forward(request, response);
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