<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/layout/css/login.css" />
<link rel="shortcut icon" type="text/css" href="${pageContext.request.contextPath }/images/portal.ico" />
<script type="text/javascript" src="${pageContext.request.contextPath }/layout/lib/jquery/jquery-1.8.3.js" ></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/layout/lib/jquery/jquery.md5.js" ></script>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<script type="text/javascript">
	window.document.oncontextmenu = function() {
		return false;
	}
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/layout/lib/jquery/jquery-jrumble.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/layout/lib/jquery/jquery.tipsy.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/pages/login/Login.js"></script>
<style>
html,body{
	height:100%;
}
.text_success {
	font-size: 14px;
	text-align: center;
	margin: 0px 0 35px 0;
	line-height: 25px;
	position: absolute;
	left: 50%;
	width: 200px;
	top: 50%;
	z-index: 50;
	margin-left: -100px;
	text-transform: uppercase;
	padding: 20px 0px;
	margin-top: -100px;
	display: none;
}

.text_success img {
	
}

.text_success span {
	display: block;
	padding: 10px;
	color: #CCC;
}

#login .logo {
	width: 500px;
	height: 51px;
}
</style>
</head>
<body onload="" class="imc_ui_log_body" style="over-flow:hidden;">
<img style="position:absolute;top:0px;left:0px;width:100%;height:100%;z-index:-1;" src="${pageContext.request.contextPath}/images/login/login.png"/>
<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
	vertical-align: middle;
}

button[id='loginCmd'] {
	background: rgb(144, 194, 241) !important;
	color: #ffffff;
	width: 266px;
	height: 40px;
	margin: 0;
	border: none;
	-moz-border-radius: 0px 0px 0px 0px !important;
	-webkit-border-radius: 0px 0px 0px 0px !important;
	border-radius: 0px 0px 0px 0px !important;
}
textarea {
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}

input.ui-inputfield{
  text-align: left;
  padding:13px !important;
  font-size: 14px !important;
}

input.ui-inputfield:focus{
  outline: -webkit-focus-ring-color auto 5px;
  outline-color: -webkit-focus-ring-color;
  outline-style: auto;
  outline-width: 5px;
}


input.ui-inputtext, input.ui-password {
	width: 222px;
	height: 40px !important;
	border: none !important;
	display: inline-block;
	background: transparent !important;
	line-height: 14px;
}

textarea:focus {
	border-color: rgba(82, 168, 236, 0.8);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0 none;
}

input:focus {
	outline: 0 none !important;
}

img {
	border: none;
	margin: 0;
	padding: 0;
}

ul, ol, dl {
	padding: 0;
	margin: 0;
	list-style: none;
}

a:hover {
	text-decoration: underline;
}

select, input, button, button img, label {
	vertical-align: middle;
}

.ui-menu {
	width: 22em !important;
}

.ui-message-error {
	padding: 0;
	margin: 0;
}

.ui-message-error .ui-message-error-detail {
	color: rgb(251, 176, 59);
}

.csm-browser-tip {
	color: #ffffff;
}

.csm-browser-link {
	color: #7DB4D8;
}
</style>
	<div class="text_success">
		<span><img
			src="${pageContext.request.contextPath }/images/login/loader_green.gif"
			alt="Please wait" /></span> <span>登陆成功!请稍后....</span>
	</div>
	<div style="position:absolute;top:0px;color:#ededed;font-family:Microsoft YaHei;font-size:14px;height:30px;line-height:30px;padding-left:15px;padding-top: 14px;"><img style="padding-bottom: 4px;" src="${pageContext.request.contextPath }/images/login/icon_cloud_logo.png">&nbsp;CCPL-TS-CD Portal</div>
	<div id="login" style="scrolling: no;height:100%;">
		<div class="ui-widget-loginframe"  style="border:0px solid red;height:100%;">
		    
			<form id="loginForm" name="loginForm" method="post"
				action="${pageContext.request.contextPath }/loginController.do?login"
				check="${pageContext.request.contextPath }/loginController.do?checkuser">
				
				<span id="j_id_k"></span>
				<div class="imc_ui_log_content" style="border:0px solid red;height:100%;">

					<div class="imc_ui_log_box" style="margin-top: 140px;">

						<div class="imc_ui_log_box_center" style="margin-top: 0px;">
							<table width="100%" border="0" cellspacing="8" cellpadding="0"
								style="margin-top: 0px;">
								<tbody>
									<tr>
										<td rowspan="2">
											<div class="imc_ui_log_box_center_top"
												style="margin-bottom:15px;">
												<table id="loginTitle" width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tbody>
													    <tr>
													       <td align="center" valign="center">
													         <img style="padding-bottom: 6px;"  src="${pageContext.request.contextPath }/images/login/cloud_logo.png">
													       </td>
													    </tr>
														<tr>
															<td valign="top" align="center" style="color:#ffffff;font-size:21px;font-weight:bold;font-family:Microsoft YaHei;">
															        定制开发门户网站开发框架
															</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div class="imc_ui_log_box_center_left"
												style="display: inline-block;border:0px solid red;">
												<table width="100%" border="0" cellspacing="8"
													cellpadding="0">
													<tbody>
														<tr>
															<td>
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tbody>
																		<tr>
																			<td><div class="imc_ui_bg_user">
																					<table width="100%" border="0" cellspacing="0"
																						cellpadding="0">
																						<tbody>
																							<tr>
																								<td><img
																									src="${pageContext.request.contextPath}/images/login/icon_user.png"
																									align="absmiddle" alt=""
																									style="margin-left: 11px; margin-right: 18px;">
																								</td>
																								<td><input id="loginname" name="loginname"
																									type="text" maxlength="32" tabindex="1"
																									class="ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all"
																									role="textbox" aria-disabled="false" 
																									nullmsg="请输入用户名!" aria-readonly="false"
																									placeholder="用户名"></td>
																							</tr>
																						</tbody>
																					</table>
																				</div></td>
																		</tr>
																		<tr>
																			<td><div id="j_id_t" aria-live="polite"></div></td>
																		</tr>
																	</tbody>
																</table>
															</td>
														</tr>
														<tr>
															<td>
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tbody>
																		<tr>
																			<td>
																				<div class="imc_ui_bg_pwd">
																					<table width="100%" border="0" cellspacing="0"
																						cellpadding="0">
																						<tbody>
																							<tr>
																								<td><img
																									src="${pageContext.request.contextPath}/images/login/icon_pwd.png"
																									align="absmiddle" alt=""
																									style="margin-left: 11px; margin-right: 18px;">
																								</td>
																								<td><input id="passwd" name="passwd"
																									type="password"
																									class="ui-inputfield ui-password ui-widget ui-state-default ui-corner-all"
																									maxlength="32" tabindex="2" nullmsg="请输入密码!"
																									role="textbox" aria-disabled="false"
																									aria-readonly="false" placeholder="密码"></td>
																							</tr>
																						</tbody>
																					</table>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<td><div id="j_id_10" aria-live="polite"></div></td>
																		</tr>
																	</tbody>
																</table>
															</td>
														</tr>
														<tr>
															<td>
																<div class="imc_ui_log_errmsg2">
																	<input type="hidden" id="errMsg" name="errMsg" value="">
																	<div id="alertMessage"></div>
																	<span id="fff"></span>
																	<div id="j_id_13" aria-live="polite"></div>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
												<div class="imc_ui_log_box_center_bottom"
													style="margin-top: 30px; margin-left: 8px;">
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tbody>
															<tr>
																<td valign="middle"><button id="loginCmd"
																		name="loginCmd"
																		class="h3c-button"
																		tabindex="4" type="button"
																		onclick="login()" >
																		<span class="ui-button-text ui-c">登录</span>
																	</button></td>
															</tr>
															<tr>
																<td valign="middle">
																   <font style="font-weight:bold;font-size:12px;font-family:Arial;width:100%;text-align:left;border:0px solid red;color: #FFFFFF;">
																	     推荐使用的浏览器及版本为：Chrome 35+、Firefox 30+、IE9+
																	     &nbsp;&nbsp;&nbsp;&nbsp;推荐分辨率显示宽度为1280+
																   </font>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</td>
										<td style="height: 30px;"></td>

									</tr>
									<tr>
										<td>
											<table>
												<tr>
													<td>
														<div class="imc_ui_log_separate"
															style="border: 1px solid white;filter: alpha(opacity = 50);opacity: 0.5;-moz-opacity: 0.5;filter: progid:DXImageTransform.Microsoft.Alpha(opacity=50);height: 230px; margin-left: 60px;"></div>
													</td>
													<td>
														<div class="imc_ui_log_illustration"
															style="margin-left: 46px; margin-top: 0px;">
															<img
																src="${pageContext.request.contextPath}/images/login/login_illustration.png"
																align="absmiddle">
														</div>
													</td>
												</tr>

											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</div>



					</div>


					<div class="imc_ui_log_box_bottom" ></div>
					
				</div>
				<input type="hidden" name="loginForm_SUBMIT" value="1"><input
					type="hidden" name="javax.faces.ViewState"
					id="javax.faces.ViewState"
					value="lMp1ry8K5RhTB7k4YGW21GJcRYFeKupwbcFiggHO/kNHuFrg">
			</form>
			<div style="font-weight:bold;font-size:12px;color:#6FA9DB;font-family:Arial;width:100%;position:absolute;bottom:18px;margin-bottom:0px;text-align:center;border:0px solid red;">
				&copy;Copyright 2004-2017 H3C Technologies Co.,Ltd. All rights reserved
			</div>
		</div>
	</div>
	
	<div id="textarea_simulator"
		style="position: absolute; top: 0px; left: 0px; visibility: hidden;"></div>
	<div>
		<object id="ClCache" click="sendMsg" host="" width="0" height="0"></object>
	</div>
<script type="text/javascript">
	$("#loginTitle").width($(".imc_ui_bg_user").width());
	
	function initHeight() {
		var height = document.body.clientHeight;
		var h = (height-679)/2+140;
		$(".imc_ui_log_box").css("margin-top",""+h+"px");
	}
	$(window).resize(function() {
		initHeight();
	});
	initHeight();
</script>
</body>
</html>