package tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import data.Highscore;

public class ProfilDropDown extends SimpleTagSupport{
	
	public void doTag() {
		PageContext pc = (PageContext) getJspContext();
		ServletRequest request = (HttpServletRequest) pc.getRequest();

		
		@SuppressWarnings("unchecked")
		ArrayList<Highscore> scores = (ArrayList<Highscore>) request.getAttribute("scores");
		JspWriter out = getJspContext().getOut();
		String s = "";
		
		if(scores.size() != 0) {
			s += "<form action = \"Profil\" method = \"POST\"><div class=\"input-group mb-3\">"
			  +"<div class=\"input-group-prepend\">"
					+"<select class = \"custom-select\" name = \"score1\">";
			
			  for(int j = 0; j<scores.size();j++) {
				  s+="<option value="+ scores.get(j).getId() +"> Highscore: " + scores.get(j).getScore() + "</option> ";
			  }
			  s+="<option selected>Choose<option>"
			  +"</select>"
			    +"<label class=\"input-group-text\" for=\"inputGroupSelect01\">VS</label>"
			
			   + "</div> "
			   + "<select class=\"custom-select\"  name = \"score2\"> "
			   + "<option selected>Choose...</option> ";
			   for(int i =0; i<scores.size();i++) {
				   s+="<option value="+ scores.get(i).getId() +"> Highscore: " + scores.get(i).getScore() + "</option> ";
						   
			   }
			  s+=  "</select> "	
					+ "<button class = \"btn btn-primary\" type = \"submit\">Senden</button>"
			   + "</div> </form>";
		}else {
			s = "UPS";
		}
		try {
			out.append(s);
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}

}
