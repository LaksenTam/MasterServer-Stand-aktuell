package tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TippTag extends SimpleTagSupport {
	
	public void doTag() {
		Random rand = new Random();
		JspWriter out = getJspContext().getOut();
		
		List<String> tipps = new ArrayList<String>();
		tipps.add("Heuristiken sind schneller als exakte Verfahren");
		tipps.add("Diese Seite hat ein Lexikon mit vielen weiteren Tipps");
		tipps.add("In der Bestenliste können die Ergebnisse der Kontrahenten angesehen werden");
		tipps.add("Das Silver-Meal Verfahren ist eigentlich ein Verfahren zur Ermittlung dynamischer Losgrößen");
		tipps.add("Der Sicherheitsbestand ist ein Element des Mindestbestandes");
		tipps.add("Der Mindestbestand kann Engpässe überbrücken");
		tipps.add("Die ABC-Analyse zeigt die prozentuale Warenwertverteilung im Verhältnis zur Gesamtmenge");
		
		
		int selector = rand.nextInt(tipps.size()) + 0;
		
		try {
			out.append(tipps.get(selector));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
