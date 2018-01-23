/***********************************************************************
*
* ${Group.js}
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     ${lfw2082}<br/>
* @create-time ${2016.01.15} ${08:51:00}
* @revision    $$Id:  1.0
***********************************************************************/
//获取组织信息
function getGroupInfo(id, url){
	$.ajax({
		type : 'get',
		url : 'groupController.do?getGroupInfo',
		async : true,
		contentType : '',
		data : {
			'groupid' : id + ""
		},
		success : function(rs) {
			h3c.autoFillPage(rs.data);
			getParentName(rs.data.parentid);
		},
		error : function(rs) {
			h3c.alert("系统异常！");
		}
	});
}


//获取组织的名字
function getParentName(id){
	$.ajax({
		type : 'get',
		url : 'groupController.do?getGroupInfo',
		async : true,
		contentType : '',
		data : {
			'groupid' : id + ""
		},
		success : function(rs) {
			var node = rs.data;
			
			$("#parentName").val(node.name);
			$("#parentRate").val(node.rate);
		},
		error : function(rs) {
		}
	});
}

//增加节点
function addGroup(){
	var groupid = $("#groupid").val();
	var name = $("#name").val();
	var rate = $("#rate").val();
	var nextrate = (parseInt(rate)+1).toString();
	if (groupid == null || groupid == "") {
		h3c.alert("请选择要添加在哪个节点下！");
		return;
	}
	$("#groupid").val(null);
	$("#parentid").val(groupid);
	$("#parentName").val(name);
	$("#parentRate").attr("value",rate);
	$("#name").val(null);
	$("#description").val(null);
	$("#principal").val(null);
	$("#shortname").val(null);
	$("#address").val(null);
	$("#tel").val(null);
	$("#linkman").val(null);
	$("#type").attr("value","panel");
	$("#chargedept").val(null);
	$("#rate").attr("value",nextrate);
	$("#owner").val(null);
	$("#createdate").val(null);
	$("#status").attr("value","1");
}

//删除节点信息
function deleteGroup() {
	h3c.confirm("是否确认删除？",function(){
		var params = {
				groupid: document.Group.groupid.value
		};
		//删除之前判断该组织下是否有用户存在，
		if(hasGroupUser(params)){
			h3c.confirm("当前组织下存在用户或子组织，是否继续？",function(){
				actuallyDeleteGroup(params)
			})
		}else{
			actuallyDeleteGroup(params)
		}
	});
}

/**
 *  真正删除组织
 * @param params
 */
function actuallyDeleteGroup(params){
	h3c.doEvent("groupController.do?deleteGroup", params, function(rs) {
		h3c.alert("删除成功!", function() {
			h3c.reset();
		});
	});
}

//判断该组织下是否有用户存在
function hasGroupUser(params){
	var result = false;
	$.ajax({
		url: 'groupController.do?hasGroupUser',
		data: params,
		dataType: 'json',
		async: false,
		success: function(rs){
			result = rs;
		},
		error: function(res){
			h3c.error("Ajax请求异常!");
		}
	});
	return result;
}

//保存节点信息
function saveGroup() {
	h3c.submit("Group");
}

//自适应
function initHeight() {
	var height = document.documentElement.clientHeight;
	$("#group").height(height);
}

//自适应
$(window).resize(function() {
	var height = document.documentElement.clientHeight;
	$("#group").height(height);
});

//自适应
$(document).ready(function() {
	initHeight();
	
	$("#status").attr("disabled",true);
});

