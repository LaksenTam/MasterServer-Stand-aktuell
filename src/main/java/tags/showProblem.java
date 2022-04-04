package tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class showProblem extends SimpleTagSupport {
	
	@SuppressWarnings("unused")
	public void doTag() {
		PageContext pc = (PageContext) getJspContext();
		
		ServletRequest request = (HttpServletRequest) pc.getRequest();
		@SuppressWarnings("unchecked")
		List<String> problemname =  (List<String>) request.getAttribute("savedProblem");
		
		JspWriter out = getJspContext().getOut();
		String s = "";
		
			s += "<form action = \"Profil\" method = \"POST\"><div class=\"input-group mb-3 \">"
			  +"<div class=\"input-group-prepend\">"
					+"<select class = \"custom-select bg-dark text-white\" name = \"score1\">";
			
			  for(int j = 0; j<problemname.size();j++) {
				  s+="<option value="+ problemname.get(j) +">" + problemname.get(j) + "</option> ";
			  }
			  s +="<option selected>Auswahl<option>"
					  +"</select>"
					  + "</div> ";
		s += "<button class = \"btn btn-dark type = \"submit\">Laden</button>"
				+ "</select></div></form></div>";
		
		try {
			out.append(s);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
