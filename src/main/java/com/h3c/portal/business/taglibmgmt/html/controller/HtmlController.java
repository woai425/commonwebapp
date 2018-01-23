package com.h3c.portal.business.taglibmgmt.html.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.business.taglibmgmt.html.service.HtmlService;

/***********************************************************************
 *
 * HtmlController.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lfw2082<br/>
 * @create-time 2016年2月2日 上午10:40:53
 * @revision    $Id:  *
 ***********************************************************************/
@Controller
@RequestMapping(value = "/htmlController")
public class HtmlController extends ControllerSupport<Object>{
	@Autowired
   HtmlService service;
	@RequestMapping(params = "openHtml")
	public ModelAndView openHtml(){
		return new ModelAndView("pages/portal/business/taglibmgmt/html/HtmlTag");
	}
	
}
