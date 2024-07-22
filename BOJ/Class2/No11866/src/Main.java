import java.util.*;
import java.io.*;


public class Main {

	// BOJ 11866 - 요세푸스 문제 0
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// dq에 1부터 n까지 초기화
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i = 1; i <= n; i++) {
			dq.offer(i);
		}
		
		// 답을 저장할 ans deque 선언
		Deque<Integer> ans = new ArrayDeque<>();
		
		// k번째 확인 변수
		int cnt = 1;
		
		// dq에 요소가 1개만 남을 때까지 dq에서 poll -> dq로 offer
		// cnt가 k번째라면 dq가 아닌 ans에 offer
		while(dq.size() != 1) {
			if(cnt % k == 0) ans.offer(dq.poll());
			else dq.offer(dq.poll());
			cnt++;
		}
		// 마지막 요소 ans에 offer
		ans.offer(dq.poll());
		
		// 출력 형식에 맞추기
		sb.append("<");
		while(ans.size() != 1) {
			sb.append(ans.poll()).append(", ");
		}
		sb.append(ans.poll()).append(">");
		
		System.out.println(sb.toString());
		
	}
}
