import java.io.*;
import java.util.*;

public class Main {

    // BOJ 2293 - 동전 1
    public static int N, K, ans;
    public static int[] lst;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = 0;
        lst = new int[N];
        
        // 경우의 수를 저장할 dp배열 N열까지의 코인을 사용했을 때 K열의 금액을 만들 수 있는 경우의 수
        int[][] dp = new int[N][K+1];
        
        for(int i = 0; i < N; i++) {
        	lst[i] = Integer.parseInt(br.readLine());
        }
        
        // 0개의 코인으로 0원을 만들 수 있는 경우의 수 1가지
        dp[0][0] = 1;
        // 첫 줄 초기화
        for(int i = 1; i <= K; i++) {
        	// 현재 코인으로 i원을 만들 수 없다면 0으로 초기화
        	if(i < lst[0]) dp[0][i] = 0;
        	// i - 현재 코인의 값을 만들 수 있었던 경우의 수와 같다 (현재 코인을 1개 더해서 해당 경우의 수와 모두 같음)
    		else {
    			dp[0][i] = dp[0][i-lst[0]]; 
    		} 
        }
        
        // 나머지 줄 계산
        for(int i = 1; i < N; i++) {
        	for(int j = 0; j <= K; j++) {
        		// 현재 코인으로 i원을 만들 수 없다면 이전에 가능했던 경우의 수 그대로 입력
        		if(j < lst[i]) dp[i][j] = dp[i-1][j];
        		// 가능하다면 이전에 가능했던 경우의 수에 i-현재코인을 만들 수 있었던 경우의 수를 더해서 저장
        		else {
        			dp[i][j] = dp[i][j-lst[i]] + dp[i-1][j];
        		}
        	}
        }
        
        // 최종 칸에 저장된 경우의 수 출력
        System.out.println(dp[N-1][K]);        
    }
}