package com.h3c.portal;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.h3c.framework.common.entities.Sysuser;
import com.h3c.framework.core.dao.BeanUtils;
import com.h3c.framework.web.security.auth.controller.LoginController;

/**
 * ************************************************************
 *
 * Tool.java
 *
 * H3C所有， 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * 
 * @copyright Copyright：2016-2020
 * @author l11656
 * @create-time 2017年8月29日 下午6:20:10
 * @revision $Id：
 ***************************************************************
 */
@WebAppConfiguration
@ContextConfiguration({ "classpath*:conf/spring-mvc-core.xml", "classpath*:conf/dispatcher-servlet.xml" })
@Rollback(value = false)
@Transactional(transactionManager = "transactionManager")
public class Tool extends AbstractTransactionalJUnit4SpringContextTests {

	public static MockHttpServletRequest request;

	public static MockHttpServletResponse response;

	public static Sysuser user = new Sysuser();

	private volatile boolean flag = false;
	@Autowired
	private LoginController loginController;

	public Tool() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
						if (BeanUtils.getApplicationContext() != null) {
							flag = true;
						}
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@BeforeClass
	public static void setBeforeClass() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		request.setCharacterEncoding("UTF-8");
		user.setLoginname("admin");
		user.setPasswd("admin");
		// 因为我们没有启动tomcat容器,所以在web.xml中注册的监听器org.springframework.web.util.Log4jConfigListener并没有卵用，得通过以下读取文件路径的方式加载log4j的配置
		DOMConfigurator.configure(ClassLoader.getSystemResource("conf/log4j.xml"));

	}

	@Before
	public void setBefor() throws Exception {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					if (flag == true) {
						try {
							loginController.checkuser(user, request);
							loginController.login(request);
						} catch (Exception e) {
							e.printStackTrace();
						}
						user = (Sysuser) request.getSession().getAttribute("cUser");
					}
					break;
				}
			}
		}).start();
	}
}
