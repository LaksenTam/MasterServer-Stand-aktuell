package tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import data.Produkt;

public class ProblemInstanzBearbeiten extends SimpleTagSupport {
	
	@SuppressWarnings("unchecked")
	public void doTag() {
		PageContext pc = (PageContext) getJspContext();
		HttpSession session = pc.getSession();
		ArrayList<Produkt> p = (ArrayList<Produkt>) session.getAttribute("produkte");
		JspWriter out = getJspContext().getOut();
		String s ="";
		String meldung = "";
		session.setAttribute("i", p.size());
		
		if(p != null) {
			s += "<form action = \"ProblemInstanzAnpassen\" method = \"POST\">"
				+"<table class = \"table table-hover\">"
			    +"<thead>"
				+ "<tr>"
				+ "<th>Name</th>"
				+"<th>Bestellfixekosten</th>"
				+"<th>Einstandskosten</th>"
				+"<th>Fehlmengenkosten</th>"
				+"<th>Lagerkostensatz</th>"
				+"<th>Min Bestand</th>"
				+"<th>Max Bestand</th>"
				+"</tr>"
				+"</thead>"
				+"<tbody id=\"tableBody\">";
			
			for(int i = 0; i<p.size();i++) {
				
			s+=	"<tr>"				
				+ "<td>" + p.get(i).getName() + "</td>"
				+ "<td><input type = \"text\" name = \"bestellfix"+i+"\" value =" + p.get(i).getBestellfix()+" ></input></td>"
				+ "<td><input type = \"text\" name = \"einstand"+i+"\" value ="  + p.get(i).getEinstand() + "></input></td>"
				+ "<td><input type = \"text\" name = \"fehlmengenkosten"+i+"\" value = " + p.get(i).getFehlmengenkosten() + "></input></td>"
				+"<td><input type = \"text\" name = \"lagerkosten"+i+"\" value = " + p.get(i).getLagerkostensatz() + "></input></td>"
				+"<td><input type = \"text\" name = \"minBestand"+i+"\" value = " + p.get(i).getMinBestand() + "></input></td>"
				+"<td><input type = \"text\" name = \"maxBestand"+i+"\" value = " +  p.get(i).getMaxBestand() + "></input></td>"
				+"</tr>"
				;				
			}
			s +="</tbody>"
					+"<tr>"
					+ "<td><button class = \"btn btn-primary\" type = \"submit\">Erstellen!</button>"
					+"</tr></table></form>";			
		}
		
		try {			
			out.append(s);	
			out.append(meldung);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
