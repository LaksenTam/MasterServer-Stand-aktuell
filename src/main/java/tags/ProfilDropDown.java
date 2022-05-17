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
			s += "<form action = \"Profil\" method = \"POST\"><div class=\"input-group mb-3 \">"
			  +"<div class=\"input-group-prepend\">"
					+"<select class = \"custom-select bg-dark text-white\" name = \"score1\">";
			 s+="<option selected>Auswahl<option>";
			  for(int j = 0; j<scores.size();j++) {
				  s+="<option value="+ scores.get(j).getId() +"> Highscore: " + scores.get(j).getScore() + "</option> ";
			  }
			  s+="</select></div>"
			    +"<label class=\"input-group-text\" for=\"inputGroupSelect01\">VS</label>"			
			    +"<div class=\"input-group-prepend\">"
			    + "<select class=\"custom-select  bg-dark text-white\"  name = \"score2\"> "
			    + "<option selected>Auswahl</option> ";
			   for(int i =0; i<scores.size();i++) {
				   s+="<option value="+ scores.get(i).getId() +"> Highscore: " + scores.get(i).getScore() + "</option> ";
						   
			   }
			  s+=  "</select> </div>"	
					+ "<button class = \"btn btn-dark ml-2\" type = \"submit\">Senden</button>"
			   + "</div> </form>";
		}else {
			s = "";
		}
		try {
			out.append(s);
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}

}
