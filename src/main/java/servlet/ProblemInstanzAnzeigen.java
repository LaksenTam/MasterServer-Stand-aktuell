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
//import javax.servlet.http.HttpSession;

import manager.DatenManager;


/**
 * Servlet implementation class ProblemInstanzAnzeigen
 */
@WebServlet("/ProblemInstanzAnzeigen")
public class ProblemInstanzAnzeigen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProblemInstanzAnzeigen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String meldung = "";
		//HttpSession session = request.getSession();
		DatenManager daten = new DatenManager();
		
		
		//int zu = (int) session.getAttribute("zugriff");
		
		try {
			ResultSet rs = daten.problemInstanzAbrufen();
			ArrayList<String[]> results = new ArrayList<String[]>();
			while(rs.next()) {
				String[] s = { 
						 rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
	                		rs.getString(7), rs.getString(8), rs.getString(9)
				};
				results.add(s);
			}
			request.setAttribute("columnNames", new String[] { "Produktname ", "Bestellfixekosten ", "Einstandspreis "
	        		, "Fehlmengenkostensatz ", "Lagerkostensatz", "Minimaler Bestand", "Maximaler Bestand", "Produktvolumen",
	        		"verbrauch"});
	        request.setAttribute("resultList", results);
	        request.getRequestDispatcher("adminpanel.jsp").forward(request, response);
	        
		}catch(SQLException e) {
			e.printStackTrace();
			meldung = "Ups da ist etwas schiefgelaufen";
			request.setAttribute("meldung", meldung);
			request.getRequestDispatcher("adminpanel.jsp").forward(request, response);
		}		
	}
}
