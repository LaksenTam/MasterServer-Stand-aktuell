package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Produkt;
import datenbank.Datenbank;
import datenbank.UserDatenbank;
import utility.CalcVolumen;
import utility.DrawChart;
import utility.NumFormat;

/**
 * Servlet implementation class Chart
 */
@WebServlet("/Chart")
public class Chart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String produktNamen = "";
		UserDatenbank user = new UserDatenbank();
		DrawChart chart = new DrawChart();
		Datenbank db = new Datenbank();
		CalcVolumen vol = new CalcVolumen();
		
				
		try {
			List<Produkt> produktDaten = db.getVerbrauchsListe();
			List<String[]> ergebnis = user.scoreData(uid);
			List<String[]> volumen = vol.calcVolumen(produktDaten, ergebnis);			
			produktNamen = chart.produktname(ergebnis);			
			List<String> hsData = NumFormat.seperatekv(produktDaten, ergebnis);	

			
			int perioden = Integer.parseInt(ergebnis.get(ergebnis.size()-1)[6]);
			
			String drawStackedBestand = chart.drawStackedBestand(ergebnis, perioden);
		
			
			
			String drawKosten = chart.drawStackedKosten(ergebnis,perioden);
			String drawVol = chart.drawStackedVolumen(volumen,perioden);
			System.out.println(drawVol);
			String drawHighscore = chart.drawScores(hsData);
			request.setAttribute("produktNamen", produktNamen);
			request.setAttribute("drawHighscore", drawHighscore);
			request.setAttribute("drawVol", drawVol);
			request.setAttribute("drawKosten", drawKosten);
			request.setAttribute("drawBestand", drawStackedBestand);
			request.getRequestDispatcher("drawbest.jsp").forward(request, response);
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
