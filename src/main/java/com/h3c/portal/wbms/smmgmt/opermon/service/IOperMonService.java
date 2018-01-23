package com.h3c.portal.wbms.smmgmt.opermon.service;

import java.util.Map;

import com.h3c.framework.H3cException;

/**
 * *********************************************************************
 * 系统模块操作监控接口
 * IOperMonService.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年2月23日 下午9:29:48
 * @revision    $Id:  *
 **********************************************************************
 */
public interface IOperMonService {

	/**
	 * 根据不同的标志将计数器做递增
	 * @param flag
	 * @throws H3cException
	 */
	public void operCount(String flag) throws H3cException;
	
	
	public Map<String,Integer> getOperMonInfo() throws H3cException;
}
