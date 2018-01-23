<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<div id="group" style="height: 100%; width: 100%;text-align: center; overflow: hidden;vertical-align: middle;" >
	<table style="height: 100%; width: 100%;">
		<tr align="center" style="height: 100%; width: 100%;">
			<td align="center" valign="middle" height="100%">
				<table id="tableGroup" style="height: 100%; width: 100%;border: 0px solid red;background: #fff; vertical-align: top;">
					<tr
						style="border-bottom: 1px solid #bbb; font-family: Microsoft YaHei;">
						<td colspan="3"><h3c:toolbar property="groupbar" text="组织管理">
							<td align="right">
								<h3c:button property="create" text="新增" fontAwesome="icon-plus" handler="addGroup()" />
								<h3c:button property="delete" text="删除" handler="deleteGroup()" fontAwesome="icon-trash"/>
								<h3c:button property="save" text="保存" handler="saveGroup()" fontAwesome="icon-save"/>
							</td>
							</h3c:toolbar></td>
					</tr>
					<tr style="padding: 0 10px 0 10px;">
					
						<td
							style="height: 100%; background: #fff; width: 20%; vertical-align: top;">
							<h3c:accordionMenu usage="back" property="groupTree"
								fontsize="13px" handler="getGroupInfo" width="100%" height="100%"
								style="file" dataurl="groupController.do?findGroup" />
						</td>
						<td
							style="height: 100%; background: #fff; width: 80%; border-left: 1px solid #bbb; padding-top: 0px; vertical-align: top;">
							<h3c:form action="groupController.do?saveGroup" name="Group" >
							<table style="width: 100%">
								<h3c:tabLayOut column="2" />
								<tr height="15">
									<td colspan="2"></td>
								</tr>
								<tr>
									<h3c:hidden property="parentid" value=""></h3c:hidden>
									<h3c:hidden property="groupid" value=""></h3c:hidden>
								</tr>
								<tr>
									<h3c:textEdit label="父资源名称 " property="parentName" readonly="true" width="280" />
									<h3c:select label="父组织级别" property="parentRate" codeType="RATE" value="panel" width="294" disabled="true"/>
								</tr>
								<tr>
									<h3c:textEdit label="组织名称" property="name" required="true" width="280" />
									<h3c:textEdit label="组织描述" property="description"  width="280" />
								</tr>
								<tr>
									<h3c:textEdit label="机构负责人" property="principal"  width="280" />
									<h3c:textEdit label="简称" property="shortname"  width="280" />
								</tr>
								<tr>
									<h3c:textEdit label="地址" property="address"  width="280" />
									<h3c:textEdit label="电话" property="tel"  width="280" />
								</tr>
								<tr>
								    <h3c:textEdit label="联系人" property="linkman" width="280" />
									<h3c:select label="类型" property="type" codeType="ICONCLS" value="panel" width="294" />
								</tr>
								<tr>
									<h3c:textEdit label="主管部门" property="chargedept" width="280" />
									<h3c:select label="级别" property="rate" codeType="RATE" value="panel" width="294" />
								</tr>
								<tr>
									<h3c:textEdit label="创建者" property="owner" width="280" />
									<h3c:dateEdit label="创建时间" property="createdate" disabled="true" width="252" />
								</tr>
								<tr style="width: 100%;">
									<h3c:select label="有效标记" property="status" codeType="ACTIVE" value="panel" width="294" />
								</tr>
								<tr>
									<h3c:hidden property="publicflag" value="0"></h3c:hidden>
									<h3c:hidden property="rpflag" value="0"></h3c:hidden>
								</tr>									
								<tr>
									<h3c:hidden property="rbflag" value="0" ></h3c:hidden>
								</tr>
							</table>
							</h3c:form>
						</td>
                        
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript" src="pages/portal/wbms/authmgmt/group/Group.js"></script>