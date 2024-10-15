import java.io.*;

public class Main {

    // BOJ 9625 - BABBA
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 버튼을 누를 횟수 입력
        int K = Integer.parseInt(br.readLine());
        
        // DP 배열 선언 [버튼을 누른 횟수][0번 인덱스 : A의 개수 / 1번 인덱스 : B의 개수]
        int[][] dp = new int[46][2];
        
        // 초기 값 입력 - 버튼을 한 번도 누르지 않았을 때 A 1개, B 0개
        dp[0][0] = 1;
        dp[0][1] = 0;
        
        // 버튼을 한번 누를 때 마다 값 계산
        // 버튼을 한 번 누를 때 A의 개수는 이전 B의 개수만큼 남아있다.
        // B의 개수는 이전 A의 개수 + B의 개수만큼 남아있다.
        for(int i = 1; i <= 45; i++) {
        	// A의 개수는 이전 B의 개수
        	dp[i][0] = dp[i-1][1];
        	// B의 개수는 이전 A의 개수 + B의 개수
        	dp[i][1] = dp[i-1][0] + dp[i-1][1];
        }
        
        // K번 눌렀을 때의 A의 개수와 B의 개수 계산
        sb.append(dp[K][0]).append(" ").append(dp[K][1]);
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }

}