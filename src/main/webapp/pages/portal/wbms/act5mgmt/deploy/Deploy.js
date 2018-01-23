//选中函数
function getChecked(gridId){
	var nums = 0;
	var checkboxs = document.getElementsByName(gridId+"checkcheckbox");
	for(var i = 0;i< checkboxs.length;i++){
		var e = checkboxs[i];
		if(e.checked == true){
			nums ++;
		}
	}
	return nums;
}
/**
 * *打开新增页面
 */
function showAddDdprmDlg() {
	h3c.showWindowWithSrc("deployProcess", "部署流程定义", 560, 148,
			"deployController.do?showDeployProcDef",null,null,"icon-book");

}
// 删除数据字典信息
function deleteProcessDeploy() {
	if(getChecked("activiti") > 0){
		h3c.confirm("是否确认删除？", function() {
			var params = {};
			params.time = new Date();
			params.activitiData = h3c.getGridJsonData('activiti');
			h3c.doEvent("deployController.do?delete", params, function(rs) {
				h3c.alert("删除成功!", function() {
					h3c.reset();
				});
			});
		});
	}else{
		h3c.alert("请选中要删除的信息");
	}	
}
// 查询
function searchProcessDeploy() {
	var params = {};
	params.name = $('#search').val();
	params.distinct = "0"; 
	h3c.doLoadGrid('activiti', params, true);
}


function doEdit(obj) {
	var str = JSON.stringify(obj);
	return "<a href=\"javascript:void(0);\" onclick='showProcessDgrm(" + str + ")'>查看流程图</a>";
}

function showProcessDgrm(obj){
	h3c.showWindowWithHtml('actProcessImage',"流程图",900,520,"<img id=\"processImage\" style=\"width: 100%;height: 100%;\" src=\""+contextPath+"/deployController.do?showProcessDgrm&&deploymentId="+obj.deployid+"\">",null,null,"icon-picture");
}

function doLoadGrid() {
	var params = {};
	h3c.doLoadGrid('activiti', params, true);
}
doLoadGrid();

//GRID高度自适应
function initHeight() {
	var height = document.documentElement.clientHeight;
	var toolBarHeight = $("#activitiToolBar").height();
	var titleHeight = $("h4").height();
	var hrHeight = $("hr").height();
	var codePageBarHeight = $("#activitiPageBar").height();
	$("#activitiDiv").height(height-toolBarHeight-titleHeight-hrHeight-codePageBarHeight-106);
}
$(window).resize(function() {
	initHeight();
});
initHeight();
