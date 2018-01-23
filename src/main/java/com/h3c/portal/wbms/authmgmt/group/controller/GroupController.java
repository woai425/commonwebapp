package com.h3c.portal.wbms.authmgmt.group.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysgroup;
import com.h3c.framework.common.entities.Sysuser;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.framework.util.CopyIgnoreProperty;
import com.h3c.portal.wbms.authmgmt.group.service.IGroupService;
import com.h3c.portal.wbms.authmgmt.user.service.IUserService;
import com.h3c.portal.wbms.common.dto.SysgroupDTO;
import com.h3c.portal.wbms.smmgmt.opermon.service.IOperMonService;

/**
 * *********************************************************************
*
* GroupController.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     cfw2081<br/>
* @create-time 2016年1月21日 下午3:29:33
* @revision    $Id:  *
**********************************************************************
 */
@Controller
@RequestMapping(value="/groupController")
public class GroupController extends ControllerSupport<Object>{
	@Autowired
	private IGroupService service;
	
	@Autowired
	private IOperMonService operMonService;
	
	@Autowired
	private IUserService userService;

    @RequestMapping(params="openGroup")
	public ModelAndView openGroup() throws H3cException {
    	operMonService.operCount("group");
		return new ModelAndView("pages/portal/wbms/authmgmt/group/Group");
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
	
	@RequestMapping(params = "saveGroup")
	@ResponseBody
	@H3cTransaction
	public AjaxJson saveGroup(SysgroupDTO dto) throws H3cException {
		AjaxJson json = new AjaxJson();
		service.saveGroup(dto);
		return json;
	}
	
	@RequestMapping(params = "deleteGroup")
	@ResponseBody
	@H3cTransaction
	public AjaxJson deleteGroup(String groupid) throws H3cException {
		AjaxJson json = new AjaxJson();
		service.deleteGroup(groupid);
		return json;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "hasGroupUser")
	@ResponseBody
	public boolean hasGroupUser(String groupid) throws H3cException {
		// 因为这里只是验证组织下是否有用户，所以可以将分页查询写死
		AjaxJson groupUser = userService.getGroupUser(groupid, null, null, null, 0, 10);
		List<Sysgroup> sysgroupList = service.getChildrenGroupList(groupid);
		List<Sysuser> sysuserList = (List<Sysuser>) groupUser.getData();
		if ((sysgroupList.size() > 0) || (sysuserList.size() > 0)) {
			return true;
		}
		return false;
	}

}
