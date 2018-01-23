package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class HiddenInputTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String property;// 标签的ID和name
	private String value;// 标签的值

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		StringBuilder buffer = new StringBuilder(100);
		buffer.append("<input ");
		buffer.append(" type=\"hidden\"");
		buffer.append(" id=\"" + this.property + "\"");
		buffer.append(" name=\"" + this.property + "\"");
		if (this.value != null && !"".equals(this.value)) {
			buffer.append(" value = \"" + this.value + "\"");
		}
		buffer.append("> ");

		try {
			JspWriter out = pageContext.getOut();
			out.println(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return BodyTagSupport.EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		StringBuilder buffer = new StringBuilder(100);
		buffer.append("</input>");
		try {
			JspWriter out = pageContext.getOut();
			out.println(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return BodyTagSupport.EVAL_PAGE;
	}
}
