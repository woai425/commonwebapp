package com.h3c.portal.taglib.grid;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.h3c.portal.taglib.util.TagUtil;

/**
 * *********************************************************************
 * Grid外层标签
 * PortalGridTag.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月15日 下午2:48:51
 * @revision    $Id:  *
 **********************************************************************
 */
public class PortalGridTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String property;//表格的name或者说id
	private String title;//标题
	private String width;//宽度
	private String height;//高度
	private String forceNoScroll;//是否强制不横向滚动，默认是不强制，即横向可以滚动
	private String url;//表格数据也可通过此url来获取
	private String padding_top;
	private String padding_left;
	private String padding_right;
	private String padding_bottom;
	private String titlePaddingTop;//标题的padding
	private String isAsyncLoad;//是否异步，默认异步加载
    private String isColElastic;//是否允许变更列宽
	private String rowClick;//行单击事件
	private String rowDbClick;//行双击事件

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

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

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getForceNoScroll() {
		return forceNoScroll;
	}

	public void setForceNoScroll(String forceNoScroll) {
		this.forceNoScroll = forceNoScroll;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPadding_top() {
		return padding_top;
	}

	public void setPadding_top(String padding_top) {
		this.padding_top = padding_top;
	}

	public String getPadding_left() {
		return padding_left;
	}

	public void setPadding_left(String padding_left) {
		this.padding_left = padding_left;
	}

	public String getPadding_right() {
		return padding_right;
	}

	public void setPadding_right(String padding_right) {
		this.padding_right = padding_right;
	}

	public String getPadding_bottom() {
		return padding_bottom;
	}

	public void setPadding_bottom(String padding_bottom) {
		this.padding_bottom = padding_bottom;
	}
	
	public String getTitlePaddingTop() {
		return titlePaddingTop;
	}

	public void setTitlePaddingTop(String titlePaddingTop) {
		this.titlePaddingTop = titlePaddingTop;
	}

	public String getIsAsyncLoad() {
		return isAsyncLoad;
	}

	public void setIsAsyncLoad(String isAsyncLoad) {
		this.isAsyncLoad = isAsyncLoad;
	}
	
	public String getIsColElastic() {
		return isColElastic;
	}

	public void setIsColElastic(String isColElastic) {
		this.isColElastic = isColElastic;
	}
	
	public String getRowDbClick() {
		return rowDbClick;
	}

	public void setRowDbClick(String rowDbClick) {
		this.rowDbClick = rowDbClick;
	}

	public String getRowClick() {
		return rowClick;
	}

	public void setRowClick(String rowClick) {
		this.rowClick = rowClick;
	}
	
	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder(450);
		int padding = TagUtil.gridPadding;
		sb.append("<table id=\""+this.property+"\" style=\"background-color: #FFFFFF; width: "+(StringUtils.isEmpty(this.width)?"100%":(this.width.indexOf("%")==-1?this.width+"px":this.width))+"; cellpadding: 0px; cellspacing: 0px; padding: 0 0 0 0; margin: 0 0 0 0; overflow: hidden; border-collapse: collapse;\" >");
		sb.append("<tr><td id=\""+this.property+"_padding\" style=\"padding-right: "+(StringUtils.isEmpty(this.padding_right)?padding:this.padding_right) +"px; padding-left: "+(StringUtils.isEmpty(this.padding_left)?padding:this.padding_left)+"px; padding-bottom: "+(StringUtils.isEmpty(this.padding_bottom)?padding:this.padding_bottom)+"px; padding-top: "+(StringUtils.isEmpty(this.padding_top)?padding:this.padding_top)+"px;\">");
		if(!StringUtils.isEmpty(this.title)){
			sb.append("<h4 id=\""+this.property+"_title\" align=\"left\" style=\"margin-top: 0px;\" >"+this.title+"</h4>");
			sb.append("<hr style=\"margin: 0 0 10px 0; height: 1px; border: none; border-top: 1px dashed #0066CC;\">");
		}
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
		StringBuilder sb = new StringBuilder(1100);
		sb.append("<input type=\"hidden\" id=\""+this.property+"Data\" name=\""+this.property+"Data\"/></td></tr></table>");
		sb.append("<script>");
		if("true".equals(isColElastic)){
			sb.append(" var "+this.property+"TD = \"\";");//用来存储当前更改宽度的Table Cell,避免快速移动鼠标的问题 
		}
		sb.append("var "+this.property+"Params = {};");
		sb.append("function "+this.property+"AjaxPageIndexQuery(pageIndex){");
		sb.append("	var start = (pageIndex-1)*"+this.property+"Params.limit; ");
		sb.append("	"+this.property+"AjaxPageQuery('"+this.property+"', "+this.property+"Params, start, "+this.property+"Params.limit); }");
		
		sb.append("function "+this.property+"AjaxPageLimitQuery(limit){");
		sb.append("	"+this.property+"AjaxPageQuery('"+this.property+"', "+this.property+"Params, 0, limit); }");
		
		sb.append("function "+this.property+"AjaxPageQuery(gridId, params, start, limit) {");
		
		sb.append("h3c.gridLoading('" + this.property + "');");
		sb.append("$('#' + gridId + 'DivGrid tr:gt(0)').remove();");

		sb.append("setTimeout(function(){ ");// 让grid框先加载出来，让进度条也提前展现
		sb.append(""+this.property+"Params=params;");
		sb.append(""+this.property+"Params.start=start;");
		sb.append(""+this.property+"Params.limit=limit;");
		sb.append("$.ajax({url : '"+this.url+"',");
		sb.append("type : 'post',data : "+this.property+"Params,dataType : 'json',");			
		sb.append("success : function(rs) {");
		sb.append("h3c.setGridData(gridId, start, rs ,"+(!StringUtils.isEmpty(rowClick) ? rowClick : null)+","+(!StringUtils.isEmpty(rowDbClick) ? rowDbClick : null)+");");

		sb.append("if($('#"+this.property+"PageBar').length>0){");	
		sb.append(" h3c.setPageToolBar(gridId, rs.totalCount, start, limit);");
		sb.append("} ");	
		if("true".equals(isColElastic)){
			sb.append(" h3c.colResize(gridId,"+this.property+"TD);");
		}
		sb.append(" setTimeout(function(){h3c.gridUnloading('" + this.getProperty() + "');},300); },");
		sb.append("error : function() {");
		sb.append(" setTimeout(function(){h3c.gridUnloading('" + this.getProperty() + "');},300); ");
		sb.append("},");
		sb.append("async : "+(StringUtils.isEmpty(this.isAsyncLoad) ? true : this.isAsyncLoad)+"});}");
		sb.append(", 150);}");
		sb.append("</script>");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return BodyTagSupport.EVAL_PAGE;
	}
}
