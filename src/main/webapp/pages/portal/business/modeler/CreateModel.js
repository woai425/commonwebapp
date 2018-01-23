
$("#sub").click(function(){
	var name=$.trim($("#name").val());
	var key=$.trim($("#key").val());
	if(name==""||key==""){
		h3c.alert("必填字段不可为空");
		return;
	}
	var reg=/^[a-zA-Z]+$/;
	if(!reg.test(name)){
		h3c.alert("名称只能输入英文");
		return;
	}
	if(!reg.test(key)){
		h3c.alert("KEY只能输入英文");
		return;
	}
	var params = {};
	params.name = name;
	h3c.doEvent("ModelController.do?checkName", params, function(rs) {
		if(rs.data==2){
			h3c.alert("模型名称重复，请重新命名");
			return;
		}
		if(rs.data==1){
			$.ajax({
				url:'ModelController.do?create',
				data:{
					name:name,
					key:key,
					description:$("#description").val()
				},
				dataType:'text',
				type:'post',
				async:true,
				success:function(rs){
					h3c.closeWindow("createModel","ok");
					localStorage.setItem("rs",JSON.stringify(rs));
				},
				error:function(res){
					console.log("error");
				}
			})
		}
		
		
		
	});
	
	//方法二(嵌入到ifeam里)
	
})
//重置
$("#back").click(function(){
	
	$("#name").val("");
	$("#key").val("");
	$("#description").val("");
})
