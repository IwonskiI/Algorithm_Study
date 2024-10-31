import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 6603 - 로또
	public static int K;
	public static int[] lst, selected = new int[6];
	public static boolean[] visited;
	public static StringBuilder sb = new StringBuilder();
	
	// 조합 생성
	public static void combi(int cnt, int start) {
		// 6개를 뽑았다면
		if(cnt == 6) {
			for(int i = 0; i < 6; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 6개를 뽑기
		for(int i = start; i < K; i++) {
			// 현재 숫자 입력
			selected[cnt] = lst[i];
			// 다음 숫자 선택 진행
			combi(cnt+1, i+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			// 숫자 개수 K
			K = Integer.parseInt(st.nextToken());
			
			// 0 입력 시 종료
			if(K == 0) break;
			
			// 숫자 입력
			lst = new int[K];
			for(int i = 0; i < K; i++) {
				lst[i] = Integer.parseInt(st.nextToken());
			}
			
			// 조합 생성
			combi(0, 0);
			// 케이스별 빈 줄 입력
			sb.append("\n");
		}
		
		// 최종 결과 출력
		System.out.println(sb.toString());
	}
}