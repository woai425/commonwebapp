<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>

<!DOCTYPE html>
<html>
<head>
<title>PORTAL</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/lib/bootstrap/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/stylesheets/theme.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/lib/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layout/css/html/html.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layout/css/grid/grid.css"/>
<link rel="shortcut icon" type="text/css"
	href="${pageContext.request.contextPath}/images/portal.ico" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layout/lib/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layout/lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/basejs/h3c.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/basejs/zDrag.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/basejs/zDialog.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layout/lib/bootstrap/js/bootstrap-datetimepicker.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layout/lib/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>
<script type="text/javascript"> 
	$(document).ready(function(){ 
		document.onkeydown=function(evt){ 
			if(evt.keyCode == 13){ 
				if (document.activeElement.type == "textarea") 
					return true;
				else 
					return false;
				} ;
        if (evt.keyCode == 8) { 
            if (document.activeElement.type == "text" || document.activeElement.type == "textarea" || document.activeElement.type == "password") { 
                if (document.activeElement.readOnly == false) 
                    return true; 
                } 
            return false; 
            };
        }}); 
</script>
<h3c:dupLoginCheck/>
<h3c:sessnTmoutCntl/>
<style type="text/css">
#h3c-menuToolBar td:hover, #h3c-simpleMenu td:hover {
	cursor: pointer;
}

#h3c-menuToolBar .shotcutI:hover, #h3c-simpleMenu .shotcutI:hover {
	color: #fff;
	cursor: pointer;
}

#h3c-menuToolBar td {
	text-align: center;
	color: #979898;
	font-size: 20px;
	width: 20%;
}

#h3c-menuToolBar img {
	display: block;
	margin: auto;
	width: 30px;
	height: 25px;
}

#h3c-simpleMenu td {
	height: 40px;
	font-size: 20px;
	text-align: center;
	color: #979898;
}

#h3c-simpleMenu img {
	display: block;
	margin: auto;
	width: 30px;
	height: 30px;
}

.contentHolder {
	position: relative;
	margin: 0px auto;
	padding: 0px;
	overflow: hidden;
}

::-webkit-scrollbar-track-piece {
	background-color: #2C2E2F;
}
</style>
<link
	href="${pageContext.request.contextPath}/layout/lib/scrollbar/perfect-scrollbar.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/layout/lib/scrollbar/perfect-scrollbar.js"></script>
<script
	src="${pageContext.request.contextPath}/layout/lib/scrollbar/jquery.mousewheel.js"></script>
<script type="text/javascript">
	if ((navigator.userAgent.indexOf('MSIE') >= 0 || navigator.userAgent.indexOf('rv:11') >= 0)
			&& (navigator.userAgent.indexOf('Opera') < 0)) {
		$("#menuAndToolBar").css("overflow-y", "hidden");
		jQuery(document).ready(function($) {
			"use strict";
			$("#menuAndToolBar").perfectScrollbar();
		});
	}
	$(
			function() {
				if ((navigator.userAgent.indexOf('MSIE') >= 0 || navigator.userAgent
						.indexOf('rv:11') >= 0)
						&& (navigator.userAgent.indexOf('Opera') < 0)) {

				} else {
					$("#menuAndToolBar").css("overflow-y", "auto");
				}
			})
</script>
</head>
<body style="overflow: hidden;">
    <div id="basicWindow" style="display: none;"></div>
	<div id="mask" class="mask"
		style="display: none; text-align: center; overflow: auto;z-index: 10000;position: absolute;">
		<table width="100%" height="100%">
			<tr>
				<td align="center" valign="middle">
					<div id="portalWaitingContainer" style="vertical-align: middle;"
						align="center"></div> <span id="saveSuccess"></span>
				</td>
			</tr>
		</table>
	</div>

	<h3c:portalLayout width="100%">
		<h3c:north height="50">
			<jsp:include page="/ANorth.jsp" flush="true"></jsp:include>
		</h3c:north>
		<h3c:middle>

			<h3c:west width="250" valign="top" align="left">
				<div id="h3c-simpleMenu" style="width: 0px;">
					<table style="width: 90%;">
						<tr>
							<td class="shotcutI" onclick="menuShow()" valign="middle"
								align="left"><div
									style="width: 30px; height: 30px; background: #000; text-align: center; margin: auto;">
									<i style="display: block; padding-top: 5px;"
										class="icon-align-justify"></i>
								</div></td>
						</tr>

						<tr>
							<td class="shotcutImg" style=""><img
								onmouseout="shrink(this)" onclick="funJump(1)"
								onmouseover="expand(this)"
								src="${pageContext.request.contextPath }/images/wbms/middle/west/menutitle/Home.png" /></td>
						</tr>
						<tr>
							<td class="shotcutImg" style=""><img
								onmouseout="shrink(this)" onmouseover="expand(this)"
								src="${pageContext.request.contextPath }/images/wbms/middle/west/menutitle/list.png" /></td>
						</tr>
						<tr>
							<td class="shotcutImg" style=""><img
								onmouseout="shrink(this)" onmouseover="expand(this)"
								src="${pageContext.request.contextPath }/images/wbms/middle/west/menutitle/Service.png" /></td>
						</tr>
						<tr>
							<td class="shotcutImg" style=""><img
								onmouseout="shrink(this)" onmouseover="expand(this)"
								src="${pageContext.request.contextPath }/images/wbms/middle/west/menutitle/users.png" /></td>
						</tr>

					</table>
				</div>
				<div id="menuAndToolBar" style="background: #2c2e2f; width: 250px;"
					class="contentHolder">
					<div id="h3c-menuToolBar"
						style="height: 40px; border-bottom: 1px solid #374451;">
						<table
							style="width: 80%; height: 100%; margin: auto; text-align: center;"
							align="center">
							<tr>
								<td class="shotcutImg" style=""><img id="homeIcon"
									onclick="funJump(1)" onmouseout="shrink(this)"
									onmouseover="expand(this)"
									src="${pageContext.request.contextPath }/images/wbms/middle/west/menutitle/Home.png" /></td>
								<td class="shotcutImg" style=""><img
									onmouseout="shrink(this)" onmouseover="expand(this)"
									src="${pageContext.request.contextPath }/images/wbms/middle/west/menutitle/list.png" /></td>
								<td class="shotcutImg" style=""><img
									onmouseout="shrink(this)" onmouseover="expand(this)"
									src="${pageContext.request.contextPath }/images/wbms/middle/west/menutitle/Service.png" /></td>
								<td class="shotcutImg" style=""><img
									onmouseout="shrink(this)" onmouseover="expand(this)"
									src="${pageContext.request.contextPath }/images/wbms/middle/west/menutitle/users.png" /></td>
								<td class="shotcutI" valign="middle" onclick="menuHide()"><div
										style="width: 30px; height: 27px; background: #000; text-align: center;">
										<i style="display: block; padding-top: 4px;"
											class="icon-align-justify"></i>
									</div></td>
							</tr>
						</table>
					</div>
					<h3c:accordionMenu usage="portal" fontsize="13px" style="2"
						property="abc" width="100%" 
						dataurl="${pageContext.request.contextPath }/rsrcFilterController.do?filterRsrc"
						contentIframeId="welcome" />
				</div>
			</h3c:west>

			<h3c:center valign="top" align="left">
				<iframe id="welcome" frameborder="no" border="0"
					class="portal_framework_iframe"
					style="padding-left: 0px; width: 100%; height: 100%;"
					marginwidth="0" marginheight="0" scrolling="no"
					allowtransparency="yes" src="Welcome.jsp"></iframe>
			</h3c:center>
		</h3c:middle>
	</h3c:portalLayout>

	<script>
		var shrinkFlag = false;
		function initHeight() {
			$("#h3c-simpleMenu").hide();
			var height = document.documentElement.clientHeight;
			var width = document.documentElement.clientWidth;
			$("#adminWeblcome").height(height - $("#northLayoutId").height());
			$("#abc").height(
					height - $("#northLayoutId").height()
							- $("#h3c-menuToolBar").height() - 2);
			//$("#adminWeblcome").width(width - 250);
		}
		$(window).resize(
				function() {
					var height = document.documentElement.clientHeight;
					$("#adminWeblcome").height(
							height - $("#northLayoutId").height());
					var width = document.documentElement.clientWidth;
					$("#abc").height(
							height - $("#northLayoutId").height()
									- $("#h3c-menuToolBar").height() - 2);
					if (!shrinkFlag) {
						$("#adminWeblcome").width(width - 250);
					}else{
						$("#adminWeblcome").width(width-40);
					}
					
				});
		initHeight();

		function menuHide() {
			shrinkFlag = true;
			if ((navigator.userAgent.indexOf('MSIE') >= 0)
					&& (navigator.userAgent.indexOf('Opera') < 0)) {
				$("#menuAndToolBar").hide();
				$("#h3c-menuToolBar").hide();
				$("#abc").hide();

				$("#westLayoutId").css("width", "40px");
				$("#h3c-simpleMenu").show();
				$("#h3c-simpleMenu").width(40);

				var width = document.documentElement.clientWidth - 40;
				$("#adminWeblcome").width(width);
				$("#westLayoutId").css("background", "#333");
			} else {
				$("#menuAndToolBar").animate({
					width : '0px',
					opacity : '0'
				}, "200");
				$("#menuAndToolBar").hide(10);
				$("#h3c-menuToolBar").hide();
				$("#abc").hide();

				$("#westLayoutId").css("width", "40px");
				$("#h3c-simpleMenu").show(100);
				$("#h3c-simpleMenu").animate({
					width : '40px',
					opacity : '1'
				}, "200");
				var width = document.documentElement.clientWidth - 40;
				$("#adminWeblcome").animate({
					width : width,
					opacity : '1'
				}, "200")
				$("#westLayoutId").css("background", "#333");
			}
		}

		function menuShow() {
			shrinkFlag = false;
			if ((navigator.userAgent.indexOf('MSIE') >= 0)
					&& (navigator.userAgent.indexOf('Opera') < 0)) {
				$("#h3c-simpleMenu").hide();

				var width = document.documentElement.clientWidth - 250;
				$("#adminWeblcome").width(width);

				$("#westLayoutId").css("width", "250px");
				$("#menuAndToolBar").width(250);
				$("#menuAndToolBar").show();
				$("#h3c-menuToolBar").show();
				$("#abc").show();
				$("#westLayoutId").css("background", "#F4F4F4");
			} else {
				$("#h3c-simpleMenu").animate({
					width : '0px',
					opacity : '1'
				}, "200");
				$("#h3c-simpleMenu").hide();

				var width = document.documentElement.clientWidth - 250;
				$("#adminWeblcome").animate({
					width : width,
					opacity : '1'
				}, "200");

				$("#westLayoutId").css("width", "250px");
				$("#menuAndToolBar").animate({
					width : '250px',
					opacity : '1'
				}, "180");
				$("#menuAndToolBar").show(200);
				$("#h3c-menuToolBar").show();
				$("#abc").show();
				$("#westLayoutId").css("background", "#333");
			}
		}
		function expand(img) {
			$(img).animate({
				height : "30px",
				width : "35px"
			}, 400);
		}
		function shrink(img) {
			$(img).animate({
				height : "25px",
				width : "30px"
			}, 400);
		}

		function funJump(obj) {
			if (obj == "1") {
				document.getElementById('welcome').src = "Welcome.jsp";
			}
		}
		
		var resource = '${resource}';
		if(resource != null && resource != undefined && resource != ""){
			document.getElementById('welcome').src = resource;
		}
		
	</script>
</body>
</html>