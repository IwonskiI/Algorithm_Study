import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 17836 - 공주님을 구해라!
	public static int R, C, T;
	public static int[][] board, b_visited, a_visited, d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static boolean fin = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 배열의 크기 및 제한 시간 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		// 길 배열 및 방문 배열 선언
		board = new int[R][C];
		// 방문 배열은 도착한 시간을 기록하기 위해 int형 배열로 선언
		// 그람을 얻기 전 방문 배열과 그람을 얻고난 뒤 방문 배열로 구분
		b_visited = new int[R][C];
		a_visited = new int[R][C];
		
		// 방문배열의 초기값은 시간의 최대치 이상으로 초기화
		for(int r = 0; r < R; r++) {			
			Arrays.fill(b_visited[r], 10010);
			Arrays.fill(a_visited[r], 10010);
		}
		
		// 초기 입력
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// BFS 탐색
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {0, 0, 0});
		// 첫 시작 지점에서는 그람이 없고 걸리는 시간은 0이므로 b방문 배열을 0으로 초기화
		b_visited[0][0] = 0;
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			// 현재 좌표 및 그람의 획득 현황(0 미획득 / 1 획득)
			int r = cur[0], c = cur[1], k = cur[2], t = -1;
			// 그람을 획득했다면 a배열이 현재 위치까지의 시간
			if(k == 1) t = a_visited[r][c];
			// 아니라면 b배열이 현재 위치까지의 시간
			else t = b_visited[r][c];
			// 현재 시간이 이미 제한 시간을 넘었거나 도착 지점에 도달했다면 탐색하지 않고 스킵
			if((t > T) || (r == R-1 && c == C-1)) continue;
			// 현재 칸에 그람이 있다면 그람 획득 후 a배열의 현재 위치를 현재 시간으로 설정
			if(board[r][c] == 2) {
				a_visited[r][c] = t;
				k = 1;
			}
			// 4방향 탐색
			for(int dd = 0; dd < 4; dd++) {
				// 새 좌표 계산 및 범위 체크
				int nr = r + d[dd][0], nc = c + d[dd][1];
				boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
				// 범위 안에 있다면,
				if(in_range) {
					// 그람을 획득했고, 이동하려는 좌표까지 걸린시간이 현재시간 +1보다 크다면,
					if(k == 1 && a_visited[nr][nc] > t+1) {
						// 해당 위치 탐색 및 시간 갱신
						dq.offer(new int[] {nr, nc, k});
						a_visited[nr][nc] = t+1;
					}
					// 그람 획득 이전이고, 이동하려는 곳이 벽이 아니고, 이동하려는 좌표까지 걸린 시간이 현재시간+1보다 크다면, 
					else if(k == 0 && board[nr][nc] != 1 && b_visited[nr][nc] > t+1) {
						// 해당 위치 탐색 및 시간 갱신
						dq.offer(new int[] {nr, nc, k});
						b_visited[nr][nc] = t+1;
					}
				}
			}
			
		}
		// 그람을 획득하고 도착한 시간과 그렇지 않은 시간을 비교 - 더 작은 값 저장
		int ans = Math.min(a_visited[R-1][C-1], b_visited[R-1][C-1]);
		// 시간 내에 도착해서 ans가 10010이 아니라면, ans 추가
		if(ans != 10010) sb.append(ans).append("\n");
		// 만약 둘 다 시간내에 도착하지 못해서 ans가 초기값 10010이라면, fail 추가
		else sb.append("Fail\n");
		
		
		// 최종 결과 출력
		System.out.println(sb.toString());
	}

}