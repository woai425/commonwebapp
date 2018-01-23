package com.h3c.portal.wbms.authmgmt.password.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.entities.Sysgroup;
import com.h3c.portal.wbms.common.dto.TreeNode;

/**
 * *********************************************************************
*
* TreeBuilder.java
*
* H3C所有，
* 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
* @copyright   Copyright: 2015-2020
* @creator     s11972<br/>
* @create-time 2016年3月14日 上午10:36:23
* @revision    $Id:  *
**********************************************************************
 */
public class TreeBuilder {
	
	private TreeBuilder(){
		
	}
	
	private static class SingletonHolder{
		 public final static TreeBuilder instance = new TreeBuilder();   
	}
	
	public static TreeBuilder getInstance(){
		return SingletonHolder.instance;
	}
	
	public String getMenu(List<Sysgroup> hzauList)throws H3cException{
		return buildMenu(hzauList);
	}
	
	private String buildMenu(List<Sysgroup> groupList){		
		TreeNode root = buildTree(groupList);
		StringBuffer sb = new StringBuffer("[");
		if (root!=null){
			getSubMenu(root,sb);
			sb.append(" ] ");
			return sb.toString();
		}
		else{
			return "";
		}		
	}
	
	private TreeNode getGroupTreeNode(Sysgroup group,int orderno){
		TreeNode node = new TreeNode();
		node.setId(group.getGroupid());
		node.setLabel(group.getName());
		node.setLeaf(false);
		node.setParentid(group.getParentid());
		node.setOrderno((short)orderno);
		return node;
	}
	
	private TreeNode buildTree(List<Sysgroup> groupList){
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		TreeNode root = null;
		for (int i=0;i<groupList.size();i++){
			TreeNode node = this.getGroupTreeNode(groupList.get(i),i);
			if (node!=null){
				treeNodeList.add(node);
				if (node.getParentid().equals("root")){
					root=node;
				}
			}
		}
		
		for (int i=0;i<treeNodeList.size();i++){
			TreeNode node = treeNodeList.get(i);
			for (int j=0;j<treeNodeList.size();j++){
				TreeNode childnode=treeNodeList.get(j);
				if (childnode.getParentid().equals(node.getId())){
					node.addChild(childnode);
					childnode.setParent(node);
				}
			}
		}
		return root;
	}
	
	@SuppressWarnings("unchecked")
	private void getSubMenu(TreeNode node, StringBuffer sb){
		
		List<TreeNode> children = node.getChildnode();
		if (children.size()<=0)
			return;
		else{
			Collections.sort(children);
			for (int i=0;i<children.size();i++){
				TreeNode n= (TreeNode)children.get(i);
				sb.append("{");
				sb.append("label : '"+n.getLabel()+"', id : '"+n.getId()+"'");

				if(n.getChildnode().size()>0){
					sb.append(" , children : [ ");
					//递归继续生成下面的子菜单
					getSubMenu(n,sb);
					sb.append(" ]");
				}
				sb.append("}");
				//不是最后一个才在}后加,
				if(i<(children.size()-1)){
					sb.append(",");
				}
			}
		}
	}

}
