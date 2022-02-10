package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Lager;
import datenbank.Datenbank;

/**
 * Servlet implementation class UpdateLager
 */
@WebServlet(
		urlPatterns = { "/UpdateLager" }
		)
		
public class UpdateLager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kapBindung = request.getParameter("kapBindung");
		String lagerVol = request.getParameter("vol");
		
		Datenbank db = new Datenbank();
		
		Lager lager = new Lager();
		
		lager.setKbindung(Float.parseFloat(kapBindung));
		lager.setLagerVol(Float.parseFloat(lagerVol));
		
		try {
			db.updateLager(lager);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("lagerkap", lager.getKbindung());
		request.setAttribute("lagervol", lager.getLagerVol());
		request.getRequestDispatcher("ProblemInstanzAnzeigen?").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
