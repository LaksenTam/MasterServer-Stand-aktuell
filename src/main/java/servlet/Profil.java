package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import utility.NumFormat;

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
				List<String> bestHsData = NumFormat.seperatekvHigh(produktDaten, bestScore, bestScoreErgebnis);				
				String drawHighBestand = chart.drawHighStackedBestand(bestScoreErgebnis,perioden);
				String drawHighKosten = chart.drawHighStackedKosten(bestScoreErgebnis,perioden);
				String drawHighVol = chart.drawStackedVolumen(highVol,perioden);
				String drawHighscoreScore = chart.drawScores(bestHsData);									
				request.setAttribute("drawHighBestand", drawHighBestand);
				request.setAttribute("drawHighKosten", drawHighKosten);
				request.setAttribute("drawHighscoreScore", drawHighscoreScore);
				request.setAttribute("drawHighVol", drawHighVol);	
				bestScoreErgebnis = NumFormat.formatNum(bestScoreErgebnis);				
				request.setAttribute("resultList2", bestScoreErgebnis);								
			}		
			if(userData.size()!= 0) {				
				List<String[]> bestandsverlauf = bestand.bestandsVerlauf(userData,produktDaten);				
				List<String> hsData = NumFormat.seperatekv(produktDaten, stackedUserData);				
				String drawHighscore = chart.drawScores(hsData);
				String drawBestandsVerlauf = chart.drawGesamtBestandVerlauf(bestandsverlauf);				
				String drawStackedKosten = chart.drawStackedKosten(stackedUserData, perioden);				
				String stackedBestand = chart.drawStackedBestand(stackedUserData, perioden);
				String stackedVolumen = chart.drawStackedVolumen(volumen, perioden);		
				
				request.setAttribute("stackedBestand", stackedBestand);
				request.setAttribute("userBestandsVerlauf", drawBestandsVerlauf);
				request.setAttribute("drawHighscore", drawHighscore);
				request.setAttribute("stackedVolumen", stackedVolumen);			
				request.setAttribute("drawStackedKosten", drawStackedKosten);
			
				stackedUserData = NumFormat.formatNum(stackedUserData);
				
				ArrayList<String[]> results = new ArrayList<String[]>();
				
				for( int n = 0; n< stackedUserData.size();n++) {					
					String[] s= {
							stackedUserData.get(n)[0], stackedUserData.get(n)[1],stackedUserData.get(n)[2], stackedUserData.get(n)[6]
					};
					results.add(s);				
				}
				
				request.setAttribute("resultList", results);
				}
			
			
			
			String produktNamen = chart.produktname(stackedUserData);
			request.setAttribute("produktNamen", produktNamen);
			session.setAttribute("produktNames", produktNamen);
			request.setAttribute("columnNames", new String[] {"Produktname", "Bestellmenge", "Kosten", "Periode"});
			
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
		System.out.println(pList.size());
		String key = (String) session.getAttribute("key");
		String score1 = request.getParameter("score1");
		String score2 = request.getParameter("score2");
		String pNamen = (String) session.getAttribute("produktNames");		
		List<Highscore> scores = user.userScores(key);
		List<String[]> score1data = user.scoreData(score1);
		List<String[]> score2data = user.scoreData(score2);
		int perioden = 0;
		if(score1data.size()!= 0) {
			perioden = Integer.parseInt(score1data.get(score1data.size()-1)[6]);	
		}else {
			perioden = Integer.parseInt(score2data.get(score2data.size()-1)[6]);
		}
		System.out.println(score1 + " score2: " + score2);
		List<String[]> volScore1 = vol.calcVolumen(pList, score1data);	
		List<String[]> volScore2 = vol.calcVolumen(pList, score2data);
		List<String> hsData1=new ArrayList<>();
		List<String> hsData2 = new ArrayList<>();
		String drawHighScore1 = "";
		String drawHighScore2 = "";
		System.out.println(score1);
		
		if(!score1.equals("Auswahl")) {
			hsData1 = NumFormat.seperatekv(pList, score1data);	
			drawHighScore1 = chart.drawScores(hsData1);
			request.setAttribute("drawHighscore",drawHighScore1);
		}
		
		if(!score2.equals("Auswahl")) {
			hsData2 = NumFormat.seperatekv(pList, score2data);
			drawHighScore2 = chart.drawScores(hsData2);
			request.setAttribute("drawHighscoreScore",drawHighScore2 );

		}
		


		String drawScore1Bestand =  chart.drawStackedBestand(score1data,perioden);
		String drawScore2Bestand = chart.drawStackedBestand(score2data,perioden);
		String drawScore1Kosten = chart.drawStackedKosten(score1data,perioden);
		String drawScore2Kosten = chart.drawStackedKosten(score2data,perioden);		
		String drawVolScore1 = chart.drawStackedVolumen(volScore1,perioden);
		String drawVolScore2 = chart.drawStackedVolumen(volScore2,perioden);
		
		/*
		 * Linke Seite
		 */
		request.setAttribute("stackedBestand", drawScore1Bestand);		
		request.setAttribute("drawStackedKosten", drawScore1Kosten);		
		request.setAttribute("stackedVolumen", drawVolScore1);
		
		
		/**
		 * Rechte Seite
		 */
		
		request.setAttribute("drawHighVol", drawVolScore2);
		request.setAttribute("drawHighBestand", drawScore2Bestand);
		request.setAttribute("drawHighKosten", drawScore2Kosten);
		
		
		request.setAttribute("produktNamen", pNamen);
		request.setAttribute("scores", scores);
		request.getRequestDispatcher("profil.jsp").forward(request, response);

	}

}
