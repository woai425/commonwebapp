package com.h3c.portal.wbms.authmgmt.resource.service;

import java.util.List;

import com.h3c.framework.H3cException;
import com.h3c.portal.wbms.common.dto.*;
import com.h3c.framework.common.entities.Sysfunction;

/**
 * *********************************************************************
*
* IResourceService.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年1月6日 下午5:44:37
* @revision    $Id:  1.0
**********************************************************************
 */
public interface IResourceService {
	
	/**
	 * 获取导航栏菜单
	 * @return
	 */
	public List<Sysfunction> getMenuBack();
	
	/**
	 * 获取单个节点的信息
	 * @param id
	 * @return
	 * @throws H3cException
	 */
	public SysfunctionDTO getNodeInfo(String id) throws H3cException;
	
	/**
	 * 保存节点信息
	 * @param dto
	 * @throws H3cException
	 */
	public void saveNode(SysfunctionDTO dto) throws H3cException;
	
	/**
	 * 删除节点信息
	 * @param functionid
	 * @throws H3cException
	 */
	public void deleteNode(String functionid) throws H3cException;
	
	/**
	 * 查询子节点
	 * @param functionid
	 * @return
	 * @throws H3cException
	 */
	public List<Sysfunction> getFunctionChild(String functionid) throws H3cException;

}
