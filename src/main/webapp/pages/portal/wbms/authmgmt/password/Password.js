$(function(){
	getGroupInfo();
});

function getGroupInfo(){
	var params = {};
	var groupid = document.getElementById("groupid").value;
	params.userid = document.getElementById("userid").value;
	h3c.doEvent("passwordController.do?getGroupInfo", params, function(rs) {
		var groupInfo = eval('(' + rs.data + ')');
		$('#tree1').tree({
            data: groupInfo,
            autoOpen: true,
            closedIcon: '',
            openedIcon: '',
            dragAndDrop: false,
            selectable: true,
            slide: false,
            onCanSelectNode: function(node) {
                if (node.id == groupid) {
                    // Nodes without children can be selected
                    return true;
                }
                else {
                    // Nodes with children cannot be selected
                    return false;
                }
            }
		});	
		
		var node = $('#tree1').tree('getNodeById', groupid);
		$('#tree1').tree('selectNode', node);
		
		$("#tree1 div:has(a)").addClass("icon-folder-open");
		$("#tree1 span:not(.jqtree-title-folder)").prepend("&nbsp;&nbsp;");
		$("#tree1 span:not(.jqtree-title-folder)").addClass("icon-file-alt");
	});
};

function selectGroup(){
	
}

function closeWindow(){
	h3c.closeWindow("openPassword");
}


function updateUser(){
	if(checkPassword() == true&&checkLength() == true){
		h3c.submit("commForm", function(rs) {
			h3c.alert("修改成功!", function() {
				h3c.closeWindow("openPassword");
			});
		}, function(rs) {
			alert("修改失败！" + rs.msg);
		});
	}
}

function checkPassword(){
	var passwd = document.getElementById("passwd").value;
	var confirmPasswd = document.getElementById("confirmPasswd").value;
	if(confirmPasswd != passwd){
		h3c.alert("两次输入的密码不一致");
		return false;
	}else{
		return true;
	}
}

function checkLength(){
	var password = document.getElementById("password").value;
	var password_new = document.getElementById("passwd").value;
	if(password == password_new){
		return true;
	}else{
		if(password_new.length <= 20){
			return true;
		}else{
			h3c.alert("密码长度不能超过20！");
			return false;
		}
	};
	
}