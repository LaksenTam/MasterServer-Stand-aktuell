package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Highscore;
import data.Produkt;
import data.Produktergebnis;
import data.Userergebnis;
import datenbank.UserDatenbank;
import manager.DatenManager;
import utility.CalcHighscore;
import utility.CheckTime;

/**
 * Servlet implementation class Schwierigkeitsgrad2
 */
@WebServlet("/Schwierigkeitsgrad2")
public class Schwierigkeitsgrad2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Schwierigkeitsgrad2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ergebnis = request.getParameter("ergebnis");
		
		List<Produkt> produktListe = new ArrayList<Produkt>();
		
		Userergebnis ue = new Userergebnis();
		DatenManager daten = new DatenManager();
		CheckTime time = new CheckTime();
		UserDatenbank user = new UserDatenbank();
		CalcHighscore score = new CalcHighscore();
		CheckTime check = new CheckTime();
		Produktergebnis pe = new Produktergebnis();
		


		PrintWriter pw = response.getWriter();
		
		response.setContentType("text/json");
		
		try {
			ue = daten.ergebnis(ergebnis, ue);
			produktListe = daten.produktListePeriode(produktListe, pe.getPeriode());
			long stamp = System.currentTimeMillis();
			//Schwierigkeitsgrad 2 zeit
			if(time.testeStempel(stamp, ue.getAPI_KEY())) {
				daten.userErgebnisSpeichern(ue, 2);
				if(pe.getPeriode()!= daten.getPeriodenAnzahl()) {
					String jsonString = daten.dataToJson(produktListe);
					pw.print(jsonString);
					pw.flush();
					pw.close();
					user.insStempel(ue.getAPI_KEY(), stamp);
				}else {
					//berechne Highscore
					Highscore highscore = score.berechneHighscore(ue.getProdukte());
					long endstamp = check.berechneZeit(ue.getAPI_KEY());
					highscore.setTime(endstamp);
					highscore.setId(UUID.randomUUID().toString());
					user.saveHighScore(highscore, ue, 2);
					user.produktErgebnisGesamtSpeicher(ue, highscore.getId());
					pw.print("Geschafft!");
					pw.flush();
					pw.close();
				}
			}else {
				pw.print("Zu langsam, optimiere den Algorithmus");
				pw.flush();
				pw.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			pw.print("Da ist etwas schiefgelaufen bitte pruefe deine Angaben");
			pw.flush();
			pw.close();
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
