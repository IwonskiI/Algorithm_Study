import java.util.*;
import java.io.*;

public class Main {
	
	// BOJ 1021 - 회전하는 큐
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 찾을 수를 저장할 list
		int[] lst = new int[M];
		
		// 2번 연산과 3번 연산을 할 덱 선언
		Deque<Integer> q1 = new ArrayDeque<>();
		Deque<Integer> q2 = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		
		// 찾을 수 입력
		for(int i = 0; i < M; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
		}
		
		// 1부터 N까지 덱에 삽입
		for(int i = 1; i <= N; i++) {
			q1.offer(i);
			q2.offer(i);
		}
		
		// 최소 횟수
		int ans = 0;
		
		// M개의 숫자 찾기
		for(int i = 0; i < M; i++) {
			// 2번연산의 횟수 cnt1, 3번 연산의 횟수 cnt2
			int cnt1 = 0, cnt2 = 0;
			
			// q1에서 찾을 수가 나올 때까지 2번 연산 반복
			while(!(q1.peek() == lst[i])){
				cnt1++;
				q1.offer(q1.poll());
			}
			// q2에서 찾을 수가 나올 때까지 3번 연산 반복			
			while(!(q2.peek() == lst[i])){
				cnt2++;
				q2.offerFirst(q2.pollLast());
			}
			
			// 둘 중 작은 값의 결과로 저장하기
			
			// cnt1이 작으면 q1의 결과로 저장
			if(cnt1 <= cnt2) {
				// q2 초기화
				q2 = new ArrayDeque<>();
				// 정답 횟수에 cnt1 더하기
				ans += cnt1;
				// 찾는 수 뽑아서 제거
				q1.poll();
				// 기존 값 복사
				for(int j = 0; j < (N-1-i); j++) {
					int temp = q1.poll();
					q1.offer(temp);
					q2.offer(temp);
				}
			}
			// cnt2가 작으면 q2의 결과로 저장
			else {
				// q1 초기화
				q1 = new ArrayDeque<>();
				// 정답 횟수에 cnt2 더하기
				ans += cnt2;
				// 찾는 수 뽑아서 제거
				q2.poll();
				// 기존 값 복사
				for(int j = 0; j < (N-1-i); j++) {
					int temp = q2.poll();
					q1.offer(temp);
					q2.offer(temp);
				}
			}
		}
		
		// 정답 출력
		System.out.println(ans);
	}

}