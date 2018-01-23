package com.h3c.portal.business.taglibmgmt.tab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.business.taglibmgmt.tab.service.TabService;

/***********************************************************************
 *
 * TabController.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lfw2082<br/>
 * @create-time 2016年2月3日 上午9:47:13
 * @revision    $Id:  *
 ***********************************************************************/
@Controller
@RequestMapping(value="/tabController")
public class TabController extends ControllerSupport<Object>{
	@Autowired
	TabService service;
	@RequestMapping(params="openTab")
	public ModelAndView openTab(){
		return new ModelAndView("pages/portal/business/taglibmgmt/tab/TabTag");
		
	}

}
