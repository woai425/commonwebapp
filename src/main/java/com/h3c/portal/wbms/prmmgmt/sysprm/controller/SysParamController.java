package com.h3c.portal.wbms.prmmgmt.sysprm.controller;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.annotation.H3cLogType;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.annotation.OpLog;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.common.dto.SysparamDTO;
import com.h3c.portal.wbms.prmmgmt.sysprm.service.ISysprmService;
import com.h3c.portal.wbms.smmgmt.opermon.service.IOperMonService;


/**
 * *********************************************************************
*
* SysprmController.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     admil<br/>
* @create-time 2016年1月9日 下午2:17:34
* @revision    $Id:  *
**********************************************************************
 */
@Controller
@RequestMapping(value = "/sysParamController")
public class SysParamController extends ControllerSupport<Object>{
	@Autowired
	private ISysprmService service;
	
	@Autowired
	private IOperMonService operMonService;
	
	/**
	 * 打开demo模块
	 * @return
	 * @throws H3cException 
	 */
	@RequestMapping(params = "openSysParam")
	public ModelAndView openSysprm() throws H3cException {
		operMonService.operCount("sysprm");
		return new ModelAndView("/pages/portal/wbms/prmmgmt/sysprm/Sysprm");
	}
	
	@RequestMapping(params = "showAddSysprm")
	public ModelAndView showAddSysprm() throws H3cException {

		return new ModelAndView("/pages/portal/wbms/prmmgmt/sysprm/AddSysprm");
	}
	
	@RequestMapping(params = "showUpdateSysprm")
	public ModelAndView showUpdateSysprm(SysparamDTO dto) throws H3cException {
        ModelAndView mav = new ModelAndView("/pages/portal/wbms/prmmgmt/sysprm/UpdateSysprm");       
        try {
			dto.setParamexplain(new String(dto.getParamexplain().getBytes("ISO-8859-1"),"UTF-8"));
			dto.setParamvalue(new String(dto.getParamvalue().getBytes("ISO-8859-1"),"UTF-8"));
			dto.setParamname(new String(dto.getParamname().getBytes("ISO-8859-1"),"UTF-8"));
			dto.setParamcode(new String(dto.getParamcode().getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        mav.addObject("sysparam", dto);
        return mav;
	}
	
	 /**
     * 分页查询
     * @return
     * @throws H3cException
     */
	@RequestMapping(params = "query")
	@ResponseBody
	public AjaxJson query( String paramcode,String paramname,String maintstate,Integer start,Integer limit)
			throws H3cException {
		if(start==null){
			start = 0;
		}
		return service.getSysprmGrid( paramcode,paramname,maintstate,start, limit);
	}
	
	/**
	 * 删除系统参数
	 * @param codeid
	 * @return
	 * @throws H3cException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "deleteSysprm")
	@ResponseBody
	@H3cTransaction
	@OpLog(value=H3cLogType.RECORD,digest="删除系统参数",moduleName="系统参数维护")
	public AjaxJson deleteSysprm(String codeData) throws H3cException {
		AjaxJson json = new AjaxJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String,Object>> lst = mapper.readValue(codeData, List.class);
			for (int i = 0; i < lst.size(); i++) {
				Map<String,Object> map = lst.get(i);
				if (Boolean.parseBoolean(map.get("check").toString())) {
					service.deleteSysprm(map.get("paramcode").toString());
				}
			}
		} catch (IOException e) {
			throw new H3cException(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 保存系统参数
	 * @param dto
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "saveSysprm")
	@ResponseBody
	@H3cTransaction
	@OpLog(value=H3cLogType.RECORD,digest="增加系统参数",moduleName="系统参数维护")
	public AjaxJson saveSysprm(SysparamDTO dto) throws H3cException{
		AjaxJson json = new AjaxJson();
		service.saveSysprm(dto);
		return json;
	}
	
	@RequestMapping(params = "updateSysprm")
	@ResponseBody
	@H3cTransaction
	@OpLog(value=H3cLogType.RECORD,digest="修改系统参数",moduleName="系统参数维护")
	public AjaxJson updateSysprm(SysparamDTO dto) throws H3cException{
		AjaxJson json = new AjaxJson();
		service.updateSysprm(dto);
		return json;
	}
	
}

	
