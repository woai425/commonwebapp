package com.h3c.portal.wbms.authmgmt.role.service;


import java.util.List;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysact;
import com.h3c.framework.common.entities.Sysfunction;
import com.h3c.framework.common.entities.Sysrole;
import com.h3c.framework.common.entities.Sysroleacl;
import com.h3c.portal.wbms.common.dto.SysroleDTO;

/***********************************************************************
 *
 * RoleService.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lfw2082<br/>
 * @create-time 2016年1月19日 上午10:09:03
 * @revision    $Id:  *
 ***********************************************************************/

public interface IRoleService {
	public AjaxJson getRoleGrid( String rolename,String roledesc,String owner, Integer start, Integer limit)
			throws H3cException;
	
	public void deleteRole(String roleid) throws H3cException;
	
	public void saveRole(SysroleDTO  dto) throws H3cException;
	
	public void doGrant(String roleid,String treeInfo) throws H3cException;
	
	/**
	 * 删除某个角色
	 * @param roleId
	 * @throws H3cException
	 */
	public void delete(String roleId) throws H3cException;
	
	/**
	 * 获取导航栏菜单
	 * @return
	 */
	public List<Sysfunction> getTreeMenu();
	
	/**
	 * 获取角色树
	 * @return
	 * @throws H3cException
	 */
	public List<Sysroleacl> getRoleTree(String roleid) throws H3cException;
	
	/**
	 * 获取用户角色
	 * @param roleid
	 * @return
	 * @throws H3cException
	 */
	public Sysrole getSysRole(String roleid) throws H3cException;

	/**
	 * 获取角色下组织或用户关联对象
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	public List<Sysact> getRoleUsers(String roleId);

	/**
	 * 分页查询角色对应用户列表
	 * 
	 * @param roleId
	 *            角色id
	 * @param start
	 *            分页查询起始位置
	 * @param limit
	 *            每页查询个记录条数
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getqueryRoleUsersList(String roleId, Integer start, Integer limit) throws H3cException;
	/**
	 * 获取用户名
	 * @return
	 * @throws H3cException
	 */
	public List<String> getRoleNames() throws H3cException;
}
