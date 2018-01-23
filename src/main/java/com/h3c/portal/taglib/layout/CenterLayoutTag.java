package com.h3c.portal.taglib.layout;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;


import com.h3c.portal.taglib.util.TagUtil;

/**
 * *********************************************************************
 * 布局标签之中心标签
 * CenterLayoutTag.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月14日 上午10:22:19
 * @revision    $Id:  *
 **********************************************************************
 */
public class CenterLayoutTag  extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String width;//宽度
	private String align;//水平位置调整
	private String valign;//垂直位置调整

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getValign() {
		return valign;
	}

	public void setValign(String valign) {
		this.valign = valign;
	}

	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder(120);
		sb.append("<td id=\"centerLayoutId\" style=\"padding: 0 0 0 0;margin: 0 0 0 0;\"");
	    sb.append(" align=\""+(StringUtils.isEmpty(this.align)?"center":this.align)+"\" ");
		sb.append(" valign=\""+(StringUtils.isEmpty(this.valign)?"middle":this.valign)+"\" ");
		sb.append(">");
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
		StringBuilder sb = new StringBuilder(120);
		TagUtil.setTagPageContextParam(pageContext,"CENTER",(StringUtils.isEmpty(this.width)?"1":this.width), "");
		sb.append("</td>");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return BodyTagSupport.EVAL_PAGE;
	}
}
