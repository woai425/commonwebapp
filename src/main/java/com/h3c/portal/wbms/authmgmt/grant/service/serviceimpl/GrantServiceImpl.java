package com.h3c.portal.wbms.authmgmt.grant.service.serviceimpl;

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
import com.h3c.portal.wbms.authmgmt.grant.service.IGrantService;
import com.h3c.portal.wbms.common.dto.SysgroupDTO;

/**
 * *********************************************************************
*
* GrantServiceImpl.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年1月12日 下午1:59:22
* @revision    $Id:  *
**********************************************************************
 */
@Service
public class GrantServiceImpl extends ServiceSupport implements IGrantService {
	
	/* BEGIN: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	@SuppressWarnings("unchecked")
	@Override
	public String findRolesGranted(String objectid) throws H3cException {
		String hql ="from Sysact b where b.objectid = :objectid AND b.roleid in (select roleid  from Sysrole where status = 1 )";
		Query query = session.createQuery(hql);
		query.setParameter("objectid", objectid);
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
	
	@Override
	public AjaxJson getSysuser(String loginname,
			String username,int start,int limit) throws H3cException {
		StringBuffer hql = new StringBuffer("from Sysuser a where 1=1");
		HashMap<String, Object> mp = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(loginname)) {
			hql.append(" and a.loginname like:loginname");
			mp.put("loginname", "%"+loginname+"%");
		}
		if (!StringUtils.isEmpty(username)) {
			hql.append(" and a.username =:username");
			mp.put("username", username);
		}
		return this.pageHqlQuery(hql.toString(), mp, start, limit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AjaxJson getGroup()
			throws H3cException {
		String hql = "from Sysgroup a where a.rate <> '-1' order by a.rate asc ";
		List<Sysgroup> sysgroupList = session.createQuery(hql).list();
		List<SysgroupDTO> dtoList = new ArrayList<SysgroupDTO>();
		for(Sysgroup sysGroup:sysgroupList){
			int number = 0;
			SysgroupDTO dto = new SysgroupDTO();
			CopyIgnoreProperty.copy(sysGroup, dto);
			for(int i=0;i<sysgroupList.size();i++){
				if(sysGroup.getGroupid().equals(sysgroupList.get(i).getParentid())){
					number ++;
				}
			}
			dto.setChild(String.valueOf(number));
			dtoList.add(dto);
		}
		AjaxJson json = new AjaxJson();
		json.setData(dtoList);
		return json;
	}
	
	/* BEGIN: Modified by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	@Override
	public AjaxJson getRoleByCondition(String objectid, String grant,
			int start, int limit) throws H3cException {
		//String hql = "from Sysrole ";
		String hql = "from Sysrole where status=1";
		HashMap<String, Object> mp = new HashMap<String, Object>();
		return this.pageHqlQuery(hql, mp, start, limit);
	}
	/* END: Modified by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
 
	/* BEGIN: Modified by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	@SuppressWarnings("unchecked")
	@Override
	public void grant(String objectid, String objecttype, String grant, String rolegrid) throws H3cException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String, Object>> dtoLst = mapper.readValue(rolegrid,
					List.class);
			for (int i = 0; i < dtoLst.size(); i++) {
				Map<String, Object> map = dtoLst.get(i);
				//Sysact sys = new Sysact();
				Sysact sys = (Sysact) session.createQuery("from Sysact where objectid = :objectid and roleid = :roleid")
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
	public List<Sysrole> getAllRoles() throws H3cException {
		// TODO 获取全部角色
		String hql = "from Sysrole";
		@SuppressWarnings("unchecked")
		List<Sysrole> roles = session.createQuery(hql).list();
		if (roles.size() >= 0) {
			return roles;
		} else {
			return new ArrayList<Sysrole>();
		}
	}

}
