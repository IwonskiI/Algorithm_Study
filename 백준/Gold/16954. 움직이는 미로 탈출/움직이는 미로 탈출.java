import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 16954 - 움직이는 미로 탈출
	public static int ans;
	public static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 1}, {1, -1}, {-1, -1}, {1, 1}, {0, 0}};
	public static boolean[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 게임 판을 저장할 보드판
		// 1초에 모든 벽이 한칸씩 내려오므로 칸을 2배로 설정해서 이동할 때 위치를 한 줄 위에 저장
		board = new boolean[16][8];
		// 가능성을 출력할 변수
		ans = 0;
		for(int r = 8; r < 16; r++) {
			String[] str = br.readLine().split("");
			for(int c = 0; c < 8; c++) {
				// 벽이면 지나다닐 수 없다는 것을 true로 표시
				if(str[c].charAt(0) == '#')
					board[r][c] = true;
			}
		}
		
		// BFS 탐색
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {15, 0, 0});
		int prev = -1;
		// 중복 연산을 없애기 위해 visited 배열 선언
		boolean[][] visited = new boolean[8][8];
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			// 현재 좌표와 이동한 시간을 저장
			int r = cur[0], c = cur[1], t = cur[2];
			// 같은 시간대에 같은 장소에 가는 것을 막기 위함이므로 시간이 지났을 때는 visited 초기화
			if(t != prev)
				visited = new boolean[8][8];
			// 현재 시간을 이전 시간에 저장
			prev = t;
			// 이동한 시간에 따라 가장 윗 지점과 가장 아랫 지점을 설정
			int B = 15 - t, T = 8 - t;
			// 8초가 지났을 때까지 살아있다면 더이상 위에서 내려올 블록이 없음 -> 무조건 우측 상단으로 도달 가능
			if(t == 8) {
				ans = 1;
				break;
			}
			// 원점에 머무를 수도 있기 때문에 9방향 탐색
			for(int dd = 0; dd < 9; dd++) {
				// 좌표 계산
				int nr = r + d[dd][0], nc = c + d[dd][1];
				// 범위 계산 - col은 0부터 7사이지만 row는 시간에 따라 달라지는 가장 윗 줄과 가장 아랫줄로 검사
				boolean in_range = (T <= nr && nr <= B) && (0 <= nc && nc < 8);
				// 범위 안에 있고, 새로 이동할 좌표가 길이고, 1초 뒤에도 해당 칸이 벽으로 안 막히고, 현재 시간에 이동 한 곳인지 확인
				if(in_range && !board[nr][nc] && !board[nr-1][nc] && !visited[nr+t-8][nc]) {
					// 이동 가능하다면 한 줄 위에 이동한것으로 간주하고 이동
					dq.offer(new int[] {nr -1, nc, t + 1});
					// 방문 처리
					visited[nr+t-8][nc] = true;
				}
			}
		}
		
		// 반복문 종료 후 가능성 출력
		System.out.println(ans);

	}

}