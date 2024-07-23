import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 2164 - 카드2
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 수열을 관리할 큐 구현
		Deque<Integer> dq = new ArrayDeque<>();
		
		// 큐 초기화
		for(int i = 1; i <= n; i++) {
			dq.offer(i);
		}
		
		// 카드를 버릴지, 젤 뒤로 보낼 지 확인할 변수 cnt 선언
		int cnt = 0;
		
		// 큐에 숫자가 한 개만 남을 때 까지 반복
		while(dq.size() != 1) {
			cnt++;
			// 처음(cnt홀수)일 때는 큐에서 버리고,
			if(cnt % 2 != 0) {
				dq.poll();
			}
			// 다음(cnt짝수)일 때는 큐에서 빼서 젤 뒤로 다시 넣어줌.
			else {
				dq.offer(dq.poll());
			}
		}
		
		// 마지막 남은 수 출력
		System.out.println(dq.poll());
	}
}
