package com.h3c.portal.taglib.layout;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;


import com.h3c.portal.taglib.util.TagUtil;

/**
 * *********************************************************************
 * 布局标签
 * PortalLayoutTag.java
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月4日 下午8:42:58
 * @revision    $Id:  *
 **********************************************************************
 */
public class PortalLayoutTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String property;//table的id和name
	private String width;//宽度
	private String height;//宽度
	private String selfAdaption;//页面布局自适应
	private String border;//边界

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
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
	
	public String getSelfAdaption() {
		return selfAdaption;
	}

	public void setSelfAdaption(String selfAdaption) {
		this.selfAdaption = selfAdaption;
	}
	
	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder(500);
		sb.append("<table style=\"width:100%; height:100%;cellpadding: 0px; cellspacing: 0px;padding: 0 0 0 0;margin: 0 0 0 0;overflow: hidden;border-collapse: collapse;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 0 0 0 0;margin: 0 0 0 0;\">");
		sb.append("<table id=\""+(StringUtils.isEmpty(this.property)?"portalLayoutId":this.property)+"\" border=\""+(StringUtils.isEmpty(this.border)?"0":this.border)+"\" style=\"vertical-align: middle;width: "+(StringUtils.isEmpty(this.width)?"100%":this.width)+"; height: "+(StringUtils.isEmpty(this.height)?"100%":this.height)+";border-color: blue;cellpadding: 0px; cellspacing: 0px;padding: 0 0 0 0;margin: 0 0 0 0;border-collapse: collapse;border-width: 0px;overflow: hidden;\">");
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
		StringBuilder sb = new StringBuilder(800);
		StringBuilder sb2 = new StringBuilder(400);
		String portalLayoutId = (StringUtils.isEmpty(this.property)?"portalLayoutId":this.property);
		Map<String,String> northMap = TagUtil.getTagPageContextParam(pageContext, "NORTH");
		Map<String,String> westMap = TagUtil.getTagPageContextParam(pageContext, "WEST");
		Map<String,String> centerMap = TagUtil.getTagPageContextParam(pageContext, "CENTER");
		Map<String,String> eastMap = TagUtil.getTagPageContextParam(pageContext, "EAST");
		Map<String,String> southMap = TagUtil.getTagPageContextParam(pageContext, "SOUTH");
		sb.append("</table></td></tr></table>");
		if(this.selfAdaption==null||"true".equals(this.selfAdaption)){
			sb.append("<script>");
			sb.append("$(document).ready(function(){");
			sb2.append("var height = document.documentElement.clientHeight;");
			sb2.append("$(\"#"+portalLayoutId+"\").height(height);");
					
			//处理中间层高度自适应
			String northHeight = "0";
			if(northMap!=null){
				northHeight = northMap.get("P1");
				sb2.append("$(\"#northLayoutId\").height("+northHeight+");");
			}
			String southHeight = "0";
			if(southMap!=null){
				southHeight = southMap.get("P1");
				sb2.append("$(\"#southLayoutId\").height("+southHeight+");");
			}
			if(westMap!=null){
				//sb2.append("$(\"#westLayoutId\").height(height-"+northHeight+"-"+southHeight+"-10);");
			}else if(centerMap!=null){
				//sb2.append("$(\"#centerLayoutId\").height(height-"+northHeight+"-"+southHeight+"-10);");
			}else if(eastMap!=null){
				//sb2.append("$(\"#eastLayoutId\").height(height-"+northHeight+"-"+southHeight+"-10);");
			}
			
			//处理中间层宽度自适应,先处理高度是要去除瞬间产生的下拉滚动条，这样获取的宽度才是正确的
			sb2.append("var width = $(\"#"+portalLayoutId+"\").width();");
			String westWidth = "0",centerWidth="0",eastWidth="0";
			int count = 0 ;
			if(westMap!=null){
				westWidth = westMap.get("P0");
				count++;
			}
			if(centerMap!=null){
				centerWidth = centerMap.get("P0");
				count++;
			}
			if(eastMap!=null){
				eastWidth = eastMap.get("P0");
				count++;
			}
            if(!"0".equals(centerWidth)&&!"0".equals(eastWidth)){
            	sb2.append("$(\"#centerLayoutId\").width((width-"+westWidth+")*("+centerWidth+"/("+centerWidth+"+"+eastWidth+")));");
            	sb2.append("$(\"#eastLayoutId\").width((width-"+westWidth+")*("+eastWidth+"/("+centerWidth+"+"+eastWidth+")));");
			}else{
				if(!"0".equals(centerWidth)){
					sb2.append("$(\"#centerLayoutId\").width(width-"+westWidth+");");
					if(count==1){
						sb2.append("$(\"#centerLayoutId\").attr('colspan',3);");
					}else if(count==2){
						sb2.append("$(\"#centerLayoutId\").attr('colspan',2);");
					}
				}
	            if(!"0".equals(eastWidth)){
	            	sb2.append("$(\"#eastLayoutId\").width(width-"+westWidth+");");
					if(count==1){
						sb2.append("$(\"#eastLayoutId\").attr('colspan',3);");
					}else if(count==2){
						sb2.append("$(\"#eastLayoutId\").attr('colspan',2);");
					}
				}	
			}
			if(!StringUtils.isEmpty(westWidth)&&!"0".equals(westWidth)){
				sb2.append("$(\"#westLayoutId\").width("+westWidth+");");
			}
			
			sb.append(sb2);
			sb.append("});");
			sb.append("$(window).resize(function() {");
			sb.append("setTimeout(function(){ ");
			sb.append(sb2);
			sb.append("},100);});");
			sb.append("</script>");
		}
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return BodyTagSupport.EVAL_PAGE;
	}

}
