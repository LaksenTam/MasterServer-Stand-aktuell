package tags;

import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class UserManagementTag extends SimpleTagSupport {

	public void doTag() {
		PageContext pc = (PageContext) getJspContext();
		ServletRequest request = (HttpServletRequest) pc.getRequest();
		JspWriter out = getJspContext().getOut();
		@SuppressWarnings("unchecked")
		ArrayList<String[]> list = (ArrayList<String[]>) request.getAttribute("user");
		String s = "";

		if(list.size()!=0) {
			s += 	"<div class=\"container \">"
					+"<table class = \"table table-hover \">"
				    +"<thead>"
					+ "<tr>"
				    +"<th>#</th>"
					+ "<th>Name</th>"
				    +"<th>Highscore</th>"
					+"<th>Fehlmengenkosten</th>"
					+"<th>Gesamtkosten</th>"
					+"<th>ben&ouml;tigte Zeit</th>"
					+"<th>L&ouml;schen</th>"
					+"<th>Admin</th>"
					+"</tr>"
					+"</thead>"
					+"<tbody id=\"tableBody\">";
			for(int i = 0; i<list.size();i++) {
				String[] string = list.get(i);
				s+= "<tr>"
						+ "<td class = m-1>" + (i+1) + "</td>";
						for(int j=0; j<string.length-1;j++) {
							s +=  "<td>" + string[j] + "</td>";
						}
				s +="<td><a class = \"btn btn-danger\" href = \"UserLoeschen?name="+ list.get(i)[0]+ "\" data-confirm =\"Sind Sie sich sicher " + list.get(i)[0] + " zu l�schen?\">User</a>"
						+ "<a class = \"btn btn-info\" href =\"ErgebnisLoeschen?name="+list.get(i)[0] + "&score=" + list.get(i)[1]+"\" data-confirm=\"Das Ergebnis von "+list.get(i)[0]+" soll gel�scht werden?\" >Ergebnis</a></td>";
				if(list.get(i)[5].equals("0")) {
						s +="<td><a class =\"btn btn-sucess invert\" href =\"AdminErstellen?name=" + list.get(i)[0] + "\" data-confirm =\"Sind Sie sich sicher " +list.get(i)[0]+ " zu einem Admin zu machen?\"><img src=\"img/arrow-alt-circle-up-solid.svg\" width = \"20\" height = \"20\"></a></td>";
				}else {
					s += "<td><a class =\"invert btn btn-sucess \" href =\"AdminDemote?name=" + list.get(i)[0] + "\" data-confirm =\"Sind Sie sich sicher " +list.get(i)[0]+  "zu einem Studenten zu machen?\"><img src = \"img/arrow-circle-down-solid.svg\" width = \"20\" height = \"20\"></a></td>";
				}
			}
			s+="</tbody></table></div>";
		}else {
			s = "Noch keine Eintr�ge vorhanden";

		}
		try {
			out.append(s);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
