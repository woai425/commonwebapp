package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.h3c.portal.taglib.util.TagUtil;

public class DateEditTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String label;// 左边的说明性文本
	private String colspan;// 标签所占的总列数
	private String width;// 宽度，只在size为空且colspan也为空时起
	private boolean required;// 是否必填项
	private String value;// 下拉框的默认值，也是什么都没选时的出现的提示性的值
	private String size;// 和text的size同
	private String property;// text的id和name
	private String format;// 日期格式
	private boolean labelAlign;// 判断说明性文字是否在输入框的左边
	private String time;// 判断输入的时间最小单位
	private String disabled;// 是否可见不可用

	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder(1200);
		String disabledCss = "";
		if (this.disabled != null) {
			disabledCss = "disabled";
		}
		if (this.getLabel() == null && this.colspan == null) {
			this.colspan = "2";
		}
		if (this.labelAlign != true) {
			sb.append("<td nowrap align='right' style='font-family:Microsoft YaHei;padding-top:6px;'><span style='font-size:13px;font-family:Microsoft YaHei;'  >");
			sb.append(TagUtil.getLabelString(this.getLabel(), this.isRequired())
					+ ((this.label == null) ? ("") : this.label)
					+ "&nbsp;</span></td> ");
			sb.append("<td  align='left' style='padding-top:6px;' nowrap colspan=\""
					+ (this.getColspan() == null ? "1" : (Integer.parseInt(this
							.getColspan()) - 1)) + "\"> ");
			sb.append("<div class='input-prepend input-group'>");
			sb.append("<span class='add-on input-group-addon'>");
			sb.append("<i class='icon-calendar'></i></span>");
			sb.append("<input  type='text' " + disabledCss + "  id=\""
					+ this.property + "\" name=\"" + this.property
					+ "\" readonly='true' ");
			if (this.required == true) {
				sb.append(" required = \"" + this.required + "\"");
			}
			if (this.value != null && !"".equals(this.value)) {
				sb.append(" value = \"" + this.value + "\" ");
			}
			if (this.width != null && !"".equals(this.width)) {
				sb.append(" style='width:" + this.width
						+ "px; background:#fff' ");
			} else {
				sb.append(" style='width:180px; background:#fff' ");
			}
			sb.append("/>");
			sb.append("</div></td>");
		} else {
			sb.append("<td nowrap colspan=\""
					+ (this.getColspan() == null ? "1" : (Integer.parseInt(this
							.getColspan()))) + "\" > ");
			sb.append("<table style='border-style:none'><tr><td>");
			sb.append("<label style='font-family:Microsoft YaHei;text-align:left'> <span style='font-size:13px;font-family:Microsoft YaHei;' >");
			sb.append(TagUtil.getLabelString(this.getLabel(), this.isRequired())
					+ ((this.label == null) ? ("") : this.label)
					+ "&nbsp;</span></label> ");
			sb.append("</td></tr><tr><td>");
			sb.append("<div class='input-prepend input-group'>");
			sb.append("<span class='add-on input-group-addon'>");
			sb.append("<i class='icon-calendar'></i></span>");
			sb.append("<input  type='text' " + disabledCss + " id=\""
					+ this.property + "\" name=\"" + this.property
					+ "\" readonly='true' ");
			if (this.required == true) {
				sb.append(" required = \"" + this.required + "\"");
			}
			if (this.value != null && !"".equals(this.value)) {
				sb.append(" value = \"" + this.value + "\" ");
			}
			if (this.width != null && !"".equals(this.width)) {
				sb.append(" style='width:" + this.width
						+ "px; background:#fff' ");
			} else {
				sb.append(" style='width:180px; background:#fff' ");
			}
			sb.append("/>");
			sb.append("</div></td></tr></table></td>");
		}
		boolean required = this.isRequired();
		sb.append("<script type=\"text/javascript\">");
		sb.append("  $(function () {");
		sb.append("$('#" + this.property + "').datetimepicker({");
		if (time == null) {
			sb.append("　minView: 'month',");
		} else {
			sb.append("　minView: '" + this.time + "',");
		}
		if (format == null) {
			sb.append("format: ' yyyy-mm-dd' ,");
		} else {
			sb.append("format: ' " + this.format + " ' ,");
		}
		sb.append("language: 'zh-CN',");
		sb.append("autoclose:true ");
		sb.append(" });");
		sb.append(" });");
		sb.append("$(function(){");
		sb.append("$('#" + this.property + "').blur(function(){");
		sb.append("var content = $('#" + this.property + "').val();");
		sb.append("if($.trim(content) == \"\"&&" + required + "){");
		sb.append("$('#"
				+ this.property
				+ "').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid red;width:"
				+ ((this.width == null) ? "" : this.width) + "px;');");
		sb.append("}");
		sb.append("else{$('#"
				+ this.property
				+ "').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:"
				+ ((this.width == null) ? "" : this.width) + "px;');}");
		sb.append("})");
		sb.append("});");
		sb.append("$(function(){$('#"
				+ this.property
				+ "').focus(function(){$('#"
				+ this.property
				+ "').attr('style','font-size:13px;font-family:Microsoft YaHei;border: 1px solid #cccccc; background-color: #ffffff;width:"
				+ ((this.width == null) ? "" : this.width) + "px;');})});");

		sb.append("</script>");

		try {
			JspWriter out = pageContext.getOut();

			out.println(sb.toString());
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return BodyTagSupport.SKIP_BODY;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getColspan() {
		return colspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
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

	public boolean isLabelAlign() {
		return labelAlign;
	}

	public void setLabelAlign(boolean labelAlign) {
		this.labelAlign = labelAlign;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

}
