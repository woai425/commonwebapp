package com.h3c.portal.wbms.authmgmt.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.common.dto.SysuserDTO;
import com.h3c.framework.util.CopyIgnoreProperty;
import com.h3c.portal.wbms.authmgmt.user.service.IUserService;
import com.h3c.portal.wbms.common.dto.SysgroupDTO;
import com.h3c.portal.wbms.smmgmt.opermon.service.IOperMonService;

/***********************************************************************
 *
 * UserController.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lfw2082<br/>
 * @create-time 2016年1月15日 上午11:07:44
 * @revision    $Id:  *
 ***********************************************************************/
@Controller
@RequestMapping(value = "/userController")
public class UserController extends ControllerSupport<Object>{
	@Autowired
	private IUserService service;
	@Autowired
	private IOperMonService operMonService;
	
	@RequestMapping(params = "openUser")
	public ModelAndView openUser() throws H3cException {
		operMonService.operCount("user");
		return new ModelAndView("/pages/portal/wbms/authmgmt/user/User");
	}
	
	@RequestMapping(params = "findGroup")
	@ResponseBody
    public AjaxJson findGroup() throws H3cException {
		AjaxJson json = new AjaxJson();
		List<SysgroupDTO> menus = service.getMenuBack();
		json.setData(menus);
    	return json;
    }
	
	/**
	 * 获取组织信息
	 * @param groupid
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "getGroupInfo")
	@ResponseBody
	public AjaxJson getGroupInfo(String groupid) throws H3cException {
		AjaxJson json = new AjaxJson();
		SysgroupDTO dto = new SysgroupDTO();
		CopyIgnoreProperty.copy(service.getGroupInfo(groupid), dto);
		json.setData(dto);
		return json;
	}
	
	/**
	 * 获取组织的用户
	 * @param groupid
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "getGroupUser")
	@ResponseBody
	public AjaxJson getGroupUser(String groupid, String loginname, String username, String usertype, Integer start,Integer limit) throws H3cException {
		if (start == null) {
			start = 0;
		}
		return service.getGroupUser(groupid, loginname, username, usertype, start, limit);
	}	
	 
	/**
	 * 打开用户修改页面
	 * @param loginname
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "openUpdateUser")
	@ResponseBody
	public ModelAndView openUpdateUser(String userid,String groupid) throws H3cException {
		ModelAndView m = new ModelAndView("/pages/portal/wbms/authmgmt/user/UpdateUser");
		m.addObject("user", service.getSysuser(userid));
		m.addObject("groupid", groupid);
		return m;
	}
	
	/**
	 * 更新用户信息
	 * @param dto
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "updateUser")
	@ResponseBody
	@H3cTransaction
	public AjaxJson updateUser(SysuserDTO dto) throws H3cException{
		AjaxJson json = new AjaxJson();
		service.updateUser(dto);
		return json;
	}
	
	/**
	 * 删除用户
	 * @param userid
	 * @return
	 */
	@RequestMapping(params = "deleteUser")
	@ResponseBody
	@H3cTransaction
	public AjaxJson deleteUser(String userid,String groupid) throws H3cException{
		AjaxJson json = new AjaxJson();
		service.deleteUser(userid,groupid);
		return json;
	}
	
	/**
	 * 解锁用户
	 * @param dto
	 * @return
	 */
	@RequestMapping(params = "unlockUser")
	@ResponseBody
	@H3cTransaction
	public AjaxJson unlockUser(SysuserDTO dto) throws H3cException{
		AjaxJson json = new AjaxJson();
		service.unlockUser(dto.getUserid());
		return json;
	}
	/**
	 * 解锁用户
	 * @param dto
	 * @return
	 */
	@RequestMapping(params = "lockUser")
	@ResponseBody
	@H3cTransaction
	public AjaxJson lockUser(SysuserDTO dto) throws H3cException{
		AjaxJson json = new AjaxJson();
		service.lockUser(dto.getUserid());
		return json;
	}
}
