package com.h3c.portal.business.modeler.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.portal.business.modeler.IModelerService;

@Service
public class ModelerServiceImpl extends ServiceSupport implements IModelerService {

	@Override
	public AjaxJson getModelerList(Integer start, Integer limit) throws H3cException {
		String sql = "select a.ID_,a.KEY_,a.NAME_,a.VERSION_,a.CREATE_TIME_,a.LAST_UPDATE_TIME_ from  act_re_model  a  where 1=1 order by a.CREATE_TIME_ desc";
		AjaxJson json = pageSqlQuery(sql, new HashMap<String, Object>(), start, limit);
		return json;
	}
}
