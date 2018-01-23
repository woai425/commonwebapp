<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<html>
<head>
<h3c:head />
</head>      
<h3c:form action="addUserController.do?addUser" name="addUser">
	<table style="height: 100%; width: 100%; border: 0px solid red; background: #fff; " >
	<h3c:tabLayOut column="2"/>
		<tr>
			<h3c:textEdit label="用户登入名" property="loginname"  required="true"/>
		</tr>
		<tr>
			<h3c:textEdit label="姓名" property="username"  required="true"/>
		</tr>
		<tr>
			<h3c:select property="dept" label="部门"  codeType="DEPT" ></h3c:select>
		</tr>
		<tr>
			<h3c:hidden property="groupid" value="${groupid }"></h3c:hidden>
		</tr>
		<tr>
			<h3c:textEdit property="email" label="邮箱"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="phone" label="电话"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="address" label="地址" placeholder="地址长度小于100"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textarea label="描述" property="description" width="220" />
		</tr>
		<tr style="width: 100%;text-align: right;">
			<td></td>
			<td>
				<span style="margin: 0 76px 0 0;"><h3c:button property="saveUser" text="保存" fontAwesome="icon-save" handler="addUserInfo()" /></span>
			</td>
		</tr>
		<tr style="height:10px;"></tr>
	</table>
</h3c:form>
<script type="text/javascript">
//保存
function addUserInfo() {
	h3c.submit("addUser",function(res){
			h3c.alert("增加成功!", function() {
				 h3c.closeWindow("addUser");
				 h3c.getRootPageObject().loadGrid2();
			 });
		 },function(res){
			 h3c.error(res.msg);
		 });		
}
$(function(){
	$("#address").attr("maxlength","100");
})
</script>
</html>