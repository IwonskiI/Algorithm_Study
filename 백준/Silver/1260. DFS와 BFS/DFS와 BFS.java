import java.util.*;
import java.io.*;

public class Main {
	
	// BOJ 1260 - DFS와 BFS
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		// 간선 연결을 관리할 graph, 방문처리를 확인할 v_bfs, v_dfs
		Map<Integer, List<Integer>> graph = new HashMap<>();
		boolean[] v_bfs = new boolean[n];
		boolean[] v_dfs = new boolean[n];
		
		// 양방향 간선 연결
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			List<Integer> list1 = graph.getOrDefault(n1, new LinkedList<Integer>());
			List<Integer> list2 = graph.getOrDefault(n2, new LinkedList<Integer>());
			list1.add(n2);
			list2.add(n1);
			graph.put(n1, list1);
			graph.put(n2, list2);
		}
		
		Deque<Integer> stack  = new ArrayDeque<>();
		stack.offerLast(v);
		while(!stack.isEmpty()) {
			int cur = stack.pollLast();
			if(v_dfs[cur-1]) continue;
			v_dfs[cur-1] = true;
			sb.append(cur).append(" ");
			List<Integer> cur_lst = graph.getOrDefault(cur, new LinkedList<Integer>());
			Collections.sort(cur_lst, (o1, o2) -> o2 - o1);
			for(int i : cur_lst) {
				if(!v_dfs[i-1]) {
					stack.offerLast(i);
				}
			}
		}
		sb.append("\n");
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(v);
		v_bfs[v-1] = true;
		sb.append(v).append(" ");
		while(!q.isEmpty()) {
			int cur = q.poll();
			List<Integer> cur_lst = graph.getOrDefault(cur, new LinkedList<Integer>());
			Collections.sort(cur_lst);
			for(int i : cur_lst) {
				if(!v_bfs[i-1]) {
					q.offer(i);
					v_bfs[i-1] = true;
					sb.append(i).append(" ");
				}
			}
		}
		
		System.out.println(sb.toString());
	}

}
