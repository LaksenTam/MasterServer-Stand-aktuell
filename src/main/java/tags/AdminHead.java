package tags;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import data.*;


public class AdminHead extends SimpleTagSupport {
	
	
	public void doTag() {
	
		PageContext pc = (PageContext) getJspContext();	
		HttpSession session = pc.getSession();
		JspWriter out = getJspContext().getOut();
		
		String name =  (String) session.getAttribute("name");
		String adminHead = "";
		try {
		if(name != null) {			
				adminHead = "<div class=\"container-fluid mb-5 mt-2\">\r\n"
						+ "	<div class=\"row align-items-center\" >\r\n"
						+ "		<div class=\"col-md-2 center\">\r\n"
						+ "		<img src = \"img/logo-uni.svg\" width = \"70\" height = \"70\">\r\n"
						+ "		</div>\r\n"
						+ "		<div class=\"col-md-8 p-2\">\r\n"
						+ "			<ul class=\"nav justify-content-center\">\r\n"
						+ "				<li class=\"nav-item\">\r\n"
						+ "					<a class=\"nav-link active\" href=\"index.jsp\">Home</a>\r\n"
						+ "				</li>\r\n"
						+ "				<li class=\"nav-item\">\r\n"
						+ "					<a class=\"nav-link active\" href=\"profil.jsp\">Profil</a>\r\n"
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
						+ "			</ul>\r\n"
						+ "		</div>\r\n"
						+ "		<div class=\"col-md-1 justify-content-center\">\r\n"
						+ "		<a href= \"Logout\"><button id = \"navbutton\" type=\"button\" class=\"btn btn-danger btn-block rounded-pill \">Logout</button></a>\r\n"
						+ "		</div>\r\n"
						+ "	</div>\r\n"
						+ "</div>";
				
				
		}else {
			adminHead = "<div class=\"container-fluid mb-5 mt-2\">\r\n"
					+ "	<div class=\"row align-items-center\" >\r\n"
					+ "		<div class=\"col-md-2 center\">\r\n"
					+ "		<img src = \"img/logo-uni.svg\" width = \"70\" height = \"70\">\r\n"
					+ "		</div>\r\n"
					+ "		<div class=\"col-md-8 p-2\">\r\n"
					+ "			<ul class=\"nav justify-content-center\">\r\n"
					+ "				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link active\" href=\"index.jsp\">Home</a>\r\n"
					+ "				</li>\r\n"
					+ "				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link\" href=\"registrierung.jsp\">Registrieren</a>\r\n"
					+ "				</li>\r\n"
					+ "				<li class=\"nav-item\">\r\n"
					+ "					<a class=\"nav-link\" href=\"BestenListe?\">Bestenliste</a>\r\n"
					+ "				</li>\r\n"
					+ "				\r\n"
					+ "			</ul>\r\n"
					+ "		</div>\r\n"
					+ "		<div class=\"col-md-1 justify-content-center\">\r\n"
					+ "		<a href =\"login.jsp\"><button id = \"navbutton\" type=\"button\" class=\"btn btn-primary btn-block rounded-pill \">Login</button></a>\r\n"
					+ "		</div>\r\n"
					+ "	</div>\r\n"
					+ "</div>";
			
			
		}
		
		out.append(adminHead);

		}catch(IOException e) {
			e.printStackTrace();
		}
	}


}
