package tags;


import javax.servlet.jsp.JspWriter;

import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FooterTag extends SimpleTagSupport {
	
	public void doTag() {
		
		JspWriter out = getJspContext().getOut();
		
		String foot ="<nav class = \"navbar fixed-bottom navbar-dark bg-dark\">	\r\n"
				+ "		<a  class = \"navbar-brand\" href = \"impressum.jsp\">Impressum</a>\r\n"
				+ "		<a  class = \"navbar-brand\" href = \"datenschutz.jsp\">Datenschutz</a>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "<!-- Cookie Consent by TermsFeed (https://www.TermsFeed.com) -->\r\n"
				+"<script type=\"text/javascript\" src=\"https://www.termsfeed.com/public/cookie-consent/4.0.0/cookie-consent.js\" charset=\"UTF-8\"></script>\r\n"
				+ "<script type=\"text/javascript\" charset=\"UTF-8\">\r\n"
				+ "document.addEventListener('DOMContentLoaded', function () {\r\n"
				+ "cookieconsent.run({\"notice_banner_type\":\"simple\",\"consent_type\":\"implied\",\"palette\":\"light\",\r\n"
				+ "	\"language\":\"de\",\"page_load_consent_levels\":[\"strictly-necessary\",\"functionality\",\"tracking\",\"targeting\"],\r\n"
				+ "	\"notice_banner_reject_button_hide\":false,\"preferences_center_close_button_hide\":false,\r\n"
				+ "	\"page_refresh_confirmation_buttons\":false,\"website_name\":\"www.lukas-hoffmeister.de\"});\r\n"
				+ "});\r\n"
				+ "</script>\r\n"
				+ "\r\n"
				+ "<noscript>ePrivacy and GPDR Cookie Consent by <a href=\"https://www.TermsFeed.com/\" rel=\"nofollow\">TermsFeed Generator</a></noscript>\r\n"
				+ "<!-- End Cookie Consent by TermsFeed (https://www.TermsFeed.com) -->\r\n"
				+ "</nav>";	
		
		try {
			out.append(foot);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
