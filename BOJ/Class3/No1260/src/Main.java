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
		
		// DFS 탐색
		Deque<Integer> stack  = new ArrayDeque<>();
		// 시작 노드 stack추가
		stack.offerLast(v);
		while(!stack.isEmpty()) {
			int cur = stack.pollLast();
			// 이미 방문한 노드라면 skip
			if(v_dfs[cur-1]) continue;
			// 아니라면 방문 처리
			v_dfs[cur-1] = true;
			sb.append(cur).append(" ");
			
			// 연결된 간선 체크
			List<Integer> cur_lst = graph.getOrDefault(cur, new LinkedList<Integer>());
			// 정렬
			Collections.sort(cur_lst, (o1, o2) -> o2 - o1);
			// 방문하지 않은 노드 추가
			for(int i : cur_lst) {
				if(!v_dfs[i-1]) {
					stack.offerLast(i);
				}
			}
		}
		// DFS 끝
		sb.append("\n");
		
		// BFS 탐색
		Deque<Integer> q = new ArrayDeque<>();
		// 시작 노드 queue추가
		q.offer(v);
		// 시작 점 방문 체크
		v_bfs[v-1] = true;
		sb.append(v).append(" ");
		while(!q.isEmpty()) {
			int cur = q.poll();
			// 연결된 간선 체크
			List<Integer> cur_lst = graph.getOrDefault(cur, new LinkedList<Integer>());
			// 정렬
			Collections.sort(cur_lst);
			// 방문하지 않은 노드 추가
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
