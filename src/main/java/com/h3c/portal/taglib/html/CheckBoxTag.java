package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;



public class CheckBoxTag extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	
    private String label; //Checkbox右边的文本
    private String property;//Checkbox 的id和name
    private String handler;//checkBox选中触发的事件
    private String check;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public int doStartTag() throws JspException{
		StringBuilder sb = new StringBuilder(600);
		if(this.check!=null&&!"".equals(this.check)){
			sb.append("<table><tr><td>");
			sb.append("<div id=\""+this.property + "\" class='h3c-check-col-on'    ");
			if(this.handler!=null&&!"".equals(this.handler)){
				sb.append("  onclick="+this.handler+" >");
		    }
			
			sb.append("</div></td> ");
			if(this.label!=null&&!"".equals(this.label)){
				sb.append("<td >"+this.label+"</td>");
			}
			sb.append("</tr></table>");
		}else{
	    sb.append("<table><tr><td>");
		sb.append("<div id=\""+this.property + "\" class='h3c-check-col' onclick='checkBox(this)'    >");
		sb.append("</div></td>");
		if(this.label!=null&&!"".equals(this.label)){
			sb.append("<td >"+this.label+"</td>");
		}
		sb.append("</tr></table>");
		sb.append("<script type=\"text/javascript\">");
		sb.append("function checkBox(obj){");
		sb.append("var obj = document.getElementById(obj.id);");
		sb.append("if (obj.className == 'h3c-check-col') {");
		sb.append("obj.className = 'h3c-check-col-on';");
		sb.append("document.getElementById(obj.id.replace('-check','')).checked = \"checked\";");
		if(this.handler!=null&&!"".equals(this.handler)){
			sb.append(this.handler+";");
	    }
		sb.append(" }else{ obj.className = 'h3c-check-col';");
		sb.append("document.getElementById(obj.id.replace('-ckeck','')).checked = \" \" ;");
		sb.append("}");
		
		sb.append("}");
		sb.append("</script>");
		}  
		try {      
            JspWriter out = pageContext.getOut(); 
               
            out.println(sb.toString());      
        } catch (IOException e) {      
            throw new JspException(e.getMessage());      
        } 
		return BodyTagSupport.SKIP_BODY;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	
}
