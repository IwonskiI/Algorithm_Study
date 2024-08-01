import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ2178 - 미로찾기
	static int[][] board;
	static int[][] visited;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static int N, M;
	
	public static void dfs() {
		Deque<int[]> stack = new ArrayDeque<>();
		stack.offer(new int[] {0, 0, 2});
		visited[0][0] = 1;
		
		while(!stack.isEmpty()) {
			int[] cur = stack.pollLast();
			int r = cur[0], c = cur[1], lv = cur[2];
			for(int dd = 0; dd < 4; dd++) {
				int nr = r + dr[dd], nc = c + dc[dd];
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
				if(in_range && board[nr][nc] == 1 && (visited[nr][nc] == 0 || visited[nr][nc] > lv)) {
					visited[nr][nc] = lv;
					stack.offer(new int[] {nr, nc, lv + 1});
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		visited = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			String[] temp = br.readLine().split("");
			for(int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(temp[c]);
			}
		}
		dfs();
		System.out.println(visited[N-1][M-1]);
	}

}
