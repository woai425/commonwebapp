package com.h3c.portal.wbms.authmgmt.group.service.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.entities.Sysact;
import com.h3c.framework.common.entities.Sysgroup;
import com.h3c.framework.common.entities.Sysusergroupref;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.util.CopyIgnoreProperty;
import com.h3c.framework.util.UUIDHexUtil;
import com.h3c.portal.wbms.authmgmt.group.service.IGroupService;
import com.h3c.portal.wbms.common.dto.SysgroupDTO;
/**
 **********************************************************************
*
* GroupServiceImpl.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     cfw2081<br/>
* @create-time 2016年1月23日 上午10:06:58
* @revision    $Id:  *
**********************************************************************
 */
@Service
public class GroupServiceImpl extends ServiceSupport implements IGroupService{
	@SuppressWarnings("unchecked")
	@Override
	public List<SysgroupDTO> getMenuBack() throws H3cException {
		Query query = session.createQuery("from Sysgroup ");	
		List<Sysgroup> menus = query.list();
		List<SysgroupDTO> dtos = new ArrayList<SysgroupDTO>();
		for(Sysgroup sysGroup:menus){
			SysgroupDTO dto = new SysgroupDTO();
			CopyIgnoreProperty.copy(sysGroup, dto);
			dto.setFunctionid(dto.getGroupid());
			dto.setParent(dto.getParentid());
			dto.setTitle(dto.getName());
			dtos.add(dto);
		}
		return dtos;
	}
	
	@Override
	public Sysgroup getGroupInfo(String groupid) throws H3cException {
		
		Query query = session.createQuery("from Sysgroup where groupid = :groupid");
		query.setString("groupid", groupid);
		return (Sysgroup) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void deleteGroup(String groupid) throws H3cException {
		
		//1. 获取该组织
		Sysgroup group = (Sysgroup) session.get(Sysgroup.class, groupid);
		
		//2. 获取该组织和其所有子组织
		List<Sysgroup> listAll = new ArrayList<Sysgroup>();
		List<Sysgroup> listChildren = new ArrayList<Sysgroup>();
		listAll.add(group);
		listChildren.add(group);
		List<Sysgroup> groupList = this.getChildrenGroup(listChildren, listAll);
		
		//3. 删除组织和子组织
		for(Sysgroup sysGroup : groupList){
			//3.1 删除组织的角色
			Query querySysact = session.createQuery("from Sysact a where a.objectid=:objectid ");
			querySysact.setString("objectid", sysGroup.getGroupid());
			List<Sysact> sysActList = querySysact.list();
			if(sysActList.size() >0){
				for(Sysact sysAct : sysActList){
					session.delete(sysAct);
				}
			}
			
			//3.2 删除组织人员关系
			Query query = session.createQuery("from Sysusergroupref a where a.groupid = :groupid ");
			query.setString("groupid", sysGroup.getGroupid());
			List<Sysusergroupref> SysuserList = query.list();
			if(SysuserList.size() >0){
				for(Sysusergroupref sysusergroup : SysuserList){
					session.delete(sysusergroup);
				}
			}
			
			//3.3 删除用户
			session.delete(sysGroup);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void saveGroup(SysgroupDTO dto) throws H3cException {
		Sysgroup sys = new Sysgroup();
		CopyIgnoreProperty.copy(dto, sys);
		if(StringUtils.isEmpty(sys.getGroupid())){
			sys.setGroupid(UUIDHexUtil.generate32bit());
			sys.setCreatedate(new Date());
		}else{
			Query query = session.createSQLQuery("SELECT CREATEDATE FROM sysgroup WHERE GROUPID =:groupid");
			query.setString("groupid", sys.getGroupid());
			Object createDate = query.uniqueResult();
			sys.setCreatedate((Date) createDate);
		}
		session.saveOrUpdate(sys);	
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sysgroup> getChildrenGroup(List<Sysgroup> listChildren,List<Sysgroup> listAll) throws H3cException {
		
		List<Sysgroup> tempList = new ArrayList<Sysgroup>();
		tempList.addAll(listChildren);
		listChildren.clear();
		for(Sysgroup group:tempList){
			Query query = session.createQuery("from Sysgroup where parentid = :parentid");
			query.setString("parentid", group.getGroupid());
			List<Sysgroup> groupList = query.list();
			if(groupList.size() > 0){
				listChildren.addAll(groupList);
			}
		}
		if(listChildren.size() <= 0){
			return listAll;
		}else{
			listAll.addAll(listChildren);
			return getChildrenGroup(listChildren,listAll);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysgroup> getChildrenGroupList(String groupid) {
		String hql = "FROM Sysgroup g WHERE g.parentid =:groupid";
		List<Sysgroup> sysgroupList = session.createQuery(hql).setParameter("groupid", groupid).list();
		return sysgroupList;
	}
	
}
