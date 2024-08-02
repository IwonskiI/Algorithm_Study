import java.util.*;
import java.io.*;


public class Main {

	// BOJ 11725 - 트리의 부모 찾기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// tree 연결을 관리할 map 선언
		Map<Integer, List<Integer>> tree = new HashMap<>();
		
		int N = Integer.parseInt(br.readLine());
		
		// 간선 추가
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			List<Integer> lst1 = tree.getOrDefault(num1, new LinkedList<Integer>());
			List<Integer> lst2 = tree.getOrDefault(num2, new LinkedList<Integer>());
			lst1.add(num2);
			lst2.add(num1);
			tree.put(num1, lst1);
			tree.put(num2, lst2);
		}
		
		// 부모 번호를 저장할 배열 선언
		int[] parent = new int[N+1];
		
		// 트리 순회를 위한 dq
		Deque<Integer> dq = new ArrayDeque<>();
		dq.offer(1);
		
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			// 현재 노드에 연결된 노드가 담긴 리스트를 map에서 가져온 뒤,
			List<Integer> cur_lst = tree.getOrDefault(cur, new LinkedList<Integer>());
			for(int n : cur_lst) {
				// 아직 방문전(부모가 설정되기 전)이라면 q에 추가해주고 부모를 현재 cur값으로 설정한다.
				if(parent[n]==0) {
					dq.offer(n);
					parent[n] = cur;
				}
			}
		}
		
		// 2번 노드부터 차례로 부모 번호를 출력한다.
		for(int i = 2; i <= N; i++) {
			sb.append(parent[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
