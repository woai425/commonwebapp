//选中函数
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
/**
 * *打开新增页面
 */
function showAddDdprmDlg() {
	h3c.showWindowWithSrc("saveDdrm", "新增数字字典", 590, 325,
			"ddprmController.do?showAddDdprm", null, null, "icon-book");

}
// 删除数据字典信息
function deleteDdprm() {
	if (getChecked("code") > 0) {
		h3c.confirm("是否确认删除？", function() {
			var params = {};
			params.time = new Date();
			params.codeData = h3c.getGridJsonData('code');
			h3c.doEvent("ddprmController.do?deleteDdprm", params, function(rs) {
				h3c.alert("删除成功!", function() {
					h3c.reset();
				});
			});
		});
	} else {
		h3c.alert("请选中要删除的信息");
	}
}
// 查询
function searchDdprm() {
	var params = {};
	params.code = $('#search').val();
	params.distinct = "0";
	h3c.doLoadGrid('code', params, true);
}

// 代码查询
function distinctSearchDdprm() {
	var params = {};
	params.distinct = "1";
	params.code = $('#search').val();
	h3c.doLoadGrid('code', params, true);
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
	if (mt == 0) {
		return "<a href=\"javascript:void(0);\" disabled = 'true' ><i class=\"icon-pencil h3c-check-col-gray\"></i></a>";
	} else {
		return "<a href=\"javascript:void(0);\" onclick='showUpdateDdrm(" + str
				+ ")'><i class=\"icon-pencil\"></i></a>";
	}

}

function showUpdateDdrm(obj) {
	var codeid = obj.codeid;
	var code = obj.code;
	var codename = obj.codename;
	var codevalue = obj.codevalue;
	var codeexplain = obj.codeexplain;
	var maintstate = obj.maintstate;
	var codestate = obj.codestate;
	var codetype = obj.codetype;
	h3c.showWindowWithSrc("updateDdrm", "修改数字字典", 590, 325,
			"ddprmController.do?showUpdateDdprm&codetype=" + codetype
					+ "&codeid=" + codeid + "&codename=" + codename
					+ "&codevalue=" + codevalue + "&codeexplain=" + codeexplain
					+ "&maintstate=" + maintstate + "&codestate=" + codestate
					+ "&code=" + code + "", null, null, "icon-book");
}
function doLoadGrid() {
	var params = {};
	h3c.doLoadGrid('code', params, true);
}
doLoadGrid();

// GRID高度自适应
function initHeight() {
	var height = document.documentElement.clientHeight;
	var toolBarHeight = $("#codeToolBar").height();
	var titleHeight = $("h4").height();
	var hrHeight = $("hr").height();
	var codePageBarHeight = $("#codePageBar").height();
	// alert(height-toolBarHeight-titleHeight-hrHeight-codePageBarHeight);
	$("#codeDiv").height(
			height - toolBarHeight - titleHeight - hrHeight - codePageBarHeight
					- 106);
}
$(window).resize(function() {
	initHeight();
});
initHeight();
