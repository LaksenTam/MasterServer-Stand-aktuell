package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.DatenManager;

/**
 * Servlet implementation class BestenListe
 */
@WebServlet("/BestenListe")
public class BestenListe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BestenListe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String meldung = "";
		
		DatenManager daten = new DatenManager();
		
		try {
			ResultSet rs = daten.bestenListeAbrufen();
			ArrayList<String[]> results = new ArrayList<String[]>();
			while(rs.next()) {
				String[] s = {
						rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)
				};
				results.add(s);
			}
			request.setAttribute("columnNames", new String[] {
					"id", "bestellmenge", "Produktname", "Kostne", "userkey", "periode", "zeitstempel"
			});
			request.setAttribute("resultList", results);
			request.getRequestDispatcher("best.jsp").forward(request, response);
			
		}catch(SQLException e) {
			meldung = "Fehler";
			e.printStackTrace();
			request.setAttribute("meldung", meldung);
			request.getRequestDispatcher("best.jsp").forward(request, response);
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
