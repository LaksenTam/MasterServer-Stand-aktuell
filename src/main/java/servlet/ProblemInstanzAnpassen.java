package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Produkt;
import datenbank.Datenbank;
import manager.DatenManager;

/**
 * Servlet implementation class ProblemInstanzAnpassen
 */
@WebServlet("/ProblemInstanzAnpassen")
public class ProblemInstanzAnpassen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProblemInstanzAnpassen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Datenbank db = new Datenbank();
		List<Produkt> produkte = new ArrayList<>();
		
		produkte = db.getStartProblem(produkte);
		HttpSession session = request.getSession();
		session.setAttribute("produkte", produkte);
		
		request.getRequestDispatcher("probleminstanzanpassen.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatenManager dm = new DatenManager();
		HttpSession session = request.getSession();
		int i = (int) session.getAttribute("i");
		@SuppressWarnings("unchecked")
		ArrayList<Produkt> p = (ArrayList<Produkt>) session.getAttribute("produkte");

		List<Produkt> produkt = new ArrayList<>();
		
		
		for(int j = 0; j<i;j++) {
			Produkt pro = new Produkt();	
			pro.setName(p.get(j).getName());
			pro.setBestellfix(Double.parseDouble(request.getParameter("bestellfix"+j)));
			pro.setEinstand(Double.parseDouble(request.getParameter("einstand"+j)));
			pro.setFehlmengenkosten(Double.parseDouble(request.getParameter("fehlmengenkosten"+j)));
			pro.setLagerkostensatz(Double.parseDouble(request.getParameter("lagerkosten"+j)));
			pro.setMinBestand(Integer.parseInt(request.getParameter("minBestand"+j)));
			pro.setMaxBestand(Integer.parseInt(request.getParameter("maxBestand"+j)));		
			produkt.add(pro);
		}
		
		dm.updateProblemInstanz(produkt);
		String updated= "Felder wurden aktualisiert!";
		request.setAttribute("updated", updated);
		doGet(request,response);		
	}

}
