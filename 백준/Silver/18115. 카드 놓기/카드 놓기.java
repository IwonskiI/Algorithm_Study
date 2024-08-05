import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 18115 - 카드 놓기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 손 패를 관리할 dq 선언
		Deque<Integer> dq = new ArrayDeque<>();
		
		int N = Integer.parseInt(br.readLine());
		// 기술을 사용한 반대로해서 바닥에 내려놓은 카드를 다시 줍는 방식으로 계산
		StringBuilder sb1 = new StringBuilder(br.readLine()).reverse();
		st = new StringTokenizer(sb1.toString());
		// 1번 카드부터 역으로 계산
		int cnt = 1;
		
		// 각 기술에 맞는 방향대로 dq에 넣어준다.
		for(int i = 0; i < N; i++) {
			int skill = Integer.parseInt(st.nextToken());
			switch(skill) {
			// 가장 위의 카드를 바닥에 내려놨으므로, 바닥의 카드(현재 cnt)를 dq의 가장 위(뒤)에 넣어준다.
			case 1:
				dq.offer(cnt++);
				break;
			// 위에서 두 번째 카드를 바닥에 내려놨으므로, 바닥의 카드(현재 cnt)를 dq의 가장 위 카드를 빼고 넣어준뒤,
			// 다시 가장 위 카드를 넣어준다.
			case 2:
				int temp = dq.pollLast();
				dq.offer(cnt++);
				dq.offer(temp);
				break;
			// 가장 아래의 카드를 바닥에 내려놨으므로, 바닥의 카드(현재  cnt)를 dq의 가장 아래에 넣어준다.
			case 3:
				dq.offerFirst(cnt++);
				break;
			}
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(dq.pollLast()).append(" ");
		}
		System.out.println(sb.toString());
	}
}
