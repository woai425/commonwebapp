package com.h3c.portal.taglib.tab;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;



public class TabContentTag extends BodyTagSupport{
	private static final long serialVersionUID = -3171070837401743380L;
	private String property;
	private boolean active;
	@Override
	public int doStartTag() throws JspException {
		StringBuilder buffer = new StringBuilder(100);
		String isActive = "";
		if(active){
			isActive = "active";
		}
		buffer.append("<div class=\"tab-pane "+isActive+"\" id=\""+this.property+"\">");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(buffer.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {      
            JspWriter out = pageContext.getOut();      
            out.println("</div>");      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return EVAL_PAGE;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
