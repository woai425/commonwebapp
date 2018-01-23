package com.h3c.portal.taglib.menu;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TreeMenuTag extends BodyTagSupport{
	private static final long serialVersionUID = -9154411787777979142L;
	private String property;
	private String dataurl;
	private String fontsize;
	private String width;
	private String height;
	private String handler;
	public int doStartTag() throws JspException {
    	StringBuilder buffer = new StringBuilder(1000);
    	String contextPath = ((HttpServletRequest)this.pageContext.getRequest()).getContextPath();			
		buffer.append("<link rel=\"stylesheet\" href=\""+contextPath+"/layout/css/menu/fileStyle.css\">");
		buffer.append("<style type=\"text/css\">");
		buffer.append("#h3c-accordionmenu-root {text-align:left;width:"+(this.width==null||"".equals(this.width)?"200px":this.width)+";font-size:"+(this.fontsize==null||"".equals(this.fontsize)?"13px":this.fontsize)+"}");
		buffer.append("#h3c-accordionmenu-root a:hover {cursor:pointer;color:red;}");
		buffer.append("</style>");
		buffer.append("<div id=\""+this.property+"\"></div>");
		buffer.append("<script src=\""+contextPath+"/basejs/menu/treemenu.js\"></script>");
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("	var menus =\"\";");
		buffer.append("	var html = [];");
		buffer.append("var width = \""+this.width+"\";");
		buffer.append("var height = \""+this.height+"\";");
		buffer.append("var fontsize = \""+this.fontsize+"\";");
		buffer.append("var property = \""+this.property+"\";");
		buffer.append("var handler = \""+this.handler+"\";");
		buffer.append("$(function(){");
		buffer.append("$.ajax({");
		buffer.append("			type:'POST',");
		buffer.append("			async:false,");
		buffer.append("			url: '"+this.dataurl+"',");
		buffer.append("			dataType:'json',");
		buffer.append("			contentType: \"application/json\",");
		buffer.append("			success:function(rs){");
		buffer.append("				menus = rs.data;");
		buffer.append("				html = treeMenu.buildTree(menus,html,width,height,fontsize,property,handler);");
		buffer.append("				$(\"#"+this.property+"\").append(html.join(' '));");
		buffer.append("			},");
		buffer.append("			error:function(rs){");
		buffer.append("				console.log('获取菜单数据异常！');");
		buffer.append("			}");
		buffer.append("		});");
		buffer.append("	});");
		buffer.append("</script>");
    	
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
		StringBuilder buffer = new StringBuilder(200);
		String contextPath = ((HttpServletRequest)this.pageContext.getRequest()).getContextPath();
		/* expanded: 'li:first'默认打开第一个菜单 */
		buffer.append("<script type=\"text/javascript\" src=\""+contextPath+"/basejs/menu/menucheckbox.js\"></script>");
		buffer.append("<script type=\"text/javascript\" src=\""+contextPath+"/basejs/menu/treeview.js\"></script>");
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("$(function() {");
		buffer.append("$(\"#h3c-accordionmenu-root\").treeview({");
		buffer.append("collapsed: true,");
		buffer.append("animated: \"fast\"");
		buffer.append("});");
		buffer.append("})");
		buffer.append("</script>");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(buffer.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return EVAL_PAGE;
	}
	public String getFontsize() {
		return fontsize;
	}
	public void setFontsize(String fontsize) {
		this.fontsize = fontsize;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getDataurl() {
		return dataurl;
	}
	public void setDataurl(String dataurl) {
		this.dataurl = dataurl;
	}
	
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
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
	
}
