import java.io.*;
import java.util.*;

public class Solution {

	// SWEA 5656 - [모의 SW 역량테스트] 벽돌 깨기
	public static int N, R, C, ans;
	public static int[][] board, d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	// 벽돌 연쇄 작용
	public static void breakBlock(int row, int col) {
		// 방문 배열
		boolean[][] visited = new boolean[R][C];
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {row, col, board[row][col]});
		visited[row][col] = true;
		board[row][col] = 0;
		// 연쇄 폭발
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1], level = cur[2];
			
			// 4방향 배열
			for(int dd = 0; dd < 4; dd++) {
				int nr = r + d[dd][0], nc = c + d[dd][1];
				boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
				int idx = 1;
				while(in_range && idx < level) {
					if(board[nr][nc] != 0 && !visited[nr][nc]) {
						dq.offer(new int[] {nr, nc, board[nr][nc]});
					}
					visited[nr][nc] = true;
					board[nr][nc] = 0;
					idx++;
					nr += d[dd][0];
					nc += d[dd][1];
					in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
				}
			}
		}
		
		// 블록 떨어트리기
		for(int c = 0; c < C; c++) {
			int idx = R-1;
			for(int r = R-1; r >=0; r--) {
				if(board[r][c] != 0) board[idx--][c] = board[r][c];
			}
			for(int r = idx; r >= 0; r--) {
				board[r][c] = 0;
			}
		}
	}
	
	// 순열 생성
	public static void perm(int cnt) {
		// N개의 공을 다 떨어트리면 계산
		if(cnt == N) {
			int left = 0;
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(board[r][c] > 0) left++;
				}
			}
			ans = Math.min(ans, left);
			return;
		}
		
		// 백트래킹으로 모든 상황 계산
		for(int c = 0; c < C; c++) {
			int[][] prev_board = new int[R][C];
			for(int r = 0; r < R; r++) {
				prev_board[r] = board[r].clone();
			}
			int bline = 0;
			for(int r = 0; r < R; r++) {
				if(board[r][c] != 0) {
					bline = r;
					break;
				}
			}
			breakBlock(bline, c);
			perm(cnt+1);
			board = prev_board;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			board = new int[R][C];
			
			for(int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < C; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 최소 블록 계산
			ans = Integer.MAX_VALUE;
			
			// 조합 생성
			perm(0);
			
			// 최대 점수 저장
			sb.append(ans).append("\n");
		
		}
		
		// 최종 결과 출력
		System.out.println(sb.toString());
		

	}

}