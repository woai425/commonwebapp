package com.h3c.portal.taglib.grid;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.JspTag;

import org.apache.commons.lang3.StringUtils;


/**
 * *********************************************************************
 * grid字段模型规范标签
 * GridColumnModelTag.java
 *
 * H3C所有， 
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright Copyright: 2015-2020
 * @creator z10926<br/>
 * @create-time 2015年12月20日 上午11:56:57
 * @revision $Id: *
 **********************************************************************
 */
public class GridColumnModelTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String withoutRownum;
	private String rowHeight;
	private String gridName;//供子标签查询用，不在前台赋值
	private String rowNumSortable;//行号是否允许排序

	public String getGridName() {
		return gridName;
	}

	public String getRowHeight() {
		return rowHeight;
	}

	public void setRowHeight(String rowHeight) {
		this.rowHeight = rowHeight;
	}

	public String getWithoutRownum() {
		return withoutRownum;
	}

	public void setWithoutRownum(String withoutRownum) {
		this.withoutRownum = withoutRownum;
	}
	
	public String getRowNumSortable() {
		return rowNumSortable;
	}

	public void setRowNumSortable(String rowNumSortable) {
		this.rowNumSortable = rowNumSortable;
	}

	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = null;
		if("true".equals(withoutRownum)){
			sb = new StringBuilder(800);
		}else{
			sb = new StringBuilder(600);
		}
		JspTag tag = this.getParent();
		PortalGridTag gridTag = (PortalGridTag) tag;
		String titlePadding_top = gridTag.getTitlePaddingTop();
		sb.append("<div id=\""
				+ gridTag.getProperty()
				+ "Div\" class=\"well\" style=\"overflow:auto;min-width: 600px; min-height: 130px; height: "+(StringUtils.isEmpty(gridTag.getHeight())?"100%":gridTag.getHeight())+"px;"+(StringUtils.isEmpty(titlePadding_top)?"":"padding-top : "+titlePadding_top+"px;")+"\">");	
		sb.append("<div id=\""+gridTag.getProperty()+"mask\" class=\"grid-mask\" style=\"display: none; text-align: center; overflow: auto;\">");
		sb.append("<table width=\"100%\" height=\"100%\"><tr><td align=\"center\" valign=\"middle\"><div id=\""+gridTag.getProperty()+"WaitingContainer\" style=\"vertical-align: middle;\" align=\"center\"></div></td></tr></table></div>");
		sb.append("<table id=\"" + gridTag.getProperty()+"DivGrid\" class=\"h3c-table\" "+(StringUtils.isEmpty(this.rowHeight)?"hgt=\"40\"":"hgt="+this.rowHeight)+" >");
		sb.append("<thead>");
		sb.append("<tr>");
		this.gridName = gridTag.getProperty();
		// TODO 2017-7-28-zhoujie 修改之前排序的#号为“序号”
		if ("true".equals(withoutRownum)) {
			sb.append("<th id=\""
					+ gridTag.getProperty()
					+ "DivGrid_rowNum\" dataIndex=\"rowNum\"  style=\"width: 20px;\">序号"
					+ ("true".equals(this.rowNumSortable) ? "<span class=\"h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-carat-2-n-s\" onclick=\"h3c.changeSortClass(this,'"
							+ this.gridName + "','rowNum')\">&nbsp;&nbsp;&nbsp;&nbsp;</span>"
							: "") + "</th>");
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
		StringBuilder sb = new StringBuilder();
		sb.append("</tr></thead></table></div>");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return BodyTagSupport.EVAL_PAGE;
	}
}
