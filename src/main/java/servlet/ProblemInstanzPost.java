package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import data.Produkt;
import data.Userergebnis;
import manager.DatenManager;

/**
 * Servlet implementation class ProblemInstanzPost
 */
@WebServlet("/ProblemInstanzPost")
public class ProblemInstanzPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProblemInstanzPost() {
        super();
        // TODO Auto-generated constructor stub
    }	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//Erhalte Request daten
		String ergebnis = request.getParameter("ergebnis");
		
		//initierre Liste zum Speichern der Ergebnisse
		DatenManager daten = new DatenManager();
		List<Produkt> produktListe = new ArrayList<Produkt>();		
		
		Userergebnis ue = new Userergebnis();		
		
		long currentDate = System.currentTimeMillis();
		PrintWriter pwriter = response.getWriter();
		response.setContentType("text/json");
		try {
			System.out.println("Der ERgebnisString: " + ergebnis);
			ue = daten.ergebnis(ergebnis, ue);	
			System.out.println("UserErgebnis: " + ue);
			produktListe = daten.produktListePeriode(produktListe,ue.getPeriode());		
			if(ue.getPeriode() != 0) {	
			
				if(daten.ueberpruefeZeit(currentDate, ue.getPeriode(), ue.getAPI_KEY())) {					
					daten.userErgebnisSpeichern(ue, currentDate);	
					if(ue.getPeriode() != daten.getPeriodenAnzahl()) {
						
						String jsonString = daten.dataToJson(produktListe);	
						pwriter.print(jsonString);
						pwriter.close();
					}else {						
						pwriter.print("Sie haben alle Perioden berechnet");
						pwriter.close();
						daten.berechneHighscore();
					}					
				}else {					
					pwriter.print("Optimiere deinen Algorithmus, du brauchst zu lange");
					pwriter.close();
				}
			}else {
				
				//daten.userErgebnisSpeichern(ue, currentDate);	
				produktListe = daten.getStartProblem(produktListe);
				String probInstanz = daten.dataToJson(produktListe);
				System.out.println(produktListe);
				pwriter.print(probInstanz);
				pwriter.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
			pwriter.print("Bitte ueberpruefe deinen JsonString");
			pwriter.close();
		}			
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Erhalte Request daten
				String ergebnis = request.getParameter("ergebnis");
				
				//initierre Liste zum Speichern der Ergebnisse
				DatenManager daten = new DatenManager();
				List<Produkt> produktListe = new ArrayList<Produkt>();		
				
				Userergebnis ue = new Userergebnis();		
				
				long currentDate = System.currentTimeMillis();
				PrintWriter pwriter = response.getWriter();
				response.setContentType("text/json");
				try {
					System.out.println("Der ERgebnisString: " + ergebnis);
					ue = daten.ergebnis(ergebnis, ue);	
					System.out.println("UserErgebnis: " + ue);
					produktListe = daten.produktListePeriode(produktListe,ue.getPeriode());		
					if(ue.getPeriode() != 0) {	
						System.out.println("If Nr 1");
						if(daten.ueberpruefeZeit(currentDate, ue.getPeriode(), ue.getAPI_KEY())) {
							System.out.println("IF 2");
							daten.userErgebnisSpeichern(ue, currentDate);	
							if(ue.getPeriode() != daten.getPeriodenAnzahl()) {
								System.out.println("If 3");
								String jsonString = daten.dataToJson(produktListe);	
								pwriter.print(jsonString);
								pwriter.close();
							}else {
								System.out.println("Else 1");
								pwriter.print("Sie haben alle Perioden berechnet");
								pwriter.close();
								daten.berechneHighscore();
							}
							
						}else {
							System.out.println("Else 2");
							pwriter.print("Optimiere deinen Algorithmus, du brauchst zu lange");
							pwriter.close();
						}
					}else {
						System.out.println("Else 3");
						//daten.userErgebnisSpeichern(ue, currentDate);	
						produktListe = daten.getStartProblem(produktListe);
						String probInstanz = daten.dataToJson(produktListe);
						System.out.println(produktListe);
						pwriter.print(probInstanz);
						pwriter.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
					pwriter.print("Bitte ueberpruefe deinen JsonString");
					pwriter.close();
				}			
			}
}