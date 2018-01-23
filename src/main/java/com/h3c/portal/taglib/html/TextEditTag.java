package com.h3c.portal.taglib.html;


import java.io.IOException;

import com.h3c.portal.taglib.util.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * 
 * TextEditTag
 * 只有在具有相同[属性(非数据)]集合时，标签处理器才将实例重用
 * 
 * @author 周兆巍
 * @version 创建时间：2014年10月25日 下午10:00:01
 */
public class TextEditTag extends  BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String labelId;//text的id
	private String label;//左边的说明性文本
	private boolean required;//是否必填项
	private String colspan;//标签所占的总列数
	private String width;//宽度，只在size为空且colspan也为空时起作用
	private String validator; //校验的方法
	private String invalidText;//不通过时的提示信息
	private String inputType; //可以为text, password，和html的type有着相同功能
	private String selectOnFocus;//是否在获得焦点时就选中，默认为false
	private String disabled;//是否可见不可用
	private String placeholder;//默认值
	private String readonly;//是否只读
	private String value;//值
	private String property;//text的id和name
	private boolean labelAlign;//判断lable是在框上面还是左边显示
	private String onchange;//域内容改变的时候发生的事件
	private String onclick;//域触发的时候发生的事件
	private String hint;


	public int doStartTag() throws JspException {
		String type = (this.inputType==null?"text":this.inputType);
		String spanId=((this.property == null)?"":this.property+"-tip");
		String disabledCss = "";
		String readonlyCss= "";
		String id = ((this.property == null)?"":this.property);
		String name = id;
		StringBuilder buffer = new StringBuilder(1200);
		if(this.getLabel()==null&&this.colspan==null){
			this.colspan = "2";
		}
			if(this.disabled!=null){
				disabledCss = "disabled";
			}
			if(this.readonly!=null){
				readonlyCss = "readonly";
			}
			if(this.labelAlign!=true){
				if(this.label!=null){
				buffer.append("<td nowrap align='right' style='font-family:Microsoft YaHei;'><span style='font-size:13px;font-family:Microsoft YaHei;' id='"+((this.labelId==null)?"label":this.labelId)+"' >");
				buffer.append(TagUtil.getLabelString(this.getLabel(),this.isRequired())+((this.label==null)?(""):this.label)+"&nbsp;</span></td> ");
				}
				buffer.append("<td nowrap align='left' style='padding-top:10px;font-family:Microsoft YaHei;font-size:13px;' colspan=\""+(this.getColspan()==null?"1":(Integer.parseInt(this.getColspan())-1))+"\"> ");
			    buffer.append("<input  type='"+type+"' "+disabledCss+" "+readonlyCss+"  name='"+name +"' id='"+id+"' ");
			    if(this.required==true){
			    	buffer.append(" required = \""+this.required+"\"");
			    }
			    buffer.append("style='font-family:Microsoft YaHei;font-size:13px;");
			    if(this.readonly!=null&&!"".equals(this.readonly)){
			    	buffer.append("  background:#fff; ");
				    if(this.width!=null&&!"".equals(this.width)){
				    	buffer.append(" width:"+this.width+"px; ");
				    } 
			    }else{
				    if(this.width!=null&&!"".equals(this.width)){
				        buffer.append(" width:"+this.width+"px;");
				    }
				    
			    }
			    buffer.append(" ' ");
			    if(this.placeholder!=null&&!"".equals(this.placeholder)){
					buffer.append(" placeholder = \""+this.placeholder+"\"");
				}
			    if(this.onchange!=null&&!"".equals(this.onchange)){
					buffer.append(" onchange = \""+this.onchange+"\"");
				}
			    if(this.onclick!=null&&!"".equals(this.onclick)){
					buffer.append(" onclick = \""+this.onclick+"\"");
				}
			    if(this.value!=null&&!"".equals(this.value)){
					buffer.append(" value = \""+this.value+"\"");
				}
			    buffer.append("/><span id="+spanId+"></span></td>");
			}else{
			    buffer.append("<td nowrap  colspan=\""+(this.getColspan()==null?"1":(Integer.parseInt(this.getColspan())))+"\"> ");
			    buffer.append("<table style='border-style:none'><tr><td>");
			    buffer.append("<label style='font-family:Microsoft YaHei;text-align:left'> <span style='font-size:13px;font-family:Microsoft YaHei;' id='"+((this.labelId==null)?"label":this.labelId)+"' >");
			    buffer.append(TagUtil.getLabelString(this.getLabel(),this.isRequired())+((this.label==null)?(""):this.label)+"&nbsp;</span></label> ");
			    buffer.append("</td></tr><tr><td >");
			    buffer.append("<input  type='"+type+"' "+disabledCss+" "+readonlyCss+" name='"+name +"' id='"+id+"' ");
			    if(this.required==true){
			    	buffer.append(" required = \""+this.required+"\"");
			    }
			    buffer.append("style='font-family:Microsoft YaHei;font-size:13px;");
			    if(this.readonly!=null&&!"".equals(this.readonly)){
			    	buffer.append("  background:#fff; ");
				    if(this.width!=null&&!"".equals(this.width)){
				    	buffer.append(" width:"+this.width+"px; ");
				    } 
			    }else{
				    if(this.width!=null&&!"".equals(this.width)){
				        buffer.append(" width:"+this.width+"px;");
				    }
				    
			    }
			    buffer.append(" ' ");
			    if(this.placeholder!=null&&!"".equals(this.placeholder)){
					buffer.append(" placeholder = \""+this.placeholder+"\"");
				}
			    if(this.onchange!=null&&!"".equals(this.onchange)){
					buffer.append(" onchange = \""+this.onchange+"\"");
				}
			    if(this.onclick!=null&&!"".equals(this.onclick)){
					buffer.append(" onclick = \""+this.onclick+"\"");
				}
			    if(this.value!=null&&!"".equals(this.value)){
					buffer.append(" value = \""+this.value+"\"");
				}
			    buffer.append("/><span id="+spanId+" ></span></td></tr></table></td>");
			
		}
		
		if(this.readonly==null||this.disabled==null){
	    boolean required = this.isRequired();
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("$(function(){");
		buffer.append("$('#"+this.property+"').blur(function(){");
		buffer.append("var content = $('#"+this.property+"').val();");
		buffer.append("if($.trim(content) == \"\"&&"+required+"){");
		buffer.append("$('#"+this.property+"').attr('placeholder','"+((this.hint==null)?("必填项..."):this.hint)+"');");
		buffer.append("$('#"+this.property+"').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid red; width:"+((this.width == null)?"":this.width)+"px;');");
		buffer.append("}");
		buffer.append("else{$('#"+this.property+"').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:"+((this.width == null)?"":this.width)+"px;');}");
		buffer.append("})");
		buffer.append("});");
		buffer.append("$(function(){$('#"+this.property+"').focus(function(){$('#"+this.property+"').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:"+((this.width == null)?"":this.width)+"px;');})});");
		buffer.append("</script>");
		}
		
		 try {      
	            JspWriter out = pageContext.getOut();      
	            out.println(buffer.toString());      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } 
        
		return BodyTagSupport.SKIP_BODY;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getColspan() {
		return colspan;
	}
	public void setColspan(String colspan) {
		this.colspan = colspan;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getValidator() {
		return validator;
	}
	public void setValidator(String validator) {
		this.validator = validator;
	}
	public String getInvalidText() {
		return invalidText;
	}
	public void setInvalidText(String invalidText) {
		this.invalidText = invalidText;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public String getSelectOnFocus() {
		return selectOnFocus;
	}
	public void setSelectOnFocus(String selectOnFocus) {
		this.selectOnFocus = selectOnFocus;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}

	public boolean isLabelAlign() {
		return labelAlign;
	}

	public void setLabelAlign(boolean labelAlign) {
		this.labelAlign = labelAlign;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	
	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
}