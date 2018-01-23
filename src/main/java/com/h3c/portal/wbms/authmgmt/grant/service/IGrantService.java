package com.h3c.portal.wbms.authmgmt.grant.service;

import java.util.List;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysrole;

/**
 * *********************************************************************
*
* IGrantService.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年1月12日 下午1:59:31
* @revision    $Id:  *
**********************************************************************
 */
public interface IGrantService {
	/* BEGIN: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */	
	/**
	 * 获取登录用户所有授权角色的授权和分授权信息
	 * @param objectid
	 * @return 包含授权和分授权信息的字符串
	 * @throws H3cException
	 */
	public String findRolesGranted(String objectid) throws H3cException;
	/* END: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	
	/**
	 * 获取系统用户
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getSysuser(String loginname,String username,int start,int limit) throws H3cException;

	// TODO 2017-7-28-zhoujie 获取全部角色
	/**
	 * 获取全部角色
	 * 
	 * @return
	 */
	public List<Sysrole> getAllRoles() throws H3cException;

	// -------------------------------------
	/**
	 * 获取系统组织
	 * 
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getGroup() throws H3cException;
	
	/**
	 * 获取用户角色
	 * @param objectid
	 * @param objecttype
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getRoleByCondition(String objectid, String grant, int start, int limit) throws H3cException;
	
	/**
	 * 用户授权
	 * @throws H3cException
	 */
	public void grant(String objectid, String objecttype, String grant, String rolegrid) throws H3cException;

}
