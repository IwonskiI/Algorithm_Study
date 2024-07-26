import java.util.*;
import java.io.*;


public class Main {

	// BOJ 1927 - 최소 힙
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		// minheap을 구현할 Priority Queue 구현
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) { // num이 0이라면 pq에서 poll하기
				
				// pq가 비어있을 때 null을 반환하므로 참조형인 Integer형 변수 선언
				Integer cur = pq.poll();
				
				// cur이 null이라면 0 추가
				if(cur == null) sb.append("0\n");
				// null이 아니라면 cur 추가
				else sb.append(cur).append("\n");
			}
			
			else // num이 0이 아니라면 pq에 추가
				pq.add(num);
		}
		
		System.out.println(sb.toString());

	}

}
