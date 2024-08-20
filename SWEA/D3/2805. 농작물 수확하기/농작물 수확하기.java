import java.io.*;

public class Solution {
	// SWEA 2805 - 농작물 수확하기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 1024);
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] board = new int[N][N];
			// 농작물 가치 입력
			for(int r = 0; r < N; r++) {
				String[] st = br.readLine().split("");
				for(int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st[c]);
				}
			}
			
			// 각 열의 시작점과 끝점, 총 가치 합
			int sp = N/2, ep = N/2, ans = 0;
			// 각 열마다 반복
			for(int i = 0; i < N; i++) {
				// 각 열의 시작점부터 끝점까지 가치 더하기
				for(int j = sp; j <= ep; j++) {
					ans += board[i][j];
				}
				// 절반이 지나기 전이라면 시작점 감소 끝점 증가
				if(i < N/2) {
					sp--;
					ep++;
				}
				// 절반 이후에는 시작점 증가 끝점 감소
				else {
					sp++;
					ep--;
				}
			}
			
			// 최종 가치 출력
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}