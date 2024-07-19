import java.io.*;

public class Main {

	// BOJ 2775 - 부녀회장이 될테야
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 모든 아파트 입주민 수 구하기 (1 <= k,n <= 14)
		
		// 0층부터 시작하므로 행(층수)만 +1 해서 15, 열(호수)는 14
		int[][] board = new int[15][14];
		
		// 0층, 1호 라인 초기화
		// 0층은 i호에 i명이 살고있고, 1호는 아래층의 1호 사람들의 수랑 같으므로 모두 1명
		for(int i = 0; i < 14; i++) {
			board[0][i] = i+1;
			board[i][0] = 1;
		}
		board[14][0] = 1;
		
		//모든 호수 입주민 수 구하기
		for(int i = 1; i < 15; i++) {
			for(int j = 1; j < 14; j++) {
				board[i][j] = board[i-1][j] + board[i][j-1];
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());
			
			// k층 n호이지만 0호는 없으므로 -1
			sb.append(board[k][n-1]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
