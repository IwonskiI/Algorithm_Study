import java.util.*;

class Solution {
    
    public int solution(int n, int[] money) {
        int answer = 0;
        // dp[i] = i원을 만드는 방법의 수
        int[] dp = new int[n+1];
        dp[0] = 1;
        
        // 각 화폐를 순회하면서 만들 수 있는 금액 체크
        for(int coin : money) {
            // i는 화폐부터 시작 (화폐보다 작은 숫자는 어쩌피 못 만듦)
            for(int i = coin; i <= n; i++) {
                // 기존 현재 금액(i원)을 만드는 방법과 i원에서 coin만큼 뺀 금액(i-coin원)을 만드는 방법을 더함
                // 모듈러 연산은 계속 적용
                dp[i] = (dp[i] + dp[i-coin]) % 1000000007;
            }
        }
        
        // n원을 만드는 방법 return
        answer = dp[n];
        
        return answer;
    }
}