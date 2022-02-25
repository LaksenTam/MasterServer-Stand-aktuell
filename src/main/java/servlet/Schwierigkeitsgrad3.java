package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Highscore;
import data.Lager;
import data.Produkt;
import data.Produktergebnis;
import data.Userergebnis;
import datenbank.Datenbank;
import datenbank.UserDatenbank;
import manager.DatenManager;
import utility.CalcHighscore;
import utility.CheckFeasible;
import utility.CheckTime;

/**
 * Servlet implementation class Schwierigkeitsgrad3
 */
@WebServlet("/Schwierigkeitsgrad3")
public class Schwierigkeitsgrad3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Schwierigkeitsgrad3() {
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
		Produktergebnis pe = new Produktergebnis();
		DatenManager daten = new DatenManager();
		CheckFeasible feasible = new CheckFeasible();
		Datenbank db = new Datenbank();
		CheckTime check = new CheckTime();
		CalcHighscore score = new CalcHighscore();
		UserDatenbank user = new UserDatenbank();
		
		long stempel = System.currentTimeMillis();
		PrintWriter pw = response.getWriter();
		response.setContentType("text/json");
		try {
			ue = daten.ergebnis(ergebnis, ue);
			produktListe = daten.produktListePeriode(produktListe, pe.getPeriode());
			if(check.testeStempel(stempel, ue.getAPI_KEY())) {	
				Lager lager = db.lagerAbrufen();
				
					daten.userErgebnisSpeichern(ue, 3);
					if(pe.getPeriode() != daten.getPeriodenAnzahl()) {
						String jsonString = daten.dataToJson(produktListe);
						pw.print(jsonString);
						pw.flush();
						pw.close();		
				
					}else {
						if(feasible.isFeasible(ue.getProdukte(),lager,produktListe )) {
							pw.print("Geschafft!");
							pw.flush();
							pw.close();
							Highscore highscore = score.berechneHighscore(ue.getProdukte());
							long endstamp = check.berechneZeit(ue.getAPI_KEY());
							highscore.setTime(endstamp);
							highscore.setId(UUID.randomUUID().toString());
							user.saveHighScore(highscore, ue, 3);
							user.produktErgebnisGesamtSpeicher(ue, highscore.getId());					
					}
				}
			}else {
				pw.print("Optimiere deinen Algorithmus");
				pw.flush();
				pw.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			pw.print("Da ist etwas schiefgelaufen bitte ueberpruefe deine Angaben");
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
