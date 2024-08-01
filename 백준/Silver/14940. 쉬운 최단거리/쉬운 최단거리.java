import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 14940 - 쉬운 최단거리
	
	static int N, M;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[][] board;
	static boolean[][] visited;
	
	
	public static void bfs(int r, int c, int lvl) {
		Deque<int[]> q = new ArrayDeque<>();
		visited[r][c] = true;
		board[r][c] = 0;
		// 시작점 좌표 및 목적지까지 거리 추가 (초기값 0)
		q.offer(new int[] {r, c, lvl});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			// 현재 좌표 및 목적지까지 거리
			int row = cur[0], col = cur[1], lv = cur[2];
			
			// 4방향 탐색
			for(int dd = 0; dd < 4; dd++) {
				int nr = row + dr[dd], nc = col + dc[dd];
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
				// 범위 안이고, 방문 전이고, 갈 수 있는 길이면 탐색
				if(in_range && !visited[nr][nc] && board[nr][nc] == -1) {
					// 방문 체크 및 거리 계산
					visited[nr][nc] = true;
					board[nr][nc] = lv + 1;
					// 추가 탐색
					q.offer(new int[] {nr, nc, lv + 1});
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N, M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 목표 지점 좌표 초기화
		int sr = -1, sc = -1;
		// 배열 선언
		board = new int[N][M];
		visited = new boolean[N][M];
		
		// 배열 초기화
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				// 목표 지점 찾기
				if(board[r][c] == 2) {
					sr = r;
					sc = c;
				}
				if(board[r][c] != 0) board[r][c] = -1;
			}
		}	
		
		// 최단 거리 탐색
		bfs(sr, sc, 0);
		
		// 최단 거리 출력
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				sb.append(board[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
