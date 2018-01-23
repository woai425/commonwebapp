package com.h3c.portal.wbms.smmgmt.cronjob.service.serviceimpl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Systimetask;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.util.CopyIgnoreProperty;
import com.h3c.framework.util.UUIDHexUtil;
import com.h3c.portal.business.common.dto.QuartzTaskDTO;
import com.h3c.portal.business.common.util.QuartzJobCommon;
import com.h3c.portal.wbms.common.dto.SystimetaskDTO;
import com.h3c.portal.wbms.smmgmt.cronjob.service.ICronJobService;

/**
 * *********************************************************************
 *
 * SysLogServiceImpl.java
 *
 * H3C所有， 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * 
 * @copyright Copyright: 2015-2020
 * @creator lFW2156<br/>
 * @create-time 2016年1月23日 下午4:23:58
 * @revision $Id: *
 **********************************************************************
 */
@Service
public class CronJobServiceImpl extends ServiceSupport implements ICronJobService {
	// 用于控制立即生效的时间间隔
	private static Map<String, Date> mapOfTime = new HashMap<String, Date>();
	@Override
	public AjaxJson getCronJobInfoByCondition(String desc, Integer start, Integer limit) throws H3cException {
		StringBuffer sql = new StringBuffer(
				"select b.taskid,b.jobname,b.jobgroup,b.description,b.cron,b.classname,b.active,b.isstart,b.createdate,b.creator,b.updatedate,b.updator from (select a.taskid,a.jobname,a.jobgroup,a.description,a.cron,a.classname,a.active,a.isstart,date_format(a.createdate,'%Y-%m-%d %H:%i:%s') createdate,a.creator,date_format(a.updatedate,'%Y-%m-%d %H:%i:%s') updatedate,a.updator from systimetask a where 1=1");
		HashMap<String, Object> mp = new java.util.HashMap<String, Object>();
		if ((desc != null) && !"".equals(desc)) {
			sql.append(" and a.description like:description");
			mp.put("description", "%" + desc + "%");
		}
		sql.append(" ) b order by b.createdate desc");
		return this.pageSqlQuery(sql.toString(), mp, start, limit);
	}

	@Override
	public void saveCronJob(SystimetaskDTO dto) throws H3cException {
		if (dto != null) {
			Systimetask stk = new Systimetask();
			dto.setActive("1");// 有效
			dto.setIsstart("0");
			dto.setTaskid(UUIDHexUtil.generate32bit());
			CopyIgnoreProperty.copy(dto, stk);
			stk.setCreatedate(new Date());
			session.saveOrUpdate(stk);
		}

	}

	@Override
	public void updateCronJob(SystimetaskDTO dto) throws H3cException, ClassNotFoundException, ParseException,
			SchedulerException {
		String hql = " from Systimetask a where a.taskid = :taskid";
		Query query = session.createQuery(hql);
		query.setString("taskid", dto.getTaskid());
		Systimetask stk = (Systimetask) query.uniqueResult();
		stk.setCron(dto.getCron());
		stk.setDescription(dto.getDescription());
		stk.setJobgroup(dto.getJobgroup());
		stk.setJobname(dto.getJobname());
		stk.setUpdatedate(new Date());
		stk.setUpdator(dto.getUpdator());
		stk.setActive(dto.getActive());
		if ("0".equals(dto.getActive()) || !dto.getClassname().equals(stk.getClassname())) {// 无效或者改名，停止定时器
			if ("1".equals(stk.getIsstart())) {// 前提是在运行
				JobKey jobKey = JobKey.jobKey(stk.getJobname(), stk.getJobgroup());
				QuartzJobCommon.delete(jobKey);
			}
			stk.setClassname(dto.getClassname());
			stk.setIsstart("0");
		}
		session.update(stk);
		if ("1".equals(stk.getIsstart())) {// 前提是在运行
			doQuartz(stk);
		}

	}

	@Override
	public void deleteCronJob(String taskid) throws H3cException, SchedulerException {
		Query query = session.createQuery("from Systimetask where taskid = ?");
		Systimetask stk = (Systimetask) query.setParameter(0, taskid).uniqueResult();
		JobKey jobKey = JobKey.jobKey(stk.getJobname(), stk.getJobgroup());
		QuartzJobCommon.delete(jobKey);
		session.delete(stk);

	}

	// 立即执行
	@SuppressWarnings("unchecked")
	@Override
	public AjaxJson doActiveCronJob(String taskid) throws H3cException, SchedulerException {
		AjaxJson json = new AjaxJson();
		Query query = session.createQuery("from Systimetask where taskid = ?");
		Systimetask stk = (Systimetask) query.setParameter(0, taskid).uniqueResult();
		Date now = new Date();
		if ((mapOfTime.get("time") != null) && ((now.getTime() - mapOfTime.get("time").getTime()) < 5000)) {
			json.setSuccess(false);
			json.setMsg("两次生效间隔时间必须大于5秒！");
			return json;
		}
		try {
			Class<? extends Job> c = (Class<? extends Job>) Class.forName(stk.getClassname());
			// JobKey jobKey=JobKey.jobKey(stk.getJobname(), stk.getJobgroup());
			// 复杂定时器不支持startnow，所以这边通过先执行一次，然后删除，再创建执行doQuartz(stk);
			QuartzJobCommon.executeForOnce(c);
			// stk.setActive("1");
			if ("0".equals(stk.getIsstart())) {// 前提是定时器在停止状态
				doQuartz(stk);// 如果定时器处于停止状态，那么立即生效后不仅会立即执行一次，而且还会启动定时器
				stk.setIsstart("1");
				session.update(stk);
			}
			mapOfTime.put("time", new Date());
			return json;
		} catch (ClassNotFoundException e) {
			json.setSuccess(false);
			json.setMsg("没有对应的任务类，请重新编辑！");
			return json;
		} catch (ParseException e) {
			json.setSuccess(false);
			json.setMsg("cron表达式不正确，请重新编辑！");
			return json;
		}
	}

	@Override
	public AjaxJson startCronJob(String taskid) throws H3cException {
		AjaxJson json = new AjaxJson();
		Query query = session.createQuery("from Systimetask where taskid = ?");
		Systimetask stk = (Systimetask) query.setParameter(0, taskid).uniqueResult();
		try {
			doQuartz(stk);
			stk.setIsstart("1");
			session.update(stk);
			return json;
		} catch (ClassNotFoundException e) {
			json.setSuccess(false);
			json.setMsg("没有对应的任务类，请重新编辑！");
			return json;
		} catch (ParseException e) {
			json.setSuccess(false);
			json.setMsg("cron表达式不正确，请重新编辑！");
			return json;
		}
	}

	@Override
	public void stopCronJob(String taskid) throws H3cException, SchedulerException {
		Query query = session.createQuery("from Systimetask where taskid = ?");
		Systimetask stk = (Systimetask) query.setParameter(0, taskid).uniqueResult();
		// TriggerKey triggerKey =
		// TriggerKey.triggerKey(stk.getJobname(),stk.getJobgroup());
		JobKey jobKey = JobKey.jobKey(stk.getJobname(), stk.getJobgroup());
		QuartzJobCommon.shutdown(jobKey);
		stk.setIsstart("0");
		session.update(stk);
	}

	@SuppressWarnings("unchecked")
	private void doQuartz(Systimetask stk) throws ClassNotFoundException, ParseException {
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

	@Override
	public AjaxJson checkJobName(String jobName) throws H3cException {
		AjaxJson json = new AjaxJson();
		Query query = session.createQuery("from Systimetask where jobname = ?");
		Systimetask stk = (Systimetask) query.setParameter(0, jobName).uniqueResult();
		if (stk != null) {
			json.setSuccess(false);
			json.setMsg("任务名称已存在！");
			return json;
		}
		return json;

	}

	@SuppressWarnings("all")
	@Override
	public AjaxJson checkClassName(String className) throws H3cException {
		AjaxJson json = new AjaxJson();
		try {
			Class<? extends Job> c = (Class<? extends Job>) Class.forName(className);
			return json;
		} catch (ClassNotFoundException e) {
			json.setSuccess(false);
			json.setMsg("没有对应的任务类，请重新编辑！");
			return json;
		}
	}

}
