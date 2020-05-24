package com.shenxinjie;

import java.util.Vector;

public class TreeNode {//封装树的每一个结点
	public double Similarity;//相似度
	public int block_lines;//代码行数
	public String block_str;//字符串
	public String key_word;//关键词
	public int deep;//结点的深度
	
	public int num_attribute;//有效属性个数(包括代码行数)
	
	public int block_variables;//自定义变量个数
	public int block_keywords;//关键字个数
	public int block_arrays;//数组个数
	public int block_operators;//运算符个数
	public int block_for_while;//for、while个数
	public int block_if_else;//if、else个数
	public int block_assignments;//赋值语句数
	
	public Vector<TreeNode> Child;
	public Vector<Integer> hashs;
	public Vector<Integer> pos;
	TreeNode (String ss) {
		Similarity = 0;
		block_str = "";
		key_word = ss;
		num_attribute = 8;
		Child = new Vector<TreeNode>();
		hashs = new Vector<Integer>();
		pos = new Vector<Integer>();
	}
	//拷贝构造函数
	public TreeNode(TreeNode t) {
		this.block_arrays = t.block_arrays;
		this.block_assignments = t.block_assignments;
		this.block_for_while = t.block_for_while;
		this.block_if_else = t.block_if_else;
		this.block_keywords = t.block_keywords;
		this.block_lines = t.block_lines;
		this.block_operators = t.block_operators;
		this.block_variables = t.block_variables;
	}
}
