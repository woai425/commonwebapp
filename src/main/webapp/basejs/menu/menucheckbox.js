function menuCheckbox(menusAll,menus,node){
	if($("#"+node.functionid+"").attr("checked")=='checked'){
		if(childrenExists(node)){
            build(node);
    	}
		findParentChecked(menusAll,node);
	}
	else{
		if(childrenExists(node)){
            build(node);
    	}
		
//TODO	2017-7-28-zhoujie  判断如果是按钮（2），当按钮没有选择时，不需要对父节点递归取消选择。
		if(node.type != 2){
			findParentUnchecked(menusAll,node)
		}
	}    
}
function build(node){ 
	var children = getChildren(node);
	if(children.length!=0) {  
        for(var i = 0;i<children.length;i++) {  
        	var id = children[i].functionid;
        	if($("#"+node.functionid+"").attr("checked")=='checked'){
        		if(childrenExists(children[i])){
                	$("#"+children[i].functionid+"").attr("checked",true);
                }
                else{
                	$("#"+children[i].functionid+"").attr("checked",true);
                }
        		
        	}else{
        		if(childrenExists(children[i])){
                	$("#"+children[i].functionid+"").attr("checked",false);
                }
                else{
                	$("#"+children[i].functionid+"").attr("checked",false);
                }
        		
        	}
            build(children[i]);  
        }  
   }  
}  
function childrenExists(node){
	var id = node.functionid;  
    for (var i=0;i<menus.length;i++) {  
        if (id == menus[i].parent) {  
            return true;
        }  
    }
    return false;
}
function getChildren(node){  
    var children = JSON.parse('[]');  
    var id = node.functionid;  
    for (var i=0;i<menus.length;i++) {  
        if (id == menus[i].parent) {  
            children.push(menus[i]);  
        }  
    }  
    return children;  
} 
function findParentChecked(menusAll,node){
	$(":checkbox").each(function(i){
		if(node.parent == $(this).attr("id")){
			$(this).attr("checked",true);
		}
   }); 
	for(var i = 0;i<menusAll.length;i++){
		if(node.parent == menusAll[i].functionid){
			findParentChecked(menusAll,menus[i]);
		}
	}
}
function findParentUnchecked(menusAll,node){
	var children = JSON.parse('[]');
	var uncheckedFlag = true;
	for(var i = 0;i<menusAll.length;i++){
		if(node.parent == menusAll[i].functionid){
			children = getChildren(menusAll[i]);
		}
	}
	for(var i = 0;i<children.length;i++){
		if($("#"+children[i].functionid+"").attr("checked")=='checked'){
			uncheckedFlag = false;
		}
	}
	if(uncheckedFlag){
		$(":checkbox").each(function(i){
			if(node.parent == $(this).attr("id")){
				$(this).attr("checked",false);
			}
	   }); 
	}
	
	for(var i = 0;i<menus.length;i++){
		if(node.parent == menusAll[i].functionid){
			findParentUnchecked(menusAll,menusAll[i]);
		}
	}
}