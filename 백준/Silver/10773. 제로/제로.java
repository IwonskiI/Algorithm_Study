import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 10773 - 제로
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i = 0; i < k; i++) {
			int cur = Integer.parseInt(br.readLine());
			if(cur == 0) stack.pollLast();
			else stack.offer(cur);
		}
		
		int sum =  0;
		while(!stack.isEmpty()) {
			sum += stack.poll();
		}
		
		System.out.println(sum);
	}

}
