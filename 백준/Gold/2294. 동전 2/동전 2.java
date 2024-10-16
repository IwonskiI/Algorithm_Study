import java.io.*;
import java.util.*;

public class Main {

	// BOJ 2294 - 동전 2
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 수를 만들 수 없으면 -1 출력
		int ans = -1;
		
		// 동전 목록 정리
		int[] coin = new int[N+1];
		// dp 배열
		int[][] dp = new int[N+1][K+1];
		
		// 동전 종류 입력
		for(int i = 1; i <= N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		// dp 첫 줄 초기화 (최대값으로 초기화 - 가장 작은 단위인 1로 최대 K를 채울 때 가장 많은 동전 10000개를 쓰므로 10001로 초기화)
		for(int i = 0; i <= K; i++) {
			dp[0][i] = 10001;
		}
		
		// 각 동전에 대해서 dp 계산
		for(int r = 1; r <= N; r++) {
			// c는 금액
			for(int c = 1; c <= K; c++) {
				// 현재 금액을 동전으로 매꿀 수 있으면
				if(c >= coin[r]) {
					// 현재 금액에서 현재 동전 1개를 뺀 값을 맞추는데 필요한 최소 동전 갯수에서 현재 동전 1개만 더한 개수와
					// 이전에 다른 동전들로 현재 금액을 맞추는데 사용된 동전의 개수와 비교해서 더 작은 값을 저장
					dp[r][c] = Math.min(dp[r][c-coin[r]] + 1, dp[r-1][c]);
				}
				// 현재 금액을 동전으로 매꿀 수 없으면
				else {
					// 이전 동전들로 현재 금액을 맞추는데 사용된 동전의 개수 저장
					dp[r][c] = dp[r-1][c];
				}
			}
		}
		
		// 모든 동전을 다 확인한 후, K금액을 맞췄다면
		if(dp[N][K] != 10001) {
			// ans를 해당 개수로 갱신
			ans = dp[N][K];
		}
		
		// ans 출력
		System.out.println(ans);		
	}
}