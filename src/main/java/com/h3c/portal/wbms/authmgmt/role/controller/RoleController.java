package com.h3c.portal.wbms.authmgmt.role.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysact;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.authmgmt.role.service.IRoleService;
import com.h3c.portal.wbms.common.dto.SysroleDTO;
import com.h3c.portal.wbms.smmgmt.opermon.service.IOperMonService;

/***********************************************************************
 *
 * RoleController.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lfw2082<br/>
 * @create-time 2016年1月19日 上午10:08:43
 * @revision    $Id:  *
 ***********************************************************************/
@Controller
@RequestMapping(value="/roleController")
public class RoleController extends ControllerSupport<Object> {
	@Autowired
	IRoleService service;
	@Autowired
	private IOperMonService operMonService;
	
	private static final String PREFIX = "pages/portal/wbms/authmgmt/role/";
	
    @RequestMapping(params="openRole")
	public ModelAndView openRole() throws H3cException {
    	operMonService.operCount("role");
		return new ModelAndView(PREFIX + "Role");
	}
    @RequestMapping(params="showAddRole")
	public ModelAndView showAddRole() throws H3cException {
		return new ModelAndView(PREFIX + "AddRole");
	}
    /**
     * 
     * @param rolename
     * @param roledesc
     * @param owner
     * @param start
     * @param limit
     * @return
     * @throws H3cException
     */
    @RequestMapping(params="search")
    @ResponseBody
    @H3cTransaction
	public AjaxJson search( String rolename, String roledesc, String owner,Integer start,Integer limit)
			throws H3cException {
		if(start==null){
			start = 0;
		}
		return service.getRoleGrid(rolename, roledesc, owner, start, limit);
		
	}
    
	@RequestMapping(params = "deleteRole")
	@ResponseBody
	@H3cTransaction
	public AjaxJson deleteRole(String roleid) throws H3cException {
		AjaxJson json = new AjaxJson();	
		service.deleteRole(roleid);
		return json;
		
	}
	
	/**
	 * 保存
	 * @param dto
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "saveRole")
	@ResponseBody
	@H3cTransaction
	public AjaxJson saveRole(SysroleDTO dto) throws H3cException{
		AjaxJson json = new AjaxJson();
		service.saveRole(dto);
		return json;
	}
	
	/**
	 * 打开修改角色页面
	 * @param dto
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "showModifyRole")
	@ResponseBody
	public ModelAndView openModifyRole(String roleid) throws H3cException{
		ModelAndView mav = new ModelAndView(PREFIX + "ModifyRole");
		mav.addObject("role",service.getSysRole(roleid));
		return mav;
	}

	/**
	 * 判断是否有角色对应的用户或组织
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "hasRoleUsers")
	@ResponseBody
	public boolean hasRoleUsers(String roleid) throws H3cException {
		List<Sysact> sysactList = service.getRoleUsers(roleid);
		if (sysactList.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 打开角色用户列表
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "openRoleUsersList")
	@ResponseBody
	public ModelAndView openRoleUsersList(String roleId) throws H3cException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(PREFIX + "RoleUsersList");
		mav.addObject("roleId", roleId);
		return mav;
	}

	/**
	 * 查询角色对应用户列表
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "queryRoleUsersList")
	@ResponseBody
	public AjaxJson queryRoleUsersList(String roleId,Integer start, Integer limit) throws H3cException {
		AjaxJson json = new AjaxJson();
		json = service.getqueryRoleUsersList(roleId, start, limit);
		return json;
	}
    
}
