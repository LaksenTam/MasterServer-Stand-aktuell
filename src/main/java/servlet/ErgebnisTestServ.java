package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Produktergebnis;
import data.Userergebnis;
import datenbank.UserDatenbank;
import json.ErgebnisDeserializer;
import utility.Highscore;

/**
 * Servlet implementation class ErgebnisTestServ
 */
@WebServlet("/ErgebnisTestServ")
public class ErgebnisTestServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
      Highscore score = new Highscore();
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
		UserDatenbank db = new UserDatenbank();
		String ergebnis = request.getParameter("json");
		System.out.println(ergebnis);
		Userergebnis u = new Userergebnis();
		ErgebnisDeserializer ed = new ErgebnisDeserializer();		
		u = ed.deserializeJsonInput(ergebnis, u);
		List<Produktergebnis> p = u.getProdukte();
		try {
			score.berechneHighscore(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		
		//db.produktErgebnisGesamtSpeicher(u);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
