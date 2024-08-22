import java.io.*;
import java.util.*;

public class Solution {

	// SWEA 1861 - 정사각형 방
	// 전역 변수 설정
	static int N, room_num, cnt;
	static int[][] board, visited, d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	// 해당 칸 확인
	public static void chk_board(int r, int c) {
		// 해당 칸이 탐색 전이라면
		if(visited[r][c] == -1) {
			// 현재 방의 번호
			int cur = board[r][c];
			// 4방향 탐색
			for(int dd = 0; dd < 4; dd++) {
				int nr = r + d[dd][0], nc = c + d[dd][1];
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
				// 범위 안에 있고, 해당 방 번호랑 1 차이나는 칸이 있다면
				if(in_range && board[nr][nc] == cur+1) {
					// 새로운 방이 방문 전이라면 탐색 진행
					if(visited[nr][nc] == -1) chk_board(nr, nc);
					// 탐색 완료 후 해당 방 번호에서 방문 가능한 방 개수 + 1을 저장
					visited[r][c] = visited[nr][nc] + 1;
					// 중복 되는 숫자는 없으므로 탐색 종료
					break;
				}
			}
			// 탐색 완료 후 방문 가능한 방이 없었다면, 1로 설정 (자기 자신 탐색 횟수)
			if(visited[r][c] == -1) visited[r][c] = 1;
		}
		
		// 현재 방이 기존 방 갯수 보다 많은 방을 탐색했다면
		if(visited[r][c] > cnt) {
			// 방 갯수 업데이트 및 방 번호 업데이트
			cnt = visited[r][c];
			room_num = board[r][c];
		}
		// 방 갯수가 같고, 방번호가 더 작다면 방번호만 업데이트
		else if(visited[r][c] == cnt && room_num > board[r][c]) {
			room_num = board[r][c];
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			visited = new int[N][N];
			// 방 번호 입력 및 방문 방 개수 -1로 초기화
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
					visited[r][c] = -1;
				}
			}
			// 초기 값 설정(최솟값으로 설정)
			room_num = -1;
			cnt = -1;
			// 각 칸 탐색
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					chk_board(r, c);
				}
			}
			// 해당 케이스의 결과 저장
			sb.append(room_num).append(" ").append(cnt).append("\n");
		}
		// 최종 결과 전체 출력
		System.out.println(sb.toString());
	}
	
}
