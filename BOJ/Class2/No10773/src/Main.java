import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 10773 - 제로
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		
		// stack에 수 저장
		Deque<Integer> stack = new ArrayDeque<>();
		
		for(int i = 0; i < k; i++) {
			// 입력받은 수 저장
			int cur = Integer.parseInt(br.readLine());
			
			// 입력받은 수가 0이라면 pop(pollLast)
			if(cur == 0) stack.pollLast();
			// 아니라면 push(offer)
			else stack.offer(cur);
		}
		
		// 합을 구할 변수 sum 선언
		int sum =  0;
		while(!stack.isEmpty()) {
			// stack이 빌 때까지 pop하며 sum에 추가
			sum += stack.poll();
		}
		
		System.out.println(sum);
	}

}
