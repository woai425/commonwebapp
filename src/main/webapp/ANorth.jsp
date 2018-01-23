<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/stylesheets/theme.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/lib/font-awesome/css/font-awesome.css" />
<link rel="shortcut icon" type="text/css"
	href="${pageContext.request.contextPath}/images/portal.ico" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layout/lib/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layout/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layout/lib/bootstrap/js/bootstrap-growl.js"></script>
<style>

#userIcon:hover {
	cursor: pointer;
}

.admin_header {
	width: 100%;
	height: 50px;
	margin: 0px;
	background-color: #3498DB;
	leftmargin: 0;
	topmargin: 0;
	margin: 0 0 0 0;
	padding: 0px;
}

.admin_header i {
	font-size: 16px;
	color: #fff;
	font-weight: normal;
}

.admin_title {
	width: 100%;
	min-width: 180px;
	margin: 0px;
	margin-left: 10px;
	padding: 0px 0px 0px 0px;
	text-align: left;
	font-family: Microsoft YaHei;
	font-size: 18px;
	color: #FFFFFF;
	font-weight: normal;
	text-decoration: none;
}

.admin_right {
	margin: 0px;
	min-width: 20px;
	padding: 0px 0px 0px 0px;
	text-align: left;
	font: 16px arial;
	color: #FFFFFF;
	font-weight: bold;
	text-decoration: none;
}

.admin_right .loginname {
	font: 13px arial;
}

#headItems td:hover {
	cursor: pointer;
	background: #2980B9;
}

.fold {
	width: 50px;
}

.fold:hover {
	cursor: pointer;
	background: #2980B9;
}

</style>
<script type="text/javascript">
	var userid = '${sessionScope.cUser.id}';
</script>

<table class="admin_header">
	<tr>
		<td width="16%"><div class="admin_title">
				<i style="font-size: 22px;" class="icon-cloud">&nbsp;&nbsp;</i>H3C
				Portal
			</div></td>
		<td width="54%" style="height: 100%;"><iframe src="Itopology.jsp"
				height="40px" width="100%" scrolling="no" frameborder="0"
				allowtransparency="true" style="background-color: #3498DB;"></iframe></td>
		<td width="25%" style="border: 0px solid red; height: 100%;">
			<table id="headItems" align="center"
				style="text-align: center; width: 100%; height: 100%; leftmargin: 0; topmargin: 0; margin: 0 0 0 0; padding: 0 0 0 0;">
				<tr style="border: 0px solid red; height: 100%;">
					<td width="15%"><i class="icon-envelope-alt"></i></td>
					<td width="15%"><a href="javascript:void(0)"
						onclick="shoucang(document.title,window.location)"><i
							class="icon-star-empty"></i></a></td>
					<td width="15%"><i id="iframeScrollBar"
						class="icon-resize-full" onclick="changeIframeScrollStyle()"
						title="显示下拉滚动轴"></i></td>
					<td id="userTd" width="40%" 
						style="border: 0px solid red; text-align: right;" class="admin_right">
						<div id="userDiv" style="display: inline; border: 0px solid red;margin-right:10px;">
						 	<img
								src="${pageContext.request.contextPath}/images/wbms/north/icon_user_online_28x28.png"
								style="vertical-align: middle;" title="用户"> <font
								class="loginname">${sessionScope.cUser.username}</font> 
								<i style="font-size: 13px;"></i>								
						</div>
					</td>
					<h3c:select property="select" width="130" onchange="changeChoice()" data="['1', '查看个人信息'],['2', '修改个人信息'],['3', '修改密码']">
						</h3c:select>
					
					<td width="15%" align="center"><i class="icon-off"
						onclick="javascript:logout();"></i></td>

				</tr>
			</table>
		</td>
	</tr>
</table>

<script type="text/javascript">

	function changeChoice(){
		if($("#select").val()=="1"){
				h3c.showWindowWithSrc("openPasswordForShow", "用户信息查看", 460, 600,
						"passwordController.do?openPasswordForShow&userid="
								+ userid,null,null,null,"images/wbms/north/icon_user_online_28x28.png");
		}
		if($("#select").val()=="2"){
			h3c.showWindowWithSrc("openPassword", "用户信息修改", 460, 500,
					"passwordController.do?openPassword&userid="
							+ userid,null,null,null,"images/wbms/north/icon_user_online_28x28.png");
		}
		if($("#select").val()=="3"){
			h3c.showWindowWithSrc("openUpdatePassword", "用户密码修改", 460, 270,
					"passwordController.do?openUpdatePassword&userid="
							+ userid,null,null,null,"images/wbms/north/icon_user_online_28x28.png");
		}
	}
	
	var timeout = false;
	function getInfo(){
		var url = 'loginController.do?checkValidTime';
		$.ajax({
			type : "POST",
			url : url,
			data : {
				"loginName" : "${sessionScope.cUser.loginname}",
			},
			dataType : "json",
			success : function(rs) {
				if(rs){
					timeout = true;
				}else{					 
					$.bootstrapGrowl('<strong>警告：</strong>'+"您的密码已过期，请及时更新！",{
						type:'warn',
						offset:{from: 'top', amount: 50},
						align:'right',
						width:240,
						delay:2000,
						allow_dismiss:false,
						stackup_spacing: 10
					});  
				}				
			},
			error : function() {
				    timeout = true;
					h3c.error("AJAX请求异常！");
			},
			async : false	
		});
	}
	function time(){
		if(timeout) return;
		getInfo();		
		setTimeout(time,10000);
	}
	$(function() {
		$(".admin_header").animate({
			height : "50px"
		}, 500);
		time();
		
	})
	//设置为主页 
	function SetHome(obj, vrl) {
		try {
			obj.style.behavior = 'url(#default#homepage)';
			obj.setHomePage(vrl);
		} catch (e) {
			if (window.netscape) {
				try {
					netscape.security.PrivilegeManager
							.enablePrivilege("UniversalXPConnect");
				} catch (e) {
					alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
				}
				var prefs = Components.classes['@mozilla.org/preferences-service;1']
						.getService(Components.interfaces.nsIPrefBranch);
				prefs.setCharPref('browser.startup.homepage', vrl);
			} else {
				alert("您的浏览器不支持，请按照下面步骤操作：1.打开浏览器设置。2.点击设置网页。3.输入：" + vrl
						+ "点击确定。");
			}
		}
	}
	//加入收藏 兼容360和IE6 
	function shoucang(sTitle, sURL) {
		try {
			window.external.addFavorite(sURL, sTitle);
		} catch (e) {
			try {
				window.sidebar.addPanel(sTitle, sURL, "");
			} catch (e) {
				alert("加入收藏失败，请使用Ctrl+D进行添加");
			}
		}
	}

 /* $(function() {
		var margin = ($("#userTd").width() - $("#userDiv").width()) / 2;
		$("#userDiv").css("margin-left", margin);
		$("#userDiv").click(
				function() {
					h3c.showWindowWithSrc("openPassword", "用户信息修改", 460, 600,
							"passwordController.do?openPassword&userid="
									+ userid,null,null,null,"images/wbms/north/icon_user_online_28x28.png");
				});
	}) */ 

	function logout() {
		h3c.confirm(
						'确定要退出系统吗?',
						function(btn) {
							window.top.location.href = '${pageContext.request.contextPath}/loginController.do?logout';
						}, null, "提示");
	}

	function changeIframeScrollStyle() {
		var isIE = (window.navigator.userAgent.indexOf('MSIE') !== -1 || window.navigator.userAgent
				.indexOf('rv:11') !== -1);
		var adminIframe = parent.document.getElementById("adminWeblcome");
		if (adminIframe) {
			var obj = document.getElementById("iframeScrollBar");
			if (obj.className == 'icon-resize-full') {
				obj.className = 'icon-resize-small';
				obj.title = "屏蔽下拉滚动轴";
				adminIframe.scrolling = "yes";
				if (isIE) {
					parent.window.location.reload();
					$('#adminWeblcome', window.parent.document).attr(
							'scrolling', 'yes');
					$('#adminWeblcome', window.parent.document).attr(
							'src',
							$('#adminWeblcome', window.parent.document).attr(
									'src'));//IE必须执行两次刷新才有效果
				}
				adminIframe.contentWindow.location.reload(true);
			} else {
				obj.className = 'icon-resize-full';
				obj.title = "显示下拉滚动轴";
				adminIframe.scrolling = "no";
				if (isIE) {
					$('#adminWeblcome', window.parent.document).attr(
							'src',
							$('#adminWeblcome', window.parent.document).attr(
									'src'));//IE必须执行两次刷新才有效果
				}
				adminIframe.contentWindow.location.reload(true);
			}
		}
	}
</script>