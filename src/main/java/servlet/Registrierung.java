package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.User;
import datenbank.*;
import utility.Hashing;

/**
 * Servlet implementation class Registrierung
 */
@WebServlet("/Registrierung")
public class Registrierung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrierung() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("loginname");
		String password = request.getParameter("passwort");
		String pBestaetigen = request.getParameter("pBestaetigen");
		
		int zugriff = 0;
		HttpSession session = request.getSession();
		
		if(password.equals(pBestaetigen)) {
			if(!name.equals("") && !password.equals("")) {
				Hashing hash = new Hashing();				
				password = hash.erstelleHash(password);
				String api_key = UUID.randomUUID().toString();
				UserDatenbank ud = new UserDatenbank();
				try {
					ud.registrierungUser(name, password, api_key, zugriff);
					User user = new User(name, password, zugriff);					
					
					request.setAttribute("user", user);
					request.setAttribute("userName", user.getName());
					session.setAttribute("name", user.getName());
					System.out.println(session.getAttribute("name"));
					System.out.println("Benutzer wurde erstellt: " + user.toString());
					
					request.setAttribute("name", session.getAttribute("name"));
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}	

}