package com.h3c.portal.business.common.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ResApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "res_apply")
public class ResApply implements java.io.Serializable {

	private static final long serialVersionUID = 2767589608860435500L;
	private String uuid;
	private String resName;
	private String resSpec;
	private String remark;
	private String procInstId;
	private String state;
	private Date createDate;

	// Constructors

	/** default constructor */
	public ResApply() {
	}

	/** minimal constructor */
	public ResApply(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public ResApply(String uuid, String resName, String resSpec, String remark,
			String procInstId, String state, Date createDate) {
		this.uuid = uuid;
		this.resName = resName;
		this.resSpec = resSpec;
		this.remark = remark;
		this.procInstId = procInstId;
		this.state = state;
		this.createDate = createDate;
	}

	// Property accessors
	@Id
	@Column(name = "UUID", unique = true, nullable = false, length = 36)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "RES_NAME", length = 50)
	public String getResName() {
		return this.resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	@Column(name = "RES_SPEC", length = 50)
	public String getResSpec() {
		return this.resSpec;
	}

	public void setResSpec(String resSpec) {
		this.resSpec = resSpec;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "PROC_INST_ID", length = 36)
	public String getProcInstId() {
		return this.procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Column(name = "STATE", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "CREATE_DATE", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}