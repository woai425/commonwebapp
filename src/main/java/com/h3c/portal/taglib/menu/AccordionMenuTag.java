package com.h3c.portal.taglib.menu;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.h3c.framework.common.entities.Sysfunction;
import com.h3c.portal.taglib.util.MenuUtil;
public class AccordionMenuTag extends BodyTagSupport{
	private static final long serialVersionUID = -9154411787777979142L;
	private List<Sysfunction> data;
	private String property;
	private String contentIframeId;
	private String dataurl;
	private String style;
	private String fontsize;
	private String width;
	private String height;
	private String usage;
	private String handler;
	@Override
	public int doStartTag() throws JspException {
        if((this.data == null)&&(this.dataurl == null)){
        	return EVAL_BODY_INCLUDE;
        }
        else{
        	MenuUtil tree = null;
        	StringBuilder buffer = new StringBuilder(1000);
        	String contextPath = ((HttpServletRequest)this.pageContext.getRequest()).getContextPath();
			if("back".equals(this.usage)){
				if("2".equals(this.style)){
					buffer.append("<link rel=\"stylesheet\" href=\""+contextPath+"/layout/css/menu/signStyle.css\">");
					buffer.append("<style type=\"text/css\">");
					buffer.append("#"+this.property+" {margin:0;padding:0;list-style-type:none;font-family:Microsoft YaHei;font-size:"+(this.fontsize==null?"13px":this.fontsize)+";width:"+(this.width==null?"200px":this.width)+";}");
					buffer.append("#h3c-accordionmenu-root {width:"+(this.width==null?"200px":this.width)+";}");
					buffer.append(".tree li a{color:#555;padding:.1em 7px .1em 25px;display:block;text-decoration:none;border:1px dashed #fff;background:url("+contextPath+"/images/menu/folderClose.png) 5px 50% no-repeat;}");
					String imgNameOpen = "treeicoMinus.gif";
					String imgNameClosed = "treeicoPlus.gif";
					buffer.append(".tree li a.tree-parent{background:url("+contextPath+"/images/menu/"+imgNameOpen+") 5px 50% no-repeat;}");
					buffer.append(".tree li a.tree-parent-collapsed{background:url("+contextPath+"/images/menu/"+imgNameClosed+") 5px 50% no-repeat;}");
					buffer.append("</style>");
				}else{
					buffer.append("<link rel=\"stylesheet\" href=\""+contextPath+"/layout/css/menu/fileStyle.css\">");
					buffer.append("<style type=\"text/css\">");
					buffer.append("#h3c-accordionmenu-root {text-align:left;width:"+((this.width==null)||"".equals(this.width)?"200px":this.width)+";font-size:"+((this.fontsize==null)||"".equals(this.fontsize)?"13px":this.fontsize)+"}");
					buffer.append("#h3c-accordionmenu-root a:hover {cursor:pointer;color:red;}");
					buffer.append("</style>");
				}
			}
			if("portal".equals(this.usage)){
				buffer.append("<link rel=\"stylesheet\" href=\""+contextPath+"/layout/css/menu/menu.css\">");
				if("1".equals(this.style)||(this.style == null)){
					buffer.append("<link rel=\"stylesheet\" href=\""+contextPath+"/layout/css/menu/blueflat.css\">");
					buffer.append("<style type=\"text/css\">");
					buffer.append("#c1 {padding: 12px 5px;font-weight:normal;background-color:#f6f6f6;font-size:"+(((this.fontsize == null)||"".equals(this.fontsize))?"13px":this.fontsize)+";color:#777;border-bottom: 1px solid #eee;}");
					buffer.append("</style>");
				 }
				if("3".equals(this.style)){
					buffer.append("<link rel=\"stylesheet\" href=\""+contextPath+"/layout/css/menu/whiteflat.css\">");
					buffer.append("<style type=\"text/css\">");
					buffer.append("#c1 {font-weight:normal;font-size:"+(((this.fontsize == null)||"".equals(this.fontsize))?"13px":this.fontsize)+";color:#555;padding: 12px 5px;border-bottom: 1px solid #eaeaea;}");
					buffer.append("</style>");
				 }
				if("4".equals(this.style)){
					buffer.append("<link rel=\"stylesheet\" href=\""+contextPath+"/layout/css/menu/whitenoflat.css\">");
					buffer.append("<style type=\"text/css\">");
					buffer.append("#c1 {font-weight:normal;background: -webkit-gradient(linear, left top, left bottom, color-stop(0, #FEFEFE), color-stop(1, #efefef));font-size:"+(((this.fontsize == null)||"".equals(this.fontsize))?"13px":this.fontsize)+";color:#555;padding: 12px 5px;border-bottom: 1px solid #eaeaea;}");
					buffer.append("</style>");
				 }
				if("2".equals(this.style)){
					buffer.append("<link rel=\"stylesheet\" href=\""+contextPath+"/layout/css/menu/blackflat.css\">");
					buffer.append("<style type=\"text/css\">");
					buffer.append("#c1 {font-weight:normal;font-size:"+(((this.fontsize == null)||"".equals(this.fontsize))?"13px":this.fontsize)+";padding: 12px 5px;}");
					buffer.append("</style>");
				 }
			}
			if(this.data!=null){
        		tree = new MenuUtil(this.data,this.usage,this.fontsize,this.width,this.height,this.property,this.contentIframeId,contextPath,this.style,this.handler);
        		//Tree(List<Node> nodes,String usage,String fontsize,String width,String height,String property,String contentIframeId,String contextPath,String style,String handler)
        		String menu = tree.buildTree();
        		buffer.append("<div id=\""+this.property+"\">");
			    buffer.append(menu);
			    buffer.append("</div>");
        	}else{
        		buffer.append("<div id=\""+this.property+"\"></div>");
        		buffer.append("<script src=\""+contextPath+"/basejs/menu/accordionmenu.js\"></script>");
        		buffer.append("<script type=\"text/javascript\">");
				buffer.append("	var menus =\"\";");
				buffer.append("	var html = [];");
				buffer.append("	var style = \""+this.style+"\";");
				buffer.append("	var usage = \""+this.usage+"\";");
				buffer.append("var width = \""+this.width+"\";");
				buffer.append("var height = \""+this.height+"\";");
				buffer.append("var fontsize = \""+this.fontsize+"\";");
				buffer.append("var property = \""+this.property+"\";");
				buffer.append("var handler = \""+this.handler+"\";");
				buffer.append("var contextPath = \""+contextPath+"\";");
				buffer.append("var contentIframeId = \""+this.contentIframeId+"\";");
				buffer.append("$(document).ready(function(){");
				buffer.append("$.ajax({");
				buffer.append("			type:'POST',");
				buffer.append("			async:false,");
				buffer.append("			url: '"+this.dataurl+"',");
				buffer.append("			dataType:'json',");
				buffer.append("			contentType: \"application/json\",");
				buffer.append("			success:function(rs){");
				buffer.append("				menus = rs.data;");
				buffer.append("				html = accordionMenu.buildTree(menus,html,usage,width,height,fontsize,property,contentIframeId,style,contextPath,handler);");
				buffer.append("				$(\"#"+this.property+"\").append(html.join(' '));");
				buffer.append("			},");
				buffer.append("			error:function(rs){");
				buffer.append("				console.log('获取菜单数据异常！');");
				buffer.append("			}");
				buffer.append("		});");
				buffer.append("	});");
				buffer.append("</script>");
        	}
			try {      
	            JspWriter out = pageContext.getOut();      
	            out.println(buffer.toString());      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } 
        }
		return EVAL_BODY_INCLUDE;
	}
	@Override
	public int doEndTag() throws JspException {
		StringBuilder buffer = new StringBuilder(200);
		String contextPath = ((HttpServletRequest)this.pageContext.getRequest()).getContextPath();
		if((this.data!=null)||(this.dataurl!=null)){
			if("back".equals(this.usage)){
				
				/* expanded: 'li:first'默认打开第一个菜单 */
				if("sign".equals(this.style)){
					buffer.append("<script type=\"text/javascript\" src=\""+contextPath+"/basejs/menu/tree.js\"></script>");
					buffer.append("<script type=\"text/javascript\">");
					buffer.append("$(function(){");
					buffer.append("$('#h3c-accordionmenu-root').tree({");
					buffer.append("collapsed: true,");
					buffer.append("animated: \"medium\",");
					buffer.append("control:\"#sidetreecontrol\",");
					buffer.append("persist: \"location\"");
					buffer.append("});");
					buffer.append("});");
					buffer.append("</script>");
				}else{
					buffer.append("<script type=\"text/javascript\" src=\""+contextPath+"/basejs/menu/treeview.js\"></script>");
					buffer.append("<script type=\"text/javascript\">");
					buffer.append("$(function() {");
					buffer.append("$(\"#h3c-accordionmenu-root\").treeview({");
					buffer.append("collapsed: true,");
					buffer.append("animated: \"fast\"");
					buffer.append("});");
					buffer.append("})");
					buffer.append("</script>");
				}
				// TODO 2017-8-2-zhoujie 对组织树的高度做y方向的滚动条，防止应为数据太多导致显示不全
				buffer.append("<script type=\"text/javascript\">");
				buffer.append("function treeInit(){");
				buffer.append("var height = document.documentElement.clientHeight;");
				buffer.append("$(\"#"+this.property+"\").css({");
				buffer.append("\"height\":height*0.9,");
				buffer.append("\"overflow-y\":\"auto\"");
				buffer.append("})");
				buffer.append("}");
				buffer.append("$(window).resize(function() {");
				buffer.append("treeInit();");
				buffer.append("});");
				buffer.append("$(function() {");
				buffer.append("treeInit();");				
				buffer.append("})");
				buffer.append("</script>");
				// ----------------------------------------------------------------------
			}else{
				buffer.append("<script src=\""+contextPath+"/basejs/menu/tweenmax.min.js\"></script>");
				buffer.append("<script src=\""+contextPath+"/basejs/menu/resizeable.js\"></script>");
				buffer.append("<script src=\""+contextPath+"/basejs/menu/xenon-custom.js\"></script>");
				buffer.append("<script type=\"text/javascript\">");
				buffer.append("function goIframe(iframeId,url){");
				buffer.append("document.getElementById(iframeId).src=url;");
				buffer.append("}");
				buffer.append("</script>");
			}
		}
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(buffer.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return EVAL_PAGE;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
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
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getContentIframeId() {
		return contentIframeId;
	}
	public void setContentIframeId(String contentIframeId) {
		this.contentIframeId = contentIframeId;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public List<Sysfunction> getData() {
		return data;
	}
	public void setData(List<Sysfunction> data) {
		this.data = data;
	}
	
}
