package com.h3c.portal.taglib.layout;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;



import com.h3c.portal.taglib.util.TagUtil;

/**
 * *********************************************************************
 * 布局标签之北向标签
 * NorthLayoutTag.java
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月7日 下午8:21:24
 * @revision    $Id:  *
 **********************************************************************
 */
public class NorthLayoutTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String width;//宽度
	private String height;//宽度

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

	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder(150);
		sb.append("<tr>");
		sb.append("<td id=\"northLayoutId\" colspan=\"3\" align=\"center\" valign=\"middle\" style=\"padding: 0 0 0 0;margin: 0 0 0 0;\">");
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
        TagUtil.setTagPageContextParam(pageContext,"NORTH",this.width, this.height);
        sb.append("</td></tr>");
        try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        }
		return BodyTagSupport.EVAL_PAGE;
	}
}
