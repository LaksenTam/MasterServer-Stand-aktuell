package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import manager.DatenManager;

/**
 * Servlet implementation class CreateCSV
 */
@WebServlet("/CreateCSV")
public class CreateCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCSV() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	DatenManager daten = new DatenManager();
		
		response.setContentType("text/csv");
		
		String reportName = "GenerateCSV_Report.csv";
		
		 response.setHeader("Content-disposition", "attachment; " +
	                "filename=" + reportName);
		 
		try {
			
			ResultSet rs = daten.problemInstanzAbrufen();		
			String header = "Produktname,Bestellfixekosten,Einstandspreis,Fehlmengenkostensatz,"
					+ "Lagerkostensatz,Maximaler Bestand,MinimalerBestand,Produktvolumen,verbrauch\n";
			
			PrintWriter pw = response.getWriter();
			pw.append(header);
			
			while(rs.next()) {
				String s =  rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5) + "," + rs.getString(6)
				+ "," + rs.getString(7)+ "," + rs.getString(8) + "," + rs.getString(9)+"\n";
				pw.append(s);				 
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}	

}
