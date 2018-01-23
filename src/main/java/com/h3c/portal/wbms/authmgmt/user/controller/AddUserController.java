package com.h3c.portal.wbms.authmgmt.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.portal.wbms.common.dto.SysuserDTO;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.authmgmt.user.service.IUserService;

/**
 * *********************************************************************
*
* AddUserController.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年1月22日 上午9:08:25
* @revision    $Id:  *
**********************************************************************
 */
@Controller
@RequestMapping(value = "/addUserController")
public class AddUserController extends ControllerSupport<Object>{
	
	@Autowired
	IUserService service;
	
	/**
	 * 打开新增用户界面
	 * @return
	 */
	@RequestMapping(params = "openAddUser")
	@ResponseBody
	public ModelAndView openAddExistUser(String groupid){
		ModelAndView m = new ModelAndView("/pages/portal/wbms/authmgmt/user/AddUser");
		m.addObject("groupid", groupid);
		return m;
	}
	
	/**
	 * 保存用户
	 * @param dto
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "addUser")
	@ResponseBody
	@H3cTransaction
	public AjaxJson addUser(SysuserDTO dto) throws H3cException{
		AjaxJson json = new AjaxJson();
		try {
			service.addUser(dto);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	

}
