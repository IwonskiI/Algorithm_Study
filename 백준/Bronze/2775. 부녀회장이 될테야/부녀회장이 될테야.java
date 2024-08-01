import java.io.*;

public class Main {

	// BOJ 2775 - 부녀회장이 될테야
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[][] board = new int[15][14];
		
		board[0][0] = 1;
		
		for(int i = 1; i < 14; i++) {
			board[0][i] = i+1;
			board[i][0] = 1;
		}
		board[14][0] = 1;
		
		for(int i = 1; i < 15; i++) {
			for(int j = 1; j < 14; j++) {
				board[i][j] = board[i-1][j] + board[i][j-1];
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());
			sb.append(board[k][n-1]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
