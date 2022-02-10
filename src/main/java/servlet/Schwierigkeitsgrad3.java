package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
		DatenManager daten = new DatenManager();
		long stempel = System.currentTimeMillis();
		PrintWriter pw = response.getWriter();
		response.setContentType("text/json");
		try {
			ue = daten.ergebnis(ergebnis, ue);
			produktListe = daten.produktListePeriode(produktListe, ue.getPeriode());
			if(daten.ueberpruefeZeit(stempel, ue.getPeriode(), ue.getAPI_KEY())) {	
				//CHECK FEASIBLE
				daten.userErgebnisSpeichern(ue, 3);
				if(ue.getPeriode() != daten.getPeriodenAnzahl()) {
					String jsonString = daten.dataToJson(produktListe);
					pw.print(jsonString);
					pw.flush();
					pw.close();
					//speicher daten in DB
				
				}else {
					pw.print("Geschafft!");
					pw.flush();
					pw.close();
					//speicher die Daten in der DB
					//berechne Highscore
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
