import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 9012 - 괄호
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			// 괄호 문자열을 받아옴
			String[] ps = br.readLine().split("");
			
			// 괄호를 담을 stack 구현 (Deque활용)
			Deque<Integer> dq = new ArrayDeque<>();
			
			// 정상적인 ps 문자열인지 확인하는 boolean 변수 선언
			boolean flag = true;
			
			for(int j = 0; j < ps.length; j++) {
				// '(' 괄호라면 stack에 1추가
				if(ps[j].equals("(")) dq.offer(1);
				// ')' 괄호라면
				else if(ps[j].equals(")")) {
					// 만약 더이상 뽑아낼 '('괄호(1)가 없다면 잘못된 문자열
					// flag를 false로하고 반복문 break
					if(dq.isEmpty()) {
						flag = false;
						break;
					}
					// 뽑아낼 '('괄호 (1)이 있다면 뽑고 계속
					else dq.poll();
				}
			}
			// 모든 문자열을 확인한 뒤, 제거되지 않은 '('괄호 (1)이 남았다면 잘못된 문자열
			if(dq.size() != 0) flag = false;
			
			// flag에 따라 결과 추가
			if(flag) sb.append("YES\n");
			else sb.append("NO\n");
		}
		
		System.out.println(sb.toString());
	}

}
