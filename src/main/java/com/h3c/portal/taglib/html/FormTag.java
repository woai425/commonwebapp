package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class FormTag extends BodyTagSupport{
	
private static final long serialVersionUID = 1L;
	private String name;//表单名
    private String action;//表单提交的事件
	private String enctype;//规定在发送到服务器之前应该如何对表单数据进行编码
	private String method;//提交的方法 
	private String target;//规定在何处打开 action URL



	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.println("</form>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return BodyTagSupport.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		StringBuilder buffer = new StringBuilder(100);
		buffer.append("<form ");
		if(this.name!=null&&!"".equals(this.name)){
			buffer.append(" id = \""+this.name+"\"");
			buffer.append(" name = \""+this.name+"\"");
		}else{
			buffer.append(" id = \"commForm\" ");
			buffer.append(" name = \"commForm\" ");
		}
		if(this.method!=null&&!"".equals(this.method)){
			buffer.append(" method = \""+this.method+"\"");
		}else{
			buffer.append(" method = \"post\" ");
		}
		if(this.action!=null&&!"".equals(this.action)){
			buffer.append(" action = \""+this.action+"\"");
		}
		
		if(this.enctype!=null&&!"".equals(this.enctype)){
			buffer.append(" enctype = \""+this.enctype+"\"");
		}
		
		if(this.target!=null&&!"".equals(this.target)){
			buffer.append(" target = \""+this.target+"\"");
		}
		buffer.append("> ");
		
		try {      
            JspWriter out = pageContext.getOut(); 
            
            out.println(buffer.toString());      
        } catch (IOException e) {      
            throw new JspException(e.getMessage());      
        } 
		return BodyTagSupport.EVAL_BODY_INCLUDE;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEnctype() {
		return enctype;
	}

	public void setEnctype(String enctype) {
		this.enctype = enctype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
