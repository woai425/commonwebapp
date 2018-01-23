<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<html>
<head>
<h3c:head />
</head>
<body>
	<h3c:form action="ddprmController.do?saveDdprm" name="Ddprm">
		<table
			style="height: 100%; width: 100%; border: 0px solid red; background: #fff;">
			<tr style="height: 5px;"></tr>
			<tr>
				<td style="width: 35px;"></td>
				<h3c:textEdit label="代码" property="code" labelAlign="true"
					required="true" />
				<h3c:textEdit label="代码名" property="codename" labelAlign="true"
					required="true" />
			</tr>
			<tr>
				<td style="width: 35px;"></td>
				<h3c:textEdit label="代码值" property="codevalue" labelAlign="true"
					required="true" />
				<h3c:textEdit label="代码名称" property="codeexplain" labelAlign="true"
					required="true" />
			</tr>
			<tr>
				<td style="width: 35px;"></td>
				<h3c:textEdit label="参数分类" property="codetype" labelAlign="true" />
				<h3c:select property="codestate" label="有效标志" required="true"
					labelAlign="true" codeType="USEFUL" ></h3c:select>
			</tr>
			<tr>
				<td style="width: 35px;"></td>
				<h3c:select property="maintstate" label="维护标志" required="true"
					labelAlign="true" codeType="YESNO" ></h3c:select>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td>
					<div style="width: 220px;" align="right">
						<h3c:button property="dosave" text="保存" fontAwesome="icon-save"
							handler="saveDdprm()" />
					</div>
				</td>
			</tr>
			<tr style="height: 5px;"></tr>
		</table>
	</h3c:form>
</body>
<script type="text/javascript">
	//保存
	function saveDdprm() {
		h3c.submit("Ddprm", function() {
			h3c.getRootPageObject().searchDdprm();
			document.getElementById("codevalue").value = "";
			document.getElementById("codeexplain").value = "";
		});
	}
</script>
</html>