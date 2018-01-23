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
<h3c:head />
<table
	style="height: 100%; width: 100%; border: 0px solid red; background: #fff;">
	<tr>
		<td><h3c:portalGrid property="model" title="模型信息列表" height="500"
				url="ModelController.do?ModelList" >
				<h3c:toolbar property="activitiToolBar">
				<td align="left" style="width: 80%"><h3 id="showDeployment" style="color:rgb(0, 0, 0);font-size:16px;">部署名称: ${name} , 部署id: ${deploymentId}</h3></td>
					<td align="right" style="width: 60%">
					<h3c:button property="add"
							text="新建" fontAwesome="icon-plus" handler="showAddModel()" /></td>
				</h3c:toolbar>
				<h3c:GridColumnModel >
					<h3c:gridColumn dataIndex="id_" header="ID"  width="10" />
					<h3c:gridColumn dataIndex="key_" header="key" width="12" />
					<h3c:gridColumn dataIndex="name_" header="name" width="28" sortable="true"/>
					<h3c:gridColumn dataIndex="version_" header="版本" width="20"  />
					<h3c:gridColumn dataIndex="create_time_" header="创建时间" width="20" renderer="formatTime1"/>
					<h3c:gridColumn dataIndex="last_update_time_" header="最后更新时间" width="15" renderer="formatTime2"/>
					<h3c:gridColumn dataIndex="edit" header="操作" width="10" renderer="doEdit" />
				</h3c:GridColumnModel>
				<h3c:PageToolBarTag  pageLimit="10,20,30,40,50,60" defaultLimit="15" />
			</h3c:portalGrid></td>
	</tr>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/portal/business/modeler/ModelList.js">

</script>
<script type="text/javascript">
var deploymentName="${name}";
var deploymentId="${deploymentId}";
</script>
