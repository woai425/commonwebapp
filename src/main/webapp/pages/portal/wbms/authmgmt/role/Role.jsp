<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<div id="role" style="text-align: center; overflow: auto;vertical-align: middle;">
	<table
		style="height: 100%; width: 100%;">
		<tr align="center">
			<td align="center" valign="middle">
				<table style="height: 100%; width: 100%;border: 0px solid red;background: #fff;">
					<tr
						style="border-bottom: 1px solid #bbb; font-family: Microsoft YaHei;">
						<td colspan="3"><h3c:toolbar property="rolebar" text="角色管理">
							<td align="right">
								<h3c:button property="addRole" text="新增角色" fontAwesome="icon-plus" handler="showAddRoleDlg()" />
								<h3c:button property="search" text="查询"  fontAwesome="icon-search" handler="findRole()" />
							</td>
							</h3c:toolbar></td>
					</tr>
					<tr style="padding: 0 10px 0 10px;">
						
                        <td>
                           <h3c:portalGrid property="ROLE" height="460"  url="roleController.do?search">
					<h3c:groupbox property="abc" title="角色查询" width="100%"
						style="margin-bottom:10px;" isRetracted="true">
						<table
							style="width: 100%; border: 0px solid red; background: #fff;">
							<h3c:tabLayOut />
							<tr>
								<h3c:textEdit label="角色名称" property="roleName" />
								<h3c:textEdit label="角色描述" property="roleDesc" />
							</tr>
						</table>
					</h3c:groupbox>
					<h3c:GridColumnModel withoutRownum="true" rowNumSortable="true">
					    <h3c:gridColumn dataIndex="roleid" header="ID" width="40" hidden="true"/>
						<h3c:gridColumn dataIndex="rolename" header="角色名称" width="80" />
						<h3c:gridColumn dataIndex="roledesc" header="角色描述" width="80"/>
						<h3c:gridColumn dataIndex="status" header="状态"  width="40" renderer="showState"/>
						<h3c:gridColumn dataIndex="accredit" header="授权"  width="40"  renderer="grantRole"/>
						<h3c:gridColumn dataIndex="checkUser" header="查看用户"  width="40"  renderer="showOpenRoleUsersList"/>
						<h3c:gridColumn dataIndex="modify" header="修改"  width="40"  renderer="doEdit"/>
						<h3c:gridColumn dataIndex="delete" header="删除"  width="30" renderer="deleterole"/>
					</h3c:GridColumnModel>
					<h3c:PageToolBarTag pageLimit="5,10,15,50,100,500" defaultLimit="10" />
				</h3c:portalGrid>
                        </td>    
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/portal/wbms/authmgmt/role/Role.js"></script>