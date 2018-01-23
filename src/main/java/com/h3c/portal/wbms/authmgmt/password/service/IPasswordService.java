package com.h3c.portal.wbms.authmgmt.password.service;

import java.util.List;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.entities.Sysgroup;
import com.h3c.portal.wbms.common.dto.SysuserDTO;

public interface IPasswordService {
	
	/**
	 * 获取组装好的组织信息
	 * @return
	 * @throws H3cException
	 */
	public String getGroupInfo() throws H3cException;
	
	/**
	 * 获取组织列表
	 * @return
	 * @throws H3cException
	 */
	public List<Sysgroup> getGroupList() throws H3cException;
	
	/**
	 * 更新用户
	 * @param dto
	 * @throws H3cException
	 */
	public void updateUser(SysuserDTO dto) throws H3cException;
	
	/**
	 * 更新密码
	 * @param dto
	 * @throws H3cException
	 */
	public void updatePassword(SysuserDTO dto)throws H3cException;
	/**
	 * 获取用户所在的组织
	 * @param userid
	 * @return
	 * @throws H3cException
	 */
	public Sysgroup getGroup(String userid) throws H3cException;

}
