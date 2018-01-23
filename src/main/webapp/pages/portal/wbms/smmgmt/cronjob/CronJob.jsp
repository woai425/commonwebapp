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
<h3c:portalGrid property="cronJob" height="410" isAsyncLoad="false" 
	url="cronJobController.do?query" title="定时任务管理">
	<h3c:toolbar property="cronJobToolBar">
		<h3c:textEdit property="desc" placeholder="根据任务描述查询" />		  	
		<td align="left" width="20%">
		  <h3c:button property="queryCronJob" text="查询" fontAwesome="icon-search" handler="doLoadGrid()" />
		</td>
		<td align="right" width="80%">
		<%-- <h3c:buttonAuthFilter />
		<h3c:button property="buttonTest" text="按钮测试"/> --%>
		  <h3c:button property="addCronJob" text="新建" fontAwesome="icon-plus" handler="addCronJob()" />
		   <h3c:button property="deleteCronJob" text="删除" fontAwesome="icon-trash" handler="deleteCronJob()" />
		</td>
	</h3c:toolbar>
	<h3c:GridColumnModel withoutRownum="true" rowNumSortable="true">
            <h3c:gridColumn dataIndex="taskid" header="任务ID" sortable="false" hidden="true" 
				width="10" />
			<h3c:gridColumn dataIndex="check" editor="checkbox" width="20" header="selectall"/>
			<h3c:gridColumn dataIndex="description" header="任务描述" sortable="true" showTitle="true" 
				width="45" />
			<h3c:gridColumn dataIndex="cron" header="cron表达式" sortable="true"
				width="75" />
			<h3c:gridColumn dataIndex="createdate" header="创建时间" sortable="true" showTitle="true"
				width="60" />
			<h3c:gridColumn dataIndex="updatedate" header="修改时间" sortable="true" showTitle="true"
				width="60" />			
			<h3c:gridColumn dataIndex="creator" header="创建人" sortable="true"
				width="35" />			
			<h3c:gridColumn dataIndex="updator" header="修改人" sortable="true"
				width="35" />
			<h3c:gridColumn dataIndex="active" header="是否有效" editor="select" codeType="ACTIVE" width="30"/>
			<h3c:gridColumn dataIndex="isstart" header="状态" editor="select" codeType="ISSTART"
				width="20" />
			<h3c:gridColumn dataIndex="edit" header="操作" width="80" renderer="editAlter"/>
		
		<%-- <h3c:gridColumn dataIndex="operator" header="经办者" width="50" codeType="USER" editor="select" /> --%>
	</h3c:GridColumnModel>
	<h3c:PageToolBarTag pageLimit="5,10,15,50" defaultLimit="10" />
</h3c:portalGrid>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/portal/wbms/smmgmt/cronjob/CronJob.js"></script>
</html>