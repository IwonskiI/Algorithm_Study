import java.io.*;

public class Main {

	// BOJ 1003 - 피보나치 함수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 결과를 계산할 dp 배열 (0~90)
		long[] dp = new long[91];
		// 초기값 입력
		dp[0] = 0;
		dp[1] = 1;
		
		// dp 계산
		for(int i = 2; i <= 90; i++) {
			// 피보나치 수 계산
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		// N번째 피보나치 수 계산
		int N = Integer.parseInt(br.readLine());
		
		
		// 전체 결과 출력
		System.out.println(dp[N]);		
	}
}