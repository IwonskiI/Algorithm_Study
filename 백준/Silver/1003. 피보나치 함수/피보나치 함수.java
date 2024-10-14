import java.io.*;

public class Main {

	// BOJ 1003 - 피보나치 함수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 결과를 계산할 dp 배열 (0~40), 0번 인덱스에는 0의 개수, 1번 인덱스에는 1의 개수
		int[][] dp = new int[41][2];
		// 초기값 입력
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		// dp 계산
		for(int i = 2; i <= 40; i++) {
			// 0의 개수는 이전 두 수의 0의 개수의 합
			dp[i][0] = dp[i-1][0] + dp[i-2][0];
			// 1의 개수는 이전 두 수의 1의 개수의 합 
			dp[i][1] = dp[i-1][1] + dp[i-2][1];
		}
		
		// 테스트케이스
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			// N을 입력
			int N = Integer.parseInt(br.readLine());
			// dp배열에서 N에 저장된 값 가져오기
			sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
		}
		
		// 전체 결과 출력
		System.out.println(sb.toString());		
	}
}