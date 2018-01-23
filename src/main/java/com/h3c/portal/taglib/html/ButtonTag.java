package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;


public class ButtonTag extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	private String text;
	private String handler;
	private String property;
	private String fontAwesome;
	private String bgcolor;
	private String cls;
	private String  width;
	private String height;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	
	
	public int doStartTag() throws JspException{
		StringBuilder sb = new StringBuilder(420);
		if(this.cls==null||"".equals(this.cls)){
			sb.append("<button class='btn ' ");
		}else{
			sb.append("<button class='btn "+this.cls+"'  ");
		}
	    sb.append(" type=\"button\" style=' text-align: middle;vertical-align: middle; ");
	    
		if(this.bgcolor!=null&&!"".equals(this.bgcolor)){
			sb.append("color:\""+this.bgcolor + "\"  ");
		}
		if(this.width!=null&&!"".equals(this.width)){
			sb.append(" width:"+(StringUtils.isEmpty(this.width)?this.width:(this.width.indexOf("%")==-1?this.width+"px":this.width))+"; ");
		}
		
		if(this.height!=null&&!"".equals(this.height)){
			sb.append("height:"+(StringUtils.isEmpty(this.height)?this.height:(this.height.indexOf("%")==-1?this.height+"px":this.height))+"; ");
		}else{
			sb.append("height:30px; ' ");
		}
		if(this.bgcolor!=null&&!"".equals(this.bgcolor)){
			sb.append("style='color:"+this.bgcolor+" ' ");
		}
		sb.append("id=\""+this.property + "\" ");
		if(this.handler!=null&&!"".equals(this.handler)){
		sb.append("onclick= \""+this.handler+"\" ");
		}
	    sb.append(">");

	    if(!StringUtils.isEmpty(this.fontAwesome)){
	    	sb.append("<i class=\""+this.fontAwesome + "\">");
			sb.append("</i>");
	    }
		
		sb.append(" "+this.text);
		sb.append("<script>");
		sb.append(" $(function() { document.getElementById('"+this.getProperty()+"').addEventListener('click', function() { $('#"+this.getProperty()+"').attr('disabled','disabled'); setTimeout(function(){$('#"+this.getProperty()+"').removeAttr('disabled');},1000);}, false); });");
	    sb.append("</script>");
	    sb.append("</button>");
	    try {      
            JspWriter out = pageContext.getOut();  
            out.println(sb.toString());      
        } catch (IOException e) {      
            throw new JspException(e.getMessage());      
        } 
		return BodyTagSupport.SKIP_BODY;
	}
	public String getFontAwesome() {
		return fontAwesome;
	}
	public void setFontAwesome(String fontAwesome) {
		this.fontAwesome = fontAwesome;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
}
