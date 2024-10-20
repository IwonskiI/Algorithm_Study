import java.io.*;

public class Main {

    // BOJ 9252 - LCS 2
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str1 = br.readLine();
        String str2 = br.readLine();
        
        // 각 문자열의 길이
        int len1 = str1.length(), len2 = str2.length();
        
        // 두 문자열 중 빈 문자열이 있다면
        if(len1 == 0 || len2 == 0) {
        	// 0 출력 후 종료
        	System.out.println(0);
        	return;
        }
        
        // LCS의 길이를 구할 cntDp 배열과 그 때의 LCS를 저장할 strDp 배열
        int[][] dp = new int[len1+1][len2+1];
        
        for(int i = 1; i <= len1; i++) {
        	for(int j = 1; j <= len2; j++) {
        		if(str1.charAt(i-1) == str2.charAt(j-1))
        			dp[i][j] = dp[i-1][j-1] + 1;
        		else
        			dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        	}
        }
        
        // 최종 결과 값에서 LCS의 길이를 구한 뒤 출력
        int ans = dp[len1][len2];
        System.out.println(ans);
        // 길이가 0이 아니라면 strDp배열에 있는 LCS 문자열도 출력
        if(ans != 0) {
        	int posR = len1, posC = len2;
        	StringBuilder sb = new StringBuilder();
        	while(posR >= 1 && posC >= 1) {
    	       if (dp[posR][posC] > dp[posR - 1][posC] && dp[posR - 1][posC] == dp[posR][posC - 1] && dp[posR][posC-1] == dp[posR - 1][posC-1]) {
    	            sb.append(str2.charAt(posC-1));
    	            posR--;
    	            posC--;
    	        } else if (dp[posR - 1][posC] > dp[posR][posC-1] && dp[posR - 1][posC] == dp[posR][posC]) {
    	        	posR--;
    	        } else if (dp[posR - 1][posC] < dp[posR][posC-1] && dp[posR][posC-1] == dp[posR][posC]) {
    	        	posC--;
    	        }
    	        else {
    	        	posC--;
    	        }
        	}
        	System.out.println(sb.reverse().toString());
        }
    }
}