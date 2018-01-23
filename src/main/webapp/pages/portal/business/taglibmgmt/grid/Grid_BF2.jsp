<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<h3c:head />
</head>
<body>
	<table id="demo"
		style="background-color: #FFFFFF; width: 80%; cellpadding: 0px; cellspacing: 0px; padding: 0 0 0 0; margin: 0 0 0 0; overflow: hidden; border-collapse: collapse;">
		<tr>
			<td id="demo_padding"
				style="padding-right: 20px; padding-left: 20px; padding-bottom: 10px; padding-top: 10px;">

				<h4 id="demo_title" align="left">标题栏 机构管理</h4>
				<hr
					style="padding: 0 0 0 0; margin: 0 0 0 0; height: 1px; border: none; border-top: 1px dashed #0066CC;">
				<h3c:toolbar property="sss">
					<h3c:button property="sssss" text="测试" cls="btn-success" />
					<h3c:button property="sss" text="sss" cls="btn btn-warning"
						fontAwesome="icon-plus" bgcolor="#FFFFFF" />
					<button class="btn">
						<i class="icon-plus"></i> New User
					</button>
					<button class="btn">Import</button>
					<button class="btn">Export</button>
					<div class="btn-group"></div>
				</h3c:toolbar>
				<div id="demoDiv" class="well"
					style="min-height: 200px; height: 440px; overflow: auto;">
					<table id="demoDivGrid" class="h3c-table" hgt="40">
						<thead>
							<tr>
								<th id="demoDivGrid_rowNum" style="width: 30px;" >#</th>
								<th id="demoDivGrid_aaz001" dataIndex="check" editor="checkbox" 
									style="width: 120px;"><span style="float: left"><div
											class="checkboxFive">
											<input class="cbox" type="checkbox" name="demochooseall"
												id="demochooseall" onclick="h3c.checkBoxChooseAll('demo')" /><label
												for='demochooseall'></label>
										</div></span></th>
								<th id="demoDivGrid_aab001" dataIndex="aab001" align="right"
									style="width: 210px;"><span>
									   First Name</span><span class="h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-carat-2-n-s" onclick="changeSortClass(this)">&nbsp;&nbsp;&nbsp;&nbsp;</span></th>
								<th id="demoDivGrid_aab004" dataIndex="aab004"
									style="width: 280px;"><span style="float: left;">Last
										Name</span></th>
								<th id="demoDivGrid_aab301" dataIndex="aab301"
									style="width: 210px;"><span style="float: left;">Username</span></th>
								<th id="demoDivGrid_aae013" dataIndex="aae013"
									style="width: 210px;"><span style="float: left;">demo</span></th>
							</tr>
						</thead>
						<tbody>
							<tr class="odd">
								<td>1</td>
								<td>Mark</td>
								<td>Tompson</td>
								<td>the_mark7</td>
								<td><a href="user.html"><i class="icon-pencil"></i></a> <a
									href="#myModal" role="button" data-toggle="modal"><i
										class="icon-remove"></i></a></td>
							</tr>
							<tr class="even">
								<td>2</td>
								<td>Ashley</td>
								<td>Jacobs</td>
								<td>ash11927</td>
								<td><a href="user.html"><i class="icon-pencil"></i></a> <a
									href="#myModal" role="button" data-toggle="modal"><i
										class="icon-remove"></i></a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<table id="demoPageBar"
					style="width: 100%; height: 100%; cellpadding: 0px; cellspacing: 0px; padding: 0 0 0 0; margin: 0 0 0 0; overflow: hidden; border-collapse: collapse;">
					<tr>
						<td>共有<span id="demoTotalCount">15</span>条记录，当前显示<span
							id="demoBegRecord">1</span> - <span id="demoEndRecord">10</span>，第<span
							id="demoPage">1</span>/<span id="demoTotalPage">2</span>页
						</td>
						<td>
							<div class="pagination" align="right">
								<ul id="ul1">
									<li><a id="first">首页</a></li>
									<li><a id="prev">上一页</a></li>
									<li><select name="demoPageIndex" class="input-xlarge"
										onchange="demoAjaxPageIndexQuery(this.value)"
										id="demoPageIndex" style="width: 55px; height: 30px">
									</select></li>
									<li><a id="next">下一页</a></li>
									<li><a id="last">尾页</a></li>
								</ul>
								<ul id="ul2" style="padding-left: 5px;">
									<li><select name="demoPageLimit" id="demoPageLimit"
										class="input-xlarge"
										onchange="demoAjaxPageLimitQuery(this.value)"
										style="width: 55px; height: 30px; border-left-width: 1px;">
											<option value="10">10</option>
											<option value="15">15</option>
											<option value="25">25</option>
											<option value="50">50</option>
									</select></li>
								</ul>
							</div>
						</td>
					</tr>
				</table> <input type="hidden" id="demoData" name="demoData" />
			</td>
		</tr>
	</table>

	<script>
		var demoParams = {};
		function demoAjaxPageIndexQuery(pageIndex) {
			var start = (pageIndex - 1) * demoParams.limit;
			demoAjaxPageQuery('demo', demoParams, start, demoParams.limit);
		}
		function demoAjaxPageLimitQuery(limit) {
			demoAjaxPageQuery('demo', demoParams, 0, limit);
		}
		function demoAjaxPageQuery(gridId, params, start, limit) {
			//每次ajax请求时，将表格第二行开始和分页栏数字清空
			$('#' + gridId + 'DivGrid tr:gt(0)').remove();
			demoParams.start = start;
			demoParams.limit = limit;
			$
					.ajax({
						url : 'gridController.do?query',
						type : 'post',
						data : demoParams,
						dataType : 'json',
						success : function(rs) {
							h3c.setGridData(gridId, start, rs);
							if ($("#demoPageBar").length > 0) {
								h3c.setPageToolBar(gridId, rs.totalCount,
										start, limit);
							}
							if ($('#demo_title').length <= 0
									&& $('#demo #demo_padding .btn-toolbar').length <= 0) {
								$('#demo_padding').css('padding-top', 20);
							}
							if ($('#demoPageBar').length <= 0) {
								$('#demo_padding').css('padding-bottom', 20);
							}
						},
						error : function() {
							h3c.error("加载失败...");
						},
						async : true
					});
		}
		demoParams.aab004 = '天上人间';
		demoAjaxPageQuery('demo', demoParams, 0, 10);

		function chooseAll() {
			var checkboxs = document.getElementsByName("democheckbox");
			for (var i = 0; i < checkboxs.length; i++) {
				var e = checkboxs[i];
				e.checked = !e.checked;
			}
		}
		function gridColAscSort(gridId,colNum){
			 $(function () {  
		            var array = $('#demoDivGrid tbody tr').toArray(); //tr 集合转成数组  		            
		            var orderby = array.sort(  
		                function (a, b) {  
		                    var o1 = $.trim($(a).find('td:eq(5)').html());
		                    var o2 = $.trim($(b).find('td:eq(5)').html());
		                    return o1 < o2 ? -1 : (o1 > o2 ? 1 : 0);  
		                }  
		            );
		            $('#demoDivGrid tr:gt(0)').remove();
		            $('#demoDivGrid').append(orderby);
		            
		        });  
		}
		function gridColDescSort(gridId,colNum){
			 $(function () {  
		            var array = $('#demoDivGrid tbody tr').toArray(); //tr 集合转成数组  		            
		            var orderby = array.sort(  
		                function (a, b) {  
		                    var o1 = $.trim($(a).find('td:eq(5)').html());
		                    var o2 = $.trim($(b).find('td:eq(5)').html());
		                    return o1 > o2 ? -1 : (o1 < o2 ? 1 : 0);  
		                }  
		            );
		            $('#demoDivGrid tr:gt(0)').remove();
		            $('#demoDivGrid').append(orderby);
		            
		        });  
		}
		function changeSortClass(obj){
			if(obj.className == 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-carat-2-n-s'){
				gridColAscSort();
				obj.className = 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-triangle-1-n';
			}else if(obj.className == 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-triangle-1-n'){
				obj.className = 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-triangle-1-s';
				gridColDescSort();
			}else if(obj.className == 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-triangle-1-s'){
				gridColAscSort();
				obj.className = 'h3c-ui-sortable-column-icon h3c-ui-icon h3c-ui-icon-triangle-1-n';
			}
		}
	</script>
</body>
</html>