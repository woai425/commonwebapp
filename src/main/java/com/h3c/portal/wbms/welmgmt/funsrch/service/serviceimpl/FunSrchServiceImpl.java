package com.h3c.portal.wbms.welmgmt.funsrch.service.serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.entities.Sysfunction;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.portal.wbms.welmgmt.funsrch.service.IFunSrchService;
/**
 * *********************************************************************
 *
 * FunSrchServiceImpl.java
 * 首页面功能模糊搜索接口实现类
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月19日 下午3:47:44
 * @revision    $Id:  *
 **********************************************************************
 */
@Service
public class FunSrchServiceImpl extends ServiceSupport implements IFunSrchService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysfunction> getSysFunction(String funName) throws H3cException {
		if(!StringUtils.isEmpty(funName)){
			Query query = session.createQuery("from Sysfunction a where a.active='1' and a.type='1' and exists (select 1 from Sysfunction b where b.functionid=a.parent and b.active='1') and a.title like :title");
			query.setString("title", "%"+funName+"%");
			List<Sysfunction> lst = query.list();
			return lst;
		}else{
			return null;
		}

	}

}
