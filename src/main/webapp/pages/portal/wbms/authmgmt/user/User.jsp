<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<div id=""
	style="text-align: center; overflow: auto; vertical-align: middle;">
	<table style="height: 100%; width: 100%;">
		<tr align="center">
			<td align="center" valign="middle">
				<table
					style="height: 100%; width: 100%; border: 0px solid red; background: #fff;">
					<tr style="padding: 0 10px 0 10px;">
						<td
							style="height: 100%; background: #fff; width: 16%; vertical-align: top; padding-left: 10px;">
							<table style="width: 100%;">
								<tr style="height: 20px;"></tr>
								<tr style="width: 100%;">
									<td style="width: 100%;"><h3c:groupbox property="tree"
											title="组织树">
											<h3c:accordionMenu usage="back" property="groupTree"
												handler="getGroupInfo" width="100%" height="100%"
												style="file" dataurl="userController.do?findGroup" />
										</h3c:groupbox></td>
								</tr>
							</table>
						</td>
						<td><h3c:portalGrid property="user" height="500"
								url="userController.do?getGroupUser" padding_left="6">
								<h3c:groupbox property="groupInfo" title="组织信息" width="100%"
									style="margin-bottom:10px;" isRetracted="true">
									<table
										style="width: 100%; border: 0px solid red; background: #fff;">
										<h3c:tabLayOut />
										<tr>
											<h3c:hidden property="groupid" value=""></h3c:hidden>
											<h3c:textEdit label="组织名称" property="name" />
											<h3c:select property="rate" label="级别" codeType="RATE"
												disabled="true"></h3c:select>
											<h3c:dateEdit property="createdate" label="创建时间" value=""
												width="180" disabled="true" />
										</tr>
									</table>
								</h3c:groupbox>
								<h3c:groupbox property="queryCondition" title="搜索框" width="100%"
									style="margin-bottom:10px;" isRetracted="true">
									<table
										style="width: 100%; border: 0px solid red; background: #fff;">
										<h3c:tabLayOut />
										<tr>
											<h3c:textEdit label="登入名" property="loginname"
												onchange="loadGrid2()" />
											<h3c:textEdit label="用户名" property="username"
												onchange="loadGrid2()" />
											<h3c:select property="usertype" label="用户类别"
												codeType="USERTYPE" onchange="loadGrid2()"></h3c:select>
										</tr>
									</table>
								</h3c:groupbox>
								<h3c:toolbar property="abc" text="用户管理">
									<td align="right"><h3c:button property="addUser"
											text="新增用户" fontAwesome="icon-plus" handler="openAddUser()" />
										<h3c:button property="addExistUser" text="添加已有用户"
											handler="addExistUser()" fontAwesome="icon-plus" /></td>
								</h3c:toolbar>
								<h3c:GridColumnModel withoutRownum="true">
									<h3c:gridColumn dataIndex="loginname" header="登入名" width="80" />
									<h3c:gridColumn dataIndex="username" header="姓名" width="80" />
									<h3c:gridColumn dataIndex="dept" header="部门" editor="select" codeType="DEPT" width="80" />
									<h3c:gridColumn dataIndex="usertype" header="管理员状态"
										editor="select" codeType="USERTYPE" width="80" />
									<h3c:gridColumn dataIndex="acctlockflag" header="状态"
										editor="select" renderer="isLock" width="40" />
									<h3c:gridColumn dataIndex="edit" header="操作"
										renderer="operateUser" width="40" />
									<%-- <h3c:gridColumn dataIndex="edit" header="修改"
										renderer="updateUser" width="60" />
									<h3c:gridColumn dataIndex="delete" header="删除"
										renderer="delUser" width="60" /> --%>
									<h3c:gridColumn dataIndex="userid" hidden="true" />
								</h3c:GridColumnModel>
								<h3c:PageToolBarTag pageLimit="5,10,100,15,50,500"
									defaultLimit="10" />
							</h3c:portalGrid></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<h3c:buttonAuthFilter/>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/pages/portal/wbms/authmgmt/user/User.js"></script>
