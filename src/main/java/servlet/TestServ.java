package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Lager;
import data.Produkt;
import data.Userergebnis;
import datenbank.Datenbank;
import manager.DatenManager;
import utility.CheckFeasible;

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
		String ergebnis = request.getParameter("json");		
		List<Produkt> produktListe = new ArrayList<Produkt>();
		Userergebnis ue = new Userergebnis();
		DatenManager daten = new DatenManager();
		Datenbank db = new Datenbank();
		CheckFeasible feas = new CheckFeasible();


		ue = daten.ergebnis(ergebnis, ue);
		Lager lager = db.lagerAbrufen();
		try {
			produktListe = daten.produktListePeriode(produktListe, 1);
			System.out.println("JA");
			System.out.println(ue.getProdukte());
			System.out.println(lager);
			System.out.println(produktListe);
			feas.isFeasible(ue.getProdukte(), lager, produktListe);
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
