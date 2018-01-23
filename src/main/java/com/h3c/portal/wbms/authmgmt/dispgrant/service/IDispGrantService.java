package com.h3c.portal.wbms.authmgmt.dispgrant.service;

import java.util.List;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysrole;
import com.h3c.portal.wbms.common.dto.SysgroupDTO;

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
public interface IDispGrantService {
	
	/* BEGIN: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	/**
	 * 在当前用户已有的分授权角色的基础上，得到被授权用户的授权和分授权角色信息
	 * @param objectid:被授权用户的id
	 * @param userid：登录用户（授权用户）的id
	 * @return
	 * @throws H3cException
	 */
	public String findRolesGranted(String objectid,String userid) throws H3cException;
	/* END: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	
	/**
	 * 获取系统用户
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getSysuser(String userid, String loginname,String username,int start,int limit) throws H3cException;
	

	/**
	 * 获取系统组织
	 * @return
	 * @throws H3cException
	 */
	public List<SysgroupDTO> getGroup() throws H3cException;
	
	/**
	 * 获取用户角色
	 * @param objectid
	 * @param objecttype
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getRoleByCondition(String userid, String objectid, String grant, int start, int limit) throws H3cException;

	// TODO 2017-8-2-zhoujie 获取用户的全部角色（有分授权的角色）
	/**
	 * 获取用户的全部角色（有分授权的角色）
	 * 
	 * @param userid
	 * @return
	 */
	public List<Sysrole> getUserAllRole(String userid) throws H3cException;
	// -----------------------------------------------------------

	/**
	 * 用户授权
	 * @throws H3cException
	 */
	public void grant(String objectid, String objecttype, String grant, String rolegrid) throws H3cException;
	
	/**
	 * 获取分授权信息
	 * @param userid
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getDispGrant(String userid, Integer start, Integer limit) throws H3cException;
	
	/**
	 * 获取组织号码
	 * @param userid
	 * @return
	 * @throws H3cException
	 */
	public List<SysgroupDTO> getGroupNumber(String userid) throws H3cException;
	
	/**
	 * 获取子组织
	 * @param userid
	 * @return
	 * @throws H3cException
	 */
	public List<SysgroupDTO> getChildGroup(String userid) throws H3cException;

}
