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
		style="background-color: #FFFFFF; width: 100%; cellpadding: 0px; cellspacing: 0px; padding: 0 0 0 0; margin: 0 0 0 0; overflow: hidden; border-collapse: collapse;"">
		<tr>
			<td
				style="padding-right: 20px; padding-left: 20px; padding-bottom: 10px; padding-top: 10px;">
				<div>
					<h4>标题栏 机构管理</h4>
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
						style="overflow-x: hidden; min-height: 200px; height: 440px;">
						<table id="demoDivGrid" class="h3c-table">
							<thead>
								<tr>
									<th>#</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Username</th>
									<th style="width: 26px;"></th>
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
							<td>共有<span id="totalCount">15</span>条记录，当前显示<span id="demoBegRecord">1</span> - <span id="demoEndRecord">10</span>，第<span
								id="page">1</span>/<span id="totalPage">2</span>页
							</td>
							<td>
								<div class="pagination" align="right">
									<ul id="ul1">
										<li><a id="first">首页</a></li>
										<li><a id="prev">上一页</a></li>
										<li><select name="demoPageIndex" class="input-xlarge" onchange="demoAjaxPageIndexQuery(this.value)"
												id="demoPageIndex" style="width: 55px; height: 34px">
											</select>									
										</li>
										<li><a id="next">下一页</a></li>
										<li><a id="last">尾页</a></li>
										
									</ul>
									<ul id="ul2">
									<li><select name="demoPageLimit" id="demoPageLimit" class="input-xlarge"  onchange="demoAjaxPageLimitQuery(this.value)"
											style="width: 55px; height: 34px;border-left-width: 1px;">
												<option value="10">10</option>
												<option value="15">15</option>
												<option value="25">25</option>
												<option value="50">50</option>
										</select></li>
									</ul>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>

	<script>
	    function demoAjaxPageIndexQuery(obj){
	    	var start = (obj-1)*10;
	    	demoAjaxPageQuery('demo', null, start, 10); 
	    }
	    function demoAjaxPageLimitQuery(obj){
	    	demoAjaxPageQuery('demo', null, 0, obj); 
	    }
		function demoAjaxPageQuery(gridId, params, start, limit) {
			//每次ajax请求时，将表格第二行开始和分页栏数字清空
			$("#" + gridId + "DivGrid tr:gt(0)").remove();

			$.ajax({
						url : "gridController.do?query",
						type : "post",
						data : "start=" + start + "&limit=" + limit,
						dataType : "json",
						success : function(rs) {
							for (var i = 0; i < rs.data.length; i++) {
								var result = rs.data[i];
								$("#" + gridId + "DivGrid").append(
										"<tr class="
												+ (i % 2 == 0 ? 'even' : 'odd')
												+ "><td>" + result.aaz001
												+ "</td><td>" + result.aab001
												+ "</td><td>" + result.aab004
												+ "</td><td>" + result.aab301
												+ "</td><td>" + result.aaf015
												+ "</tr>");
							}

							//////////////////////pagetoolbar////////////////////////////////
							$("#totalCount").html(rs.totalCount);//总条目数
							var totalPage = Math.ceil(rs.totalCount / limit); //一共有多少页
							var currentPage = start / limit + 1;
							$("#page").html(currentPage);//当前页
							$("#totalPage").html(totalPage);//总页数
							$("#demoBegRecord").html((currentPage-1)*limit+1);
							$("#demoEndRecord").html(currentPage*limit>rs.totalCount?rs.totalCount:currentPage*limit);
							
				            $("#" + gridId + "PageBar #ul1 #first").css('color','grey');
							$("#" + gridId + "PageBar #ul1 #first").removeAttr('onclick');
							$("#" + gridId + "PageBar #ul1 #prev").css('color','grey');
							$("#" + gridId + "PageBar #ul1 #prev").removeAttr('onclick');
							$("#" + gridId + "PageBar #ul1 #next").css('color','grey');
							$("#" + gridId + "PageBar #ul1 #next").removeAttr('onclick');
							$("#" + gridId + "PageBar #ul1 #last").css('color','grey');
							$("#" + gridId + "PageBar #ul1 #last").removeAttr('onclick');
							$("#" + gridId + "PageBar #ul1 #first").removeAttr('href');
							$("#" + gridId + "PageBar #ul1 #prev").removeAttr('href');
							$("#" + gridId + "PageBar #ul1 #next").removeAttr('href');
							$("#" + gridId + "PageBar #ul1 #last").removeAttr('href');
							if(currentPage!=1){
								$("#" + gridId + "PageBar #ul1 #first").attr("onclick","demoAjaxPageQuery('demo', null , 0, "+limit+");");
								$("#" + gridId + "PageBar #ul1 #prev").attr("onclick","demoAjaxPageQuery('demo', null , "+(currentPage-2)*limit+", "+limit+");");
								$("#" + gridId + "PageBar #ul1 #first").attr('href','javascript:void(0);');
								$("#" + gridId + "PageBar #ul1 #prev").attr('href','javascript:void(0);');
								$("#" + gridId + "PageBar #ul1 #first").removeAttr("style");
								$("#" + gridId + "PageBar #ul1 #prev").removeAttr("style");
							} 
							if(currentPage!=totalPage){
								$("#" + gridId + "PageBar #ul1 #next").attr("onclick","demoAjaxPageQuery('demo', null , "+(currentPage)*limit+", "+limit+");");
								$("#" + gridId + "PageBar #ul1 #last").attr("onclick","demoAjaxPageQuery('demo', null , "+(totalPage-1)*limit+", "+limit+");");
								$("#" + gridId + "PageBar #ul1 #next").attr('href','javascript:void(0);');
								$("#" + gridId + "PageBar #ul1 #last").attr('href','javascript:void(0);');
								$("#" + gridId + "PageBar #ul1 #next").removeAttr("style");
								$("#" + gridId + "PageBar #ul1 #last").removeAttr("style");
							} 
							$("#" + gridId + "PageIndex").empty();
							for (var j = 0; j < totalPage; j++) {
								if(currentPage==(j+1)){
									$("#" + gridId + "PageIndex").append("<option value="+(j+1)+" selected=\"selected\">"+(j+1)+"</option>");
								}else{
									$("#" + gridId + "PageIndex").append("<option value="+(j+1)+">"+(j+1)+"</option>");
								}
								
							}
						},
						error : function() {
							h3c.error("加载失败...");
						},
						async : true
					});
		}
		demoAjaxPageQuery('demo', null , 0, 10);
	</script>
</body>
</html>