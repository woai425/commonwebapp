<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html>
<html>
<head>
<h3c:head />
</head>
<body>
	<h3c:form action="cronJobController.do?saveCronJob" name="CronJob">
		<table
			style="height: 100%; width: 100%; border: 0px solid red; background: #fff;">
			<tr style="height: 5px;"></tr>
			<tr>
				<td style="width: 35px;"></td>
				<h3c:textEdit label="任务名称" property="jobname" labelAlign="true" 
					required="true" />
				<h3c:textEdit label="任务分组" property="jobgroup" labelAlign="true"
					required="true" />
			</tr>
			<tr>
				<td style="width: 35px;"></td>
				<h3c:textEdit label="任务描述" property="description" labelAlign="true" 
					required="true" />
				<h3c:textEdit label="cron表达式" property="cron" labelAlign="true" placeholder="0 * * ? * * （*）"
					required="true" />
			</tr>
			<tr>
				<td style="width: 35px;"></td>
				<h3c:textEdit label="任务类名" property="classname" labelAlign="true" required="true"/>
				<h3c:textEdit property="creator" label="创建人" required="true"
					labelAlign="true" ></h3c:textEdit>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td>
					<div style="width: 220px;" align="right">
						<h3c:button property="dosave" text="保存" fontAwesome="icon-save"
							handler="saveCronJob()" />
					</div>
				</td>
			</tr>
			<tr style="height: 5px;"></tr>
		</table>
	</h3c:form>
</body>
<script type="text/javascript">
	//新建定时任务
	function saveCronJob() {
		//检查各个编辑框是否满足相应条件
		if(checkJobName()&&checkJobGroup()&&checkDescription()&&checkCreator()&&checkCron()){
			//CronJob为form的name
		h3c.submit("CronJob", function() {
			//调用父页面查询刷新结果,openCronJob是其他页面调用CronJob模块的ID，当我们的模块被其他页面用弹出框弹出时才会使用到这个ID,一般不会使用
			h3c.getRootPageObject("openCronJob").searchCronJob();	
			 //同时关闭子页面，addCronJob为h3c.showWindowWithSrc()方法中的子页面名称
			  h3c.closeWindow("addCronJob");
			});
		}		
	}
	
	//检查创建人格式
	function checkCreator(){
	    var re=/^[\w$\u4E00-\u9FA5]*$/g
		var regex=/[\u4E00-\u9FA5]/g;
		var regex2=/[\w$]/g;
		var creator=document.getElementById('creator').value;
		if(!re.test(creator)){
			h3c.warning("创建人名字格式不正确！");
			return false;
		}
		//汉字在mysql中占两个字节，其它字符占一个字节
		if((creator.match(regex)==null?0:creator.match(regex).length)*2+(creator.match(regex2)==null?0:creator.match(regex2).length)>32){
			h3c.warning("创建人名字过长！");
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
	//检查任务分组格式
	function checkJobGroup(){
		    var re=/^[\w$\u4E00-\u9FA5]*$/g
			var regex=/[\u4E00-\u9FA5]/g;
			var regex2=/[\w$]/g;
			var jobgroup=document.getElementById('jobgroup').value;
			if(!re.test(jobgroup)){
				h3c.warning("任务组名格式不正确！");
				return false;
			}
			if((jobgroup.match(regex)==null?0:jobgroup.match(regex).length)*2+(jobgroup.match(regex2)==null?0:jobgroup.match(regex2).length)>100){
				h3c.warning("任务组名过长！");
				return false;
			}
			return true;
	}
	
	//检查任务名称
	function checkJobName(){
		var re=/^[\w$\u4E00-\u9FA5]*$/g
		var regex=/[\u4E00-\u9FA5]/g;
		var regex2=/[\w$]/g;
		var jobname=document.getElementById('jobname').value;
		var result=true;
		if(!re.test(jobname)){
			h3c.warning("任务名称格式不正确！");
			return false;
		}
		if((jobname.match(regex)==null?0:jobname.match(regex).length)*2+(jobname.match(regex2)==null?0:jobname.match(regex2).length)>100){
			h3c.warning("任务名称过长！");
			return false;
		}
		//ajax请求，从后台数据库检查任务名称是否重复
		var url='cronJobController.do?checkJobName';		
		$.ajax({
			type : "POST",
			url : url,
			data : {"jobName":jobname},
			dataType : "json",
			success : function(rs) {
				         if(!rs.success){
				           result=false;
					       h3c.warning(rs.msg);
						   		
				         }
			          },
		    async:false //使用同步的目的是为了result能够被赋值
		});		
		return result;
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