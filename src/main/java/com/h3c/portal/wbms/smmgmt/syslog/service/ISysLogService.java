package com.h3c.portal.wbms.smmgmt.syslog.service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;

/**
 * *********************************************************************
 *
 * ISysLogService.java
 * 系统日志接口
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月23日 下午4:24:03
 * @revision    $Id:  *
 **********************************************************************
 */
public interface ISysLogService {

	/**
	 * 根据模块名称、日志摘要和日志类型获取日志信息
	 * @param logPrcol1
	 * @param logDigest
	 * @param logtype
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson getSyslogInfoByCondition(String logPrcol1, String logDigest, Integer logtype,Integer start, Integer limit) throws H3cException;
}
