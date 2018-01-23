/**
 * *打开新增页面
 */

function showAddSysprmDlg() {
	h3c.showWindowWithSrc("addSysprm", "系统参数维护", 590, 250,
			"sysParamController.do?showAddSysprm",null,null,"icon-list-alt");

}
// 选中函数
function getChecked(gridId) {
	var nums = 0;
	var checkboxs = document.getElementsByName(gridId + "checkcheckbox");
	for (var i = 0; i < checkboxs.length; i++) {
		var e = checkboxs[i];
		if (e.checked == true) {
			nums++;
		}
	}
	return nums;
}

// 删除数据字典信息
function deleteSysprm() {
	if (getChecked("system") > 0) {
		h3c.confirm("是否确认删除？", function() {
			var params = {};
			params.time = new Date();
			params.codeData = h3c.getGridJsonData('system');
			h3c.doEvent("sysParamController.do?deleteSysprm", params, function(
					rs) {
				h3c.alert("删除成功!", function() {
					h3c.reset();
				});
			});
		});
	} else {
		h3c.alert("请选中要删除的信息");
	}
}

/**
 * 编辑
 * 
 * @param obj
 * @returns {String}
 */
function doEdit(obj) {
	var str = JSON.stringify(obj);
	var mt = obj.maintstate;
	if (mt == 1) {
		return "<div><a href=\"javascript:void(0);\" onclick='showUpdateSysprm(" + str + ")'><i class=\"icon-pencil\"></i></a></div>";
	} else {
		return "<div><a href=\"javascript:void(0);\" ><i class=\"icon-pencil h3c-check-col-gray \"></i></a></div>";
	}
}
// 查询
function searchSysprm() {
	var params = {};
	params.paramcode = $('#paramCode').val();
	params.paramname = $('#paramName').val();
	params.maintstate = $('#maintState').val();
	h3c.doLoadGrid('system', params, true);
}
function showUpdateSysprm(obj) {
	var paramcode = obj.paramcode;
	var paramname = obj.paramname;
	var paramvalue = obj.paramvalue;
	var paramexplain = obj.paramexplain;
	var maintstate = obj.maintstate;
	h3c.showWindowWithSrc("updateSysprm", "修改系统参数维护", 590, 260,
			"sysParamController.do?showUpdateSysprm&paramcode=" + paramcode
					+ "&paramname=" + paramname + "&paramvalue=" + paramvalue
					+ "&paramexplain=" + paramexplain + "&maintstate="
					+ maintstate + "",null,null,"icon-list-alt");
}

function doLoadGrid() {
	var params = {};
	h3c.doLoadGrid('system', params, true);
}
doLoadGrid();

// GRID高度自适应
function initHeight() {
	var height = document.documentElement.clientHeight;
	var abcHeight = $("#abc").height();
	var queryHeight = $("query").height();
	var systemPageBarHeight = $("#systemPageBar").height();
	$("#systemDiv").height(
			height - abcHeight - queryHeight - systemPageBarHeight - 166);
}
$(window).resize(function() {
	initHeight();
});
initHeight();