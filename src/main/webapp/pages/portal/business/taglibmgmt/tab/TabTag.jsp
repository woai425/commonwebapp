<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3c:head />
<style type="text/css">
html, body {
	height: 100%;
	font-family: Microsoft YaHei;
}
</style>
<div style="height: 100%; width: 100%; border: 0px solid red; background: #fff; overflow:auto">
<table
	style="height: 100%; width: 100%; border: 0px solid red; background: #fff;">
	<tr>
	<td width="50px;"></td>
	<td style="font-size:17px;font-family:Microsoft YaHei;" width="35%"><b>TAB标签</b></td>
	<td style="font-size:17px;font-family:Microsoft YaHei;" width="60%;"><b>标签描述</b></td>
	<td width="100px;"></td>
	</tr>
	<tr height="10px;"></tr>
	<tr>
    <td width="50px;"></td>
    <td align="left"> 
    Tab
    </td>
    <td>
      <pre>自定义tab容器标签;<br>
tab的容器，里面放置tabTitle和tabContent，可以统一调整tabContent样式;<br>
使用举例:<br>
h3c:tab data="['grantSysuser','用户授权管理'],['grantGroup','组织授权管理']" property="grantTab" handler="query" titlebgcolor="green" activecolor="red">
具体属性可见h3c.tld文件查看。<br>
具体例子可参考系统管理的权限管理模块的授权管理模块<br>
</pre>
</td>
<td width="100px;"></td>
    </tr>
<tr>
      <td width="50px;"></td>
    <td align="left"> 
    TabContent
    </td>
    <td>
      <pre>自定义tab容器标签的内容标签;<br>
使用举例:<br>
h3c:tabContent property="grantSysuser" active="true"><br>
具体属性可见h3c.tld文件查看。<br>
具体例子可参考系统管理的权限管理模块的授权管理模块<br>
</pre>
</td>
<td width="100px;"></td>
</tr>
<tr></tr>
<tr></tr> 
<tr></tr>     
<tr></tr>
</table>
</div>
