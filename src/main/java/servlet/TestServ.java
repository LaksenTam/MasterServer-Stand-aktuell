package servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Highscore;
import datenbank.UserDatenbank;


/**
 * Servlet implementation class TestServ
 */
@WebServlet("/TestServ")
public class TestServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDatenbank user = new UserDatenbank();
		HttpSession session = request.getSession();
		String key = (String) session.getAttribute("key");
		
		List<Highscore> scores = user.userScores(key);
		for(Highscore s: scores) {
			System.out.println(s.getScore());
		}
		request.setAttribute("scores", scores);
		request.getRequestDispatcher("test.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String test = request.getParameter("test1");
		String vergleich = request.getParameter("test2");
		System.out.println(test);
		System.out.println(vergleich);
	}

}
