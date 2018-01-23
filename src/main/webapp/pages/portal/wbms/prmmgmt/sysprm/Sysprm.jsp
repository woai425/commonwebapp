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
		<td valign="top"><h3c:portalGrid property="system" height="500"
				padding_top="0" url="sysParamController.do?query">
				<h3c:toolbar property="abc" text="系统参数维护" size="18">
					<td align="right" style="width: 80%"><h3c:button
							property="find" text="查询" fontAwesome="icon-search"
							handler="searchSysprm()" /> <h3c:button property="create"
							text="新建" fontAwesome="icon-plus" handler="showAddSysprmDlg()" />
						<h3c:button property="delete" text="删除" fontAwesome="icon-trash"
							handler="deleteSysprm()" /></td>
				</h3c:toolbar>
				<h3c:groupbox property="query" title="信息查询" width="100%"
					style="margin-bottom:10px;" isRetracted="true">
					<table
						style="width: 100%; border: 0px solid red; background: #fff;">
						<h3c:tabLayOut />
						<tr>
							<h3c:textEdit label="参数代码" property="paramCode" />
							<h3c:textEdit label="参数名称" property="paramName" />
							<h3c:select property="maintState" label="维护标志"
								codeType="MAINTSTATE"></h3c:select>
						</tr>
					</table>
				</h3c:groupbox>
				<h3c:GridColumnModel withoutRownum="true">
					<h3c:gridColumn dataIndex="check" editor="checkbox" width="20"
						header="selectall" />
					<h3c:gridColumn dataIndex="paramcode" header="参数代码" width="60" />
					<h3c:gridColumn dataIndex="paramname" header="参数名" width="60" />
					<h3c:gridColumn dataIndex="paramvalue" header="参数值" width="110" />
					<h3c:gridColumn dataIndex="paramexplain" header="参数说明" width="110" />
					<h3c:gridColumn dataIndex="maintstate" header="维护标识" width="30"
						codeType="MAINTSTATE" editor="select" />
					<h3c:gridColumn dataIndex="edit" header="编辑" width="20"
						renderer="doEdit" />
				</h3c:GridColumnModel>
				<h3c:PageToolBarTag pageLimit="5,10,100,15,50,500" defaultLimit="10" />
			</h3c:portalGrid></td>
	</tr>
</table>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/pages/portal/wbms/prmmgmt/sysprm/Sysprm.js"></script>
