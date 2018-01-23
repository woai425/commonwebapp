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
	<td style="font-size:17px;font-family:Microsoft YaHei;" width="35%"><b>MUNE标签</b></td>
	<td style="font-size:17px;font-family:Microsoft YaHei;" width="60%;"><b>标签描述</b></td>
	<td width="100px;"></td>
	</tr>
	<tr height="10px;"></tr>
	<tr>
    <td width="50px;"></td>
    <td align="left"> 
    AccordionMenuTag
    </td>
    <td>
      <pre>页面上的自定义导航栏标签;<br>
此标签是自定义手风琴式导航栏，内部子菜单采用树形或者 li>方式展现;<br>
此标签可用在页面左侧的导航栏，和后台管理，可根据自己所需要的选择自己想要的风格,
如果使用在左侧导航栏usage为portal;用在后台管理端的usage为back;
菜单风格，如果usage为back,style有两种1.file 2.sign;如果usage为
portal,style有四种1.blueFlat 2.blackFlat 3.whiteFlat 4.whiteNoFlat<br>
使用举例:<br>
h3c:accordionMenu usage="" property="" />;<br>
具体属性可见h3c.tld文件查看。<br>
具体例子可参考系统管理的权限管理模块的用户管理模块<br>
</pre>
</td>
<td width="100px;"></td>
    </tr>
<tr>
      <td width="50px;"></td>
    <td align="left"> 
    TreeMenuTag
    </td>
    <td>
      <pre>页面上的自定义后台树形导航菜单带有checkbox;<br>
此标签类似于AccordionMenuTag;<br>
使用举例:<br>
h3c:treeMenu dataurl="" property=" " />;<br>
具体属性可见h3c.tld文件查看。<br>
具体例子可参考系统管理的权限管理模块的用户管理模块<br>
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
