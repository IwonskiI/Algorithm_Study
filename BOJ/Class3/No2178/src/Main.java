import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ2178 - 미로찾기
	
	//board = 미로, visited = 최소 칸 수
	static int[][] board;
	static int[][] visited;
	// 상, 우, 하, 좌 순서로 탐색
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	// 배열의 크기
	static int N, M;
	
	public static void dfs() {
		//양방향 큐
		Deque<int[]> stack = new ArrayDeque<>();
		// {시작r, 시작c, 다음 칸까지 필요한 칸수}
		stack.offer(new int[] {0, 0, 2});
		// (0,0)은 1칸으로 초기화
		visited[0][0] = 1;
		
		//DFS
		while(!stack.isEmpty()) {
			int[] cur = stack.pollLast();
			int r = cur[0], c = cur[1], lv = cur[2];
			for(int dd = 0; dd < 4; dd++) {
				int nr = r + dr[dd], nc = c + dc[dd];
				// 범위 체크
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
				// 범위 & 길 & (아직 방문 전 or 더 적은 칸 수) 확인
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
