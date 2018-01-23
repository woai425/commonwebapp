package com.h3c.portal.taglib.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;



import com.h3c.portal.taglib.util.TagUtil;

/***********************************************************************
 *
 * Upload.java
 *
 * H3C所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * @copyright   Copyright: 2015-2020
 * @creator     lfw2082<br/>
 * @create-time 2016年5月5日 上午9:17:52
 * @revision    $Id:  *
 ***********************************************************************/

public class UploadTag extends BodyTagSupport{
	
	private static final long serialVersionUID = 1L;
	private String label;//左边的说明性文本
	private String colspan;//标签所占的总列数
	private String width;//宽度
	private String height;//高度
	private boolean required;//是否必填
	private String property;//text的id和name
	private boolean labelAlign;//判断说明性文字是否在输入框的左边
	public int doStartTag() throws JspException{
		StringBuilder buffer = new StringBuilder(1200);
		String upLoadName="";
		upLoadName=this.property+"Upload";
		String upLoadId="";
		upLoadId=this.property;
		if(this.getLabel()==null&&this.colspan==null){
			this.colspan = "2";
		}
		if(this.labelAlign!=true){
			buffer.append("<td nowrap align='right' style='font-family:Microsoft YaHei;'><span style='font-size:13px;font-family:Microsoft YaHei;' >");
			buffer.append(TagUtil.getLabelString(this.getLabel(),this.isRequired())+((this.label==null)?(""):this.label)+"&nbsp;</span></td> ");
			buffer.append("<td nowrap colspan=\""+(this.getColspan()==null?"1":(Integer.parseInt(this.getColspan())-1))+"\">");
			buffer.append("<div class='h3c-upload-box'>");
			buffer.append("<input type='text'   class='h3c-textbox' disabled='disabled' ");
			buffer.append("id=\""+this.property+"\" ");
			buffer.append("name=\""+this.property+"\" ");
			buffer.append("style=' ");
			if(this.width!=null&&!"".equals(this.width)){
				buffer.append(" width:"+this.width+"px; ");
			}else{
				buffer.append(" width:220px; ");
			}
			if(this.height!=null&&!"".equals(this.height)){
				buffer.append(" height:"+this.height+"px; ");
			}else{
				buffer.append(" height:20px; ");
			}
			buffer.append("  background:#fff; ' ");
			buffer.append("/>");
			buffer.append("<a href='javascript:void(0);' class='h3c-link'");
			buffer.append("style=' ");
			if(this.height!=null&&!"".equals(this.height)){
				buffer.append(" height:"+(Integer.parseInt(this.getHeight())-10)+"px; ");
			}else{
				buffer.append(" height:10px; ");
			};
			if(this.height!=null&&!"".equals(this.height)){
				buffer.append(" line-height:"+(Integer.parseInt(this.getHeight())-10)+"px; ");
			}else{
				buffer.append(" line-height:10px; ");
			};
			buffer.append(" '>浏览</a>  ");
			
			buffer.append("<input type='file' class='h3c-uploadFile' ");
			buffer.append("id=\""+upLoadName+"\" ");
			buffer.append("name=\""+upLoadName+"\" ");
			buffer.append("style=' ");
			if(this.width!=null&&!"".equals(this.width)){
				buffer.append(" width:"+(Integer.parseInt(this.getWidth())+63)+"px; ");
			}else{
				buffer.append(" width:280px; ");
			}
			if(this.height!=null&&!"".equals(this.height)){
				buffer.append(" height:"+this.height+"px; ");
			}else{
				buffer.append(" height:30px; ");
			}
			buffer.append("  background:#fff; ' ");
			buffer.append("onchange= getFile(this,\""+upLoadId+"\") />");
			buffer.append("</div></td>");
			
		}else{
			buffer.append("<td nowrap colspan=\""+(this.getColspan()==null?"1":(Integer.parseInt(this.getColspan())-1))+"\"><table><tr>");
			buffer.append("<td ><span style='font-size:13px;font-family:Microsoft YaHei;float:left;'  >");
			buffer.append(TagUtil.getLabelString(this.getLabel(),this.isRequired())+((this.label==null)?(""):this.label)+"&nbsp;</span></td> ");
			buffer.append("</tr><tr><td>");
			buffer.append("<div class='h3c-upload-box'>");
			buffer.append("<input type='text'   class='h3c-textbox' disabled='disabled' ");
			buffer.append("id=\""+this.property+"\" ");
			buffer.append("name=\""+this.property+"\" ");
			buffer.append("style=' ");
			if(this.width!=null&&!"".equals(this.width)){
				buffer.append(" width:"+this.width+"px; ");
			}else{
				buffer.append(" width:220px; ");
			}
			if(this.height!=null&&!"".equals(this.height)){
				buffer.append(" height:"+this.height+"px; ");
			}else{
				buffer.append(" height:20px; ");
			}
			buffer.append("  background:#fff; ' ");
			buffer.append("/>");
			buffer.append("<a href='javascript:void(0);' class='h3c-link'");
			buffer.append("style=' ");
			if(this.height!=null&&!"".equals(this.height)){
				buffer.append(" height:"+(Integer.parseInt(this.getHeight())-10)+"px; ");
			}else{
				buffer.append(" height:10px; ");
			};
			if(this.height!=null&&!"".equals(this.height)){
				buffer.append(" line-height:"+(Integer.parseInt(this.getHeight())-10)+"px; ");
			}else{
				buffer.append(" line-height:10px; ");
			};
			buffer.append(" '>浏览</a>  ");
			
			buffer.append("<input type='file' class='h3c-uploadFile' ");
			buffer.append("id=\""+upLoadName+"\" ");
			buffer.append("name=\""+upLoadName+"\" ");
			buffer.append("style=' ");
			if(this.width!=null&&!"".equals(this.width)){
				buffer.append(" width:"+(Integer.parseInt(this.getWidth())+63)+"px; ");
			}else{
				buffer.append(" width:280px; ");
			}
			if(this.height!=null&&!"".equals(this.height)){
				buffer.append(" height:"+this.height+"px; ");
			}else{
				buffer.append(" height:30px; ");
			}
			buffer.append("  background:#fff; ' ");
			buffer.append("onchange= getFile(this,\""+upLoadId+"\") />");
			buffer.append("</div></td></tr></table></td>");
		}
		buffer.append("<script type=\"text/javascript\">");
		buffer.append("	function getFile(obj, id) { document.getElementById(id).value = obj.value;}");
        buffer.append("</script>");
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
	public boolean isLabelAlign() {
		return labelAlign;
	}
	public void setLabelAlign(boolean labelAlign) {
		this.labelAlign = labelAlign;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}


}
