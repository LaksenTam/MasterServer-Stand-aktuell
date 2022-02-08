package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Highscore;
import data.Produktergebnis;
import data.Userergebnis;
import datenbank.UserDatenbank;
import json.ErgebnisDeserializer;
import utility.CalcHighscore;
import utility.CheckTime;

/**
 * Servlet implementation class ErgebnisTestServ
 */
@WebServlet("/ErgebnisTestServ")
public class ErgebnisTestServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
      CalcHighscore score = new CalcHighscore();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErgebnisTestServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CheckTime check = new CheckTime();
		UserDatenbank db = new UserDatenbank();
		String ergebnis = request.getParameter("json");
		Userergebnis u = new Userergebnis();
		ErgebnisDeserializer ed = new ErgebnisDeserializer();		
		u = ed.deserializeJsonInput(ergebnis, u);
		List<Produktergebnis> p = u.getProdukte();
		try {			
			Highscore highscore = score.berechneHighscore(p);	
			System.out.println(u.getAPI_KEY());
			long endstamp = check.berechneZeit(u.getAPI_KEY());
			highscore.setTime(endstamp);
			db.saveHighScore(highscore, u);
			db.produktErgebnisGesamtSpeicher(u);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		pw.print("Daten gesendet: " );
		pw.print(u.toString());
		pw.flush();
		pw.close();
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
