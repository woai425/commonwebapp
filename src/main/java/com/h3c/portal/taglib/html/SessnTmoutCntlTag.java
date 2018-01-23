package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.h3c.framework.util.GlobalNames;

public class SessnTmoutCntlTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		String sessnTmoutCntl = GlobalNames.sysConfig.get("SESSN_TMOUT_CNTL");
		if("1".equals(sessnTmoutCntl)){
			HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
			String contextPath = request.getContextPath();
			StringBuilder sb = new StringBuilder(480);
			sb.append("<script> $(document).ready(function(){");
			sb.append("setInterval(function(){");
			sb.append("var params = {};params.time = new Date();");
			sb.append("$.ajax({type : 'POST',url : '"+contextPath+"/loginController.do?checkSessnIsTmoutOrNot',data : params,");
			sb.append("dataType : 'json',success : function(rs){ if(rs==false){ window.top.location.href = '"+contextPath+"/loginController.do?logout';} }");
			sb.append(",error : function(rs){window.top.location.href = '"+contextPath+"/loginController.do?logout';},async : true});");
			sb.append("},30000);}); </script>");
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
