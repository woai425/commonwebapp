package com.h3c.portal.wbms.authmgmt.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.authmgmt.user.service.IUserService;

/**
 * *********************************************************************
*
* AddExistUserController.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年1月21日 下午3:44:39
* @revision    $Id:  *
**********************************************************************
 */
@Controller
@RequestMapping(value = "/addExistUserController")
public class AddExistUserController extends ControllerSupport<Object>{
	
	@Autowired
	IUserService service;
	
	/**
	 * 打开添加用户界面
	 * @return
	 */
	@RequestMapping(params = "openAddExistUser")
	@ResponseBody
	public ModelAndView openAddExistUser(String groupid){
		ModelAndView m = new ModelAndView("/pages/portal/wbms/authmgmt/user/AddExistUser");
		m.addObject("groupid", groupid);
		return m;
	}
	
	/**
	 * 获取用户
	 * @param loginname
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "getAllUser")
	@ResponseBody
	public AjaxJson getAllUser(String groupid,String loginname,Integer start,Integer limit) throws H3cException {
		if (start == null) {
			start = 0;
		}
		return service.getAllUser(groupid, loginname, start, limit);
	}
	
	/**
	 * 添加用户到组
	 * @param userData
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "addUserToGroup")
	@ResponseBody
	@H3cTransaction
	public AjaxJson addUserToGroup(String userData,String groupid) throws H3cException {
		AjaxJson json = new AjaxJson();
		service.addUserToGroup(userData, groupid);
		return json;
	}
	

}
