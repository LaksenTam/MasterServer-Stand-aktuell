package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Produkt;
import datenbank.Datenbank;
import json.DataToJson;

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
		System.out.println(response.getWriter().append("Served at: ").append(request.getContextPath()));
		Datenbank db = new Datenbank();
		DataToJson dj = new DataToJson();
		
		String periode = request.getParameter("periode");
		String produktName = request.getParameter("produktName");
		
		int per = Integer.parseInt(periode);
		
		Produkt p = db.getProduktInfos(per, produktName);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/json");
		
		String ausgabe = dj.produktToJson(p);
		System.out.println(ausgabe);
		pw.print(ausgabe);
		pw.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
