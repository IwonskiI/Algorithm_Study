import java.io.*;
import java.util.*;

public class Main {

	// BOJ 2504 - 괄호의 값
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int ans = 0;
		
		String str = br.readLine();
		
		// 괄호를 관리할 Stack
		Deque<Character> dq = new ArrayDeque<>();
		// 계산된 숫자를 관리할 Stack
		Deque<int[]> numq = new ArrayDeque<>();
		// 초기 값 입력(1단계에서의 누적합은 초기에 0)
		numq.offer(new int[] {0, 1});
		// 현재 누적 합 및 단계
		int curSum = 1, curLv = 0;
		
		for(int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			// 열리는 괄호는 stack에 추가 후 단계 증가
			if(cur == '(' || cur == '[') {
				curLv++;
				dq.offer(cur);
			}
			// 2배 닫히는 괄호 일때, 가장 마지막 스택이 2배 열리는 괄호라면
			else if (cur == ')' && !dq.isEmpty() && dq.peekLast() == '(') {
				// 지금까지 계산되어있는 값이 있다면 가져오기
				if(numq.peekLast()[1] == curLv + 1) curSum = numq.pollLast()[0];
				// 괄호를 닫으며 2배해주기
				curSum *= 2;
				// 괄호 제거
				dq.pollLast();
				// 이전에 같은 단계에서 계산한 값이 있다면
				if(numq.peekLast()[1] == curLv) {
					// 해당 값 더하기로 추가
					curSum += numq.pollLast()[0];
				}
				// 현재 단계에 다시 저장
				numq.offer(new int[] {curSum, curLv});
				// 누적합 1로 초기화(배수로 계산하기 때문에 0 불가능)
				curSum = 1;
				// 단계 감소
				curLv--;
			}
			// 3배 괄호도 동일한 로직 적용
			else if(cur == ']' && !dq.isEmpty() && dq.peekLast() == '[') {
				if(numq.peekLast()[1] == curLv + 1) curSum = numq.pollLast()[0];
				curSum *= 3;
				dq.pollLast();
				if(numq.peekLast()[1] == curLv) {
					curSum += numq.pollLast()[0];
				}
				numq.offer(new int[] {curSum, curLv});
				curSum = 1;
				curLv--;
			}
			// 괄호 짝이 맞지 않다면 불가능
			else {
				ans = -1;
				break;
			}
		}
		
		// 불가능이라면 0 출력
		if(ans == -1) ans = 0;
		// 아니라면 마지막으로 저장되어있는 1단계의 합 출력
		else ans = numq.poll()[0];
		System.out.println(ans);
	}

}