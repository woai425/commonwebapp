package com.h3c.portal.wbms.prmmgmt.sysprm.service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.portal.wbms.common.dto.SysparamDTO;


/**
 * *********************************************************************
*
* ISysprmService.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     admil<br/>
* @create-time 2016年1月9日 下午4:00:55
* @revision    $Id:  *
**********************************************************************
 */
public interface ISysprmService {
	/**
	 * 分页查询
	 * @return
	 */
	public AjaxJson getSysprmGrid(String paramcode,String paramname,String maintstate, Integer start, Integer limit) throws H3cException;

	public void saveSysprm(SysparamDTO dto) throws H3cException;

	public void deleteSysprm(String paramcode);

	public void updateSysprm(SysparamDTO dto);

	



	
}
