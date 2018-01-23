<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<table style="width:99%;height:100%;">
	<tr>
		<td>
			<h3c:portalGrid property="roleUsers" height="300" url="roleController.do?queryRoleUsersList" padding_left="5" padding_bottom="10" padding_right="5" padding_top="5">
				<h3c:GridColumnModel withoutRownum="true" rowNumSortable="true" rowHeight="28">
					<h3c:gridColumn dataIndex="userid" header="用户ID" width="40" hidden="true" />
					<h3c:gridColumn dataIndex="loginname" header="登录名" width="40" />
					<h3c:gridColumn dataIndex="username" header="用户名" width="40" />
					<h3c:gridColumn dataIndex="email" header="邮箱" width="40"/>
				</h3c:GridColumnModel>
				<h3c:PageToolBarTag pageLimit="5,10,15,50,100,500" defaultLimit="10" />
			</h3c:portalGrid></td>
	</tr>
</table>

<script type="text/javascript">
	window.onload = function(){
		doLoadGrid();
	}
	
	function doLoadGrid(){
		var params = {
				roleId : "${roleId}"
		};
		h3c.doLoadGrid("roleUsers", params, true);
	}
</script>
