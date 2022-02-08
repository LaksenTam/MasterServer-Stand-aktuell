package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Produkt;
import datenbank.Datenbank;
import datenbank.UserDatenbank;
import utility.CalcVolumen;
import utility.DrawChart;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Datenbank db = new Datenbank();
		DrawChart chart = new DrawChart();
		CalcVolumen vol = new CalcVolumen();
		String key = (String) session.getAttribute("key");
		System.out.println(key);
		UserDatenbank udb = new UserDatenbank();
		try {		
			List<Produkt> produktDaten = db.getVerbrauchsListe();
			List<String[]> userData = udb.userErgebnis(key);
			List<String[]> volumen = vol.calcVolumen(produktDaten, userData);
			String drawVol = chart.drawVolumenVerlauf(volumen);
			String drawKosten = chart.drawKosten(userData);
			String drawBestand = chart.drawBestandsVerlauf(userData);	
			String drawHighscore = chart.drawScores(userData);
			request.setAttribute("drawHighscore", drawHighscore);
			request.setAttribute("drawVol", drawVol);
			request.setAttribute("drawBestand", drawBestand);
			request.setAttribute("drawKosten", drawKosten);
			request.getRequestDispatcher("profil.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
