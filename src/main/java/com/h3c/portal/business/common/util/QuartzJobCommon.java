package com.h3c.portal.business.common.util;

import java.text.ParseException;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.h3c.portal.business.common.dto.QuartzTaskDTO;



/**
 * *********************************************************************
 * 定时任务公共类
 * QuartzTaskCommon.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月26日 上午11:26:41
 * @revision    $Id:  *
 **********************************************************************
 */
public class QuartzJobCommon {
	private static WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
	private static Scheduler scheduler = (Scheduler) ctx.getBean("scheduler");
	/**
	 * 根据传入参数创建定时任务,如果任务已经则进行相应的修改
	 * “*”字符代表所有可能的值因此，“*”在子表达式（月）里表示每个月的含义，“*”在子表达式（天（星期））表示星期的每一天
	 * ? 表示不指定值
	 * /”字符用来指定数值的增量例如：在子表达式（分钟）里的“0/15”表示从第0分钟开始，每15分钟 ;在子表达式（分钟）里的“3/20”表示从第3分钟开始，每20分钟（它和“3，23，43”）的含义一样
	 * 秒 分 时 日 月 周 年
	 * 例子：
	 * 0/5 * * * * ? * 每隔5秒执行一次
	 * 0 0 0/6 * * ? * 每天每隔6小时执行一次
	 * 0 0 0/1 * * ? * 每天每隔1小时执行一次
	 * 0 0/30 * * * ? * 每天每隔30分钟执行一次
     * 0 0/1 * * * ? * 每天每隔1分钟执行一次
	 * @param job
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void createOrUpdateTask(QuartzTaskDTO job) throws SchedulerException, ParseException {
		{	
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),
					job.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler
					.getTrigger(triggerKey); // 不存在，创建一个
			if (null == trigger) {
				JobDetail jobDetail = JobBuilder
						.newJob(job.getQuartzFactory())
						.withIdentity(job.getJobName(), job.getJobGroup())
						.build();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				
				// 表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(job.getCronExpression());
				//错过的任务不再执行
				scheduleBuilder.withMisfireHandlingInstructionDoNothing();
				// 按新的cronExpression表达式构建一个新的trigger
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(job.getJobName(), job.getJobGroup())
						.withSchedule(scheduleBuilder).build();	
				scheduler.scheduleJob(jobDetail, trigger);
			} else { // Trigger已存在，那么更新相应的定时设置 //表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(job.getCronExpression());
				//错过的任务不再执行
				scheduleBuilder.withMisfireHandlingInstructionDoNothing();
				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder()
						.withIdentity(triggerKey)
						.withSchedule(scheduleBuilder).build();
				
				// 按新的trigger重新设置job执行
				scheduler.rescheduleJob(triggerKey, trigger);
			}	
		}
	}		
	
	public static void shutdown(JobKey jobKey) throws SchedulerException{		
			//scheduler.pauseJob(jobKey);
			scheduler.deleteJob(jobKey);
	}
	public static void delete(JobKey jobKey) throws SchedulerException{
			scheduler.deleteJob(jobKey);
	}
	//开始后立即执行，只执行一次
	@SuppressWarnings("all")
	public static void executeForOnce(Class<? extends Job> c) throws ParseException, SchedulerException{				
		JobKey jobKey  = 	JobKey.jobKey("for once", "once");//随便定义
		scheduler.deleteJob(jobKey);//目的是在每次执行前删除原来的，（因为触发一次，马上删除，可能会导致没触发）。就算之前没有jobDetail，也不会报错
		JobDetail jobDetail = JobBuilder
				.newJob(c)
				.withIdentity(jobKey.getName(), jobKey.getGroup())
				.build();
		//CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		//scheduleBuilder.withMisfireHandlingInstructionDoNothing();
		scheduler.addJob(jobDetail, true);
		scheduler.triggerJob(jobKey);	//没有delete，那么这个job会一直存在			
	}	
}
