import java.io.*;
import java.util.*;

public class Main {

	// BOJ 14502 - 연구소
	public static int N, M, ans;
	public static int[][] board, d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static boolean[] using;
	public static boolean[][] visited;
	public static ArrayList<int[]> virus;
	
	public static void combi(int cnt, int start) {
		// 모든 벽을 다 사용했으면
		if(cnt == 3) {
			// 바이러스 확산 시작
			visited = new boolean[N][M];
			Deque<int[]> dq = new ArrayDeque<>();
			// 현재 감염된 칸 수
			int infected = 0;
			// 바이러스들 dq에 삽입
			for(int i = 0; i < virus.size(); i++) {
				int[] cur = virus.get(i);
				dq.offer(new int[] {cur[0], cur[1]});
				visited[cur[0]][cur[1]] = true;
				// 감염된 칸 수 증가
				infected++;
			}
			// BFS 탐색
			while(!dq.isEmpty()) {
				// 분기 처리 - 이미 너무 많이 감염되어서 최대값이 될 수 없다면 종료
				if((N*M) - infected < ans) return;
				// BFS 시작
				int[] cur = dq.poll();
				int r = cur[0], c = cur[1];
				// 4방향 탐색
				for(int dd = 0; dd < 4; dd++) {
					int nr = r + d[dd][0], nc = c + d[dd][1];
					boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
					// 범위 안이고, 방문 전이고, 빈 칸이라면
					if(in_range && !visited[nr][nc] && board[nr][nc] == 0) {
						// 방문 처리 및 다음 탐색을 위해 dq 삽입
						visited[nr][nc] = true;
						dq.offer(new int[] {nr, nc});
						// 감염된 칸 수 증가
						infected++;
					}
				}
			}
			
			// 안전한 칸 수 카운트
			int safe = 0;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					// 벽이나 바이러스가 아니었고, 이번 바이러스 감염때 방문하지 않았다면, 안전 지대
					if(board[r][c] == 0 && !visited[r][c]) safe++;
				}
			}
			
			// 안전 지대 최댓값 갱신
			ans = Math.max(ans, safe);
			
			return;
		}
		
		for(int i = 0; i < N*M; i++) {
			// 기존 벽이거나 바이러스이거나 새로 벽을 세웠다면  스킵
			if(using[i]) continue;
			// 해당 위치에 벽 세우기
			using[i] = true;
			// 현재 인덱스로 좌표 계산
			int cr = i / M, cc = i % M;
			// 현재 좌표에 벽 세우기
			board[cr][cc] = 1;
			// 다음 벽 세우기 진행
			combi(cnt+1, i+1);
			// 백트래킹 - 벽 삭제
			board[cr][cc] = 0;
			// 벽 사용 해제
			using[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		ans = 0;
		
		// 보드의 크기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 보드 배열
		board = new int[N][M];
		// 벽의 위치 (idx = r*M + c)
		using = new boolean[N*M];
		// 바이러스 위치
		virus = new ArrayList<>();
		
		// 초기 입력
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				// 벽이거나, 바이러스라면
				if(board[r][c] != 0) {
					// 벽을 세울 수 없으므로 사용중 표시
					using[(r*M)+c] = true;
					// 바이러스라면 위치도 저장
					if(board[r][c] == 2) virus.add(new int[] {r, c});
				}
			}
		}
		
		// 벽을 세울 수 있는 모든 경우의 수 계산
		combi(0, 0);
		
		
		// 탐색 종료 후 정답 출력
		System.out.println(ans);
		
	}

}