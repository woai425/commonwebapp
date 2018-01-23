<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<style type="text/css">
html, body {
	height: 100%;
	font-family: Microsoft YaHei;
}
</style>
<table
	style="height: 100%; width: 100%; border: 0px solid red; background: #fff;">
	<tr>
		<td><h3c:portalGrid property="activiti" title="流程定义信息列表" height="500"
				url="deployController.do?query">
				<h3c:toolbar property="activitiToolBar">
					<h3c:textEdit property="search" placeholder="根据 部署名称 查询" />
					<td align="left"><h3c:button property="find" text="查询"
							fontAwesome="icon-search" handler="searchProcessDeploy()" /></td>
					<td align="right" style="width: 80%"><h3c:button
							property="add" text="新建" fontAwesome="icon-plus"
							handler="showAddDdprmDlg()" /> <h3c:button property="delete"
							text="删除" fontAwesome="icon-trash" handler="deleteProcessDeploy()" /></td>
				</h3c:toolbar>
				<h3c:GridColumnModel>
					<h3c:gridColumn dataIndex="check" editor="checkbox" width="1" header="selectall" />
					<h3c:gridColumn dataIndex="deployid" header="部署ID"  width="2" hidden="true"/>
					<h3c:gridColumn dataIndex="deplyname" header="部署名称" width="12" />
					<h3c:gridColumn dataIndex="deplytime" header="发布时间" width="28" sortable="true"/>
					<h3c:gridColumn dataIndex="procid" header="流程定义ID" width="20"  />
					<h3c:gridColumn dataIndex="procname" header="流程定义名称" width="20" />
					<h3c:gridColumn dataIndex="prockey" header="KEY" width="15" />
					<h3c:gridColumn dataIndex="version" header="版本" width="9"/>
					<h3c:gridColumn dataIndex="resname" header="流程定义规则名称" width="30" />
					<h3c:gridColumn dataIndex="dgrmresname" header="流程定义图片名称" width="30" />
					<h3c:gridColumn dataIndex="edit" header="操作" width="15" renderer="doEdit" />
				</h3c:GridColumnModel>
				<h3c:PageToolBarTag defaultLimit="15" />
			</h3c:portalGrid></td>
	</tr>
</table>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/pages/portal/wbms/act5mgmt/deploy/Deploy.js"></script>