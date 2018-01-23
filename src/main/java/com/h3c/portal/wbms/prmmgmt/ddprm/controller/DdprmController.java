package com.h3c.portal.wbms.prmmgmt.ddprm.controller;

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
import com.h3c.portal.wbms.common.dto.SyscodeDTO;
import com.h3c.portal.wbms.prmmgmt.ddprm.service.IDdprmService;
import com.h3c.portal.wbms.smmgmt.opermon.service.IOperMonService;

/***********************************************************************
 *
 * DdprmController.java
 *
 * H3C所有， 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * 
 * @copyright Copyright: 2015-2020
 * @creator lfw2082<br/>
 * @create-time 2016年1月6日 下午2:55:36
 * @revision $Id: *
 ***********************************************************************/
@Controller
@RequestMapping(value = "/ddprmController")
public class DdprmController extends ControllerSupport<Object> {
	@Autowired
	private IDdprmService service;
	
	@Autowired
	private IOperMonService operMonService;

	/**
	 * 打开数据字典
	 * 
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "openDdprm")
	public ModelAndView openDdprm() throws H3cException {
		operMonService.operCount("ddprm");
		return new ModelAndView("/pages/portal/wbms/prmmgmt/ddprm/Ddprm");
	}

	@RequestMapping(params = "showAddDdprm")
	public ModelAndView showAddDdprmDemo() throws H3cException {

		return new ModelAndView("/pages/portal/wbms/prmmgmt/ddprm/AddDdprm");
	}

	@RequestMapping(params = "showUpdateDdprm")
	public ModelAndView showUpdateDdprmDemo(SyscodeDTO dto) throws H3cException {
		ModelAndView mav = new ModelAndView(
				"/pages/portal/wbms/prmmgmt/ddprm/UpdateDdprm");

		try {
			dto.setCodeexplain(new String(dto.getCodeexplain().getBytes(
					"ISO-8859-1"), "UTF-8"));
			dto.setCodevalue(new String(dto.getCodevalue().getBytes(
					"ISO-8859-1"), "UTF-8"));
			dto.setCodename(new String(
					dto.getCodename().getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mav.addObject("syscode", dto);
		return mav;
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "query")
	@ResponseBody
	public AjaxJson query(String code, String distinct ,Integer start, Integer limit)
			throws H3cException {
		if (start == null) {
			start = 0;
		}
		if("1".equals(distinct)){
			return service.getDistinctDdprmGrid(code, distinct, start, limit);
		}else{
			return service.getDdprmGrid(code, start, limit);
		}	
	}

	/**
	 * 删除
	 * 
	 * @param codeid
	 * @return
	 * @throws H3cException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "deleteDdprm")
	@ResponseBody
	@H3cTransaction
	@OpLog(value=H3cLogType.RECORD,digest="删除数据字典",moduleName="数据字典")
	public AjaxJson deleteDdprm(String codeData) throws H3cException {
		AjaxJson json = new AjaxJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String,Object>> lst = mapper.readValue(codeData, List.class);
			for (int i = 0; i < lst.size(); i++) {
				Map<String,Object> map = lst.get(i);
				if (Boolean.parseBoolean(map.get("check").toString())) {
					service.deleteDdprm(Integer.parseInt(map.get("codeid").toString()));
				}
			}
		} catch (IOException e) {
			throw new H3cException(e.getMessage());
		}
		return json;
	}

	/**
	 * 保存数据字典
	 * 
	 * @param dto
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "saveDdprm")
	@ResponseBody
	@H3cTransaction
	@OpLog(value=H3cLogType.RECORD,digest="增加数据字典",moduleName="数据字典")
	public AjaxJson saveDdprm(SyscodeDTO dto, String userlog) throws H3cException {
		AjaxJson json = new AjaxJson();
		service.saveDdprm(dto);
		return json;
	}

	@RequestMapping(params = "updateDdprm")
	@ResponseBody
	@H3cTransaction
	@OpLog(value=H3cLogType.RECORD,digest="修改数据字典",moduleName="数据字典")
	public AjaxJson updateDdprm(SyscodeDTO dto) throws H3cException {
		AjaxJson json = new AjaxJson();
		service.updateDdprm(dto);
		return json;
	}

}
