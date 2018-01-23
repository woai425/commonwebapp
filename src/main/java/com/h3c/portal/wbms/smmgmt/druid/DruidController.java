package com.h3c.portal.wbms.smmgmt.druid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.portal.wbms.smmgmt.opermon.service.IOperMonService;

/**
 * *********************************************************************
 * 数据监控
 * DruidController.java
 * 
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月25日 下午6:01:42
 * @revision    $Id:  *
 **********************************************************************
 */
@Controller
@RequestMapping("/druidController")
public class DruidController {
	
	@Autowired
	private IOperMonService operMonService;

	/**
	 * 跳转到连接池监控页面
	 * 
	 * @return
	 * @throws H3cException 
	 */
	@RequestMapping(params = "goDruid")
	public ModelAndView goDruid() throws H3cException {
		operMonService.operCount("monitor");
		return new ModelAndView("pages/portal/wbms/smmgmt/druid/Druid");
	}
}
