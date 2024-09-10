import java.io.*;
import java.util.*;

public class Main {

	// BOJ 16929 - Two Dots
	public static int N, M;
	public static String ans;
	public static char base;
	public static int[][] visited, d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static char[][] board;
	public static boolean[][] v_fin;
	
	// DFS 탐색 함수
	public static void dfs(int r, int c, int dist) {
		// 4방향 탐색
		for(int dd = 0; dd < 4; dd++) {
			// 새 좌표 계산
			int nr = r + d[dd][0], nc = c + d[dd][1];
			// 범위 체크
			boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
			// 범위 안에 있고, 다음 이동 좌표가 현재 탐색 기준과 일치한다면,
			if(in_range && board[nr][nc] == base) {
				// 만약 처음 방문하는 곳이면
				if(visited[nr][nc] == 0) {
					// 해당 칸에 몇번째로 도착했는지 체크
					visited[nr][nc] = dist + 1;
					// 해당칸에서 출발하는 dfs를 이미 실행했다는 표시
					v_fin[nr][nc] = true;
					// dfs 탐색
					dfs(nr, nc, dist+1);
					// 탐색 완료 후 사이클을 찾았다면 종료
					if(ans.equals("Yes"))return;
					// 아니라면 visited 0으로 원상복구
					visited[nr][nc] = 0;
				}
				// 만약 방문한 적 있는 곳이고, 현재 이동 거리 - 해당 칸까지의 이동거리 가 3보다 크거나 같으면
				else if(dist - visited[nr][nc] >= 3) {
					// 점 4개 이상 방문한 것이므로 사이클 성립
					ans = "Yes";
					return;
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = "No";
		// 초기 입력
		board = new char[N][M];
		visited = new int[N][M];
		v_fin = new boolean[N][M];
		for(int r = 0; r < N; r++) {
			String[] str = br.readLine().split("");
			for(int c = 0; c < M; c++) {
				board[r][c] = str[c].charAt(0);
			}
		}
		
		// 모든 칸에 대해서 dfs 탐색 시작
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				// 한번이라도 해당칸에서 dfs 진행한 적이 있다면 스킵
				if(v_fin[r][c]) continue;
				// dfs 탐색 한 적 있음 표시
				v_fin[r][c] = true;
				// 탐색 값 설정
				base = board[r][c];
				// 시작 거리 설정
				visited[r][c] = 1;
				// dfs 탐색 시작
				dfs(r, c, 1);
				// 시작 거리 초기화
				visited[r][c] = 0;
				// 탐색 종료 후 사이클이 있었다면
				if(ans.equals("Yes")) {
					// "Yes" 출력 후 종료
					System.out.println(ans);
					return;
				}
			}
		}
		
		// 탐색 종료 후 사이클을 찾지 못했다면 "No" 출력
		System.out.println(ans);
		
	}

}