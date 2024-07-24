import java.io.*;
import java.util.*;

public class Main {

    // BOJ 11286 - 절댓값 힙
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());       
        
        // heap을 구현하기 위한 pq 선언 및 comparator 구현
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// 절댓값이 다르면 절댓값을 비교해서 작은 순서대로 정렬
				if(Math.abs(o1) != Math.abs(o2)) return Math.abs(o1) - Math.abs(o2);
				// 절댓값이 같다면 더 작은 순서대로 정렬
				else return o1 - o2;
			}});
        
        
        for(int i = 0; i < n; i++) {
        	int cur = Integer.parseInt(br.readLine());
        	// 현재 값이 0이라면
        	if(cur == 0) {
        		// pq가 비어있다면 0 추가
        		if(pq.isEmpty()) sb.append("0\n");
        		// 비어있지 않다면 pq에서 poll해서 추가
        		else sb.append(pq.poll()).append("\n");
        	}
        	// 0이 아니라면 pq에 추가
        	else pq.offer(cur);
        }
        
        System.out.println(sb.toString());
    }
}