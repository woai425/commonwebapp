package com.h3c.portal.wbms.authmgmt.role.service.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysact;
import com.h3c.framework.common.entities.Sysfunction;
import com.h3c.framework.common.entities.Sysrole;
import com.h3c.framework.common.entities.Sysroleacl;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.util.CopyIgnoreProperty;
import com.h3c.framework.util.GlobalNames;
import com.h3c.framework.util.HashCodeUtil;
import com.h3c.portal.wbms.authmgmt.role.service.IRoleService;
import com.h3c.portal.wbms.authmgmt.role.util.RoleHelper;
import com.h3c.portal.wbms.common.dto.SysroleDTO;

/***********************************************************************
 *
 * RoleServiceImpl.java
 *
 * H3C所有， 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * 
 * @copyright Copyright: 2015-2020
 * @creator lfw2082<br/>
 * @create-time 2016年1月19日 上午10:09:21
 * @revision $Id: *
 ***********************************************************************/
@Service
public class RoleServiceImpl extends ServiceSupport implements IRoleService {
	@Override
	public AjaxJson getRoleGrid(String rolename, String roledesc, String owner, Integer start, Integer limit)
			throws H3cException {

		StringBuffer hql = new StringBuffer("from Sysrole a where 1=1 ");
		HashMap<String, Object> mp = new java.util.HashMap<String, Object>();

		if (!StringUtils.isEmpty(rolename)) {
			hql.append(" and a.rolename like :rolename");
			mp.put("rolename", "%" + rolename + "%");
		}
		if (!StringUtils.isEmpty(roledesc)) {
			hql.append(" and a.roledesc like :roledesc");
			mp.put("roledesc", "%" + roledesc + "%");
		}
		if (!StringUtils.isEmpty(owner)) {
			hql.append(" and a.owner like :owner");
			mp.put("owner", "%" + owner + "%");
		}
		return this.pageHqlQuery(hql.toString(), mp, start, limit);
	}

	@Override
	public void deleteRole(String roleid) throws H3cException {
		if (roleid != null) {
			boolean flag = this.isDefaultRole(roleid);
			if (!flag) {
				RoleHelper.getInstance().removeRole(roleid);
			}else{
				throw new IllegalArgumentException("this role can not be deleted");
			}
		}
	}

	@Override
	public void saveRole(SysroleDTO dto) throws H3cException {
		if (dto != null) {
			List<String> roleNames = getRoleNames();
        	if (roleNames.contains(dto.getRolename())) {
				throw new H3cException("角色名已经存在！");
			}
			if (dto.getRoleid() != null) {
				Sysrole role = (Sysrole) session.get(Sysrole.class, dto.getRoleid());
				role.setOwner(dto.getOwner());
				role.setRolename(dto.getRolename());
				role.setRoledesc(dto.getRoledesc());
				role.setStatus(dto.getStatus());
				session.saveOrUpdate(role);
			} else {
				Sysrole sys = new Sysrole();
				CopyIgnoreProperty.copy(dto, sys);
				sys.setCreatedate(new Date());
				sys.setRoleid(HashCodeUtil.getRoleId(dto.getRolename()));
				session.save(sys);
			}

		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void doGrant(String roleid, String treeInfo) throws H3cException {
		Map map = RoleHelper.getInstance().parseRoleTree(treeInfo);
		if (map != null) {
			RoleHelper.getInstance().addResousesToRole(roleid, map.get("add").toString());
			RoleHelper.getInstance().removeResourcesFromRole(roleid, map.get("remove").toString());

			// 确保都能找到根节点
			RoleHelper.getInstance().removeRoot(roleid);
			RoleHelper.getInstance().addRoot(roleid);
		}

	}

	@Override
	public void delete(String roleId) throws H3cException {
		if (roleId != null) {
			RoleHelper.getInstance().removeRole(roleId);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysfunction> getTreeMenu() {
		Query query = session.createQuery("from Sysfunction order by orderno asc");
		List<Sysfunction> menus = query.list();

		return menus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysroleacl> getRoleTree(String roleid) throws H3cException {

		Query query = session.createQuery("from Sysroleacl a where a.roleid = :roleid");
		query.setString("roleid", roleid);
		List<Sysroleacl> menus = query.list();

		return menus;

	}

	@Override
	public Sysrole getSysRole(String roleid) throws H3cException {

		Query query = session.createQuery("from Sysrole a where a.roleid = :roleid");
		query.setString("roleid", roleid);
		Sysrole sysRole = (Sysrole) query.uniqueResult();

		return sysRole;
	}

	private Boolean isDefaultRole(String roleid) {
		String roleStr = GlobalNames.sysConfig.get("DEFALUT_ROLE_ID");
		String[] array = roleStr.split(",");
		for (int i = 0; i < array.length; i++) {
			if ((roleid != null) && roleid.equals(array[i])) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysact> getRoleUsers(String roleId) {
		String hql = "FROM Sysact a WHERE a.roleid =:roleId AND a.objecttype = '0'";
		List<Sysact> sysactList = session.createQuery(hql).setParameter("roleId", roleId).list();
		return sysactList;
	}

	@Override
	public AjaxJson getqueryRoleUsersList(String roleId, Integer start, Integer limit) throws H3cException {
		StringBuffer sql = new StringBuffer(
				"SELECT s.USERID, s.LOGINNAME,s.USERNAME,s.USERTYPE,s.EMAIL FROM sysuser s, sysact a WHERE a.objecttype = '0' AND a.objectid = s.userid");
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(roleId)) {
			sql.append(" AND a.roleid=:roleId");
			map.put("roleId", roleId);
		}
		return this.pageSqlQuery(sql.toString(), map, start, limit);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRoleNames() throws H3cException {
		String sql = "SELECT ROLENAME FROM sysrole";
		Query query = session.createSQLQuery(sql);
		List<String> roleNames = query.list();
		return roleNames;
	}
}
