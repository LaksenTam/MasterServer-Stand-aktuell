package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datenbank.UserDatenbank;

/**
 * Servlet implementation class ErgebnisLoeschen
 */
@WebServlet("/ErgebnisLoeschen")
public class ErgebnisLoeschen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErgebnisLoeschen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDatenbank udb = new UserDatenbank();
		String name = request.getParameter("name");
		String score = request.getParameter("score");
		String meldung = "";
		try {
			double highscore = Double.parseDouble(score);
			udb.deleteScore(name, highscore);
			meldung = "Das Ergebnis von " + name + " wurde entfernt";

		}catch(Exception e) {
			e.printStackTrace();
			meldung = "Da ist etwas schiefgelaufen";
		}
		request.setAttribute("meldung", meldung);
		request.getRequestDispatcher("/GetStudenten").forward(request, response);
	}

	

}
