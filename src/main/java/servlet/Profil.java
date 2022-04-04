package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Highscore;
import data.Produkt;
import datenbank.Datenbank;
import datenbank.UserDatenbank;
import utility.CalcBestandsVerlauf;
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
		UserDatenbank udb = new UserDatenbank();
		CalcBestandsVerlauf bestand = new CalcBestandsVerlauf();
		try {		
			List<Produkt> produktDaten = db.getVerbrauchsListe();				
			List<Highscore> scores = udb.userScores(key);	
			Highscore userScore = udb.userScoreList(key);
					
			request.setAttribute("scores", scores);	
			session.setAttribute("pList", produktDaten);
			List<String[]> userData = udb.userErgebnis(userScore.getId());
			List<String[]> volumen = vol.calcVolumen(produktDaten, userData);				
			List<String[]> bestScore = udb.getBestUser();			
			List<String[]> stackedUserData = udb.getUserDataPerPeriod(userScore.getId());		
			int perioden = Integer.parseInt(stackedUserData.get(stackedUserData.size()-1)[6]);
			
			
			if(bestScore.size()!=0) {
				List<String[]> bestScoreErgebnis = udb.getBestUserErgebnis(bestScore.get(0)[4]);				
				List<String[]> highVol = vol.calcHighVolumen(produktDaten, bestScoreErgebnis);
				String drawHighBestand = chart.drawHighStackedBestand(bestScoreErgebnis,perioden);
				String drawHighKosten = chart.drawHighStackedKosten(bestScoreErgebnis,perioden);
				String drawHighVol = chart.drawStackedVolumen(highVol,perioden);
				String drawHighscoreScore = chart.drawHighScoreLeader(bestScore);	
				
				for(int i=0; i<bestScoreErgebnis.size();i++) {
					System.out.println(Arrays.asList(bestScoreErgebnis.get(i)));
				}
				
				request.setAttribute("drawHighBestand", drawHighBestand);
				request.setAttribute("drawHighKosten", drawHighKosten);
				request.setAttribute("drawHighscoreScore", drawHighscoreScore);
				request.setAttribute("drawHighVol", drawHighVol);		
				
				
			}		
			if(userData.size()!= 0) {
				List<String[]> bestandsverlauf = bestand.bestandsVerlauf(userData,produktDaten);				
				String drawHighscore = chart.drawScores(userData);
				String drawBestandsVerlauf = chart.drawGesamtBestandVerlauf(bestandsverlauf);				
				String drawStackedKosten = chart.drawStackedKosten(stackedUserData, perioden);
				String produktNamen = chart.produktname(stackedUserData);
				String stackedBestand = chart.drawStackedBestand(stackedUserData, perioden);
				String stackedVolumen = chart.drawStackedVolumen(volumen, perioden);
				
				request.setAttribute("stackedBestand", stackedBestand);
				request.setAttribute("userBestandsVerlauf", drawBestandsVerlauf);
				request.setAttribute("drawHighscore", drawHighscore);
				request.setAttribute("stackedVolumen", stackedVolumen);
				request.setAttribute("produktNamen", produktNamen);
				request.setAttribute("drawStackedKosten", drawStackedKosten);
			}
			
			
			request.getRequestDispatcher("profil.jsp").forward(request, response);
		} catch (SQLException | IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("fehler", "Es sind keine Ergebnisse vorhanden!");
			request.getRequestDispatcher("profil.jsp").forward(request, response);

		}		
	}

	/**
	 * For Tag ProfilDropwDown
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDatenbank user = new UserDatenbank();
		DrawChart chart = new DrawChart();
		CalcVolumen vol = new CalcVolumen();
		
		@SuppressWarnings("unchecked")
		List<Produkt> pList = (List<Produkt>) session.getAttribute("pList");
		String key = (String) session.getAttribute("key");
		String score1 = request.getParameter("score1");
		String score2 = request.getParameter("score2");
		List<Highscore> scores = user.userScores(key);
		List<String[]> score1data = user.scoreData(score1);
		List<String[]> score2data = user.scoreData(score2);
		
		List<String[]> volScore1 = vol.calcVolumen(pList, score1data);	
		List<String[]> volScore2 = vol.calcVolumen(pList, score2data);

		String drawScore1Bestand =  chart.drawBestandsVerlauf(score1data);
		String drawScore2Bestand = chart.drawBestandsVerlauf(score2data);
		String drawScore1Kosten = chart.drawKosten(score1data);
		String drawScore2Kosten = chart.drawKosten(score2data);
		String drawHighScore1 = chart.drawScores(score1data);
		String drawHighScore2 = chart.drawScores(score2data);
		String drawVolScore1 = chart.drawVolumenVerlauf(volScore1);
		String drawVolScore2 = chart.drawVolumenVerlauf(volScore2);
		
		/*
		 * Linke Seite
		 */
		request.setAttribute("drawBestand", drawScore1Bestand);		
		request.setAttribute("drawKosten", drawScore1Kosten);
		request.setAttribute("drawHighscore",drawHighScore1 );
		request.setAttribute("drawVol", drawVolScore1);
		
		
		/**
		 * Rechte Seite
		 */
		request.setAttribute("drawHighscoreScore", drawHighScore2);
		request.setAttribute("drawHighVol", drawVolScore2);
		request.setAttribute("drawHighBestand", drawScore2Bestand);
		request.setAttribute("drawHighKosten", drawScore2Kosten);
		
		
		
		request.setAttribute("scores", scores);
		request.getRequestDispatcher("profil.jsp").forward(request, response);

	}

}
