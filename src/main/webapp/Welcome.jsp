<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/stylesheets/theme.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layout/lib/font-awesome/css/font-awesome.css" /> 
<style >
html,body{
	font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
 	background-color: #abb0b5;
	margin:0px;	
	
	filter:alpha(opacity=10);
	-moz-opacity:0.1;
	width:auto !important;
	width:100%;
}
#contentDiv_td{
	background: url("${pageContext.request.contextPath}/images/wbms/middle/center/homeBg.png") 0 0 repeat; 
}
#jvmInfoTable,#sysInfoTable,#jvmInfoTable3,#sysInfoTable4{
	color:green;
	font-weight: bold;
}
.titleBack {
	opacity:0.7;
	background-color: #777;
	color:#fff;
}
#div1 img{
	opacity:0.8;
}
.contentInner_tr{
	opacity:0.7;
	filter:alpha(opacity=70);
	background:#222;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/layout/lib/echarts-2.2.7/build/dist/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layout/lib/jquery/jquery-1.8.3.js"></script>
<body>
	<!-- <div id="div1" style="position: relative;z-index: -10">
		<img width="80%" height="70%" alt="backgroundPicture" src="/portaltaglib/images/a3.png">
	</div> -->
	<div id="outerDiv" style="width: 100%; height: 100%;text-align: center;">
		<div id="contentOuterDiv" style="width: 100%; height: 100%; text-align: center">
			<table style="width: 100%; height: 100%; text-align: center">
				<tr height="1%">
					<td width="1%"></td>
					<td width="98%"></td>
					<td width="1%"></td>
				</tr>
				<tr id="contentDiv_tr" height="100%">
					<td></td>
					<td id="contentDiv_td">
						<div id="contentInnerDiv1" style="float: left; width: 50%; height: 50%;">
							<table style="width: 100%; height: 100%; border-right: solid 0px green;">
								<tr class="titleBack" height="10%">
									<td colspan="2" style="text-align: center;vertical-align: middle;">
										java虚拟机内存使用情况
									</td>
								</tr>
								<tr class="contentInner_tr" height="90%" style="background-color: #">
									<td width="65%">
										<div id="jvmContainer" style="width: 100%; height: 100%;">画仪表盘</div>
									</td>
									<td width="35%" style="vertical-align: middle;">
										<table id="jvmInfoTable" style="width:90%;height:80%;">
											<tr >
												<td>
													<div style="width:100%;">
														<span style="">总内存</span>
													</div>
													<div style="width:100%;padding-top:8px;">
														<span id="jvm_totalMemory"> --- </span>Mb
													</div>
													<div style="width:100%;text-align: center;">
														<div style="height: 1px;width:70%;border-top:solid #ddd 1px;margin-top: 4px;"></div>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div style="width:100%;">
														<span style="">已使用</span>
													</div>
													<div style="width:100%;padding-top:8px;">
														<span id="jvm_usedMemory"> --- </span>Mb
													</div>
													<div style="width:100%;text-align: center;">
														<div style="height: 1px;width:70%;border-top:solid #ddd 1px;margin-top: 4px;"></div>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div style="width:100%;">
														<span style="">未使用</span>
													</div>
													<div style="width:100%;padding-top:8px;">
														<span id="jvm_freeMemory"> --- </span>Mb
													</div>
													<div style="width:100%;text-align: center;">
														<div style="height: 1px;width:70%;border-top:solid #ddd 1px;margin-top: 4px;"></div>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<div id="contentInnerDiv2" style="float: left; width: 50%; height: 50%;">
							<table style="width: 100%; height: 100%;">
								<tr class="titleBack" height="10%">
									<td colspan="2" style="text-align: center;vertical-align: middle;">
										系统内存使用情况
									</td>
								</tr>
								<tr class="contentInner_tr" height="90%" style="">
									<td width="35%">
										<table id="sysInfoTable" style="width:90%;height:80%;text-align: right;">
											<tr >
												<td>
													<div style="width:100%;">
														<span style="">总内存</span>
													</div>
													<div style="width:100%;padding-top:8px;">
														<span id="sys_totalMemory"> --- </span>Mb
													</div>
													<div style="width:100%;">
														<div style="float:right;height: 1px;width:70%;border-top:solid #ddd 1px;margin-top: 4px;"></div>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div style="width:100%;">
														<span style="">已使用</span>
													</div>
													<div style="width:100%;padding-top:8px;">
														<span id="sys_usedMemory"> --- </span>Mb
													</div>
													<div style="width:100%;">
														<div style="float:right; height: 1px;width:70%;border-top:solid #ddd 1px;margin-top: 4px;"></div>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div style="width:100%;">
														<span style="">未使用</span>
													</div>
													<div style="width:100%;padding-top:8px;">
														<span id="sys_freeMemory"> --- </span>Mb
													</div>
													<div style="width:100%;">
														<div style="float:right; height: 1px;width:70%;border-top:solid #ddd 1px;margin-top: 4px;"></div>
													</div>
												</td>
											</tr>
										</table>
									</td>
									<td width="65%">
										<div id="sysContainer" style="width: 100%; height: 100%;">画仪表盘</div>
									</td>
								</tr>
							</table>
						</div>
						<div id="contentInnerDiv3" style="float: left; width: 50%; height:50%;">
							<table style="width: 100%; height: 100%;">
								<tr class="titleBack" height="10%">
									<td colspan="2" style="text-align: center;vertical-align: middle;">
										java虚拟机内存使用情况
									</td>
								</tr>
								<tr class="contentInner_tr" height="90%" style="">
									<td width="100%">
										<div id="jvmContainer3" style="width: 100%; height:100%;">画仪表盘</div>
									</td>
								</tr>
							</table>
						</div>
						<div id="contentInnerDiv4" style="float: left; width: 50%; height: 50%;">
							<table style="width: 100%; height: 100%;">
								<tr class="titleBack" height="10%" >
									<td colspan="2" style="text-align: center;vertical-align: middle;">
										系统内存使用情况
									</td>
								</tr>
								<tr class="contentInner_tr" height="90%" style="">
									<td width="100%">
										<div id="sysContainer4" style="width: 100%; height:100%;">画仪表盘</div>
									</td>
								</tr>
							</table>
						</div>
					</td>
					<td></td>
				</tr>
				<tr height="2%">
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/Welcome.js"></script>
