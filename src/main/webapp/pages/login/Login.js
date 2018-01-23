/**
 * 登录js操作
 */

// 点击登录
function login() {
	submit();
}

// 回车登录
$(document).keydown(function(e) {
	if (e.keyCode == 13) {
		submit();
	}
});
// 表单提交
function submit() {
	var submit = true;
	$("input[nullmsg]").each(function() {
		if ($("#" + this.name).val() == "") {
			showError($("#" + this.name).attr("nullmsg"), 300);
			jrumble();
			setTimeout('hideTop()', 500);
			submit = false;
			return false;
		}
	});
	if (submit) {
		hideTop();
		showError("正在验证用户 &nbsp;"+"<img src=\""+getContextPath()+"/images/login/loading.gif\" style=\"width:16px;\"/>");
		setTimeout("Login()", 200);
	}
}

function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}

// 登录处理函数
function Login() {
	var actionurl = $('form').attr('action');// 提交路径
	var checkurl = $('form').attr('check');// 验证路径
	/*
	var formData = new Object();
	var data = $(":input").each(function() {
		formData[this.name] = $("#" + this.name).val();
	});
	*/
	var formData = new Object();
	formData['loginname'] = $("#loginname").val();
	formData['passwd'] = $.md5($("#passwd").val());
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url : checkurl,// 请求的action路径
		data : formData,
		error : function(rs) {// 请求失败处理函数
		},
		success : function(rs) {
			var type = typeof(rs);
			if(type=="string"){
				var e = eval('('+rs+')');
				showError(e.msg);
			}
			else{
				if (rs.success) {
					loginsuccess();
					setTimeout("window.location.href='"+actionurl+"';",2000);
				} else {
					showError(rs.msg);
				}
			}
			
		}
	});
}

// 验证通过加载动画
function loginsuccess() {
	$("#login").animate({
		opacity : 1,
		top : '49%'
	}, 200, function() {
		$('.userbox').show().animate({
			opacity : 1
		}, 500);
		$("#login").animate({
			opacity : 0,
			top : '60%'
		}, 500, function() {
			$(this).fadeOut(200, function() {
				$(".text_success").slideDown();
				$("#successLogin").animate({
					opacity : 1,
					height : "200px"
				}, 1000);
			});
		});
	});
}
function showSuccess(str) {
	$('#alertMessage').removeClass('error').html(str).stop(true, true).show()
			.animate({
				opacity : 1,
				right : '0'
			}, 500);
}

function onfocus() {
	if ($(window).width() > 480) {
		$('.tip input').tipsy({
			trigger : 'focus',
			gravity : 'w',
			live : true
		});
	} else {
		$('.tip input').tipsy("hide");
	}
}

function hideTop() {
	$('#alertMessage').animate({
		opacity : 0,
		right : '-20'
	}, 500, function() {
		$(this).hide();
	});

	$('#fff').animate({
		opacity : 0,
		right : '-20'
	}, 500, function() {
		$(this).hide();
	});
}

// 显示错误提示
function showError(str) {
	$('#alertMessage').addClass('error').html("<span style=\"font-family:'Microsoft YaHei',微软雅黑;font-size:14px;\">"+str+"</span>").stop(true, true).show()
			.animate({
				opacity : 1,
				right : '0'
			}, 200);

}

// 表单晃动
function jrumble() {
	$('.ui-widget-loginframe').jrumble({
		x : 4,
		y : 0,
		rotation : 0
	});
	$('.ui-widget-loginframe').trigger('startRumble');
	setTimeout('$(".ui-widget-loginframe").trigger("stopRumble")', 500);
}

// 加载信息
function loading(name, overlay) {
	$('body').append(
			'<div id="overlay"></div><div id="preloader">' + name + '..</div>');
	if (overlay == 1) {
		$('#overlay').css('opacity', 0.1).fadeIn(function() {
			$('#preloader').fadeIn();
		});
		return false;
	}
	$('#preloader').fadeIn();
}

function unloading() {
	$('#preloader').fadeOut('fast', function() {
		$('#overlay').fadeOut();
	});
}

$(document).ready(function(){
	document.getElementById("loginname").focus();
});