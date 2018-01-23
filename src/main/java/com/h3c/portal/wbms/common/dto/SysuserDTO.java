package com.h3c.portal.wbms.common.dto;

import java.util.Date;

/***********************************************************************
 *
 * SysuserDTO.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lfw2082<br/>
 * @create-time 2016年1月15日 下午4:16:09
 * @revision    $Id:  *
 ***********************************************************************/

public class SysuserDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userid;
	private String passwd;
	private String loginname;
	private String dept;
	private String description;
	private String iplist;
	private String checkip;
	private String useful;
	private String isleader;
	private String regionid;
	private String username;
	private String owner;
	private String rate;
	private String empid;
	private String macaddr;
	private String usertype;
	private String otherinfo;
	private String createdate;
	private String email;
	private String phone;
	private String address;
	private String hashcode;
	private String groupid;
	private String leaveFlag;
	private Date pswinvalidtime;
	private String acctlockflag;
	
	public String getAcctlockflag() {
		return acctlockflag;
	}
	public void setAcctlockflag(String acctlockflag) {
		this.acctlockflag = acctlockflag;
	}
	public Date getPswinvalidtime() {
		return pswinvalidtime;
	}
	public void setPswinvalidtime(Date pswinvalidtime) {
		this.pswinvalidtime = pswinvalidtime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIplist() {
		return iplist;
	}
	public void setIplist(String iplist) {
		this.iplist = iplist;
	}
	public String getCheckip() {
		return checkip;
	}
	public void setCheckip(String checkip) {
		this.checkip = checkip;
	}
	public String getUseful() {
		return useful;
	}
	public void setUseful(String useful) {
		this.useful = useful;
	}
	public String getIsleader() {
		return isleader;
	}
	public void setIsleader(String isleader) {
		this.isleader = isleader;
	}
	public String getRegionid() {
		return regionid;
	}
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getMacaddr() {
		return macaddr;
	}
	public void setMacaddr(String macaddr) {
		this.macaddr = macaddr;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getOtherinfo() {
		return otherinfo;
	}
	public void setOtherinfo(String otherinfo) {
		this.otherinfo = otherinfo;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getLeaveFlag() {
		return leaveFlag;
	}
	public void setLeaveFlag(String leaveFlag) {
		this.leaveFlag = leaveFlag;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
