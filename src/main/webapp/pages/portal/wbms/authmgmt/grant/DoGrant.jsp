<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />

<h3c:form action="grantController.do?grantObject" name="doGrant">
	<h3c:hidden property="objectid" value="${objectid }" />
	<h3c:hidden property="objecttype" value="${objecttype }"/>
	<h3c:hidden property="grant" value="${grant }"/>
	<h3c:hidden property="rolesGranted" value="${rolesGranted }" />
	<h3c:portalGrid property="rolegrid" height="246" url="grantController.do?getRoleByCondition">
		<h3c:toolbar property="btnToolBar" >
			<td align="right">
				<h3c:button property="save" text="确定" fontAwesome="icon-save" handler="saveGrant()"/>
			</td>
		</h3c:toolbar>
		<h3c:GridColumnModel withoutRownum="true">
			<h3c:gridColumn header="selectall" width="30" editor="checkbox" dataIndex="check" />
			<h3c:gridColumn header="角色id" width="" dataIndex="roleid" editor="text" hidden="true" />
			<h3c:gridColumn header="是否可分授权限" width="80" editor="checkbox" dataIndex="dispatchauth" />
			<h3c:gridColumn header="角色名" width="80" dataIndex="rolename" editor="text" />
			<h3c:gridColumn header="资源描述" dataIndex="roledesc" editor="text" width="60" />
			<h3c:gridColumn header="状态"  renderer="showState" width="20" dataIndex="status" />
		</h3c:GridColumnModel>
		<h3c:PageToolBarTag pageLimit="5" defaultLimit="5"/>
	</h3c:portalGrid>
</h3c:form>
<script type="text/javascript">
/* BEGIN: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */ 
//添加三个全局变量来记录每一行对比的信息
// var grantFlags = [];
// var dispGrantFlags = [];
// var row = 0;
/* END: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
//2017-8-7-zhoujie 获得所有的角色
var roles = ${roles};
var rolesGranted = "${rolesGranted}";
var grant = ${grant};
window.onload = function(){
	
	var params = {};
	params.objectid=document.all.objectid.value;
	params.grant=document.all.grant.value;
	h3c.doLoadGrid("rolegrid", params, true);
	
	//TODO 2017-8-1-zhoujie 初始化打开页面数据
	init(); 
}


// $("#next").click(function(event) {
// 	dispGrantFlags = [];
// 	grantFlags = [];
// 	var limit = $("#rolegridPageLimit").find("option:selected").text();
// 	var page = $("#rolegridPageIndex").find("option:selected").text();
// 	//console.log(page);
// 	row = page * limit;
// 	//console.log(row);
// 	setTimeout(function(){
// 		for(var i=0;i<grantFlags.length;i++){
// 			 var rowindex = grantFlags[i];
// 			 if(!($('#rolegridcheckcheckbox'+rowindex).is(':checked'))){
// 				 $('#rolegridcheckcheckbox'+rowindex+'-div').trigger('click');
// 			 }
// 		}  
// 		 for(var i=0;i<dispGrantFlags.length;i++){
// 			 var rowindex = dispGrantFlags[i];
// 			 if(!($('#rolegriddispatchauthcheckbox'+rowindex).is(':checked'))){
// 				 $('#rolegriddispatchauthcheckbox'+rowindex+'-div').trigger('click');
// 			 }
// 		}
// 	},200)
// });

// $("#prev").click(function(event) {
// 	dispGrantFlags = [];
// 	grantFlags = [];
// 	var limit = $("#rolegridPageLimit").find("option:selected").text();
// 	var page = $("#rolegridPageIndex").find("option:selected").text();
// 	row = (page - 2) * limit;
// 	//console.log(row);
// 	setTimeout(function(){
// 		for(var i=0;i<grantFlags.length;i++){
// 			 var rowindex = grantFlags[i];
// 			 if(!($('#rolegridcheckcheckbox'+rowindex).is(':checked'))){
// 				 $('#rolegridcheckcheckbox'+rowindex+'-div').trigger('click');
// 			 }
// 		}  
// 		 for(var i=0;i<dispGrantFlags.length;i++){
// 			 var rowindex = dispGrantFlags[i];
// 			 if(!($('#rolegriddispatchauthcheckbox'+rowindex).is(':checked'))){
// 				 $('#rolegriddispatchauthcheckbox'+rowindex+'-div').trigger('click');
// 			 }
// 		}
// 	},200)
// });

// $("#first").click(function(event) {
// 	dispGrantFlags = [];
// 	grantFlags = [];
// 	row = 0;
// 	//console.log(row);
// 	setTimeout(function(){
// 		for(var i=0;i<grantFlags.length;i++){
// 			 var rowindex = grantFlags[i];
// 			 if(!($('#rolegridcheckcheckbox'+rowindex).is(':checked'))){
// 				 $('#rolegridcheckcheckbox'+rowindex+'-div').trigger('click');
// 			 }
// 		}  
// 		 for(var i=0;i<dispGrantFlags.length;i++){
// 			 var rowindex = dispGrantFlags[i];
// 			 if(!($('#rolegriddispatchauthcheckbox'+rowindex).is(':checked'))){
// 				 $('#rolegriddispatchauthcheckbox'+rowindex+'-div').trigger('click');
// 			 }
// 		}
// 	},200)
// });

// $("#last").click(function(event) {
// 	dispGrantFlags = [];
// 	grantFlags = [];
// 	var limit = $("#rolegridPageLimit").find("option:selected").text();
// 	var page = $("#rolegridPageIndex").find("option:last").text();
// 	console.log(page);
// 	row = (page - 1) * limit;
// 	//console.log(row);
// 	setTimeout(function(){
// 		for(var i=0;i<grantFlags.length;i++){
// 			 var rowindex = grantFlags[i];
// 			 if(!($('#rolegridcheckcheckbox'+rowindex).is(':checked'))){
// 				 $('#rolegridcheckcheckbox'+rowindex+'-div').trigger('click');
// 			 }
// 		}  
// 		 for(var i=0;i<dispGrantFlags.length;i++){
// 			 var rowindex = dispGrantFlags[i];
// 			 if(!($('#rolegriddispatchauthcheckbox'+rowindex).is(':checked'))){
// 				 $('#rolegriddispatchauthcheckbox'+rowindex+'-div').trigger('click');
// 			 }
// 		}
// 	},200)
// });

// $("#rolegridPageIndex").change(function(event) {
// 	dispGrantFlags = [];
// 	grantFlags = [];
// 	var limit = $("#rolegridPageLimit").find("option:selected").text();
// 	var page = $("#rolegridPageIndex").find("option:selected").text();
// 	row = (page - 1) * limit;
// 	setTimeout(function(){
// 		for(var i=0;i<grantFlags.length;i++){
// 			 var rowindex = grantFlags[i];
// 			$('#rolegridcheckcheckbox'+rowindex+'-div').trigger('click');
// 		}  
// 		 for(var i=0;i<dispGrantFlags.length;i++){
// 			 var rowindex = dispGrantFlags[i];
// 			$('#rolegriddispatchauthcheckbox'+rowindex+'-div').trigger('click');
// 		}
// 	},200)
// });

function saveGrant() {
	var params = {};
	params.objectid = document.doGrant.objectid.value;
	params.objecttype = document.doGrant.objecttype.value;
	params.grant = document.doGrant.grant.value;
    //params.rolegrid = h3c.getGridJsonData('rolegrid');
	
    //TODO 2017-8-1-zhoujie 将json对象转换为字符串 
	params.rolegrid = JSON.stringify(h3c.checkData);
	h3c.doEvent("grantController.do?grantObject", params, function(rs) {
		h3c.alert("更新成功!", function() {	
			h3c.closeWindow("doGrant");
		});
	});
}
/* BEGIN: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */ 
//设置延迟加载，当页面加载完成后再在UI上勾选
// setTimeout(function(){

	
// 	for(var i=0;i<grantFlags.length;i++){
// 		 var rowindex = grantFlags[i];
// 		$('#rolegridcheckcheckbox'+rowindex+'-div').trigger('click');
// 	}  
// 	 for(var i=0;i<dispGrantFlags.length;i++){
// 		 var rowindex = dispGrantFlags[i];
// 		$('#rolegriddispatchauthcheckbox'+rowindex+'-div').trigger('click');
// 	}


// },300)	
//时间可调节
/* END: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
//状态转化函数
function showState(value){
	if(value.status == undefined){
		var state = value.useful;
	}else{
		var state = value.status;
	}
	/* BEGIN: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */ 
	//在每一行中都调用check方法
	//TODO 2017-8-1-zhoujie 取消检查 
	//check(value);
	/* END: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
	if(state == "1"){
		return "<img src='"
		+ contextPath
		+ "/images/role/right.gif'>";
	}else{
		return "<img src='"
		+ contextPath
		+ "/images/role/wrong.png'>";
	}
}
/* BEGIN: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */ 
// function check(value){
// 	row++;
// 	var roleid = value.roleid;
// 	//取得后台返回的当前用户的授权和分授权信息，是个字符串信息
//  	var rolesGranted = "${rolesGranted }";
// 	if(rolesGranted!=null && rolesGranted!=""){
// 		//解析字符串
// 		var rolesG = rolesGranted.substring(0, rolesGranted.length-1);
// 		var arr = new Array();
// 		arr = rolesG.split(/[,;]/);
// 		for(var i=0;i<arr.length;i++){
// 			if(arr[i]==roleid){
// 				//将已有授权的角色row（行）信息放在数组中
// 				grantFlags.push(row);
// 				if(arr[i+1]=="1"){
// 					//若已有角色有分授权，将row信息放在数组中
// 					dispGrantFlags.push(row);
// 				}
// 				return ;
// 			}
// 			i++;
// 		}
// 	} 
// }

/* END: Added by xuechao 12972, 2017-5-10   Desc：授权和解除授权功能合并 */
 
 
 
/*********************************************2017-8-1-zhoujie 点击记录实现 **************************************************************/

// 打开每一页，然后增加数据到checkDate中去，并且去重(未用)
function addCheckData(portalGridId){
	if(h3c.checkData.length == 0){
		h3c.checkData = h3c.getGridJsonData(portalGridId,true);
	}else{
		var cur = h3c.getGridJsonData(portalGridId,true);
		for(var i=0;i<cur.length;i++){
			h3c.checkData.push(cur[i]);
		}
	}
	return removeDuplicatedItem(h3c.checkData);
}

// 数组去重(未用)
function removeDuplicatedItem(arr) {
     var ret = [];
     for (var i = 0;i<arr.length; i++) {
    	 var flag = true;
		 if(ret.length == 0){
			ret[0] = arr[i];
		 }else{
	    	 for(var j=0;j<ret.length;j++){
	        	if(ret[j].roleid == arr[i].roleid){
	        		flag = false;
					break;
	        	}
	         }
	    	 if(flag){
	       		ret.push(arr[i]);
	    	 }
		 }
    }
    return ret;
 }


// 初始化首页
function init(){
	setGrantData();
	openNewPage();
}


//初始化用户对应的全部角色和可分授权限,将数据保存在h3c.checkData中
function setGrantData(){
	var granted = rolesGranted.split(";");
	for(var i=0;i<roles.length;i++){
		var isNotRole=true;
		for(var j=0;j<granted.length;j++){
			if(granted[j].split(",")[0] == roles[i].roleid){
				roles[i].check=true;
				isNotRole=false;
				if(granted[j].split(",")[1] == "1"){
					roles[i].dispatchauth = true;
				}else{
					roles[i].dispatchauth = false;
				}
				break;
			}
		}
		if(isNotRole){
			roles[i].check=false;
			roles[i].dispatchauth = false;
		}
	}
	h3c.checkData = roles;
}


//打开页面延迟加载
function openNewPage(){
	var timer = setInterval(function(){
		if(h3c.isOver){
			initChecked();
			clearInterval(timer);
			h3c.isOver = false;
		}
	},50)
}

//将刚打开页面时用户已经有的角色和分授权选中
function initChecked(){
	checkAllCheck();
	var roleNames = $("#rolegridDivGrid tbody tr");
	for(var i=0;i<roleNames.length;i++){
		var roleEle = roleNames.eq(i).children().eq(4);
		for(var j=0;j<h3c.checkData.length;j++){
			if(roleEle.text().trim() === h3c.checkData[j].rolename.trim()){
				var hasRole = roleEle.prev().prev().prev().find("div");
				var dispatcher = roleEle.prev().find("div");
				if(h3c.checkData[j].check == true && hasRole.attr("class") !== "h3c-grid-check-col-on"){
					hasRole.trigger("click");
				}
				if(h3c.checkData[j].dispatchauth == true && dispatcher.attr("class") !== "h3c-grid-check-col-on"){
					dispatcher.trigger("click");
				}
			}
		}
	}
}

//打开页面时查看是否全选，没有全选的话，就将全选的勾选取消掉
function checkAllCheck(){
	var checkboxs = document.getElementsByName("rolegridcheckcheckbox");
	var chooseNum = 0;
	var notChooseNum = 0;
	for (var i = 0; i < checkboxs.length; i++) {
		var e = checkboxs[i];
		if (e.checked == true) {
			chooseNum++;
		}
	}
	var c = document.getElementById("rolegridcheckChooseAll");
	if (c) {
		if (chooseNum !== checkboxs.length) {
			document.getElementById("rolegridcheckChooseAll").className = 'h3c-grid-check-col';
			c.checked = false;
		} 
	}
}

$("#first,#prev,#next,#last").on("click.myClick",function(event) {
	openNewPage();
});

$("#rolegridPageIndex").on("change.myChange",function(){
	openNewPage();
})
</script>