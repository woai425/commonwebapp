package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.MDParamDTO;
import com.h3c.framework.common.entities.Sysfunction;
import com.h3c.framework.core.persistence.SysfunctionManager;

/**
 * 标签头
 * @author z10926
 * @version 创建时间：2015年12月1日 下午13:54:46
 */
public class HeadTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {	  
		StringBuilder buffer = new StringBuilder(1800);
		String contextPath = ((HttpServletRequest)this.pageContext.getRequest()).getContextPath();	
		buffer.append("<meta content=\"IE=edge,chrome=1\" http-equiv=\"X-UA-Compatible\">");
		buffer.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		buffer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/layout/lib/bootstrap/css/bootstrap.css\"/>");
		buffer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/layout/lib/bootstrap/css/bootstrap-datetimepicker.min.css\"/>");
		buffer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/layout/stylesheets/theme.css\"/>");
		buffer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/layout/lib/font-awesome/css/font-awesome.css\"/>");
		buffer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/layout/css/html/html.css\"/>");
		buffer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/layout/css/grid/grid.css\"/>");
        buffer.append("<script>var contextPath=\""+contextPath+"\";</script>");
        buffer.append("<script src=\""+contextPath+"/layout/lib/jquery/jquery-1.8.3.js\"></script>");
        buffer.append("<script src=\""+contextPath+"/layout/lib/bootstrap/js/bootstrap.js\"></script>");
        buffer.append("<script src=\""+contextPath+"/basejs/h3c.js\"></script>");
        buffer.append("<script src=\""+contextPath+"/basejs/zDrag.js\"></script>");
        buffer.append("<script src=\""+contextPath+"/basejs/zDialog.js\"></script>");
        buffer.append("<script src=\""+contextPath+"/layout/lib/bootstrap/js/bootstrap-datetimepicker.min.js\" charset=\"UTF-8\"></script>");
        buffer.append("<script src=\""+contextPath+"/layout/lib/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js\" charset=\"UTF-8\"></script>");
        buffer.append("<script type=\"text/javascript\"> $(document).ready(function(){ document.onkeydown=function(evt){ if(evt.keyCode == 13){ if (document.activeElement.type == \"textarea\") { return true; } else { return false; } } ");
        buffer.append("if (evt.keyCode == 8) { if (document.activeElement.type == \"text\" || document.activeElement.type == \"textarea\" || document.activeElement.type == \"password\") { if (document.activeElement.readOnly == false) return true; } return false; }");
        buffer.append("}}); </script>");
        //获取模块参数
		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		SysfunctionManager manager = (SysfunctionManager)ctx.getBean("sysfunctionManager");
		Sysfunction moduleSysfunction = null;
		try {
			moduleSysfunction = manager.getModuleSysfunction();
		} catch (H3cException e) {
			e.printStackTrace();
		}
		StringBuilder sb = null;
		if(moduleSysfunction!=null){
			MDParamDTO mdp=new MDParamDTO();
			mdp.setFunctionid(moduleSysfunction.getFunctionid());
			mdp.setTitle(moduleSysfunction.getTitle());
			mdp.setAuflag(moduleSysfunction.getAuflag());
			mdp.setRpflag(moduleSysfunction.getRpflag());
			mdp.setUptype(moduleSysfunction.getUptype());
			mdp.setPublicflag(moduleSysfunction.getPublicflag());
			mdp.setPrsource(moduleSysfunction.getPrsource());
			mdp.setRbflag(moduleSysfunction.getRbflag());
			mdp.setParam1(moduleSysfunction.getParam1());
			mdp.setParam2(moduleSysfunction.getParam2()); 
			mdp.setLocation(moduleSysfunction.getLocation());
			mdp.setLog(moduleSysfunction.getLog());
			try {
				mdp.setButtonid(manager.getButtonIdByFunctionId(moduleSysfunction.getFunctionid()));
			} catch (H3cException e) {
				e.printStackTrace();
			}
			
			ObjectMapper mapper = new ObjectMapper();
		    String jsonobject =null;
			try {
				jsonobject = mapper.writeValueAsString(mdp);
			} catch (Exception e) {
				e.printStackTrace();
				throw new JspException(e.getMessage());
			}
			sb = new StringBuilder(350);
			sb.append("<script language=\"JavaScript\">");
			sb.append("var MDParam="+jsonobject);
			sb.append(";</script>\n");
		}else{
			sb = new StringBuilder("<script language=\"JavaScript\">var MDParam={};</script>\n");
		}
        try {      
            JspWriter out = pageContext.getOut(); 
            out.print(sb.toString());   
            out.println(buffer.toString());      
        } catch (IOException e) {      
            throw new JspException(e.getMessage());      
        } 
        return super.doStartTag();
	}

}
