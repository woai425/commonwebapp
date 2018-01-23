package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;



import com.h3c.portal.taglib.util.TagUtil;

public class TextAreaTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String label;// 左边的说明性文本
	private boolean required;// 是否必填项
	private String cols;// 默认占的列数
	private String rows;// 默认占的行数
	private String disabled;// 是否可见不可用
	private String readonly;// 是否只读
	private String value;
	private String height;
	private String validator;// 验证或者说校验函数
	private String invalidText;// 验证不通过时的提示信息
	private String selectOnFocus;// 是否在获得焦点时就选中，默认为false
	private String property;// text的id和name
	private String placeholder;
	private boolean labelAlign;// 判断lable是在框上面还是左边显示
	private String width; // 初始宽度
	private String resize;// 可否拖拽
	private String onchange;
	private String onclick;
	private String hint;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getSelectOnFocus() {
		return selectOnFocus;
	}

	public void setSelectOnFocus(String selectOnFocus) {
		this.selectOnFocus = selectOnFocus;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public int doStartTag() throws JspException {

		String disabledCss = "";
		String spanId = ((this.property == null) ? "" : this.property + "-tip");
		String readonlyCss = "";
		StringBuilder buffer = new StringBuilder(1200);
		if (this.disabled != null) {
			disabledCss = "disabled";
		}
		if (this.readonly != null) {
			readonlyCss = "readonly";
		}
		if (this.labelAlign != true) {
			buffer.append("<td nowrap align='right' style='font-family:Microsoft YaHei;'><span  style='font-size:13px;font-family:Microsoft YaHei;' id='"
					+ ((this.label == null) ? ("label") : "lable") + "' >");
			buffer.append(TagUtil.getLabelString(this.getLabel(),
					this.isRequired())
					+ ((this.label == null) ? ("") : this.label)
					+ "&nbsp;</span></td>");
			buffer.append("<td align='left'  nowrap colspan=\""
					+ (this.getCols() == null ? "1" : (Integer.parseInt(this
							.getCols()) - 1)) + "\"> ");
			buffer.append("<textarea class='input-xlarge' " + disabledCss + " "
					+ readonlyCss + "  ");
			if (this.required == true) {
				buffer.append(" required = \"" + this.required + "\" ");
			}
			buffer.append("style='font-family:Microsoft YaHei;font-size:13px;");
			if (this.readonly != null && !"".equals(this.readonly)) {
				buffer.append("  background:#fff; ");
				if (this.width != null && !"".equals(this.width)) {
					buffer.append(" width:" + this.width + "px; ");
				}
				if (this.height != null && !"".equals(this.height)) {
					buffer.append(" height:" + this.height + "px; ");
				}
			} else {
				if (this.width != null && !"".equals(this.width)) {
					buffer.append(" width:" + this.width + "px;");
				}
				if (this.height != null && !"".equals(this.height)) {
					buffer.append(" height:" + this.height + "px; ");
				}

			}
			if (this.resize != null && !"".equals(this.resize)) {
				buffer.append("resize:" + this.resize + ";");
			}
			buffer.append(" ' ");
			
			if (this.onchange != null && !"".equals(this.onchange)) {
				buffer.append(" onchange = \"" + this.onchange + "\"");
			}
			if (this.onclick != null && !"".equals(this.onclick)) {
				buffer.append(" onclick = \"" + this.onclick + "\"");
			}
			buffer.append("name = \"" + this.property + "\" ");
			buffer.append("id = \"" + this.property + "\" ");
			if (this.placeholder != null && !"".equals(this.placeholder)) {
				buffer.append(" placeholder = \"" + this.placeholder + "\" ");
			}
			if (this.rows != null && !"".equals(this.rows)) {
				buffer.append(" rows = \"" + this.rows + "\" ");
			}
			if (this.cols != null && !"".equals(this.cols)) {
				buffer.append(" cols = \"" + this.cols + "\" ");
			}

			buffer.append(">");
			if (this.value != null && !"".equals(this.value)) {
				buffer.append(this.value);
			}
			buffer.append("</textarea>");
			buffer.append("<span id=" + spanId + "></span></td>");

		} else {
			buffer.append("<td nowrap colspan=\""
					+ (this.getCols() == null ? "1" : (Integer.parseInt(this
							.getCols()))) + "\"> ");
			buffer.append("<table style='border-style:none'><tr><td>");
			buffer.append("<label style='font-family:Microsoft YaHei;text-align:left'> <span style='font-size:13px;font-family:Microsoft YaHei;' >");
			buffer.append(TagUtil.getLabelString(this.getLabel(),
					this.isRequired())
					+ ((this.label == null) ? ("") : this.label)
					+ "&nbsp;</span></label> ");
			buffer.append("</td></tr><tr><td>");
			buffer.append("<textarea class='input-xlarge' " + disabledCss + " "
					+ readonlyCss + "  ");
			if (this.required == true) {
				buffer.append(" required = \"" + this.required + "\"");
			}
			buffer.append("style='font-family:Microsoft YaHei;font-size:13px;");
			if (this.readonly != null && !"".equals(this.readonly)) {
				buffer.append("  background:#fff; ");
				if (this.width != null && !"".equals(this.width)) {
					buffer.append(" width:" + this.width + "px; ");
				}
				if (this.height != null && !"".equals(this.height)) {
					buffer.append(" height:" + this.height + "px; ");
				}
			} else {
				if (this.width != null && !"".equals(this.width)) {
					buffer.append(" width:" + this.width + "px;");
				}
				if (this.height != null && !"".equals(this.height)) {
					buffer.append(" height:" + this.height + "px; ");
				}
			}
			if (this.resize != null && !"".equals(this.resize)) {
				buffer.append("resize:" + this.resize + ";");
			}
			buffer.append(" ' ");
			buffer.append("name = \"" + this.property + "\"");
			buffer.append("id = \"" + this.property + "\"");
			if (this.placeholder != null && !"".equals(this.placeholder)) {
				buffer.append(" placeholder = \"" + this.placeholder + "\" ");
			}

			if (this.onchange != null && !"".equals(this.onchange)) {
				buffer.append(" onchange = \"" + this.onchange + "\"");
			}
			if (this.onclick != null && !"".equals(this.onclick)) {
				buffer.append(" onclick = \"" + this.onclick + "\"");
			}
		
			if (this.rows != null && !"".equals(this.rows)) {
				buffer.append(" rows = \"" + this.rows + "\" ");
			}
			if (this.cols != null && !"".equals(this.cols)) {
				buffer.append(" cols = \"" + this.cols + "\" ");
			}

			buffer.append(">");
			if (this.value != null && !"".equals(this.value)) {
				buffer.append(this.value);
			}
			buffer.append("</textarea>");
			buffer.append("<span id=" + spanId
					+ "></span></td></tr></table></td>");
		}
		 try {      
	            JspWriter out = pageContext.getOut();      
	            out.println(buffer.toString());      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } 
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		StringBuilder buffer = new StringBuilder(1200);
		if (this.readonly == null || this.disabled == null) {
			boolean required = this.isRequired();
			buffer.append("<script type=\"text/javascript\">");
			buffer.append("$(function(){");
			buffer.append("$('#" + this.property + "').blur(function(){");
			buffer.append("var content = $('#" + this.property + "').val();");
			buffer.append("if($.trim(content) == \"\"&&" + required + "){");
			buffer.append("$('#" + this.property + "').attr('placeholder','"
					+ ((this.hint == null) ? ("必填项...") : this.hint) + "');");
			buffer.append("$('#"
					+ this.property
					+ "').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid red;width:"
					+ ((this.width == null) ? "" : this.width) + "px;resize:"
					+ this.resize + ";');");
			buffer.append("}");
			buffer.append("else{$('#"
					+ this.property
					+ "').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:"
					+ ((this.width == null) ? "" : this.width) + "px;resize:"
					+ this.resize + ";');}");
			buffer.append("})");
			buffer.append("});");
			buffer.append("$(function(){$('#"
					+ this.property
					+ "').focus(function(){$('#"
					+ this.property
					+ "').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:"
					+ ((this.width == null) ? "" : this.width) + "px;resize:"
					+ this.resize + ";');})});");
			buffer.append("</script>");
		}
		 try {      
	            JspWriter out = pageContext.getOut();      
	            out.println(buffer.toString());      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } 

		return EVAL_PAGE;
	}

	public boolean isLabelAlign() {
		return labelAlign;
	}

	public void setLabelAlign(boolean labelAlign) {
		this.labelAlign = labelAlign;
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

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getResize() {
		return resize;
	}

	public void setResize(String resize) {
		this.resize = resize;
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

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
