package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datenbank.Datenbank;

/**
 * Servlet implementation class GetStudenten
 */
@WebServlet("/GetStudenten")
public class GetStudenten extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudenten() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Datenbank db = new Datenbank();
		List<String[]> user = db.getUser();		
		
		request.setAttribute("user", user);
		request.getRequestDispatcher("usermanagement.jsp").forward(request, response);
	}

}
