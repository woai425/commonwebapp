package com.h3c.portal.wbms.common.dto;

/***********************************************************************
 *
 * SyscodeDTO.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lfw2082<br/>
 * @create-time 2016年1月8日 下午4:32:14
 * @revision    $Id:  *
 ***********************************************************************/

public class SyscodeDTO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer codeid;
	private String code;
	private String codename;
	private String codevalue;
	private String codeexplain;
	private String codetype;
	private String codestate;
	private String maintstate;
	public Integer getCodeid() {
		return codeid;
	}
	public void setCodeid(Integer codeid) {
		this.codeid = codeid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public String getCodevalue() {
		return codevalue;
	}
	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}
	public String getCodeexplain() {
		return codeexplain;
	}
	public void setCodeexplain(String codeexplain) {
		this.codeexplain = codeexplain;
	}
	public String getCodetype() {
		return codetype;
	}
	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}
	public String getCodestate() {
		return codestate;
	}
	public void setCodestate(String codestate) {
		this.codestate = codestate;
	}
	public String getMaintstate() {
		return maintstate;
	}
	public void setMaintstate(String maintstate) {
		this.maintstate = maintstate;
	}


}
