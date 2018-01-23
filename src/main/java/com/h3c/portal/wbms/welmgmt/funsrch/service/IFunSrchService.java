package com.h3c.portal.wbms.welmgmt.funsrch.service;

import java.util.List;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.entities.Sysfunction;

/**
 * *********************************************************************
 * 
 * IFunSrchService.java
 * 首页面功能模糊搜索接口
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月19日 下午3:47:16
 * @revision    $Id:  *
 **********************************************************************
 */
public interface IFunSrchService {

	/**
	 * 根据模块名查询所有的功能模块
	 * @param funName
	 * @return
	 * @throws H3cException
	 */
	public List<Sysfunction> getSysFunction(String funName) throws H3cException;
}
