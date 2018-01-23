<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/stylesheets/theme.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/lib/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/css/grid/grid.css" />
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layout/lib/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layout/lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/basejs/h3c.js"></script>
<style type="text/css">
html,body{height:100%;font-family:Arial;background:#EEEEEE;}
.h3c-funcone td,.h3c-funcone td table{
	-webkit-transition: background 0.5s ease;
    -moz-transition: background 0.5s ease;
	background:#3B5998;
	color:#ffffff;
}
.h3c-funcone:hover td,.h3c-funcone:hover table{
	background:#ffffff;
	color:#3B5998;
	cursor:pointer;
}
.h3c-functwo td,.h3c-functwo td table{
	border:0px;
	-webkit-transition: background 0.5s ease;
    -moz-transition: background 0.5s ease;
	background:#55acee;
	color:#ffffff;
}
.h3c-functwo:hover td,.h3c-functwo:hover table{
	background:#ffffff;
	color:#55acee;
	cursor:pointer;
}
.h3c-functhree td,.h3c-functhree td table{
	border:0px;
	-webkit-transition: background 0.5s ease;
    -moz-transition: background 0.5s ease;
	background:#DD4B39;
	color:#ffffff;
}
.h3c-functhree:hover td,.h3c-functhree:hover table{
	background:#ffffff;
	color:#DD4B39;
	cursor:pointer;
}

#shotcut div{
	float:left;
	background-color:#fff;
	height:100%;
	width:23.5%;
	/*filter:progid:DXImageTransform.Microsoft.Shadow(color=#cccccc,direction=120,strength=2);兼容ie*/
	-moz-box-shadow: 2px 2px 5px #ddd;/*兼容firefox*/
	-webkit-box-shadow: 2px 2px 5px #ddd;/*兼容safari或chrome*/
	box-shadow:2px 2px 5px #ddd;/*兼容opera或ie9*/ 
}
#shotcut div:hover .h3c-shotcutdescription{
	color:#ffffff;
}
.h3c-shotcutdescription{
	vertical-align:top;
	padding:0px 0px 0px 10px;
	color:#aaa;font-size:16px;
	font-weight:normal;
	color:#bbbbbb;
} 
.shotcutone table{
	-webkit-transition: background 0.5s ease;
    -moz-transition: background 0.5s ease;
}
#shotcut div table tr:second{
	color:#aaaaaa;
}
.shotcutone table:hover{
	cursor:pointer;
	background:#486887;
	color:#ffffff;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#bbbbbb,direction=120,strength=4);/*兼容ie*/
	-moz-box-shadow: 4px 4px 8px #ccc;/*兼容firefox*/
	-webkit-box-shadow: 4px 4px 8px #ccc;/*兼容safari或chrome*/
	box-shadow:4px 4px 8px #ccc;/*兼容opera或ie9*/ 
}
.shotcuttwo table{
	-webkit-transition: background 0.5s ease;
    -moz-transition: background 0.5s ease;
}
.shotcuttwo table:hover{
	cursor:pointer;
	background:#00A8EC;
	color:#ffffff;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#bbbbbb,direction=120,strength=4);/*兼容ie*/
	-moz-box-shadow: 4px 4px 8px #ccc;/*兼容firefox*/
	-webkit-box-shadow: 4px 4px 8px #ccc;/*兼容safari或chrome*/
	box-shadow:4px 4px 8px #ccc;/*兼容opera或ie9*/ 
}
.shotcutthree table{
	-webkit-transition: background 0.5s ease;
    -moz-transition: background 0.5s ease;
}
.shotcutthree table:hover{
	cursor:pointer;
	background:#99CCFF;
	color:#ffffff;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#bbbbbb,direction=120,strength=4);/*兼容ie*/
	-moz-box-shadow: 4px 4px 8px #ccc;/*兼容firefox*/
	-webkit-box-shadow: 4px 4px 8px #ccc;/*兼容safari或chrome*/
	box-shadow:4px 4px 8px #ccc;/*兼容opera或ie9*/ 
}
.shotcutfour table{
	-webkit-transition: background 0.5s ease;
    -moz-transition: background 0.5s ease;
}
.shotcutfour table:hover{
	cursor:pointer;
	background:#009F3C;
	color:#ffffff;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#bbbbbb,direction=120,strength=4);/*兼容ie*/
	-moz-box-shadow: 4px 4px 8px #ccc;/*兼容firefox*/
	-webkit-box-shadow: 4px 4px 8px #ccc;/*兼容safari或chrome*/
	box-shadow:4px 4px 8px #ccc;/*兼容opera或ie9*/ 
}

#shotcut img{
	width:60px;
	height:60px;
}
#shotcut table{
	height:100%;
	width:100%;
}
#titleBar{
	color:#222;
	height:100%;
	width:96%;
	background:#fff;
	margin:auto;
}
#calendar,#ptwo{
	border:0px;
}
#bottomDiv table td{
	margin-top:0px;
	padding-top:0px;
}

#searchform input[type="text"] { 
	background:#fff;
	float:left;		
	margin:0px;
	width:180px; 
	height:38px;
	line-height:38px;
	font-size:13px;
	font-family:Microsoft YaHei;
	color:#585858;
	border:0px;
    filter：alpha(opacity=40);
	opacity: 0.4; 
	border-color: #FFFFFF;

}
#searchform input[type="button"] { background:url(${pageContext.request.contextPath}/images/menu/icon-search.png) center 11px no-repeat; cursor:pointer; margin:0px; padding:0px; width:38px; height:38px; line-height:38px; }
#searchButton {color:#ffffcolor:#585858; ff; text-transform:uppercase; border:0px; font-size:20px;cursor:pointer; float:left; overflow:visible; transition: all .3s linear; -moz-transition: all .3s linear; -o-transition: all .3s linear; -webkit-transition: all .3s linear; }
#searchform input[type="button"]{background-color:#3498DB;}
#searchform input[type="button"]:hover { background-color:#217bcc; }
#homeTools td:hover{
	background:#EEEEEE;
	cursor:pointer;
}
#topDiv{
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=2);/*兼容ie*/
	-moz-box-shadow: 2px 2px 5px #ddd;/*兼容firefox*/
	-webkit-box-shadow: 2px 2px 5px #ddd;/*兼容safari或chrome*/
	box-shadow:2px 2px 5px #ddd;/*兼容opera或ie9*/ 
}
#shadowDiv{
	height:1%;
	width:100%;
	filter: progid:DXImageTransform.Microsoft.Alpha(opacity=0);
    filter：alpha(opacity=0);
	opacity: 0; 
}
.shadow{
	-moz-box-shadow: 2px 2px 5px #ddd;/*兼容firefox*/
	-webkit-box-shadow: 2px 2px 5px #ddd;/*兼容safari或chrome*/
	box-shadow:2px 2px 5px #ddd;/*兼容opera或ie9*/ 
}
.transparent{
	filter: progid:DXImageTransform.Microsoft.Alpha(opacity=0);
    filter：alpha(opacity=0);
	opacity: 0;
}
.test{
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=2);/*兼容ie*/
	-moz-box-shadow: 2px 2px 5px #909090;/*兼容firefox*/
	-webkit-box-shadow: 2px 2px 5px #909090;/*兼容safari或chrome*/
	box-shadow:2px 2px 5px #909090;/*兼容opera或ie9*/ 
  	filter: progid:DXImageTransform.Microsoft.Alpha(opacity=50);
    filter：alpha(opacity=50);
	opacity: 0.5; 
}

.shortcutsix {
	-webkit-transition: background 0.5s ease;
    -moz-transition: background 0.5s ease;
}
.shortcutsix:HOVER tr{
	cursor:pointer;
	background-color:#13b2bd;
	color:#ffffff;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#bbbbbb,direction=120,strength=4);/*兼容ie*/
	-moz-box-shadow: 4px 4px 8px #ccc;/*兼容firefox*/
	-webkit-box-shadow: 4px 4px 8px #ccc;/*兼容safari或chrome*/
	box-shadow:4px 4px 8px #ccc;/*兼容opera或ie9*/ 
}
.shortcutfive {
	-webkit-transition: background 0.5s ease;
    -moz-transition: background 0.5s ease;
}
.shortcutfive:HOVER tr{
	cursor:pointer;
	background-color:#99eade;
	color:#ffffff;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#bbbbbb,direction=120,strength=4);/*兼容ie*/
	-moz-box-shadow: 4px 4px 8px #ccc;/*兼容firefox*/
	-webkit-box-shadow: 4px 4px 8px #ccc;/*兼容safari或chrome*/
	box-shadow:4px 4px 8px #ccc;/*兼容opera或ie9*/ 
}

.h3c-table td {
    font-size: 10px;
}
</style>

<link href="${pageContext.request.contextPath}/layout/lib/fullcalendar-1.5.3/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/layout/lib/fullcalendar-1.5.3/fullcalendar/stylecalendar.css" rel="stylesheet"/> 
</head>
<body >
<div id="back" style="width:100%;height:100%;">

<!-- <div id="topDiv" style="height:7%;background:#fff;width:100%;vertical-align:middle;">
	<div style="height:100%;width:96%;margin:auto;">
		<table style="height:100%;width:100%;">
			<tr>
				<td style="width:40%;" valign="middle">&nbsp;<i style="font-size:35px;color:#eee" class="icon-desktop"></i><span style="font-size:35px;color:#ddd;font-weight:bold;font-family:Microsoft YaHei;">&nbsp;&nbsp;H3C</td>
				<td style="width:48%;"></td>
				<td style="width:12%;height:100%;">
					<table id="homeTools" style="width:100%;height:100%;color:#79B3DA;font-size:23px;text-align:center;">
						<tr>
							<td style="border-bottom:3px solid #FB6E52"><i class="icon-refresh"></i></td>
							<td style="border-bottom:3px solid #FFCE55"><i class="icon-edit"></i></td>
							<td style="border-bottom:3px solid #2DC3E8"><i class="icon-question-sign"></i></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div> -->
<div id="shadowDiv"></div>

<div id="titleBarDiv" style="height:10%;border:0px;background:#EEEEEE;">
	<div id="titleBar" style="background:#EEEEEE;">
		<table style="height:100%;width:100%;">
			<tr><td></td></tr>
			<tr  style="height:38px;width:100%;">
				<td valign="top" style="width:100%;">
					<table class="shadow" style="height:98%;width:100%;">
						<tr>
							<td style="font-family:Microsoft YaHei;width:88%;background:#fff;">&nbsp;<i style="font-size:18px;color:#aaa;" class="icon-home"></i><span style="font-size:13px;color:#aaa;">&nbsp;主页&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;后台管理系统&nbsp;&nbsp;/&nbsp;&nbsp;</span><span style="color:#aaa;font-size:13px;">快捷方式</span></td>
							<td width="1%"></td>
							<td valign="middle" align="right" style="height:100%;width:10%;background:#fff;">
								<table style="height:100%;"  id="searchform">
									<tr >
										<td>
										   <div class="dropdown">
											    <input id="search" style="width: 180px;height: 40px;" name="search" data-toggle="dropdown" onkeyup="funSearch()" type="text" placeholder="Searching……" value="Searching……"
											           onfocus="if(this.value == 'Searching……'){this.value = '';}" 
											           onblur="if(this.value == ''){this.value = 'Searching……';this.style.color='#777';}" class="dropdown-toggle"  />										   									         
												 <ul class="dropdown-menu" style="margin: 38px 0 0 0;width: 180px;border: 0;"></ul>										  
										   </div>
										</td>
										<td> 
										   <input id="searchButton" name="button" type="button" disabled="true" style="cursor: default;"/> 
										</td>										
									</tr>
								</table>
							</td>
						</tr>
					</table>
				<td>
			</tr>
			<tr class="transparent"><td></td></tr>
		</table>
	</div>
</div>

<div id="bodyDiv" style="height:15%;width:96%;margin:0px auto;background:#eee;">
	<table id="shotcut" style="width:100%;height:100%;">
		<tr >
			<td style="height:100%;">
				<div  class="shotcutone" style="height:100%;" onclick="funJump('group')">
					<table>
						<tr>
							<td  style="width:60px;padding-left:20px;" rowspan="2"><img src="${pageContext.request.contextPath}/images/wbms/middle/center/users.png"></td>
							<td  style="vertical-align:bottom;padding:0px 0px 0px 10px;font-size:20px;font-family:Microsoft YaHei;">组织</td>
							<td rowspan="2" style="font-size:28px;font-weight:bold;"><span id="groupCount">0</span></td>
						</tr>
						<tr><td class="h3c-shotcutdescription" >group</td></tr>
					</table>
				</div>
			
				<div class="shotcuttwo" style="margin-left:2%;" onclick="funJump('user')">
					<table >
						<tr>
							<td style="width:60px;padding-left:20px;" rowspan="2"><img src="${pageContext.request.contextPath}/images/wbms/middle/center/user.png"></td>
							<td style="vertical-align:bottom;padding:0px 0px 0px 10px;font-size:20px;font-family:Microsoft YaHei;">用户</td>
							<td rowspan="2" style="font-size:28px;font-weight:bold;"><span id="userCount">0</span></td>
						</tr>
						<tr><td class="h3c-shotcutdescription">users</td></tr>
					</table>
				</div>
			
				<div class="shotcutthree" style="margin-left:2%;" onclick="funJump('role')">
					<table>
								<tr>
									<td style="width:60px;padding-left:20px;" rowspan="2"><img src="${pageContext.request.contextPath}/images/wbms/middle/center/role.png"></td>
									<td style="vertical-align:bottom;padding:0px 0px 0px 10px;font-size:20px;font-family:Microsoft YaHei;">角色</td>
									<td rowspan="2" style="font-size:28px;font-weight:bold;"><span id="roleCount">0</span></td>
								</tr>
								<tr><td class="h3c-shotcutdescription">roles</td></tr>
					</table>
				</div>
			
				<div class="shotcutfour" style="margin-left:2%;" onclick="funJump('grant')">
					<table >
								<tr>
									<td style="width:60px;padding-left:20px;" rowspan="2"><img src="${pageContext.request.contextPath}/images/wbms/middle/center/grant.png"></td>
									<td style="vertical-align:bottom;padding:0px 0px 0px 10px;font-size:20px;font-family:Microsoft YaHei;">授权</td>
									<td rowspan="2" style="font-size:28px;font-weight:bold;"><span id="grantCount">0</span></td>
								</tr>
								<tr><td  class="h3c-shotcutdescription">grant</td></tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</div>

<div id="bottomDiv" style="height:74%;border:0px solid blue;">
	<table  style="margin:auto;height:100%;width:96%;border:0px solid green;">
		<tr style="height:4%;"><td></td><td></td><td></td></tr>
		<tr id="bottomTr" style="height:92%;">
			<td  style="width:65%;" valign="top" align="left">
				<div id="hSum" class="shadow" style="height:100%;width:97%;background:#fff;">
		            <h3c:portalGrid property="syslog" height="384" isAsyncLoad="false"  padding_right="15" padding_bottom="15" rowDbClick="openSysLog"
						url="sysLogController.do?query" title="日志管理"> 
						<h3c:GridColumnModel rowHeight="36" >
							<h3c:gridColumn dataIndex="digest" header="日志摘要"  width="90" showTitle="true"/>
							<h3c:gridColumn dataIndex="opip" header="经办人IP" width="60" />
							<h3c:gridColumn dataIndex="broswer" header="浏览器" sortable="true" showTitle="true" width="40" />
							<h3c:gridColumn dataIndex="operator" header="经办者" width="40" codeType="USER" editor="select" />
							<h3c:gridColumn dataIndex="optime" header="经办时间" sortable="true" width="60" />
						</h3c:GridColumnModel>
					</h3c:portalGrid>
				</div>
			</td>
			<td height="95%" style="width:17%;" valign="top" align="left">
				<div id="verticalDiv1" style="height:100%;width:100%;margin-top:0px;border:solid 0px green;">
					<table id="bottomTable1" style="height:100%;width:92%;border:solid 0px red;">
						<tr class="" style="height:47.5%;">
							<td>
								<table class="shortcutfive" style="background-color: #fff;width:100%;height:100%;font-size:50px;text-align:center;" onclick="funJump('monitor')">
									<tr height="60%" style="width:100%;text-align: center;" >
										<td style="text-align: center;padding-top:8px;" colspan="2" >
											<img width="120" alt="" src="${pageContext.request.contextPath}/images/wbms/middle/center/monitor.png">
										</td>
									</tr>
									<tr height="40%">
										<td width="50%">
											<table style="height:70%;width:100%;">
												<tr><td style="width:13%"></td><td style="font-size:20px;text-align:left;font-family:Microsoft YaHei;">数据监控</td></tr>
												<tr><td style="width:13%"></td><td style="font-size:14px;text-align:left;">data monitor</td></tr>
											</table>
										</td>
										<td width="50%" style="text-align: center;font-size:28px;font-weight:bold;">
											<span id="monitorCount">0</span>
										</td>
									</tr>
								</table> 
							</td>
						</tr>
						<tr height="3%"></tr>
						<tr style="height:47.5%;">
							<td>
								<table class="shortcutsix" style="background-color: #fff;width:100%;height:100%;font-size:50px;text-align:center;" onclick="funJump('time')">
									<tr height="60%" style="width:100%;text-align: center;" >
										<td style="text-align: center;padding-top:8px;" colspan="2" >
											<img width="120" alt="" src="${pageContext.request.contextPath}/images/wbms/middle/center/time.png">
										</td>
									</tr>
									<tr height="40%">
										<td width="50%">
											<table style="height:70%;width:100%;">
												<tr><td style="width:13%"></td><td style="font-size:20px;text-align:left;font-family:Microsoft YaHei;">定时任务</td></tr>
												<tr><td style="width:13%"></td><td style="font-size:14px;text-align:left;">timed task</td></tr>
											</table>
										</td>
										<td width="50%" style="text-align: center;font-size:28px;font-weight:bold;">
											<span id="timeCount" >0</span>
										</td>
									</tr>
								</table> 
							</td>
						</tr>
					</table>
				</div>		
			</td>
			<td height="95%" style="width:14%;" valign="top" align="right">
				<div id="verticalDiv" style="height:100%;width:100%;margin-top:0px;">
					<table id="bottomTable" style="height:100%;width:100%;">
						<tr class="h3c-funcone" style="height:32.3%;">
							<td >
								<table style="height:100%;width:100%;font-size:50px;text-align:center;" align="center" valign="middle" onclick="funJump('resource')">
									<tr style=""><td><table style="font-size:28px;font-weight:bold;"><tr><td style="width:15%;"></td><td><span id="resourceCount">0</span></td></tr></table></td><td><i class="icon-list-ul"></i></td></tr>
									<tr>
										<td>
											<table style="height:70%;width:100%;">
												<tr><td style="width:13%"></td><td style="font-size:18px;text-align:left;font-family:Microsoft YaHei;">功能列表</td></tr>
												<tr><td style="width:13%"></td><td style="font-size:12px;text-align:left;">resource list</td></tr>
											</table>
										</td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr height="2.5%"></tr>
						<tr  class="h3c-functwo" style="height:32.3%;">
							<td >
								<table style="height:100%;width:100%;font-size:40px;text-align:center;" align="center" valign="middle" onclick="funJump('ddprm')">
									<tr ><td><table style="font-size:28px;font-weight:bold;"><tr><td style="width:15%;"></td><td><span id="ddprmCount">0</span></td></tr></table></td><td><i class="icon-book"></i></td></tr>
									<tr>
										<td>
											<table style="height:70%;width:100%;">
												<tr><td style="width:13%"></td><td style="font-size:18px;text-align:left;font-family:Microsoft YaHei;">数据字典</td></tr>
												<tr><td style="width:13%"></td><td style="font-size:12px;text-align:left;">data dict</td></tr>
											</table>
										</td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr height="2.5%"></tr>
						<tr class="h3c-functhree" style="height:32.3%;">
							<td  >
								<table style="height:100%;width:100%;font-size:50px;text-align:center;" align="center" valign="middle" onclick="funJump('sysprm')">
									<tr ><td><table style="font-size:28px;font-weight:bold;"><tr><td style="width:15%;"></td><td><span id="sysprmCount">0</span></td></tr></table></td><td><i class="icon-list-alt"></i></td></tr>
									<tr>
										<td>
											<table style="height:70%;width:100%;">
												<tr><td style="width:13%"></td><td style="font-size:18px;text-align:left;font-family:Microsoft YaHei;">系统参数</td></tr>
												<tr><td style="width:13%"></td><td style="font-size:12px;text-align:left;">system params</td></tr>
											</table>
										</td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>	
			</td>
		</tr>
		<tr style="height:4%;"><td></td><td></td><td></td></tr>
	</table>
</div>	
</div>
<script type="text/javascript">
	function openSysLog(obj){
		funJump("syslog");
	}
	function doLoadGrid() {
		var params = {};
		h3c.doLoadGrid('syslog', params, true);
	}
    function funSearch(){
    	$('.dropdown-menu').empty();
    	var params = {};
    	params.funName = document.getElementById('search').value;
    	if(params.funName==""||params.funName==null){
    		return false;
    	}
		$.ajax({
			type : "POST",
			url : "funSrchController.do?query",
			data : params,
			dataType : "json",
			success : function(rs){
				if(rs.success){
					for (var i = 0; i < rs.data.length; i++) {
						var result = rs.data[i];
						if(i>0){
							$('.dropdown-menu').append('<li class="divider"></li>');
						}
						$('.dropdown-menu').append('<li><a href="javascript:funJump(\''+result["location"]+'\');">'+result["title"]+'</a></li>');
					}
				}
			},
			error : function(rs){
				h3c.error(rs.msg);
			},
			async : true
		});
    }
/*     function funJump(location){
    	parent.document.getElementById('adminWeblcome').src="${pageContext.request.contextPath}/"+location;
    } */
    
	if((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
		var bottomTr = $("#bottomDiv").outerHeight()-40;
		$("#hSum").height(bottomTr);
		$("#ptwo").height(bottomTr);
		$("#bottomTable").height(bottomTr);
	}
	var hCalendar = document.documentElement.clientHeight*0.67*0.92-65;
	function initHeight(){
		var height = document.documentElement.clientHeight;
		var vmap_world = height*0.67*0.92-65;
		$("#main").height(vmap_world);
		$("#syslogDiv").height(height*0.6-50);
		window.setTimeout(function(){
			$(".fc-border-separate").height(height*0.67*0.92-110);
		},10);
		hCalendar = height*0.67*0.92-65;
	}
	initHeight();
	
	$(window).resize(function(){
		var height = document.documentElement.clientHeight;
		var vmap_world =height*0.67*0.92-65;
		$("#main").height(vmap_world);
		$("#syslogDiv").height(height*0.6-50);
		window.setTimeout(function(){
			$(".fc-border-separate").height(height*0.67*0.92-110);
		},10);
		hCalendar = height*0.67*0.92-65;
	});
	
	  $(function(){
		  doLoadGrid();
			/* var Script = function () {
		    var date = new Date();
		    var d = date.getDate();
		    var m = date.getMonth();
		    var y = date.getFullYear();
		    $('#calendar').fullCalendar({
		        header: {
		            left: 'prev,next today',
		            center: 'title',
		            right: 'month,agendaWeek,agendaDay'
		        },
		        selectHelper: true,
		        editable: true,
		        height:hCalendar,
		        handleWindowResize:true,
		        titleFormat:{
		        	month: 'MMMM yyyy', // September 2013
		        	week: "MMMM yyyy", // Sep 7 - 13 2013
		        	day: 'MMMM yyyy' // Tuesday, Sep 8, 2013
		        }
		    });
		}(hCalendar); */
	});  
	
    
	function funJump(obj) {
		if (obj == "group") {
			parent.document.getElementById('adminWeblcome').src = contextPath+"/groupController.do?openGroup";
		}else if(obj == "user"){
			parent.document.getElementById('adminWeblcome').src = contextPath+"/userController.do?openUser";
		}else if(obj == "role"){
			parent.document.getElementById('adminWeblcome').src = contextPath+"/roleController.do?openRole";
		}else if(obj == "grant"){
			parent.document.getElementById('adminWeblcome').src = contextPath+"/grantController.do?openGrant";
		}else if(obj == "resource"){
			parent.document.getElementById('adminWeblcome').src = contextPath+"/resourceController.do?openResource";
		}else if(obj == "ddprm"){
			parent.document.getElementById('adminWeblcome').src = contextPath+"/ddprmController.do?openDdprm";
		}else if(obj == "time"){
			parent.document.getElementById('adminWeblcome').src = contextPath+"/cronJobController.do?openCronJob";
		}else if(obj == "monitor"){
			parent.document.getElementById('adminWeblcome').src = contextPath+"/druidController.do?goDruid";
		}else if(obj == "syslog"){
			parent.document.getElementById('adminWeblcome').src = contextPath+"/sysLogController.do?openSysLog";
		}else{
			parent.document.getElementById('adminWeblcome').src = contextPath+"/sysParamController.do?openSysParam";
		}
	}
	
	function operMonitorQuery(){
		$.ajax({
			type : 'get',
			url : contextPath+'/operMonController.do?monitorQuery',
			async : true,
			contentType : '',
			data : {
				'time' : new Date()
			},
			success : function(rs) {
				$('#groupCount').html(rs.data.group);
				$('#userCount').html(rs.data.user);
				$('#roleCount').html(rs.data.role);
				$('#grantCount').html(rs.data.grant);
				$('#resourceCount').html(rs.data.resource);
				$('#ddprmCount').html(rs.data.ddprm);
				$('#sysprmCount').html(rs.data.sysprm);
				$('#timeCount').html(rs.data.time);
				$("#monitorCount").html(rs.data.monitor);
			},
			error : function(rs) {
				
			}
		});
	}
	operMonitorQuery();
	setInterval(function(){
		operMonitorQuery()
	},8000);
</script>
<script src="${pageContext.request.contextPath}/layout/lib/echarts-2.2.7/build/dist/echarts.js"></script>
<script src="${pageContext.request.contextPath}/layout/lib/fullcalendar-1.5.3/fullcalendar/fullcalendar1.5.3.min.js"></script>
</body>

</html>
