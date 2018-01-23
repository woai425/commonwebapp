package com.h3c.portal.util;

import java.util.Map;
import org.apache.log4j.Logger;

/**
 * @author l11656
 * 
 */
public class BeanFactory {

	private static ApplicationContextUtil applicationContextUtil;
	private static final Logger logger = Logger.getLogger(BeanFactory.class);

	/**
	 * get the dm bean registerd in spring
	 * 
	 * @param beanName
	 * @return Object
	 */
	public static Object getBean(String beanName) {
		return applicationContextUtil.getBean(beanName);
	}

	public static <T> Map<String, T> getInstanceList(Class<T> clazz) {
		Map<String, T> instances = applicationContextUtil.getInstanceList(clazz);
		return instances;
	}

	public static void init() {
		try {
			String[] beanPath = new String[] { "classpath:conf/spring-mvc-core.xml",
					"classpath:conf/dispatcher-servlet.xml" };
			applicationContextUtil = ApplicationContextUtil.getInstance();
			applicationContextUtil.intiContext(beanPath);
		} catch (Exception e) {
			logger.error("load spring configuration failed! system exit", e);
			System.exit(-1);
		}
	}
}
