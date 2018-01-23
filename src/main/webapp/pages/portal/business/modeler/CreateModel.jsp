<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<style type="text/css">
html, body {
	height: 100%;
	font-family: Microsoft YaHei;

}

#sub{
	width:58px;
	height:30px;
	font-family:"Microsoft YaHei" ! important;
	font-size:14px;
	font-weight: bold;
	color:#666666;
	border:1px solid #c8c6c6;
	white-space:nowrap;
	background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
    background-repeat: repeat-x;
    display: inline-block;
}
#back{
    width:58px;
	height:30px;
	font-family:"Microsoft YaHei" ! important;
	font-size:14px;
	font-weight: bold;
	color:#666666;
	border:1px solid #c8c6c6;
	white-space:nowrap;
	background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
    background-repeat: repeat-x;
    display: inline-block;

}
#back:hover{
    background-color: #217BCC;
	background-image: linear-gradient(to bottom, #217BCC, #217BCC);
    background-repeat: repeat-x;
    border: 1px solid #217BCC;
    color: #FFFFFF;
}

#sub:hover {
	background-color: #217BCC;
	background-image: linear-gradient(to bottom, #217BCC, #217BCC);
    background-repeat: repeat-x;
    border: 1px solid #217BCC;
    color: #FFFFFF;
} 
</style>
<h3c:head />
<div style="height: 100%; width: 100%; border: 0px solid red; background: #fff;">
<h3c:form action="ModelController.do?create" name="ProcessInformation">
   <table
		style=" background: #fff;">
		<tr style="height: 5px;"></tr>
		<tr>
			<td style="width: 35px;">
			</td>
			<h3c:textEdit label="名称" property="name" width="350" 
				placeholder="请输入流程名称" required="true"/>
			<td>
			<i class="icon-question-sign icon-large" title="只能输入英文字母" style="cursor: pointer; vertical-align: middle;"></i>
			</td>
				
		</tr>
		<tr style="height: 5px;"></tr>
		<tr>
			<td style="width: 35px;"></td>
			<h3c:textEdit label="KEY" property="key" width="350"
				placeholder="请输入KEY值" required="true" />
			<td>
			 <i class="icon-question-sign icon-large" title="只能输入英文字母" style="cursor: pointer; vertical-align: middle;"></i>
			</td>
		</tr>
		<tr style="height: 15px;"></tr>
		<tr>
			<td style="width: 35px;"></td>
			<h3c:textarea label="描述" property="description" width="365"/>
		</tr>
		<tr style="height: 5px;"></tr>
		<tr>
			<td></td>
			<td></td>
			<td>
			<input type="button" value="创建" id="sub" >
			<input type="button" value="重置" id="back" >
			</td>
		</tr>
		<tr style="height: 5px;"></tr>
	</table>
	</h3c:form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/portal/business/modeler/CreateModel.js">

</script>
