package com.h3c.portal.util;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 * @author l11656
 * 
 */
public class ApplicationContextUtil {
	private static Logger logger = Logger.getLogger(ApplicationContextUtil.class);
	private static ApplicationContextUtil contextUtil;
	private ApplicationContext context;

	private ApplicationContextUtil() {
	}

	public synchronized void intiContext(String... applicationContextXml) {
		if (applicationContextXml.length > 0) {
			try {
				context = new ClassPathXmlApplicationContext(applicationContextXml);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("failed to load spring context class path:"
						+ Arrays.asList(applicationContextXml).toString(), e);
			}
			if (context == null) {
				try {
					context = new FileSystemXmlApplicationContext(applicationContextXml);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("failed to load spring context file system:"
							+ Arrays.asList(applicationContextXml).toString(), e);
				}
			}
		} else {
			try {
				context = new FileSystemXmlApplicationContext("file:*applicationContext.xml",
						"file:*/*applicationContext.xml");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("failed to load spring context file system", e);
			}
			if (context == null) {
				try {
					context = new ClassPathXmlApplicationContext("classpath*:*applicationContext.xml",
							"classpath*:*/*applicationContext.xml");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("failed to load spring context classpath", e);
				}
			}
		}
		if (context == null)
			throw new RuntimeException("failed to init spring application context.");
	}

	public static synchronized ApplicationContextUtil getInstance() {
		if (contextUtil == null)
			contextUtil = new ApplicationContextUtil();
		return contextUtil;
	}

	public Object getBean(String id) {
		if (context == null)
			throw new RuntimeException(" spring application context is null");
		return context.getBean(id);
	}

	public <T> T getBean(Class<T> clazz) {
		if (context == null)
			throw new RuntimeException(" spring application context is null");
		return context.getBean(clazz);
	}

	public <T> Map<String, T> getInstanceList(Class<T> clazz) {
		if (context == null)
			throw new RuntimeException(" spring application context is null");
		Map<String, T> instances = context.getBeansOfType(clazz);
		return instances;
	}

	public String getMessage(String key, Object[] args) {
		if (context == null)
			throw new RuntimeException(" spring application context is null");
		return context.getMessage(key, args, Locale.getDefault());
	}

	public String getMessage(String key, Object[] args, Locale locale) {
		if (context == null)
			throw new RuntimeException(" spring application context is null");
		return context.getMessage(key, args, locale);
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
}
