import java.io.*;
import java.util.*;

public class Main {

	// BOJ 12865 - 평범한 배낭
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 물건 개수 N, 최대 무게 K
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// weight : 무게, value : 가치
		int[] weight = new int[N+1], value = new int[N+1];
		// dp 배열
		int[][] dp = new int[N+1][K+1];
		
		// 계산의 편리성을 위해 1번 인덱스부터 입력
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		// 0번 열은 물건이 없을 때 기준 (전부 0으로 초기화)
		// 1번 열 (1번 물건) 부터 시작
		for(int n = 1; n <= N; n++) {
			// k는 현재 담을 수 있는 최대 무게
			// 0일때는 담을 수 없으므로 1부터 시작
			for(int k = 1; k <= K; k++) {
				// 현재 물건의 무게가 현재 담을 수 있는 무게보다 커서 담지 못한다면
				if(weight[n] > k) {
					// 이전까지 담은 최대 값을 그대로 가져옴
					dp[n][k] = dp[n-1][k];
				}
				// 현재 물건의 무게를 가방에 담을 수 있다면
				else {
					// 현재 물건을 선택하지 않은 경우 (이전까지 물건을 담은 최댓값)과
					// 현재 물건의 무게를 제외한 나머지 무게로 채울 수 있었던 가장 높은 가치와 현재 물건의 가치를 더한 값을 비교
					// 둘 중에 더 큰 값을 저장
					dp[n][k] = Math.max(dp[n-1][k], dp[n-1][k-weight[n]] + value[n]);
				}
			}
		}
		
		// 가치의 최댓값 출력
		System.out.println(dp[N][K]);
		
	}

}
