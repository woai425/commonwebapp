<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<h3c:tab data="['pageGrid','分页GRID'],['grid','普通GRID']"
	property="griddemo" handler="init">
	<h3c:tabContent property="pageGrid" active="true">
		<h3c:portalGrid property="zzw" height="400"
			url="gridController.do?query" padding_top="0" isColElastic="true">
			<h3c:toolbar property="sss">
				<h3c:textEdit property="search" placeholder="根据代码查询" />
				<td align="left"><h3c:button property="find" text="查询"
						fontAwesome="icon-search" handler="searchDdprm()" /></td>
				<td align="right" style="width: 80%"><h3c:button property="add"
						text="新建" fontAwesome="icon-plus" handler="showAddDdprmDlg()" />
					<h3c:button property="delete" text="删除" fontAwesome="icon-trash"
						handler="deleteDdprm()" /></td>
			</h3c:toolbar>
			<h3c:GridColumnModel>
				<h3c:gridColumn dataIndex="check" editor="radio" width="40"
					header="#" />
				<h3c:gridColumn dataIndex="aab001" header="测试2" sortable="true" />
				<h3c:gridColumn dataIndex="aab004" header="测试3" hidden="false" />
				<h3c:gridColumn dataIndex="aab301" header="测试4" />
				<h3c:gridColumn dataIndex="aae013" header="测试5" sortable="true" />
				<h3c:gridColumn dataIndex="edit" header="编辑" width="80"
					renderer="doSomeThing" />
			</h3c:GridColumnModel>
			<h3c:PageToolBarTag />
		</h3c:portalGrid>
	</h3c:tabContent>
	<h3c:tabContent property="grid" >
		<h3c:portalGrid property="zzw2" height="400" isColElastic="true"
			url="gridController.do?query2">
			<h3c:groupbox property="abcx" title="groupbox测试"
				style="margin-bottom: 10px;">
				<table width="100%">
					<tr>
						<h3c:textEdit property="assx" label="ssss" />
						<h3c:textEdit property="abx" label="bbbb" />
						<h3c:textEdit property="ccc" label="ccc" />
					</tr>
				</table>
			</h3c:groupbox>
			<h3c:GridColumnModel withoutRownum="true">
				<h3c:gridColumn dataIndex="check" editor="checkbox"
					header="selectall" width="40" />
				<h3c:gridColumn dataIndex="aab001" header="测试2" />
				<h3c:gridColumn dataIndex="aab004" header="测试3" hidden="false"
					 />
				<h3c:gridColumn dataIndex="aab301" header="测试4" />
				<h3c:gridColumn dataIndex="aae013" header="测试5"/>
			</h3c:GridColumnModel>
		</h3c:portalGrid>
	</h3c:tabContent>
</h3c:tab>



<script>
	function doLoadGrid() {
		var params = {};
		//params.aab004 = '天上人间';
		h3c.doLoadGrid('zzw', params, true);
		var zzwData = h3c.getGridJsonData('zzw');
		for (var i = 0; i < zzwData.length; i++) {
			if (zzwData[i].aab001 == '李四') {
				h3c.setCheckBoxDisable('zzwcheckbox' + (i + 1) + '-div');
			}
		}
	}
	function doLoadGrid2() {
		var params = {};
		params.aab004 = '天上人间';
		h3c.doLoadGrid('zzw2', params, false);
	}
	doLoadGrid();
	doLoadGrid2();
	function doSomeThing(obj, rowIndex) {
		return "<a href=\"javascript:void(0);\" onclick=\"h3c.alert('"
				+ obj.aae013 + "');\"><i class=\"icon-pencil\"></i></a>";
	}

	function test() {
		h3c.submit();
	}
	
	function init(id){
		alert(id);
	}
	/*
	$("#mask").css("height", document.documentElement.clientHeight);
	$("#mask").css("width", document.documentElement.clientWidth);
	$("#mask").show();
	$("#mask #loading").show();
	 */
</script>
