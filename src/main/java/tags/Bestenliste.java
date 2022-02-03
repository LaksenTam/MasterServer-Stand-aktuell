package tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import data.Highscore;

public class Bestenliste extends SimpleTagSupport {
	
	public void doTag() {
		PageContext pc = (PageContext) getJspContext();
		HttpSession session  = pc.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<Highscore> score = (ArrayList<Highscore>) session.getAttribute("score");
		
		JspWriter out =  getJspContext().getOut();
		String s = "";
		
		if(score.size()!=0) {
			s += "<div class=\"list-group w-25 mb-4\">"
			  +"<div class=\"list-group-item list-group-item-action list-group-item-info i-btn\">"
			  +"<img src = \"img/info.svg\">"
			  +"<span>Klicken Sie auf die Spalte, um die Tabelle zu sortieren  </span>  "
			  +"</div>"
			  +"</div>"
			  + "<div class = \"container\">"
			  +"<input class=\"form-control \" id=\"myInput\" type=\"text\" placeholder=\"Search..\">"

			  +"<table id = \"tablesorter\" class = \"table table-hover tablesorter\">"
			  +"<thead class = \"bg-danger text-white\">"
			            +"<tr>"
			             +"<th>#</th>"
			            +"<th>name</th>"
			             +"<th>score</th>"
			            +"</tr>"
			            +"</thead>"
			            +"<tbody id = \"myTable\">"  ;
			
			for(int i= 0;i<score.size();i++ ) {
				s += "<tr>"
					+ "<td>" + (i+1) + "</td>"
					+ "<td>" + score.get(i).getName() + "</td>"	
					+ "<td>" + score.get(i).getScore() + "</td>"	
			     	+ "</tr>";
			}	  
			              
			           
			 s +=      "</tbody>"
			      + "</table> "
			+"</div>";
			       
		}
		
		try {
			out.append(s);
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	

}
