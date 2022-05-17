package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Produkt;
import data.Zwischenergebnis;
import datenbank.Datenbank;
import datenbank.UserDatenbank;
import json.DataToJson;
import json.ErgebnisDeserializer;

/**
 * Servlet implementation class verbrauchProProdukt
 * Schwierigkeitsgrad 1 erhalte lediglich ein angefragtes Produkt zurück
 */
@WebServlet("/Schwierigkeitsgrad1")
public class Schwierigkeitsgrad1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Schwierigkeitsgrad1() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long neuStempel = System.currentTimeMillis();
		Datenbank db = new Datenbank();
		DataToJson dj = new DataToJson();		
		UserDatenbank ud = new UserDatenbank();
		ErgebnisDeserializer ed = new ErgebnisDeserializer();
	
		String anfrage = request.getParameter("anfrage");
		System.out.println(anfrage);
		Zwischenergebnis ue = new Zwischenergebnis(); 
		try {
			 ue = ed.deserializeJsonZwischenergebnis(anfrage, ue);
			 System.out.println(ue.getAPI_KEY() + " " + ue.getProduktNameAnfrage() + " " + ue.zwischenPeriode);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		PrintWriter pw = response.getWriter();
		
			Produkt p = db.getProduktInfos(ue.zwischenPeriode, ue.getProduktNameAnfrage());			
		
			response.setContentType("text/json");
			
			String ausgabe = dj.produktToJson(p);
			
			pw.print(ausgabe);
			pw.close();
			
		try {
				ud.insStempel(ue.getAPI_KEY(), neuStempel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	

}
