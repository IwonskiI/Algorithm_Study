import java.io.*;
import java.util.*;


public class Main {
	
	public static int K, N, hole;
	public static int[][] board, holelst = new int[][] {{0, 1, 2, 3}, {1, 0, 3, 2}, {2, 3, 0, 1}, {3, 2, 1, 0}};
	
	public static void fill(int cnt, int r, int c) {
		if(cnt == 1) {
			board[r][c] = holelst[hole][0];
			board[r][c+1] = holelst[hole][1];
			board[r+1][c] = holelst[hole][2];
			board[r+1][c+1] = holelst[hole][3];
			return;
		}
		
		fill(cnt/2, r, c);
		fill(cnt/2, r+cnt, c);
		fill(cnt/2, r, c+cnt);
		fill(cnt/2, r+cnt, c+cnt);
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		K = Integer.parseInt(br.readLine());
		N = (int) Math.pow(2, K);
		board = new int[N][N];
		st = new StringTokenizer(br.readLine());
		int type = 0;
		for(int i = 0; i < 2*K; i++) {
			char op = st.nextToken().charAt(0);
			if(op == 'D') {
				if(type == 0) type = 2;
				else if(type == 1) type = 3;
			}
			else if(op == 'R') {
				if(type == 0) type = 1;
				else if(type == 2) type = 3;
			}
			else if(op == 'U') {
				if(type == 2) type = 0;
				else if(type == 3) type = 1;
			}
			else if(op == 'L') {
				if(type == 1) type = 0;
				else if(type == 3) type = 2;
			}
			
		}
		hole = holelst[type][Integer.parseInt(br.readLine())];
		fill(N/2, 0, 0);
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				sb.append(board[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
