import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 2606 - 바이러스 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		// 노드를 키값으로 사용하고, 연결되어있는 노드들을 리스트로 관리
		Map<Integer, List<Integer>> graph = new HashMap<>();
		
		int k = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			// 노드 번호를 map에서 조회한 뒤, 값이 없다면 빈 리스트를 가져옴
			List<Integer> lst1 = graph.getOrDefault(num1, new ArrayList<Integer>());
			List<Integer> lst2 = graph.getOrDefault(num2, new ArrayList<Integer>());
			
			// 리스트에 연결된 노드 추가 (양방향)
			lst1.add(num2);
			lst2.add(num1);
			// graph에 리스트 추가
			graph.put(num1, lst1);
			graph.put(num2, lst2);
		}
		
		// 해당 노드를 방문했는지 체크할 배열
		boolean[] virus = new boolean[N+1];
		int ans = 0;
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(1);
		virus[1] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			// 방문한 노드와 연결된 노드가 있는지 리스트를 받아옴
			List<Integer> cur_lst = graph.getOrDefault(cur, new ArrayList<Integer>());
			
			// 리스트를 순회하며 방문 전 노드라면 q에 추가하고 ans를 증가 및 방문 체크
			for(int num : cur_lst) {
				if(!virus[num]) {
					q.offer(num);
					virus[num] = true;
					ans++;
					
				}
			}
		}
		
		System.out.println(ans);
	}
}
