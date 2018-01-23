package com.h3c.portal.business.taglibmgmt.grid.service.serviceimpl;


import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.portal.business.taglibmgmt.grid.service.IGridService;


/**
 * 单位基本信息修改DEMO 业务实现类
 * @author 周兆巍
 * @version 创建时间：2014年12月17日 下午4:35:52
 */
@Service
public class GridServiceImpl extends ServiceSupport implements IGridService {

	@Override
	public AjaxJson getOrgInfoByCondition(String aab001, String aab004,
			String aab030, int start, int limit) throws H3cException {
//		Session session = HBUtil.openNewSession();
//		try{
//			Criteria c = session.createCriteria(Ab01.class);
//			c.setCacheable(true);
//			c.add(Restrictions.eq("aaz001", 1L));
//			Ab01 ab01 = (Ab01)c.uniqueResult();
//			System.out.println(ab01.getAab001());
//		}finally{
//			session.close();
//		}
		// Query query = session.createQuery("from Ab01 a ");
		// query.setCacheable(true);
		// query.list();
//		Ab01 ab01 = (Ab01)query.uniqueResult();
		//Ab01 ab01 = (Ab01)sess..
//		System.out.println(ab01.getAab001());
		// return new AjaxJson();
		StringBuffer hql = new StringBuffer("from Ab01 a where 1=1");
		HashMap<String, Object> mp = new java.util.HashMap<String, Object>();
		if (!StringUtils.isEmpty(aab001)) {
			hql.append(" and a.aab001 like :aab001");
			mp.put("aab001", "%" + aab001 + "%");
		}
		if (!StringUtils.isEmpty(aab004)) {
			hql.append(" and a.aab004 like :aab004");
			mp.put("aab004", "%" + aab004 + "%");
		}
		if (!StringUtils.isEmpty(aab030)) {
			hql.append(" and a.aab030 = :aab030");
			mp.put("aab030", aab030);
		}
		return this.pageHqlQuery(hql.toString(), mp, start, limit);
	}

	@Override
	public AjaxJson getOrgInfoByCondition(String aab001, String aab004,
			String aab030) throws H3cException {
		StringBuffer hql = new StringBuffer("from Ab01 a where 1=1");
		if(!StringUtils.isEmpty(aab001)){
			hql.append(" and a.aab001 like :aab001");
		}
		if(!StringUtils.isEmpty(aab004)){
			hql.append(" and a.aab004 like :aab004");
		}
		if(!StringUtils.isEmpty(aab030)){
			hql.append(" and a.aab030 = :aab030");
		}
		Query query = session.createQuery(hql.toString());
		if(!StringUtils.isEmpty(aab001)){
			query.setString("aab001", "%"+aab001+"%");
		}
		if(!StringUtils.isEmpty(aab004)){
			query.setString("aab004", "%"+aab004+"%");
		}
		if(!StringUtils.isEmpty(aab030)){
			query.setString("aab030", aab030);
		}
		AjaxJson json = new AjaxJson();
		json.setData(query.list());
		return json;
	}
}
