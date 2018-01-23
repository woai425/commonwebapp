package com.h3c.portal.util;

import java.io.InputStream;

import org.apache.log4j.Logger;
import com.h3c.framework.H3cException;
import com.h3c.framework.common.CodeManager;
import com.h3c.framework.util.GlobalNames;
import com.h3c.framework.util.LicenseUtil;
import com.h3c.framework.web.system.quartz.IPortalQuartzJob;

/**
 * ************************************************************
*in order to initialize sysconfig.xml as well as The Class of InitListener.java
* InitExcutionHelp.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright Copyright：2016-2020
* @author l11656
* @create-time 2017年8月29日 下午3:59:51
* @revision $Id：
***************************************************************
 */
public class InitExcutionHelp {

	private static Logger log;

	public static void contextInitialized() throws Exception {
		// TODO Auto-generated method stub
		log = Logger.getLogger(InitExcutionHelp.class);
		log.info("H3CPlatform License cache...");
		Thread keyThread = new Thread(new Runnable() {
			public void run() {
				try {
					LicenseUtil.parseLicenseXml();
					LicenseUtil.initKeySeed();
				} catch (Exception e) {
					log.error("license 秘钥生成失败！", e);
					return;
				}
			}
		}, "keyThread");
		keyThread.start();
		log.info("H3CPlatform Starting...");
		// event.getServletContext().log("H3CPlatform Starting...");
		log.info("Loading Public Resources...");
		// event.getServletContext().log("Loading Public Resources...");
		log.info("Loading SysConfig.xml");
		// event.getServletContext().log("Loading SysConfig.xml");
		Class<InitExcutionHelp> initBeanClass = InitExcutionHelp.class;
		InputStream inputstream = initBeanClass.getClassLoader().getResourceAsStream("conf/sysconfig.xml");
		GlobalNames.readConfiguration(inputstream);
		GlobalNames.readConfiguration();
		// event.getServletContext().log("H3CPlatform is Started");
		log.info("Init SysCode");
		try {
			CodeManager.getCodeManager().initDdMap();
		} catch (H3cException e) {
			log.info("Init SysCode Error:" + e.getMessage());
		}

		log.info("Init QuartzJob");
		try {
			@SuppressWarnings("unchecked")
			Class<? extends IPortalQuartzJob> clazz = (Class<? extends IPortalQuartzJob>) Class.forName(GlobalNames.sysConfig.get("QUARTZ_JOB_INIT_CLASS"));
			IPortalQuartzJob job = (IPortalQuartzJob) clazz.newInstance();
			job.initQuartzJob();
		} catch (Exception e) {
			log.info("Init QuartzJob Error:" + e.getMessage());
		}
		log.info("H3CPlatform is Started");

	}
}
