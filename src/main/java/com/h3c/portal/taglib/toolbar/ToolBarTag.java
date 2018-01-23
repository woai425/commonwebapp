package com.h3c.portal.taglib.toolbar;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;


public class ToolBarTag extends BodyTagSupport{
	
	private static final long serialVersionUID = 1L;
	private String property;
	private String cls;
	private String text;
	private String size;
	private String style;
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int doStartTag() throws JspException{
		StringBuilder sb = new StringBuilder(300);
		if(this.cls==null||"".equals(this.cls)){
			sb.append("<div id=\""+this.property + "\"  class= 'btn-toolbar' ");
		}else{
			sb.append("<div id=\""+this.property + "\" class=\""+ this.cls + "\" ");
		}
		if(this.style==null||"".equals(this.style)){
			sb.append("style=\""+this.style + "\" ");
		}
		sb.append(">");
		sb.append("<table style='width: 100%;'><tr>");
		if(this.text!=null){
			sb.append("<td align='left' style='font-family:Microsoft YaHei;'>");
			sb.append("<span style='font-size: "+ (this.size==null?"20":this.size) +"px;font-weight: bold; font-family:Microsoft YaHei;' > ");
			sb.append(" "+this.text+"</span></td>");
		}
	
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return EVAL_BODY_INCLUDE;
	}
	
	public int doEndTag() throws JspException{
		StringBuilder sb = new StringBuilder(100);
		sb.append("</tr></table></div>");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return EVAL_PAGE;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}

}
