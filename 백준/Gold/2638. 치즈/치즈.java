import java.io.*;
import java.util.*;


public class Main {

	// BOJ 2638 - 치즈
	public static int[][] d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	
	int N = Integer.parseInt(st.nextToken());
	int M = Integer.parseInt(st.nextToken());
	
	// 치즈의 위치를 표시할 배열
	boolean[][] cheese = new boolean[N][M];
	// 치즈는 2의 강도를 갖고 공기에서 bfs 탐색을 하다가 치즈를 만나면 강도를 1 깎음
	// bfs 탐색이 끝난 뒤, cheese 배열에는 치즈가 있지만, board배열에서 0보다 작거나 같은 강도가 저장되어있으면
	// 녹아서 없어진 것으로 간주하고 치즈 수를 줄임
	int[][] board = new int[N][M];
	// 치즈의 개수 관리
	int cnt = 0;
	
	for(int r = 0; r < N; r++) {
		st = new StringTokenizer(br.readLine());
		for(int c = 0; c < M; c++) {
			int cur = Integer.parseInt(st.nextToken());
			// 치즈가 있으면
			if(cur == 1) {
				// 치즈 수 증가
				cnt++;
				// 치즈 배열에 치즈 존재 여부 변경
				cheese[r][c] = true;
				// 치즈 강도 2로 설정
				board[r][c] = 2;
			}
		}
	}
	
	// 치즈가 다 녹는데 걸리는 시간
	int time = 0;
	// 치즈가 다 녹을 때 까지 반복
	while(cnt > 0) {
		// 시간 증가
		time++;
		// 방문 처리 배열
		boolean[][] visited = new boolean[N][M];
		// bfs 탐색을 위한 queue 선언
		Deque<int[]> dq = new ArrayDeque<>();
		// 공기 bfs 탐색을 위해 0,0부터 탐색 시작
		dq.offer(new int[] {0,0});
		// 방문처리
		visited[0][0] = true;
		// bfs 탐색 시작
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			// 현재 좌표
			int r = cur[0], c = cur[1];
			// 4방향 탐색
			for(int dd = 0; dd < 4; dd++) {
				// 새 좌표
				int nr = r + d[dd][0], nc = c + d[dd][1];
				// 범위 체크
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
				// 범위 안에 있고, 아직 방문 전이며, 치즈가 아니라면
				if(in_range && !visited[nr][nc] && !cheese[nr][nc]) {
					// 추가 탐색 진행
					// 방문 처리
					visited[nr][nc] = true;
					// 다음 탐색을 위해 큐에 추가
					dq.offer(new int[] {nr, nc});
				}
				// 범위 안에 있고, 치즈 강도가 남아 있다면
				else if(in_range && board[nr][nc] > 0) {
					// 공기중에 접촉한 부분 강도 감고
					board[nr][nc]--;
				}
			}
		}
		// bfs 탐색 이후 치즈 수 카운트 및 치즈 강도 복원
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				// 치즈가 아니라 공기중이면 스킵
				if(!cheese[r][c]) continue;
				// 치즈의 강도가 0보다 작다면,
				if(board[r][c] <= 0) {
					// 치즈가 녹아서 없어짐
					cheese[r][c] = false;
					// 치즈 수 감소
					cnt--;
				}
				// 아직 사라지지 않은 치즈라면 강도 2로 재 설정
				else board[r][c] = 2;
			}
		}
	}
	
	// 치즈가 모두 녹은 시간 출력
	System.out.println(time);
		
	}

}
