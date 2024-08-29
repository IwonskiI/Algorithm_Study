import java.util.*;
import java.io.*;

public class Solution {
	// SWEA 2105 - [모의 SW 역량테스트] 디저트 카페
	public static int N, ans, R, C;
	public static int[][] board, d = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
	public static boolean[] kind;
	public static boolean[][] visited;
	
	// dfs 탐색
	public static void dfs(int r, int c, int dir, int cur) {
		// 현재 방향 이상으로만 탐색
		for(int dd = dir; dd < 4; dd++) {
			// 새 좌표
			int nr = r + d[dd][0], nc = c + d[dd][1];
			// 범위 체크
			boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
			if(in_range) {
				// 새 좌표가 시작점이고, 최소 거리 이상 돌았고, 현재 거리가 이전 최대 거리보다 크다면 갱신 후 종료
				if(nr == R && nc == C && cur >= 4 && ans < cur) {
					ans = cur;
					return;
				}
				// 다음 칸이 방문 전이고, 아직 먹지 않은 종류라면
				if(!visited[nr][nc] && !kind[board[nr][nc]]) {
					// 방문 처리
					visited[nr][nc] = true;
					kind[board[nr][nc]] = true;
					// 추가 탐색
					dfs(nr, nc, dd, cur + 1);
					// 방문 해제 처리
					visited[nr][nc] = false;
					kind[board[nr][nc]] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 탐색 실패할 경우 -1 출력
			ans = -1;
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			// 초기 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 각 칸마다 dfs 완전 탐색
			// 대각선으로 움직여야하므로 위에서부터 맨 아레에서 두번째 줄까지만 탐색
			// 제일 왼쪽 줄과 제일 오른쪽 줄 빼고 탐색
			for(int r = 0; r < N - 2; r++) {
				for(int c = 1; c < N - 1; c++) {
					// 방문 배열 초기화
					visited = new boolean[N][N];
					// 종류 배열 초기화
					kind = new boolean[101];
					// 시작 좌표 설정
					R = r;
					C = c;
					// 방문 처리
					visited[r][c] = true;
					kind[board[r][c]] = true;
					// 탐색 시작 (초기 방향 0, cnt 1)
					dfs(r, c, 0, 1);
				}
			}
			
			// 탐색 후 정답 저장
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			
		}
		// 최종 결과 출력
		System.out.println(sb.toString());
	}
}
