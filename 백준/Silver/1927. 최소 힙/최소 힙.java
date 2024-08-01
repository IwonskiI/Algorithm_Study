import java.util.*;
import java.io.*;


public class Main {

	// BOJ 1927 - 최소 힙
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) { 
				Integer cur = pq.poll();
				if(cur == null) sb.append("0\n");
				else sb.append(cur).append("\n");
				
			}
			else 
				pq.add(num);
		}
		
		System.out.println(sb.toString());

	}

}
