<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<h3c:form action="grantRoleController.do?doGrantRole" name="doGrantRole">
<div id="addResourceContent" style="height: 100%;  background: #fff;">
            <h3c:groupbox property="adb" title="">
            <table style="width:280px;"><tr>
            <td style="width:130px;font-size:15px;font-family:Microsoft YaHei;">授权管理</td>
				 <td  align="right" width="130px;">
		  		   <h3c:button property="save" text="保存" fontAwesome="icon-save" handler="doSave()"/>
		  		 </td>
		  	</tr></table>
		     </h3c:groupbox>
<h3c:hidden property="roleid" value="${roleid }" />
<table style="height: 100%;width: 100%;background: #fff;">
	<tr >
		<td style="vertical-align: top;">
		<div style="height:400px;width: 100%;background: #fff;overflow: auto;">
		<h3c:treeMenu dataurl="grantRoleController.do?getTreeMenu" property="roleTree" fontsize="13px"/>
		</div>
		</td>

	</tr>
</table>
</div>
</h3c:form>
<script>
window.onload = function(){
	getTree();
};

function getTree(){
	var params = {};
	params.roleid = document.getElementById("roleid").value;
	h3c.doEvent("grantRoleController.do?getRoleTree", params, function(rs) {
		var myobj=eval(rs.data);
		for(var i=0;i<myobj.length;i++){
			if(document.getElementById(myobj[i].functionid) != null&&document.getElementById(myobj[i].functionid) != ""&&document.getElementById(myobj[i].functionid) != "undefined"){
				document.getElementById(myobj[i].functionid).checked = true;
			}
		}
		formatJson();
	});
}

function doSave(){
	var params = {};
	params.roleid = document.getElementById("roleid").value;
	params.treeInfo = formatJson();
	h3c.doEvent("grantRoleController.do?doGrantRole", params, function(rs) {
		h3c.alert("授权成功!",function(){
			h3c.getRootPageObject().doLoadGrid();
			h3c.closeWindow("openGrantRolePage");
		});
	});
}

function formatJson(){
	var json = "";
	var num = 0;
	var doGrantRole = document.doGrantRole;
	for(var i = 0;i<doGrantRole.length;i++){
		if(doGrantRole[i].type == "checkbox"){
			json = json + doGrantRole[i].id + ":"+doGrantRole[i].checked + ",";
		}
	}
	return json;
}
  
</script>