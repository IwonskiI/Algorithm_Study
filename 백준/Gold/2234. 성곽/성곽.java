import java.io.*;
import java.util.*;

public class Main {
	
	//BOJ 2234 - 성곽
	public static int[][] d = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		// 성곽의 상태 board, 방문 배열(방끼리 같은 숫자) visited
		int[][] board = new int[N][M], visited = new int[N][M];
		// 각 방의 크기를 저장할 room map
		HashMap<Integer, Integer> room = new HashMap<>();
		
		// 초기 입력
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 방 번호
		int roomIdx = 1;
		// 최대 방 크기
		int maxRoom = 0;
		
		// 모든 방 탐색
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				// 이미 방문한 방이면 스킵
				if(visited[r][c] > 0) continue;
				// 새로운 방 탐색 시작 - 방크기 1칸부터 시작
				int roomCnt = 1;
				Deque<int[]> dq = new ArrayDeque<>();
				dq.offer(new int[] {r, c});
				// 현재 위치를 현재 방 번호로 저장
				visited[r][c] = roomIdx;
				// BFS
				while(!dq.isEmpty()) {
					int[] cur = dq.poll();
					int cr = cur[0], cc = cur[1];
					// 현재 칸의 벽 상태
					int cell = board[cr][cc];
					// 4방향 탐색
					for(int dd = 0; dd < 4; dd++) {
						// 현재 방향으로 나아갔을 때 방 좌표
						int nr = cr + d[dd][0], nc = cc + d[dd][1];
						// 현재 방향에 벽이 없고, 아직 방문하기 전 방이면
						if((cell & (1 << dd)) == 0 && visited[nr][nc] == 0) {
							// 해당 방을 탐색 queue에 추가
							dq.offer(new int[] {nr, nc});
							// 현재 방번호로 저장 및 방문 처리
							visited[nr][nc] = roomIdx;
							// 방 개수 증가
							roomCnt++;
						}
					}
				}
				// 탐색이 끝나면 현재 방 번호의 방 크기를 map에 저장
				room.put(roomIdx, roomCnt);
				// 방번호 증가
				roomIdx++;
				// 최대 방 크기 갱신
				maxRoom = Math.max(maxRoom, roomCnt);
			}
		}
		
		
		// 벽 뚫어서 최대 방 구하기
		int afterRoom = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				// 현재 칸에서 4방향 탐색
				for(int dd = 0; dd < 4; dd++) {
					// 현재 방향에서의 새로운 좌표
					int nr = r + d[dd][0], nc = c + d[dd][1];
					// 범위 체크
					boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
					// 범위 안에 있고, 새로운 좌표가 다른 방이라면
					if(in_range && visited[r][c] != visited[nr][nc]) {
						// 그 방을 뚫었을 때 방의 크기와 현재까지의 최대 방 크기 중 큰 것을 저장
						afterRoom = Math.max(afterRoom, room.get(visited[r][c]) + room.get(visited[nr][nc]));
					}
				}
			}
		}
		
		// 방 번호, 최대 방 크기, 벽 뚫고 난 뒤 최대 방 크기 저장
		sb.append(roomIdx-1).append("\n").append(maxRoom).append("\n").append(afterRoom);
		// 최종 결과 출력
		System.out.println(sb.toString());
	}
}