<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layout/lib/jquery/jquery.form.js"></script>
<h3c:form action="deployController.do?deploy" name="processDeploy"
	method="POST" enctype="multipart/form-data" target="impFrame">
	<table
		style="height: 100%; width: 100%; border: 0px solid red; background: #fff;">
		<tr style="height: 5px;"></tr>
		<tr>
			<td style="width: 20px;"></td>
			<h3c:textEdit label="部署名称" property="deployName" width="350"
				required="true" />
		</tr>
		<tr>
			<td style="width: 20px;"></td>
			<h3c:upLoad property="deployFile" label="流程文件(.zip)" width="302" required="true"></h3c:upLoad>
		</tr>
		<tr style="height: 2px;"></tr>
		<tr>
			<td></td>
			<td></td>
			<td>
				<div style="width: 365px;" align="right">
					<h3c:button property="dodeploy" text="部署流程" fontAwesome="icon-save"
						handler="deploy()" />
				</div>
			</td>
		</tr>
		<tr style="height: 14px;"></tr>
	</table>
	<iframe id="impFrame" name="impFrame" width="0" height="0"></iframe>
</h3c:form>
<script type="text/javascript">
	//保存
	function deploy() {
		document.getElementById("dodeploy").onclick=null;
		document.getElementById("dodeploy").disabled="disabled";
		if (document.getElementById('deployFile').value != "") {
			h3c.mask();
			$('#processDeploy').ajaxSubmit(
							{
								type : "POST",
								enctype : "multipart/form-data",
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								url : document.processDeploy.action,
								data : null,
								dataType : "json",
								success : function(rs) {
									if (rs.success) {// 请求成功
										h3c.unmask();
										h3c.alert("部署成功！", function() {
											document.getElementById("dodeploy").disabled="";
											h3c.getRootPageObject()
													.searchProcessDeploy();
											h3c.closeWindow("deployProcess");
										});

									} else {// 请求失败
										h3c.unmask();
										h3c.error("Ajax上传请求失败，请与系统管理员联系！",
												function() {
													h3c.reset();
												});
									}
								},
								error : function(rs) {
									h3c.unmask();
									h3c.error("Ajax上传请求失败，请与系统管理员联系！",
											function() {
												h3c.reset();
											});

								}
							});

		} else {
			h3c.error('请选择文件之后再做导入处理！');
		}
	}
</script>