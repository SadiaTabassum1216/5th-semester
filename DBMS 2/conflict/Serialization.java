package conflict;

import java.util.ArrayList;

public class Serialization {

	int N = 100;
	final int WHITE = 0;
	final int GREY = 1;
	final int BLACK = 2;

	int[][] graph = new int[N][N];
	int[] color = new int[N + 1];
	int[] prev = new int[N + 1];
	boolean flag = false;

	public void createGraph(ArrayList<String> input) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = 0;
			}
		}

		for (int i = 0; i < input.size(); i++) {
			for (int j = i + 1; j < input.size(); j++) {
				String a = input.get(i);
				String b = input.get(j);
				if ((a.charAt(3) == b.charAt(3)) && (a.charAt(1) != b.charAt(1))) {
					if ((a.charAt(0) == 'R' && b.charAt(0) == 'W') || (a.charAt(0) == 'W' && b.charAt(0) == 'R')
							|| (a.charAt(0) == 'W' && b.charAt(0) == 'W')) {

						int u = Integer.parseInt(String.valueOf(a.charAt(1)));
						int v = Integer.parseInt(String.valueOf(b.charAt(1)));
						graph[u][v] = 1;
					}
				}
			}
		}
	}

	public boolean DFS() {
		for (int u = 0; u < N; u++) {
			color[u] = WHITE;
			prev[u] = -1;
		}

		for (int u = 0; u < N; u++) {
			if (color[u] == WHITE) {
				dfsVisit(u);
			}
		}
		return flag;
	}

	public void dfsVisit(int u) {
		color[u] = GREY;
		for (int v = 0; v < N; v++) {
			if (graph[u][v] == 1 && color[v] == WHITE) {
				prev[v] = u;
				dfsVisit(v);
			} 
			else if (graph[u][v] == 1 && color[v] == GREY) {
				flag = true;
			}
		}
		color[u] = BLACK;
	}

}
