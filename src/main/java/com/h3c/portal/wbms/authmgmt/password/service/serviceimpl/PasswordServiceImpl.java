package com.h3c.portal.wbms.authmgmt.password.service.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bouncycastle.util.encoders.Base64;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.entities.Sysdigestauth;
import com.h3c.framework.common.entities.Sysgroup;
import com.h3c.framework.common.entities.Sysuser;
import com.h3c.framework.core.support.ServiceSupport;
import com.h3c.framework.util.GlobalNames;
import com.h3c.framework.util.MD5;
import com.h3c.portal.wbms.authmgmt.password.service.IPasswordService;
import com.h3c.portal.wbms.authmgmt.password.util.TreeBuilder;
import com.h3c.portal.wbms.common.dto.SysuserDTO;

@Service
public class PasswordServiceImpl extends ServiceSupport implements IPasswordService {

	@Override
	public String getGroupInfo() throws H3cException {
		List<Sysgroup> groupList = this.getGroupList();
		try {
			return TreeBuilder.getInstance().getMenu(groupList);
		} catch (H3cException e) {
			throw new H3cException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysgroup> getGroupList() throws H3cException {
		String hql = "from Sysgroup";
		Query query = session.createQuery(hql);
		return query.list();
	}
	private boolean pswStrengthCheck(String pwd){
		int maths=0, smalls=0, bigs=0, corps=0, count=0;
		String numReg=".*\\d.*";
		String smallReg =".*[a-z].*";
		String bigReg = ".*[A-Z].*";
		String corpsReg = "[a-zA-Z0-9]";
		if(pwd!=null){
			maths = pwd.matches(numReg)?1:0;
			smalls = pwd.matches(smallReg)?1:0;
			bigs = pwd.matches(bigReg)?1:0;
			String[] arrayPwd = pwd.split("");
			for(int i=1;i<arrayPwd.length;i++){//从1开始的原因是第一个字符是空的
				if(!arrayPwd[i].matches(corpsReg)){
					corps=1;
					break; 
				}
			}
			count = maths+smalls+bigs+corps;
			if(pwd.length()>=6&&pwd.length()<=8&&count>=2){
				return true;
			}
			if(pwd.length()>8&&count>=1){
				return true;
			}
		}		
		return false;
	}
	@Override
	public void updatePassword(SysuserDTO dto)throws H3cException{
		if(dto==null){
			throw new H3cException("修改用户失败：传入空信息！");
		}
		Sysuser user = (Sysuser)session.get(Sysuser.class, dto.getUserid());
		String pswStrengthCheck = GlobalNames.sysConfig.get("PSW_STRENGTH_CHECK");
		if(user!=null){
			//修改密码，并且开启强度校验时，进行强度校验
			if("true".equals(pswStrengthCheck)&&!user.getPasswd().equals(dto.getPasswd())){
				String pwd = dto.getPasswd();
				if(!pswStrengthCheck(pwd)){
					throw new H3cException("密码强度必须中级以上！");
				}
			}
			
			if(dto.getPasswd().length() <= 20){
				user.setPasswd(MD5.md5(dto.getPasswd()));
			}
			//修改密码时，进行密码有效期处理		
			if(!user.getPasswd().equals(dto.getPasswd())){	
				String pswTmoutCntl = GlobalNames.sysConfig.get("PSW_TMOUT_CNTL");
				//当开启密码有效性控制时
				if(pswTmoutCntl!=null&&!"0".equals(pswTmoutCntl)){									
					Calendar ca=Calendar.getInstance();
					ca.setTime(new Date());			
					ca.add(Calendar.DATE,Integer.parseInt(pswTmoutCntl));
					user.setPswinvalidtime(ca.getTime());
				}else if(pswTmoutCntl!=null&&"0".equals(pswTmoutCntl)){
					user.setPswinvalidtime(null);
				}
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
	}
	
	@Override
	public void updateUser(SysuserDTO dto) throws H3cException {
		if(dto==null){
			throw new H3cException("修改用户失败：传入空信息！");
		}
		Sysuser user = (Sysuser)session.get(Sysuser.class, dto.getUserid());
		String pswStrengthCheck = GlobalNames.sysConfig.get("PSW_STRENGTH_CHECK");
		if(user!=null){
			//修改密码，并且开启强度校验时，进行强度校验
			if("true".equals(pswStrengthCheck)&&!user.getPasswd().equals(dto.getPasswd())){
				String pwd = dto.getPasswd();
				if(!pswStrengthCheck(pwd)){
					throw new H3cException("密码强度必须中级以上！");
				}
			}
			Date createdate = user.getCreatedate();
			user.setLoginname(dto.getLoginname());
			user.setUsername(dto.getUsername());
			if(dto.getPasswd().length() <= 20){
				user.setPasswd(MD5.md5(dto.getPasswd()));
			}
			//修改密码时，进行密码有效期处理		
			if(!user.getPasswd().equals(dto.getPasswd())){	
				String pswTmoutCntl = GlobalNames.sysConfig.get("PSW_TMOUT_CNTL");
				//当开启密码有效性控制时
				if(pswTmoutCntl!=null&&!"0".equals(pswTmoutCntl)){									
					Calendar ca=Calendar.getInstance();
					ca.setTime(new Date());			
					ca.add(Calendar.DATE,Integer.parseInt(pswTmoutCntl));
					user.setPswinvalidtime(ca.getTime());
				}else if(pswTmoutCntl!=null&&"0".equals(pswTmoutCntl)){
					user.setPswinvalidtime(null);
				}
			}
			user.setDept(dto.getDept());
			user.setEmail(dto.getEmail());
			user.setPhone(dto.getPhone());
			user.setAddress(dto.getAddress());
			user.setCreatedate(createdate);
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
	}

	@Override
	public Sysgroup getGroup(String userid) throws H3cException {
		 
		String hql = "from Sysgroup a where a.groupid = (select groupid from Sysusergroupref a where a.userid = :userid)";
		Query query = session.createQuery(hql);
		query.setString("userid", userid);
		return (Sysgroup) query.uniqueResult();
	}

}
