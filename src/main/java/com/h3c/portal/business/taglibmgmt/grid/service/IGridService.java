package com.h3c.portal.business.taglibmgmt.grid.service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;

/**
 * *********************************************************************
 *
 * IGridService.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月18日 下午4:50:23
 * @revision    $Id:  *
 **********************************************************************
 */
public interface IGridService {
	
	public AjaxJson getOrgInfoByCondition(String aab001,String aab004,String aab030,int start, int limit) throws H3cException;
	
	
	public AjaxJson getOrgInfoByCondition(String aab001,String aab004,String aab030) throws H3cException;

}
