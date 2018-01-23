package com.h3c.portal.wbms.prmmgmt.ddprm.service;



import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.portal.wbms.common.dto.SyscodeDTO;


/***********************************************************************
 *
 * IDdprmService.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lFW2082<br/>
 * @create-time 2016年1月7日 上午11:29:12
 * @revision    $Id:  *
 ***********************************************************************/

public interface IDdprmService {

	/**
	 * 根据条件获取数据字典信息
	 * @param code
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getDdprmGrid(String code,Integer start, Integer limit) throws H3cException ;
	
	/**
	 * 根据条件获取代码和代码名信息
	 * @param code
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getDistinctDdprmGrid(String code,String distinct,Integer start, Integer limit) throws H3cException;

	/**
	 * @param codeid
	 * @throws H3cException
	 */
	public void deleteDdprm(Integer codeid) throws H3cException;
	
	public void saveDdprm(SyscodeDTO dto) throws H3cException;
	
	public void updateDdprm(SyscodeDTO dto) throws H3cException;  

}
