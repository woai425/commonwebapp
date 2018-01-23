package com.h3c.portal.wbms.common.dto;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class TreeNode implements java.io.Serializable,Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String parentid;
	private List<TreeNode> childnode = new ArrayList<TreeNode>();
	private TreeNode parent;
	private String label;
	private boolean leaf;
	private short orderno;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<TreeNode> getChildnode() {
		return childnode;
	}

	public void setChildnode(List<TreeNode> childnode) {
		this.childnode = childnode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public short getOrderno() {
		return orderno;
	}

	public void setOrderno(short orderno) {
		this.orderno = orderno;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	
	public int compareTo(Object o) {
		if (o instanceof TreeNode){
			TreeNode node = (TreeNode) o;
			if (this.parentid.equals(node.parentid)){
				return (int)(this.orderno-node.orderno);
			}
			else
				return 0;
		}
		else{
			return 0;
		}

	}
	
	public void addChild(TreeNode node){
		childnode.add(node);
	}
}
