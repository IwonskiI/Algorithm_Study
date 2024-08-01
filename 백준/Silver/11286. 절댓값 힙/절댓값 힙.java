import java.io.*;
import java.util.*;

public class Main {

    // BOJ 11286 - 절댓값 힙
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());        
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) != Math.abs(o2)) return Math.abs(o1) - Math.abs(o2);
				else return o1 - o2;
			}});
        
        for(int i = 0; i < n; i++) {
        	int cur = Integer.parseInt(br.readLine()); 
        	if(cur == 0) {
        		if(pq.isEmpty()) sb.append("0\n");
        		else sb.append(pq.poll()).append("\n");
        	}
        	else pq.offer(cur);
        }
        
        System.out.println(sb.toString());
    }
}