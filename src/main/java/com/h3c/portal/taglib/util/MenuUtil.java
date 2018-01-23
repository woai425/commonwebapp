package com.h3c.portal.taglib.util;

import java.util.ArrayList;
import java.util.List;
import com.h3c.framework.common.entities.Sysfunction;

public class MenuUtil {
	private StringBuffer html = new StringBuffer();
	private List<Sysfunction> nodes;
	private String usage;
	private String width;
	private String height;
	private String fontsize;
	private String property;
	private String contentIframeId;
	private String contextPath;
	private String style;
	private String handler;

	public MenuUtil() {
	}

	public MenuUtil(List<Sysfunction> data, String usage, String fontsize,
			String width, String height, String property,
			String contentIframeId, String contextPath, String style,
			String handler) {
		this.nodes = data;
		this.usage = usage;
		this.width = width;
		this.height = height;
		this.fontsize = fontsize;
		this.property = property;
		this.contentIframeId = contentIframeId;
		this.contextPath = contextPath;
		this.style = style;
		this.handler = handler;
	}

	public String buildTree() {
		if ("portal".equals(usage)) {
			html.append("<div id=\""
					+ property
					+ "\">"
					+ "<div class=\"page-container\" style=\"text-align:left;padding-left:0px;height:"
					+ (this.height == null ? "0px" : this.height)
					+ ";width:"
					+ (this.width == null ? "300px" : this.width)
					+ ";font-family:Microsoft YaHei;font-size:"
					+ (this.fontsize == null ? "13px" : this.fontsize)
					+ "\">"
					+ "<div class=\"sidebar-menu toggle-others fixed\"  style=\"width:"
					+ (this.width == null ? "280px" : this.width) + "\">"
					+ "<div class=\"sidebar-menu-inner\"  style=\"\">"
					+ "<ul  id=\"main-menu\" class=\"main-menu\"  style=\"\">");
			for (Sysfunction node : nodes) {
				String icon = "";
				if (node.getIconcls() != null && !"".equals(node.getIconcls())) {
					icon = "<i class=\"" + node.getIconcls() + "\"></i>";
				} else if (node.getIconurl() != null
						&& !"".equals(node.getIconurl())) {
					icon = "<img style='width:16px;height:16px;' src=\""
							+ this.contextPath + "/" + node.getIconurl()
							+ "\">&nbsp;&nbsp;";
				}
				String id = node.getFunctionid();
				if (node.getParent() == null
						|| "S000000".equals(node.getParent())) {
					if (!childrenExists(node)) {
						html.append("\r\n<li id='"
								+ id
								+ "'><a id=\"c1\"  href=\"#\" onclick=\"goIframe('"
								+ this.contentIframeId + "','" + contextPath
								+ "/" + node.getLocation() + "')\">" + icon
								+ "<span class=\"title\">" + node.getTitle()
								+ "</span></a></li>");
					} else {
						String firstOpen = "";
						if ("1".equals(node.getFirstopen())
								|| "2".equals(node.getFirstopen())) {
							firstOpen = " class='opened active' ";
						}
						html.append("\r\n<li " + firstOpen + " id='" + id
								+ "'><a id=\"c1\"   href='#'>" + icon
								+ "<span class=\"title\">" + node.getTitle()
								+ "</span></a>");
						build(node);
					}
				}
			}
			html.append("\r</ul></div></div></div></div>");
			return html.toString();
		} else {
			if ("sign".equals(this.style)) {
				html.append("<ul id=\"h3c-accordionmenu-root\">");

				for (Sysfunction node : nodes) {
					String id = node.getFunctionid();
					if (node.getParent() == null
							|| "root".equals(node.getParent())) {
						if (!childrenExists(node)) {
							html.append("\r\n<li id='" + id
									+ "'><a  onclick=\"" + handler + "(" + id
									+ ",'" + contextPath + "/"
									+ node.getLocation() + "')\">"
									+ node.getTitle() + "</a></li>");
						} else {
							html.append("\r\n<li id='" + id
									+ "'><a  onclick=\"" + handler + "(" + id
									+ ",'" + contextPath + "/"
									+ node.getLocation() + "')\">"
									+ node.getTitle() + "</a>");
							build(node);
						}
					}
				}
				html.append("\r</ul>");
			} else {
				html.append("<ul id=\"h3c-accordionmenu-root\" class=\"filetree\">");
				for (int i = 0; i < nodes.size(); i++) {
					String id = nodes.get(i).getFunctionid();
					if (nodes.get(i).getParent() == null
							|| "root".equals(nodes.get(i).getParent())) {
						if (!childrenExists(nodes.get(i))) {
							html.append("\r\n<li class='closed' id='" + id
									+ "'><span class='folder'><a onclick=\""
									+ handler + "(" + id + ",'" + contextPath
									+ "/" + nodes.get(i).getLocation()
									+ "')\">" + nodes.get(i).getTitle()
									+ "</a></span></li>");
						} else {
							String firstOpen = "closed";
							if ("0".equals(nodes.get(i).getFirstopen())
									|| "2".equals(nodes.get(i).getFirstopen())) {
								firstOpen = "open";
							}
							html.append("\r\n<li class='" + firstOpen
									+ "' id='" + id
									+ "'><span class='folder'><a  onclick=\""
									+ handler + "(" + id + ",'" + contextPath
									+ "/" + nodes.get(i).getLocation()
									+ "')\">" + nodes.get(i).getTitle()
									+ "</a></span>");
							build(nodes.get(i));
						}
					}
				}
				html.append("\r</ul>");
			}
			return html.toString();
		}

	}

	private void build(Sysfunction sysfunction) {
		if ("portal".equals(usage)) {
			List<Sysfunction> children = getChildren(sysfunction);
			if (!children.isEmpty()) {
				html.append("\r<ul>");
				for (Sysfunction child : children) {
					String icon = "";
					if (child.getIconcls() != null
							&& !"".equals(child.getIconcls())) {
						icon = "<i class=\"" + child.getIconcls() + "\"></i>";
					} else if (child.getIconurl() != null
							&& !"".equals(child.getIconurl())) {
						icon = "<img style='width:16px;height:16px;' src=\""
								+ this.contextPath + "/" + child.getIconurl()
								+ "\">&nbsp;&nbsp;";
					}
					if (childrenExists(child)) {
						String firstOpen = "";
						if ("1".equals(child.getFirstopen())
								|| "2".equals(child.getFirstopen())) {
							firstOpen = " class='opened' ";
						}
						html.append("\r<li " + firstOpen + "><a  href='#'>"
								+ icon + "<span class=\"title\">"
								+ child.getTitle() + "</span></a>");
					} else {
						html.append("\r<li><a   href=\"#\" onclick=\"goIframe('"
								+ this.contentIframeId
								+ "','"
								+ contextPath
								+ "/"
								+ child.getLocation()
								+ "')\">"
								+ icon
								+ "<span class=\"title\">"
								+ child.getTitle()
								+ "</span></a></li>");
					}
					build(child);
				}
				html.append("\r</ul>\r</li>");
			}
		} else {
			List<Sysfunction> children = getChildren(sysfunction);
			if ("sign".equals(this.style)) {
				if (!children.isEmpty()) {
					html.append("\r<ul>");
					for (Sysfunction child : children) {
						String id = child.getFunctionid();
						if (childrenExists(child)) {
							html.append("\r<li><a  onclick=\"" + handler + "("
									+ id + ",'" + contextPath + "/"
									+ child.getLocation() + "')\">"
									+ child.getTitle() + "</a>");
						} else {
							html.append("\r<li><a onclick=\"" + handler + "("
									+ id + ",'" + contextPath + "/"
									+ child.getLocation() + "')\">"
									+ child.getTitle() + "</a></li>");
						}
						build(child);
					}
					html.append("\r</ul>\r</li>");
				}
			} else {
				if (!children.isEmpty()) {
					html.append("\r<ul>");
					for (int i = 0; i < children.size(); i++) {
						String id = children.get(i).getFunctionid();
						if (childrenExists(children.get(i))) {
							String firstOpen = "closed";
							if ("0".equals(children.get(i).getFirstopen())
									|| "2".equals(children.get(i)
											.getFirstopen())) {
								firstOpen = "open";
							}
							html.append("\r<li class='" + firstOpen
									+ "'><span class='folder'><a  onclick=\""
									+ handler + "(" + id + ",'" + contextPath
									+ "/" + children.get(i).getLocation()
									+ "')\">" + children.get(i).getTitle()
									+ "</a></span>");
						} else {
							html.append("\r<li class='closed'><span class='file'><a  onclick=\""
									+ handler
									+ "("
									+ id
									+ ",'"
									+ contextPath
									+ "/"
									+ children.get(i).getLocation()
									+ "')\">"
									+ children.get(i).getTitle()
									+ "</a></span></li>");
						}
						build(children.get(i));
					}
					html.append("\r</ul>\r</li>");
				}
			}
		}
	}

	private boolean childrenExists(Sysfunction sysfunction) {
		String id = sysfunction.getFunctionid();
		for (Sysfunction child : nodes) {
			if (id.equals(child.getParent())) {
				return true;
			}
		}
		return false;
	}

	private List<Sysfunction> getChildren(Sysfunction sysfunction) {
		List<Sysfunction> children = new ArrayList<Sysfunction>();
		String id = sysfunction.getFunctionid();
		for (Sysfunction child : nodes) {
			if (id.equals(child.getParent())) {
				children.add(child);
			}
		}
		return children;
	}
}
