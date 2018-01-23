/***********************************************************************
*
* ${Resource.js}
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     ${lfw2082}<br/>
* @create-time ${2016.01.15} ${08:51:00}
* @revision    $$Id:  1.0
***********************************************************************/
var globleGroupid = null;
//获取组织信息
function getGroupInfo(id, url){
	globleGroupid = id;
	$.ajax({
		type : 'get',
		url : 'userController.do?getGroupInfo',
		async : true,
		contentType : '',
		data : {
			'groupid' : id + ""
		},
		success : function(rs) {
			h3c.autoFillPage(rs.data);
			loadGrid(id);
		},
		error : function(rs) {
			h3c.alert("系统异常！");
		}
	});
}


//查询函数
function loadGrid(id) {
	var params = {};
	params.groupid = id;
	h3c.doLoadGrid("user", params, true);
}

function loadGrid2() {
	var params = {};
	params.groupid = document.getElementById("groupid").value;
	params.loginname = document.getElementById("loginname").value;
	params.username = document.getElementById("username").value;
	params.usertype = document.getElementById("usertype").value;
	h3c.doLoadGrid("user", params, true);
}

//增加用户
function openAddUser(){
	var groupid = document.getElementById("groupid").value;
	if(groupid != null&&groupid != ""&&groupid != "null"){
		h3c.showWindowWithSrc("addUser", "新增用户", 400, 446, "addUserController.do?openAddUser&groupid="+groupid,null,null,"icon-user");
	}else{
		h3c.alert("请先选择要添加的组织！");
	}
}

//添加已有用户
function addExistUser(){
	var groupid = document.all.groupid.value;
	if(groupid != null&&groupid != ""&&groupid != "null"){
		h3c.showWindowWithSrc("addExistUser", "用户添加", 680, 556, "addExistUserController.do?openAddExistUser&groupid="+groupid,null,null,"icon-user");
	}else{
		h3c.alert("请先选择要添加的组织！");
	}
	
}

//修改用户
/*function updateUser(obj) {
	var value = JSON.stringify(obj);
	return "<a href=\"javascript:void(0);\" onclick='openUpdateUser(" + value + ")'><i class=\"icon-pencil\"></i></a>";
}*/

//打开修改用户界面
function openUpdateUser(value){
	var groupid = document.all.groupid.value;
	h3c.showWindowWithSrc("updateUser", "更新用户", 460, 556, "userController.do?openUpdateUser&userid="+value.userid+"&groupid="+groupid,null,null,"icon-user");
}
function lockUser(value){
	h3c.confirm("是否确认加锁？",function(){
		var params = {};
		params.userid = value.userid;
		h3c.doEvent("userController.do?lockUser", params, function(rs) {
			h3c.alert("加锁成功!", function() {
						loadGrid(globleGroupid);
					});
		});
	});
}
function unlockUser(value){
	h3c.confirm("是否确认解锁？",function(){
		var params = {};
		params.userid = value.userid;
		h3c.doEvent("userController.do?unlockUser", params, function(rs) {
			h3c.alert("解锁成功!", function() {
						loadGrid(globleGroupid);
					});
		});
	});
}
//删除用户图标
/*function delUser(obj){
	var value = JSON.stringify(obj);
	return "<a href=\"javascript:void(0);\" onclick='deleteUser(" + value + ")'><i class=\"icon-trash\"></i></a>";
}*/

//删除用户
function deleteUser(value){
	h3c.confirm("是否确认删除？",function(){
		var params = {};
		params.userid = value.userid;
		params.groupid = document.getElementById("groupid").value;
		h3c.doEvent("userController.do?deleteUser", params, function(rs) {
			h3c.alert("删除成功!", function() {
						loadGrid(globleGroupid);
					});
		});
	});
}


//自适应
function initHeight() {
	var height = document.documentElement.clientHeight;
	var groupInfoHeight = $("#groupInfo").height();
	var queryConditionHeight = $("#queryCondition").height();
	var abcHeight = $("#abc").height();
	$("#userDiv").height(height - groupInfoHeight - queryConditionHeight - abcHeight - 186);
	$("#tree").height(height - 96);
}

//自适应
$(window).resize(function() {
	initHeight();
});

window.onload = function(){
	initHeight();
};
function operateUser(obj) {
	var value = JSON.stringify(obj);
	if(obj.acctlockflag=="1"){
		return "<a href=\"javascript:void(0);\" onclick='openUpdateUser(" + value + ")'><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='deleteUser(" + value + ")'><i class=\"icon-trash\"></i></a>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='unlockUser(" + value + ")'><i class=\"icon-unlock\"></i></a>";
	}
	return "<a href=\"javascript:void(0);\" onclick='openUpdateUser(" + value + ")'><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='deleteUser(" + value + ")'><i class=\"icon-trash\"></i></a>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='lockUser(" + value + ")'><i class=\"icon-lock\"></i></a>";	
}
function isLock(obj) {
	var value = JSON.stringify(obj);
	if(obj.acctlockflag=="1"){
		return "<i class=\"icon-lock\"></i>";
	}
	return "<i class=\"icon-unlock\"></i>";
	
}

