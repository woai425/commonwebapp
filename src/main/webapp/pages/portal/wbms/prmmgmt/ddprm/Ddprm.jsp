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
		<td><h3c:portalGrid property="code" title="数据字典" height="500"
				url="ddprmController.do?query">
				<h3c:toolbar property="codeToolBar">
					<h3c:textEdit property="search" placeholder="根据 代码||代码名 查询" />
					<td align="left"><h3c:button property="find" text="查询"
							fontAwesome="icon-search" handler="searchDdprm()" /></td>
					<td align="left"><h3c:button property="find2" text="过滤查询"
							fontAwesome="icon-search" handler="distinctSearchDdprm()" /></td>
					<td align="right" style="width: 80%"><h3c:button
							property="add" text="新建" fontAwesome="icon-plus"
							handler="showAddDdprmDlg()" /> <h3c:button property="delete"
							text="删除" fontAwesome="icon-trash" handler="deleteDdprm()" /></td>
				</h3c:toolbar>
				<h3c:GridColumnModel withoutRownum="true" rowNumSortable="true">
					<h3c:gridColumn dataIndex="check" editor="checkbox" width="20"
						header="selectall" />
					<h3c:gridColumn dataIndex="codeid" header="ID" hidden="true"
						width="10" />
					<h3c:gridColumn dataIndex="code" header="代码" width="55" />
					<h3c:gridColumn dataIndex="codename" header="代码名" hidden="false"
						width="60" />
					<h3c:gridColumn dataIndex="codevalue" header="代码值" width="80"
						sortable="true" />
					<h3c:gridColumn dataIndex="codeexplain" header="代码名称" width="55" />
					<h3c:gridColumn dataIndex="codetype" header="参数分类" width="40" />
					<h3c:gridColumn dataIndex="codestate" header="有效标志" width="40"
						codeType="USEFUL" editor="select" />
					<h3c:gridColumn dataIndex="maintstate" header="维护标志" width="40"
						codeType="YESNO" editor="select" />
					<h3c:gridColumn dataIndex="edit" header="编辑" width="30"
						renderer="doEdit" />
				</h3c:GridColumnModel>
				<h3c:PageToolBarTag defaultLimit="15" />
			</h3c:portalGrid></td>
	</tr>
</table>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/pages/portal/wbms/prmmgmt/ddprm/Ddprm.js"></script>