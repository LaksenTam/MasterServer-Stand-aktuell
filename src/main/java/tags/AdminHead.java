package tags;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;




public class AdminHead extends SimpleTagSupport {
	
	
	public void doTag() {
	
		PageContext pc = (PageContext) getJspContext();	
		HttpSession session = pc.getSession();
		JspWriter out = getJspContext().getOut();
		
		String name =  (String) session.getAttribute("name");
		
		
		String head =  "<div class=\"container-fluid  bg-dark mb-5\">\r\n"
				+ "	<div class=\"row align-items-center\" >\r\n"
				+ "		<div class=\"col-md-2 center\">\r\n"
				+ "		<img src = \"img/info.svg\" width = \"70\" height = \"70\">\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"col-md-8 p-2\">\r\n";
	
		if(name != null) {	
			int zugriff = (int) session.getAttribute("zugriff");
			
			if(zugriff == 1) {
				head += 
						 "			<ul class=\"nav justify-content-center\">\r\n"
						+ "				<li class=\"nav-item\">\r\n"
						+ "					<a class=\"nav-link active\" href=\"index.jsp\">Home</a>\r\n"
						+ "				</li>\r\n"
						+ "				<li class=\"nav-item\">\r\n"
						+ "					<a class=\"nav-link active\" href=\"Profil?\">Profil</a>\r\n"
						+ "				</li>\r\n"
						+ "				<li class=\"nav-item\">\r\n"
						+ "					<a class=\"nav-link\" href=\"ProblemInstanzAnzeigen?\">Adminpanel</a>\r\n"
						+ "				</li>\r\n"
						+ "				<li class=\"nav-item\">\r\n"
						+ "					<a class=\"nav-link\" href=\"BestenListe?\">Bestenliste</a>\r\n"
						+ "				</li>\r\n"
						+ "				\r\n"
						+ "				<li class=\"nav-item\">\r\n"
						+ "					<a class=\"nav-link\" href=\"ProblemInstanzAnpassen\">Anpassen</a>\r\n"
						+ "				</li>\r\n"
						+"				<li class=\"nav-item\">\r\n"
						+ "				<a class=\"nav-link\" href=\"GetStudenten?\">UserManagement</a>\r\n"
						+ "				</li>\r\n"
						+"				<li class=\"nav-item\">\r\n"
						+ "					<a class=\"nav-link\" href=\"how-to.jsp\">How-To</a>\r\n"
						+ "				</li>\r\n"
						+ "			</ul>\r\n"
						+ "		</div>\r\n"
						+ "		<div class=\"col-md-1 justify-content-center\">\r\n"
						+ "		<a href= \"Logout\"><button id = \"navbutton\" type=\"button\" class=\"btn btn-danger btn-block rounded-pill \"><span class = \"button-icon\"><img src = \"img/arrow-right-from-bracket-solid.svg\" width=\"13px\" height=\"15px\" class = \"log\"></span><span class = \"button-text\">Logout</span></button></a>\r\n"
						+ "		</div>\r\n"
						+ "	</div>\r\n"
						+ "</div>";
			}	
		}if(name != null) {
			int zugriff = (int) session.getAttribute("zugriff");
			if(zugriff == 0) {
			head += 
					 
					 "			<ul class=\"nav justify-content-center\">\r\n"
					+ "				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link active\" href=\"index.jsp\">Home</a>\r\n"
					+ "				</li>\r\n"
					+ "				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link active\" href=\"Profil?\">Profil</a>\r\n"
					+ "				</li>\r\n"								
					+ "				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link\" href=\"BestenListe?\">Bestenliste</a>\r\n"
					+ "				</li>\r\n"											
					+"				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link\" href=\"how-to.jsp\">How-To</a>\r\n"
					+ "				</li>\r\n"
					+ "			</ul>\r\n"
					+ "		</div>\r\n"
					+ "		<div class=\"col-md-1 justify-content-center\">\r\n"
					+ "		<a href= \"Logout\"><button id = \"navbutton\" type=\"button\" class=\"btn btn-danger btn-block rounded-pill \"><span class = \"button-icon\"><img src = \"img/arrow-right-from-bracket-solid.svg\" width=\"13px\" height=\"15px\" class = \"log\"></span><span class = \"button-text\">Logout</span></button></a>\r\n"
					+ "		</div>\r\n"
					+ "	</div>\r\n"
					+ "</div>";
			}
		}else {
			head += 	
					 "			<ul class=\"nav justify-content-center\">\r\n"
					+ "				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link text-white\" href=\"index.jsp\">Home</a>\r\n"
					+ "				</li>\r\n"
					+ "				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link text-white\" href=\"registrierung.jsp\">Registrieren</a>\r\n"
					+ "				</li>\r\n"
					+ "				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link text-white\" href=\"BestenListe?\">Bestenliste</a>\r\n"
					+ "				</li>\r\n"
					+"				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link text-white\" href=\"how-to.jsp\">How-To</a>\r\n"
					+ "				</li>\r\n"
					+ "				\r\n"
					+ "			</ul>\r\n"
					+ "		</div>\r\n"
					+ "		<div class=\"col-md-1 justify-content-center\">\r\n"
					+ "		<a href =\"login.jsp\"><button id = \"navbutton\" type=\"button\" class=\"btn btn-secondary btn-block rounded-pill \"><span class = \"button-icon\"><img src = \"img/arrow-right-to-bracket-solid.svg\" width=\"13px\" height=\"15px\" class = \"log\"></span><span class = \"button-text\">Login</span></button></a>\r\n"
					+ "		</div>\r\n"
					+ "	</div>\r\n"
					+ "</div>";
			
			
		}
		try {
			out.append(head);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}


}
