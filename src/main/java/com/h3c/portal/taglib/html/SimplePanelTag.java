package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;


/**
 * *********************************************************************
 * 简单的panel标签
 * SimplePanelTag.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月16日 上午10:25:18
 * @revision    $Id:  *
 **********************************************************************
 */
public class SimplePanelTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String width;//宽度
	private String height;//高度
	private String title;//标题
	private String property;//标签的name和Id
	private String align;//标题的位置
	private String noTitle;//没有标题

	@Override
	public int doStartTag() throws JspException {
    
		StringBuilder sb = new StringBuilder(550);
			sb.append("<div  class='panel panel-default panel-border panel-shadow ' "
					+ "id=\""+this.property + "\" " );
			sb.append("style='padding-top:10px;font-family:Microsoft YaHei; ");
			if(this.width!=null&&!"".equals(this.width)){
				sb.append("width: "+this.width+"; ");
			}

			if(this.height!=null&&!"".equals(this.height)){
				sb.append("height: "+this.height+"; ");
			}
			sb.append("'>");
			sb.append("<div class='panel-heading' style='height:16px;margin-top:0px;' ");
			sb.append(" text-align:"+(StringUtils.isEmpty(this.align)?"left":this.align)+" '> ");
			if(this.title!=null&&!"".equals(this.title)){
				sb.append("<span style='font-size:15px;margin-left:10px;'>"+this.title+"</span>");
			}else{
				sb.append("<span style='font-size:15px;align:center'></span>");
			}
			
			sb.append("<div class='panel-options' style='margin-right:10px;' >");
       
			sb.append("<a href='#' data-toggle='panel'>");
			sb.append("<span class='collapse-icon'>–</span>");
			sb.append("<span class='expand-icon'>+</span></a>");
			sb.append("<a href='#' data-toggle='reload'>");
			sb.append("<i class='icon-refresh'></i></a>");
			sb.append("<a href='#' data-toggle='remove'>×</a>");
			sb.append("</div></div>");
			sb.append("<div class='panel-body'>");

	
			sb.append("<div class='panel-body' style='height: "+this.height+";' >");

		
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
    
		StringBuilder sb = new StringBuilder();
		
		
		sb.append("</div></div>");
		 try {      
	            JspWriter out = pageContext.getOut();      
	            out.println(sb.toString());      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } 
		return EVAL_PAGE;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getNoTitle() {
		return noTitle;
	}

	public void setNoTitle(String noTitle) {
		this.noTitle = noTitle;
	}


}
