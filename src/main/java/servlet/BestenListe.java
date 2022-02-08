package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Highscore;
import datenbank.UserDatenbank;


/**
 * Servlet implementation class BestenListe
 */
@WebServlet("/BestenListe")
public class BestenListe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger("bestenlist");

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
			
			//HttpSession session = request.getSession();
			UserDatenbank db = new UserDatenbank();
			List<Highscore> score = db.highscoresabrufen();	
			List<String[]> stringScores = new ArrayList<String[]>();
			
			for(Highscore s: score) {				
				String[] sh = { s.getName(), Double.toString(s.getScore())};
				//System.out.println(s.getName() + " " + s.getScore());	
				
				stringScores.add(sh);
			}
			
			LOGGER.log( Level.FINE, "processing score entries", score.size() );
			
			request.setAttribute("resultList", stringScores);
			
			request.setAttribute("score", score);		
			request.getRequestDispatcher("best.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
