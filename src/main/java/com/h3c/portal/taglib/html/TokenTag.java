package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.h3c.portal.taglib.util.TagUtil;

/**
 * 令牌标签
 * @author z10926
 *
 */
public class TokenTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {	  
		StringBuilder buffer = new StringBuilder(100);
		TagUtil.saveToken((HttpServletRequest)this.pageContext.getRequest());		
    	buffer.append("<input type=\"hidden\" id=\"org.apache.struts.taglib.html.TOKEN\" name=\"org.apache.struts.taglib.html.TOKEN\" value=\""+((HttpServletRequest)this.pageContext.getRequest()).getSession().getAttribute(TagUtil.TRANSACTION_TOKEN_KEY)+"\"/>");
        try {      
            JspWriter out = pageContext.getOut();      
            out.println(buffer.toString());      
        } catch (IOException e) {      
            e.printStackTrace();      
        } 
        return super.doStartTag();
	}
}
