package com.h3c.portal.wbms.authmgmt.user.service;

import java.util.List;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysgroup;
import com.h3c.framework.common.entities.Sysuser;
import com.h3c.framework.common.entities.Sysusergroupref;
import com.h3c.portal.wbms.common.dto.*;


/***********************************************************************
*
* UserService.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     lfw2082<br/>
* @create-time 2016年1月15日 上午11:08:27
* @revision    $Id:  *
***********************************************************************/

public interface IUserService {
	
	/**
	 * 获取主菜单
	 * @return
	 * @throws H3cException
	 */
	public List<SysgroupDTO> getMenuBack() throws H3cException;
	
	/**
	 * 获取组织信息
	 * @param groupid
	 * @return
	 * @throws H3cException
	 */
	public Sysgroup getGroupInfo(String groupid) throws H3cException;
	
	/**
	 * 更新用户信息
	 * @param dto
	 * @throws H3cException
	 */
	public void updateUser(SysuserDTO dto) throws H3cException;
	
	/**
	 * 查询组织下面的用户
	 * @param groupid
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getGroupUser(String groupid, String loginname, String username, String usertype, int start,int limit) throws H3cException;
	
	/**
	 * 根据userid获取用户
	 * @param userid
	 * @return
	 * @throws H3cException
	 */
	public Sysuser getSysuser(String userid) throws H3cException;
	
	/**
	 * 根据userid删除用户
	 * @param userid
	 * @throws H3cException
	 */
	public void deleteUser(String userid,String groupid) throws H3cException;
	
	/**
	 * 增加用户
	 * @param dto
	 * @return
	 * @throws H3cException
	 */
	public String addUser(SysuserDTO dto) throws H3cException;
	
	/**
	 * 获取所有用户信息
	 * @param loginname
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getAllUser(String groupid, String loginname,int start,int limit) throws H3cException;
	
	/**
	 * 添加用户到组
	 * @param userData
	 * @throws H3cException
	 */
	public void addUserToGroup(String userData,String groupid) throws H3cException;
	
	/**
	 * 获取用户组
	 * @param userid
	 * @return
	 * @throws H3cException
	 */
	public Sysusergroupref getSysusergroupref(String userid) throws H3cException;
	/**
	 * 为用户解锁
	 * @param userid
	 * @throws H3cException
	 */
	public void unlockUser(String userid)throws H3cException;
	/**
	 * 为用户加锁
	 * @param userid
	 * @throws H3cException
	 */
	public void lockUser(String userid)throws H3cException;

}