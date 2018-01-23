package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.h3c.framework.core.persistence.CurrentUser;
import com.h3c.framework.util.GlobalNames;

/**
 * *********************************************************************
 *
 * DupLoginCheckTag.java
 * 重复登录校验标签
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月21日 上午9:02:38
 * @revision    $Id:  *
 **********************************************************************
 */
public class DupLoginCheckTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String rate;//校验得频率
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	@Override
	public int doStartTag() throws JspException {
		String dupLoginCheckFlag = GlobalNames.sysConfig.get("DUP_LOGIN_CHECK");
		if("1".equals(dupLoginCheckFlag)){
			HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
			CurrentUser cUser = (CurrentUser)request.getSession().getAttribute("cUser");
			String contextPath = request.getContextPath();
			StringBuilder sb = new StringBuilder(600);
			sb.append("<script> $(document).ready(function(){");
			sb.append("var dupLoginCheckTimer =setInterval(function(){");
			sb.append("var params = {};params.time = new Date();params.loginname = '"+cUser.getLoginname()+"';");
			sb.append("params.username = '"+cUser.getUsername()+"';");
			sb.append("$.ajax({type : 'POST',url : '"+contextPath+"/loginController.do?checkIsLoginOrNot',data : params,");
			sb.append("dataType : 'json',success : function(rs){");
			sb.append("if(rs.success==false){ clearInterval(dupLoginCheckTimer); h3c.autoCloseAlert('您好，您的账号在IP地址为['+rs.data.ip+']的机器上被登录，请确认！',function(){");
			sb.append("window.top.location.href = '"+contextPath+"/loginController.do?logout';");
			sb.append("},'','',120,8);}},error : function(rs){},async : true});");
			sb.append("},"+(StringUtils.isEmpty(this.rate)?10000:Integer.parseInt(this.rate)*1000)+");}); </script>");
	        try {      
	            JspWriter out = pageContext.getOut();      
	            out.println(sb.toString());      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } 
		}
		return BodyTagSupport.SKIP_BODY;
	}
}
