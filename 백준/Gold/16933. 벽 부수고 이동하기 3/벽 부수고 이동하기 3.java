import java.io.*;
import java.util.*;

public class Main {

	// BOJ 2206 - 벽 부수고 이동하기
	public static int N, M, K, ans;
	public static int[][] board, d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static boolean[][][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = -1;
		// 초기 입력
		board = new int[N][M];
		visited = new boolean[N][M][K+1];
		for(int r = 0; r < N; r++) {
			String[] str = br.readLine().split("");
			for(int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(str[c]);
			}
		}
		
		
		// BFS 탐색
		Deque<int[]> dq = new ArrayDeque<>();
		// 첫 시작은 0,0부터 초기 거리는 1, 아직 블록을 부술 수 있으므로 true로 시작
		dq.offer(new int[] {0, 0, 1, 0});
		visited[0][0][0] = true;
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1], dist = cur[2], rm = cur[3];
			// 도착 지점에 왔다면
			if(r == N-1 && c == M - 1) {
				// 현재까지 이동 거리를 저장한 뒤 종료
				ans = dist;
				break;
			}
			
			// 밤이라면 제자리 정지 고려
			if(dist % 2 == 0) {
				dq.offer(new int[] {r, c, dist+1, rm});
			}
			
			// 4방향 탐색
			for(int dd = 0; dd < 4; dd++) {
				// 새 좌표 계산
				int nr = r + d[dd][0], nc = c + d[dd][1];
				// 범위 체크
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
				// 범위 안이라면,
				if(in_range) {
					// 진행하려는 칸이 빈칸이고, 깰 수 있는 현재 블록 수로 도달한 적이 없다면
					if(board[nr][nc] == 0 && !visited[nr][nc][rm]) {
						// 이동
						dq.offer(new int[] {nr, nc, dist+1, rm});
						visited[nr][nc][rm] = true;
					}
					// 진행하려는 칸이 빈칸이 아니고,
					else {
						// 아직 깰 수 있는 블록이 남아있고, 블록을 깰 수 있는 낮이고, 하나 깨고 도달한 적이 없다면
						if(rm < K && dist % 2 == 1 && !visited[nr][nc][rm+1]) {
							// 블록 부수고 이동
							dq.offer(new int[] {nr, nc, dist+1, rm+1});
							visited[nr][nc][rm+1] = true;
						}
					}
				}
			}
		}
		
		// 탐색 종료 후 정답 출력
		System.out.println(ans);
		
	}

}