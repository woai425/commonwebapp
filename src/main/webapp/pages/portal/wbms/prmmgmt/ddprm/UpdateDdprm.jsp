<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<html>
<head>
<h3c:head />
</head>
<body>         <h3c:form action="ddprmController.do?updateDdprm" name="UpdateDdprm">
				 <table style="height: 100%; width: 100%; border: 0px solid red; background: #fff; " >
				   <tr style="height:5px;" ></tr>	
					<tr >
						<td style="width:35px;"></td>
						<h3c:hidden property="codeid" value="${syscode.codeid}"></h3c:hidden>
						<h3c:textEdit label="代码" property="code"  labelAlign="true"  required="true" readonly="true" value="${syscode.code}"/>
					    <h3c:textEdit label="代码名" property="codename" labelAlign="true" required="true"  readonly="true" value="${syscode.codename}" />
					</tr>
					<tr>
						<td style="width:35px;"></td>
						<h3c:textEdit label="代码值" property="codevalue" labelAlign="true" required="true" value="${syscode.codevalue}"/>
					    <h3c:textEdit label="代码名称" property="codeexplain" labelAlign="true" required="true" value="${syscode.codeexplain}"/>
					</tr>
					<tr>
						<td style="width:35px;"></td>
						<h3c:textEdit label="参数分类" property="codetype" labelAlign="true"  value="${(syscode.codetype==null||syscode.codetype==\"null\")?\"\":syscode.codetype}"/>
					    <h3c:select property="codestate" label="有效标志"  labelAlign="true" required="true" codeType="USEFUL"  value="${syscode.codestate}"></h3c:select>
					</tr>
					<tr>
						<td style="width:35px;"></td>
						<h3c:select property="maintstate" label="维护标志"  labelAlign="true" required="true" codeType="YESNO"  value="${syscode.maintstate}"></h3c:select>
					</tr>
					<tr >
					<td></td>
					<td></td>
					<td >
					<div style="width:220px;"align="right">
					   <h3c:button property="update" text="保存" fontAwesome="icon-save" handler="updatee()" /> 
					</div>
					</td>
					</tr>
					<tr style="height:5px;"></tr>
				</table>
				</h3c:form>
</body>

<script type="text/javascript">
//保存
function updatee() {
	h3c.submit("UpdateDdprm",function(){
		//调用父页面查询刷新结果
		h3c.getRootPageObject().searchDdprm();	
		//关闭弹出框
		h3c.closeWindow("updateDdrm");
	});
	
}

</script>
</html>