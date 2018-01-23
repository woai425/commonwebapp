package com.h3c.portal.business.taglibmgmt.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.business.taglibmgmt.menu.service.MenuService;

/***********************************************************************
 *
 * MenuController.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lfw2082<br/>
 * @create-time 2016年2月3日 上午9:24:16
 * @revision    $Id:  *
 ***********************************************************************/
@Controller
@RequestMapping(value="/menuController")
public class MenuController extends ControllerSupport<Object> {
	@Autowired
	MenuService service;
	@RequestMapping(params="openMenu")
	public ModelAndView openMenu(){
		return new ModelAndView("pages/portal/business/taglibmgmt/menu/MenuTag");
		
	}

}
