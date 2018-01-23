<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="h3c" uri="/WEB-INF/h3c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3c:head />
<style type="text/css">
html, body {
	height: 100%;
	font-family: Microsoft YaHei;
}
</style>
<div style="height: 100%; width: 100%; border: 0px solid red; background: #fff; overflow:auto">
<table
	style="height: 100%; width: 100%; border: 0px solid red; background: #fff;">
	<tr>
	<td width="50px;"></td>
	<td style="font-size:17px;font-family:Microsoft YaHei;" width="20%"><b>HTML标签</b></td>
	<td style="font-size:17px;font-family:Microsoft YaHei;" width="25%;"><b>标签描述</b></td>
	<td width="80px;"></td>
	<td style="font-size:17px;font-family:Microsoft YaHei;" width="20%"><b>HTML标签</b></td>
	<td style="font-size:17px;font-family:Microsoft YaHei;" width="25%"><b>标签描述</b></td>
	<td width="50px;"></td>
	</tr>
	
	<tr height="10px;"></tr>
	<tr>
    <td width="50px;"></td>
    <td align="left"> 
    SimplePanelTag
    </td>
    <td>
      		<pre>页面上的自定义模块标签;<br>
使用举例:<br>
h3c:simplePanel property="panel">/h3c:simplePanel><br>
具体属性可见h3c.tld文件查看。<br>
<h3c:simplePanel property="panel"></h3c:simplePanel>
</pre>
	</td>
	    <td width="50px;"></td>
    <td align="left"> 
      HeadTag
    </td>
    <td>
		<pre>页面上的自定义头标签，自动引入框架需要的的css、js和其他文件;<br>
使用举例:<br>
head>
meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
title>Insert title here /title>
h3c:head/>
/head>;<br>
具体属性可见h3c.tld文件查看。<br>



</pre>
	</td>
    </tr>
    <tr>
    <td width="50px;"></td>
    <td align="left"> 
      ButtonTag
    </td>
    <td>
		<pre>页面上的自定义按钮标签;<br>
使用举例:<br>
function doSave(){alert('按钮单击事件触发！');}<br>
h3c:button text="保存" handler ="doSave" property="" /><br>
具体属性可见h3c.tld文件可查看。
<h3c:button property="button" text="按钮例子" handler="doSave()"/>

		</pre>
	</td>
	<td width="50px;"></td>
    <td align="left"> 
      	TabLayOutTag
    </td>
     <td>
		<pre>页面上的自定义table布局标签，通过colgroup实现td自动排列;<br>
使用举例:<br>
table border="0" id="myform" align="center" width="100%"  cellpadding="0" cellspacing="0">
     h3c:tabLayOut /> <br>
具体属性可见h3c.tld文件可查看。<br>	
	
		</pre>
	</td>
    </tr>
    
    
        <tr>
    <td width="80px;"></td>
    <td align="left"> 
     TextEditTag
    </td>
    <td>
		<pre>页面上的自定义文本标签;<br>
使用举例:<br>
h3c:textEdit property="sfz" label="身份证" readonly="true" >/h3c:textEdit>;<br>
具体属性可见h3c.tld文件查看。<br>
<div><table><tr><h3c:textEdit property="sfz" label="身份证" readonly="true" ></h3c:textEdit></tr></table></div>
		</pre>
	</td>
	<td width="50px;"></td>
    <td align="left"> 
      	DateEditTag 
    </td>
     <td>
		<pre>自定义时间框标签;<br>
使用举例:<br>
h3c:dateEdit property="date" format="yyyy-mm-dd"  label="时间框">/h3c:dateEdit>;<br>
具体属性可见h3c.tld文件查看。<br>
<div><table><tr>
<h3c:dateEdit property="date" format="Y-m-d"  label="时间框"></h3c:dateEdit>
</tr></table></div>
		</pre>
	</td>
    </tr>
        <tr>
    <td width="50px;"></td>
    <td align="left"> 
     	FormTag
    </td>
    <td>
		<pre>自定义表单标签，和html自带的一样，name可以不输入默认，enctype支持是否是上传文件;<br>
使用举例:<br>
h3c:form action="orgInfoAlterController.do?saveOrg">…/h3c:form>;<br>
具体属性可见h3c.tld文件查看。<br>



		</pre>
	</td>
	<td width="50px;"></td>
    <td align="left"> 
     	HiddenInputTag 
    </td>
     <td>
		<pre>自定义文本隐藏标签，类似 input type="hidden" name="" id="" value="">;<br>
使用举例:<br>
h3c:hidden property="abc" />;<br>
具体属性可见h3c.tld文件查看。<br>



		</pre>
	</td>
    </tr>
        <tr>
    <td width="50px;"></td>
    <td align="left"> 
      	NumberEditTag
    </td>
    <td>
		<pre>自定义数值标签;<br>
使用举例:<br>
h3c:numberEdit property="number" required="true" decimalPrecision="2" label="价格" minValue="30" maxValue="100" hint="范围在30到100以内的数">/h3c:numberEdit>;<br>
具体属性可见h3c.tld文件查看。<br>
<div><table><tr>
<h3c:numberEdit property="number" required="true" decimalPrecision="2" label="价格" minValue="30" maxValue="100" hint="范围在30到100以内的数"></h3c:numberEdit>
</tr></table></div>
</pre>
	</td>
	<td width="50px;"></td>
    <td align="left"> 
     TextAreaTag
    </td>
     <td>
		<pre>页面上的自定义文本区标签;<br>
使用举例:<br>
h3c:textarea property="textarea" label="单位名称" value=" " >
/h3c:textarea>;<br>
具体属性可见h3c.tld文件查看。<br>
<div><table><tr>
<h3c:textarea property="textarea" label="单位名称" value=" " >
</h3c:textarea>
</tr></table></div>
</pre>
	</td>
    </tr>
        <tr>
    <td width="50px;"></td>
    <td align="left"> 
      GroupBoxTag
    </td>
    <td>
		<pre>页面上的自定义分组标签;<br>
使用举例:<br>
h3c:groupbox title="基本信息" property="groupbox">
	    table align="center">  
		      tr>
           ……
       /tr>
	   /table>
 /h3c:groupBox>;<br>
具体属性可见h3c.tld文件查看。<br>

<h3c:groupbox property="groupbox" title="基本信息">
	    <table align="center">  
		      <tr>
		      <td>。。。</td>
       </tr>
	   </table>

</h3c:groupbox>
</pre>
	</td>
	<td width="50px;"></td>
    <td align="left"> 
     SelectTag
    </td>
     <td>
		<pre>页面上的数据字典下拉框标签;<br>
使用举例:<br>
h3c:select property="select"  data="['1', '身份证'],['2', '毕业证'],['3', '军官正'],['4', '其他证件']" label="证件类型" >/h3c:select>;<br>
具体属性可见h3c.tld文件查看。<br>






<div><table><tr>
<h3c:select property="select" data="['1', '身份证'],['2', '毕业证'],['3', '军官正'],['4', '其他证件']" label="证件类型"></h3c:select>
</tr></table></div>





</pre>
	</td>
    </tr>
        <tr>

	<td width="50px;"></td>
    <td align="left"> 
     	CheckBoxTag
    </td>
     <td>
		<pre>页面上的自定义确认框，能单独使用也可以在grid的中使用;<br>
使用举例:<br>
 h3c:checkbox property="workplace" label="工作地点1" /><br>

具体属性可见h3c.tld文件查看。<br>
<div><table><tr>
<h3c:checkbox property="workplace" label="工作地点1" />
</tr></table></div>
</pre>
	</td>
    </tr>
  
      

</table>
</div>
<script type="text/javascript">
 function doSave(){
	 alert('按钮单击事件触发！');
 }
 
</script>