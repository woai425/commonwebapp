/***********************************************************************
*
* ${Grant.js}
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     ${s11972}<br/>
* @create-time ${2016.01.07} ${08:51:00}
* @revision    $$Id:  1.0
***********************************************************************/
var tabId = "grantSysuser";
window.onload = function(){
	var params = {};
	h3c.doLoadGrid('grantSysuser', params, true);
	initHeight(tabId);
	getGroup();
}

//加载组织信息
function getGroup() {
	var params = {};
	h3c.doEvent("grantController.do?getGroup", params, function(rs) {
		var myobj=eval(rs.data);
		var option = {
                theme:'vsStyle',
                expandLevel : 1,
                beforeExpand : function($treeTable, id) {
                    //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                    if ($('.' + id, $treeTable).length) { return; }
                    //这里的html可以是ajax请求
                    var html = "";
                    for(var i=0;i<myobj.length;i++){
                    	if(myobj[i].parentid == id){
                    		if(myobj[i].child == "0"){
                    			html = html + '<tr height="40px" id="'+myobj[i].groupid+'" pId="'+myobj[i].parentid+'">' + '<td>'+myobj[i].name+'</td>'+
                            	'<td style="text-align: center;">'+checkValue(myobj[i].shortname)+'</td>'+
                            	'<td style="text-align: center;">'+showRate(checkValue(myobj[i].rate))+'</td>'+
                            	'<td style="text-align: center;">'+checkValue(myobj[i].owner)+'</td>'+
                            	'<td style="text-align: center;">'+checkValue(myobj[i].createdate)+'</td>'+
                            	'<td style="text-align: center;">'+showGroupState(checkValue(myobj[i].status))+'</td>'+
                            	'<td style="text-align: center;">'+grantGroup(myobj[i].groupid)+'</td>'+
                            	'</tr>';
                    			/* Deleted by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
                    		}else{
                    			html = html + '<tr height="40px" id="'+myobj[i].groupid+'" pId="'+myobj[i].parentid+'" hasChild="true">' +
                    			'<td>'+myobj[i].name+'</td>'+
                            	'<td style="text-align: center;">'+checkValue(myobj[i].shortname)+'</td>'+
                            	'<td style="text-align: center;">'+showRate(checkValue(myobj[i].rate))+'</td>'+
                            	'<td style="text-align: center;">'+checkValue(myobj[i].owner)+'</td>'+
                            	'<td style="text-align: center;">'+checkValue(myobj[i].createdate)+'</td>'+
                            	'<td style="text-align: center;">'+showGroupState(checkValue(myobj[i].status))+'</td>'+
                            	'<td style="text-align: center;">'+grantGroup(myobj[i].groupid)+'</td>'+
                            	'</tr>';
                    			/* Deleted by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
                    		}
                    		
                    	}	
                    }
                    $treeTable.addChilds(html);
                    alterTable();
                },
                onSelect : function($treeTable, id) {
                    //window.console && console.log('onSelect:' + id);
                    
                }

        };
        $('#treeTable1').treeTable(option);
	});
}

//查询函数
function query(id){
	tabId = id;
	var params = {};
	params.loginname = document.all.loginname.value;
	params.username = document.all.username.value;
	h3c.doLoadGrid('grantSysuser', params, true);
	initHeight(id);
}

//用户授权函数
function doGrant(currentTab,objectid) {
	var objecttype = "0";
	if ("tab1" == currentTab) {
	} else {
		objecttype="1";
	}
	h3c.showWindowWithSrc("doGrant","授权信息窗口", 800, 410, "grantController.do?showDoGrant&objectid="
			+ objectid+"&objecttype="+objecttype+"&grant=1",null,null,"icon-stethoscope");
}

/* Deleted by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */

//组织授权
function grantGroup(objectid) {
	return "<img src='"
			+ contextPath
			+ "/images/role/grantRole.gif' title='授权！' style=\"cursor:pointer;\" onclick=\"doGrant('tab2','"
			+ objectid + "');\">";
}

/* Deleted by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */

//用户授权
function grantUser(value, params, record, rowIndex, colIndex, ds) {
	var objectid = value.userid;
	return "<img src='"
			+ contextPath
			+ "/images/role/grantRole.gif' title='授权！' style=\"cursor:pointer;\" onclick=\"doGrant('tab1','"
			+ objectid + "');\">";
}

/* Deleted by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */

//格式化时间
function formatTime(value){
	return h3c.formatTimeStamp(value.createdate);
}

//状态转化函数
function showState(value){
	if(value.status == undefined){
		var state = value.useful;
	}else{
		var state = value.status;
	}
	if(state == "1"){
		return "<img src='"
		+ contextPath
		+ "/images/role/right.gif'>";
	}else{
		return "<img src='"
		+ contextPath
		+ "/images/role/wrong.png'>";
	}
}

//GRID高度自适应
function initHeight(id) {
	var height = document.documentElement.clientHeight;
	if(id == "grantSysuser"){
		$("#grantSysuserDiv").height(height-246);
	}else{
		$("#treeTable").height(height-66);
	}
}

$(window).resize(function() {
	initHeight(tabId);
});

//检验是否为空函数
function checkValue(data){
	if(data == "null"||data == null){
		return "";
	}else{
		return data;
	}
}

//状态转换函数
function showGroupState(state){
	if(state == "1"){
		return "<img src='"
		+ contextPath
		+ "/images/role/right.gif'>";
	}else{
		return "<img src='"
		+ contextPath
		+ "/images/role/wrong.png'>";
	}
}

//级别转换函数
function showRate(rate){
	if(rate == "0"){
		return "部级";
	}else if(rate == "1"){
		return "省级";
	}else if(rate == "2"){
		return "市级";
	}else if(rate == "3"){
		return "区级";
	}else if(rate == "4"){
		return "主管部门";
	}else if(rate == "5"){
		return "街道";
	}else if(rate == "6"){
		return "社区";
	}else if(rate == "7"){
		return "单位";
	}else if(rate == "8"){
		return "个人";
	}
}

//对table进行优化
function alterTable(){
	$("tr:odd").removeClass("odd");
	$("tr:odd").removeClass("even");
	$("tr:even").removeClass("odd");
	$("tr:even").removeClass("even");
	$("tr:odd").addClass("odd");
	$("tr:even").addClass("even");
}
