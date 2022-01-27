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

import data.Produkt;
import datenbank.Datenbank;
import datenbank.UserDatenbank;
import json.DataToJson;

/**
 * Servlet implementation class GetProdukte
 * Servlet um die Produktdaten abzufragen
 */
@WebServlet("/GetProdukte")
public class GetProdukte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProdukte() {
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
		UserDatenbank ud = new UserDatenbank();

		
		String key = request.getParameter("key");
		System.out.println(key);
	
		
		List<Produkt> produkte = new ArrayList<>();
		
		try {
			long stamp = System.currentTimeMillis();
			ud.insStempel(key, stamp);
			produkte = db.produktListeAbrufen();
			PrintWriter pw = response.getWriter();
			response.setContentType("text/json");
			String produkteToString = dj.dataToJson(produkte);
			pw.print(produkteToString);
			pw.flush();
			pw.close();

		}catch(IOException | SQLException e) {
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
