package com.h3c.portal.business.taglibmgmt.grid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.business.common.dto.GridDTO;
import com.h3c.portal.business.taglibmgmt.grid.service.IGridService;


/**
 * *********************************************************************
 *
 * GridController.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月18日 下午4:06:46
 * @revision    $Id:  *
 **********************************************************************
 */
@Controller
@RequestMapping(value = "/gridController")
public class GridController extends ControllerSupport<GridDTO>{

	@Autowired
	IGridService service;

	
	@RequestMapping(params = "openGrid")
	public ModelAndView openGrid() {
		return new ModelAndView("pages/portal/business/taglibmgmt/grid/Grid");
	}
	
    /**
     * 分页查询
     * @param aab001
     * @param aab004
     * @param aab030
     * @param start
     * @param limit
     * @return
     * @throws H3cException
     */
	@RequestMapping(params = "query")
	@ResponseBody
	public AjaxJson query(String aab001,String aab004,String aab030,Integer start,Integer limit)
			throws H3cException {
		if(start==null){
			start = 0;
		}
//		service.getOrgInfoByCondition(aab001, aab004, aab030, start, limit);
//		return new AjaxJson();
		return service.getOrgInfoByCondition(aab001, aab004, aab030, start, limit);
	}
	
	/**
	 * 不分页查询
	 * @param aab001
	 * @param aab004
	 * @param aab030
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "query2")
	@ResponseBody
	public AjaxJson query(String aab001,String aab004,String aab030)
			throws H3cException {
		
		// return new AjaxJson();
		return service.getOrgInfoByCondition(aab001, aab004, aab030);
	}
	
	/**
	 * 不分页查询
	 * @param aab001
	 * @param aab004
	 * @param aab030
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	@Override
	public AjaxJson saveWithOpLog(GridDTO dto, String arg1) throws H3cException {
		System.out.println(dto.getZzwData());
		System.out.println(dto.getZzw2Data());
		return new AjaxJson();
	}
	
	
}
