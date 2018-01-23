package com.h3c.portal.business.common.dto;

import org.quartz.Job;

/**
 * *********************************************************************
 * 定时任务参数DTO
 * QuartzTaskDTO.java
 * 
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月26日 上午11:26:19
 * @revision    $Id:  *
 **********************************************************************
 */
public class QuartzTaskDTO{
	 
    /** 任务名称 */
    private String jobName;
    
    /** 任务分组 */
    private String jobGroup;
 
    /** 任务运行时间表达式 */
    private String cronExpression;
 
    /** 任务描述 */
    private String desc;

    /** 任务执行类 */
	private Class<? extends Job> quartzFactory;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Class<? extends Job> getQuartzFactory() {
		return quartzFactory;
	}

	public void setQuartzFactory(Class<? extends Job> quartzFactory) {
		this.quartzFactory = quartzFactory;
	}
}
