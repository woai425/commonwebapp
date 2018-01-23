package com.h3c.portal.wbms.smmgmt.opermon.service.serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.portal.wbms.smmgmt.opermon.service.IOperMonService;
@Service
public class OperMonServiceImpl implements IOperMonService{

	private AtomicInteger groupCount = new AtomicInteger(0);
	private AtomicInteger userCount = new AtomicInteger(0);
	private AtomicInteger roleCount = new AtomicInteger(0);
	private AtomicInteger grantCount = new AtomicInteger(0);
	private AtomicInteger resourceCount = new AtomicInteger(0);
	private AtomicInteger ddprmCount = new AtomicInteger(0);
	private AtomicInteger sysprmCount = new AtomicInteger(0);
	private AtomicInteger timeCount = new AtomicInteger(0);
	private AtomicInteger monitorCount = new AtomicInteger(0);
	
	@Override
	public void operCount(String flag) throws H3cException {
		if (flag.equals("group")) {
			groupCount.getAndIncrement();
		}else if(flag.equals("user")){
			userCount.getAndIncrement();
		}else if(flag.equals("role")){
			roleCount.getAndIncrement();
		}else if(flag.equals("grant")){
			grantCount.getAndIncrement();
		}else if(flag.equals("resource")){
			resourceCount.getAndIncrement();  
		}else if(flag.equals("ddprm")){
			ddprmCount.getAndIncrement();
		}else if(flag.equals("time")){
			timeCount.getAndIncrement();
		}else if(flag.equals("monitor")){
			monitorCount.getAndIncrement();
		}else{
			sysprmCount.getAndIncrement();
		}
		
	}

	@Override
	public Map<String,Integer> getOperMonInfo() throws H3cException {
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("group", groupCount.get());
        map.put("user", userCount.get());
        map.put("role", roleCount.get());
        map.put("grant", grantCount.get());
        map.put("resource", resourceCount.get());
        map.put("ddprm", ddprmCount.get());
        map.put("sysprm", sysprmCount.get());
        map.put("time", timeCount.get());
        map.put("monitor", monitorCount.get());
		return map;
	}
	
}
