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

import data.Lager;
import data.Produkt;
import datenbank.Datenbank;
import json.DataToJson;

/**
 * Servlet implementation class ProduktListenAnfordern
 */
@WebServlet("/ProduktListenAnfordern")
public class ProduktListenAnfordern extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduktListenAnfordern() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Datenbank db = new Datenbank();
		DataToJson dj = new DataToJson();
		
		List<Produkt> produkte = new ArrayList<>();
		Lager lager = new Lager();
		
		try {
			produkte = db.produktListeAbrufen();
			lager = db.lagerAbrufen();
			PrintWriter pw = response.getWriter();
			
			response.setContentType("text/json");
			
			String ausgabe = dj.dataToJson(produkte);
			String lagerausgabe = dj.lagerToJson(lager); 
			System.out.println(ausgabe);
			pw.print(lagerausgabe + "?");
			
		
			pw.print(ausgabe);
			pw.close();
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
