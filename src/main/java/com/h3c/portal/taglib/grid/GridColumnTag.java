package com.h3c.portal.taglib.grid;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.JspTag;

import org.apache.commons.lang3.StringUtils;


import com.h3c.framework.common.CodeManager;

/**
 * *********************************************************************
 * Grid列标签
 * GridEditColumnTag.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月15日 下午2:54:16
 * @revision    $Id:  *
 **********************************************************************
 */
public class GridColumnTag extends BodyTagSupport{
	
	private static final long serialVersionUID = 1L;
	private String header; //selectall时这列的头部显示一个checkbox，同时需要使用gridName属性（其负责处理全选）
	private String width;
	private String sortable;//是否允许排序
	private String dataIndex;//id的值
	private String hidden;
	private String renderer; //对列的显示进行特殊处理的js函数名字
	private String editor;
	private String codeType;//代码类型
	private String showTitle;//会在鼠标移到元素上时显示一段工具提示文本
	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

	public String getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	
	public String getRenderer() {
		return renderer;
	}

	public void setRenderer(String renderer) {
		this.renderer = renderer;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder(300);
		JspTag tag = (JspTag) this.getParent();
		GridColumnModelTag gridColumn = (GridColumnModelTag) tag;
		sb.append("<th id=\""+gridColumn.getGridName()+"DivGrid_"+this.dataIndex+"\" dataIndex=\""+this.dataIndex+"\" ");
		
		sb.append(" editor=\""+(StringUtils.isEmpty(this.editor)?"text":this.editor)+"\" ");
		if(!StringUtils.isEmpty(this.renderer)){
			sb.append(" renderer=\""+this.renderer+"\" ");
		}
		if("true".equals(this.hidden)){
			sb.append(" dataHidden=\""+this.hidden+"\" ");	
		}
		if(!StringUtils.isEmpty(this.width)&&"true".equals(this.hidden)){
			sb.append(" style=\"width: "+this.width+"px;display: none;\" ");
		}else if(!StringUtils.isEmpty(this.width)){
			sb.append(" style=\"width: "+this.width+"px;\" ");
		}else if("true".equals(this.hidden)){
			sb.append(" style=\"display: none;\" ");
		}
		if("true".equals(this.showTitle)){
			sb.append(" showTitle=\""+this.showTitle+"\" ");	
		}
		sb.append("> ");
		if("selectall".equals(this.header)){
			sb.append("<div id=\""+gridColumn.getGridName() + this.getDataIndex() + "ChooseAll\" class=\"h3c-grid-check-col\" onclick=\"h3c.checkBoxChooseAll('"+gridColumn.getGridName()+"','"+ this.getDataIndex() +"')\"></div></th>");
		}else{
			sb.append("<span>"+this.header+"");
			if("select".equals(this.editor)&&!StringUtils.isEmpty(this.codeType)){
				sb.append("<input type=\"hidden\" id=\""+gridColumn.getGridName()+"_"+this.dataIndex+"_select\" class=\"gridSelectDD_\" value=\""+CodeManager.getArrayCode(codeType)+"\">");
			}
			sb.append("</span>"+("true".equals(this.sortable)?"<span class=\"h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-carat-2-n-s\" onclick=\"h3c.changeSortClass(this,'"+gridColumn.getGridName()+"','"+this.dataIndex+"')\">&nbsp;&nbsp;&nbsp;&nbsp;</span>":"")+"</th>");
		}
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return BodyTagSupport.SKIP_BODY;
	}
	

}
