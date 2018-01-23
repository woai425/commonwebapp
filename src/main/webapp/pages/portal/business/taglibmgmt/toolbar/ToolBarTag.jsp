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
	<td style="font-size:17px;font-family:Microsoft YaHei;" width="35%"><b>TOOLBAR标签</b></td>
	<td style="font-size:17px;font-family:Microsoft YaHei;" width="60%;"><b>标签描述</b></td>
	<td width="100px;"></td>
	</tr>
	<tr height="10px;"></tr>
	<tr>
    <td width="50px;"></td>
    <td align="left"> 
   ToolBarTag
    </td>
    <td>
      <pre>页面上的自定义工具栏标签;<br>
 此标签相当于一个DIV，是一个容器，里面存放具体的工具;<br>
使用举例:<br>
h3c:toolbar property="groupbar" text="toolTar">
 td align="right">
   h3c:button property="delete" text="删除" handler="deleteGroup()" fontAwesome="icon-trash"/>
 /td>
/h3c:toolbar><br>
具体属性可见h3c.tld文件查看。<br>
                     <div style="background: #fff;">
						<h3c:toolbar property="groupbar" text="toolTar" >
							<td align="right">
								<h3c:button property="delete" text="删除" handler="deleteGroup()" fontAwesome="icon-trash"/>	
							</td>
							</h3c:toolbar>
					 </div>

</pre>
</td>
<td width="100px;"></td>
    </tr>
<tr></tr>
<tr></tr>
<tr></tr> 
<tr></tr>     
<tr></tr>
</table>
</div>
