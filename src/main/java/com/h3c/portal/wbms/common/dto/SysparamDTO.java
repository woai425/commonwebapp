package com.h3c.portal.wbms.common.dto;

import java.io.Serializable;

public class SysparamDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String paramcode;
	private String paramname;
	private String paramvalue;
	private String paramexplain;
	private String maintstate;
	
	public String getParamcode() {
		return paramcode;
	}
	public void setParamcode(String paramcode) {
		this.paramcode = paramcode;
	}
	public String getParamname() {
		return paramname;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	public String getParamexplain() {
		return paramexplain;
	}
	public void setParamexplain(String paramexplain) {
		this.paramexplain = paramexplain;
	}
	public String getMaintstate() {
		return maintstate;
	}
	public void setMaintstate(String maintstate) {
		this.maintstate = maintstate;
	}
	
	
}
