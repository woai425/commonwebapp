<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<h3c:head />
<style type="text/css">
	.pwdLevel_weak{ float:left; background:#ED3500; width:62px; height:4px; margin-top:5px; margin-left:5px; _margin-top:0px;_height:2px; font-size:0px;}
    .pwdLevel_medium{ float:left; background: #F38313; width:62px; height:4px; margin-top:5px; margin-left:5px; _margin-top:0px;_height:2px; font-size:0px;}
    .pwdLevel_tough{ float:left; background: #6DA005; width:62px; height:4px; margin-top:5px; margin-left:5px; _margin-top:0px;_height:2px; font-size:0px;}
	.pwdLevel_primary{ float:left; background:#d6d3d3; width:62px; height:4px; margin-top:5px; _margin-top:0px; margin-left:5px; _height:2px;font-size:0px;}
	.pwdLevel_font{ float:left; width:62px; margin-left:5px; text-align:center; color:#b0adad; font-size:8px; }
</style>
<div style="background-color: #fff;height: 600px;width: 460px;overflow: auto;">
<h3c:form action="passwordController.do?updateUser">
<h3c:groupbox isRetracted="true" style="margin: 10px 10px 10px 10px;" property="userinfo" title="基本信息">
	<table style="text-align: center;">
	<h3c:tabLayOut column="2"/>
		<tr align="center">
			<h3c:textEdit property="loginname" value="${user.loginname }" label="用户登录名" width="260"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="username" required="true" label="用户名" value="${user.username }" width="260"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="passwd" inputType="password" value="${user.passwd }" label="密码" width="260" onchange="checkLength()"></h3c:textEdit>
		</tr>
		<!-- <tr id="pwdLevel" style="display: none;">
			<td></td>
			<td style="padding: 0 0 0 0;margin: 0 0 0 0">
				<div class="pwdLevel_primary" id='pwdLevel_1'> </div>
            	<div class="pwdLevel_primary" id='pwdLevel_2'> </div>
            	<div class="pwdLevel_primary" id='pwdLevel_3'> </div>
			</td>
		</tr>
		<tr id="pwdLevel_f" style="display: none;">
			<td></td>
			<td style="padding: 0 0 0 0;margin: 0 0 0 0">
				<div class="pwdLevel_font"> 弱</div>
            	<div class="pwdLevel_font"> 中</div>
            	<div class="pwdLevel_font"> 强</div>
			</td>       
		</tr>  -->
		<tr>
			<h3c:textEdit property="confirmPasswd"  inputType="password" value="${user.passwd }" label="确认密码" width="260" onchange="checkPassword()"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:select property="dept" codeType="DEPT" label="部门" value="${user.dept }" width="276"></h3c:select>
		</tr>
		<tr>
			<h3c:textEdit property="email" required="true" label="邮箱" value="${user.email }" width="260"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="phone" label="电话" value="${user.phone }" width="260"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="address" label="地址" value="${user.address }" width="260"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:hidden property="userid" value="${user.userid }" />
		</tr>
		<tr>
			<h3c:hidden property="groupid" value="${groupid }" />
		</tr>
		<tr>
			<h3c:hidden property="password" value="${user.passwd }" />
		</tr>
	</table>
</h3c:groupbox>
<!-- <h3c:groupbox isRetracted="true" style="margin: 10px 10px 10px 10px;" property="usergroupinfo" title="组织机构">
<table style="width: 100%;">
	<tr>
		<td style="width: 22%;text-align: right;font-size:13px;font-family:Microsoft YaHei;">
			组织机构
		</td>
		<td style="width: 66%;">
			<div id="tree1" style="height: 166px;overflow: auto;border: 1px solid #C0C0C0;"></div>
		</td>
		<td style="width: 12%;">
		</td>
	</tr>
</table>
</h3c:groupbox> -->
<tr>
	<td>
		<table>
			<tr height="6px"></tr>
			<tr>
				<td width="36%"></td>
				<td align="center"><h3c:button text="保存" handler="updateUser()" property="update" fontAwesome="icon-save"></h3c:button></td>
				<td width="10%"></td>
				<td align="center"><h3c:button text="取消" handler="closeWindow()" property="close" fontAwesome="icon-undo"></h3c:button></td>
				<td width="30%"></td>
			</tr>
		</table> 
	<td>
</tr>
</h3c:form>
</div>
<script type="text/javascript">
$(function(){
	$('#passwd').attr("readonly","readonly");
	$('#confirmPasswd').attr("readonly","readonly");
	$('#loginname').attr("readonly","readonly");
});

/*$(function(){
	$('#passwd').blur(function(){
		if(!$("#pwdLevel").is(":hidden")&&!$("#pwdLevel_f").is(":hidden")){//显示状态时缩小间距
			$("#passwd").css("margin-bottom","0");
			$("#passwd").parent().prev().css("padding-top","10px");
		}else{
			$("#passwd").css("margin-bottom","9px");
			$("#passwd").parent().prev().css("padding-top","1px");
		}
	});
	$('#passwd').focus(function(){
		if(!$("#pwdLevel").is(":hidden")&&!$("#pwdLevel_f").is(":hidden")){//显示状态时缩小间距
			$("#passwd").css("margin-bottom","0");
			$("#passwd").parent().prev().css("padding-top","10px");
		}else{
			$("#passwd").css("margin-bottom","9px");
			$("#passwd").parent().prev().css("padding-top","1px");
		}
		
	});
	 $('#passwd').keyup(function () {
		 var val = $(this).val();
		 if (!val) {
             $("#pwdLevel").hide();
             $("#pwdLevel_f").hide();
            $("#passwd").css("margin-bottom","9px");
             $("#passwd").parent().prev().css("padding-top","1px");
         }else{
        	 $("#pwdLevel").show();
             $("#pwdLevel_f").show();
             $("#passwd").css("margin-bottom","0");
             $("#passwd").parent().prev().css("padding-top","10px");
        	 var level = checkPasswordS($(this));//参数是jquery对象
        	
        	 if (level > 0 && level < 2) {
                 weak();
             } else if (level >= 2 && level < 4) {
                 medium();
             } else if (level >= 4) {
                 tough();
             }
         }
	 });
});
function weak(){
	 $('#pwdLevel_1').attr('class', 'pwdLevel_weak');
	 $('#pwdLevel_2').attr('class', 'pwdLevel_primary');
	 $('#pwdLevel_3').attr('class', 'pwdLevel_primary');
}
function medium(){
	 $('#pwdLevel_1').attr('class', 'pwdLevel_weak');
	 $('#pwdLevel_2').attr('class', 'pwdLevel_medium');
	 $('#pwdLevel_3').attr('class', 'pwdLevel_primary');
}
function tough(){
	 $('#pwdLevel_1').attr('class', 'pwdLevel_weak');
	 $('#pwdLevel_2').attr('class', 'pwdLevel_medium');
	 $('#pwdLevel_3').attr('class', 'pwdLevel_tough');
}
function checkPasswordS(pwdinput) {
       var maths, smalls, bigs, corps, cat, num;
       var str = $(pwdinput).val()
       var len = str.length;
      // var cat = /.{32}/g
       if (len == 0) return 1;
       //if (len > 16) { $(pwdinput).val(str.match(cat)[0]); }
       cat = /.*[\u4e00-\u9fa5]+.*$/
       if (cat.test(str)) {
           return -1;
       }
       cat = /\d/;
       var maths = cat.test(str);
       cat = /[a-z]/;
       var smalls = cat.test(str);
       cat = /[A-Z]/;
       var bigs = cat.test(str);
       var corps = corpses(pwdinput);
       var num = maths + smalls + bigs + corps;

       if (len < 6) { return 1; }

       if (len >= 6 && len <= 8) {
           if (num == 1) return 1;
           if (num == 2 || num == 3) return 2;
           if (num == 4) return 3;
       }

       if (len > 8 && len <= 11) {
           if (num == 1) return 2;
           if (num == 2) return 3;
           if (num == 3) return 4;
           if (num == 4) return 5;
       }

       if (len > 11) {
           if (num == 1) return 3;
           if (num == 2) return 4;
           if (num > 2) return 5;
       }
}

function corpses(pwdinput) {
    var cat = /./g
    var str = $(pwdinput).val();
    var sz = str.match(cat);//如果是正则表达式，返回数组
    for (var i = 0; i < sz.length; i++) {
        cat = /\d/;
        maths_01 = cat.test(sz[i]);//若为false，则非数字
        cat = /[a-z]/;
        smalls_01 = cat.test(sz[i]);//若为false，则非小写字母
        cat = /[A-Z]/;
        bigs_01 = cat.test(sz[i]);//若为false，则非大写字母
        if (!maths_01 && !smalls_01 && !bigs_01) { return true; }
    }
    return false;
}*/
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/portal/wbms/authmgmt/password/Password.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/layout/lib/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/layout/lib/jqTree/tree.jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/layout/lib/jqTree/jqtree.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/layout/lib/jquery/jquery.cookie.js"></script>