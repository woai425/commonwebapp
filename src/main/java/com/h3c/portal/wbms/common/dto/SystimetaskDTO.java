package com.h3c.portal.wbms.common.dto;

import java.util.Date;

public class SystimetaskDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String taskid;
	private String jobname;
	private String jobgroup;
	private String description;
	private String cron;
	private String classname;
	private String active;
	private String isstart;
	private Date createdate;
	private String creator;
	private Date updatedate;
	private String updator;

	// Constructors

	/** default constructor */
	public SystimetaskDTO() {
	}

	public String getTaskid() {
		return this.taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getJobname() {
		return this.jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getJobgroup() {
		return this.jobgroup;
	}

	public void setJobgroup(String jobgroup) {
		this.jobgroup = jobgroup;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public String getCron() {
		return this.cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getIsstart() {
		return this.isstart;
	}

	public void setIsstart(String isstart) {
		this.isstart = isstart;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getUpdatedate() {
		return this.updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

}