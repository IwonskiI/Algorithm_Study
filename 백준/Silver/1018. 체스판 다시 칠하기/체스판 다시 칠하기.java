import java.io.*;
import java.util.*;

public class Main {

	// BOJ 1018 - 체스판 다시 칠하기
	public static int N, M, ans;
	public static char[][] board;
	
	public static int chk_board(int row, int col) {
		
		// 시작점 칸 색
		char start = board[row][col];
		// 시작점과 같은 색으로 시작 / 시작점과 다른 색으로 시작
		int change1 = 0, change2 = 0;
		
		// 8*8 배열 탐색
		for(int r = row; r < row+8; r++) {
			for(int c = col; c < col + 8; c++) {
				// 시작점과 같은 색으로 시작했을 때 바꿔야하는 수 계산
				if((r - row) % 2 == 0 && (c - col) % 2 == 0 && board[r][c] != start) change1++;
				else if((r - row) % 2 == 0 && (c - col) % 2 != 0 && board[r][c] == start) change1++;
				else if((r - row) % 2 != 0 && (c - col) % 2 == 0 && board[r][c] == start) change1++;
				else if((r - row) % 2 != 0 && (c - col) % 2 != 0 && board[r][c] != start) change1++;
				// 시작점과 반대 색으로 시작했을 때 바꿔야하는 수 계산				
				if((r - row) % 2 == 0 && (c - col) % 2 == 0 && board[r][c] == start) change2++;
				else if((r - row) % 2 == 0 && (c - col) % 2 != 0 && board[r][c] != start) change2++;
				else if((r - row) % 2 != 0 && (c - col) % 2 == 0 && board[r][c] != start) change2++;
				else if((r - row) % 2 != 0 && (c - col) % 2 != 0 && board[r][c] == start) change2++;
			}
		}
		// 둘 중 더 작은 수 return
		return Math.min(change1, change2);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 최솟값을 저장할 ans
		ans = Integer.MAX_VALUE;
		// 초기 입력
		board = new char[N][M];
		for(int r = 0; r < N; r++) {
			String[] str = br.readLine().split("");
			for(int c = 0; c < M; c++) {
				board[r][c] = str[c].charAt(0);
			}
		}
		
		// 8*8배열이 만들어지는 칸 안에서 탐색
		for(int r = 0; r <= N - 8; r++) {
			for(int c = 0; c <= M - 8; c++) {
				// 현재 좌표에서 시작했을 때 바꿔야하는 수 계산
				int cur = chk_board(r, c);
				// 더 작으면 갱신
				if(ans > cur) ans = cur;
			}
		}
		
		// 탐색 종료 후 최솟값 출력
		System.out.println(ans);
		
	}

}