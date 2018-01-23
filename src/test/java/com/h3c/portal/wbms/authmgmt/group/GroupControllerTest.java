package com.h3c.portal.wbms.authmgmt.group;

import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.portal.Tool;
import com.h3c.portal.wbms.authmgmt.group.controller.GroupController;
import com.h3c.portal.wbms.authmgmt.role.controller.RoleController;

/**
 * ************************************************************
 *
 * GroupControllerTest.java
 *
 * H3C所有， 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * 
 * @copyright Copyright：2016-2020
 * @author l11656
 * @create-time 2017年9月6日 下午5:03:29
 * @revision $Id：
 ***************************************************************
 */
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroupControllerTest extends Tool {
	private static final Logger logger = LoggerFactory.getLogger(GroupControllerTest.class);

	@Autowired
	private GroupController groupController;

	@Autowired
	private RoleController roleController;

	@Test
	public void test001FindGroup() throws H3cException {
		AjaxJson json = new AjaxJson();
		json = groupController.findGroup();
		assertNotNull(json);
	}

	@Test
	public void test002GetGroupInfo() throws H3cException {
		AjaxJson json = new AjaxJson();
		json = groupController.getGroupInfo("0e68e524c9d14185b8a2a4117ac95c05");
		logger.info(json.getData().toString());
		assertNotNull(json);
	}

	@Test
	@Rollback(value=false)
	public void test0003DeleteGroup() throws H3cException {
		AjaxJson json = new AjaxJson();
		roleController.deleteRole("aa0f813513b34b98a98fa132155b2c35");
	}
}
