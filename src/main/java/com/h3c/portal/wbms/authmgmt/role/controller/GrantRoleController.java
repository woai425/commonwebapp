package com.h3c.portal.wbms.authmgmt.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysfunction;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.authmgmt.role.service.IRoleService;
/**
 * *********************************************************************
*
* GrantRoleController.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年2月1日 下午3:49:46
* @revision    $Id:  *
**********************************************************************
 */
@Controller
@RequestMapping(value = "/grantRoleController")
public class GrantRoleController extends ControllerSupport<Object>{
	@Autowired
	private IRoleService service;
	@RequestMapping(params = "openGrantRole")
	public ModelAndView openGrantRole(String roleid){
		ModelAndView m = new ModelAndView("pages/portal/wbms/authmgmt/role/RoleTree");
		m.addObject("roleid",roleid);
		return m;
	}
	
	/**
	 * 导航栏
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "getTreeMenu")
	@ResponseBody
    public AjaxJson getTreeMenu() throws H3cException {
		AjaxJson json = new AjaxJson();
		List<Sysfunction> menus = service.getTreeMenu();
		json.setData(menus);
    	return json;
    }
	
	@RequestMapping(params = "doGrantRole")
	@ResponseBody
	@H3cTransaction
	public AjaxJson doGrantRole( String roleid, String treeInfo) throws H3cException {
		AjaxJson json = new AjaxJson();
		service.doGrant(roleid, treeInfo);
		return json;
	}
	
	/**
	 * 获取角色信息
	 * @param roleid
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "getRoleTree")
	@ResponseBody
	@H3cTransaction
	public AjaxJson getRoleTree( String roleid) throws H3cException {
		AjaxJson json = new AjaxJson();
		json.setData(service.getRoleTree(roleid));
		return json;
	}
	
}
