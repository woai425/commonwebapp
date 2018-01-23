package com.h3c.portal.business.modeler.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.annotation.H3cTransaction;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.framework.util.GlobalNames;
import com.h3c.portal.business.modeler.IModelerService;

/**
 * 模型控制类 *********************************************************************
 * 类注释，类作用 <br/>
 * ModelController.java <br/>
 *
 * H3C所有，<br/>
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。<br/>
 *
 * @copyright Copyright: 2015-2020
 * @creator gfw2359 <br/>
 * @create-time 2017年8月4日 上午10:29:02
 * @revision $Id: *
 **********************************************************************
 */
@Controller
@RequestMapping(value = "/ModelController")
@ResponseBody
public class ModelController extends ControllerSupport<Object> {

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private IModelerService modelerService;

	private static final String PREFIX = "/pages/portal/business/modeler/";

	/**
	 * 打开模型列表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "openModelList")
	public ModelAndView openModelList() {
		List<Deployment> list = repositoryService.createDeploymentQuery().orderByDeploymenTime().desc().list();
		Deployment deployment = list.get(0);
		String name = deployment.getName();
		String deploymentId = deployment.getId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", name);
		mv.addObject("deploymentId", deploymentId);
		mv.setViewName(PREFIX + "ModelList");
		return mv;

	}

	/**
	 * 
	 * 分页查询模型列表
	 * 
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "ModelList")
	@ResponseBody
	public AjaxJson ModelList(Integer start, Integer limit) throws H3cException {
		AjaxJson json = new AjaxJson();
		/*
		 * List<Model> list =
		 * repositoryService.createModelQuery().orderByLastUpdateTime
		 * ().desc().listPage(start, limit); AjaxJson json = new AjaxJson();
		 * List<Map<String, Object>> mapLst = null; if ((list.size() > 0) &&
		 * (list != null)) { mapLst = new ArrayList<>(); for (Model modelList :
		 * list) { DateFormat df =
		 * DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		 * Map<String, Object> map = new HashMap<>(); map.put("id",
		 * modelList.getId()); map.put("key", modelList.getKey());
		 * map.put("name", modelList.getName()); map.put("version",
		 * modelList.getVersion()); map.put("createtime",
		 * df.format(modelList.getCreateTime())); map.put("lasttime",
		 * df.format(modelList.getLastUpdateTime())); mapLst.add(map); } }
		 * json.setData(mapLst); return json;
		 */
		if (start == null) {
			start = 0;
		}

		json = modelerService.getModelerList(start, limit);

		return json;
	}

	/**
	 * 打开创建模型窗口
	 * 
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "showCreateModel")
	public ModelAndView showCreateModel() throws H3cException {
		return new ModelAndView(PREFIX + "CreateModel");
	}


	/**
	 * 创建模型(方法二)
	 * 
	 * @param name
	 * @param key
	 * @param description
	 * @param request
	 * @param response
	 */

	@RequestMapping(params = "create")
	@ResponseBody
	public String create(String name, String key, String description, HttpServletRequest request,
			HttpServletResponse response) {

		Model modelData = repositoryService.newModel();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			description = StringUtils.defaultString(description);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name);
			modelData.setKey(StringUtils.defaultString(key));
			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

		} catch (Exception e) {

			GlobalNames.log.error("模型创建失败", e);
		}
		return request.getContextPath() + "/modeler.html?modelId=" + modelData.getId();
	}

	/**
	 * 删除模型
	 * 
	 * @param dto
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "delete")
	@ResponseBody
	@H3cTransaction
	public AjaxJson delete(String id) throws H3cException {
		repositoryService.deleteModel(id);
		AjaxJson json = new AjaxJson();

		return json;
	}
	
	
	/**
	 * 根据模型id部署
	 * 
	 * @param modelId
	 * @param redirectAttributes
	 * @return
	 */

	@SuppressWarnings("finally")
	@RequestMapping(params = "deploy")
	@ResponseBody
	public AjaxJson deploy(String id) {
		AjaxJson aj = new AjaxJson();
		try {
			Model modelData = repositoryService.getModel(id);
			ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService
					.getModelEditorSource(modelData.getId()));
			byte[] bpmnBytes = null;

			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);

			String processName = modelData.getName() + ".bpmn";
			Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
					.addString(processName, new String(bpmnBytes)).deploy();
			modelData.setDeploymentId(deployment.getId());
			aj.setData(deployment.getId());
			aj.setMsg("部署成功");
		} catch (Exception e) {
			aj.setSuccess(false);
			aj.setMsg("部署失败，请绘制流程图");
			throw new H3cException("根据模型部署失败!");
		} finally {

			return aj;
		}
	}

	/**
	 * 展示部署信息
	 * 
	 * @param deploymentId
	 * @return
	 * @throws H3cException
	 */
	@RequestMapping(params = "show")
	@ResponseBody
	public AjaxJson showMsg(String deploymentId) throws H3cException {
		AjaxJson aj = new AjaxJson();
		Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
		aj.setMsg(deployment.getName());
		return aj;
	}

	/**
	 * 获取模型名称
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "getModelName")
	@ResponseBody
	public AjaxJson getModelName(String id) {
		AjaxJson aj = new AjaxJson();
		String modelName = repositoryService.getModel(id).getName();
		aj.setData(modelName);
		return aj;

	}

	/**
	 * 校验名字
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(params = "checkName")
	@ResponseBody
	public AjaxJson checkName(String name) {
		AjaxJson aj = new AjaxJson();
		Model model = repositoryService.createModelQuery().modelName(name).singleResult();
		if (model == null) {
			aj.setData(1);
		} else {
			aj.setData(2);
		}
		return aj;
	}

}
