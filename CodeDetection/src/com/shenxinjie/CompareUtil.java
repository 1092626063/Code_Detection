package com.shenxinjie;

//import bean.Results;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;

class node
{
	public int x,y;
	public int val;
};

public class CompareUtil
{
	private static int havewords = 0;//标记当前段是否全有效行，不仅仅是空格、制表符
	private static Set<String> CPPName1;
	private static Set<String> CPPName2;
	private static Set<String> CPPName3;
	private static ArrayList<Vector<Vector<String>>> Words = new ArrayList<Vector<Vector<String>>>();
	public static double[][] Sim;//存储相似度
	
	private static int FileNumber;
	private static final int MAXSTU = 100;
	private static final int MAXLINE = 2010;
	private static final int MAXNUM = 1000;
	private static String[] FileNameList;//第i个文件的路径
	private static String[][] FileData;//第i个文件的第j行的所有字符，未处理
	private static String[][] ChuLi;
	private static String[][] FileDataModified;//处理后，第i个文件的第j行所包含的关键字字符串序列
	private static int dFileLine[];//第i个文件一共有多少行
	private static int MatchInf[][];//第i和j个文件的相似度结果
	private static int MatchResult[][][][];//MatchResult[i][ii][j][jj] 第i个文件第ii行与第j个文件第jj行相同则为1， 不同则为0.此项用于单击某一行，显示对应标红
	private static int MatchR[][];//MatchR[1,2][i] 第1个文件或者第2个文件的第i行，为1标红。//此项用于点击详情后跳转输出，或点击全部对照时输出
	private static node print[];
	private static Results results;
	private static FileTree[] tree;
	private static Similarity_Sort Sims;
	
	public static Results getResultSet(ArrayList<String> filePathList, int compareFileNum, 
		boolean considerSpace, boolean considerCapitalization)
	{
		CPPName1 = new HashSet<String>();//关键字集合初始化
		CPPName2 = new HashSet<String>();
		CPPName3 = new HashSet<String>();
		ProcessCPPName1();//添加关键字
		ProcessCPPName2();
		ProcessCPPName3();
		ProcessFileName(filePathList);//得到一共有多少个文件以及每个文件的路径
		
		results = null;
		dFileLine = new int[MAXLINE];
		MatchInf = new int[MAXSTU][MAXSTU];
		MatchResult = new int [15][MAXSTU*5][15][MAXSTU*5];
		MatchR = new int [15][MAXSTU*8];
		
		FileData = new String[FileNumber][MAXLINE];
		ChuLi = new String[FileNumber][MAXLINE];
		Sim = new double[FileNumber][FileNumber];
		FileDataModified = new String[MAXSTU][MAXLINE];
		
		
		tree = new FileTree[FileNumber];//声明N棵树
		for (int th = 0; th < FileNumber; th++) {
			tree[th] = new FileTree(th, "#Root");
		}
		
		for(int i = 0; i < FileNumber; i++)
		{
			ReadData(FileNameList[i], i);/**
										 * 读取第i个文件每一行的字符存储在FileData中,不包含空行。
										 * 去除头文件、注释后存储在ChuLi中。
										 * 分割单词，每行的单词存储在line中，每一行的line存储在word中，每一个文件的word存储在Words中。
										 * 对分割完单词后的Words添加花括号。
										 */										
			Tongji(tree[i], i);//建立语法树
		}
		
		Sims = new Similarity_Sort(FileNumber * (FileNumber - 1) / 2);
		int ths = 0;
		System.out.println("计算相似度：");
		for (int th1 = 0; th1 < FileNumber; th1++) {
			for (int th2 = th1+1; th2 < FileNumber; th2++) {
				double sim1 = cal_similarity(tree[th1].root, tree[th2].root);//返回一个相似度，然后存储两棵树的相似度
				//System.out.println("相似度: "+ sim1 + " " + filename[th1] + " " + filename[th2]);
				Sim[th1][th2] = sim1;
				//Sim[th2][th1] = sim1;
				Sims.add_node(ths++, sim1, FileNameList[th1], FileNameList[th2], th1, th2);
			}
		}
		
		
		PrintInf();
		print = null;
		dFileLine = null;
		MatchInf = null;
		MatchResult = null;
		MatchR = null;
		FileNameList = null;
		FileData = null;
		FileDataModified = null;
		ChuLi = null;
		Sim = null;
		tree = null;
		Sims = null;
//		Words = null;因为Words定义在此函数外面，所以不能设置null，第二次会有问题。
		Words.clear();//暂且先这样，据说有个swap可以对内存占有优化。
		return results;
	}
	private static void PrintInf()
	{
		// TODO Auto-generated method stub
		int results_num = FileNumber * (FileNumber - 1) / 2;
		String[] orFileNames = new String[results_num];
		String[] sortFileNames = new String[results_num];
		String[] simResults = new String[results_num];
		int[] recordX = new int[results_num];
		int[] recordY = new int[results_num];
//		for (int i = 0; i <= MAXSTU; ++i)
//		{
//			print[i] = new node();
//		}
//
//		for (int i = 1; i <= PrintNumber; ++i)
//		{
//			Double num = new Double(print[i].val);
//			orFileNames[i] = FileNameList[print[i].x].contains("\\") ? 
//				FileNameList[print[i].x].substring(FileNameList[print[i].x].lastIndexOf("\\")+1) : 
//				(FileNameList[print[i].x].contains("/") ? FileNameList[print[i].x].substring(FileNameList[print[i].x].lastIndexOf("\\")+1) : FileNameList[print[i].x]);
//			sortFileNames[i] = FileNameList[print[i].y].contains("\\") ? 
//				FileNameList[print[i].y].substring(FileNameList[print[i].y].lastIndexOf("\\")+1) : 
//				(FileNameList[print[i].y].contains("/") ? FileNameList[print[i].y].substring(FileNameList[print[i].y].lastIndexOf("\\")+1) : FileNameList[print[i].y]);
//			//FileNameList[print[i].y];
//			simResults[i] = num.toString();
//		}
		
		Sims.Sim_Sort();
		for (int th1 = 0; th1 < Sims.num; th1++) {
			//if (Sims.A[th1].sim > 0.5)
//			System.out.println("相似度: "+ Sims.A[th1].sim + " " + Sims.A[th1].name1 + " " + Sims.A[th1].name2);
			Double sim_val = new Double(Sims.A[th1].sim);
			orFileNames[th1] = FileNameList[Sims.A[th1].th_1].contains("\\") ? 
				FileNameList[Sims.A[th1].th_1].substring(FileNameList[Sims.A[th1].th_1].lastIndexOf("\\")+1) : 
				(FileNameList[Sims.A[th1].th_1].contains("/") ? FileNameList[Sims.A[th1].th_1].substring(FileNameList[Sims.A[th1].th_1].lastIndexOf("\\")+1) : FileNameList[Sims.A[th1].th_1]);
			sortFileNames[th1] = FileNameList[Sims.A[th1].th_2].contains("\\") ? 
				FileNameList[Sims.A[th1].th_2].substring(FileNameList[Sims.A[th1].th_2].lastIndexOf("\\")+1) : 
				(FileNameList[Sims.A[th1].th_2].contains("/") ? FileNameList[Sims.A[th1].th_2].substring(FileNameList[Sims.A[th1].th_2].lastIndexOf("\\")+1) : FileNameList[Sims.A[th1].th_2]);
			simResults[th1] = sim_val.toString();
			recordX[th1] = Sims.A[th1].th_1;
			recordY[th1] = Sims.A[th1].th_2;
//			System.out.println("相似性: "+ simResults[th1] + " " + orFileNames[th1] + " " + sortFileNames[th1]);
		}
		
		results = new Results(orFileNames, sortFileNames, simResults, FileData, Sims.num, recordX, recordY);

		orFileNames = null;
		sortFileNames = null;
		simResults = null;
	}
	
	private static double cal_similarity(TreeNode tree1, TreeNode tree2) {
		//int max_line_now = Math.max(tree1.block_lines, tree2.block_lines);
		TreeNode copy_tree1 = new TreeNode(tree1);
		TreeNode copy_tree2 = new TreeNode(tree2);
		//首先决定是否往更深层次的结点去比较，定义一个规则
		int child1_size = tree1.Child.size();
		int child2_size = tree2.Child.size();
		//System.out.println(tree1.key_word + " " + tree2.key_word + child1_size + " "+child2_size);
		Similarity_Sort sim_sort = new Similarity_Sort(child1_size*child2_size);//叶子结点相似度数组
		int th = 0;
		for (int th1 = 0; th1 < child1_size; th1++) {
			TreeNode child1 = tree1.Child.get(th1);
			copy_tree1 = get_rest(copy_tree1, child1);
			for (int th2 = 0; th2 < child2_size; th2++) {
				TreeNode child2 = tree2.Child.get(th2);
				if (get_type(child1.key_word) == get_type(child2.key_word)) {
					int num_ok = match_num_ok(child1, child2);//匹配度较高的属性数
					if (num_ok >= child1.num_attribute/2) {
						double sim = cal_similarity(child1, child2);
						sim_sort.add_node(th++, sim, th1, th2, Math.max(child1.block_lines, child2.block_lines));
					}else {
						sim_sort.add_node(th++, 0, th1, th2, 0);
					}
				}else {
					sim_sort.add_node(th++, 0, th1, th2, 0);
				}
			}
		}
		for (int th2 = 0; th2 < child2_size; th2++) {
			TreeNode child2 = tree2.Child.get(th2);
			copy_tree2 = get_rest(copy_tree2, child2);
		}
		
		//统计子结点的综合结果
		sim_sort.Sim_Sort();
		/*System.out.println(sim_sort.num);
		for (int th1 = 0; th1 < sim_sort.num; th1++) {
			System.out.println(sim_sort.A[th1].sim + " " + sim_sort.A[th1].max_line + " " + sim_sort.A[th1].th_1 + " "+sim_sort.A[th1].th_2);

		}*/
		
		//对当前结点的处理
		if (child1_size == 0 || child2_size == 0) {//叶子结点计算相似度
			double sim_now = get_sim(tree1, tree2);
			if (tree1.deep == 1 && tree2.deep == 1) {//lcs计算出来的相似度与属性度量出来的相似度各占50%，考虑到函数体里面结构的顺序调换所产生的影响
				int lcs = LCS(tree1, tree2);
				int max_hash_size = Math.max(tree1.hashs.size(), tree2.hashs.size());
				if (max_hash_size != 0) {
					double sim_lcs = lcs * 1.0 / max_hash_size;
					sim_now = sim_now * 0.5 + sim_lcs * 0.5;
				}
			}
			return sim_now;
		}else {//非叶子结点计算相似度
			int[] vis1, vis2;//用来记录结点是否已经被处理
			vis1 = new int[child1_size];
			vis2 = new int[child2_size];
			for (int th1 = 0; th1 < child1_size; th1++) {
				vis1[th1] = 0;
			}
			for (int th1 = 0; th1 < child2_size; th1++) {
				vis2[th1] = 0;
			}
			
			double sim_child = 0, sim_rest = 0, sim_now = 0;
			int max_line_now = Math.max(tree1.block_lines, tree2.block_lines);
			//子结点相似度之和
			for (int th1 = 0; th1 < sim_sort.num; th1++) {
				if (sim_sort.A[th1].sim == 0) break;
				int aa = sim_sort.A[th1].th_1;
				int bb = sim_sort.A[th1].th_2;
				if (vis1[aa] == 0 && vis2[bb] == 0) {
					vis1[aa] = 1;
					vis2[bb] = 1;
					if (max_line_now != 0)
						sim_child += sim_sort.A[th1].sim * sim_sort.A[th1].max_line / max_line_now;
				}
			}
			//当前剩余属性相似度计算
			sim_rest = get_sim(copy_tree1, copy_tree2);
			if (max_line_now != 0)
				sim_rest = sim_rest * Math.max(copy_tree1.block_lines, copy_tree2.block_lines) / max_line_now;
			//当前结点总相似度
			sim_now = sim_child + sim_rest;
			if (tree1.deep == 1 && tree2.deep == 1) {//判断是否是函数体，额外增加一个hash值的LCS以提高精确度。
				int lcs = LCS(tree1, tree2);
				int max_hash_size = Math.max(tree1.hashs.size(), tree2.hashs.size());
				if (max_hash_size != 0) {
					double sim_lcs = lcs * 1.0 / max_hash_size;
					sim_now = sim_now * 0.5 + sim_lcs * 0.5;//lcs计算出来的相似度与属性度量出来的相似度各占50%，考虑到函数体里面结构的顺序调换所产生的影响
				}
			}
			return sim_now;
		}
	}
	
	private static TreeNode get_rest(TreeNode tree1, TreeNode tree2) {
		tree1.block_arrays -= tree2.block_arrays;
		tree1.block_assignments -= tree2.block_assignments;
		tree1.block_for_while -= tree2.block_for_while;
		tree1.block_if_else -= tree2.block_if_else;
		tree1.block_keywords -= tree2.block_keywords;
		tree1.block_lines -= tree2.block_lines;
		tree1.block_operators -= tree2.block_operators;
		tree1.block_variables -= tree2.block_variables;
		return tree1;
	}
	
	private static double get_sim(TreeNode tree1, TreeNode tree2) {
		double sum_fz = 0, sum_fm1 = 0, sum_fm2 = 0, sum_fm = 0;
		//计算分子
		sum_fz += tree1.block_arrays*tree2.block_arrays;
		sum_fz += tree1.block_assignments*tree2.block_assignments;
		sum_fz += tree1.block_for_while*tree2.block_for_while;
		sum_fz += tree1.block_if_else*tree2.block_if_else;
		sum_fz += tree1.block_keywords*tree2.block_keywords;
		sum_fz += tree1.block_lines*tree2.block_lines;
		sum_fz += tree1.block_operators*tree2.block_operators;
		sum_fz += tree1.block_variables*tree2.block_variables;
		//计算分母1
		sum_fm1 += tree1.block_arrays*tree1.block_arrays;
		sum_fm1 += tree1.block_assignments*tree1.block_assignments;
		sum_fm1 += tree1.block_for_while*tree1.block_for_while;
		sum_fm1 += tree1.block_if_else*tree1.block_if_else;
		sum_fm1 += tree1.block_keywords*tree1.block_keywords;
		sum_fm1 += tree1.block_lines*tree1.block_lines;
		sum_fm1 += tree1.block_operators*tree1.block_operators;
		sum_fm1 += tree1.block_variables*tree1.block_variables;
		//计算分母2
		sum_fm2 += tree2.block_arrays*tree2.block_arrays;
		sum_fm2 += tree2.block_assignments*tree2.block_assignments;
		sum_fm2 += tree2.block_for_while*tree2.block_for_while;
		sum_fm2 += tree2.block_if_else*tree2.block_if_else;
		sum_fm2 += tree2.block_keywords*tree2.block_keywords;
		sum_fm2 += tree2.block_lines*tree2.block_lines;
		sum_fm2 += tree2.block_operators*tree2.block_operators;
		sum_fm2 += tree2.block_variables*tree2.block_variables;
						
		sum_fm1 = Math.sqrt(sum_fm1);
		sum_fm2 = Math.sqrt(sum_fm2);
		sum_fm = sum_fm1 * sum_fm2;
		if (sum_fm != 0 && Math.max(sum_fm1, sum_fm2) != 0) {
			return (sum_fz / sum_fm) * (Math.min(sum_fm1, sum_fm2) / Math.max(sum_fm1, sum_fm2));
		}
		return 0;
	}
	
	private static int LCS(TreeNode tree1, TreeNode tree2) {
		int len1 = tree1.hashs.size();
		int len2 = tree2.hashs.size();
		int[][] dp = new int[len1+1][len2+1];
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				if (tree1.hashs.get(i).equals(tree2.hashs.get(j))) {
					dp[i+1][j+1] = dp[i][j] + 1;
				}else {
					dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
				}
			}
		}
		return dp[len1][len2];
	}
	
	private static int match_num_ok(TreeNode node1, TreeNode node2) {
		int num_ok = 0;
		if (Math.abs(node1.block_arrays - node2.block_arrays) <= (Math.max(node1.block_arrays, node2.block_arrays)+1)/2)
			num_ok++;
		if (Math.abs(node1.block_assignments - node2.block_assignments) <= (Math.max(node1.block_assignments, node2.block_assignments)+1)/2)
			num_ok++;
		if (Math.abs(node1.block_for_while - node2.block_for_while) <= (Math.max(node1.block_for_while, node2.block_for_while)+1)/2)
			num_ok++;
		if (Math.abs(node1.block_if_else - node2.block_if_else) <= (Math.max(node1.block_if_else, node2.block_if_else)+1)/2)
			num_ok++;
		if (Math.abs(node1.block_keywords - node2.block_keywords) <= (Math.max(node1.block_keywords, node2.block_keywords)+1)/2)
			num_ok++;
		if (Math.abs(node1.block_lines - node2.block_lines) <= (Math.max(node1.block_lines, node2.block_lines)+1)/2)
			num_ok++;
		if (Math.abs(node1.block_operators - node2.block_operators) <= (Math.max(node1.block_operators, node2.block_operators)+1)/2)
			num_ok++;
		if (Math.abs(node1.block_variables - node2.block_variables) <= (Math.max(node1.block_variables, node2.block_variables)+1)/2)
			num_ok++;
		return num_ok;
	}
	
	private static int get_type(String s) {
		switch(s) {
		case "for":
		case "while":
		case "do":
			return 1;
		case "int":
		case "double":
		case "float":
		case "char":
		case "string":
		case "vector":
		case "map":
		case "set":
		case "void":
		case "long":
		case "stack":
		case "list":
		case "bool":
		case "short":
			return 2;
		case "if":
		case "else":
		case "case":
		case "default":
			return 3;
		default:
			return 0;
		}
	}
	
	private static void dfs(TreeNode tree) {
		System.out.println("关键词:"+tree.key_word);
		System.out.println("深度:"+tree.deep);
		System.out.println("hash_size:"+tree.hashs.size());
		System.out.println("代码行数:"+tree.block_lines);
		System.out.println("数组个数:"+tree.block_arrays);
		System.out.println("关键字个数:"+tree.block_keywords);
		System.out.println("运算符个数:"+tree.block_operators);
		System.out.println("赋值语句数:"+tree.block_assignments);
		System.out.println("自定义变量个数:"+tree.block_variables);
		System.out.println("if、else个数:"+tree.block_if_else);
		System.out.println("for、while个数:"+tree.block_for_while);
		System.out.println("");
		int _size = tree.Child.size();
		
		for (int i = 0; i < _size; i++) {
			dfs(tree.Child.get(i));
		}
	}
	
	private static void Tongji(FileTree tree, int th) {
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.add(tree.root);
		Vector<Vector<String>> s = Words.get(th);
		String key_word = "";
		int do_while = 0;//用于判别do-while循环
		int is_inside_brace = 0;//用于消除()中的key_word影响
		//System.out.println("TongJi");
		System.out.println("st.size() = "+st.size());
		for (int i = 0; i < s.size(); i++) {//行
			Vector<String> h = s.get(i);
			String str_line = "";
			//System.out.println("000 "+ h);
			for (int j = 0; j < h.size(); j++) {//每一行的单词
				String now_str = h.get(j);
				//System.out.println("222 "+ now_str);
				switch (now_str) {
				case "{":
					//System.out.println("111  "+key_word + "  " + i);
					TreeNode temp = new TreeNode(key_word);
					temp.deep = st.peek().deep + 1;
					key_word = "";
					st.push(temp);
					continue;
				case "}":
					if (st.size() != 0) {
						TreeNode old_top = st.pop();
						if (st.size() != 0) {
							old_top.block_str = "{"+old_top.block_str+"}";
							st.peek().block_str += old_top.block_str;
							st.peek().block_arrays += old_top.block_arrays;
							st.peek().block_assignments += old_top.block_assignments;
							st.peek().block_for_while += old_top.block_for_while;
							st.peek().block_if_else += old_top.block_if_else;
							st.peek().block_keywords += old_top.block_keywords;
							st.peek().block_lines += old_top.block_lines;
							st.peek().block_operators += old_top.block_operators;
							st.peek().block_variables += old_top.block_variables;
							
							//hash值迁移
							if (st.size() != 1) {
								int len = old_top.hashs.size();
								for (int th1 = 0; th1 < len; th1++) {
									st.peek().hashs.add(old_top.hashs.get(th1));
								}
							}
							tree.LinkChild(st.peek(), old_top);
						}
					}
					continue;
				case "while"://这里有个顺序，while先判，再判do
					if (do_while == 1) {//解决do-while的判定
						do_while = 0;
						continue;
					}
					if (is_inside_brace == 0)
						key_word = now_str;
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_str += now_str;
						st.peek().block_for_while++;
						st.peek().block_keywords++;
						st.peek().block_lines++;
						str_line += now_str;
					}
					continue;
				case "do":
					if (is_inside_brace == 0)
						key_word = now_str;
					do_while = 1;
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_str += now_str;
						st.peek().block_for_while++;
						st.peek().block_keywords++;
						st.peek().block_lines++;
						str_line += now_str;
					}
					continue;
				case "for":
					if (is_inside_brace == 0)
						key_word = now_str;
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_str += now_str;
						st.peek().block_for_while++;
						st.peek().block_keywords++;
						st.peek().block_lines--;//遇到一个for两个; 所以减一个
						str_line += now_str;
					}
					continue;
				case "switch":
					if (is_inside_brace == 0)
						key_word = now_str;
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_str += now_str;
						st.peek().block_keywords++;
						st.peek().block_lines++;
						str_line += now_str;
					}
					continue;
				case "if":
				case "case":
				case "default":
					if (is_inside_brace == 0)
						key_word = now_str;
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_str += now_str;
						st.peek().block_if_else++;
						st.peek().block_keywords++;
						st.peek().block_lines++;
						str_line += now_str;
					}
					continue;
				case "else":
					if (j != h.size() - 1) {
						String next_str = h.get(j+1);
						if (next_str.equals("{")) {
							if (is_inside_brace == 0)
								key_word = now_str;
							if (st.size() == 0) {
								System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
							}else {
								st.peek().block_str += now_str;
								st.peek().block_if_else++;
								st.peek().block_keywords++;
								st.peek().block_lines++;
								str_line += now_str;
							}
						}
					}else {
						if (is_inside_brace == 0)
							key_word = now_str;
						if (st.size() == 0) {
							System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
						}else {
							st.peek().block_str += now_str;
							st.peek().block_if_else++;
							st.peek().block_keywords++;
							st.peek().block_lines++;
							str_line += now_str;
						}
					}
					continue;
				case "int":
				case "double":
				case "float":
				case "char":
				case "string":
				case "vector":
				case "map":
				case "set":
				case "void":
				case "long":
				case "stack":
				case "list":
				case "bool":
				case "short":
					if (key_word.equals("") || CPPName3.contains(key_word))
						if (is_inside_brace == 0)
							key_word = now_str;
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_str += now_str;
						st.peek().block_keywords++;
						st.peek().block_variables++;
						str_line += now_str;
					}
					continue;
				case "["://存在一个隐形的误判，无法判别二维数组
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_arrays++;
						st.peek().block_str += now_str;
						str_line += now_str;
					}
					continue;
				case "=":
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_assignments++;
					}
				case ">=":
				case "<=":
				case "+":
				case "-":
				case "*":
				case "/":
				case "&":
				case "^":
				case "%":
				case ">>":
				case "<<":
				case "==":
				case ">":
				case "<":
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_operators++;
						st.peek().block_str += now_str;
						str_line += now_str;
					}
					continue;
				case ";":
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_lines++;
						st.peek().block_str += now_str;
						str_line += now_str;
					}
					continue;
				case "(":
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_keywords++;
						st.peek().block_str += now_str;
						str_line += now_str;
						is_inside_brace++;
					}
					continue;
				case ")":
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_keywords++;
						st.peek().block_str += now_str;
						str_line += now_str;
						is_inside_brace--;
					}
					continue;
				default:
					if (CPPName2.contains(now_str)) {
						if (st.size() == 0) {
							System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
						}else {
							st.peek().block_keywords++;
						}
					}else {
						now_str = "$";
					}
					if (st.size() == 0) {
						System.out.println("stack_size_is_zero :" + th + " "+s.get(i)+" "+j);
					}else {
						st.peek().block_str += now_str;
						str_line += now_str;
					}
				}	
			}//一行结束
			if (str_line.length() != 0)
				if (st.size() == 0) {
					System.out.println("stack_size_is_zero :" + th + " "+s.get(i));
				}else {
					st.peek().hashs.add(get_hash(str_line));
				}
		}
		
	}
	
	private static int get_hash(String s) {
		//System.out.println("str: "+s.hashCode());
		return s.hashCode();
	}
	
	private static void ReadData(String fileName, int i) {
	  try {
			String encoding="UTF-8";
			File file = new File(fileName);
			//System.out.println("文件名FileName: " + fileName);
			if(file.isFile() && file.exists()){ //判断文件是否存在
			  InputStreamReader read = new InputStreamReader(new FileInputStream(file));//encoding考虑到编码格式
			  BufferedReader bufferedReader = new BufferedReader(read);
			  
			  String lineTxt = "";
			  int x=0, y=0;
			  int notes = 0;//注释标记
			  int parentheses = 0;//圆括号标记
			  int Quotation_marks = 0;//双引号标记
			  int depart = 0;//手动分段
			  int need_Curly_braces_for = 0, need_Curly_braces_case = 0;//是否需要增加花括号
			  
			  while((lineTxt = bufferedReader.readLine()) != null){
				FileData[i][++x]=lineTxt;
				if(FileData[i][x].length()==0) --x;//排除空行
				else {
					/**
				     * 去除字符串中头部和尾部所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
				     */
					lineTxt = lineTxt.replaceAll("\t", "").trim();
					String[] str = lineTxt.split("\\s+");
					ChuLi[i][y] = "";
					//System.out.println(lineTxt);
					for (int index = 0; index < lineTxt.length(); ++index) {
						char ch = lineTxt.charAt(index);

						/**
						 * 去除头文件
						 */
						if (str[0].charAt(0) == '#') break;
						
						/**
						 * 去除注释。
						 */
						if (notes == 1) {
							if (ch == '*' && index < lineTxt.length()-1 && lineTxt.charAt(index+1) == '/') {
								notes = 0;
								++index;
							}
							continue;
						}
						if (notes == 0 && ch == '/') {
							if (index < lineTxt.length()-1 && lineTxt.charAt(index+1) == '/') {							
								//存储前面的
								break;
							}
							else if (index < lineTxt.length()-1 && lineTxt.charAt(index+1) == '*') {
								//存储前面的
								notes = 1;
								++index;
								continue;
							}
						}
						
						/**
						 * 分号分段的情况处理：
						 * 				字符串中分号的处理，如"ack;"
						 * 				括号中也有分号，如for循环for(int i = 0; i < 5; ++i)
						 * 				字符串中也可能有括号
						 *所以先处理字符串的，对未在字符串中的括号继续处理。
						 *隐藏问题：
						 *		printf("555");    ///564565
						 *		这种情况会出现下一行全是空格的问题。
						 *		要解决这种问题，在所有字符串去掉所有空格、制表符之后再进行分段操作也许会比较好
						 *		解决了，通过记录是否是有效字符串来控制！
						 */
						/*if (Quotation_marks == 0 && ch == '"') Quotation_marks = 1;
						else if (Quotation_marks == 1 && ch == '"') Quotation_marks = 0;
						else if (Quotation_marks == 0) {//未在引号里
							if (ch == '(') parentheses++;
							else if (parentheses != 0 && ch == ')') parentheses--;
							else if (parentheses == 0) {//未在括号里面
								if (ch == ';' && index != lineTxt.length() - 1) {//分段
									depart = 1;
								}
							}
						}*/
						
						if (!(ch == ' ' || ch == '\t')) havewords = 1;
						//if (ch != ' ')//这里不能去掉句内空格，如int i = 0;
						ChuLi[i][y] += ch;
						/*if (depart == 1) {
							ChuLi[i][y] = ChuLi[i][y].replaceAll("\t", "").trim();//存储前一段
							if (havewords == 1) {
								y++;
								ChuLi[i][y] = "";//这要是不置空的话另起一行会出现字符串开头是null的情况！！！
								havewords = 0;
							}
							depart = 0;
						}*/
						
						
					}
					if (ChuLi[i][y].length() != 0) {
						ChuLi[i][y] = ChuLi[i][y].replaceAll("\t", "").trim();
						if (havewords == 1) {
							y++;//非空再变为下一行
							havewords = 0;
						}
					}
				}
				lineTxt = "";
			  }
			  
			  /*System.out.println("处理后:");
			  for (int t = 0; t < y; ++t) {
				  System.out.println(ChuLi[i][t]);
			  }*/
			  
			  /**
			   * 分离出单词
			   * 根据特殊符号来分离
			   */
			  Vector<Vector<String>> word = new Vector<Vector<String>>();
			  int xiaokuohao = 0;
			  for (int th1 = 0; th1 < y; ++th1) {
				  int len = ChuLi[i][th1].length();
				  Vector<String> line = new Vector<>();
				  String str = new String();
				  String old = new String();
				  String key = new String();
				  str = ""; old = ""; key = "";
				  int delete_next = 0;
				  for (int th2 = 0; th2 < len; ++th2) {//一行中的字符处理
					  char ch = ChuLi[i][th1].charAt(th2);
					  switch(ch) {
					  case '\t':
					  case ' ':
						  if (str.length() != 0) {
							  /*if (str.equals("if") && !old.equals("else")) {//变量是字母或者下划线开头，所以添加前缀0可以区别else if中的if
								  str = "0"+str;
							  }*/
							  line.add(str);
							  if (CPPName3.contains(str))
								  key = str;
							  old = str;
							  str = "";
						  }
						  continue;
					  case '-':
					  case '+':
						  if (th2 < len-1 && ChuLi[i][th1].charAt(th2+1) == '=') {
							  if (str.length() != 0) {
								  line.add(str);
								  line.add("=");
							  	  line.add(str);
							  	  line.add(String.valueOf(ch));
							  }else {
								  line.add("=");
							  	  line.add(old);
							  	  old = "";
							  	  line.add(String.valueOf(ch));
							  }
							  th2++;
						  }else if (th2 < len-1 && (ChuLi[i][th1].charAt(th2+1) == '+' || ChuLi[i][th1].charAt(th2+1) == '-')) {
							  if (str.length() != 0) {
								  line.add(str);
								  line.add("=");
								  line.add(str);
								  line.add(String.valueOf(ch));
								  line.add("1");
								  str = "";
							  }else {
								  line.add("$");
								  line.add("=");
								  line.add("$");
								  line.add(String.valueOf(ch));
								  line.add("1");
								  //下一个不要存了
								  delete_next = 1;
							  }
							  th2++;
						  }else {
							  if (str.length() != 0) {
								  line.add(str);
								  str = "";
							  }
							  line.add(String.valueOf(ch));
						  }	
						  continue;
					  case '*':
					  case '/':
					  case '<':
					  case '>':
					  case '%':
					  case '^':
					  case '&':
					  case '=':
						  if (th2 < len-1 && (ChuLi[i][th1].charAt(th2+1) == '=' || ChuLi[i][th1].charAt(th2+1) == ch)) {
							  if (str.length() != 0) {
								  line.add(str);
								  line.add(String.valueOf(ch) + String.valueOf(ChuLi[i][th1].charAt(th2+1)));
								  str = "";
							  }else {
								  line.add(String.valueOf(ch) + String.valueOf(ChuLi[i][th1].charAt(th2+1)));
							  }
							  th2++;
						  }else {
							  if (str.length() != 0) {
								  line.add(str);
								  str = "";
							  }
							  line.add(String.valueOf(ch));
						  }
						  continue;
					  case '{':
					  case '}':
					  case '[':
					  case ']':
					  case '(':
					  case ')':
					  case ';':
					  case '"':
					  case '\'':
					  case '~':
						  if (str.length() != 0) {
							  if(!(ch == ';' && delete_next == 1))//++i的情况要少一个i
								  line.add(str);
						  }
						  if (ch == '(') xiaokuohao++;
						  else if (ch == ')') xiaokuohao--;
						  str = "";
						  line.add(String.valueOf(ch));
						  continue;
					  case ',':
						  if (str.length() != 0) {
							  if (!key.equals("") && xiaokuohao == 0) {
								  line.add(str);
								  line.add(";");
								  line.add(key);
								  str = "";
							  }else {
								  line.add(str);
								  line.add(",");
								  str = "";
							  }
						  }
						  continue;
					  case ':':
					  case '?':
						  if (str.length() != 0) {
							  line.add(str);
							  str = "";
							  line.add(String.valueOf(ch));
						  }
						  continue;
					  default:
						  str += ch;
						  if (th2 == len-1) {
							  line.add(str);
							  old = str;
							  str = "";
						  }
						  continue;
					  }
				  }
				  word.add(line);
			  }
			  Words.add(word);
			  
			  //单词分割后输出
			 // System.out.println("文件"+i+"单词分割后：");
			 // print_words_division_or_add_brackets(i);
			  
			  //第三步，添加花括号
			  /**
			   * for、while、if、else、do若没有{}，则下一行用{}括起来。
			   * switch-case语句需要与if-else语句转换，转换方法：
			   * switch后面的{}，先不处理，由后续处理。
			   * case需要将下面的整个一段用{}括起来,即在case前加}，在需要{的地方添加上，这就处理好switch与if的对应关系。
			   */
			  int need_Curly_brace_word_for = 0, need_Curly_brace_word_case = 0;
			  int num_kuohao = 0;//记录圆括号层数，用于添加for(;;) a++; 的{}
			  Vector<Vector<String>> s = Words.get(i);
			  for (int th2 = 0; th2 < s.size(); th2++) {
				  Vector<String> ss = s.get(th2);
				  
				  for (int th3 = 0; th3 < ss.size(); th3++) {//一行
					  if(need_Curly_braces_for == 1) {//需要{}但开头不是{
						  //开头结尾添加{}
						  if (th3 == 0 && !ss.get(th3).equals("{")) {
							  Words.get(i).get(th2).add(0, "{");
							  Words.get(i).get(th2).add("}");
							  need_Curly_braces_for = 0;
							  continue;
						  }
						  need_Curly_braces_for = 0;
					  }else if (need_Curly_braces_case == 1) {
						  if (th3 == 0 && !ss.get(th3).equals("{")) {
							  Words.get(i).get(th2).add(0, "{");
							  need_Curly_braces_case = 0;
							  continue;
						  }
						  need_Curly_braces_case = 0;
					  }

					  if (CPPName1.contains(ss.get(th3))) {
						  need_Curly_brace_word_for = 1; 
					  }else if (ss.get(th3).equals("{")) {
						  need_Curly_brace_word_for = 0;
						  need_Curly_brace_word_case = 0;
					  }else if (ss.get(th3).equals("case") || ss.get(th3).equals("default")) {
						  Words.get(i).get(th2).add(0, "}");
						  th3++;
						  need_Curly_brace_word_case = 1;
					  }else if (ss.get(th3).equals(":") && need_Curly_brace_word_case == 1) {
						  if (th3 != ss.size()-1) {
							  if(!ss.get(th3+1).equals("{")) {
								  Words.get(i).get(th2).add(th3+1, "{");
							  }
						  }
					  }else if (need_Curly_brace_word_for == 1) {
						  if (ss.get(th3).equals("(")) {
							  num_kuohao++;
						  }else if (ss.get(th3).equals(")")) {
							  num_kuohao--;
							  if (num_kuohao == 0 && th3 != ss.size()-1) {//解决 for(;;) a++; 的{}
								  if(!ss.get(th3+1).equals("{")) {
									  Words.get(i).get(th2).add(th3+1, "{");
									  Words.get(i).get(th2).add("}");
								  }
							  }
						  }
					  }else if (need_Curly_brace_word_for == 1) {
						  num_kuohao = 0;
					  }
				  }
				  if (need_Curly_brace_word_for == 1) {
					  need_Curly_brace_word_for = 0;
					  need_Curly_braces_for = 1;
				  }
				  if (need_Curly_brace_word_case == 1) {
					  need_Curly_brace_word_case = 0;
					  need_Curly_braces_case = 1;
				  }
			  }
			  
			  //System.out.println("文件"+i+"添加括号后：");
			  //print_words_division_or_add_brackets(i);
			  
			  /**
			   * 按照括号分块统计词数。
			   */
			  
			  
			  read.close();
			}else{
			  System.out.println("找不到指定的文件");
			}
		} catch (IOException e) {
		  System.out.println("读取文件内容出错");
		  e.printStackTrace();
		}
	}
	private static void ProcessFileName(ArrayList<String> fileList)
	{
		int _filenum = 1;
		int length = fileList.size();
		FileNumber = length;
		FileNameList = new String[FileNumber];
		System.out.println("CompareUtil: " + length + " " + fileList);
		for(int i = 0; i < length; ++i)
		{
			++_filenum;
//			System.out.println("CompareUtil: " + (String)fileList.get(i-1));
			FileNameList[i] = (String)fileList.get(i);
		}
       	
		System.out.println("CompareUtil: FileNumber: " + FileNumber);
	}
	
	private static void ProcessCPPName1() {//硬核级别有限处理的关键字
		CPPName1.add("for");
		CPPName1.add("while");
		CPPName1.add("do");
		CPPName1.add("if");
		CPPName1.add("else");
	}
	private static void ProcessCPPName2() {//程序关键字集合，包括特殊符号
		CPPName2.add("asm");
		CPPName2.add("bool");
		CPPName2.add("break");
		CPPName2.add("char");
		CPPName2.add("continue");
		CPPName2.add("case");
		CPPName2.add("cin");
		CPPName2.add("cout");	
		CPPName2.add("catch");
		CPPName2.add("class");
		CPPName2.add("const");
		CPPName2.add("const_cast");
		CPPName2.add("do");
		CPPName2.add("default");
		CPPName2.add("delete");
		CPPName2.add("double");
		CPPName2.add("dynamic_cast");
		CPPName2.add("enum");
		CPPName2.add("else");
		CPPName2.add("explicit");
		CPPName2.add("export");
		CPPName2.add("extern");
		CPPName2.add("false");
		CPPName2.add("true");
		CPPName2.add("friend");
		CPPName2.add("float");
		CPPName2.add("for");
		CPPName2.add("goto");
		CPPName2.add("getchar");
		CPPName2.add("inline");
		CPPName2.add("int");
		CPPName2.add("if");
		CPPName2.add("long");
		CPPName2.add("list");
		CPPName2.add("mutable");
		CPPName2.add("memset");
		CPPName2.add("main");
		CPPName2.add("math");
		CPPName2.add("new");
		CPPName2.add("operator");
		CPPName2.add("private");
		CPPName2.add("protected");
		CPPName2.add("public");
		CPPName2.add("printf");
		CPPName2.add("priority_queue");
		CPPName2.add("queue");
		CPPName2.add("register");
		CPPName2.add("reinterpret_cast");
		CPPName2.add("return");
		CPPName2.add("signed");
		CPPName2.add("sizeof");
		CPPName2.add("static");
		CPPName2.add("size");
		CPPName2.add("static_cast");
		CPPName2.add("struct");
		CPPName2.add("switch");
		CPPName2.add("scanf");
		CPPName2.add("stack");
		CPPName2.add("string");
		CPPName2.add("strlen");
		CPPName2.add("sizeof");
		CPPName2.add("template");
		CPPName2.add("this");
		CPPName2.add("throw");
		CPPName2.add("try");
		CPPName2.add("typedef");
		CPPName2.add("typeid");
		CPPName2.add("typename");
		CPPName2.add("union");
		CPPName2.add("unsigned");
		CPPName2.add("using");
		CPPName2.add("virtual");
		CPPName2.add("volatile");
		CPPName2.add("vector");
		CPPName2.add("wchar_t");
		CPPName2.add("while");
											
		CPPName2.add("\"");
		CPPName2.add("\'");
		CPPName2.add(":");
		CPPName2.add("?");
	}
	
	private static void ProcessCPPName3() {//函数的返回值或者自定义变量类型
		CPPName3.add("int");
		CPPName3.add("double");
		CPPName3.add("float");
		CPPName3.add("char");
		CPPName3.add("short");
		CPPName3.add("string");
		CPPName3.add("void");
		CPPName3.add("vector");
		CPPName3.add("map");
		CPPName3.add("set");
		CPPName3.add("long");
		CPPName3.add("queue");
		CPPName3.add("stack");
		CPPName3.add("list");
		CPPName3.add("bool");
	}
}
