package com.shenxinjie;

public class FileTree {//封装一棵完整的树
	public TreeNode root;
	public int Tree_th;
	public FileTree (int ii, String ss) {
		root = new TreeNode(ss);
		root.deep = 0;
		Tree_th = ii;
	}
	
	public void LinkChild(TreeNode fa, TreeNode child) {
		fa.Child.add(child);
	}

	public TreeNode GetRoot() {
		return root;
	}
	public int GetTh() {
		return Tree_th;
	}
}
