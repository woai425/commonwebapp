<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<style type="text/css">
	.pwdLevel_weak{ float:left; background:#ED3500; width:62px; height:4px; margin-top:5px; margin-left:5px; _margin-top:0px;_height:2px; font-size:0px;}
    .pwdLevel_medium{ float:left; background: #F38313; width:62px; height:4px; margin-top:5px; margin-left:5px; _margin-top:0px;_height:2px; font-size:0px;}
    .pwdLevel_tough{ float:left; background: #6DA005; width:62px; height:4px; margin-top:5px; margin-left:5px; _margin-top:0px;_height:2px; font-size:0px;}
	.pwdLevel_primary{ float:left; background:#d6d3d3; width:62px; height:4px; margin-top:5px; _margin-top:0px; margin-left:5px; _height:2px;font-size:0px;}
	.pwdLevel_font{ float:left; width:62px; margin-left:5px; text-align:center; color:#b0adad; font-size:8px; }
</style>
<div style="background-color: #fff;height: 600px;width: 460px;overflow: auto;">
<h3c:form action="passwordController.do?updateUser">
<h3c:groupbox isRetracted="true" style="margin: 10px 10px 10px 10px;" property="userinfo" title="基本信息">
	<table style="text-align: center;">
	<h3c:tabLayOut column="2"/>
		<tr align="center">
			<h3c:textEdit  property="loginname"  value="${user.loginname }" label="用户登录名" width="260"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit  property="username"  label="用户名" value="${user.username }" width="260"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit  property="passwd"  inputType="password" value="${user.passwd }" label="密码" width="260" onchange="checkLength()"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit  property="confirmPasswd" inputType="password" value="${user.passwd }" label="确认密码" width="260" onchange="checkPassword()"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:select property="dept" codeType="DEPT" label="部门" value="${user.dept }" width="276"></h3c:select>
		</tr>
		<tr>
			<h3c:textEdit  property="email" label="邮箱" value="${user.email }" width="260"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit  property="phone" label="电话" value="${user.phone }" width="260"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit  property="address" label="地址" value="${user.address }" width="260"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:hidden  property="userid" value="${user.userid }" />
		</tr>
		<tr>
			<h3c:hidden  property="groupid" value="${groupid }" />
		</tr>
		<tr>
			<h3c:hidden  property="password" value="${user.passwd }" />
		</tr>
	</table>
</h3c:groupbox>
<h3c:groupbox isRetracted="true" style="margin: 10px 10px 10px 10px;" property="usergroupinfo" title="组织机构">
<table style="width: 100%;">
	<tr>
		<td style="width: 22%;text-align: right;font-size:13px;font-family:Microsoft YaHei;">
			组织机构
		</td>
		<td style="width: 66%;">
			<div id="tree1" style="height: 166px;overflow: auto;border: 1px solid #C0C0C0;"></div>
		</td>
		<td style="width: 12%;">
		</td>
	</tr>
</table>
</h3c:groupbox>
</h3c:form>
</div>
<script type="text/javascript">
$(function(){
	$("#loginname").attr("readonly","readonly");
	$("#username").attr("readonly","readonly");
	$("#passwd").attr("readonly","readonly");
	$("#confirmPasswd").attr("readonly","readonly");
	$("#dept").attr("disabled","disabled");
	$("#email").attr("readonly","readonly");
	$("#phone").attr("readonly","readonly");
	$("#address").attr("readonly","readonly");
	$("#userid").attr("readonly","readonly");
	$("#groupid").attr("readonly","readonly");
	$("#password").attr("readonly","readonly");
	
	
})
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/portal/wbms/authmgmt/password/Password.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/layout/lib/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/layout/lib/jqTree/tree.jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/layout/lib/jqTree/jqtree.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/layout/lib/jquery/jquery.cookie.js"></script>