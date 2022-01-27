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
		
		PrintWriter pw = response.getWriter();
		
		response.setContentType("text/json");
		
		try {
			ue = daten.ergebnis(ergebnis, ue);
			produktListe = daten.produktListePeriode(produktListe, ue.getPeriode());
			//f�r schwierigkeitgrad 2 muss die zeit nicht gespeichert werden
			daten.userErgebnisSpeichern(ue, System.currentTimeMillis());
			if(ue.getPeriode()!= daten.getPeriodenAnzahl()) {
				String jsonString = daten.dataToJson(produktListe);
				pw.print(jsonString);
				pw.flush();
				pw.close();
			}else {
				//berechne Highscore
				pw.print("Geschafft!");
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
