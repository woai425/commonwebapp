package com.h3c.portal.wbms.welmgmt.funsrch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.welmgmt.funsrch.service.IFunSrchService;

/**
 * *********************************************************************
 * 首页面功能模糊搜索功能
 * FunSrchController.java
 * 
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月19日 下午3:47:10
 * @revision    $Id:  *
 **********************************************************************
 */
@Controller
@RequestMapping(value = "/funSrchController")
public class FunSrchController extends ControllerSupport<Object> {

	@Autowired
	IFunSrchService service;
	
	/**
	 * 根据模块名称模糊查询对应的模块
	 * @param funName
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "query")
	@ResponseBody
	public AjaxJson getSysFunction(String funName) throws H3cException{
		AjaxJson json = new AjaxJson();
		json.setData(service.getSysFunction(funName));
		return json;
	}
	
}
