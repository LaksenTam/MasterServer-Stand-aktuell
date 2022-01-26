package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.User;
import datenbank.UserDatenbank;
import utility.Hashing;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("loginname");
		String password = request.getParameter("passwort");
		Hashing hash = new Hashing();
		UserDatenbank db = new UserDatenbank();
		System.out.println(password);
		
		password = hash.erstelleHash(password);
		
				
		try {
			//if(db.login(name, password)) {
				User user = new User();
				if(db.getUser(name, password, user) != null && user.getApi_key() != null) {
					
				//user = db.getUser(name, password, user);
				user.setName(name);
				System.out.println(user.getApi_key());
				HttpSession session = request.getSession();
				request.setAttribute("key", user.getApi_key());
				request.setAttribute("name", name);
				session.setAttribute("name", name);
				session.setAttribute("key", user.getApi_key());
				request.setAttribute("name", session.getAttribute("name"));
				System.out.println(session.getAttribute("name"));
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				System.out.println("Else");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}