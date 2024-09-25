import java.io.*;

public class Main {
	
	// BOJ 9251 - LCS
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 문자열 입력
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		// 문자열의 길이 계산
		int len1 = str1.length(), len2 = str2.length();
		
		// 문자열을 비교할 2차원 dp 배열
		int[][] dp = new int[len1+1][len2+1];
		
		// 각 자리를 순서대로 비교
		for(int i = 1; i <= len1; i++) {
			// 첫번째 문자열의 i번째 자리 글자
			char char1 = str1.charAt(i-1);
			for(int j = 1; j <= len2; j++) {
				// 두번째 문자열의 j번째 자리 글자
				char char2 = str2.charAt(j-1);
				// 두 문자가 같다면 두 문자가 더해지기 이전 문자열까지의 최대값에 +1
				if(char1 == char2) dp[i][j] = dp[i-1][j-1] + 1;
				// 두 문자가 다르다면 각 문자를 더하기 전 문자열까지의 최댓값 중 더 큰 것 저장
				else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
			}
		}
		// 마지막 위치에 저장된 값 출력(최댓값)
		sb.append(dp[len1][len2]);
		// 최종 결과 출력
		System.out.println(sb.toString());
	}

}