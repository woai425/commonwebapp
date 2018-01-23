package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * *********************************************************************
 * Portal框架的 按钮权限过滤
 * ButtonAuthFilterTag.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年2月21日 上午9:49:28
 * @revision    $Id:  *
 **********************************************************************
 */
public class ButtonAuthFilterTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		return BodyTagSupport.EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		StringBuilder sb = new StringBuilder(380);
		sb.append("<script>");
		sb.append("$(document).ready(function(){");
		sb.append("var protal_btn = MDParam.buttonid;");
		sb.append("var p_btns = protal_btn.split(',');");
		sb.append("for(var i=0;i<p_btns.length;i++){");
		sb.append("$('#'+p_btns[i]).attr('disabled','true');");
		sb.append("try{var btn_class = document.getElementById(p_btns[i]).className;document.getElementById(p_btns[i]).className=(btn_class==null?'':btn_class+ ' btn-auth-grey');}catch(e){}");
				sb.append("}});</script>");
        try {      
            JspWriter out = pageContext.getOut();      
            out.println(sb.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
		return BodyTagSupport.EVAL_PAGE;
	}

}
