package com.h3c.portal.wbms.smmgmt.syslog.service.serviceimpl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.portal.wbms.smmgmt.syslog.service.ISysLogService;

/**
 * *********************************************************************
 *
 * SysLogServiceImpl.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月23日 下午4:23:58
 * @revision    $Id:  *
 **********************************************************************
 */
@Service
public class SysLogServiceImpl extends ServiceSupport implements ISysLogService {

	@Override
	public AjaxJson getSyslogInfoByCondition(String logPrcol1, String logDigest, Integer logtype,Integer start, Integer limit)
			throws H3cException {
		StringBuffer sql = new StringBuffer("select b.opseno,b.opip,b.digest,b.prcol1,b.broswer,b.operator,b.optime from (select a.opseno,a.opip,a.digest,a.prcol1,a.broswer,a.operator,date_format(a.optime,'%Y-%m-%d %H:%i:%s') optime from Sysuserlog a where 1=1 ");
		HashMap<String, Object> mp = new java.util.HashMap<String, Object>();
		if(!StringUtils.isEmpty(logPrcol1)){
			sql.append(" and a.prcol1 like:logPrcol1");
			mp.put("logPrcol1", "%"+logPrcol1+"%");
		}
		if(!StringUtils.isEmpty(logDigest)){
			sql.append(" and a.digest like:logDigest");
			mp.put("logDigest", "%"+logDigest+"%");
		}
		if(logtype!=null&&!"0".equals(logtype.toString())){
			sql.append(" and a.logtype = :logtype");
			mp.put("logtype", logtype);
		}
		sql.append(" ) b order by b.opseno desc");
		return this.pageSqlQuery(sql.toString(), mp, start, limit);
	}

}
