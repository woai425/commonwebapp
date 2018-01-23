package com.h3c.portal.business.common.customdispatchpage.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.web.security.auth.service.ICustomDispatchPageService;

/**
 * *********************************************************************
 * 客户端首页面定制，返回客户端自定义的首页面 <br/>
 * CustomDispatchPageServiceImpl.java <br/>
 *
 * H3C所有，<br/>
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。<br/>
 *
 * @copyright Copyright: 2015-2020
 * @creator z10926 <br/>
 * @create-time 2016年11月17日 上午10:38:55
 * @revision $Id: *
 **********************************************************************
 */
@Service
public class CustomDispatchPageServiceImpl extends ServiceSupport implements ICustomDispatchPageService {

	@Override
	public String getCustomDispatchPage(String userId, String loginName) throws H3cException {

		return null;
	}

}
