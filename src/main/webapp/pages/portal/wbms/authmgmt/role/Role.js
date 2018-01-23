window.onload = function() {
	doLoadGrid();
	initHeight();
}
// 查询
function findRole() {
	var params = {};
	params.rolename = $('#roleName').val();
	params.roledesc = $('#roleDesc').val();
	params.owner = $('#roleOwner').val();
	h3c.doLoadGrid('ROLE', params, true);
}

function doLoadGrid() {
	var params = {};
	h3c.doLoadGrid('ROLE', params, true);
}
// 状态转化函数
function showState(value) {
	if (value.status == undefined) {
		var state = value.useful;
	} else {
		var state = value.status;
	}
	if (state == "1") {
		return "<img src='" + contextPath + "/images/role/right.gif'>";
	} else {
		return "<img src='" + contextPath + "/images/role/wrong.png'>";
	}
}

// 用户授权
function grantRole(value) {
	var roleid = value.roleid;
	return "<img src='"
			+ contextPath
			+ "/images/role/grantRole.gif'  style=\"cursor:pointer;\" onclick=\"openGrantRole('"
			+ roleid + "');\" );\">";
}

// 打开用户授权函数
function openGrantRole(roleid) {
	h3c.showWindowWithSrc("openGrantRolePage", "用户授权", 300, 460,
			"grantRoleController.do?openGrantRole&roleid=" + roleid, null,
			null, "icon-user-md");
}

// 删除具体操作
function deleteRole(value) {
	h3c.confirm("是否确认删除？", function() {
		var params = {};
		params.time = new Date();
		params.roleid = value.roleid;
		//判断角色下是否有用户
		if(hasRoleUsers(params)){
			h3c.error("当前角色下存在用户，不允许删除!");
		}else{
			$.ajax({
				type : "POST",
				url : "roleController.do?deleteRole",
				data : params,
				dataType : "json",
				success : doSuccess,
				error : doFailure,
				async : true,
			});
		}
	});
}

//判断是否角色下是否有用户
function hasRoleUsers(params){
	var result = false;
	$.ajax({
		url: 'roleController.do?hasRoleUsers',
		data:params,
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

function doSuccess(response) {
	if (response.success) {// 请求成功
		h3c.alert("删除成功",doLoadGrid)
	} else {
		h3c.error("该角色为系统默认角色，无法删除");
	}
}

function doFailure() {
	h3c.error("ajax请求异常")
}

// 删除用户图标
function deleterole(obj) {
	var value = JSON.stringify(obj);
	return "<a href=\"javascript:void(0);\" onclick='deleteRole(" + value
			+ ")'><i class=\"icon-trash\"></i></a>";
}

// 新增
function showAddRoleDlg() {
	h3c.showWindowWithSrc("addRole", "新建角色", 310, 270,
			"roleController.do?showAddRole", null, null, "icon-user-md");

}

// 自适应
function initHeight() {
	var height = document.documentElement.clientHeight;
	var abcHeight = $("#abc").height();
	var ROLEPageBarHeight = $("#ROLEPageBar").height();
	$("#ROLEDiv").height(height - abcHeight - ROLEPageBarHeight - 160);
}

$(window).resize(function() {
	initHeight();
});

// 修改角色
function doEdit(obj) {
	var str = JSON.stringify(obj);
	return "<a href=\"javascript:void(0);\" onclick='showUpdateRole(" + str
			+ ")'><i class=\"icon-pencil\"></i></a>";
}

// 打开修改用户角色页面
function showUpdateRole(obj) {
	var roleid = obj.roleid;
	h3c.showWindowWithSrc("updateRole", "修改角色", 310, 330,
			"roleController.do?showModifyRole&roleid=" + roleid, null, null,
			"icon-user-md");
}

/**
 * 显示打开角色对应用户列表
 * @param role
 * @returns {String}
 */
function showOpenRoleUsersList(role){
	var roleId = role.roleid;
	var roleName = role.rolename;
	return "<a href=\"javascript:void(0);\" onclick='openRoleUsersList(\""+roleName+"\",\"" + roleId + "\")'><i style='padding-left:10px;' class=\"icon-list icon-large\"></i></a>";
}

/**
 * 打开用户列表
 */
function openRoleUsersList(roleName,roleId){
	var url = 'roleController.do?openRoleUsersList&roleId='+roleId;
	h3c.showWindowWithSrc("roleUsersList", roleName+"角色对应用户列表", 652, 400, url, function(rs){
		
	}, null, "icon-list",null);
}