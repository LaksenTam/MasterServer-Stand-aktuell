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
import javax.servlet.http.HttpSession;

import data.Highscore;
import data.Produkt;
import data.Userergebnis;
import data.Zwischenergebnis;
import datenbank.Datenbank;
import datenbank.UserDatenbank;
import json.DataToJson;
import json.ErgebnisDeserializer;


/**
 * Servlet implementation class TestServ
 */
@WebServlet("/TestServ")
public class TestServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServ() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
