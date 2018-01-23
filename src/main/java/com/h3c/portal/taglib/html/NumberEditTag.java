package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.h3c.portal.taglib.util.TagUtil;

public class NumberEditTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String label;// 左边的说明性文本
	private String minValue;// 最小值
	private String maxValue;// 最大值
	private String decimalPrecision;// 精度，小数点后几位
	private boolean required;// 是否必填项
	private String colspan;// 标签所占的总列数
	private String width;// 宽度，只在size为空且colspan也为空时起作用
	private String validator; // 校验的方法
	private String invalidText;// 不通过时的提示信息
	private String inputType; // 可以为text, password，和html的type有着相同功能
	private String selectOnFocus;// 是否在获得焦点时就选中，默认为false
	private String disabled;// 是否可见不可用
	private String readonly;// 是否只读
	private String value;// 默认值
	private String size;// 和text的size同
	private String property;// text的id和name
	private boolean labelAlign;// 判断lable是在框上面还是左边显示
	private String labelId;// text的id
	private String placeholder;
	private String onclick;
	private String onchange;
	private String hint;

	public int doStartTag() throws JspException {
		String disabledCss = "";
		String readonlyCss = "";
		String id = ((this.property == null) ? "" : this.property);
		String name = id;
		StringBuilder buffer = new StringBuilder(1200);
		if (this.getLabel() == null && this.colspan == null) {
			this.colspan = "2";
		}
		if (this.disabled != null) {
			disabledCss = "disabled";
		}
		if (this.readonly != null) {
			readonlyCss = "readonly";
		}

		if (this.labelAlign != true) {
			buffer.append("<td nowrap align='right' style='font-family:Microsoft YaHei;'><span style='font-size:13px;font-family:Microsoft YaHei;' id='"
					+ ((this.labelId == null) ? "label" : this.labelId) + "' >");
			buffer.append(TagUtil.getLabelString(this.getLabel(), this.isRequired())
					+ ((this.label == null) ? ("") : this.label) + "&nbsp;</span></td> ");
			buffer.append("<td nowrap align='left' style='padding-top:10px;' colspan=\""
					+ (this.getColspan() == null ? "1" : (Integer.parseInt(this.getColspan()) - 1)) + "\"> ");
			buffer.append("<input  type='text' " + disabledCss + " " + readonlyCss + " name='" + name + "' id='" + id
					+ "' placeholder='" + ((this.hint == null) ? ("请输入数字...") : this.hint)
					+ "'  onkeydown = 'return checkNum" + this.property + "(event)' onpaste='return false' ");
			if (this.required == true) {
				buffer.append(" required = \"" + this.required + "\"");
			}
			buffer.append("style='font-family:Microsoft YaHei;font-size:13px;");
			if (this.readonly != null && !"".equals(this.readonly)) {
				buffer.append("  background:#fff; ");
				if (this.width != null && !"".equals(this.width)) {
					buffer.append(" width:" + this.width + "px; ");
				}
			} else {
				if (this.width != null && !"".equals(this.width)) {
					buffer.append(" width:" + this.width + "px;");
				}

			}
			buffer.append(" ' ");
			if (this.placeholder != null && !"".equals(this.placeholder)) {
				buffer.append(" placeholder = \"" + this.value + "\"");
			}
			if (this.onchange != null && !"".equals(this.onchange)) {
				buffer.append(" onchange = \"" + this.onchange + "\"");
			}
			if (this.onclick != null && !"".equals(this.onclick)) {
				buffer.append(" onclick = \"" + this.onclick + "\"");
			}

			if (this.value != null && !"".equals(this.value)) {
				buffer.append(" value = \"" + this.value + "\"");
			}
			buffer.append("/><span id=\"" + this.property + "-tip\"></span></td>");
		} else {
			buffer.append("<td nowrap colspan=\""
					+ (this.getColspan() == null ? "1" : (Integer.parseInt(this.getColspan()))) + "\"> ");
			buffer.append("<table style='border-style:none'><tr><td>");
			buffer.append("<label style='font-family:Microsoft YaHei;text-align:left' '> <span style='font-size:13px;font-family:Microsoft YaHei;' >");
			buffer.append(TagUtil.getLabelString(this.getLabel(), this.isRequired())
					+ ((this.label == null) ? ("") : this.label) + "&nbsp;</span></label> ");
			buffer.append("</td></tr><tr><td>");
			buffer.append("<input  type='text' " + disabledCss + " " + readonlyCss + "  name='" + name + "' id='" + id
					+ "'  placeholder='" + ((this.hint == null) ? ("请输入数字...") : this.hint)
					+ "' onkeydown = 'return checkNum" + this.property + "(event)' onpaste='return false' ");
			if (this.required == true) {
				buffer.append(" required = \"" + this.required + "\"");
			}
			buffer.append("style='font-family:Microsoft YaHei;font-size:13px;");
			if (this.readonly != null && !"".equals(this.readonly)) {
				buffer.append("  background:#fff; ");
				if (this.width != null && !"".equals(this.width)) {
					buffer.append(" width:" + this.width + "px; ");
				}
			} else {
				if (this.width != null && !"".equals(this.width)) {
					buffer.append(" width:" + this.width + "px;");
				}

			}
			buffer.append(" ' ");
			if (this.placeholder != null && !"".equals(this.placeholder)) {
				buffer.append(" placeholder = \"" + this.placeholder + "\"");
			}
			if (this.onchange != null && !"".equals(this.onchange)) {
				buffer.append(" onchange = \"" + this.onchange + "\"");
			}
			if (this.onclick != null && !"".equals(this.onclick)) {
				buffer.append(" onclick = \"" + this.onclick + "\"");
			}
			if (this.value != null && !"".equals(this.value)) {
				buffer.append(" value = \"" + this.value + "\"");
			}
			buffer.append("/><span id=\"" + this.property + "-tip\"></span></td></tr></table></td>");

		}

		if (this.readonly == null || this.disabled == null) {
			buffer.append("<script type=\"text/javascript\">");
			buffer.append("function checkNum" + this.property + "(event){ ");
			buffer.append("var keynum =event.which;");
			buffer.append(" if((keynum>47&&keynum<58)||(keynum>95&&keynum<106)||(keynum==110)||(keynum==8)){ ");
			buffer.append("return true; }");
			buffer.append("else{");
			buffer.append("return false; }}");
			buffer.append("$(function(){");
			buffer.append("$('#" + this.property + "').blur(function(){");
			buffer.append("var content = $('#" + this.property + "').val();");
			if (this.minValue != null && this.maxValue != null) {

				buffer.append("if($.trim(content)< " + this.minValue + "||" + "$.trim(content)>" + this.maxValue + "){");
				buffer.append("$('#" + this.property + "').attr('placeholder','"
						+ ((this.hint == null) ? ("您输入的数值不在该范围内...") : this.hint) + "');");
				buffer.append("$('#" + this.property
						+ "').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid red;width:"
						+ ((this.width == null) ? "" : this.width) + "px;');");
				buffer.append("}");
			}
			if (this.decimalPrecision != null) {
				buffer.append("if(content.indexOf('.')!=-1){");
				buffer.append("var con = content.substring(0,content.indexOf('.')+" + this.decimalPrecision + "+1);");
				buffer.append("$('#" + this.property + "').val(con);");
				buffer.append("}");
				buffer.append("});");

				buffer.append("$('#" + this.property + "').focus(function(){");
				buffer.append("$('#"
						+ this.property
						+ "').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:"
						+ ((this.width == null) ? "" : this.width) + "px;');");
				buffer.append("})");
			} else {

				buffer.append("});");
				buffer.append("$('#" + this.property + "').focus(function(){");
				buffer.append("$('#"
						+ this.property
						+ "').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:"
						+ ((this.width == null) ? "" : this.width) + "px;');");
				buffer.append("})");
			}
			buffer.append("});");
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getDecimalPrecision() {
		return decimalPrecision;
	}

	public void setDecimalPrecision(String decimalPrecision) {
		this.decimalPrecision = decimalPrecision;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public boolean isLabelAlign() {
		return labelAlign;
	}

	public void setLabelAlign(boolean labelAlign) {
		this.labelAlign = labelAlign;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

}