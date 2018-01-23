package com.h3c.portal.wbms.smmgmt.cronjob.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.common.dto.SystimetaskDTO;
import com.h3c.portal.wbms.smmgmt.cronjob.service.ICronJobService;
import com.h3c.portal.wbms.smmgmt.opermon.service.IOperMonService;
/**
 * *********************************************************************
*
* CronJobController.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     lFW2156<br/>
* @create-time 2016年2月4日 上午10:24:50
* @revision    $Id:  *
**********************************************************************
*/

@Controller
@RequestMapping(value = "/cronJobController")
public class CronJobController extends ControllerSupport<Object>{
	
	@Autowired
	ICronJobService service;
	@Autowired
	private IOperMonService operMonService;

	@RequestMapping(params = "openCronJob")
	public ModelAndView openCronJob() throws H3cException {
		operMonService.operCount("time");
		return new ModelAndView("/pages/portal/wbms/smmgmt/cronjob/CronJob");
	}
	
	@RequestMapping(params = "showAddCronJob")
	public ModelAndView showAddCronJob() throws H3cException {
		return new ModelAndView("/pages/portal/wbms/smmgmt/cronjob/AddCronJob");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "showUpdateCronJob")
	public ModelAndView showUpdateCronJob(String codeData) throws H3cException, UnsupportedEncodingException {
        ModelAndView mav = new ModelAndView("/pages/portal/wbms/smmgmt/cronjob/UpdateCronJob");
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = null;
		try {
			map = mapper.readValue(new String(codeData.getBytes("ISO-8859-1"),"utf-8"), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new H3cException(e.getMessage());
		}
        mav.addObject("cronJob", map);
        return mav;
	}
	
	/**
	 * 更新定时任务
	 * 
	 * @param dto
	 * @return
	 * @throws H3cException
	 * @throws ClassNotFoundException
	 * @throws SchedulerException 
	 */
	@RequestMapping(params = "updateCronJob")
	@ResponseBody
	@H3cTransaction
	public AjaxJson updateCronJob(SystimetaskDTO dto) throws H3cException,ClassNotFoundException, SchedulerException{
		AjaxJson json = new AjaxJson();
		try {
			service.updateCronJob(dto);
			return json;
		} catch (ParseException e) {
			json.setSuccess(false);
			json.setMsg("cron表达式不正确，请重新编辑！");
			return json;
		}
	}
	
	/**
	 * 检查任务名称是否存在
	 * 
	 * @param jobName
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "checkJobName")
	@ResponseBody
	@H3cTransaction
	public AjaxJson checkJobName(String jobName) throws H3cException{
		return service.checkJobName(jobName);
	}
	
	/**
	 * 检查任务类是否存在
	 * @param className
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "checkClassName")
	@ResponseBody
	@H3cTransaction
	public AjaxJson checkClassName(String className) throws H3cException{
		return service.checkClassName(className);
	}
	
	/**
	 * 立即生效定时任务
	 * 
	 * @param taskid
	 * @return
	 * @throws H3cException
	 * @throws SchedulerException 
	 */
	@RequestMapping(params = "doActiveCronJob")
	@ResponseBody
	@H3cTransaction
	public AjaxJson doActiveCronJob(String taskid) throws H3cException, SchedulerException{		
		return service.doActiveCronJob(taskid);
	}
	
	/**
	 * 开始执行定时任务
	 * 
	 * @param taskid
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "startCronJob")
	@ResponseBody
	@H3cTransaction
	public AjaxJson startCronJob(String taskid) throws H3cException{
				return service.startCronJob(taskid);
	}
	
	/**
	 * 停止正在执行的定时任务
	 * 
	 * @param taskid
	 * @return
	 * @throws H3cException
	 * @throws SchedulerException 
	 */
	@RequestMapping(params = "stopCronJob")
	@ResponseBody
	@H3cTransaction
	public AjaxJson stopCronJob(String taskid) throws H3cException, SchedulerException{
		AjaxJson json = new AjaxJson();
		service.stopCronJob(taskid);
		return json;
	}
	
	/**
	 * 新建定时任务
	 * 
	 * @param dto
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "saveCronJob")
	@ResponseBody
	@H3cTransaction
	public AjaxJson saveDdprm(SystimetaskDTO dto) throws H3cException{
		AjaxJson json = new AjaxJson();
		service.saveCronJob(dto);
		return json;
	}
	
	/**
	 * 删除定时任务
	 * 
	 * @param codeData
	 * @return
	 * @throws H3cException
	 * @throws SchedulerException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "deleteCronJob")
	@ResponseBody
	@H3cTransaction
	public AjaxJson deleteCronJob(String codeData) throws H3cException, SchedulerException {
		AjaxJson json = new AjaxJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String,Object>> lst = mapper.readValue(codeData, List.class);
			for (int i = 0; i < lst.size(); i++) {
				Map<String,Object> map = lst.get(i);
				if (Boolean.parseBoolean(map.get("check").toString())) {
					service.deleteCronJob(map.get("taskid").toString());
				}
			}
		} catch (IOException e) {
			throw new H3cException(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 分页查询定时任务
	 * 
	 * @param desc
	 * @param start
	 * @param limit
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "query")
	@ResponseBody
	public AjaxJson query(String desc,@RequestParam(value="start",defaultValue="0") Integer start,Integer limit)
			throws H3cException {
		if(start==null){
			start = 0;
		}
		return service.getCronJobInfoByCondition(desc,start,limit);
	}
	
}
