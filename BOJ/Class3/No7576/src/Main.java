import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ7576 - 토마토
	
	static int M, N, ans = 1;
	static int[][] board;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	
	public static void bfs(int row, int col) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {row, col, 2});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0], c = cur[1], lv = cur[2];
			for(int dd = 0; dd < 4; dd++) {
				int nr = r + dr[dd], nc = c + dc[dd];
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
				if(!in_range || board[nr][nc] == -1) continue;
				if(board[nr][nc] == 0 || board[nr][nc] > lv) {
					board[nr][nc] = lv;
					q.offer(new int[] {nr, nc, lv+1});
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(board[r][c] == 1) bfs(r, c);
			}
		}
		
		int ans = 0;
		boolean flag = false;
		
		for(int r = 0; r < N; r++) {
			if(flag) break;
			for(int c = 0; c < M; c++) {
				if(board[r][c] == 0) {
					flag = true;
					ans = -1;
					break;
				}
				ans = ans < board[r][c]-1 ? board[r][c]-1 : ans;
			}
		}
		
		System.out.println(ans);
	}

}
