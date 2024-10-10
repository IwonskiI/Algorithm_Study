import java.io.*;
import java.util.*;

public class Main {

	// BOJ 23309 - 철도 공사
	public static int N, M;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 인덱스 번호의 역의 이전역과 다음역의 고유번호를 저장
		int[] prev_lst = new int[1000001];
		int[] next_lst = new int[1000001];
		
		st = new StringTokenizer(br.readLine());
		int start = -1, prv = -1, cur = -1;
		for(int i = 0; i < N; i++) {
			cur = Integer.parseInt(st.nextToken());
			// 제일 처음 입력되는 역은 따로 저장
			if(i == 0) {
				start = prv = cur;
			}
			// 두번째 역 부터는 이전역의 정보를 사용해서 역 정보 저장
			else {
				// 현재역의 이전역 정보를 prev_lst에 저장
				prev_lst[cur] = prv;
				// 이전역의 다음역 정보를 next_lst에 저장
				next_lst[prv] = cur;
				// 현재 역을 이전역으로 설정하고 반복
				prv = cur;
			}
		}
		// 순환하기 때문에 마지막 역이 시작역의 이전 역이 되고, 
		prev_lst[start] = cur;
		// 시작역이 마지막역의 다음역이 됨
		next_lst[cur] = start;
		
		// 주어진 커맨드 실행
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String com = st.nextToken();
			int a, b, next, prev;
			switch(com) {
			// a역의 다음역을 출력하고 그 사이에 b역 건설
			case "BN":
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				// a역의 다음역 next 구하기
				next = next_lst[a];
				// next 출력
				sb.append(next).append("\n");
				// b역의 이전역은 a역
				prev_lst[b] = a;
				// a역의 다음역은 b역
				next_lst[a] = b;
				// next역의 이전역은 b역
				prev_lst[next] = b;
				// b역의 다음역은 next역
				next_lst[b] = next;
				break;
			// a역의 이전역을 출력하고 그 사이에 b역 건설
			case "BP":
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				// a역의 이전역 prev 구하기
				prev = prev_lst[a];
				// prev 출력
				sb.append(prev).append("\n");
				// a역의 이전역은 b역
				prev_lst[a] = b;
				// b역의 다음역은 a역
				next_lst[b] = a;
				// b역의 이전역은 prev역
				prev_lst[b] = prev;
				// prev역의 다음역은 b역
				next_lst[prev] = b;
				break;
			// a역의 이전역을 폐쇄하고 그 이전역과 a역을 연결
			case "CP":
				a = Integer.parseInt(st.nextToken());
				// a역의 이전역 prev 구하기
				prev = prev_lst[a];
				// prev 출력
				sb.append(prev).append("\n");
				// prev의 이전 역 b 구하기
				b = prev_lst[prev];
				
				// prev역 폐쇄 - 연결고리 다 끊음 
				prev_lst[prev] = 0;
				next_lst[prev] = 0;
				// a역과 b역 연결
				prev_lst[a] = b;
				next_lst[b] = a;
				break;
			// a역의 다음역을 폐쇄하고 그 다음역과 a역을 연결
			case "CN":
				a = Integer.parseInt(st.nextToken());
				// a역의 다음역 next 구하기
				next = next_lst[a];
				// next 출력
				sb.append(next).append("\n");
				// next의 다음역 b 구하기
				b = next_lst[next];
				
				// next역 폐쇄 - 연결고리 다 끊음
				prev_lst[next] = 0;
				next_lst[next] = 0;
				// a역과 b역 연결
				prev_lst[b] = a;
				next_lst[a] = b;
				break;
			}
		}
		
		// 저장된 결과 출력
		System.out.println(sb.toString());
		
	}

}