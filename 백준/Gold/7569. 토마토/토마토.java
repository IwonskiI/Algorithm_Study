import java.util.*;
import java.io.*;
	
public class Main {
	
	// BOJ 7569 - 토마토
	public static int m, n, l;
	public static int[] dr = {-1, 0, 1, 0, 0, 0}, dc = {0, -1, 0, 1, 0, 0}, dh = {0, 0, 0, 0, 1, -1};
	public static int[][][] board;
	
	public static void bfs(int h, int r, int c) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {h, r, c, 1});
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int ch = cur[0], cr = cur[1], cc = cur[2], lv = cur[3]+1;
			for(int dd = 0; dd < 6; dd++) {
				int nh = ch + dh[dd], nr = cr + dr[dd], nc = cc + dc[dd];
				boolean in_range = 0 <= nh && nh < l && 0 <= nr && nr < n && 0 <= nc && nc < m;
				if(in_range && (board[nh][nr][nc] == 0 || board[nh][nr][nc] > lv)) {
					board[nh][nr][nc] = lv;
					dq.offer(new int[] {nh, nr, nc, lv});
				}
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		board = new int[l][n][m];
		
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < m; k++) {
					board[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					if(board[i][j][k] == 1) {
						bfs(i, j, k);
					}
				}
			}
		}
		
		int ans = -1;
		
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					if(board[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
					else if(ans < board[i][j][k]) {						
						ans = board[i][j][k];
					}
				}
			}
		}
		
		System.out.println(ans-1);
		return;
	}
}
