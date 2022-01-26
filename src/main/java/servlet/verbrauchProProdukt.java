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
import datenbank.Datenbank;
import datenbank.UserDatenbank;
import json.DataToJson;
import utility.CheckTime;

/**
 * Servlet implementation class verbrauchProProdukt
 */
@WebServlet("/verbrauchProProdukt")
public class verbrauchProProdukt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verbrauchProProdukt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// TODO Auto-generated method stub
		long neuStempel = System.currentTimeMillis();
		Datenbank db = new Datenbank();
		DataToJson dj = new DataToJson();
		CheckTime ct = new CheckTime();
		UserDatenbank ud = new UserDatenbank();
	
		String periode = request.getParameter("periode");
		String produktName = request.getParameter("produktName");
		String key = request.getParameter("key");
		

		int per = Integer.parseInt(periode);
		
		PrintWriter pw = response.getWriter();
		if(ct.testeStempel(neuStempel, key)) {
			Produkt p = db.getProduktInfos(per, produktName);			
		
			response.setContentType("text/json");
			
			String ausgabe = dj.produktToJson(p);
			
			pw.print(ausgabe);
			pw.close();
			
			try {
				ud.insStempel(key, neuStempel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			pw.print("Du warst zu langsam Sorry");
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
