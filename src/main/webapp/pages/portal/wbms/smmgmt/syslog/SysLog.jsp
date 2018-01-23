<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<html>
<head>
<h3c:head />
<style type="text/css">
html, body {
	height: 100%;
	font-family: Microsoft YaHei;
}
</style>
</head>
<body>
<h3c:portalGrid property="syslog" height="410" isAsyncLoad="false" 
	url="sysLogController.do?query" title="日志管理">
	<h3c:toolbar property="syslogToolBar">
		<td align="right" width="2%"><table><tr><td><h3c:textEdit property="logPrcol1"  placeholder="根据 模块名称 查询" onchange="doLoadGrid()"/></td></tr></table></td>
		<td align="right" width="4%"><table><tr><td><h3c:textEdit property="logDigest"  placeholder="根据 日志摘要 查询" onchange="doLoadGrid()"/></td></tr></table></td>
		<td align="right" width="71%"><table><tr><td><h3c:select property="logtype" data="['1','登录'],['2','退出'],['3','业务']" /></td></tr></table></td>
		<td align="right" width="3%"><h3c:button property="sysLog" text="查询" fontAwesome="icon-search" handler="doLoadGrid()" /></td>
	</h3c:toolbar>
	<h3c:GridColumnModel rowHeight="35">
		<h3c:gridColumn dataIndex="opseno" header="流水号" sortable="true" width="30" />
		<h3c:gridColumn dataIndex="prcol1" header="模块"  width="60" />
		<h3c:gridColumn dataIndex="digest" header="日志摘要"  width="120" showTitle="true"/>
		<h3c:gridColumn dataIndex="opip" header="经办人IP" width="60" />
		<h3c:gridColumn dataIndex="broswer" header="浏览器" sortable="true" showTitle="true" width="50" />
		<h3c:gridColumn dataIndex="operator" header="经办者" width="50" codeType="USER" editor="select" />
		<h3c:gridColumn dataIndex="optime" header="经办时间" sortable="true" width="80" />
	</h3c:GridColumnModel>
	<h3c:PageToolBarTag defaultLimit="15"/>
</h3c:portalGrid>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/portal/wbms/smmgmt/syslog/SysLog.js"></script>
</html>