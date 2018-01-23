package com.h3c.portal.wbms.authmgmt.group.service;

import java.util.List;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.entities.Sysgroup;
import com.h3c.portal.wbms.common.dto.SysgroupDTO;
/**
 **********************************************************************
*
* IGroupService.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     cfw2081<br/>
* @create-time 2016年1月21日 下午3:38:46
* @revision    $Id:  *
**********************************************************************
 */

public interface IGroupService {
	
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
	 * 保存节点信息
	 * @param dto
	 * @throws H3cException
	 */
	public void saveGroup(SysgroupDTO dto) throws H3cException;
	
	/**
	 * 删除节点信息
	 * @param groupid
	 * @throws H3cException
	 */
	public void deleteGroup(String groupid) throws H3cException;
	
	/**
	 * 递归获取子组织
	 * @param group
	 * @throws H3cException
	 */
	public List<Sysgroup> getChildrenGroup(List<Sysgroup> group, List<Sysgroup> listAll) throws H3cException;

	/**
	 * 获取子组织列表
	 * 
	 * @param groupid
	 *            父组织id
	 * @return
	 */
	public List<Sysgroup> getChildrenGroupList(String groupid);
	
}
