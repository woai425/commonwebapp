package com.h3c.portal.wbms.authmgmt.resource.service.serviceimpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.entities.Sysfunction;
import com.h3c.framework.common.entities.Sysroleacl;
import com.h3c.framework.core.persistence.PageCacheManager;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.util.CopyIgnoreProperty;
import com.h3c.framework.util.HashCodeUtil;
import com.h3c.portal.wbms.authmgmt.resource.service.IResourceService;
import com.h3c.portal.wbms.common.dto.SysfunctionDTO;
/**
 * *********************************************************************
*
* ResourceServiceImpl.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年1月6日 下午5:43:57
* @revision    $Id:  1.0
**********************************************************************
 */
@Service
public class ResourceServiceImpl extends ServiceSupport implements IResourceService {
	
	@Autowired
	private PageCacheManager cache;

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysfunction> getMenuBack() {		
		Query query = session.createQuery("from Sysfunction order by orderno asc");		
		List<Sysfunction> menus = query.list();
		
		return menus;
	}

	@Override
	public SysfunctionDTO getNodeInfo(String functionid) throws H3cException {
		Query query = session.createQuery("from Sysfunction where functionid = ?");
		Sysfunction node = (Sysfunction) query.setParameter(0, functionid).uniqueResult();
		if(node != null){
			SysfunctionDTO dto = new SysfunctionDTO();
			CopyIgnoreProperty.copy(node, dto);
			dto.setCreatedate(dto.getCreatedate() + " " + String.format("%tT", node.getCreatedate()));
			return dto;
		}else
			return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveNode(SysfunctionDTO dto) throws H3cException {
		Sysfunction sys_parent = (Sysfunction) session.get(Sysfunction.class, dto.getParent());
		if (sys_parent.getType().equals("0") && dto.getType().equals("2")) {// 如果父节点是
			// 节点，则下面不允许存在按钮
			throw new H3cException("菜单节点下不允许直接建立按钮节点");
		}
		if (sys_parent.getType().equals("1")) {// 如果父节点是叶子，则下面只能存放按钮，其他都异常
			if (dto.getType().equals("0")) {
				throw new H3cException("叶子节点下不允许建立菜单节点！");
			}
			if (dto.getType().equals("1")) {
				throw new H3cException("叶子节点下不允许建立叶子节点！");
			}
		}
		if (sys_parent.getType().equals("2")) {// 如果父节点是按钮，则下面只能存放按钮，其他都异常
			if (dto.getType().equals("0")) {
				throw new H3cException("按钮节点下不允许建立菜单节点！");
			}
			if (dto.getType().equals("1")) {
				throw new H3cException("按钮节点下不允许建立叶子节点！");
			}
		}
		
		List<Sysfunction> sysFunctions = this.getFunctionChild(dto.getFunctionid());
		//对子节点进行验证
		if(sysFunctions != null&&sysFunctions.size() >0){
			int countType0 = 0,countType1 = 0,countType2 = 0;
			for(Sysfunction sys:sysFunctions){
				if("0".equals(sys.getType())){
					countType0 ++;
				}else if("1".equals(sys.getType())){
					countType1++;
				}else if("2".equals(sys.getType())){
					countType2++;
				}
			}
			if("0".equals(dto.getType())){
				if(countType2 > 0){
					throw new H3cException("菜单节点下不允许直接建立按钮节点");
				}
			}
			if("1".equals(dto.getType())){
				if(countType0 > 0){
					throw new H3cException("叶子节点下不允许建立菜单节点！");
				}else if(countType1 >0){
					throw new H3cException("叶子节点下不允许建立叶子节点");
				}
			}
			if("2".equals(dto.getType())){
				if(countType0 > 0||countType1 >0||countType2 >0){
					throw new H3cException("按钮下面不允许建立其他！");
				}
			}
		}
		
		if("2".equals(dto.getType())){
			Query queryForBID = session.createQuery("from Sysfunction a where a.buttonid=:buttonid");
			queryForBID.setString("buttonid", dto.getButtonid());
			if (dto.getFunctionid() == null || "".equals(dto.getFunctionid())){
				// 新增判断
		    	if (queryForBID.list().size() > 0) {
		    		throw new H3cException("数据库中已经存在相同的buttonID，请确认输入是否正确！");
		    	}
			}else{
				// 新增判断
		    	List<Sysfunction> lstForBID = queryForBID.list();
		    	if (lstForBID.size() > 1) {
		    		throw new H3cException("数据库中已经存在相同的buttonID，请确认输入是否正确！");
		    	}
		    	if (lstForBID.size() == 1 &&!dto.getFunctionid().equals(lstForBID.get(0).getFunctionid())) {
		    		throw new H3cException("数据库中已经存在相同的buttonID，请确认输入是否正确！");
		    	}
			}
		}
		
		// 判断URL是否存在重复
		Query query = session.createQuery("from Sysfunction a where a.location=:location");
		query.setString("location", dto.getLocation());
		synchronized(this){
			if (dto.getFunctionid() == null || "".equals(dto.getFunctionid())) {
		    	if (query.list().size() > 0) {
		    		throw new H3cException("数据库中已经存在相同的URL地址，请确认输入的URL是否正确！");
		    	}
		    	Sysfunction sys = new Sysfunction();
		    	CopyIgnoreProperty.copy(dto, sys);
		    	sys.setFunctionid(HashCodeUtil.getSysFunctionId(sys.getParent(), sys.getLocation(), sys.getTitle()));
		    	sys.setCreatedate(new Date());
		    	if("0".equals(sys.getType())&&"panel".equals(sys.getIconcls())){
		    		sys.setIconcls(null);//节点都选默认的图片
		    	}
		    	session.save(sys);
		        if("1".equals(sys.getCache())){
		            cache.addPageCache(sys.getFunctionid(), sys.getLocation());
		        }
		   }else{
		    	List<Sysfunction> lst = query.list();
		    	if (lst.size() == 1 &&!dto.getFunctionid().equals(lst.get(0).getFunctionid())) {
		    		throw new H3cException("数据库中已经存在相同的URL地址，请确认输入的URL是否正确！");
		    	}
		    	if (lst.size() > 1) {
		    		throw new H3cException("数据库中已经存在相同的URL地址，请确认输入的URL是否正确！");
		    	}
		    	Sysfunction sys = (Sysfunction) session.get(Sysfunction.class, dto.getFunctionid());
		    	Date createdate = sys.getCreatedate();
		    	CopyIgnoreProperty.copy(dto, sys);
		    	sys.setCreatedate(createdate);
		    	if("0".equals(sys.getType())&&"panel".equals(sys.getIconcls())){
		    		sys.setIconcls(null);//节点都选默认的图片
		    	}
		    	session.update(sys);
		    	if("1".equals(sys.getCache())){
		            cache.addPageCache(sys.getFunctionid(), sys.getLocation());
		        }else{
		            cache.removePageCache(sys.getFunctionid());
		        }
		    }
		}	
	}
	
	@SuppressWarnings("unchecked")
	private void recursiveDeleteSysfunion(String functionid) throws H3cException {
		Query query = session.createQuery("from Sysfunction a where a.parent = :functionid");
		query.setString("functionid", functionid);
		List<Sysfunction> lst = query.list();
		if(lst==null||lst.size()==0){
			return ;
		}
		for(Sysfunction sysFun : lst){
			recursiveDeleteSysfunion(sysFun.getFunctionid());
			session.delete(sysFun);
			System.out.println("delete title = "+sysFun.getTitle()+" location = "+sysFun.getLocation());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void deleteNode(String functionid) throws H3cException {
		//1、删除当前节点信息
		Query query = session.createQuery("from Sysfunction where functionid = ?");
		Sysfunction node = (Sysfunction) query.setParameter(0, functionid).uniqueResult();
		session.delete(node);
		
		//2、删除节点下所有的叶子
		recursiveDeleteSysfunion(functionid);
				
		//3、删除所有角色存在此功能ID的信息
		List<Sysroleacl> lst = session.createQuery("from Sysroleacl a where a.functionid=:functionid").
				setString("functionid", functionid).list();
		for(Sysroleacl acl : lst){
			session.delete(acl);
		}
		cache.removePageCache(functionid);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysfunction> getFunctionChild(String functionid)
			throws H3cException {
		Query query = session.createQuery("from Sysfunction a where a.parent = :functionid");
		query.setString("functionid", functionid);
		List<Sysfunction> lst = query.list();
		if(lst==null||lst.size()==0){
			return null;
		}else
			return lst;
	}

}
