<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<html>
<head>
<h3c:head />
</head>
<body>         <h3c:form action="cronJobController.do?updateCronJob" name="UpdateCronJob">
				 <table style="height: 100%; width: 100%; border: 0px solid red; background: #fff; " >
				   <tr style="height:5px;" ></tr>	
					<tr >
						<td style="width:35px;"></td>
						<h3c:hidden property="taskid" value="${cronJob.taskid}"></h3c:hidden>
						<h3c:textEdit label="任务名称" readonly="true" property="jobname"  labelAlign="true"  required="true"  value="${cronJob.jobname}"/>
					    <h3c:textEdit label="任务分组" readonly="true" property="jobgroup" labelAlign="true" required="true"   value="${cronJob.jobgroup}" />
					</tr>
					<tr>
						<td style="width:35px;"></td>
						<h3c:textEdit label="任务描述" property="description" labelAlign="true" required="true" value="${cronJob.description}"/>
					    <h3c:textEdit label="cron表达式" property="cron" labelAlign="true" required="true" value="${cronJob.cron}"/>
					</tr>
					<tr>
						<td style="width:35px;"></td>
						<h3c:textEdit label="任务类名" property="classname" labelAlign="true"  value="${cronJob.classname}"/>
					    <h3c:textEdit property="updator" label="修改人"  labelAlign="true" required="true"  value="${cronJob.updator}"></h3c:textEdit>
					</tr>
					<tr>
						<td style="width:35px;"></td>
						<h3c:select property="active" codeType="ACTIVE" label="是否有效" value="${cronJob.active}" labelAlign="true" ></h3c:select>		
					</tr>
					<tr >
					<td></td>
					<td></td>
					<td >
					<div style="width:220px;"align="right">
					   <h3c:button property="update" text="保存" fontAwesome="icon-save" handler="updateCronJob()" /> 
					</div>
					</td>
					</tr>
					<tr style="height:10px;"></tr>
				</table>
				</h3c:form>
</body>

<script type="text/javascript">
//更新定时任务
function updateCronJob() {
	if(checkDescription()&&checkUpdator()&&checkClassName()&&checkCron()){
	  h3c.submit("UpdateCronJob",function(){
		//调用父页面查询刷新结果,openCronJob是其他页面调用CronJob模块的ID，当我们的模块被其他页面用弹出框弹出时才会使用到这个ID,一般不会使用
		h3c.getRootPageObject("openCronJob").searchCronJob();	
		//关闭弹出框
		h3c.closeWindow("updateCronJob");
	  });
	}
}

//检查任务类是否存在，要在后台执行
function checkClassName(){
	var classname=document.getElementById('classname').value;
	var url='cronJobController.do?checkClassName';
	var result=true;
	$.ajax({
		type : "POST",
		url : url,
		data : {"className":classname},
		dataType : "json",
		success : function(rs) {
			         if(!rs.success){
			           result=false;
				       h3c.warning(rs.msg);					   		
			         }
		          },
	    async:false
	});	
	return result;
}

//检查修改人格式
function checkUpdator(){
    var re=/^[\w$\u4E00-\u9FA5]*$/g
	var regex=/[\u4E00-\u9FA5]/g;
	var regex2=/[\w$]/g;
	var updator=document.getElementById('updator').value;
	if(!re.test(updator)){
		h3c.warning("修改人名字格式不正确！");
		return false;
	}
	if((updator.match(regex)==null?0:updator.match(regex).length)*2+(updator.match(regex2)==null?0:updator.match(regex2).length)>32){
		h3c.warning("修改人名字过长！");
		return false;
	}
	return true;
}

//检查任务描述格式
function checkDescription(){
    var re=/^[\w$\u4E00-\u9FA5]*$/g
	var regex=/[\u4E00-\u9FA5]/g;
	var regex2=/[\w$]/g;
	var description=document.getElementById('description').value;
	if(!re.test(description)){
		h3c.warning("任务描述格式不正确！");
		return false;
	}
	if((description.match(regex)==null?0:description.match(regex).length)*2+(description.match(regex2)==null?0:description.match(regex2).length)>150){
		h3c.warning("任务描述过长！");
		return false;
	}
	return true;
}

//检查cron表达式格式
function checkCron(){
    //秒
var regex="/^(([0-5]{0,1}[0-9]{1})|([0-5]{0,1}[0-9]{1}([,-][0-5]{0,1}[0-9])*)|([0-5]{0,1}[0-9]{1}[/][0-5]{0,1}[0-9]{1})|[\*])([ ])";
    //分
    regex+="(([0-5]{0,1}[0-9]{1})|([0-5]{0,1}[0-9]{1}([,-][0-5]{0,1}[0-9])*)|([0-5]{0,1}[0-9]{1}[/][0-5]{0,1}[0-9]{1})|[\*])([ ])";
    //时
    regex+="(([0-1]{0,1}[0-9]{1})|(2[0-3])|((([0-1]{0,1}[0-9]{1})|(2[0-3]))([,-](([0-1]{0,1}[0-9]{1})|(2[0-3])))*)|((([0-1]{0,1}[0-9]{1})|(2[0-3]))[/](([0-1]{0,1}[0-9]{1})|(2[0-3])))|[\*])([ ])";
    //日
    regex+="(([*?L]{1})|(LW)|([1-9])[W]{0,1}|([1-2]{1}[0-9]{1}[W]{0,1})|(3[0-1][W]{0,1})|((([1-9][W]{0,1})|([1-2]{1}[0-9]{1}[W]{0,1})|(3[0-1][W]{0,1}))([,-](([1-9][W]{0,1})|([1-2]{1}[0-9]{1}[W]{0,1})|(3[0-1][W]{0,1})))*)|(([1-9][W]{0,1})|([1-2]{1}[0-9]{1}[W]{0,1})|(3[0-1][W]{0,1}))[/](([1-9][W]{0,1})|([1-2]{1}[0-9]{1}[W]{0,1})|(3[0-1][W]{0,1})))([ ])";
    //月
    regex+="(([*]{1})|([1-9])|(1[0-2])|(JAN|jan|FEB|feb|MAR|mar|APR|apr|MAY|may|JUN|jun|JUL|jul|AUG|aug|SEP|sep|OCT|oct|NOV|nov|DEC|dec)|((([1-9])|(1[0-2]))([,-](([1-9])|(1[0-2])))*)|((([1-9])|(1[0-2]))[/](([1-9])|(1[0-2])))|(((JAN|jan|FEB|feb|MAR|mar|APR|apr|MAY|may|JUN|jun|JUL|jul|AUG|aug|SEP|sep|OCT|oct|NOV|nov|DEC|dec))"+
    		"([,-]((JAN|jan|FEB|feb|MAR|mar|APR|apr|MAY|may|JUN|jun|JUL|jul|AUG|aug|SEP|sep|OCT|oct|NOV|nov|DEC|dec)))*)|(((JAN|jan|FEB|feb|MAR|mar|APR|apr|MAY|may|JUN|jun|JUL|jul|AUG|aug|SEP|sep|OCT|oct|NOV|nov|DEC|dec))[/]((JAN|jan|FEB|feb|MAR|mar|APR|apr|MAY|may|JUN|jun|JUL|jul|AUG|aug|SEP|sep|OCT|oct|NOV|nov|DEC|dec))))([ ])";
    //周(或年)
    regex+="(([*?L]{1})|([1-7]#[1-5])|([1-7])|([1-7]L)|(SUN|MON|TUE|WED|THU|FRI|SAT|sun|mon|tue|wed|thu|fri|sat)|(([1-7])|((SUN|MON|TUE|WED|THU|FRI|SAT|sun|mon|tue|wed|thu|fri|sat)))([,](([1-7])|((SUN|MON|TUE|WED|THU|FRI|SAT|sun|mon|tue|wed|thu|fri|sat)))*)|(([1-7])([-]([1-7]))*)|(((SUN|MON|TUE|WED|THU|FRI|SAT|sun|mon|tue|wed|thu|fri|sat))"+
    		"([-]((SUN|MON|TUE|WED|THU|FRI|SAT|sun|mon|tue|wed|thu|fri|sat)))*)|(([1-7])[/](1-7))|(((SUN|MON|TUE|WED|THU|FRI|SAT|sun|mon|tue|wed|thu|fri|sat))[/]((SUN|MON|TUE|WED|THU|FRI|SAT|sun|mon|tue|wed|thu|fri|sat))))([ ](([*])|20[0-9][0-9]([-]20[0-9][0-9])*))?$/g";
var regex3=eval(regex);
var regex4=/[?]/g;
var cron=document.getElementById('cron').value;
if (!regex3.test(cron)||cron.indexOf("?")==-1||cron.match(regex4).length==2) {
	h3c.warning("cron表达式格式不正确！");
	return false;
} 
return true;
}
</script>
</html>