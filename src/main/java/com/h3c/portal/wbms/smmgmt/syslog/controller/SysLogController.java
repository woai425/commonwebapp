package com.h3c.portal.wbms.smmgmt.syslog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.smmgmt.syslog.service.ISysLogService;

/**
 * *********************************************************************
 * 
 * SysLogController.java
 * 系统日志查询
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月23日 下午4:22:03
 * @revision    $Id:  *
 **********************************************************************
 */
@Controller
@RequestMapping(value = "/sysLogController")
public class SysLogController extends ControllerSupport<Object>{
	
	@Autowired
	ISysLogService service;
	
	@RequestMapping(params = "openSysLog")
	public ModelAndView openSysLog() throws H3cException {
		return new ModelAndView("/pages/portal/wbms/smmgmt/syslog/SysLog");
	}
	
	
    /**
     * 分页查询系统日志
     * @return
     * @throws H3cException
     */
	@RequestMapping(params = "query")
	@ResponseBody
	public AjaxJson query(String logPrcol1, String logDigest, Integer logtype,@RequestParam(value="start",defaultValue="0") Integer start,Integer limit)
			throws H3cException {
		if(limit==null){
			limit = 10;
		}
		return service.getSyslogInfoByCondition(logPrcol1,logDigest,logtype,start,limit);
	}
	
}
