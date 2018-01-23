package com.h3c.portal.business.common.customlogin.service.serviceimpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.web.security.auth.service.ICustomLoginService;

/**
 * *********************************************************************
 * 自定义登录业务逻辑实现类，目的：在本地校验失败后可以继续实现自定义的登录校验，例如Rest Api校验
 * CustomLoginServiceImpl.java
 * 
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月23日 上午10:51:51
 * @revision    $Id:  *
 **********************************************************************
 */
@Service
public class CustomLoginServiceImpl extends ServiceSupport implements ICustomLoginService{

	@Override
	public AjaxJson customLogin(String loginname,String password, HttpServletRequest req) throws H3cException {
		AjaxJson json = new AjaxJson();	
		json.setSuccess(false);
		/**
		 * 1.根据实际情况调用第三方人员信息认证功能
		 * 2.认证成功后，需要返回一个sysuser的对象，json.setData(sysuser);
		 * 3.如果错误需要返回，可以json.setSuccess(false);json.setMsg(msg);
		 */
		return json;
	}





}
