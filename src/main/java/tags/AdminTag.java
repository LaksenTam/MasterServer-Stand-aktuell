package tags;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

import data.User;

public class AdminTag extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() {
		
		HttpSession session = pageContext.getSession();
		User admin = (User) session.getAttribute("zugriff");

		
		if(admin != null) {
			if(admin.getZugriff() != 1) {
		
			
			return EVAL_BODY_INCLUDE;
			}
		}else {
		
			return SKIP_BODY;
		}
		return SKIP_BODY;
			
		}
	}


