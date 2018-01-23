package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;


public class TabLayOutTag extends BodyTagSupport {


	private static final long serialVersionUID = 1L;
	private String column;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	@Override
	public int doStartTag() throws JspException {
		StringBuilder buffer = new StringBuilder(150);
		if(StringUtils.isEmpty(this.column))
		{
			this.column = "3";
		}
		buffer.append("<COLGROUP>");
		float width = (100-Integer.parseInt(this.column)*10)/(Float.parseFloat(this.column));
		for(int i=0;i<Integer.parseInt(this.column)*2;i++)
		{
			if(i%2==0){
				buffer.append("<COL width='10%'>");
			}else
			{
				buffer.append("<COL width='"+width+"%'>");
			}
		}
		buffer.append("</COLGROUP>");
		
		 try {      
	            JspWriter out = pageContext.getOut();      
	            out.println(buffer.toString());      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } 
		return TabLayOutTag.SKIP_BODY;
	}
	
    
}
