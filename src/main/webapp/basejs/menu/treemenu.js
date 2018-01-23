/*递归读出所有的节点，并拼接成"ul li"的嵌套形式。menus为树形菜单list集合*/
var treeMenu = {
		buildTree : function(menus,html,width,height,fontsize,property,contextPath,handler) {

			html.push("<ul id=\"h3c-accordionmenu-root\" class=\"filetree\">");
            for (var i = 0;i<menus.length;i++) { 
//            	alert(menus.length);
            	var id = menus[i].functionid;  
                if (menus[i].parent == null||menus[i].parent == ""||menus[i].parent == "root") {
                	if(!childrenExists(menus[i])){
                		html.push("\r\n<li class='closed' id='" + id + "f'><span class='folder'><input id='"+id+"'  onclick='menuCheckbox("+JSON.stringify(menus)+","+JSON.stringify(menus)+","+JSON.stringify(menus[i])+")'  type='checkbox'/><a onclick=\""+handler+"('"+id+"','"+contextPath+"/"+menus[i].location+"')\">" +menus[i].title+ "</a></span></li>");
                	}else{
                		var firstOpen = "closed";
                		if(menus[i].firstopen == '0'||menus[i].firstopen == '2'){
                			firstOpen = "open";
                		}
                		html.push("\r\n<li class='"+firstOpen+"' id='" + id + "f'><span class='folder'><input id='"+id+"'  onclick='menuCheckbox("+JSON.stringify(menus)+","+JSON.stringify(menus)+","+JSON.stringify(menus[i])+")'  type='checkbox'/><a  onclick=\""+handler+"('"+id+"','"+contextPath+"/"+menus[i].location+"')\">" +menus[i].title+ "</a></span>");  
                        build(menus[i],html); 
                	}
                    html.push("\r</ul>"); 
                }
                return html; 
            }
			function build(node,html){ 
		    	var children = getChildren(node);
    			if(children.length!=0) {  
	                html.push("\r<ul>");  
	                for(var i = 0;i<children.length;i++) {  
	                	var id = children[i].functionid;
	                    if(childrenExists(children[i])){
	                    	var firstOpen = "closed";
	                		if(children[i].firstopen == '0'||children[i].firstopen == '2'){
	                			firstOpen = "open";
	                		}
	                    	html.push("\r<li class='"+firstOpen+"'><span class='folder'><input id='"+id+"'  onclick='menuCheckbox("+JSON.stringify(menus)+","+JSON.stringify(children)+","+JSON.stringify(children[i])+")'  type='checkbox'/><a onclick=\""+handler+"('"+id+"','"+contextPath+"/"+children[i].location+"')\">" + children[i].title+ "</a></span>");
	                    }
	                    else{
	                    	html.push("\r<li class='closed'><span class='file'><input id='"+id+"'  onclick='menuCheckbox("+JSON.stringify(menus)+","+JSON.stringify(children)+","+JSON.stringify(children[i])+")'  type='checkbox'/><a  onclick=\""+handler+"('"+id+"','"+contextPath+"/"+children[i].location+"')\">" + children[i].title+ "</a></span></li>");
	                    }
	                    build(children[i],html);  
	                }  
	                html.push("\r</ul>\r</li>");  
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
		}
}

      
	