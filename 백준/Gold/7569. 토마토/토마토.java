import java.util.*;
import java.io.*;
	
public class Main {
	
	// BOJ 7569 - 토마토
	public static int m, n, l;
	public static int[] dr = {-1, 0, 1, 0, 0, 0}, dc = {0, -1, 0, 1, 0, 0}, dh = {0, 0, 0, 0, 1, -1};
	public static int[][][] board;
	
	// BFS 탐색
	public static void bfs(int h, int r, int c) {
		Deque<int[]> dq = new ArrayDeque<>();
		// 높이, 열, 행, 걸린날짜(+1) 순서로 정수배열을 만들어 queue에 삽입
		dq.offer(new int[] {h, r, c, 1});
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			// 현재 위치 및 날짜에 하루 더해서 현재 값으로 설정
			int ch = cur[0], cr = cur[1], cc = cur[2], lv = cur[3]+1;
			for(int dd = 0; dd < 6; dd++) {
				// 좌,상,우,하,위,아래 순서로 탐색
				int nh = ch + dh[dd], nr = cr + dr[dd], nc = cc + dc[dd];
				// 새로운 좌표가 배열에서 벗어나지 않는지 확인
				boolean in_range = 0 <= nh && nh < l && 0 <= nr && nr < n && 0 <= nc && nc < m;
				// 배열안에 있고, 아직 익지 않은 토마토이거나, 다른쪽에서 더 빨리 익게 할 수 있다면
				if(in_range && (board[nh][nr][nc] == 0 || board[nh][nr][nc] > lv)) {
					// 값 변경 및 queue에 추가
					board[nh][nr][nc] = lv;
					dq.offer(new int[] {nh, nr, nc, lv});
				}
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		board = new int[l][n][m];
		
		// 배열 입력
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < m; k++) {
					board[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		// 배열의 값이 1(초기에 익은 토마토)일때만 bfs 탐색
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					if(board[i][j][k] == 1) {
						bfs(i, j, k);
					}
				}
			}
		}
		
		int ans = -1;
		
		// 전체 순회하며 0(익지 않은 토마토)이 있다면 -1 출력 후 종료
		// 아니라면 최댓값(모두 다 익기 위해 필요한 날짜)을 구함
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					if(board[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
					else if(ans < board[i][j][k]) {						
						ans = board[i][j][k];
					}
				}
			}
		}
		
		// 초기 토마토가 1이었고, n일 뒤에 익은 토마토를 n+1로 표시하고 있으므로 ans에서 1빼고 출력 
		System.out.println(ans-1);
		return;
	}
}
