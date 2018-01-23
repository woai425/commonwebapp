package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;


/**
 * *********************************************************************
 *
 * GroupBoxTag.java
 *
 * H3C所有， 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * 
 * @copyright Copyright: 2015-2020
 * @creator z10926<br/>
 * @create-time 2016年1月9日 上午11:30:07
 * @revision $Id: *
 **********************************************************************
 */
public class GroupBoxTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String title;// 标签标题
	private String width;// 标签宽度
	private String property;// 标签id
	private String isRetracted;// groupBox框是否可伸缩；
	private String style;// 样式
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getIsRetracted() {
		return isRetracted;
	}

	public void setIsRetracted(String isRetracted) {
		this.isRetracted = isRetracted;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder(100);
		sb.append("<fieldset id="+this.property+" ");
		if(StringUtils.isEmpty(this.style)){
			sb.append(" style=\""+(StringUtils.isEmpty(this.width)?"100%":(this.width.indexOf("%")==-1?this.width+"px":this.width))+";\" ");
		}else{
			sb.append(" style=\""+(StringUtils.isEmpty(this.width)?"100%":(this.width.indexOf("%")==-1?this.width+"px":this.width))+";"+this.style+"\" ");
		}
		sb.append(">");
		if(this.title!=null&&!"".equals(this.title)){
			sb.append("<legend>"+this.title+"</legend>");
		}
		 try {      
	            JspWriter out = pageContext.getOut();      
	            out.println(sb.toString());      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } 
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		StringBuilder sb = new StringBuilder(100);
		sb.append("</fieldset>");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return BodyTagSupport.EVAL_PAGE;
	}

}
