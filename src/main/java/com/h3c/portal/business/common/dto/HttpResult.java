package com.h3c.portal.business.common.dto;

/**
 * *********************************************************************
 *
 * HttpResultDTO.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月23日 上午10:54:49
 * @revision    $Id:  *
 **********************************************************************
 */
public class HttpResult
{
	private boolean success = true;
	private String msg = "success";
	private String errorCode = "";
	private int code;
	private String entity = null;
	
	public boolean isSuccess()
	{
		return success;
	}
	
	public void setSuccess(boolean success)
	{
		this.success = success;
	}
	
	public String getMsg()
	{
		return msg;
	}
	
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	public String getErrorCode()
	{
		return errorCode;
	}
	
	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}
	
	public int getCode()
	{
		return code;
	}
	
	public void setCode(int code)
	{
		this.code = code;
	}
	
	public String getEntity()
	{
		return entity;
	}
	
	public void setEntity(String entity)
	{
		this.entity = entity;
	}
	
}
