class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] dp = new int[n+1];
        // dp 배열 초기화
        dp[0] = 1;
        // dp 배열 계산
        // i번째 : (A)B 형태에서 A에서 j개의 괄호를 사용하면, B에서 i-j-1(A를 감싸는 괄호 1개 사용해서 1 더빼줌)개의 괄호를 사용
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        answer = dp[n];
        return answer;
    }
}