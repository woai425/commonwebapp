package com.h3c.portal.taglib.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.h3c.framework.util.UUIDHexUtil;

/**
 * *********************************************************************
 * 标签功能辅助类
 * TagUtil.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2015年12月14日 上午11:50:01
 * @revision    $Id:  *
 **********************************************************************
 */
public class TagUtil {
	
	/*
	 * grid的内边距值
	 */
	public static final int gridPadding = 20;
	public static final String TRANSACTION_TOKEN_KEY = "TRANSACTION_TOKEN_KEY";
	public static final String TOKEN_KEY = "TOKEN_KEY";
	
	private TagUtil(){}
	
	public static synchronized String saveToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String token = UUIDHexUtil.generate36bit();
		if(token != null){
			session.setAttribute(TRANSACTION_TOKEN_KEY, token);
		}
		return token;
	}
	
	/**
	 * 获取label信息
	 * @param label
	 * @param isRequired
	 * @return
	 */
    public static String getLabelString(String label,Boolean isRequired)
    {
    	String labelInfo ="";
    	if(label!=null&&!label.trim().equals(""))
    	{
    		labelInfo += "";
    		if(isRequired!=null&&isRequired)
    		{
    			labelInfo += "<font color=red>*</font>";
    		}
    	}
    	return labelInfo;
    }
    
	/**
	 * 设置布局标签系统参数
	 * @param pageContext
	 * @param params
	 */
    public static void setTagPageContextParam(PageContext pageContext,String ... params)
    {
    	if(params!=null&&params.length>0){
        	List<String> lst = new ArrayList<String>();
            for(int i=1;i<params.length;i++){
            	lst.add(params[i]);
            }
            pageContext.setAttribute(params[0], lst);
    	}
    }
    
	/**
	 * 获取布局标签的系统参数
	 * @param pageContext
	 * @param params
	 */
    @SuppressWarnings("unchecked")
	public static Map<String,String> getTagPageContextParam(PageContext pageContext,String param)
    {
    	Map<String,String> m = null;
    	List<String> lst = (ArrayList<String>)pageContext.getAttribute(param);
    	if(lst!=null&&lst.size()>0){
    		m = new HashMap<String, String>();
            for(int i=0;i<lst.size();i++){
            	m.put("P"+i, lst.get(i));
            }
    	}
    	return m;
    }
    
    
}
