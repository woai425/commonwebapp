package com.h3c.portal.business.common.dto;

import java.util.Date;

public class ResApplyDTO {

	private String uuid;
	private String resName;
	private String resSpec;
	private String remark;
	private String procInstId;
	private String state;
	private Date createDate;
	

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResSpec() {
		return resSpec;
	}
	public void setResSpec(String resSpec) {
		this.resSpec = resSpec;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProcInstId() {
		return procInstId;
	}
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
