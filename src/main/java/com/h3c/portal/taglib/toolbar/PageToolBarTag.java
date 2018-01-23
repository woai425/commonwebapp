package com.h3c.portal.taglib.toolbar;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.JspTag;

import org.apache.commons.lang3.StringUtils;

import com.h3c.portal.taglib.grid.PortalGridTag;

/**
 * *********************************************************************
 * 分页工具栏
 * PageToolBarTag.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月22日 上午11:43:39
 * @revision    $Id:  *
 **********************************************************************
 */
public class PageToolBarTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String pageLimit;
	private String defaultLimit;
	
	public String getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(String pageLimit) {
		this.pageLimit = pageLimit;
	}
	
	public String getDefaultLimit() {
		return defaultLimit;
	}

	public void setDefaultLimit(String defaultLimit) {
		this.defaultLimit = defaultLimit;
	}

	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder(1200);
		JspTag tag = (JspTag) this.getParent();
		PortalGridTag gridTag = (PortalGridTag) tag;
		sb.append("<table id=\""+gridTag.getProperty()+"PageBar\" style=\"width: 100%; height: 100%; cellpadding: 0px; cellspacing: 0px; padding: 0 0 0 0; margin: 0 0 0 0; overflow: hidden; border-collapse: collapse;\">");
		sb.append("<tr><td align=\"left\"><div style=\"height: 30px;margin-top: 0px;padding-top: 10px;vertical-align: middle;\">共有<span id=\""+gridTag.getProperty()+"TotalCount\">0</span>条记录，当前显示<span id=\""+gridTag.getProperty()+"BegRecord\">0</span> - <span id=\""+gridTag.getProperty()+"EndRecord\">0</span>，第<span id=\""+gridTag.getProperty()+"Page\">0</span>/<span id=\""+gridTag.getProperty()+"TotalPage\">0</span>页</div></td>");
		sb.append("<td style=\"padding 0 0 0 0;vertical-align: bottom;\"><div class=\"pagination\" align=\"right\"><ul id=\"ul1\">");
		sb.append("<li><a id=\"first\" class=\"icon-fast-backward\" style=\"color: grey;\"></a></li>");
		sb.append("<li><a id=\"prev\" class=\"icon-backward\" style=\"color: grey;\"></a></li>");
		sb.append("<li><select name=\""+gridTag.getProperty()+"PageIndex\" class=\"input-xlarge\" onchange=\""+gridTag.getProperty()+"AjaxPageIndexQuery(this.value)\"");
		sb.append("id=\""+gridTag.getProperty()+"PageIndex\" style=\"width: 55px; height: 30px\"></select></li>");
		sb.append("<li><a id=\"next\" class=\"icon-forward\" style=\"color: grey;\"></a></li>");
		sb.append("<li><a id=\"last\" class=\"icon-fast-forward\" style=\"color: grey;\"></a></li></ul>");
		sb.append("<ul id=\"ul2\" style=\"padding-left: 5px;\"><li><select name=\""+gridTag.getProperty()+"PageLimit\" id=\""+gridTag.getProperty()+"PageLimit\" class=\"input-xlarge\"  onchange=\""+gridTag.getProperty()+"AjaxPageLimitQuery(this.value)\" ");
		sb.append("style=\"width: 55px; height: 30px;border-left-width: 1px;\"> ");
		if(!StringUtils.isEmpty(pageLimit)){
			String[] limits = pageLimit.split(",");
			Set<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					return o1-o2;
				}
			});
			for(int i=0;i<limits.length;i++){
				set.add(Integer.parseInt(limits[i]));
			}
			Iterator<Integer> it = set.iterator();  
			while (it.hasNext()) {  
			  int value = it.next();  
			  if(!StringUtils.isEmpty(defaultLimit)&&value==Integer.parseInt(defaultLimit)){
				  sb.append("<option value=\""+value+"\" selected=selected>"+value+"</option>"); 
			  }else{
				  sb.append("<option value=\""+value+"\">"+value+"</option>");
			  }
			}  
		}else{
			sb.append("<option value=\"10\" "+("10".equals(defaultLimit)?"selected=selected":"")+">10</option>");
			sb.append("<option value=\"15\" "+("15".equals(defaultLimit)?"selected=selected":"")+">15</option>");
			sb.append("<option value=\"25\" "+("25".equals(defaultLimit)?"selected=selected":"")+">25</option>");
			sb.append("<option value=\"50\" "+("50".equals(defaultLimit)?"selected=selected":"")+">50</option>");
		}

		sb.append("</select></li></ul>");
		sb.append("</div></td></tr></table>");
		try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return BodyTagSupport.SKIP_BODY;
	}
}
