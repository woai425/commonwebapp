package com.h3c.portal.restfulbiz.server.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h3c.framework.H3cException;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.framework.exception.UserException;
import com.h3c.framework.web.security.auth.service.ISSOLoginService;

/**
 * *********************************************************************
 * 单点登录Controller类，作用是对外提供单点登录到Portal的TOKEN <br/>
 * H3cSSOController.java <br/>
 *
 * H3C所有，<br/>
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。<br/>
 * 
 * @copyright Copyright: 2015-2020
 * @creator z10926 <br/>
 * @create-time 2016年10月26日 上午11:10:45
 * @revision $Id: *
 **********************************************************************
 */
@Controller
public class H3cSSOController extends ControllerSupport<Object> {

	@Autowired
	private ISSOLoginService service;

	/**
	 * 向外提供单点登录所需的TOKEN
	 * 
	 * @param req
	 * @param loginName
	 * @return
	 * @throws UserException
	 * @throws H3cException
	 */
	@RequestMapping(value = "/sso/token/{loginname}", method = RequestMethod.GET)
	@ResponseBody
	public String getPortalToken(HttpServletRequest req, @PathVariable(value = "loginname") String loginName)
			throws UserException, H3cException {
		return service.getToken(loginName);
	}

}
