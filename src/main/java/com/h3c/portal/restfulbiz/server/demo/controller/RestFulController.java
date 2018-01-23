package com.h3c.portal.restfulbiz.server.demo.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h3c.framework.util.GlobalNames;

/**
 * *********************************************************************
 *
 * RestFulController.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月28日 下午2:08:05
 * @revision    $Id:  *
 **********************************************************************
 */
@Controller
public class RestFulController {
	
	/**
	 * 获取定制系统的验证码和sessionId
	 * @return
	 */
	@RequestMapping(value = "/getCookie", method = RequestMethod.GET)
	@ResponseBody
	public String getHzauCookie(HttpSession session,@RequestHeader("User-Agent") String header) {
		GlobalNames.log.info("定制系统sessionid:"+session.getId()+".... 目标浏览器版本："+header);
		return "receive({\"sessionId\":\""+session.getId()+"\",\"randCsmCode\":\""+new Date()+"\",\"brower\":\""+"目标浏览器版本："+header+"\"})";
	}
	
}
