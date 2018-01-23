/***********************************************************************
*
* ${Resource.js}
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     ${s11972}<br/>
* @create-time ${2016.01.07} ${08:51:00}
* @revision    $$Id:  1.0
***********************************************************************/

function getNodeInfo(id, url) {
	$.ajax({
		type : 'get',
		url : 'resourceController.do?getNodeInfo',
		async : true,
		contentType : '',
		data : {
			'functionid' : id + ""
		},
		success : function(rs) {
			h3c.autoFillPage(rs.data);
			getParentName(rs.data.parent);
			showType();
		},
		error : function(rs) {
			h3c.alert("系统异常！");
		}
	});
}

//自适应
function initHeight() {
	var height = document.documentElement.clientHeight;
	$("#resource").height(height);
}

//自适应
$(window).resize(function() {
	var height = document.documentElement.clientHeight;
	$("#resource").height(height);
});

//自适应
$(document).ready(function() {
	initHeight();
	showType();
	getWidth();
});

//获取组织的名字
function getParentName(id){
	$.ajax({
		type : 'get',
		url : 'resourceController.do?getNodeInfo',
		async : true,
		contentType : '',
		data : {
			'functionid' : id + ""
		},
		success : function(rs) {
			var node = rs.data;
			if(node != null&&node != "null"){
				$("#parentName").val(node.title);
			}
		},
		error : function(rs) {
		}
	});
}

//增加节点
function addNode(){
	var functionid = $("#functionid").val();
	var title = $("#title").val();
	if (functionid == null || functionid == "") {
		h3c.alert("请选择要添加在哪个节点下！");
		return;
	}
	$("#functionid").val(null);
	$("#parent").val(functionid);
	$("#parentName").val(title);
	$("#title").val(null);
	$("#orderno").val(null);
	$("#description").val(null);
	$("#location").val(null);
	$("#type").attr("value","1");
	$("#active").attr("value","1");
	$("#iconcls").attr("value","panel");
	$("#iconurl").val(null);
	$("#uptype").attr("value","0");
	$("#param1").val(null);
	$("#param2").val(null);
	$("#log").attr("value","0");
	$("#cache").attr("value","0");
	$("#owner").val(loginname);
	$("#createdate").val(null);
	$("#prsource").val('[{property:"objectid",label:"对象ID"}]');
}

//界面调整
function showType(){
	var type = $("#type").val();
	if(type == "2"){
		$("#ButtonShow").hide();
		$("#ButtonShow1").show();
		$("#buttonid").prop('required','true');
		if($("#ButtonShow1 td:first").html().indexOf('*')<0){
			$("#ButtonShow1 td:first").prepend("<font color=red>*</font>");
		}
		(function(){
			$('#buttonid').blur(function(){
				var content = $('#buttonid').val();
				if($.trim(content) == ""){
					$("#buttonid").attr('placeholder','必填项...');
					$("#buttonid").attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid red;width:280px;');
				}else{
					$('#buttonid').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:280px;');
				}
			})
			
		})();
		(function(){$('#buttonid')
			.focus(function(){
				$('#buttonid').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:280px;');
			})
		})();
		
		//$("#buttonid").removeAttr('disabled');
	}else{
		$("#ButtonShow").show();
		$("#ButtonShow1").hide();
		$("#buttonid").removeAttr("required");
	}
}
//保存节点信息
function saveNode() {
	h3c.submit("Resource",null,function(rs){
		h3c.error(rs.msg);
	});
}

//删除节点信息
function deleteNode() {
	h3c.confirm("是否确认删除？",function(){
			var params = {};
			params.functionid = document.Resource.functionid.value;
			h3c.doEvent("resourceController.do?deleteNode", params, function(rs) {
				h3c.alert("删除成功!", function() {
							h3c.reset();
						});
			});
	});
}

//宽度自适应
function getWidth(){
	var width0=$("#getWidth").children('td').eq(0).width();
	var width1=$("#getWidth").children('td').eq(1).width();
	var width2=$("#getWidth").children('td').eq(2).width();
	var widthTotal = width1 + width2 + 290;
//	$("#buttonid").width(width1 + width2 + 290);
//	$("#prsource").width(width1 + width2 + 290);
}