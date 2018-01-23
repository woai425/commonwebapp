/**
 * *********************************************************************
 * 
 * SysLog.js
 * 系统日志查询
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     z10926<br/>
 * @create-time 2016年1月23日 下午4:22:03
 * @revision    $Id:  *
 **********************************************************************
 */

function doLoadGrid() {
	var params = {};
	params.desc = document.getElementById("desc").value;
	h3c.doLoadGrid('cronJob', params, true);
	checkNone();
}

$(function(){
	initHeight();
	doLoadGrid();
})

//判断是否有数据，如果没有数据，那么修改分页中的数字
function checkNone(){
	var jsonData=h3c.getGridJsonData("cronJob",true);//这是json数组
	if(jsonData.length==0){
		$("#cronJobBegRecord").html(0);
		$("#cronJobPage").html(0);
	};
}
function searchCronJob() {
	var params = {};
	params.code = $('#queryCronJob').val();
	h3c.doLoadGrid('cronJob', params, true);
}
function getChecked(gridId){
	    var nums = 0;
	    var checkboxs = document.getElementsByName(gridId+"checkcheckbox");
	    for(var i = 0;i< checkboxs.length;i++){
	       var e = checkboxs[i];
	       if(e.checked == true){
	           nums ++;
	       }
	    }
	    return nums;
	}
//GRID高度自适应
function initHeight() {
	var height = document.documentElement.clientHeight;
	var toolBarHeight = $("#cronJobToolBar").height();
	var titleHeight = $("h4").height();
	var hrHeight = $("hr").height();
	var codePageBarHeight = $("#cronJobPageBar").height();
	//alert(height-toolBarHeight-titleHeight-hrHeight-codePageBarHeight);
	$("#cronJobDiv").height(height-toolBarHeight-titleHeight-hrHeight-codePageBarHeight-106);
}
$(window).resize(function() {
	initHeight();
});


function addCronJob(){
	h3c.showWindowWithSrc("addCronJob", "新建定时器任务", 590, 255,"cronJobController.do?showAddCronJob",null,null,"icon-time");
}
function updateCronJob(obj){
	//obj是json对象
	var codeData=JSON.stringify(obj);
	var url='cronJobController.do?showUpdateCronJob&codeData=';
	//使用encodeURI()的目的是因为codeData是json格式的字符串
	h3c.showWindowWithSrc("updateCronJob", "编辑定时器任务", 590, 325,url+encodeURIComponent(codeData),null,null,"icon-time");	
}
function doActiveCronJob(obj){
	var taskid=obj.taskid;
	var url='cronJobController.do?doActiveCronJob&taskid='+taskid;
	h3c.doEvent(url, null, function(rs) {
		if(rs.success){
			h3c.reset();
		}
	},function(rs){
		if(!rs.success){
		       h3c.warning(rs.msg);					   		
	         }
	});
			
}
function startCronJob(obj){
	var taskid=obj.taskid;
	var url='cronJobController.do?startCronJob&taskid='+taskid;
	h3c.doEvent(url, null, function(rs) {
		if(rs.success){
			h3c.showOK("定时任务已开启！",function() {
 				h3c.reset();
 		   });
		}
	},function(rs){
		if(!rs.success){
		       h3c.warning(rs.msg);					   		
	         }
	});	
}
function stopCronJob(obj){
	var taskid=obj.taskid;
	var url='cronJobController.do?stopCronJob&taskid='+taskid;
	h3c.doEvent(url, null, function(rs) {
		h3c.showOK("定时任务已关闭！",function() {
			h3c.reset();
		});
	});
	
}
function editAlter(obj) {
	var str = JSON.stringify(obj);  
	if(obj.active=="0"){//只要是无效，运行状态一定是0，停止
		return "<span style='color:#AEAEAE'>[立即生效]&nbsp;</span>"+
					"<span style='color:#AEAEAE'>[开始]&nbsp;</span>"+
					 "<a href=\"javascript:void(0);\" onclick='updateCronJob("+ str+ ")'>[编辑]</a>";
	}else{//只要有效，那么立即生效一直可以点击
		if(obj.isstart=="0"){
			 return "<a href=\"javascript:void(0);\" onclick='doActiveCronJob("+ str+ ")'>[立即生效]</a>&nbsp;"+
		        "<a href=\"javascript:void(0);\" onclick='startCronJob("+ str+ ")'>[开始]</a>&nbsp;"+
                "<a href=\"javascript:void(0);\" onclick='updateCronJob("+ str+ ")'>[编辑]</a>";
		}else{
			return "<a href=\"javascript:void(0);\" onclick='doActiveCronJob("+ str+ ")'>[立即生效]</a>&nbsp;"+
					   "<a href=\"javascript:void(0);\" onclick='stopCronJob("+ str+ ")'>[停止]</a>&nbsp;"+
					 "<a href=\"javascript:void(0);\" onclick='updateCronJob("+ str+ ")'>[编辑]</a>";
		}
	}
}
function deleteCronJob(){
	if(getChecked("cronJob") > 0){
		h3c.confirm("是否确认删除？", function() {
			var params = {};
			params.codeData = h3c.getGridJsonData('cronJob');
			h3c.doEvent("cronJobController.do?deleteCronJob", params, function(rs) {
				h3c.alert("删除成功!", function() {
					h3c.reset();
				});
			});
		});
	}else{
		h3c.alert("请选中要删除的信息");
	}	
}