package com.h3c.portal.wbms.smmgmt.cronjob.service;

import java.text.ParseException;

import org.quartz.SchedulerException;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.portal.wbms.common.dto.SystimetaskDTO;
/**
 * *********************************************************************
*
* ICronJobService.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     lFW2156<br/>
* @create-time 2016年2月4日 上午10:41:35
* @revision    $Id:  *
**********************************************************************
 */
public interface ICronJobService {
    /**
     * 分页查询定时任务
     * 
     * @param desc
     * @param start
     * @param limit
     * @return
     * @throws H3cException
     */
	public AjaxJson getCronJobInfoByCondition(String desc,Integer start, Integer limit) throws H3cException;
	
	/**
	 * 新建定时任务
	 * 
	 * @param dto
	 * @throws H3cException
	 */
	public void saveCronJob(SystimetaskDTO dto) throws H3cException;
	
	/**
	 * 删除定时任务
	 * 
	 * @param taskid
	 * @throws H3cException
	 */
	public void deleteCronJob(String taskid) throws H3cException,SchedulerException;
	
	/**
	 * 编辑定时任务
	 * 
	 * @param dto
	 * @throws H3cException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 */
	public void updateCronJob(SystimetaskDTO dto) throws H3cException,ClassNotFoundException,ParseException,SchedulerException;
	
	/**
	 * 立即生效定时任务
	 * 
	 * @param taskid
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson doActiveCronJob(String taskid) throws H3cException,SchedulerException;
	
	/**
	 * 开始执行定时任务
	 * 
	 * @param taskid
	 * @return
	 * @throws H3cException
	 * @throws ParseException
	 */
	public AjaxJson startCronJob(String taskid) throws H3cException;
	
	/**
	 * 停止正在执行的定时任务
	 * 
	 * @param taskid
	 * @throws H3cException
	 */
	public void stopCronJob(String taskid) throws H3cException,SchedulerException;
	
	/**
	 * 检查定时任务名称是否存在
	 * 
	 * @param jobName
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson checkJobName(String jobName) throws H3cException;
	
	/**
	 * 检查任务类是否存在
	 * 
	 * @param className
	 * @return
	 * @throws H3cException
	 */
	public AjaxJson checkClassName(String className) throws H3cException;
}
