package com.h3c.portal.wbms.common.dto;

/**
 * *********************************************************************
*
* SysactDTO.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年1月12日 下午2:02:15
* @revision    $Id:  *
**********************************************************************
 */
public class SysactDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String actid;
	private String objectid;
	private String roleid;
	private String sceneid;
	private String objecttype;
	private String dispatchauth;
	private String hashcode;
	private String grant;
	public String getActid() {
		return actid;
	}
	public void setActid(String actid) {
		this.actid = actid;
	}
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getSceneid() {
		return sceneid;
	}
	public void setSceneid(String sceneid) {
		this.sceneid = sceneid;
	}
	public String getObjecttype() {
		return objecttype;
	}
	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}
	public String getDispatchauth() {
		return dispatchauth;
	}
	public void setDispatchauth(String dispatchauth) {
		this.dispatchauth = dispatchauth;
	}
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	public String getGrant() {
		return grant;
	}
	public void setGrant(String grant) {
		this.grant = grant;
	}
	

}