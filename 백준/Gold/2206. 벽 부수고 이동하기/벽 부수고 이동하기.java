import java.io.*;
import java.util.*;

public class Main {

	// BOJ 2206 - 벽 부수고 이동하기
	public static int N, M, ans;
	public static int[][] board, d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static int[][][] visited;
	
	// 좌표를 관리할 class
	public static class Pos{
		int r, c, dist;
		boolean rm;
		
		public Pos(int r, int c, int dist, boolean rm) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.rm = rm;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = -1;
		// 초기 입력
		board = new int[N][M];
		visited = new int[N][M][2];
		for(int r = 0; r < N; r++) {
			String[] str = br.readLine().split("");
			for(int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(str[c]);
			}
		}
		
		
		// BFS 탐색
		Deque<Pos> dq = new ArrayDeque<>();
		// 첫 시작은 0,0부터 초기 거리는 1, 아직 블록을 부술 수 있으므로 true로 시작
		dq.offer(new Pos(0, 0, 1, true));
		visited[0][0][0] = 1;
		visited[0][0][1] = 1;
		while(!dq.isEmpty()) {
			Pos cur = dq.poll();
			// 도착 지점에 왔다면
			if(cur.r == N-1 && cur.c == M - 1) {
				// 현재까지 이동 거리를 저장한 뒤 종료
				ans = cur.dist;
				break;
			}
			
			// 4방향 탐색
			for(int dd = 0; dd < 4; dd++) {
				// 새 좌표 계산
				int nr = cur.r + d[dd][0], nc = cur.c + d[dd][1];
				// 범위 체크
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
				// 범위 안이라면,
				if(in_range) {
					// 아직 블록을 깰 수 있고, 다음 진행하려는 칸의 블록을 자르지 않고 도달할 수 있었던 최소 거리가 현재 거리보다 길다면
					if(cur.rm) {
						// 뚫린 길이라면 방문 처리
						if(board[nr][nc] == 0 && (visited[nr][nc][0] == 0 || visited[nr][nc][0] > cur.dist+1)) {
							dq.offer(new Pos(nr, nc, cur.dist+1, cur.rm));
							visited[nr][nc][0] = cur.dist + 1;
						}
						// 막힌 길이지만, 아직 벽을 부수지 않았다면
						else if(board[nr][nc] == 1 && cur.rm && (visited[nr][nc][1] == 0 || visited[nr][nc][1] > cur.dist+1)) {
							// 벽을 뚫고 방문 처리
							dq.offer(new Pos(nr, nc, cur.dist+1, false));
							// 벽을 뚫고 도달한 거리 갱신
							visited[nr][nc][1] = cur.dist+1;
						}
					}
					// 블록을 이미 깼고, 다음 진행하려는 칸의 블록을 자르고 도달할 수 있었던 최소 거리가 현재 거리보다 길다면
					else if(!cur.rm && (visited[nr][nc][1] == 0 || visited[nr][nc][1] > cur.dist+1)) {
						// 뚫린 길이라면 방문 처리
						if(board[nr][nc] == 0) {
							dq.offer(new Pos(nr, nc, cur.dist+1, cur.rm));
							visited[nr][nc][1] = cur.dist + 1;
						}
						// 막힌 길이지만, 아직 벽을 부수지 않았다면
						else if(board[nr][nc] == 1 && cur.rm) {
							// 벽을 뚫고 방문 처리
							dq.offer(new Pos(nr, nc, cur.dist+1, false));
							visited[nr][nc][1] = cur.dist+1;
						}
					}
				}
			}
		}
		
		// 탐색 종료 후 정답 출력
		System.out.println(ans);
		
	}

}