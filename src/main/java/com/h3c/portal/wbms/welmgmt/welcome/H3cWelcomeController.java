package com.h3c.portal.wbms.welmgmt.welcome;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.core.support.ControllerSupport;
import com.h3c.portal.wbms.common.dto.MemoryDTO;
import com.sun.management.OperatingSystemMXBean;

/**
 * *********************************************************************
 * 非管理人员的欢迎页面controller
 * WelcomeController.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年10月21日 下午3:35:23
 * @revision    $Id:  *
 **********************************************************************
 */
@Controller
@RequestMapping(value = "/h3cWelcomeController")
public class H3cWelcomeController extends ControllerSupport<Object> {

	private static final int KB = 1024;

	@RequestMapping(params="refreshData")
	@ResponseBody
	@SuppressWarnings("restriction")
	public AjaxJson refreshData(){
		AjaxJson json = new AjaxJson();
		//======================获取虚拟机的内存================================
		MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
		//堆内存
		MemoryUsage usage = memorymbean.getHeapMemoryUsage();
		//非堆内存
		MemoryUsage usage2 = memorymbean.getNonHeapMemoryUsage();
		
		MemoryDTO memoryInfo = new MemoryDTO();
		BigDecimal b = null;
		
		//总jvm内存
		double jvm_totalMemory = (double)(usage.getInit()) / (KB*KB);
		b = new BigDecimal(jvm_totalMemory);
		memoryInfo.setJvm_totalMemory(b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
		
		//已使用jvm内存
		double jvm_usedMemory = (double)(usage.getUsed()+usage2.getUsed()) / (KB*KB);
		b = new BigDecimal(jvm_usedMemory);
		memoryInfo.setJvm_usedMemory(b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
		
		//剩余jvm内存
		b = new BigDecimal(jvm_totalMemory - jvm_usedMemory);
		memoryInfo.setJvm_freeMemory(b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
		
		//内存使用率
		double jvm_usedRatio = ((jvm_usedMemory*100)/jvm_totalMemory);
		b = new BigDecimal(jvm_usedRatio);  
		memoryInfo.setJvm_usedRatio(b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue()); 
		
		//======================获取系统的内存================================
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		// 操作系统
//		String osName = System.getProperty("os.name");
		// 总的物理内存
		double sys_totalMemory = (double)osmxb.getTotalPhysicalMemorySize() / (KB*KB);
		b = new BigDecimal(sys_totalMemory);
		memoryInfo.setSys_totalMemory(b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
		
		// 剩余的物理内存
		double sys_freeMemory = (double)osmxb.getFreePhysicalMemorySize() / (KB*KB);
		b = new BigDecimal(sys_freeMemory);
		memoryInfo.setSys_freeMemory(b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
		
		//已使用的物理内存
		b = new BigDecimal(sys_totalMemory - sys_freeMemory);
		memoryInfo.setSys_usedMemory(b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
		
		//内存使用率
		double sys_usedRatio = (((sys_totalMemory - sys_freeMemory)*100)/sys_totalMemory);
		b = new BigDecimal(sys_usedRatio);  
		memoryInfo.setSys_usedRatio(b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue()); 
		
		json.setData(memoryInfo);
		return json;
	}
}
