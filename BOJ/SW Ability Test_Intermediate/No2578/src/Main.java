import java.io.*;
import java.util.*;


public class Main {
	
	static int[][] board;
	static boolean[][] chk_board;
	
	public static boolean chk_bingo() {
		
		int bingo_cnt = 0;
		
		
		for(int c = 0; c < 5; c++) {
			boolean flag = true;
			for (int r = 0; r < 5; r++) {
				if (chk_board[c][r] == false) {
					flag = false;
					break;
				}
			}
			if (flag) bingo_cnt++;
		}
		
		for(int r = 0; r < 5; r++) {
			boolean flag = true;
			for (int c = 0; c < 5; c++) {
				if (chk_board[c][r] == false) {
					flag = false;
					break;
				}
			}
			if (flag) bingo_cnt++;
		}
		if (chk_board[0][0] && chk_board[1][1] && chk_board[2][2] && chk_board[3][3] && chk_board[4][4]) bingo_cnt++;
		if (chk_board[0][4] && chk_board[1][3] && chk_board[2][2] && chk_board[3][1] && chk_board[4][0]) bingo_cnt++;
		
		if (bingo_cnt >= 3) return true;
		else return false;
	}

	public static int[] find_num(int call) {
		int c = 0, r = 0;
		for(c = 0; c < 5; c++) {
			for(r = 0; r < 5; r++) {
				if(board[c][r] == call) {
					int[] pos = {c, r};
					return pos;
				}
			}
		}
		return null;
		
	}
	
	public static void main(String[] args) throws Exception {
		board = new int[5][5];
		chk_board = new boolean[5][5];
		int ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			boolean flag = false;
			for(int j = 0; j < 5; j++) {
				int call = Integer.parseInt(st.nextToken());
				ans++;
				int[] pos = find_num(call);
				chk_board[pos[0]][pos[1]] = true;
				if(chk_bingo()) {
					flag = true;
					break;
				}
			}
			if(flag) break;
		}
		System.out.println(ans);
	}

}
