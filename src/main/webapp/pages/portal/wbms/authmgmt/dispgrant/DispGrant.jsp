<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<script type="text/javascript">
	var userid = '${sessionScope.cUser.id}';
</script>
<style type="text/css">
</style>
<h3c:head />
<!--2017-8-2-zhoujie 修改背景颜色 -->
<h3c:tab data="['dispGrant','可分授角色'],['grantSysuser','用户授权管理'],['grantGroup','组织授权管理']" property="grantTab" handler="query" titlebgcolor="#359FE5" activecolor="#5FBCFF">
	<h3c:tabContent property="dispGrant" active="true">
	<h3c:portalGrid url="dispGrantController.do?getDispGrant" property="get_dispGrant" height="">
		<h3c:GridColumnModel>
			<h3c:gridColumn dataIndex="roleid" header="ID" width="40" hidden="true"/>
			<h3c:gridColumn dataIndex="rolename" header="角色名称" width="80" />
			<h3c:gridColumn dataIndex="roledesc" header="角色描述" width="80"/>
			<h3c:gridColumn dataIndex="owner" header="所有者"  width="60"/>
			<h3c:gridColumn dataIndex="status" header="状态"  width="40" />
		</h3c:GridColumnModel>
		<h3c:PageToolBarTag />
	</h3c:portalGrid>
	</h3c:tabContent>
	<h3c:tabContent property="grantSysuser">
	<h3c:portalGrid property="get_grantSysuser" height="300px" url="dispGrantController.do?getSysuser">
		<h3c:groupbox property="queryGroupBox1" title="查询条件" width="100%" style="margin:0 0 6px 0; background: #fff;" isRetracted="true">
			<table style="width: 100%; border: 0px solid red; background: #fff;">
			<h3c:tabLayOut />
				<tr>
					<h3c:textEdit property="loginname" label="登录名" />
					<h3c:textEdit property="username" label="姓名" />
					<td style="text-align: right;">
						<span style="margin: 0 20px 0 0;"><h3c:button property="find" text="查询" fontAwesome="icon-search" handler="query()" /></span>
					</td>
				</tr>
			</table>
		</h3c:groupbox>
		<h3c:GridColumnModel withoutRownum="true">
<!-- 		2017-7-28-zhoujie  去掉没有选中操作的可选框 -->
<%-- 		<h3c:gridColumn header="selectall" dataIndex="usercheck" width="40" editor="checkbox"/>		 --%>
			<h3c:gridColumn header="用户id" dataIndex="userid" width="30" hidden="true" />
			<h3c:gridColumn header="登录名" dataIndex="loginname" width="60" />
			<h3c:gridColumn header="姓名" dataIndex="username" width="60" />
			<h3c:gridColumn header="状态" dataIndex="useful" renderer="showState" width="50" />
			<h3c:gridColumn header="授权" dataIndex="grantUser" width="50"  renderer="grantUser" />
			<!-- Deleted by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并  -->
		
		</h3c:GridColumnModel>
		<h3c:PageToolBarTag pageLimit="" defaultLimit="5"/>
	</h3c:portalGrid>
	</h3c:tabContent>
	<h3c:tabContent property="grantGroup">
	<div id="treeTable" style="height: 700px;">
		<table id="treeTable1" class="h3c-table" style="width:100%;line-height: 40px;font-size: 14px;font-family: Microsoft YaHei;">
			<tr height="40px" class="even">
         		<th width="200px">用户组名</th>
         		<th style="text-align: center;" width="100px">简称</th>
         		<th style="text-align: center;" width="100px">级别</th>
         		<th style="text-align: center;" width="100px">所有者</th>
         		<th style="text-align: center;" width="100px">创建时间</th>
         		<th style="text-align: center;" width="100px">状态</th>
         		<th style="text-align: center;" width="100px">授权</th>
         		<!-- Deleted by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并  -->
        	</tr>
        	<tr height="40px" id="2f9f08b71fdd43c7c2f73dc22aa0ttr4" hasChild="true">
        		<td><span controller="true">组织树</span></td>
        		<td style="text-align: center;"></td>
        		<td style="text-align: center;">Root</td>
        		<td style="text-align: center;">zhouzw</td>
        		<td style="text-align: center;">2014-11-11</td>
        		<td style="text-align: center;"><script type="text/javascript">showGroupState(1);</script></td>
        		<td style="text-align: center;"></td>
        		<!-- Deleted by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并  -->
        	</tr>
     </table>
     </div>
            <hr/>
	</h3c:tabContent>
</h3c:tab>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/portal/wbms/authmgmt/dispgrant/DispGrant.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/layout/lib/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/layout/lib/treeTable/jquery.treeTable.js"></script>
	
		