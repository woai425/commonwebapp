/**
 * *********************************************************************
 * 
 * SysLog.js
 * 系统日志查询
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月23日 下午4:22:03
 * @revision    $Id:  *
 **********************************************************************
 */

function doLoadGrid() {
	var params = {};
	params.logtype = document.getElementById("logtype").value;
	params.logPrcol1 = document.getElementById("logPrcol1").value;
	params.logDigest = document.getElementById("logDigest").value;
	h3c.doLoadGrid('syslog', params, true);
}

$(document).ready(function(){
	doLoadGrid();
});


//GRID高度自适应
function initHeight() {
	var height = document.documentElement.clientHeight;
	var toolBarHeight = $("#syslogToolBar").height();
	var titleHeight = $("h4").height();
	var hrHeight = $("hr").height();
	var codePageBarHeight = $("#syslogPageBar").height();
	//alert(height-toolBarHeight-titleHeight-hrHeight-codePageBarHeight);
	$("#syslogDiv").height(height-toolBarHeight-titleHeight-hrHeight-codePageBarHeight-106);
}
$(window).resize(function() {
	initHeight();
});
initHeight();