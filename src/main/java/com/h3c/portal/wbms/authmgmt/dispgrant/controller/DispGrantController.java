package com.h3c.portal.wbms.authmgmt.dispgrant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.authmgmt.dispgrant.service.IDispGrantService;

/**
 * *********************************************************************
*
* DispGrantController.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年3月8日 下午3:51:36
* @revision    $Id:  *
**********************************************************************
 */
@Controller
@RequestMapping(value = "/dispGrantController")
public class DispGrantController extends ControllerSupport<Object> {
	private static ObjectMapper mapper = new ObjectMapper();
	@Autowired
	IDispGrantService service;
	
	/**
	 * 打开用户授权页面
	 * @return
	 */
	@RequestMapping(params = "openDispGrant")
	public ModelAndView openGrant() throws H3cException {
		return new ModelAndView("pages/portal/wbms/authmgmt/dispgrant/DispGrant");
	}
	
	/**
	 * 获取系统用户
	 * @param loginname
	 * @param username
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "getSysuser")
	@ResponseBody
	@H3cTransaction
	public AjaxJson getSysuser(String userid, String loginname, String username, Integer start, Integer limit) throws H3cException{
		if (start == null) {
			start = 0;
		}
		return service.getSysuser(userid, loginname, username, start, limit);
		
	}
	
	/**
	 * 获取组织信息
	 * @param groupname
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "getGroup")
	@ResponseBody
	@H3cTransaction
	public AjaxJson getGroup(String userid) throws H3cException{
		AjaxJson json = new AjaxJson();
		json.setData(service.getGroupNumber(userid));
		return json;
		
	}
	
	/**
	 * 打开授权页面
	 * 
	 * @param objectid
	 * @param objecttype
	 * @param grant
	 * @return
	 * @throws H3cException
	 * @throws JsonProcessingException
	 */
	@RequestMapping(params = "showDoGrant")
	public ModelAndView showDoGrant(String userid, String objectid, String objecttype, String grant)
			throws H3cException, JsonProcessingException {
		ModelAndView m = new ModelAndView("pages/portal/wbms/authmgmt/dispgrant/DoDispGrant");
		m.addObject("userid", userid);
		m.addObject("objectid", objectid);
		m.addObject("objecttype", objecttype);
		m.addObject("grant", grant);
		/* BEGIN: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
		String rolesGranted = service.findRolesGranted(objectid,userid);
		m.addObject("rolesGranted", rolesGranted);
		/* END: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */

		// TODO 查询出用户带有分授权的所有角色
		m.addObject("roles", mapper.writeValueAsString(service.getUserAllRole(userid)));
		// ----------------------------------------
		return m;	
	}
	
	/**
	 * 获取用户角色
	 * @param objectid
	 * @param grant
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "getRoleByCondition")
	@ResponseBody
	public AjaxJson getRoleByCondition(String userid, String objectid, String grant, Integer start, Integer limit) throws H3cException{
		if (start == null) {
			start = 0;
		}
		return service.getRoleByCondition(userid, objectid, grant, start, limit);
		
	}
	
	/**
	 * 对应户进行授权
	 * @param objectid
	 * @param objecttype
	 * @param grant
	 * @param rolegrid
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "grantObject")
	@ResponseBody
	@H3cTransaction
	public AjaxJson grant(String objectid, String objecttype, String grant, String rolegrid) throws H3cException{
		AjaxJson json = new AjaxJson();
		service.grant(objectid, objecttype, grant, rolegrid);
		return json;
		
	}
	
	
	/**
	 * 获取分授权角色
	 * @param userid
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(params = "getDispGrant")
	@ResponseBody
	@H3cTransaction
	public AjaxJson getDispGrant(String userid, Integer start, Integer limit) throws H3cException{
		if (start == null) {
			start = 0;
		}
		return service.getDispGrant(userid, start, limit);
		
	}

}
