<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<table style="height: 100%; width: 100%;background: #fff;">
	<tr style="padding: 0 10px 0 10px;">
         <td>
			<h3c:portalGrid property="user"  height="300" width="300" url="addExistUserController.do?getAllUser">
            	<h3c:groupbox property="query" title="查询可用用户" width="100%" style="margin-bottom:10px;" isRetracted="true">
					<table style="width: 100%; border: 0px solid red; background: #fff;">
						<h3c:tabLayOut />
						<tr>
							<h3c:textEdit label="用户登入名" property="loginname" />
							<td>
								<h3c:button property="searchUser" text="查询" fontAwesome="icon-search" handler="query()"/>
							</td>
						</tr>
					</table>
				</h3c:groupbox>
				<h3c:GridColumnModel withoutRownum="true" rowNumSortable="true">
					<h3c:gridColumn dataIndex="check" editor="checkbox" header="selectall" />
					<h3c:gridColumn dataIndex="loginname" header="用户登入名"  />
					<h3c:gridColumn dataIndex="username" header="姓名" />
					<h3c:gridColumn dataIndex="description" header="描述"  />
					<h3c:gridColumn dataIndex="userid" hidden="true" />
					<h3c:gridColumn dataIndex="isleader" hidden="true" />
				</h3c:GridColumnModel>
				<h3c:PageToolBarTag pageLimit="5,10" defaultLimit="10" />
			</h3c:portalGrid>
		</td>    
	</tr>
	<tr>
		<td>
			<h3c:hidden property="groupid" value="${groupid }"></h3c:hidden>
		</td>
	</tr>
	<tr>
		<td style="text-align: right;">
			<span style="margin: 0 20px 0 0;"><h3c:button property="addUser" text="增加用户到组" fontAwesome="icon-plus" handler="addGroupUser()"/></span>
		</td>
	</tr>
	<tr height="10px">
	</tr>
</table>
<script type="text/javascript">
window.onload = function(){
	var params = {};
	params.groupid = document.getElementById("groupid").value;
	h3c.doLoadGrid('user', params, true);
}

//查询函数
function query(){
	var params = {};
	params.loginname = document.getElementById("loginname").value;
	params.groupid = document.getElementById("groupid").value;
	h3c.doLoadGrid('user', params, true);
}

//添加用户到组
function addGroupUser(){
	h3c.confirm("是否确认？",function(){
		var params = {};
		params.userData = h3c.getGridJsonData('user');
		params.groupid = document.getElementById("groupid").value;
		h3c.doEvent("addExistUserController.do?addUserToGroup", params, function(rs) {
			h3c.alert("添加成功!", function() {
						h3c.closeWindow("addExistUser");
						h3c.getRootPageObject().loadGrid2();
					});
		});
	});
}
</script>