import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 10828 - 스택
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 주어진 명령의 수 범위가 1 <= N <= 10000 이므로 모든 명령이 push일 것을 대비하여 배열을 10001칸으로 선언 
		int[] stack = new int[10001];
		// stack의 현재 위치를 나타내 줄 cur 변수 선언
		int cur = -1;
		
		// 명령의 갯수 n 입력
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			// 명령 입력
			String op = st.nextToken();
			switch(op) {
			case "push":
				// push라면 넣을 숫자 입력
				int num = Integer.parseInt(st.nextToken());
				// stack의 현재 위치 다음에 숫자 입력
				stack[++cur] = num;
				break;
			case "pop":
				// 만약 stack이 비어있다면 -1 출력
				if(cur == -1) sb.append(-1).append("\n");
				// stack안에 요소가 있다면 요소를 출력한 뒤 cur 감소
				else sb.append(stack[cur--]).append("\n");
				break;
			case "size":
				// 현재 위치 + 1로 크기 출력
				sb.append(cur+1).append("\n");
				break;
			case "empty":
				// stack이 비어있다면(현재 위치가 -1이라면) 1 출력
				if(cur == -1) sb.append("1\n");
				// stack이 비어있지 않다면 0 출력
				else sb.append("0\n");
				break;
			case "top":
				// 만약 stack이 비어있다면 -1 출력
				if(cur == -1) sb.append(-1).append("\n");
//				stack안에 요소가 있다면 요소를 출력
				else sb.append(stack[cur]).append("\n");
				break;
			}
		}
		System.out.println(sb.toString());
	}

}
