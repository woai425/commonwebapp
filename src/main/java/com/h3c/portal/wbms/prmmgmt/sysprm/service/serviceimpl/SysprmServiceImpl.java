package com.h3c.portal.wbms.prmmgmt.sysprm.service.serviceimpl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysparam;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.util.CopyIgnoreProperty;
import com.h3c.framework.util.GlobalNames;
import com.h3c.portal.wbms.common.dto.SysparamDTO;
import com.h3c.portal.wbms.prmmgmt.sysprm.service.ISysprmService;

@Service
public class SysprmServiceImpl extends ServiceSupport implements ISysprmService {

	public AjaxJson getSysprmGrid(String paramcode, String paramname,
			String maintstate, Integer start, Integer limit)
			throws H3cException {
		StringBuffer hql = new StringBuffer("from Sysparam a where 1=1 ");
		HashMap<String, Object> mp = new java.util.HashMap<String, Object>();

		if (!StringUtils.isEmpty(paramcode)) {
			hql.append(" and a.paramcode like :paramcode");
			mp.put("paramcode", "%" + paramcode + "%");
		}
		if (!StringUtils.isEmpty(paramname)) {
			hql.append(" and a.paramname like :paramname");
			mp.put("paramname", "%" + paramname + "%");
		}
		if (!StringUtils.isEmpty(maintstate)) {
			hql.append(" and a.maintstate like :maintstate");
			mp.put("maintstate", "%" + maintstate + "%");
		}
		hql.append(" order by paramname desc");
		return this.pageHqlQuery(hql.toString(), mp, start, limit);
	}

	@Override
	public void saveSysprm(SysparamDTO dto) throws H3cException {
		if (dto != null) {
			Sysparam sys = new Sysparam();
			CopyIgnoreProperty.copy(dto, sys);
			session.save(sys);
			GlobalNames.sysConfig.put(sys.getParamcode(), sys.getParamvalue());
		}
	}

	@Override
	public void deleteSysprm(String paramcode) {
		Query query = session.createQuery("from Sysparam where paramcode = ?");
		Sysparam sysprm = (Sysparam) query.setParameter(0, paramcode)
				.uniqueResult();
		session.delete(sysprm);
		GlobalNames.sysConfig.remove(sysprm.getParamcode());
	}

	@Override
	public void updateSysprm(SysparamDTO dto) {
		/**
		 * 修改数据字典
		 */
		String hql = " from Sysparam a where a.paramcode = :paramcode";
		Query query = session.createQuery(hql);
		query.setString("paramcode", dto.getParamcode());
		Sysparam sysparam = (Sysparam) query.uniqueResult();
		sysparam.setParamvalue(dto.getParamvalue());
		sysparam.setParamexplain(dto.getParamexplain());
		sysparam.setMaintstate(dto.getMaintstate());
		GlobalNames.sysConfig.put(sysparam.getParamcode(), sysparam.getParamvalue());
	}

}
