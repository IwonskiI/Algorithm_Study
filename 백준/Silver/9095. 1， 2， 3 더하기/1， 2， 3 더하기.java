import java.io.*;

public class Main {
	
	// BOJ 9095 - 1,2,3 더하기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		// 인덱스가 그 수를 나타내기 위한 합의 경우의 수
		int[] dp = new int[11];
		dp[1] = 1; // 1 -> 1가지
		dp[2] = 2; // 1+1, 2 -> 2가지
		dp[3] = 4; // 1+1+1, 1+2, 2+1, 3 -> 4가지
		
		// 4부터는 바로 전 경우의 수에 1을 더한 경우와, 전전 경우에 2를 더한 경우와 전전전 경우에 3을 더한경우의 합과 같음
		for(int i = 4; i <= 10; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		
		// 테스트 케이스를 입력 받고 주어진 수 계산
		for(int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append("\n");
		}
		
		// 최종 결과 출력
		System.out.println(sb.toString());
	}

}