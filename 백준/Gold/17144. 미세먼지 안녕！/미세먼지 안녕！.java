import java.io.*;
import java.util.*;

public class Main {

	// BOJ 17144 - 미세먼지 안녕!
	public static int R, C, T;
	public static int[][] board, d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static int[][] d2 = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 보드의 크기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// 시뮬레이션 시간
		T = Integer.parseInt(st.nextToken());
		
		// 보드
		board = new int[R][C];
		
		// 공기청정기의 위치
		int[] pos = new int[2];
		
		// 초기 입력
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				// 공기 청정기 위치 저장
				if(board[r][c] == -1) {
					// 0번 인덱스에 위의 위치 저장
					if(pos[0] == 0) pos[0] = r;
					// 1번 인덱스에 아래의 위치 저장
					else pos[1] = r;
				}
			}
		}
		
		// T초 동안 시뮬레이션 진행
		for(int t = 0; t < T; t++) {
			// 미세먼지가 확산한 뒤, 공기청정기가 순환할 새로운 보드
			int[][] n_board = new int[R][C];
			
			// 미세먼지 확산
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					// 공기 청정기거나 빈 칸이라면 스킵 
					if(board[r][c] <= 0) continue;
					// 현재 미세먼지 cur
					int cur = board[r][c];
					// 이웃한 칸으로 옮겨갈 미세먼지의 수
					int avg = board[r][c] / 5;
					// 이동 한 미세먼지의 합
					int sum = 0;
					// 4방향 탐색
					for(int dd = 0; dd < 4; dd++) {
						int nr = r + d[dd][0], nc = c + d[dd][1];
						boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
						// 범위 안에 있고, 공기청정기가 아니라면
						if(in_range && board[nr][nc] != -1) {
							// 미세먼지에 옮겨갈 미세먼지 수 추가
							sum += avg;
							// 새로운 보드에 옮겨 갈 미세먼지 추가
							n_board[nr][nc] += avg;
						}
					}
					// 새로운 보드에 옮겨가고 남은 미세먼지 추가
					n_board[r][c] += cur - sum;
				}
			}
			
			// 위쪽 공기 청정기 가동
			int cr = pos[0], cc = 0;
			// 위 -> 오른 -> 아래 -> 왼 순서로 값 복사하며 이동
			for(int dd = 0; dd < 4; dd++) {
				while(true) {
					// 다음 좌표 확인
					int nr = cr + d[dd][0], nc = cc + d[dd][1];
					// 범위는 위쪽 공기청정기가 순환할 수 있는 범위만 회전
					boolean in_range = (0 <= nr && nr <= pos[0]) && (0 <= nc && nc < C);
					// 범위를 벗어나면 방향 바꿔서 다시 탐색
					if(!in_range) break;
					// 현재 칸이 공기청정기라면 값을 공기 청정기 표시
					if(cr == pos[0] && cc == 0) n_board[cr][cc] = -1;
					// 다음 칸이 공기청정기라면 맑은 공기(0) 복사
					else if(n_board[nr][nc] == -1) n_board[cr][cc] = 0;
					// 두 경우가 아니라면 다음 칸에 있는 먼지 이동
					else n_board[cr][cc] = n_board[nr][nc];
					// 다음 좌표로 이동
					cr = nr;
					cc = nc;
				}
			}
			// 아래쪽 공기 청정기 가동
			cr = pos[1];
			cc = 0; 
			// 아래 -> 오른 -> 위 -> 왼 순서로 값 복사하면서 이동
			for(int dd = 0; dd < 4; dd++) {
				while(true) {
					// 다음 좌표 확인
					int nr = cr + d2[dd][0], nc = cc + d2[dd][1];
					// 범위는 아래쪽 공기청정기가 순환할 수 있는 범위만 회전
					boolean in_range = (pos[1] <= nr && nr < R) && (0 <= nc && nc < C);
					// 범위를 벗어나면 방향 바꿔서 다시 탐색
					if(!in_range) break;
					// 현재 칸이 공기청정기라면 값을 공기 청정기로 표시
					if(cr == pos[1] && cc == 0) n_board[cr][cc] = -1;
					// 다음 칸이 공기청정기라면 맑은 공기(0) 복사
					else if(n_board[nr][nc] == -1) n_board[cr][cc] = 0;
					// 두 경우가 아니라면 다음 칸에 있는 먼지 이동
					else n_board[cr][cc] = n_board[nr][nc];
					// 다음 좌표로 이동
					cr = nr;
					cc = nc;
				}
			}
			// 먼지 이동까지 완료되면 board를 새로운 board로 설정
			board = n_board;
		}
		
		// 남아있는 먼지의 합 구하기
		
		// 공기청정기는 -1의 값을 갖고 이러한 칸이 2칸 있으므로 2부터 시작
		int sum = 2;
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				// 모든 칸에 있는 숫자 더하기
				sum += board[r][c];
			}
		}
		
		// 저장된 결과 출력
		System.out.println(sum);
		
	}

}