package tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

import javax.servlet.http.HttpSession;

public class StudentHead extends SimpleTagSupport {
	
	public void doTag() {
		PageContext pc = (PageContext) getJspContext();
		@SuppressWarnings("unused")
		HttpSession session = pc.getSession();
		JspWriter out = getJspContext().getOut();
		String studentHead = "";
		
		try {
			studentHead = "";
			
			out.append(studentHead);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
