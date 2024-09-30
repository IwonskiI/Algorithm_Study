import java.util.*;
import java.io.*;


public class Solution {

    // SWEA 3282 - 0/1 Knapsack
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			// 무게를 담을 w, 가치를 담을 v
			int[] w = new int[N+1], v = new int[N+1];
			
			// 초기 입력
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				w[i] = Integer.parseInt(st.nextToken());
				v[i] = Integer.parseInt(st.nextToken());
			}
			
			// dp 배열
			int[][] dp = new int[N+1][K+1];
			
			// dp배열 채우기
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= K; j++) {
					// 물건을 넣을 수 있다면 
					if(w[i] <= j)
						// 물건을 넣었을 때와 넣지 않았을 때 중 더 큰 것을 저장
						dp[i][j] = Math.max(dp[i-1][j-w[i]] + v[i], dp[i-1][j]);
					
					// 못 넣는다면 이전 값 가져오기
					else dp[i][j] = dp[i-1][j];
				}
			}
			
			// 마지막 값 저장
			sb.append("#").append(tc).append(" ").append(dp[N][K]).append("\n");
		}
		
		// 최종 결과 출력
		System.out.println(sb.toString());

	}

}