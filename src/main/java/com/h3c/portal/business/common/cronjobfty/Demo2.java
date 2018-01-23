package com.h3c.portal.business.common.cronjobfty;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.h3c.framework.core.support.ServiceSupport;

public class Demo2 extends ServiceSupport implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//如果要使用session，那要在该方法上添加@Transactional(propagation = Propagation.REQUIRED)
		/*WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		ICronJobService service = ctx.getBean(ICronJobService.class);
		try {
			AjaxJson aj=service.checkJobName("");
			System.out.println(aj.isSuccess());
		} catch (H3cException e) {
			System.out.println(2222);
			e.printStackTrace();
		}*/

	}

}
