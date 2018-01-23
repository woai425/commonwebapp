package com.h3c.portal.wbms.authmgmt.dispgrant.service.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysact;
import com.h3c.framework.common.entities.Sysgroup;
import com.h3c.framework.common.entities.Sysrole;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.util.CopyIgnoreProperty;
import com.h3c.framework.util.HashCodeUtil;
import com.h3c.portal.wbms.authmgmt.dispgrant.service.IDispGrantService;
import com.h3c.portal.wbms.common.dto.SysgroupDTO;

/**
 * *********************************************************************
 *
 * GrantServiceImpl.java
 *
 * H3C所有， 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * 
 * @copyright Copyright: 2015-2020
 * @creator s11972<br/>
 * @create-time 2016年1月12日 下午1:59:22
 * @revision $Id: *
 **********************************************************************
 */
@Service
public class DispGrantServiceImpl extends ServiceSupport implements
		IDispGrantService {

	@Override
	public AjaxJson getSysuser(String userid, String loginname,
			String username, int start, int limit) throws H3cException {
		StringBuffer groupid = new StringBuffer();
		List<SysgroupDTO> groupList = this.getChildGroup(userid);
		for (int i = 0; i < groupList.size(); i++) {
			groupid.append("'");
			groupid.append(groupList.get(i).getGroupid());
			groupid.append("'");
			groupid.append(",");
		}
		groupid.append("'test'");
		StringBuffer hql = new StringBuffer("from Sysuser a where 1=1");
		HashMap<String, Object> mp = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(loginname)) {
			hql.append(" and a.loginname like:loginname");
			mp.put("loginname", "%" + loginname + "%");
		}
		if (!StringUtils.isEmpty(username)) {
			hql.append(" and a.username =:username");
			mp.put("username", username);
		}
		if (groupList.size() > 0) {
			hql.append(" and a.userid in(select userid from Sysusergroupref where groupid in ("
					+ groupid + "))");
		}
		return this.pageHqlQuery(hql.toString(), mp, start, limit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysgroupDTO> getGroup() throws H3cException {
		String hql = "from Sysgroup a where a.rate <> '-1' order by a.rate asc ";
		List<Sysgroup> sysgroupList = session.createQuery(hql).list();
		List<SysgroupDTO> dtoList = new ArrayList<SysgroupDTO>();
		for (Sysgroup sysGroup : sysgroupList) {
			int number = 0;
			SysgroupDTO dto = new SysgroupDTO();
			CopyIgnoreProperty.copy(sysGroup, dto);
			for (int i = 0; i < sysgroupList.size(); i++) {
				if (sysGroup.getGroupid().equals(
						sysgroupList.get(i).getParentid())) {
					number++;
				}
			}
			dto.setChild(String.valueOf(number));
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	/* BEGIN: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	@Override
	public String findRolesGranted(String objectid,String userid) throws H3cException {
		StringBuffer hql = new StringBuffer("from Sysact b where b.objectid = :objectid and "
				+ "b.roleid in (select roleid from Sysact a "
				+ "where a.dispatchauth = '1' and a.objectid =:userid )");
		Query query = session.createQuery(hql.toString());
		query.setParameter("objectid", objectid);
		query.setParameter("userid", userid);
		List<Sysact> sysactList = query.list();
		String result = "";
		if((sysactList!=null)&&(sysactList.size()>0)){
			for(Sysact sysact:sysactList){
				result += sysact.getRoleid() + "," + sysact.getDispatchauth() + ";";
			}
		}
		return result;
		}
	/* END: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	
	/* BEGIN: Modified by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	@Override
	public AjaxJson getRoleByCondition(String userid, String objectid,
			String grant, int start, int limit) throws H3cException {
		String hql = "from Sysrole where roleid in(select roleid from"
				+ " Sysact a where a.dispatchauth = '1' and a.objectid = :userid)";
		HashMap<String, Object> mp = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(userid)) {
			mp.put("userid", userid);
		}
		return this.pageHqlQuery(hql, mp, start, limit);
	}
	/* END: Modified by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	
	/* BEGIN: Modified by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	@SuppressWarnings("unchecked")
	@Override
	public void grant(String objectid, String objecttype, String grant,
			String rolegrid) throws H3cException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String, Object>> dtoLst = mapper.readValue(rolegrid,
					List.class);
			for (int i = 0; i < dtoLst.size(); i++) {
				Map<String, Object> map = dtoLst.get(i);
				Sysact sys = (Sysact) session
						.createQuery(
								"from Sysact where objectid = :objectid and roleid = :roleid")
						.setString("objectid", objectid)
						.setString("roleid", map.get("roleid").toString())
						.uniqueResult();
				if (Boolean.parseBoolean(map.get("check").toString())) {
					if(sys==null){
						sys = new Sysact();
						sys.setActid(HashCodeUtil.getSysAclId(map.get("roleid").toString()));
						sys.setObjectid(objectid);
						sys.setObjecttype(objecttype);
						sys.setRoleid(map.get("roleid").toString());
						if (Boolean.parseBoolean(map.get("dispatchauth").toString())) {
							sys.setDispatchauth("1");
						} else {
							sys.setDispatchauth("0");
						}
						session.save(sys);
					}else{
						if (Boolean.parseBoolean(map.get("dispatchauth").toString())) {
							sys.setDispatchauth("1");
						} else {
							sys.setDispatchauth("0");
						}
						session.update(sys);
					}
				}else{
					if(sys!=null){
						session.delete(sys);
					}
				}
			}
		} catch (IOException e) {
			throw new H3cException(e.getMessage());
		}
	}
	/* END: Modified by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	@Override
	public AjaxJson getDispGrant(String userid, Integer start, Integer limit)
			throws H3cException {
		StringBuffer hql = null;
		hql = new StringBuffer(
				"from Sysrole where roleid in(select roleid from Sysact a where a.dispatchauth = '1' ");
		HashMap<String, Object> mp = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(userid)) {
			hql.append(" and a.objectid = :objectid");
			mp.put("objectid", userid);
		}
		hql.append(")");
		return this.pageHqlQuery(hql.toString(), mp, start, limit);
	}

	@Override
	public List<SysgroupDTO> getGroupNumber(String userid) throws H3cException {
		List<SysgroupDTO> groupList = this.getGroup();
		List<SysgroupDTO> groupDTOList = new ArrayList<SysgroupDTO>();
		Sysgroup sysGroup = (Sysgroup) session
				.createQuery(
						"from Sysgroup a where a.groupid = (select groupid from Sysusergroupref su where su.userid = :userid)")
				.setString("userid", userid).uniqueResult();
		for (int i = 0; i < groupList.size(); i++) {
			if (sysGroup.getGroupid().equals(groupList.get(i).getGroupid())) {
				groupList.get(i).setGroupnumber("1");
				groupDTOList.add(groupList.get(i));
			}
		}
		for (int i = 0; i < groupList.size(); i++) {
			for (int j = 0; j < groupDTOList.size(); j++) {
				for (int k = 0; k < groupList.size(); k++) {
					if (groupDTOList.get(j).getGroupid()
							.equals(groupList.get(k).getParentid())) {
						groupList.get(k).setGroupnumber("1");
						groupDTOList.add(groupList.get(k));
					}
				}
			}
		}
		return groupList;
	}

	@Override
	public List<SysgroupDTO> getChildGroup(String userid) throws H3cException {
		List<SysgroupDTO> groupList = this.getGroupNumber(userid);
		List<SysgroupDTO> groupDTOList = new ArrayList<SysgroupDTO>();
		for (SysgroupDTO dto : groupList) {
			if ("1".equals(dto.getGroupnumber())) {
				groupDTOList.add(dto);
			}
		}
		return groupDTOList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysrole> getUserAllRole(String userid) throws H3cException {
		List<Sysrole> roles = new ArrayList<Sysrole>();
		String hql = "from Sysrole s where s.roleid in (select a.roleid from Sysact a where a.objectid = :userid)";
		if (!StringUtils.isEmpty(userid)) {
			roles = session.createQuery(hql).setString("userid", userid).list();
		}
		return roles;
	}

}
