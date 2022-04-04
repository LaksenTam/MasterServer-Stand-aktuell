package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datenbank.Datenbank;

/**
 * Servlet implementation class loadProblem
 */
@WebServlet("/loadProblem")
public class loadProblem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadProblem() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Datenbank db = new Datenbank();
		
		String pkey = db.getProblemInstanzByName(name);
		db.loadProbleminstanz(pkey);
		
		System.out.println(pkey);
		response.sendRedirect("showSavedProbleminstanz?");
		
	}

}
