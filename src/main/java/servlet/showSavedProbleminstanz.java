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
 * Servlet implementation class showSavedProbleminstanz
 */
@WebServlet("/showSavedProbleminstanz")
public class showSavedProbleminstanz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showSavedProbleminstanz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Datenbank db = new Datenbank();
		
		List<String> problem = db.showSavedProbleminstanz();
		for(int i = 0;i<problem.size();i++) {
			System.out.println(problem.get(i));
		}
		
		
		request.setAttribute("savedProblem", problem);
		request.getRequestDispatcher("test.jsp").forward(request, response);
	}

	

}
