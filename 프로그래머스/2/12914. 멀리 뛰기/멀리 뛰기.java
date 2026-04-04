class Solution {
    public long solution(int n) {
        long answer = 0;
        long[] dp = new long[2001];
        dp[1] = 1; dp[2] = 2;
        if(n < 3) return dp[n];
        
        for(int i = 3; i <= n; i++) {
            dp[i] = (dp[i-2] % 1234567) + (dp[i-1] % 1234567);
        }
        answer = dp[n] % 1234567;
        return answer;
    }
}