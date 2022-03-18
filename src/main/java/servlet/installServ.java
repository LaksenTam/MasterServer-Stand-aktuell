package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datenbank.Datenbankinstaller;
import utility.Hashing;

/**
 * Servlet implementation class installServ
 */
@WebServlet("/installServ")
public class installServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public installServ() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		Hashing hash = new Hashing();
		String salt = hash.erstelleHash(pass);
		String meldung = "";
		
		if(Datenbankinstaller.getConnection() == null) {
			Datenbankinstaller.createAdminUser(name, salt);
			meldung = "Installation durchgeführt, Sie können sich nun mit den hinterlegten Daten einloggen";			

		}else {
			meldung = "Zugriff verweigert";
						
		}
		
		request.setAttribute("meldung", meldung);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		
	}

}
