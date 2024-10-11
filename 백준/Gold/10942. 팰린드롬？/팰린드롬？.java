import java.io.*;
import java.util.*;

public class Main {

	// BOJ 10942 - 팰린드롬?
	public static int N;
	public static int[] lst;
	public static int[][] dp;
	
	public static boolean search(int sp, int ep) {
		// 시작점이 끝점보다 뒤로 가서 역전되었으면
		if(sp > ep) {
			// 현재 두 지점이 같으면 팰린드롬수
			if(lst[sp] == lst[ep]) {
				// 1 표시
				dp[ep][sp] = 1;
				// 팰린드롬
				return true;
			}
			// 두 지점이 다르면 팰린드롬수 아님
			else {
				// -1 표시
				dp[ep][sp] = -1;
				// 팰린드롬 아님
				return false;
			}
		}
		
		// 이미 계산한 값이 있다면
		if(dp[sp][ep] != 0) {
			// 팰린드롬 가능하면 true
			if(dp[sp][ep] == 1) return true;
			// 아니면 false
			else return false;
		}
		
		// 계산한적이 없다면
		else {
			//시작점과 끝점이 같고, 중간이 팰린드롬 수라면
			if(lst[sp] == lst[ep] && search(sp+1, ep-1)) {
				// 1 표시
				dp[sp][ep] = 1;
				// 팰린드롬
				return true;
			}
			// 아니라면
			else {
				// -1 표시
				dp[sp][ep] = -1;
				// 팰린드롬 아님
				return false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		lst = new int[N];
		dp = new int[N][N];
		
		// 길이가 1인 숫자는 무조건 팰린드롬 수 
		for(int i = 0; i < N; i++) {
			dp[i][i] = 1;
		}
		
		// 초기 수열 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
		}
		
		// 팰린드롬수 테스트
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 시작점과 끝점 입력 (인덱스는 0부터라서 -1 처리)
			int sp = Integer.parseInt(st.nextToken()) - 1, ep = Integer.parseInt(st.nextToken()) - 1;
			
			// 팰린드롬 수 검증
			boolean res = search(sp, ep);
			
			// 팰린드롬 수라면 1 추가
			if(res) sb.append("1\n");
			// 아니라면 0 추가
			else sb.append("0\n");
		}
		// 최종 결과 출력
		System.out.println(sb.toString());		
	}
}