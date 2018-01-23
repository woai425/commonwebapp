package com.h3c.portal.taglib.tab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.h3c.framework.common.dto.KeyAndValueDTO;

public class TabTag extends BodyTagSupport{
	private static final long serialVersionUID = -3171070837401743380L;
	private String property;
	private String height;
	private String width;
	private String data;
	private String handler;
	private String titlebgcolor;
	private String contentbgcolor;
	private String activecolor;
	private String activeTab;
	@Override
	public int doStartTag() throws JspException {
		StringBuilder buffer = new StringBuilder(100);
		if(StringUtils.isEmpty(data)){
			return EVAL_BODY_INCLUDE;
		}
		String contextPath = ((HttpServletRequest)this.pageContext.getRequest()).getContextPath();
		@SuppressWarnings("rawtypes")
		List<KeyAndValueDTO> list = new ArrayList<KeyAndValueDTO>();
		String[] str1 = data.replace("],[", "]@@[").split("@@");
		String[] str2 = null; 
		for (int i = 0; i < str1.length; i++) {
			str2 = str1[i].replaceAll("(\\[)|(\\])|(\\'*)", "").split(",");
			list.add(new KeyAndValueDTO<String,String>(str2[0], str2[1]));
		}
		buffer.append("<link rel=\"stylesheet\" href=\""+contextPath+"/layout/css/tab/tab.css\">");
		buffer.append("<style type=\"text/css\">");
		buffer.append(".nav.nav-tabs > li > a {");
		buffer.append("  background-color: "+this.titlebgcolor+";");
		buffer.append("}");
		buffer.append(".nav.nav-tabs > li.active > a {");
		buffer.append("color:#ffffff;");
		buffer.append("background-color: "+this.activecolor+";");
		buffer.append("}");
		buffer.append(".nav.nav-tabs + .tab-content {");
		buffer.append("  background: "+this.contentbgcolor+";");
		buffer.append("}");
		buffer.append(".tab-pane{");
		buffer.append("	width:"+(StringUtils.isEmpty(this.width)?"100%":(this.width.indexOf("%")==-1?this.width+"px":this.width))+"px;");
		buffer.append("word-break: break-all;word-wrap: break-word;}");
		// TODO 2017-8-2-zhoujie 设置最小宽度为800px，防止页面太窄而出现换行
		buffer.append("#" + this.property + "{min-width:800px;}");
		// ------------------------------------------------------------
		buffer.append("</style>");
		
		buffer.append("<ul class=\"nav nav-tabs\" id=\""+this.property+"\">" );
		for(int i = 0;i<list.size();i++){
			if(StringUtils.isEmpty(activeTab)&&(i==0)){
				buffer.append("<li class=\"active\">");
			}
			else if(list.get(i).getKey().equals(activeTab)){
				buffer.append("<li class=\"active\">");
			}else{
				buffer.append("<li>");
			}
			if(StringUtils.isEmpty(this.handler)){
				buffer.append("<a href=\"#"+list.get(i).getKey()+"\" data-toggle=\"tab\">");
			}else{
				buffer.append("<a onclick=\""+this.handler+"('"+list.get(i).getKey()+"','"+list.get(i).getValue()+"')\" href=\"#"+list.get(i).getKey()+"\" data-toggle=\"tab\">");
			}
			buffer.append("<span><i class=\"\"></i></span>");
			buffer.append("<span>"+list.get(i).getValue()+"</span>");
			buffer.append("</a>");
			buffer.append("</li>");
		}
		buffer.append("</ul>");
		buffer.append("<div id=\""+this.property+"\" class=\"tab-content\" style=\"overflow:auto;overflow-x:hidden;width:"+(StringUtils.isEmpty(this.width)?"100%":(this.width.indexOf("%")==-1?this.width+"px":this.width))+";height:"+(StringUtils.isEmpty(this.height)?"100%":(this.height.indexOf("%")==-1?this.height+"px":this.height))+"\">");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(buffer.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {      
            JspWriter out = pageContext.getOut();      
            out.println("</div>");      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return EVAL_PAGE;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getTitlebgcolor() {
		return titlebgcolor;
	}

	public void setTitlebgcolor(String titlebgcolor) {
		this.titlebgcolor = titlebgcolor;
	}

	public String getContentbgcolor() {
		return contentbgcolor;
	}

	public void setContentbgcolor(String contentbgcolor) {
		this.contentbgcolor = contentbgcolor;
	}

	public String getActivecolor() {
		return activecolor;
	}

	public void setActivecolor(String activecolor) {
		this.activecolor = activecolor;
	}

	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}
	
}
