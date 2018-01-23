package com.h3c.portal.wbms.smmgmt.opermon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.smmgmt.opermon.service.IOperMonService;

/**
 * *********************************************************************
 * 系统模块操作监控
 * OperMonController.java
 * 此模块没有对应的界面，都是后台处理
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年2月23日 下午9:22:00
 * @revision    $Id:  *
 **********************************************************************
 */
@Controller
@RequestMapping(value = "/operMonController")
public class OperMonController extends ControllerSupport<Object>{
	@Autowired
	private IOperMonService service;
	
    /**
     * 分页查询系统日志
     * @return
     * @throws H3cException
     */
	@RequestMapping(params = "monitorQuery")
	@ResponseBody
	public AjaxJson monitorQuery() throws H3cException {
		AjaxJson j = new AjaxJson();
		j.setData(service.getOperMonInfo());
		return j;
	}
}
