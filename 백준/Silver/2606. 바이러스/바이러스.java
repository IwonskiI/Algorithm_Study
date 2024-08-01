import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 2606 - 바이러스 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Map<Integer, List<Integer>> graph = new HashMap<>();
		int k = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			List<Integer> lst1 = graph.getOrDefault(num1, new ArrayList<Integer>());
			List<Integer> lst2 = graph.getOrDefault(num2, new ArrayList<Integer>());
			lst1.add(num2);
			lst2.add(num1);
			graph.put(num1, lst1);
			graph.put(num2, lst2);
		}
		
		boolean[] virus = new boolean[N+1];
		int ans = 0;
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(1);
		virus[1] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			List<Integer> cur_lst = graph.getOrDefault(cur, new ArrayList<Integer>());
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
