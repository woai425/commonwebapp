/**
 * 框架核心js
 */
var h3c = {
	version            : '1.0.0.1',
	msg                : '正在处理中...',
	defaultTitle       : '系统提示',
	defaultError       : '错误',
	defaultWarn        : '警告',
	ajaxaSynchronous   : false,
	//TODO	2017-8-1-zhoujie 存放点击checkbox按钮时的数据
	checkData          :[],
	isOver             : false,
	
	/** ***ajax默认的同步异步标志，false为同步，true为异步****** */
	getTopWindow : function() {
		var obj = window.self;
		while (true) {
			if (obj.document.getElementById("basicWindow")) {
				return obj;
			}
			obj = obj.window.parent;
		}
		return obj;
	},
	mask : function() {
		var obj = h3c.getTopWindow();
		$("#mask", obj.document).css("height",
				obj.document.documentElement.clientHeight);
		$("#mask", obj.document).css("width",
				obj.document.documentElement.clientWidth);
		$("#mask", obj.document).show();
		if(navigator.userAgent.indexOf('MSIE 8') < 0){
			h3c.loading();
		}
	},
	unmask : function(currentPage) {
		var obj = h3c.getTopWindow();
		$("#mask", obj.document).hide();
		if(navigator.userAgent.indexOf('MSIE 8') < 0){
			h3c.unloading();
		}
		$("#saveSuccess", obj.document).hide();	
	},
	loading : function(){
		h3c.getTopWindow().canvasLoading();
	},
	unloading : function(){
		var obj = h3c.getTopWindow();
		var container = $("#portalWaitingContainer", obj.document);
		var loadingPanel = $("#portalWaitingContainer > .portalWaitingCanvas", obj.document);
		if (loadingPanel.length > 0) {
			container.empty();
		}
	},
	gridLoading : function(gridId) {
		window.setTimeout(function() {
			$("#" + gridId + "mask").css('left',
					$("#" + gridId + "Div").offset().left);
			$("#" + gridId + "mask").css('top',
					$("#" + gridId + "Div").offset().top);
			$("#" + gridId + "mask").css("height",
					$("#" + gridId + "Div").outerHeight());
			$("#" + gridId + "mask").css("width",
					$("#" + gridId + "Div").outerWidth());
			$("#" + gridId + "mask").show();
			if (navigator.userAgent.indexOf('MSIE 8') < 0) {
				var div, loading;
				var container = $("#" + gridId + "WaitingContainer");
				var loadingPanel = $("#" + gridId + "WaitingContainer > ."
						+ gridId + "WaitingCanvas");
				if (loadingPanel.length == 0) {
					div = document.createElement('div');
					div.className = gridId + 'WaitingCanvas';
					loading = new Sonic(loaders[1]);
					div.appendChild(loading.canvas);
					container[0].appendChild(div);
					loading.play();
				}
			}
		}, 100);
	},
	gridUnloading : function(gridId){
		if(navigator.userAgent.indexOf('MSIE 8') < 0){
			var container = $("#"+gridId+"WaitingContainer");
			var loadingPanel = $("#"+gridId+"WaitingContainer > ."+gridId+"WaitingCanvas");
			if (loadingPanel.length > 0) {
				container.empty();
			}
		}
		$("#"+gridId+"mask").hide();
	},
	showMsgWithMask : function(msg, currentPage) {
		if (currentPage) {
			$("#saveSuccess").show();
			$('#saveSuccess').html(
							"<font style=\"font-family: 'Microsoft YaHei' ! important;font-size: 14px;color: #FFFFFF;\">"
									+ msg + "</font>");
		} else {
			var obj = h3c.getTopWindow();
			$("#saveSuccess", obj.document).show();
			$('#saveSuccess', obj.document).html(
							"<font style=\"font-family: 'Microsoft YaHei' ! important;font-size: 14px;color: #FFFFFF;\">"
									+ msg + "</font>");
		}
	},
	lastExecMethod : function(func,param,winId){
		if(func){
			var obj = h3c.getTopWindow();
			var time = setInterval(function(){
				if(winId){
					if(!obj.document.getElementById('_DialogDiv_'+winId)){
						clearInterval(time);
						if(param){
						   func(param);	
						}else{
						   func();
						}
					}
				}else{
					if($('#_DialogBGDiv',obj.document).css('display')=='none'){
						clearInterval(time);
						if(param){
						   func(param);	
						}else{
						   func();
						}
					}
				}
			},150);
		}
	},
	doEvent : function(url, eParam, successFun, failFun, asynchronous,isMask) {
		if(isMask){
		   h3c.mask();
		}
		if (asynchronous == null || asynchronous == "") {
			if(isMask){
				asynchronous = true;//异步
			}else{
				asynchronous = false;//同步
			}
		}
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : url,
				data : eParam,
				dataType : "json",
				success : doSuccess,
				error : doFailure,
				async : true,
				complete : function() {
					setTimeout(function(){
						if(isMask){
							h3c.unmask();
						}
					},250);	
				}
			});
		},50);

		function doSuccess(response) {
			if (response.success) {// 请求成功
				if (successFun) {// 有成功回调函数
					successFun(response);	
				}
			} else {// 请求失败
				if (failFun) {// 有失败回调函数
					failFun(response);
				} else {
					var errmsg = null;
					if (response.msg != "") {
						errmsg = "请求异常:" + response.msg;
					}
					h3c.error(errmsg);
				}
			}
		}
		function doFailure(response) {
			if (failFun) {// 有失败回调函数
				failFun(response);
			} else {
				var errmsg = null;
				if (response.msg != "") {
					errmsg = "请求异常:" + response.msg;
				}
				h3c.error(errmsg);
			}
		}
	},
	formClear : function(theForm) {
		if (theForm == null || theForm == "") {
			theForm = "commForm";
		}
		$("#" + theForm + "").find(":input").not(".gridSelectDD_").val("");
	},
	reset : function() {
		setTimeout(function() {
			window.history.go(0);
			window.location.reload();
		}, 300);
	},
	alert : function(info, fun, title, width, height) {
		Dialog.alert(info, fun, width, height, title);
	},
	autoCloseAlert : function(info, fun, title, width, height ,sec) {
		Dialog.autoCloseAlert(info, fun, width, height, title ,sec);
	},
	confirm : function(info, funOK, funcCal, title, width, height) {
		Dialog.confirm(info, funOK, funcCal, width, height, title);
	},
	error : function(info, fun, title, width, height) {
		Dialog.error(info, fun, width, height, title);
	},
	warning : function(info, fun, title, width, height) {
		Dialog.warning(info, fun, width, height, title);
	},
	showOK : function(info, fun, title, width, height) {
		Dialog.showOK(info, fun, width, height, title);
	},
	showWindowWithSrc : function(id, t, w, h, newSrc, okFunc, cancelFunc, iconCls, iconUrl) {
		var w = w || 400, h = h || 100;
		var diag = new Dialog({
			Width : w,
			Height : h,
		});
		diag.ID = id;
		diag.titleHeight = 40;
		diag.ShowButtonRow = false;
		diag.iconCls = iconCls;
		if(iconCls==null||iconCls==""){
			diag.iconUrl = iconUrl;
		}
		if (t != null && t != "") {
			diag.Title = t;
		} else {
			diag.Title = "错误";
		}
		diag.CancelEvent = function() {
			diag.close();
			if (cancelFunc){
				cancelFunc();
			}	
		};
		diag.OKEvent = function() {
			diag.close();
			if (okFunc){
				okFunc();
			}		
		}
		diag.InnerHtml = '<iframe id="'+id+'welcome" frameborder="no" border="0" style="padding-left:0px;" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" width="100%" height="100%" src="'
				+ newSrc + '"></iframe>';
		diag.show();
		return diag;
	},
	showWindowWithHtml : function(id, t, w, h, html, successFunc, failFunc, iconCls, iconUrl) {
		var w = w || 400, h = h || 100;
		var diag = new Dialog({
			Width : w,
			Height : h,
		});
		diag.ID = id;
		diag.titleHeight = 40;
		diag.ShowButtonRow = false;
		diag.iconCls = iconCls;
		if(iconCls==null||iconCls==""){
			diag.iconUrl = iconUrl;
		}
		if (t != null && t != "") {
			diag.Title = t;
		} else {
			diag.Title = "错误";
		}
		diag.CancelEvent = function() {
			diag.close();
			if (failFunc)
				failFunc();
		};
		diag.OKEvent = function() {
			diag.close();
			if (successFunc)
				successFunc();
		}
		diag.InnerHtml = html;
		diag.show();
		return diag;
	},
	closeWindow : function(id, btn) {
		var obj = h3c.getTopWindow();
		if (btn == 'ok') {
			obj.Dialog.getInstance(id).okButton.onclick.apply();
		} else {
			obj.Dialog.getInstance(id).cancelButton.onclick.apply();
		}
	},
	autoFillPage : function(obj, theForm) {
		if (theForm == null) {
			$("input").not(".gridSelectDD_").val("");//先清屏
			$("input").each(function(index, o) {
				var value = null;
				if (o.name != null && o.name != "") {
					try {
						value = eval("obj." + o.name);
					} catch (e) {

					}
					if (value != 'undefined' && value != undefined) {
						document.getElementById(o.name).value = value;
					}
				}
			});
			$("textarea").each(function(index, o) {
				var value = null;
				if (o.name != null && o.name != "") {
					try {
						value = eval("obj." + o.name);
					} catch (e) {

					}
					if (value != 'undefined' && value != undefined) {
						document.getElementById(o.name).value = value;
					}
				}

			});
			$("select").each(function(index, o) {
				var value = null;
				if (o.name != null && o.name != "") {
					try {
						value = eval("obj." + o.name);
					} catch (e) {

					}
					if (value != 'undefined' && value != undefined) {
						$("#" + o.name).val(value);
					}
				}
			});
		} else {
			h3c.formClear(theForm);//先清屏
			$("#" + theForm).find("input").each(function(index, o) {
				var value = null;
				if (o.name != null && o.name != "") {
					try {
						value = eval("obj." + o.name);
					} catch (e) {

					}
					if (value != 'undefined' && value != undefined) {
						document.getElementById(o.name).value = value;
					}
				}
			});

			$("#" + theForm).find("textarea").each(function(index, o) {
				var value = null;
				if (o.name != null && o.name != "") {
					try {
						value = eval("obj." + o.name);
					} catch (e) {

					}
					if (value != 'undefined' && value != undefined) {
						document.getElementById(o.name).value = value;
					}
				}

			});
			$("#" + theForm).find("select").each(function(index, o) {
				var value = null;
				if (o.name != null && o.name != "") {
					try {
						value = eval("obj." + o.name);
					} catch (e) {

					}
					if (value != 'undefined' && value != undefined) {
						$("#" + o.name).val(value);
					}
				}
			});
		}
	},
	getElements : function(form) {
		var params = {};
		if (form == null || form == "") {
			$(':input').each(
					function(index, obj) {
						if (obj.name != null && obj.name != ""
								&& obj.name.indexOf("-") == -1) {
							if (obj.name.indexOf("Data") != -1) {
								var name = obj.name.split('Data')[0];
								h3c.getGridJsonData(name);
							}
							var exp = "params." + obj.name + "=\""
									+ $(obj).val() + "\"";
							eval(exp);
						}

					});
		} else {
			$("#" + form + "").find(":input").each(
					function(index, obj) {
						if (obj.name != null && obj.name != ""
								&& obj.name.indexOf("-") == -1) {
							if (obj.name.indexOf("Data") != -1) {
								var name = obj.name.split('Data')[0];
								h3c.getGridJsonData(name);
							}
							var exp = "params." + obj.name + "=\""
									+ $(obj).val() + "\"";
							eval(exp);
						}

					});
		}
		return params;
	},
	getGridJsonData : function(gridId,isJsonObjOrNot) {
		var inputName = gridId + "Data";
		var colJson = {};
		var indexArray = new Array();
		var array = new Array();
		var i = 0;
		$('#' + gridId + 'DivGrid thead tr th').each(function(index, o) {
			array[i] = $('#' + o.id).attr('dataIndex');// 拿到字段名
			i = i + 1;
		});
		var j = 0;
		var json = "[";
		$('#' + gridId + 'DivGrid tbody tr td')
				.each(
						function(index, o) {
							if (j == 0) {
								json += "{";
							}
							for (var k = 0; k < array.length; k++) {
								if (j == k) {
									json += "\""; // 加上一个双引号
									json += array[k];
									json += "\"";
									json += ":";
									if ($(this).find('input[type=checkbox]').length > 0) {
										if ($(this)
												.find('input[type=checkbox]')
												.attr("checked") == "checked") {
											json += true;
										} else {
											json += false;
										}
									} else if ($(this).find('input[type=hidden][name=gridSelect]').length > 0) {
										json += "\"";
										json += $.trim($(this).find('input[type=hidden][name=gridSelect]').val());
										json += "\"";
									}else if ($(this).find('input[type=hidden][name=gridRadio]').length > 0) {
										json += "\"";
										json += $.trim($(this).find('input[type=hidden][name=gridRadio]').val());
										json += "\"";
									} else {
										json += "\"";
										json += $.trim($(this).text().replace(/[\r\n]/g,"\\r\\n"));
										json += "\"";
									}
									json += ",";
									break;
								}
							}
							if (j == array.length - 1) {
								json = json.substring(0, json.length - 1);
								json += "}";
								json += ",";
								j = 0;
							} else {
								j = j + 1;
							}
						});
		if (json.length > 1) {
			json = json.substring(0, json.length - 1);
		}
		json += "]";
		document.getElementById(inputName).value = json;
		if(isJsonObjOrNot){
			return eval('(' + json + ')');
		}
		return json;
	},
	/** *********统一的提交之前的全局校验函数，用来判断非空的是否有空的存在，不空的是否符合业务校验规则****************** */
	checkValue : function(form) {
		// var errtitle = "<b>请先修正以下问题后再进行本操作：</b><br>";
		var eles = eval('document.' + form).elements;
		for (var i = 0; i < eles.length; i++) {
			var obj = eles[i];
			if (obj.getAttribute("required") == 'true'
					|| (!obj.getAttribute("required") && obj.required)) {
				if (obj.value == "") { // 非空判断
					$('#' + obj.id).blur();
					// h3c.error(errtitle + obj.getAttribute("label") +
					// "不能为空！",h3c.doFocus);
					return false;
				}
			}
		}
		return true;
	},
	doOpLog : function(theForm) {
		// 操作日志处理
		var prsource;
		try {
			prsource = eval(MDParam.prsource);
		} catch (e) {
			return null;
		}
		if (prsource) {
			var len = prsource.length;
			var items = new Array(len);
			for (var i = 0; i < len; i++) {
				items[i] = "";
			}
			for (var i = 0; i < len; i++) {
				var prop = prsource[i].property;
				if (prop == "")
					continue;

				var type = prsource[i].type;
				if (type && type == "select") {
					prop = prop + "_combo";
				}
				var el = document.getElementById(prop);
				if (el) {
					if (type && type.toLowerCase() == "text") {
						items[i] = el.innerHTML;
					} else {
						items[i] = el.value;
					}
				}
			}
			var digest = "";
			for (var i = 1; i < len; i++) {
				if (items[i] == "")
					continue;

				digest = digest + "," + prsource[i].label + ":" + items[i];
			}
			if (digest != "") {
				digest = digest.substring(1);
			}
			// 获取原始界面信息
			// var
			// oriSource="<html>"+document.documentElement.innerHTML+"</html>";
			var oriSource = "";
			var chromeForTextArea = "";
			var isChrome = window.navigator.userAgent.indexOf('Chrome') !== -1;
			if(!$.browser.msie) {
				var inputs = document.getElementsByTagName("input");
				for (var i = 0; i < inputs.length; i++) {
					inputs[i].setAttribute("value", inputs[i].value);
				}
				if(isChrome){
					var textareas = document.getElementsByTagName("textarea");
					for (var i = 0; i < textareas.length; i++) {
						chromeForTextArea = chromeForTextArea + " document.getElementById('"+textareas[i].id+"').value='"+textareas[i].value.replace(/[\r\n]/g,"\\r\\n")+"';";
					}
				}
	
			}
			if (document.documentElement.outerHTML) {
				oriSource = document.documentElement.outerHTML;
			} else { // firefox 2011.9.13
				oriSource = document.documentElement.innerHTML
			}
			var oriSource = oriSource.replace(
					/<script([^>]*?>([^<]*(<[^\/]*)*))<\/script>/gi, "");
			if(isChrome){
				oriSource = oriSource +"<script> "+chromeForTextArea+" </script>";
			}
			/*
			 * var userlog={functionid:MDParam.functionid, objectid:digest[0],
			 * prcol1:digest[1], prcol2:digest[2], prcol3:digest[3],
			 * prcol4:digest[4], prcol5:digest[5], prcol6:digest[6],
			 * prcol7:digest[7], prcol8:digest[8], orisource:oriSource };
			 */
			var userlog = {
				functionid : MDParam.functionid,
				objectid : items[0],
				digest : digest,
				prcol1 : MDParam.title,
				prcol2 : "",
				prcol3 : "",
				prcol4 : "",
				prcol5 : "",
				prcol6 : "",
				prcol7 : "",
				prcol8 : "",
				orisource : oriSource
			};
			// var srtUserlog=JSON.stringify(userlog);
			var srtUserlog = JSON.stringify(userlog);
			return srtUserlog;
		} else {
			return null;
		}
	},
	submit : function(theForm, successFun, failFun, asynchronous ,isMsgInMask) {// Ajax方式提交保存
		if (!theForm) {
			theForm = "commForm";
		}
		h3c.mask();
		// 将表格的数据转换成json数据
		$(':input').each(
				function(index, obj) {
					if (obj.name != null && obj.name != ""
							&& obj.name.indexOf("-") == -1) {
						if (obj.name.indexOf("Data") != -1) {
							var name = obj.name.split('Data')[0];
							h3c.getGridJsonData(name);
						}
					}

				});
		if (asynchronous == null || asynchronous == "") {
			asynchronous = true;// 异步
		}
		if (isMsgInMask == null || isMsgInMask == "") {
			isMsgInMask = true;// 在遮罩上显示响应消息
		}
		
		var params = $('#' + theForm).serializeArray();		
		if (h3c.checkValue(theForm) == false) { // 做通一校验判断
			h3c.unmask();
			return false;
		}
		
		if (MDParam) {
			// 操作日志处理
			if (MDParam.log == "1") {
				if (MDParam.prsource == "" || MDParam.prsource == null) {
					h3c.error("日志摘要未配置，请确认！");
					return;
				}
				var srtUserlog = this.doOpLog();
				if (srtUserlog) {
					var obj = {};
					obj.name = "userlog";
					obj.value = srtUserlog;
					params.push(obj);
				}
			}
		}
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : eval('document.' + theForm).action,
				data : params,
				dataType : "json",
				success : doSuccess,
				error : doFailure,
				async : asynchronous,
				complete : function() {
					$('#saveSuccess').html(
							"<font style=\"font-family: 'Microsoft YaHei' ! important;font-size: 14px;color: #FFFFFF;\">请求成功！</font>");
				}
			});
		},50);
		var resetFlag = true;
		function doSuccess(response) {
			if (response.success) {// 请求成功
				setTimeout(function() {
					if(navigator.userAgent.indexOf('MSIE 8') < 0){
						h3c.unloading();
					}
					if(isMsgInMask){
						h3c.showMsgWithMask(response.msg);
					}
					if (successFun) {// 有成功回调函数
						successFun(response);
						resetFlag = false;
						h3c.unmask();
					} else {
						setTimeout(function() {
							h3c.unmask();
							if (resetFlag) {
								h3c.reset();
							}
						}, 200);
					}
				}, 300);
			} else {// 请求失败
				h3c.unmask();
				if (failFun) {// 有失败回调函数
					failFun(response);
					resetFlag = false;
				} else {
					var errmsg = null;
					if (response.msg != "") {
						errmsg = "请求异常:" + response.msg;
					}
					if(isMsgInMask){
						h3c.showMsgWithMask(errmsg);
					}
				}
			}
		}

		function doFailure(response) {
			h3c.unmask();
			if (failFun) {// 有失败回调函数
				failFun(response);
				resetFlag = false;
			} else {
				var errmsg = null;
				if (response.msg != "") {
					errmsg = "请求异常:" + response.msg;
				}
				h3c.error(errmsg, function() {
					h3c.reset();
				});
			}
		}
	},
	/**
	 * 加载grid数据
	 * 
	 * @param {Object}
	 *            gridId grid的property
	 * @param {Object}
	 *            gridId 查询参数 {}
	 * @param {Object}
	 *            isPageGrdi 是否为分页grid true是，false否
	 */
	doLoadGrid : function(gridId, gridParam, isPageGrid) {
		if (isPageGrid) {
			h3c.loadPageGridWithQueryParams(gridId, gridParam);
		} else {
			h3c.loadGridData(gridId, gridParam);
		}
	},
	loadPageGridWithQueryParams : function(gridId, params) {
		var limit = $("#" + gridId + "PageLimit").val();
		try {
			eval("" + gridId + "AjaxPageQuery('" + gridId + "', params, 0, "
					+ limit + ")");
		} catch (e) {
			//h3c.error(e);
		}		
	},
	loadGridData : function(gridId, params) {
		try {
			eval("" + gridId + "AjaxPageQuery('" + gridId + "', params, 0, 0)");
		} catch (e) {
			//h3c.error(e);
		}
	},
	colResize : function(gridId,tTD){
		var table = document.getElementById(gridId+"DivGrid");
		for (j = 0; j < table.rows[0].cells.length; j++) {
			table.rows[0].cells[j].onmousedown = function() {
				//记录单元格 
				tTD = this;
				if (event.offsetX > tTD.offsetWidth - 10) {
					tTD.mouseDown = true;
					tTD.oldX = event.x;
					tTD.oldWidth = tTD.offsetWidth;
				}
			};
			table.rows[0].cells[j].onmouseup = function() {
				//结束宽度调整 
				if (tTD == undefined||tTD==null){
					tTD = this;
				}
				tTD.mouseDown = false;
				tTD.style.cursor = 'default';
			};
			table.rows[0].cells[j].onmousemove = function() {
				//更改鼠标样式 
				if (event.offsetX > this.offsetWidth - 10){
					if(this.style.width==""||this.style.width==null){
						this.style.cursor = 'col-resize';
					}	
				}else{
					this.style.cursor = 'default';
				}
				//取出暂存的Table Cell 
				if (tTD == undefined||tTD==null){
					tTD = this;
				}
				//调整宽度 
				if (tTD.mouseDown != null && tTD.mouseDown == true) {
					tTD.style.cursor = 'default';
					if (tTD.oldWidth + (event.x - tTD.oldX) > 0)
						tTD.width = tTD.oldWidth + (event.x - tTD.oldX);
					//调整列宽 
					tTD.style.width = tTD.width;
					tTD.style.cursor = 'col-resize';
					//调整该列中的每个Cell 
					table = tTD;
					while (table.tagName != 'TABLE'){
						table = table.parentElement;
					}	
					for (j = 0; j < table.rows.length; j++) {
						table.rows[j].cells[tTD.cellIndex].width = tTD.width;
					}
				}
			};
		}
	},
	checkBoxChooseAll : function(gridId,dataIndex) {
		/*
		 * 1、全选框选中，下面全部选中 2、全选框取消选中，下面全取消
		 */
//		var obj = document.getElementById(gridId + dataIndex + "ChooseAll");
//		if (obj.className == 'h3c-grid-check-col') {
//			obj.className = 'h3c-grid-check-col-on';
//		} else {
//			obj.className = 'h3c-grid-check-col';
//		}
//		var checkboxs = document.getElementsByName(gridId + dataIndex + "checkbox");
//		for (var i = 0; i < checkboxs.length; i++) {
//			var e = checkboxs[i];
//			
//			e.checked = !e.checked;
//          if ($('#' + e.id + "-div").attr('class') == 'h3c-grid-check-col-on') {
//              $('#' + e.id + "-div").attr('class', 'h3c-grid-check-col');
//	    	} else {
//        		$('#' + e.id + "-div").attr('class', 'h3c-grid-check-col-on');
//        	}
//			
//			
//		}
//	}
		
		// TODO 2017-7-28-zhoujie  修改GridColumnTag标签的点击全选和点击全部去除功能，没有反选功能
		var obj = document.getElementById(gridId + dataIndex + "ChooseAll");
		var checkboxs = document.getElementsByName(gridId + dataIndex + "checkbox");
		if(obj.className == 'h3c-grid-check-col'){
			for (var i = 0; i < checkboxs.length; i++) {
					var e = checkboxs[i];
					if($('#' + e.id + "-div").attr('class') === 'h3c-grid-check-col'){
						$('#' + e.id + "-div").trigger("click");
					}else{
						continue;
					}
				}
		}else{
			for (var i = 0; i < checkboxs.length; i++) {
				var e = checkboxs[i];
				if($('#' + e.id + "-div").attr('class') === 'h3c-grid-check-col-on'){
					$('#' + e.id + "-div").trigger("click");
				}else{
					continue;
				}
			}
		}
	    //---------------------------------
	},
	//TODO 2017-8-1-zhoujie 在方法的参数增加roleId
	checkBoxControl : function(gridId, roleId,dataIndex, obj) {
		/*
		 * 3、下面全选中，上面必须选中 4、下面有一个没选中，上面必须取消 5、反选（全选框也必须同步）
		 */
	
		// 先对div的class进行操作
		if (obj.className == 'h3c-grid-check-col') {
			obj.className = 'h3c-grid-check-col-on';
			document.getElementById(obj.id.replace('-div', '')).checked = "checked";
			
			//TODO 2017-7-31-zhoujie 点击选中时改变状态
			h3c.setChecked(obj,roleId,true);
			console.log(h3c.checkData);
			//------------------
		} else {
			obj.className = 'h3c-grid-check-col';
			document.getElementById(obj.id.replace('-div', '')).checked = "";
			
			//TODO2017-7-31-zhoujie 点击选中时改变状态
			h3c.setChecked(obj,roleId,false);
			console.log(h3c.checkData);
			//------------------
		}

		// 再对checkbox进行处理
		var checkboxs = document.getElementsByName(gridId + dataIndex + "checkbox");
		var chooseNum = 0;
		var notChooseNum = 0;
		for (var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			if (e.checked == true) {
				chooseNum++;
			}
		}
		var c = document.getElementById(gridId + dataIndex + "ChooseAll")
		if (c) {
			if (chooseNum == checkboxs.length) {
				document.getElementById(gridId + dataIndex + "ChooseAll").className = 'h3c-grid-check-col-on';
				c.checked = true;
			} else {
				document.getElementById(gridId + dataIndex + "ChooseAll").className = 'h3c-grid-check-col';
				c.checked = false;
			}
		}
	},
	radioControl:function(gridId, dataIndex, obj){
		var radios = document.getElementsByName(gridId + dataIndex + "checkbox");
		if (obj.className == 'h3c-grid-radio-col') {
			$('#' + gridId +'DivGrid .h3c-grid-radio-col-on').attr('class','h3c-grid-radio-col');
			obj.className = 'h3c-grid-radio-col-on';
			document.getElementById(obj.id.replace('-div', '')).value = "true";
		} else {
			obj.className = 'h3c-grid-radio-col';
			document.getElementById(obj.id.replace('-div', '')).value = "false";
		}
	},setCheckBoxDisable : function(id) {
		$('#' + id).attr('class', $('#' + id).attr('class') + ' h3c-check-col-gray');
		$('#' + id).removeAttr('onclick');
		$('#' + id).removeAttr('onclick');//执行了2次removeAttr可以解决IE8不起作用的BUG
	},
	setPageToolBar : function(gridId, totalCount, start, limit) {
		$('#' + gridId + 'TotalCount').html(totalCount);// 总条目数
		var totalPage = Math.ceil(totalCount / limit); // 一共有多少页
		var currentPage = start / limit + 1;
		if(totalCount == 0){
			currentPage = 0;
			$('#' + gridId + 'BegRecord').html(0);
			$('#' + gridId + 'EndRecord').html(0);
		}else{
			$('#' + gridId + 'BegRecord').html((currentPage - 1) * limit + 1);
			$('#' + gridId + 'EndRecord').html(
					currentPage * limit > totalCount ? totalCount : currentPage
							* limit);	
		}
		$('#' + gridId + 'Page').html(currentPage);// 当前页
		$('#' + gridId + 'TotalPage').html(totalPage);// 总页数
		$('#' + gridId + 'PageBar #ul1 #first').css('color', 'grey');
		$('#' + gridId + 'PageBar #ul1 #first').removeAttr('onclick');
		$('#' + gridId + 'PageBar #ul1 #prev').css('color', 'grey');
		$('#' + gridId + 'PageBar #ul1 #prev').removeAttr('onclick');
		$('#' + gridId + 'PageBar #ul1 #next').css('color', 'grey');
		$('#' + gridId + 'PageBar #ul1 #next').removeAttr('onclick');
		$('#' + gridId + 'PageBar #ul1 #last').css('color', 'grey');
		$('#' + gridId + 'PageBar #ul1 #last').removeAttr('onclick');
		$('#' + gridId + 'PageBar #ul1 #first').removeAttr('href');
		$('#' + gridId + 'PageBar #ul1 #prev').removeAttr('href');
		$('#' + gridId + 'PageBar #ul1 #next').removeAttr('href');
		$('#' + gridId + 'PageBar #ul1 #last').removeAttr('href');
		if (currentPage!=0 && currentPage != 1) {
			$('#' + gridId + 'PageBar #ul1 #first').attr(
					'onclick',
					gridId + 'AjaxPageQuery(\'' + gridId + '\', ' + gridId
							+ 'Params , 0, ' + limit + ');');
			$('#' + gridId + 'PageBar #ul1 #prev').attr(
					'onclick',
					gridId + 'AjaxPageQuery(\'' + gridId + '\', ' + gridId
							+ 'Params , ' + (currentPage - 2) * limit + ', '
							+ limit + ');');
			$('#' + gridId + 'PageBar #ul1 #first').attr('href',
					'javascript:void(0);');
			$('#' + gridId + 'PageBar #ul1 #prev').attr('href',
					'javascript:void(0);');
			$('#' + gridId + 'PageBar #ul1 #first').removeAttr('style');
			$('#' + gridId + 'PageBar #ul1 #prev').removeAttr('style');
		}
		if (currentPage != totalPage) {
			$('#' + gridId + 'PageBar #ul1 #next').attr(
					'onclick',
					gridId + 'AjaxPageQuery(\'' + gridId + '\', ' + gridId
							+ 'Params , ' + (currentPage) * limit + ', '
							+ limit + ');');
			$('#' + gridId + 'PageBar #ul1 #last').attr(
					'onclick',
					gridId + 'AjaxPageQuery(\'' + gridId + '\', ' + gridId
							+ 'Params , ' + (totalPage - 1) * limit + ', '
							+ limit + ');');
			$('#' + gridId + 'PageBar #ul1 #next').attr('href',
					'javascript:void(0);');
			$('#' + gridId + 'PageBar #ul1 #last').attr('href',
					'javascript:void(0);');
			$('#' + gridId + 'PageBar #ul1 #next').removeAttr('style');
			$('#' + gridId + 'PageBar #ul1 #last').removeAttr('style');
		}
		$('#' + gridId + 'PageIndex').empty();
		var beginPageIndex;
		var endPageIndex;
		//总页数不足100，则全部显示
		if(totalPage<=100){
			beginPageIndex =1;
			endPageIndex = totalPage;
		}else{//总页数大于100，则显示当前页附近的共100个页码，前49个和后50个
			beginPageIndex =currentPage-49;
			endPageIndex = currentPage+50;
			// 当 当前页 前面的页码不足49个时，则显示前100个页码
			if(beginPageIndex<1){
				beginPageIndex=1;
				endPageIndex=100;
			}
			// 当后面的页码不足50个时，则显示后100个页码
			if(endPageIndex>totalPage){
				beginPageIndex=totalPage-99;
				endPageIndex=totalPage;
			}
		}
		for (var j = beginPageIndex-1; j < endPageIndex; j++) {
			if (currentPage == (j + 1)) {
				$('#' + gridId + 'PageIndex').append(
						'<option value=' + (j + 1) + ' selected=selected >'
								+ (j + 1) + '</option>');
			} else {
				$('#' + gridId + 'PageIndex').append(
						'<option value=' + (j + 1) + '>' + (j + 1)
								+ '</option>');
			}
		}
	},
	setGridData : function(gridId, start, rs ,rowClick,rowDbClick) {
		$('#' + gridId + 'DivGrid tbody tr').remove();
		var startNum = start;
		var trHeight = $('#' + gridId + 'DivGrid').attr('hgt');
		var chooseAllCount = 0;
		//TODO 增加一个属性
		var dataIndex = "check";
		//-------------------
		if(rs==null||rs.data==null){
			return ;
		}
		for (var i = 0; i < rs.data.length; i++) {
			var result = rs.data[i];
			var tds = "";
			$('#' + gridId + 'DivGrid thead tr th').each(
					function(index, o) {
						
						if(o.id==gridId+'DivGrid_rowNum'){
							tds =tds + '<td>'+(++startNum)+'</td>';
						}else{
							if(index==0&&o.id!=gridId+'DivGrid_rowNum'){
								startNum++;
							}
							var textAlign = "left";
							if($('#'+o.id).attr('align') != null && $('#'+o.id).attr('align') != undefined && $('#'+o.id).attr('align') != ""){
								textAlign = $('#'+o.id).attr('align');
							}
							if($('#'+o.id).attr('dataHidden')=="true"){
								tds =tds + '<td style="text-align:'+textAlign+';display: none;">'+result[$('#'+o.id).attr('dataIndex')]+'</td>';
							}else if($('#'+o.id).attr('renderer')!=null&&$('#'+o.id).attr('renderer')!=""){
								tds =tds + '<td style="text-align:'+textAlign+'">'+eval($('#'+o.id).attr('renderer')+"(result,"+startNum+")")+'</td>';
							}else if($('#'+o.id).attr('editor')=='checkbox'){
								if(result[$('#'+o.id).attr('dataIndex')]==true||result[$('#'+o.id).attr('dataIndex')]=="true"){
									chooseAllCount++;
									//TODO 2017-8-1-zhoujie 将角色对应的id传入,只增加result.roleid
									tds =tds + '<td style="text-align:'+textAlign+'"><div id='+gridId+$('#'+o.id).attr('dataIndex')+'checkbox'+startNum+'-div class="h3c-grid-check-col-on" onclick="h3c.checkBoxControl(\''+gridId+'\',\''+result.roleid+'\',\''+$('#'+o.id).attr('dataIndex')+'\',this)"><input type="checkbox" style="display:none;" name=\''+gridId+$('#'+o.id).attr('dataIndex')+'checkbox\' id=\''+gridId+$('#'+o.id).attr('dataIndex')+'checkbox'+startNum+'\' checked="checked" /></div></td>';
								}else{
									tds =tds + '<td style="text-align:'+textAlign+'"><div id='+gridId+$('#'+o.id).attr('dataIndex')+'checkbox'+startNum+'-div class="h3c-grid-check-col" onclick="h3c.checkBoxControl(\''+gridId+'\',\''+result.roleid+'\',\''+$('#'+o.id).attr('dataIndex')+'\',this)"><input type="checkbox" style="display:none;" name=\''+gridId+$('#'+o.id).attr('dataIndex')+'checkbox\' id=\''+gridId+$('#'+o.id).attr('dataIndex')+'checkbox'+startNum+'\'  /></div></td>';
									//--------------------------------------------------------
								}
								//TODO 获取到dataIndex的值
								dataIndex = $('#'+o.id).attr('dataIndex');
								//-----------------------------------------
							}else if($('#'+o.id).attr('editor')=='select'){
								if(result[$('#'+o.id).attr('dataIndex')]==null||result[$('#'+o.id).attr('dataIndex')]=="undefined"){
									tds =tds + '<td style="text-align:'+textAlign+'"><input type="hidden" name="gridSelect" value=""/></td>';
								}else{
									tds =tds + '<td style="text-align:'+textAlign+'">'+h3c.getDataDictionaryValue(gridId +'_'+$('#'+o.id).attr('dataIndex')+'_select',result[$('#'+o.id).attr('dataIndex')])+'<input type="hidden" name="gridSelect" value="'+result[$('#'+o.id).attr('dataIndex')]+'"/></td>';
								}
							}else if($('#'+o.id).attr('editor')=='radio'){
								if(result[$('#'+o.id).attr('dataIndex')]==true||result[$('#'+o.id).attr('dataIndex')]=="true"){
									tds =tds + '<td style="text-align:'+textAlign+'"><div id='+gridId+$('#'+o.id).attr('dataIndex')+'radio'+startNum+'-div class="h3c-grid-radio-col-on" onclick="h3c.radioControl(\''+gridId+'\',\''+$('#'+o.id).attr('dataIndex')+'\',this)"><input type="hidden" name="gridRadio" id=\''+gridId+$('#'+o.id).attr('dataIndex')+'radio'+startNum+'\' value="true" /></div></td>';
								}else{
									tds =tds + '<td style="text-align:'+textAlign+'"><div id='+gridId+$('#'+o.id).attr('dataIndex')+'radio'+startNum+'-div class="h3c-grid-radio-col" onclick="h3c.radioControl(\''+gridId+'\',\''+$('#'+o.id).attr('dataIndex')+'\',this)"><input type="hidden"  name="gridRadio" id=\''+gridId+$('#'+o.id).attr('dataIndex')+'radio'+startNum+'\' value="false"/></div></td>';
								}
							}else{
								if(result[$('#'+o.id).attr('dataIndex')]==null||result[$('#'+o.id).attr('dataIndex')]=="undefined"){
									tds =tds + '<td style="text-align:'+textAlign+'"></td>';
								}else{
									if($('#'+o.id).attr('showTitle')=="true"||$('#'+o.id).attr('showTitle')==true){
										tds =tds + '<td style="text-align:'+textAlign+'" title=\''+result[$('#'+o.id).attr('dataIndex')]+'\'>'+result[$('#'+o.id).attr('dataIndex')]+'</td>';
									}else{
										tds =tds + '<td style="text-align:'+textAlign+'">'+result[$('#'+o.id).attr('dataIndex')]+'</td>';
									}
									
								}
							}
						}
			});
			$('#' + gridId + 'DivGrid').append(
					'<tr height="'+trHeight+'" class='+ (i % 2 == 0 ? 'even' : 'odd')+ '>'
					        + tds
					+'</tr>');
			var c = document.getElementById(gridId+"ChooseAll")
			if(c){
				if(chooseAllCount==rs.data.length){
			    	 document.getElementById(gridId+"ChooseAll").className = 'h3c-grid-check-col-on';
			    	 c.checked=true; 
				}else{
				     document.getElementById(gridId+"ChooseAll").className = 'h3c-grid-check-col';
				     c.checked=false; 
				}
			}
		}		
		if (rowClick) {
			var codeRows = document.getElementById(gridId + 'DivGrid').rows;
			if (codeRows.length > 0) {
				for (var i = 1; i < codeRows.length; i++) {
					(function(i) {
						var obj = codeRows[i];
						obj.onclick = function() {
							rowClick(obj.cells);
						};
					})(i);
				}
			}
		}
		if (rowDbClick) {
			var codeRows = document.getElementById(gridId + 'DivGrid').rows;
			if (codeRows.length > 0) {
				for (var i = 1; i < codeRows.length; i++) {
					(function(i) {
						var obj = codeRows[i];
						obj.ondblclick = function() {
							rowDbClick(obj.cells);
						};
					})(i);
				}
			}
		}
		//TODO 2017-8-3-zhoujie 页面在加载完后查看是否全选没有全选的话取消上面的全选
		var checkboxs = document.getElementsByName(gridId + dataIndex + "checkbox");
		var chooseNum = 0;
		var notChooseNum = 0;
		for (var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			if (e.checked == true) {
				chooseNum++;
			}
		}
		var c = document.getElementById(gridId + dataIndex + "ChooseAll")
		if (c) {
			if (chooseNum == checkboxs.length) {
				document.getElementById(gridId + dataIndex + "ChooseAll").className = 'h3c-grid-check-col-on';
				c.checked = true;
			} else {
				document.getElementById(gridId + dataIndex + "ChooseAll").className = 'h3c-grid-check-col';
				c.checked = false;
			}
		}
		h3c.isOver = true;
		//------------------------------------------------------------------------
	},
	gridColAscSort : function(gridId, colNum) {
		var array = $('#' + gridId + 'DivGrid tbody tr').toArray(); // tr 集合转成数组
		var orderby = array.sort(function(a, b) {
			var o1 = $.trim($(a).find('td:eq(' + colNum + ')').html());
			var o2 = $.trim($(b).find('td:eq(' + colNum + ')').html());
			if (!isNaN(o1) && !isNaN(o2)) {
				var o11 = parseInt(o1);
				var o22 = parseInt(o2);
				return o11 < o22 ? -1 : (o11 > o22 ? 1 : 0);// 是数字
			} else {
				return o1 < o2 ? -1 : (o1 > o2 ? 1 : 0);// 不是数字
			}
		});
		$('#' + gridId + 'DivGrid tr:gt(0)').remove();
		$('#' + gridId + 'DivGrid').append(orderby);
	},
	gridColDescSort : function(gridId, colNum) {
		var array = $('#' + gridId + 'DivGrid tbody tr').toArray(); // tr 集合转成数组
		var orderby = array.sort(function(a, b) {
			var o1 = $.trim($(a).find('td:eq(' + colNum + ')').html());
			var o2 = $.trim($(b).find('td:eq(' + colNum + ')').html());
			if (!isNaN(o1) && !isNaN(o2)) {
				var o11 = parseInt(o1);
				var o22 = parseInt(o2);
				return o11 > o22 ? -1 : (o11 < o22 ? 1 : 0);
			} else {
				return o1 > o2 ? -1 : (o1 < o2 ? 1 : 0);
			}
		});
		$('#' + gridId + 'DivGrid tr:gt(0)').remove();
		$('#' + gridId + 'DivGrid').append(orderby);
	},
	changeSortClass : function(obj, gridId, colId) {
		var i = 0;
		$('#' + gridId + 'DivGrid thead tr th').each(function(index, o) {
			if (colId == $('#' + o.id).attr('dataIndex')) {
				return false;
			}
			i = i + 1;
		});
		if (obj.className == 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-carat-2-n-s') {// 升序
			obj.className = 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-triangle-1-n';
			h3c.gridColAscSort(gridId, i);
		} else if (obj.className == 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-triangle-1-n') {// 降序
			obj.className = 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-triangle-1-s';
			h3c.gridColDescSort(gridId, i);
		} else if (obj.className == 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-triangle-1-s') {// 升序
			obj.className = 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-triangle-1-n';
			h3c.gridColAscSort(gridId, i);
		}
	},
	formatDate : function(jsonDate) {
		if (!jsonDate)
			return null;
		var year = String(jsonDate.year + 1900);
		var month = String(jsonDate.month + 1);
		var date = String(jsonDate.date);
		month = h3c.fillLeft(month, "0", 2);
		date = h3c.fillLeft(date, "0", 2);
		var theDate = year + "-" + month + "-" + date;
		return theDate;
	},
	formatDateTime : function(jsonDate) {
		if (!jsonDate)
			return null;

		var year = String(jsonDate.year + 1900);
		var month = String(jsonDate.month + 1);
		var date = String(jsonDate.date);
		var hour = String(jsonDate.hours);
		var minute = String(jsonDate.minutes);
		var second = String(jsonDate.seconds);
		month = h3c.fillLeft(month, "0", 2);
		date = h3c.fillLeft(date, "0", 2);
		hour = h3c.fillLeft(hour, "0", 2);
		minute = h3c.fillLeft(minute, "0", 2);
		second = h3c.fillLeft(second, "0", 2);
		var theDateTime = year + "-" + month + "-" + date + " " + hour + ":"
				+ minute + ":" + second;
		return theDateTime;
	},
	/**
	 * 获取主页面的Iframe对象，用此方法可以刷新模块，一般情况不需要输入parentWinId
	 * parentWinId的使用场景，将我们的模块用弹出框来操作的时候，且我们的模块不是在.portal_framework_iframe中打开的
	 * 使用例子可以详见UpdateCronJob.jsp
	 * @returns
	 */
	getRootPageObject : function(parentWinId) {
		var obj = h3c.getTopWindow();
		try{
			var iframeObj = obj.document.getElementById($(
					".portal_framework_iframe", obj.document).attr('id')).contentWindow;
			return iframeObj;
		}catch(e){
			var id = "#"+parentWinId+"welcome";
			return obj.document.getElementById($(id, obj.document).attr('id')).contentWindow;
		}	
	},
	/**
	 * 获取当前父页面的Iframe对象，用此方法可以刷新上一层弹出框的内容
	 * @param iframeId
	 * @returns
	 */
	getParentIframeObject:function(iframeId) {
	       return window.parent.document.getElementById($("#"+iframeId+"welcome", window.parent.document).attr('id')).contentWindow;
	},
	getDataDictionaryValue : function(id, key) {
		var data = $('#' + id).val();
		if (data.indexOf("['" + key + "',") != -1) {
			var values = data.split("['" + key + "',");
			if (values != null) {
				return values[1].split("]")[0].replace("\'", "").replace("\'",
						"");
			}
		}
	},
	fillLeft : function(oldstr, ch, len) {
		var newstr = oldstr;
		for (var i = oldstr.length; i < len; i++) {
			newstr = ch + newstr;
		}
		return newstr;
	},
	fillRight : function(oldstr, ch, len) {
		var newstr = oldstr;
		for (var i = oldstr.length; i < len; i++) {
			newstr = newstr + ch;
		}
		return newstr;
	},
	renderDate : function(dateVal) {
		if (!dateVal || dateVal == "") {
			dateVal = new Date();
		} else {
			if (typeof dateVal == 'string') {
				dateVal = Date.parseDate(dateVal, 'Y-m-d');
			}
		}
		return Ext.util.Format.date(dateVal, 'Y-m-d');
	},
	formatDate : function(now) {
		var year = (now.getYear() < 1900 ? (1900 + now.getYear()) : now
				.getYear());
		var month = now.getMonth() + 1;
		if (parseInt(month) < 10) {
			month = "0" + month;
		}
		var date = now.getDate();
		if (parseInt(date) < 10) {
			date = "0" + date;
		}
		var hour = now.getHours();
		if (parseInt(hour) < 10) {
			hour = "0" + hour;
		}
		var minute = now.getMinutes();
		if (parseInt(minute) < 10) {
			minute = "0" + minute;
		}
		var second = now.getSeconds();
		if (parseInt(second) < 10) {
			second = "0" + second;
		}
		return year + "-" + month + "-" + date + " " + hour + ":" + minute
				+ ":" + second;
	},
	formatTimeStamp : function(tm) {
		/*
		 * var tt = new Date(parseInt(tm)).toLocaleString().replace(/年|月/g, "/")
		 * .replace(/日/g, " ").replace(/:\d{1,2,3}$/, ' ');
		 */
		if (tm == null || tm == "" || tm == "null" || tm == "undefined") {
			return "";
		}
		return h3c.formatDate(new Date(parseInt(tm)));
	},
	
	/**
	 * 2017-7-31-zhoujie 
	 * 点击check按钮时将check和dispatch放到h3c.checkdata中去
	 * @param isChecked
	 * 		true  : 授权
	 *      false : 取消授权
	 */
	//TODO 2017-7-31-zhoujie 点击check按钮时将check和dispatch放到h3c.checkdata中去
	setChecked: function (obj,roleId,isChecked){
		//点击的是分授权
		if(obj.id.indexOf("dispatch") > 0){
			
			//判断是否已经有对应的角色了，没有角色，不能分授权
			 
			if(document.getElementById(obj.id.replace("dispatchauth","check")).className === "h3c-grid-check-col"){
				h3c.autoCloseAlert("请先给用户赋予相应的角色！");
				obj.className = 'h3c-grid-check-col';
				document.getElementById(obj.id.replace('-div', '')).checked = "";
				return;
			}
			
			for(var i=0;i<h3c.checkData.length;i++){
				if(h3c.checkData[i].roleid == roleId){
					h3c.checkData[i].dispatchauth = isChecked;
					break;
				}
			}
		}else{  //点击的是角色授权
			for(var i=0;i<h3c.checkData.length;i++){
				if(h3c.checkData[i].roleid == roleId){
					h3c.checkData[i].check = isChecked;
				
					//如果分授权也被选中，则一并取消掉
					if(!isChecked){
						var dispatcher = document.getElementById(obj.id.replace("check","dispatchauth"));
						if(dispatcher.className === "h3c-grid-check-col-on"){
							dispatcher.className = 'h3c-grid-check-col';
							document.getElementById(obj.id.replace('-div', '')).checked = "";
							//同时将h3c.checkData中记录的分授权设置为false
							h3c.checkData[i].dispatchauth = isChecked;
						}
					}
					break;
				}
			}
		}
	}
	//--------------------------------------------------------
}

document.write("<script language=javascript src='basejs/html/checkbox.js'></script>");
document.write("<script language=javascript src='basejs/html/panel.js'></script>");
document.write("<script language=javascript src='basejs/html/mask.js'></script>");