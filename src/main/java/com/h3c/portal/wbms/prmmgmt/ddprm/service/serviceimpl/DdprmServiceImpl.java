package com.h3c.portal.wbms.prmmgmt.ddprm.service.serviceimpl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.CodeManager;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Syscode;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.util.CopyIgnoreProperty;
import com.h3c.framework.util.RecognitionUtil;
import com.h3c.portal.wbms.common.dto.SyscodeDTO;
import com.h3c.portal.wbms.prmmgmt.ddprm.service.IDdprmService;

/***********************************************************************
 *
 * DdprmServiceImpl.java
 *
 * H3C所有， 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * 
 * @copyright Copyright: 2015-2020
 * @creator lFW2082<br/>
 * @create-time 2016年1月7日 上午11:30:11
 * @revision $Id: *
 ***********************************************************************/
@Service
public class DdprmServiceImpl extends ServiceSupport implements IDdprmService {

	@Override
	public AjaxJson getDdprmGrid(String code, Integer start, Integer limit)
			throws H3cException {
		StringBuffer hql = new StringBuffer("from Syscode a where 1=1 ");
		HashMap<String, Object> mp = new java.util.HashMap<String, Object>();

		if (!StringUtils.isEmpty(code)) {
			if(RecognitionUtil.containsChinese(code)){
				hql.append(" and a.codename like :code");
				mp.put("code", "%" + code + "%");
			}else{
				hql.append(" and a.code like :code");
				mp.put("code", "%" + code + "%");
			}
		}
		hql.append(" order by a.code asc,codevalue asc");
		return this.pageHqlQuery(hql.toString(), mp, start, limit);
	}
	
	@Override
	public AjaxJson getDistinctDdprmGrid(String code,String distinct,Integer start, Integer limit) throws H3cException{
		StringBuffer sql = new StringBuffer("select a.code,a.codename from Syscode a where 1=1");
		HashMap<String, Object> mp = new java.util.HashMap<String, Object>();
		if (!StringUtils.isEmpty(code)) {
			if(RecognitionUtil.containsChinese(code)){
				sql.append(" and a.codename like :code");
				mp.put("code", "%" + code + "%");
			}else{
				sql.append(" and a.code like :code");
				mp.put("code", "%" + code + "%");
			}
		}
		sql.append(" group by a.code,a.codename order by a.code asc");
		return this.pageSqlQuery(sql.toString(), mp, start, limit);
	}

	@Override
	public void deleteDdprm(Integer codeid) throws H3cException {
		Syscode ddprm = (Syscode) session.get(Syscode.class, codeid);
		CodeManager.getCodeManager().deleteDdMap(ddprm.getCode(),
				ddprm.getCodevalue());
		session.delete(ddprm);
	}

	@Override
	public void saveDdprm(SyscodeDTO dto) throws H3cException {
		if (dto != null) {
			Syscode sys = new Syscode();
			CopyIgnoreProperty.copy(dto, sys);
			session.saveOrUpdate(sys);
			CodeManager.getCodeManager().insertDdMap(dto.getCode(),
					dto.getCodevalue(), dto.getCodeexplain());
		}
	}

	@Override
	public void updateDdprm(SyscodeDTO dto) throws H3cException {
		String hql = " from Syscode a where a.codeid = :codeid";
		Query query = session.createQuery(hql);
		query.setInteger("codeid", dto.getCodeid());
		Syscode syscode = (Syscode) query.uniqueResult();
		if (syscode != null) {
			CodeManager.getCodeManager().updateDdMap(syscode.getCode(),
					syscode.getCodevalue(), dto.getCodevalue(),
					dto.getCodeexplain());
			syscode.setCodeexplain(dto.getCodeexplain());
			syscode.setCodestate(dto.getCodestate());
			syscode.setCodetype(dto.getCodetype());
			syscode.setCodevalue(dto.getCodevalue());
			syscode.setMaintstate(dto.getMaintstate());
			session.update(syscode);
		}
	}

}
