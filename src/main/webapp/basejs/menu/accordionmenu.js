/*递归读出所有的节点，并拼接成"ul li"的嵌套形式。menus为树形菜单list集合*/
var accordionMenu = {
		buildTree : function(menus,html,usage,width,height,fontsize,property,contentIframeId,style,contextPath,handler) {
			if("portal"==usage){
	    		html.push("<div class=\"page-container\" style=\"text-align:left;height:"+(height==null||height==''?"0px":height)+";width:"+(width==null||width==''?"300px":width)+";font-family:Microsoft YaHei;\">"+			
	    				"<div class=\"sidebar-menu toggle-others fixed\"  style=\"width:220px\">"+		
						"<div class=\"sidebar-menu-inner\"  style=\"\">"+
			    		"<ul  id=\"main-menu\" class=\"main-menu\"  style=\"\">");  
	            for (var i = 0;i<menus.length;i++) {
	            	var icon = '';
	            	if(menus[i].iconcls!=null&&menus[i].iconcls.length !=0){
	            		icon = "<i style=\"font-size:13px;width:13px;\" class=\""+menus[i].iconcls+"\"></i>&nbsp;&nbsp;";
	            	}else if(menus[i].iconurl!=null&&menus[i].iconurl.length !=0&&menus[i].iconurl!="none"){
	            		icon = "<img style='width:16px;height:16px;' src=\""+contextPath+"/"+menus[i].iconurl+"\">&nbsp;&nbsp;";
	            	}
	                var id = menus[i].functionid;  
	                if (menus[i].parent == null||menus[i].parent == ""||menus[i].parent == "S000000") {
	                	if(!childrenExists(menus[i])){
	                		html.push("\r\n<li id='" + id + "'><a id=\"c1\"  href=\"#\" onclick=\"goIframe('"+contentIframeId+"','"+contextPath+"/"+menus[i].location+"')\">"+icon+"<span  class=\"title\" >"+menus[i].title+"</span></a></li>");
	                	}else{
	                		var firstOpen = "";
	                		if(menus[i].firstopen == '1'||menus[i].firstopen == '2'){
	                			firstOpen = " class='opened' ";
	                		}
	                		html.push("\r\n<li "+firstOpen+" id='" + id + "' ><a id=\"c1\" >"+icon+"<span class=\"title\">"+menus[i].title+"</span></a>");  
	                        build(menus[i],html,usage); 
	                	}
	                }  
	            }  
	            html.push("\r</ul></div></div></div>"); 
	            return html; 
	    	}else{
	    		if(style=='sign'){
	    			html.push("<ul id=\"h3c-accordionmenu-root\">");  
		            for (var i = 0;i<menus.length;i++) {  
		            	var id = menus[i].functionid;  
		                if (menus[i].parent == null||menus[i].parent == ""||menus[i].parent == "root") {
		                	if(!childrenExists(menus[i])){
		                		html.push("\r\n<li id='" + id + "'><a  target=\""+contentIframeId+"\" href=\""+contextPath+"/"+menus[i].location+"\">" +menus[i].title+ "</a></li>");
		                	}else{
		                		html.push("\r\n<li id='" + id + "'><a  target=\""+contentIframeId+"\" href=\""+contextPath+"/"+menus[i].location+"\">" +menus[i].title+ "</a>");  
		                        build(menus[i],html,usage); 
		                	}
		                }  
		            }  
		            html.push("\r</ul>");
	    		}else{
	    			html.push("<ul id=\"h3c-accordionmenu-root\" class=\"filetree\">");
		            for (var i = 0;i<menus.length;i++) {  
		            	var id = menus[i].functionid;  
		                if (menus[i].parent == null||menus[i].parent == ""||menus[i].parent == "root") {
		                	if(!childrenExists(menus[i])){
		                		html.push("\r\n<li class='closed' id='" + id + "f'><span class='folder'><a onclick=\""+handler+"('"+id+"','"+contextPath+"/"+menus[i].location+"')\">" +menus[i].title+ "</a></span></li>");
		                	}else{
		                		var firstOpen = "closed";
		                		if(menus[i].firstopen == '0'||menus[i].firstopen == '2'){
		                			firstOpen = "open";
		                		}
		                		html.push("\r\n<li class='"+firstOpen+"' id='" + id + "f'><span class='folder'><a  onclick=\""+handler+"('"+id+"','"+contextPath+"/"+menus[i].location+"')\">" +menus[i].title+ "</a></span>");  
		                        build(menus[i],html,usage); 
		                	}
		                }  
		            }  
		            html.push("\r</ul>"); 
	    		}
	            return html; 
	    	}
			function build(node,html,usage){ 
		    	if("portal"==usage){
		    		//var children = JSON.parse('[]'); 
		    		var children = getChildren(node);  
		            if (children.length!=0) {  
		                html.push("\r<ul>");  
		                for (var i = 0;i<children.length;i++) {
		                	var icon = '';
			            	if(children[i].iconcls!=null&&children[i].iconcls.length !=0){
			            		icon = "<i style=\"font-size:13px;width:13px;\" class=\""+children[i].iconcls+"\"></i>&nbsp;&nbsp;";
			            	}else if(children[i].iconurl!=null&&children[i].iconurl.length !=0&&children[i].iconurl !="none"){
			            		icon = "<img style='width:16px;height:16px;' src=\""+contextPath+"/"+children[i].iconurl+"\">&nbsp;&nbsp;";
			            	}
		                    var id = children[i].functionid;
		                    if(childrenExists(children[i])){
		                    	var firstOpen = "";
		                		if(children[i].firstopen == '1'||children[i].firstopen == '2'){
		                			firstOpen = " class='opened active' ";
		                		}
		                    	html.push("\r<li "+firstOpen+"><a href='#'>"+icon+"<span class=\"title\">"+children[i].title+"</span></a>");
		                    }
		                    else{
		                    	html.push("\r<li><a  href=\"#\" onclick=\"goIframe('"+contentIframeId+"','"+contextPath+"/"+children[i].location+"')\">"+icon+"<span class=\"title\">"+children[i].title+"</span></a></li>");
		                    }
		                    build(children[i],html,usage);  
		                }  
		                html.push("\r</ul>\r</li>"); 
		            }
		            
		    	}else{
		    		var children = getChildren(node);
		    		if(style=='sign'){
		    			 if (children.length!=0) {  
				                html.push("\r<ul>");  
				                for (var i = 0;i<children.length;i++) {  
				                	var id = children[i].functionid;
				                    if(childrenExists(children[i])){
				                    	html.push("\r<li><a  target=\""+contentIframeId+"\" href=\""+contextPath+"/"+children[i].location+"\">" + children[i].title+ "</a>");
				                    }
				                    else{
				                    	html.push("\r<li><a  target=\""+contentIframeId+"\" href=\""+contextPath+"/"+children[i].location+"\">" + children[i].title+ "</a></li>");
				                    }
				                    build(children[i],html);  
				                }  
				                html.push("\r</ul>\r</li>");  
				          }  
		    		}else{
		    			if(children.length!=0) {  
			                html.push("\r<ul>");  
			                for(var i = 0;i<children.length;i++) {  
			                	var id = children[i].functionid;
			                    if(childrenExists(children[i])){
			                    	var firstOpen = "closed";
			                		if(children[i].firstopen == '0'||children[i].firstopen == '2'){
			                			firstOpen = "open";
			                		}
			                    	html.push("\r<li class='"+firstOpen+"'><span class='folder'><a onclick=\""+handler+"('"+id+"','"+contextPath+"/"+children[i].location+"')\">" + children[i].title+ "</a></span>");
			                    }
			                    else{
			                    	html.push("\r<li class='closed'><span class='file'><a  onclick=\""+handler+"('"+id+"','"+contextPath+"/"+children[i].location+"')\">" + children[i].title+ "</a></span></li>");
			                    }
			                    build(children[i],html);  
			                }  
			                html.push("\r</ul>\r</li>");  
			          }  
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
		}
}

      
	