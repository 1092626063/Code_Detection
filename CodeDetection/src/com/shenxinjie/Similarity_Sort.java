package com.shenxinjie;

import java.util.Arrays;

class nodes implements Comparable<nodes>  {
	public double sim;
	public int th_1, th_2, max_line;
	public String name1, name2;
	public nodes (double ss, int aa, int bb, int ll) {
		sim = ss;
		if (Double.compare(sim, 1.0) > 0)
			sim = 1.0;
		th_1 = aa;
		th_2 = bb;
		max_line = ll;
	}
	public nodes (double ss, String name1, String name2, int x, int y) {
		sim = ss;
		if (Double.compare(sim, 1.0) > 0)
			sim = 1.0;
		this.name1 = name1;
		this.name2 = name2;
		th_1 = x;
		th_2 = y;
	}
	public int compareTo(nodes a) {
		if (Double.compare(this.sim, a.sim) < 0) {
			return 1;
		}else if (Double.compare(this.sim, a.sim) > 0) {
			return -1;
		}
		return 0;
	}
}

public class Similarity_Sort {
	public nodes A[];
	public int num;
	Similarity_Sort(int N) {
		A = new nodes[N];
		num = N;
	}
	public void add_node(int th, double ss, int aa, int bb, int ll) {
		A[th] = new nodes(ss, aa, bb, ll);
	}
	public void add_node(int th, double ss, String name1, String name2, int x, int y) {
		A[th] = new nodes(ss, name1, name2, x, y);
	}
	public void Sim_Sort() {
		Arrays.sort(A);
	}
}
