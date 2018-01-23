package com.h3c.portal.wbms.authmgmt.role.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.entities.Sysact;
import com.h3c.framework.common.entities.Sysrole;
import com.h3c.framework.common.entities.Sysroleacl;
import com.h3c.framework.core.dao.H3cSession;
import com.h3c.framework.core.dao.HBUtil;
import com.h3c.framework.util.HashCodeUtil;


/***********************************************************************
 *
 * RoleHelper.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lFW2082<br/>
 * @create-time 2016年1月27日 上午9:54:19
 * @revision    $Id:  *
 ***********************************************************************/

public class RoleHelper {
	private RoleHelper(){}

	/**
	 * 角色的无效状态
	 */
	public static final int ROLE_UN_USEFUL = 0;
	/**
	 * 角色的有效状态
	 */
	public static final int ROLE_USEFUL = 1;
	/**
	 * 角色不存在状态
	 */
	public static final int ROLE_NOT_EXIST = -1; 
	
	/**
	 * 角色有效
	 */
	public static final String USEFUL = "1";
	/**
	 * 角色无效
	 */
	public static final String UN_USEFUL = "0";
	
	private static class SingletonHolder{
		 public final static RoleHelper instance = new RoleHelper();   
	}
	
	public static RoleHelper getInstance(){
		return SingletonHolder.instance;
	}
	
	
	@SuppressWarnings("rawtypes")
	public Map parseRoleTree(String treeinfo){
		String[] nodes = null;
		HashMap<String,String> nodemap = new HashMap<String,String>();
		if(treeinfo !=null) {
			nodes = treeinfo.split(",");
			for(int i=0;i<nodes.length;i++) {
				nodemap.put(nodes[i].split(":")[0], nodes[i].split(":")[1]);
			}
		}
		StringBuffer addresourceIds = new StringBuffer();
		StringBuffer removeresourceIds = new StringBuffer();
		for(String node :nodemap.keySet()) {
			if(nodemap.get(node).equals("true")) {
				addresourceIds.append(node+",");
			}else if(nodemap.get(node).equals("false")) {
				removeresourceIds.append(node+",");
			}
		}
		Map<String, String> p = new HashMap<String, String>();
        p.put("add", addresourceIds.toString());
        p.put("remove", removeresourceIds.toString());
		return p;
	}
	
	/**
	 * 给角色授权。建立角色与资源的关联
	 * @param roleId
	 *            授权角色的ID
	 * @param resourceIds
	 *            资源ID串 多个functionid用逗号隔开
	 * @return 成功返回true
	 * @throws H3cException
	 */
	public void addResousesToRole(String roleId, String resourceIds) throws H3cException{
        H3cSession session = HBUtil.getH3cSession(); 
		Sysrole role = (Sysrole)session.get(Sysrole.class, roleId);

		if (RoleHelper.isUsefulRole(role) == RoleHelper.ROLE_NOT_EXIST)
			throw new H3cException("该角色不存在！");
		if (RoleHelper.isUsefulRole(role) == RoleHelper.ROLE_UN_USEFUL)
			throw new H3cException("无效的角色");
        if(resourceIds.indexOf(",")==-1){
        	return ;
        }
		String[] functionIds = resourceIds.split(",");
		for (int i = 0; i < functionIds.length; i++) {
			
			String hql = "from Sysroleacl a where a.roleid=:roleid and a.functionid=:functionid";
			Query query = session.createQuery(hql);
			query.setString("roleid", roleId);
			query.setString("functionid", functionIds[i]);
			if (query.list().size() > 0) {
				continue;
			}

			Sysroleacl acl = new Sysroleacl();
			acl.setRoleid(roleId);
			acl.setResourceid(HashCodeUtil.getAclResourceId(functionIds[i]));
			acl.setFunctionid(functionIds[i]);
			session.save(acl);
		}
	}
	
	/**
	 * 撤销角色的部分授权，并回收相应的权限
	 * @param roleId  角色ID
	 * @param resourceIds  资源ID 多个resourceid用逗号隔开
	 * @return 成功撤销授权返回true
	 * @throws PrivilegeException
	 */
	public boolean removeResourcesFromRole(String roleId, String resourceIds)
			throws H3cException {
        H3cSession session = HBUtil.getH3cSession(); 
		Sysrole role = (Sysrole)session.get(Sysrole.class, roleId);
		if (role == null || role.getStatus().equals(RoleHelper.UN_USEFUL))
			throw new H3cException("角色不存在");

		recycleResource(roleId, resourceIds);// 资源回收
//		PrivilegeLogger privilegeLog = PrivilegeLogger.getLogger();
//		privilegeLog.log("撤销角色"+role.getName()+"的部分授权，并回收相应的权限", "调用IRoleControl接口的removeResourcesFromRole方法", null);
		return true;
	}
	
	
	/*
	 * 私有方法，移除角色的部分授权，并回收相应的权限
	 * @param roleId  角色ID
	 * @param resourceIds  资源ID串， 多个resourceid用逗号隔开
	 * @return void
	 * @throws PrivilegeException
	 */
	@SuppressWarnings("unchecked")
	private void recycleResource(String roleId, String resourceIds)
			throws H3cException {
		if (resourceIds == null || resourceIds.trim().length() == 0)
			return;
		String[] functionIds = resourceIds.split(",");
		H3cSession session = HBUtil.getH3cSession(); 
		for (int i = 0; i < functionIds.length; i++) {
			String hql = "from Sysroleacl a where a.roleid=:roleid and a.functionid=:functionid";
			Query query = session.createQuery(hql);
			query.setString("roleid", roleId);
			query.setString("functionid", functionIds[i]);
			List<Sysroleacl> list = query.list();
			if (list.size() == 0) {
				continue;
			}

			Sysroleacl acl = (Sysroleacl) list.get(0);
			session.delete(acl);
		}
	}
	
	/**
	 * 根据roleid删除角色和角色权限表
	 * @param roleid
	 * @throws H3cException
	 */
	@SuppressWarnings("unchecked")
	public void removeRole(String roleId) throws H3cException {
        H3cSession session = HBUtil.getH3cSession(); 
		Sysrole role = (Sysrole)session.get(Sysrole.class, roleId);
		if (role == null)
			throw new H3cException("角色不存在");
		session.delete(role);
		
		Query query = session.createQuery("from Sysroleacl a where a.roleid=:roleid ");
		query.setString("roleid", roleId);
		List<Sysroleacl> lst = query.list();
		for(Sysroleacl acl : lst){
			session.delete(acl);
		}
		
		Query query1 = session.createQuery("from Sysact a where a.roleid=:roleid ");
		query1.setString("roleid", roleId);
		List<Sysact> sysActList = query1.list();
		for(Sysact sysAct : sysActList){
			session.delete(sysAct);
		}
	}
	
	/**
	 * 删除角色下的root
	 * @param roleId
	 * @throws H3cException
	 */
	@SuppressWarnings("unchecked")
	public void removeRoot(String roleId) throws H3cException {
		H3cSession session = HBUtil.getH3cSession(); 
		Query query = session.createQuery("from Sysroleacl a where a.roleid=:roleid and a.functionid='S000000'");
		query.setString("roleid", roleId);
		List<Sysroleacl> lst = query.list();
		for(Sysroleacl acl : lst){
			session.delete(acl);
		}
	}
	
	/**
	 * 为角色增加root
	 * @param roleId
	 * @throws H3cException
	 */
	public void addRoot(String roleId) throws H3cException {
		H3cSession session = HBUtil.getH3cSession(); 
		Sysroleacl acl = new Sysroleacl();
		acl.setRoleid(roleId);
		acl.setResourceid(HashCodeUtil.getAclResourceId("S000000"));
		acl.setFunctionid("S000000");
		session.save(acl);
	}
	
	/**
	 * 判断角色的有效性
	 * @param sr 等待判断的SmtRole对象实例
	 * @return ROLE_USEFUL 有效，ROLE_UN_USEFUL 无效
	 */
	public static int isUsefulRole(Sysrole sr){
		if(sr == null)
			return ROLE_NOT_EXIST;
		int status = Integer.parseInt(sr.getStatus());
		if(status == ROLE_USEFUL){
			return ROLE_USEFUL;
		}else{
			return ROLE_UN_USEFUL;
		}
	}

}
