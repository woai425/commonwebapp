package com.h3c.portal.business.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.context.ContextLoaderListener;

/***********************************************************************
 *
 * FuncUtils.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     dfw2447<br/>
 * @create-time 2016年5月6日 下午5:23:34
 * @revision    $Id:  *
 ***********************************************************************/

public class FuncUtils {

	 /**
     * 根据位运算把 byte[] -> int
     * @param bytes
     * @return int
     */
    public static int bytesToInt(byte[] bytes) {
        int addr = bytes[3] & 0xFF;
        addr |= ((bytes[2] << 8) & 0xFF00);
        addr |= ((bytes[1] << 16) & 0xFF0000);
        addr |= ((bytes[0] << 24) & 0xFF000000);
        return addr;
    }
    
    /**
     * 把IP地址转化为byte[]
     * @param ipAddr
     * @return byte[]
     */
    public static byte[] ipToBytesByReg(String ipAddr) {
        byte[] ret = new byte[4];
        try {
            String[] ipArr = ipAddr.split("\\.");
            ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
            return ret;
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }

    }
    
    /** 
     * 把ip->int地址
     * @param ipAddr
     * @return
     */
    public static int ipToInt(String ipAddr) {
    	return bytesToInt(ipToBytesByReg(ipAddr));
    }
    
    /**
     * 把int->ip地址
     * @param ipInt
     * @return String
     */
    public static String intToIp(int ipInt) {
        return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.')
                .append((ipInt >> 16) & 0xff).append('.').append(
                        (ipInt >> 8) & 0xff).append('.').append((ipInt & 0xff))
                .toString();
    }
    
    /**
     * BASE64加密，编码格式‘utf-8’
     * @param target
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String Base64Encode(String target) throws UnsupportedEncodingException {
    	return new String(Base64
				.encodeBase64(target.getBytes()), "utf-8");
    }
    
    /**
     * BASE64解密，编码格式‘utf-8’
     * @param target
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String Base64Decode(String target) throws UnsupportedEncodingException {
    	return new String(Base64.decodeBase64(target.getBytes("utf-8")));
    }
    
    /**
     * 获取IOC容器中的bean
     * @param beanClazz 
     * @return E
     */
	public static <E> E getBean(Class<E> beanClazz) {
		return ContextLoaderListener.getCurrentWebApplicationContext().getBean(
				beanClazz);
	}
	
    public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(Base64Decode("YWRtaW4="));
	}
}
