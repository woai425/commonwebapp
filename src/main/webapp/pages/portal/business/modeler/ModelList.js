function doEdit(obj) {
	return "<a href=\"javascript:void(0);\" onclick=\"operate('" + obj.id_+ "')\">编辑</a> <a href=\"javascript:void(0);\" onclick=\"deploy('" + obj.id_+ "')\">部署</a> <a href=\"javascript:void(0);\" onclick='del(" + obj.id_ + ")'>删除</a> ";
	
	}
/**
 * 分页
 */
$(function(){
	query();
})
function query(){
	var params = {};
	h3c.doLoadGrid('model', params, true);
}
//转换时间
function formatTime1(obj){
	return h3c.formatTimeStamp(obj.create_time_);
}

function formatTime2(obj){
	return h3c.formatTimeStamp(obj.last_update_time_);
}

/**
 * *打开模型新增页面(方法一窗口显示)
 */
/*function showAddModel() {
	h3c.showWindowWithSrc("createModel", "创建模型", 500, 250,
			"ModelController.do?showCreateModel",null,null,"icon-book");
	
 
}*/
/**
 * *打开模型新增页面(方法二ifream到原有页面中有问题)
 */
function showAddModel() {
	h3c.showWindowWithSrc("createModel", "创建模型", 500, 250,
			"ModelController.do?showCreateModel",function(rs){
		h3c.lastExecMethod(function(){
			var path = JSON.parse(localStorage.getItem("rs"));
			parent.$("#adminWeblcome").attr("src",path);
			localStorage.clear();
		})
		
	},null,"icon-book");
	

}
//删除
function del(id){
	var params = {};
	params.id = id;
	h3c.doEvent("ModelController.do?getModelName", params, function(rs) {
		var modelName=rs.data;
		if(modelName==deploymentName){
			h3c.alert("不能删除正在部署的模型");
			return;
		}
		if(modelName!=deploymentName){	
			h3c.confirm("是否确定删除模型",function(){
				h3c.doEvent("ModelController.do?delete", params, function(rs) {
					query();
				});
			})
		}
	});
}

//编辑
function operate(id){
	var url="./modeler.html?modelId="+id;
    location.href=url;

}
//部署
function deploy(id){
	var params = {};
	params.id = id;
	h3c.doEvent("ModelController.do?deploy", params, function(rs) {
		h3c.alert(rs.msg,function(){
			h3c.reset();
			
		});
		var deploymentId=rs.data;
		params.deploymentId = deploymentId;
		h3c.doEvent("ModelController.do?show", params, function(rs) {
			var name=rs.msg;				
			$('table').find('h3').html("部署名称: "+name+" , 部署Id: "+deploymentId);
		});
	});
}



