<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<script type="text/javascript">
	var loginname = '${sessionScope.cUser.loginname}';
</script>
<div id="resource" style="text-align: center; overflow: auto;vertical-align: middle;">
	<table
		style="height: 100%; width: 100%;">
		<tr align="center">
			<td align="center" valign="middle">
				<table style="height: 100%; width: 100%;border: 0px solid red;background: #fff;">
					<tr
						style="border-bottom: 1px solid #bbb; font-family: Microsoft YaHei;">
						<td colspan="3"><h3c:toolbar property="abc" text="资源管理">
							<td align="right">
								<h3c:button property="create" text="新建" fontAwesome="icon-plus" handler="addNode()" />
								<h3c:button property="delete" text="删除" fontAwesome="icon-trash" handler="deleteNode()" />
								<h3c:button property="save" text="保存" fontAwesome="icon-save" handler="saveNode()" />
							</td>
							</h3c:toolbar></td>
					</tr>
					<tr style="padding: 0 10px 0 10px;">
						<td
							style="height: 100%; background: #fff; width: 20%; vertical-align: top;">
							<h3c:accordionMenu usage="back" property="roleTree"
								fontsize="13px" handler="getNodeInfo" width="100%" height="100%"
								style="file" dataurl="resourceController.do?findBack" />
						</td>
						<td
							style="height: 100%; background: #fff; width: 80%; border-left: 1px solid #bbb; padding-top: 0px; vertical-align: top;">
							<h3c:form action="resourceController.do?saveNode" name="Resource" >
							<table style="width: 100%">
								<h3c:tabLayOut column="2" />
								<tr height="15">
									<td colspan="2"></td>
								</tr>
								<tr>
									<h3c:hidden property="parent" value=""></h3c:hidden>
									<h3c:hidden property="functionid" value=""></h3c:hidden>
								</tr>
								<tr id="getWidth">
									<h3c:textEdit label="父资源名称 " property="parentName" readonly="true" width="280" />
									<h3c:textEdit label="资源名称" property="title" required="true" width="280" />
								</tr>
								<tr>
									<h3c:numberEdit label="同级菜单序号" property="orderno" required="true" width="280" />
									<h3c:textEdit label="资源描述" property="description" required="true" width="280" />
								</tr>
								<tr>
									<h3c:textEdit label="资源URL" property="location" required="true" width="280" />
									<h3c:select label="模块功能分类" property="uptype" codeType="UPTYPE" value="0" width="294"/>
								</tr>
								<tr>
									<h3c:select label="类型" property="type" required="true" codeType="NTYPE" value="1" width="294" onchange="showType()" />
									<h3c:select label="状态" property="active" required="true" codeType="USEFUL" value="1" width="294" />
								</tr>
								<tr id="ButtonShow">
									<h3c:select label="模块logo参数" property="iconcls" codeType="ICONCLS" value="panel" width="294" />
									<h3c:textEdit label="模块logo路径" property="iconurl" width="280" />
								</tr>
								<tr id="ButtonShow1" style="display: none;">
									<h3c:textEdit label="按钮ID" property="buttonid" width="280" />
									<h3c:textEdit label="模块logo路径" property="iconurl" width="280" disabled="true" />
								</tr>
								<tr>
									<h3c:textEdit label="自定义模块参数1" property="param1" width="280" />
									<h3c:textEdit label="自定义模块参数2" property="param2" width="280" />
								</tr>
								<tr>
									<h3c:select label="是否记录日志" property="log" codeType="YESNO" value="0" width="294" />
									<h3c:select label="是否缓存页面" property="cache" codeType="YESNO" value="0" width="294" />
								</tr>
								<tr>
									<h3c:textEdit label="资源持有者" property="owner" width="280" />
									<h3c:dateEdit label="创建时间" property="createdate" disabled="true" width="252" />
								</tr>
								<tr id="prsourceLine">
									<h3c:textarea label="日志信息主要字段" property="prsource" cols="60" rows="4" width="722" resize="none" ></h3c:textarea>
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
<script type="text/javascript" src="pages/portal/wbms/authmgmt/resource/Resource.js"></script>