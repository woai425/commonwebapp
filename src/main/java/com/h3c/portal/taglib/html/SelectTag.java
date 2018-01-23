package com.h3c.portal.taglib.html;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;


import com.h3c.framework.common.CodeManager;
import com.h3c.framework.common.dto.KeyAndValueDTO;
import com.h3c.portal.taglib.util.TagUtil;

public class SelectTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String property;// 标签的ID和name
	private String label;// 下拉框左边的说明性文本
	private String data;// 下拉框的下拉数据
	private String codeType;//代码类型
	private boolean required;// 是否必填项
	private String colspan;// 标签所占的总列数
	private String width;// 下拉框的宽度，只在size为空且colspan也为空时起作用
	private String value;// 下拉框默认选中项
	private boolean labelAlign;// 判断lable是在框上面还是左边显示
	private boolean disabled;// 是否可见不可用
	private String onchange;
	private String onclick;

	public int doStartTag() throws JspException {
		String disabledCss = "";
		List<KeyAndValueDTO<String,String>> list = new ArrayList<KeyAndValueDTO<String,String>>();
		String str = null;
		if(!StringUtils.isEmpty(this.data)){
			str = this.data;
			String[] str1 = str.replace("],[", "]@@[").split("@@");
			String[] str2 = null; 
			for (int i = 0; i < str1.length; i++) {
				str2 = str1[i].replaceAll("(\\[)|(\\])|(\\'*)", "").split(",");
				list.add(new KeyAndValueDTO<String,String>(str2[0], str2[1]));
			}
		}else if(!StringUtils.isEmpty(this.codeType)){
			Iterator<Entry<String,String>> it = CodeManager.getIteratorFromDd(this.codeType);
			while (it.hasNext()) {
				Entry<String,String> entry = it.next();
				if (entry != null) {
					if(str==null){
						str = "data";
					}
					list.add(new KeyAndValueDTO<String,String>(entry.getKey(),entry.getValue()));
				}
			}
		}else{
			throw new JspException("下拉框必须设置数据来源！");
		}
		if(str.isEmpty()){
			throw new JspException("下拉框必须设置数据来源！");
		}

		StringBuilder buffer = new StringBuilder(1200);
		if (this.getLabel() == null && this.colspan == null) {
			this.colspan = "2";
		}
		if (this.disabled) {
			disabledCss = "disabled";
		}

		if (this.labelAlign != true) {
			if(this.label == null){
				buffer.append("<td nowrap align='left' style='padding-top:8px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;' colspan=\""
						+ (this.getColspan() == null ? "1" : (Integer.parseInt(this
								.getColspan()) - 1)) + "\"> ");
				buffer.append("<select "+ disabledCss + " ");
			}else{
			buffer.append("<td nowrap align='right' style='font-family:Microsoft YaHei;'><span style='font-size:14px;font-family:Microsoft YaHei;' id='"
					+ ((this.label == null) ? ("label") : "lable") + "' >");
			buffer.append(TagUtil.getLabelString(this.getLabel(),
					this.isRequired())
					+ ((this.label == null) ? ("") : this.label)
					+ "&nbsp;</span></td>");
			buffer.append("<td nowrap align='left' style='padding-top:8px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;' colspan=\""
					+ (this.getColspan() == null ? "1" : (Integer.parseInt(this
							.getColspan()) - 1)) + "\"> ");
			buffer.append("<select "+ disabledCss + " ");
			}
			if(this.required==true){
				buffer.append("required= \"" + this.required + "\" ");
			}
            buffer.append(" style='font-size:14px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff; ");
			if (this.width != null && !"".equals(this.width)) {
				buffer.append("width:" + this.width + "px; ");
			}
			buffer.append(" ' ");
			buffer.append(" name = \"" + this.property + "\" ");
			buffer.append(" id = \"" + this.property + "\" ");
		    if(this.onchange!=null&&!"".equals(this.onchange)){
				buffer.append(" onchange = \""+this.onchange+"\"");
			}
		    if(this.onclick!=null&&!"".equals(this.onclick)){
				buffer.append(" onclick = \""+this.onclick+"\"");
			}
			if (this.value != null && !"".equals(this.value)) {
				buffer.append(" value = \"" + this.value + "\" ");
				buffer.append(">");
				buffer.append("<option value='' disabled selected style='display:none;'>请您选择...</option>");
				buffer.append("<option value='' >-----All-----</option>");
				for (KeyAndValueDTO<String,String> kv : list) {
					if (value.equals(kv.getKey())) {
						buffer.append("<option value='" + kv.getKey()+ "' selected='selected'>");
					} else {
						buffer.append("<option value='" + kv.getKey() + "'>");
					}
					buffer.append(kv.getValue() + "</option>");
				}
			} else {
				buffer.append(">");
				buffer.append("<option value='' disabled selected style='display:none;'>请您选择...</option>");
				buffer.append("<option value='' >-----All-----</option>");
				for (KeyAndValueDTO<String,String> kv : list) {
					buffer.append("<option value='" + kv.getKey() + "'>");
					buffer.append(kv.getValue() + "</option>");
				}
			}
			buffer.append("</select></td>");
		} else {
			buffer.append("<td nowrap align='left' style='font-family:Microsoft YaHei;'><span style='font-size:14px;font-family:Microsoft YaHei;' id='"
					+ ((this.label == null) ? ("label") : "lable") + "' >");
			buffer.append("<table style='border-style:none'><tr><td>");
			buffer.append("<label style='font-family:Microsoft YaHei;text-align:left'> <span style='font-size:14px;font-family:Microsoft YaHei;' >");
			buffer.append(TagUtil.getLabelString(this.getLabel(),
					this.isRequired())
					+ ((this.label == null) ? ("") : this.label)
					+ "&nbsp;</span></label> ");
			buffer.append("</td></tr><tr><td>");
			buffer.append("<select " + disabledCss + "  style='font-size:14px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;");
			if (this.width != null && !"".equals(this.width)) {
				buffer.append(" width:" + this.width + "px; ");
			}
			buffer.append("'");
			if(this.required==true){
				  buffer.append(" required = \""+this.required+"\"");
			}
			buffer.append("name = \"" + this.property + "\" ");
			buffer.append("id = \"" + this.property + "\" ");
		    if(this.onchange!=null&&!"".equals(this.onchange)){
				buffer.append(" onchange = \""+this.onchange+"\"");
			}
		    if(this.onclick!=null&&!"".equals(this.onclick)){
				buffer.append(" onclick = \""+this.onclick+"\"");
			}
			if (!StringUtils.isEmpty(this.value)) {
				buffer.append("value = \"" + this.value + "\"");
				buffer.append(">");
				buffer.append("<option value='' disabled selected style='display:none;'>请您选择...</option>");
				buffer.append("<option value='' >-----All-----</option>");
				for (KeyAndValueDTO<String,String> kv : list) {
					if (value.equals(kv.getKey())) {
						buffer.append("<option value='" + kv.getKey()+ "' selected='selected'>");
					} else {
						buffer.append("<option value='" + kv.getKey() + "'>");
					}
					buffer.append(kv.getValue() + "</option>");
				}
			} else {
				buffer.append(">");
				buffer.append("<option value='' disabled selected style='display:none;'>请您选择...</option>");
				buffer.append("<option value='' >-----All-----</option>");
				for (KeyAndValueDTO<String,String> kv : list) {
					buffer.append("<option value='" + kv.getKey() + "'>");
					buffer.append(kv.getValue() + "</option>");
				}
			}
			buffer.append("</select></td></tr></table></td>");
		}
		boolean required = this.isRequired();
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("$(function(){");
		buffer.append("$('#"+this.property+"').blur(function(){");
		buffer.append("var content = $('#"+this.property+"').val();");
		buffer.append("if($.trim(content)== '-----All-----'){");
		buffer.append("content='';");
		buffer.append("}");
		buffer.append("if($.trim(content) == \"\"&&"+required+"){");
		buffer.append("$('#"+this.property+"').attr('style','font-size:14px;font-family:Microsoft YaHei;border: 1px solid red;width:"+((this.width == null)?"":this.width)+"px;');");
		buffer.append("}");
		buffer.append("else{$('#"+this.property+"').attr('style','font-size:14px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:"+((this.width == null)?"":this.width)+"px;');}");
		buffer.append("})");
		buffer.append("});");
		buffer.append("$(function(){$('#"+this.property+"').focus(function(){$('#"+this.property+"').attr('style','font-size:14px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:"+((this.width == null)?"":this.width)+"px;');})});");
		buffer.append("</script>");
		 try {      
	            JspWriter out = pageContext.getOut();      
	            out.println(buffer.toString());      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } 

		return BodyTagSupport.SKIP_BODY;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
	public boolean isLabelAlign() {
		return labelAlign;
	}

	public void setLabelAlign(boolean labelAlign) {
		this.labelAlign = labelAlign;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isRequired() {
		return required;
	}

	public boolean isDisabled() {
		return disabled;
	}
	
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
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
}
