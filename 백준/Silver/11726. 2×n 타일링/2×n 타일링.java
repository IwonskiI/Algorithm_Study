import java.io.*;


public class Main {
	
	// BOJ 11726 - 2xn 타일링
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[1010];
		
		// 가장 마지막에 오는 타일을 기준으로 생각했을 때, 가능한 경우는 n-1개를 채운 뒤, 세로로 1개를 세운 1가지와
		// n-2를 채운 뒤 가로로 두개를 눕혀 놓은 경우 1가지를 더하는 경우밖에 없다.
		// 따라서 n-1까지 채우는 경우와 n-2까지 채우는 경우를 구해서 더해주면 된다.
		// bottom-up 방식을 사용하기 위해 가장 기본이 되는 dp[1]의 경우와 dp[2]의 경우는 초기화 해주고 n까지 차례로 더해서 올라간다.
		
		// 2*1을 채울 수 있는 경우는 세로로 세운 1가지
		dp[1] = 1;
		
		// 2*2를 채울 수 있는 경우는 세로로 일렬로 세운 경우와 가로로 쌓은 경우 2가지
		dp[2] = 2;
		
		// 10007로 나눈 나머지를 저장해야하므로 나머지를 dp배열에 저장한다.
		for(int i = 3; i <= n; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		
		// dp[n]을 구하면 답
		System.out.println(dp[n]);

	}

}