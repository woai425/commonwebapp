<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<html>
<head>
<h3c:head />
</head>
<body>         <h3c:form action="sysParamController.do?saveSysprm" name="Sysprm">
				 <table style="height: 100%; width: 100%; border: 0px solid red; background: #fff; " >
				   <tr style="height:5px;" ></tr>	
					 <tr >
						<td style="width:35px;"></td>
						<h3c:textEdit label="参数代码" property="paramcode" labelAlign="true" required="true"/>
					    <h3c:textEdit label="参数名" property="paramname" labelAlign="true" required="true"/>
					 </tr>
					<tr>
						<td style="width:35px;"></td>
						<h3c:textEdit label="参数值" property="paramvalue" labelAlign="true" required="true"/>
					    <h3c:textEdit label="参数说明" property="paramexplain" labelAlign="true" required="true"/>
					</tr>
					<tr>
						<td style="width:35px;"></td>
						<h3c:select property="maintstate" label="参数可维护标志" required="true" labelAlign="true" codeType="YESNO" value="2"></h3c:select>
					</tr>
					<tr>
					<td></td>
					<td></td>
					<td >
					<div style="width:220px;"align="right">
					   <h3c:button property="dosave" text="保存" fontAwesome="icon-save" handler="saveSysprm()" /> 
					</div>
					</td>
					</tr>
					<tr style="height:5px;"></tr>
				</table>
				</h3c:form>
</body>
<script type="text/javascript">
//保存
function saveSysprm() {
		
		 h3c.submit("Sysprm",function(){
			 h3c.alert("增加成功!", function() {
				 h3c.getRootPageObject().doLoadGrid();
				 h3c.closeWindow("addSysprm");
			 });
		 });		
}
</script>
</html>