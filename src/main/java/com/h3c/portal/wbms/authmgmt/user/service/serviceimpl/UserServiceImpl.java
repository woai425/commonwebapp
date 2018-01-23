package com.h3c.portal.wbms.authmgmt.user.service.serviceimpl;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;
import com.h3c.framework.common.entities.Sysact;
import com.h3c.framework.common.entities.Sysdigestauth;
import com.h3c.framework.common.entities.Sysgroup;
import com.h3c.framework.common.entities.Sysuser;
import com.h3c.framework.common.entities.Sysusergroupref;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.util.CopyIgnoreProperty;
import com.h3c.framework.util.GlobalNames;
import com.h3c.framework.util.HashCodeUtil;
import com.h3c.framework.util.MD5;
import com.h3c.framework.util.ResourceUtil;
import com.h3c.framework.util.UUIDHexUtil;
import com.h3c.portal.wbms.authmgmt.user.service.IUserService;
import com.h3c.portal.wbms.common.dto.SysgroupDTO;
import com.h3c.portal.wbms.common.dto.SysuserDTO;


/***********************************************************************
*
* UserServiceImpl.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     lfw2082<br/>
* @create-time 2016年1月15日 上午11:09:24
* @revision    $Id:  *
***********************************************************************/
@Service
public class UserServiceImpl extends ServiceSupport implements IUserService {
	@Override
	@SuppressWarnings("unchecked")
	public List<SysgroupDTO> getMenuBack() throws H3cException {
		Query query = session.createQuery("from Sysgroup ");	
		List<Sysgroup> menus = query.list();
		List<SysgroupDTO> dtos = new ArrayList<SysgroupDTO>();
		for(Sysgroup sysGroup:menus){
			SysgroupDTO dto = new SysgroupDTO();
			CopyIgnoreProperty.copy(sysGroup, dto);
			dto.setFunctionid(dto.getGroupid());
			dto.setParent(dto.getParentid());
			dto.setTitle(dto.getName());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public Sysgroup getGroupInfo(String groupid) throws H3cException {
		
		Query query = session.createQuery("from Sysgroup where groupid = :groupid");
		query.setString("groupid", groupid);
		return (Sysgroup) query.uniqueResult();
	}

	@Override
	public AjaxJson getGroupUser(String groupid, String loginname, String username, String usertype, int start, int limit)
			throws H3cException {	
		StringBuffer hql = new StringBuffer("from Sysuser a where a.userid in "
				+ "(select sys.userid from Sysusergroupref sys where 1=1");
		HashMap<String, Object> mp = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(groupid)) {
			hql.append(" and sys.groupid = :groupid");
			mp.put("groupid", groupid);
		}
		hql.append(" )");
		if (!StringUtils.isEmpty(loginname)) {
			hql.append(" and a.loginname like :loginname");
			mp.put("loginname", "%"+loginname+"%");
		}
		if (!StringUtils.isEmpty(username)) {
			hql.append(" and a.username like :username");
			mp.put("username", "%"+username+"%");
		}
		if (!StringUtils.isEmpty(usertype)) {
			hql.append(" and a.usertype = :usertype");
			mp.put("usertype", usertype);
		}
		return this.pageHqlQuery(hql.toString(), mp, start, limit);
	}

	@Override
	public Sysuser getSysuser(String userid) throws H3cException {
		Query query = session.createQuery("from Sysuser a where a.userid = :userid");
		query.setString("userid", userid);
		return (Sysuser) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteUser(String userid,String groupid) throws H3cException {

		
		//1、删除用户
		String loginname = null;
		if(userid!=null&&!"".equals(userid)){
			Sysuser user = this.getSysuser(userid);
			if(user!=null){
				loginname = user.getLoginname();
				session.delete(user);
			}
		}else{
			throw new H3cException("删除用户失败：userid为空");
		}
				
		//2、删除用户和组之间的关系
		Query queryGroup = session.createQuery("from Sysusergroupref a where a.userid=:userid");
		queryGroup.setString("userid", userid);
		Sysusergroupref ref = (Sysusergroupref)queryGroup.uniqueResult();			
		if(ref!=null){
			session.delete(ref);
		}
				
		//3、删除用户的权限关联信息
		Query query = session.createQuery("from Sysact a where a.objectid=:objectid and a.objecttype='0' ");
		query.setString("objectid", userid);
		List<Sysact> actLst = query.list();
		for(Sysact act : actLst){
			if(act!=null){
				session.delete(act);
			}
		}
		
		//4、删除系统摘要认证密码核对表
		if(!StringUtils.isEmpty(loginname)){
			Sysdigestauth digestAuth = (Sysdigestauth)session.get(Sysdigestauth.class, loginname);
			if(digestAuth!=null){
				session.delete(digestAuth);
			}
		}
	}
	/**
	 * 检查密码强度
	 * @param pwd
	 * @return
	 */
	private boolean pswStrengthCheck(String pwd){
		int maths=0, smalls=0, bigs=0, corps=0, count=0;
		String numReg=".*\\d.*";//判断是否含数字
		String smallReg =".*[a-z].*";//判断是否含小写字母
		String bigReg = ".*[A-Z].*";//判断是否含大写字母
		String corpsReg = "[a-zA-Z0-9]";
		if(pwd!=null){
			maths = pwd.matches(numReg)?1:0;//含数字，则为1，不含则为0
			smalls = pwd.matches(smallReg)?1:0;//含小写字母，则为1，不含则为0
			bigs = pwd.matches(bigReg)?1:0;//含大写字母，则为1，不含则为0
			String[] arrayPwd = pwd.split("");
			for(int i=1;i<arrayPwd.length;i++){//从1开始的原因是第一个字符是空的
				if(!arrayPwd[i].matches(corpsReg)){//只要出现一个非a-zA-Z0-9的字符，就将corps设为1，否则为0
					corps=1;
					break; 
				}
			}
			count = maths+smalls+bigs+corps;//相加
			if(pwd.length()>=6&&pwd.length()<=8&&count>=2){//如果密码长度在6-8之间并且count>=2时，认为是中等强度
				return true;
			}
			if(pwd.length()>8&&count>=1){//如果密码长度在大于8，并且count>=1时，认为是中等强度
				return true;
			}
		}		
		return false;
	}
	@Override
	public void updateUser(SysuserDTO dto) throws H3cException {
		if(dto==null){
			throw new H3cException("修改用户失败：传入空信息！");
		}
		Sysuser user = (Sysuser)session.get(Sysuser.class, dto.getUserid());
		String pswStrengthCheck = GlobalNames.sysConfig.get("PSW_STRENGTH_CHECK");
		
		if(user!=null){
			//修改密码时，进行强度校验
			if("true".equals(pswStrengthCheck)&&!user.getPasswd().equals(dto.getPasswd())){
				String pwd = dto.getPasswd();
				if(!pswStrengthCheck(pwd)){
					throw new H3cException("密码强度必须中级以上！");
				}
			}
			//修改密码时，进行密码长度校验
			if((dto.getPasswd().length() > 20) && !(user.getPasswd().equals(dto.getPasswd()))){
				throw new H3cException("密码长度不能超过20个字符！");
			}
			
			try {
				Date createdate = user.getCreatedate();
				//用户的锁定状态
				String isLock= user.getAcctlockflag();
				//用户的密码失效时间
				Date pswinvalidtime = user.getPswinvalidtime();
				CopyIgnoreProperty.copy(dto, user);//在管理员修改用户时，使用了CopyIgnoreProperty.copy()方法，因此很多属性会被置为空
				if(user.getPasswd().length() <= 20){
					user.setPasswd(MD5.md5(dto.getPasswd()));
				}
				user.setCreatedate(createdate);
				user.setAcctlockflag(isLock);
				user.setPswinvalidtime(pswinvalidtime);
				//修改密码时，进行密码有效期处理		
				if(!user.getPasswd().equals(dto.getPasswd())){	
					String pswTmoutCntl = GlobalNames.sysConfig.get("PSW_TMOUT_CNTL");	
					//判断是否开启密码有效性控制
					if(pswTmoutCntl!=null&&!"0".equals(pswTmoutCntl)){									
						Calendar ca=Calendar.getInstance();
						ca.setTime(new Date());			
						ca.add(Calendar.DATE,Integer.parseInt(pswTmoutCntl));
						user.setPswinvalidtime(ca.getTime());
					}else if(pswTmoutCntl!=null&&"0".equals(pswTmoutCntl)){
						//如果没有开启，那么在修改密码后，不管pswinvalidtime之前有没有值，都会被置为null
						user.setPswinvalidtime(null);
					}
				}
			} catch (H3cException e) {
				
			}
			session.update(user);
			//在Sysdigestauth表里添加数据
			if(dto.getPasswd().length() <= 20){
				Sysdigestauth digestAuth = new Sysdigestauth();
				digestAuth.setLoginname(user.getLoginname());
				digestAuth.setPasswd(MD5.md5(dto.getPasswd()));
				String digestPasswd = null;
				try {
					digestPasswd = new String(Base64.encode((dto.getPasswd()+":"+MD5.md5(dto.getPasswd())).getBytes("UTF-8")),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				digestAuth.setDigestpasswd(digestPasswd);
				session.saveOrUpdate(digestAuth);
			}
		}
		if("1".equals(dto.getLeaveFlag())){
			Query query = session.createQuery("from Sysusergroupref a where a.groupid=:groupid and a.userid=:userid");
			query.setString("groupid", dto.getGroupid());
			query.setString("userid", dto.getUserid());
			Sysusergroupref ref = (Sysusergroupref)query.uniqueResult();			
			if(ref!=null){
				session.delete(ref);
			}
		}
	}
	
	public String addUser(SysuserDTO dto) throws H3cException {		
		if(dto!=null){
			Sysuser user = new Sysuser();
			try {
				CopyIgnoreProperty.copy(dto, user);				
				if (user.getOwner() == null) {// 是否指定持有者
					user.setOwner(ResourceUtil.getSessionUser().getUser().getLoginname());// 默认为当前用户
				} 
				//判断登录名是否出现重复
				Query query = session.createQuery("from Sysuser a where a.loginname=:loginname");
				query.setString("loginname", user.getLoginname());
				if(query.list().size()>0){
					throw new H3cException("该登录名在系统中已经存在！");
				}
				
				Sysgroup group = (Sysgroup)session.get(Sysgroup.class, dto.getGroupid());
				if(group==null){
					throw new H3cException("该组织不存在，请确认！");
				}
				
				String passwd = MD5.md5(GlobalNames.sysConfig.get("USER_DEFAULT_PASSWORD")==null?user.getLoginname():GlobalNames.sysConfig.get("USER_DEFAULT_PASSWORD"));
				user.setUserid(HashCodeUtil.getUserId(user.getLoginname(), user.getUsername()));
				user.setPasswd(passwd);			
				user.setCreatedate(new java.util.Date());
				user.setUseful("1");
				user.setUsertype(dto.getUsertype()==null?"2":dto.getUsertype());
				String pswTmoutCntl = GlobalNames.sysConfig.get("PSW_TMOUT_CNTL");
				//判断是否开启密码有效性控制
				if(pswTmoutCntl!=null&&!"0".equals(pswTmoutCntl)){
					Calendar ca=Calendar.getInstance();
					ca.setTime(new Date());
					ca.add(Calendar.DATE,Integer.parseInt(pswTmoutCntl));
					user.setPswinvalidtime(ca.getTime());
				}				
				session.save(user);
				
				Sysusergroupref ref = new Sysusergroupref();
				ref.setGroupid(dto.getGroupid());
				ref.setIsleader(dto.getIsleader());
				ref.setUsergroupid(HashCodeUtil.getUserGroupId());
				ref.setUserid(user.getUserid());
				session.save(ref);
				
				//在Sysact表里添加数据
				Sysact sys = new Sysact();
				String roleid = (String) session.createSQLQuery("select roleid from sysrole where rolename='普通用户'").uniqueResult();
				sys.setActid(HashCodeUtil.getSysAclId(roleid));
				sys.setObjectid(user.getUserid());
				sys.setRoleid(roleid);
				sys.setObjecttype("0");
				sys.setDispatchauth("0");
				session.save(sys);
				
				//在Sysdigestauth表里添加数据
				Sysdigestauth digestAuth = new Sysdigestauth();
				digestAuth.setLoginname(user.getLoginname());
				digestAuth.setPasswd(passwd);
				String digestPasswd = null;
				try {
					digestPasswd = new String(Base64.encode(((GlobalNames.sysConfig.get("USER_DEFAULT_PASSWORD")==null?user.getLoginname():GlobalNames.sysConfig.get("USER_DEFAULT_PASSWORD"))+":"+passwd).getBytes("UTF-8")),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				digestAuth.setDigestpasswd(digestPasswd);
				session.save(digestAuth);
				
				return user.getUserid();
			} catch (H3cException e) {
				throw new H3cException("用户信息增加出错："+e.getMessage());
			}
		}else{
			throw new H3cException("用户信息为空");
		}
		
	}
	
	@Override
	public AjaxJson getAllUser(String groupid, String loginname,int start,int limit) throws H3cException {
		StringBuffer hql = new StringBuffer("from Sysuser a where a.userid not in "
				+ "(select sys.userid from Sysusergroupref sys where 1=1 ");
		HashMap<String, Object> mp = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(groupid)) {
			hql.append(" and sys.groupid = :groupid");
			mp.put("groupid", groupid);
		}
		hql.append(")");
		if (!StringUtils.isEmpty(loginname)) {
			hql.append(" and a.loginname = :loginname");
			mp.put("loginname", loginname);
		}
		return this.pageHqlQuery(hql.toString(), mp, start, limit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addUserToGroup(String userData,String groupid) throws H3cException {	
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String,Object>> dtoLst = mapper.readValue(userData, List.class);
			for (int i = 0; i < dtoLst.size(); i++) {
				Map<String,Object> jObj = dtoLst.get(i);
				if (Boolean.parseBoolean(jObj.get("check").toString())) {
					if(this.getSysusergroupref(jObj.get("userid").toString()) == null){
						Sysusergroupref sysGroup = new Sysusergroupref();
						sysGroup.setUsergroupid(UUIDHexUtil.generate32bit());
						sysGroup.setUserid(jObj.get("userid").toString());
						sysGroup.setGroupid(groupid);
						sysGroup.setIsleader((jObj.get("isleader")==null)||("null".equals(jObj.get("isleader")))? "":jObj.get("isleader").toString());
						session.save(sysGroup);
					}else{
						Sysusergroupref sysGroup = this.getSysusergroupref(jObj.get("userid").toString());
						sysGroup.setGroupid(groupid);
						session.saveOrUpdate(sysGroup);
					}
				}
			}
		} catch (IOException e) {
			throw new H3cException(e.getMessage());
		}
	}

	@Override
	public Sysusergroupref getSysusergroupref(String userid)
			throws H3cException {
		Query query = session.createQuery("from Sysusergroupref a where a.userid = :userid");
		query.setString("userid", userid);
		return (Sysusergroupref) query.uniqueResult();
	}

	@Override
	public void unlockUser(String userid) throws H3cException {
		Sysuser user = getSysuser(userid);
		if(user!=null){
			user.setAcctlockflag("0");//解锁
			session.update(user);
		}
		
	}

	@Override
	public void lockUser(String userid) throws H3cException {
		Sysuser user = getSysuser(userid);
		if(user!=null){
			user.setAcctlockflag("1");//加锁
			session.update(user);
		}		
	}

}
