package com.h3c.portal.taglib.layout;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;



/**
 * *********************************************************************
 * 布局标签之中间层标签
 * MiddleLayoutTag.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月14日 上午10:34:50
 * @revision    $Id:  *
 **********************************************************************
 */
public class MiddleLayoutTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder(150);
		sb.append("<tr id=\"middleLayoutId\">");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return BodyTagSupport.EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		StringBuilder sb = new StringBuilder(150);
		sb.append("</tr>");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return BodyTagSupport.EVAL_PAGE;
	}

}
