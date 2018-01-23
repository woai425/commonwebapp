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
<h3c:form action="userController.do?updateUser">
	<table id="myform" style="width: 100%; border: 0px solid red; background: #fff; height: 100%">
	<h3c:tabLayOut column="4"/>
		<tr>
			<h3c:textEdit property="loginname" required="true" value="${user.loginname }" label="用户登录名" readonly="readonly"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="username" label="用户名" value="${user.username }"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="passwd" inputType="password" value="${user.passwd }" label="密码" ></h3c:textEdit>
			<td  colspan="1"><input class="btn" type="button" value="修改密码" style="font-size: 13px;font-weight:normal;" onclick="changePassWord()"></td>
		</tr>
		<tr id="pwdLevel" style="display: none;">
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
		</tr>
		<tr>
			<h3c:select property="dept" codeType="DEPT" label="部门" value="${user.dept }"></h3c:select>
		</tr>
		<tr>
			<h3c:select property="useful" label="状态" value="${user.useful }" codeType="USEFUL"></h3c:select>
		</tr>
		<tr>
			<h3c:textEdit property="email" label="邮箱" value="${user.email }"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="phone" label="电话" value="${user.phone }"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="address" label="地址" value="${user.address }" placeholder="地址长度小于100"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:textEdit property="description" label="描述" value="${user.description }"></h3c:textEdit>
		</tr>
		<tr>
			<h3c:select property="leaveFlag" label="是否脱离此组织" value="0" codeType="YESNO"></h3c:select>
		</tr>
		<tr height="5px"></tr>
		<tr>
			<td></td>
			<td align="right" colspan="1">
				<table>
					<tr>
						<td align="center"><h3c:button text="修改" handler="update()" property="updatebut" fontAwesome="icon-save"></h3c:button></td>
						<td width="30%"></td>
					</tr>
				</table> 
				<h3c:hidden property="groupid" value="${groupid }" /> 
				<h3c:hidden property="userid" value="${user.userid }" />
				<h3c:hidden property="usertype" value="${user.usertype }" />
			<td>
		</tr>
		<tr height="30px"></tr>
	</table>
</h3c:form>
<script type="text/javascript">
$(function(){
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
             $("#pwdLevel_f").hide();/* #passwd{margin-bottom: 0} */
             $("#passwd").css("margin-bottom","9px");
             $("#passwd").parent().prev().css("padding-top","1px");
         }else{
        	 $("#pwdLevel").show();
             $("#pwdLevel_f").show();
             $("#passwd").css("margin-bottom","0");
             $("#passwd").parent().prev().css("padding-top","10px");
        	 var level = checkPassword($(this));//参数是jquery对象
        	
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
function checkPassword(pwdinput) {
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
}

function update() {
	var groupid = document.commForm.groupid.value;
	h3c.submit("commForm", function(rs) {
		h3c.alert("修改成功!", function() {
			h3c.closeWindow("updateUser");
			h3c.getRootPageObject().loadGrid2();
		});
	}, function(rs) {
		h3c.error(rs.msg);
	});
}
$(function(){
	$("#passwd").attr("readonly","readonly");
})

function changePassWord(){
	$("#passwd").removeAttr("readonly");
	$("#passwd").val("");
}
$(function(){
	$("#address").attr("maxlength","100");
})
</script>