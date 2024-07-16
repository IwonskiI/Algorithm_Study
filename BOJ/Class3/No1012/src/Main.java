import java.io.*;
import java.util.*;


public class Main {
	
	static int C, R, N, ans;
	static int[][] board;
	static boolean[][] visited;
	static int[] dc = {-1, 0, 1, 0}, dr = {0, -1, 0, 1};
	
	public static void bfs(int col, int row) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {col, row});
		visited[col][row] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int c = cur[0], r = cur[1];
			
			for(int dd = 0; dd < 4; dd++) {
				int nc = c + dc[dd], nr = r + dr[dd];
				boolean in_range = (0 <= nc && nc < C) && (0 <= nr && nr < R);
				if(in_range && !visited[nc][nr] && board[nc][nr] == 1) {
					queue.add(new int[] {nc, nr});
					visited[nc][nr] = true;
					
				}
			}
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			board = new int[C][R];
			visited = new boolean[C][R];
			
			for(int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int column = Integer.parseInt(st.nextToken());
				board[column][row] = 1;
			}
			
			for(int c = 0; c < C; c++) {
				for(int r = 0; r < R; r++) {
					if(board[c][r] == 1 && !visited[c][r]) {
						ans += 1;
						bfs(c, r);
					}
				}
			}
			
			sb.append(ans).append("\n");
			
		}
		
		System.out.println(sb.toString());
	}

}
