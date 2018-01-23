<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<html>
<head>
<h3c:head />
</head>
<body>         <h3c:form action="roleController.do?saveRole" name="Role">
				 <table style="height: 100%; width: 100%; border: 0px solid red; background: #fff; " >
				   <tr style="height:5px;" ></tr>	
					<tr>
						<td style="width:35px;"></td>
						<h3c:textEdit label="角色名称" property="rolename"  required="true" labelAlign="true"/>
					</tr>
					<tr>
						<td style="width:35px;"></td>
						<h3c:textEdit label="角色描述" property="roledesc" labelAlign="true"/>
					</tr>
			<tr>
				<td style="width: 35px;"></td>
				<td align="left" style="font-family: Microsoft YaHei;">
					<span style="font-size: 14px; font-family: Microsoft YaHei;" id="lable">
						<table style="border-style: none">
							<tr>
								<td>
								<label style="font-family: Microsoft YaHei; text-align: left">
									<span style="font-size: 14px; font-family: Microsoft YaHei;">状态&nbsp;</span>
								</label>
								</td>
							</tr>
							<tr>
								<td>
									<select style="font-size: 14px; font-family: Microsoft YaHei; border: 1px solid #cccccc; background-color: #ffffff; width: px;"	name="status" id="status">
										<option value="0">无效</option>
										<option value="1" selected="selected">有效</option>
									</select>
								</td>
							</tr>
						</table>
					</span>
				</td>
			</tr>
			<tr height="10px">
			</tr>
			<tr>
				<td></td>
				<td>
					<div style="width: 220px;" align="right">
						<h3c:button property="dosave" text="保存" fontAwesome="icon-save" handler="saveRole()" />
					</div>
				</td>
			</tr>
			<tr style="height: 16px;"></tr>
		</table>
	</h3c:form>
</body>
<script type="text/javascript">
//保存

	function saveRole() {
		h3c.submit("Role", function(res) {
			h3c.alert("保存成功!", function() {
				h3c.getRootPageObject().doLoadGrid();
				h3c.closeWindow("addRole");
			});
		}, function(res) {
			h3c.error(res.msg);
		});
	}

</script>
</html>