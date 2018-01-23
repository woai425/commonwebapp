package com.h3c.portal.business.common.cronjobfty.initimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.quartz.Job;
import org.quartz.SchedulerException;

import com.h3c.framework.common.entities.Systimetask;
import com.h3c.framework.core.dao.HBUtil;
import com.h3c.framework.util.GlobalNames;
import com.h3c.framework.web.system.quartz.IPortalQuartzJob;
import com.h3c.portal.business.common.dto.QuartzTaskDTO;
import com.h3c.portal.business.common.util.QuartzJobCommon;

/**
 * *********************************************************************
 * 定时任务辅助工具类 QuartzJobUtil.java
 *
 * H3C所有， 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * 
 * @copyright Copyright: 2015-2020
 * @creator z10926<br/>
 * @create-time 2016年2月4日 下午5:48:02
 * @revision $Id: *
 **********************************************************************
 */
public class QuartzJobInitImpl implements IPortalQuartzJob {

	@SuppressWarnings("unchecked")
	public void initQuartzJob() {
		Session session = HBUtil.openNewSession();
		try {
			String hql = " from Systimetask a where a.isstart = :isstart";
			Query query = session.createQuery(hql);
			query.setString("isstart", "1");
			List<Systimetask> list = query.list();
			if (list != null && list.size() != 0) {
				for (Systimetask stk : list) {
					QuartzTaskDTO QtDto = new QuartzTaskDTO();
					Class<? extends Job> c = (Class<? extends Job>) Class.forName(stk.getClassname());
					QtDto.setCronExpression(stk.getCron());
					QtDto.setDesc(stk.getDescription());
					QtDto.setJobGroup(stk.getJobgroup());
					QtDto.setJobName(stk.getJobname());
					QtDto.setQuartzFactory(c);
					try {
						QuartzJobCommon.createOrUpdateTask(QtDto);
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
			}
			// doSomething
		} catch (Exception e) {
			GlobalNames.log.info("初始化调度器异常：" + e.getMessage());
			e.printStackTrace();
		} finally {
			session.close(); // 释放资源
		}
	}

}
