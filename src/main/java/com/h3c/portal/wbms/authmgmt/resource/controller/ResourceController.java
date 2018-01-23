package com.h3c.portal.wbms.authmgmt.resource.controller;

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
import com.h3c.portal.wbms.authmgmt.resource.service.IResourceService;
import com.h3c.portal.wbms.common.dto.*;
import com.h3c.portal.wbms.smmgmt.opermon.service.IOperMonService;
/**
 * *********************************************************************
*
* ResourceController.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年1月6日 下午5:44:26
* @revision    $Id:  1.0
**********************************************************************
 */
@Controller
@RequestMapping(value = "/resourceController")
public class ResourceController extends ControllerSupport<Object> {
	
	@Autowired
	IResourceService service;
	@Autowired
	private IOperMonService operMonService;
	/**
	 * 打开权限列表函数
	 * @return
	 */
	@RequestMapping(params = "openResource")
	public ModelAndView openResource() throws H3cException{
		operMonService.operCount("resource");
		return new ModelAndView("pages/portal/wbms/authmgmt/resource/Resource");
	}
	
	/**
	 * 导航栏
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "findBack")
	@ResponseBody
    public AjaxJson findBack() throws H3cException {
		AjaxJson json = new AjaxJson();
		List<Sysfunction> menus = service.getMenuBack();
		json.setData(menus);
    	return json;
    }
	
	/**
	 * 获取节点信息
	 * @param functionid
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "getNodeInfo")
	@ResponseBody
    public AjaxJson getNodeInfo(String functionid) throws H3cException {
		AjaxJson json = new AjaxJson();
		SysfunctionDTO node = service.getNodeInfo(functionid);
		json.setData(node);
    	return json;
    }
	
	/**
	 * 保存节点
	 * @param dto
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "saveNode")
	@ResponseBody
	@H3cTransaction
	public AjaxJson saveNode(SysfunctionDTO dto) throws H3cException {
		AjaxJson json = new AjaxJson();
		service.saveNode(dto);
		return json;
	}
	
	/**
	 * 删除节点
	 * @param functionid
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "deleteNode")
	@ResponseBody
	@H3cTransaction
	public AjaxJson deleteNode(String functionid) throws H3cException {
		AjaxJson json = new AjaxJson();
		service.deleteNode(functionid);
		return json;
	}
}
