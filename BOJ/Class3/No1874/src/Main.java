import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 1874 - 스택 수열
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] num_lst = new int[N];
		
		// 결과 값 배열에 저장
		for(int i = 0; i < N; i++) {
			num_lst[i] = Integer.parseInt(br.readLine());
		}
		
		// 1~N까지 숫자를 넣고 뺄 stack 선언
		Deque<Integer> stack = new ArrayDeque<>();
		
		// cnt = 1~N까지 수 , idx = 결과값 인덱스
		int cnt = 1, idx = 0;
		
		// 주어진 수열의 마지막까지 반복하며 확인
		while(idx < N) {
			// 현재 값 저장
			int cur = num_lst[idx];
			
			// cnt가 현재 값보다 작을 경우 stack에 추가
			while(cnt <= cur) {
				stack.offer(cnt);
				// cnt 증가
				cnt++;
				// push했으므로 정답 문자열에 + 추가
				sb.append("+\n");
			}
			
			// 현재 뽑아낼 수 있는 stack의 젤 위 값이 현재 뽑아야할 값(cur)과 같다면
			if(stack.peekLast() == cur) {
				// 마지막 값 pop
				stack.pollLast();
				// pop 했으므로 정답 문자열에 - 추가
				sb.append("-\n");
				// 다음 수열 확인을 위해 idx++
				idx++;
			}
			else {
				// 만약 뽑아내야할 값(cur)이 cnt보다 작아서 더 이상 stack에 추가할 수 없고,
				// 현재 stack에서 pop햇을 때 나오는 값이 cur이 아니라면
				// No 출력 후 종료
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println(sb.toString());
	}
}
