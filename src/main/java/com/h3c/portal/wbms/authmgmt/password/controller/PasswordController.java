package com.h3c.portal.wbms.authmgmt.password.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.authmgmt.password.service.IPasswordService;
import com.h3c.portal.wbms.authmgmt.user.service.IUserService;
import com.h3c.portal.wbms.common.dto.*;

/**
 * *********************************************************************
*
* PasswordController.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年3月12日 上午10:22:59
* @revision    $Id:  *
**********************************************************************
 */
@Controller
@RequestMapping(value = "/passwordController")
public class PasswordController extends ControllerSupport<Object> {

	@Autowired
	IUserService service;
	
	@Autowired
	IPasswordService service1;
	
	@RequestMapping(params = "openPassword")
	public ModelAndView openPassword(String userid) throws H3cException {
		ModelAndView m = new ModelAndView("/pages/portal/wbms/authmgmt/password/Password");
		m.addObject("user",service.getSysuser(userid));
		m.addObject("groupid", service1.getGroup(userid).getGroupid());
		return m;
	}
	
	@RequestMapping(params = "openPasswordForShow")
	public ModelAndView openPasswordForShow(String userid) throws H3cException {
		ModelAndView m = new ModelAndView("/pages/portal/wbms/authmgmt/password/PasswordForShow");
		m.addObject("user",service.getSysuser(userid));
		m.addObject("groupid", service1.getGroup(userid).getGroupid());
		return m;
	}
	
	@RequestMapping(params = "openUpdatePassword")
	public ModelAndView openUpdatePassword(String userid) throws H3cException {
		ModelAndView m = new ModelAndView("/pages/portal/wbms/authmgmt/password/UpdatePassword");
		m.addObject("user",service.getSysuser(userid));
		m.addObject("groupid", service1.getGroup(userid).getGroupid());
		return m;
	}
	
	@RequestMapping(params = "getGroupInfo")
	@ResponseBody
	@H3cTransaction
	public AjaxJson getGroupInfo() throws H3cException{
		AjaxJson json = new AjaxJson();
		json.setData(service1.getGroupInfo());
		return json;
	}
	
	@RequestMapping(params = "updateUser")
	@ResponseBody
	@H3cTransaction
	public AjaxJson updateUser(SysuserDTO dto) throws H3cException{
		AjaxJson json = new AjaxJson();
		service1.updateUser(dto);
		return json;
		
	}
	
	@RequestMapping(params = "updatePassword")
	@ResponseBody
	@H3cTransaction
	public AjaxJson updatePassword(SysuserDTO dto) throws H3cException{
		AjaxJson json = new AjaxJson();
		service1.updatePassword(dto);
		return json;
		
	}
	

}
