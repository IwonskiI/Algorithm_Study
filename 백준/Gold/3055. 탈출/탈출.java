import java.util.*;
import java.io.*;

public class Main {

	// BOJ 3055 - 탈출 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 장애물을 표시할 보드
		int[][] board = new int[N][M];
		// 4방향 탐색
		int[][] dd = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		// 방문 확인
		boolean[][] visited = new boolean[N][M];
		// 퍼질 물을 관리하는 리스트
		List<int[]> water = new ArrayList<>();
		// 이동을 관리할 큐
		Deque<int[]> move = new ArrayDeque<>();
		// 시작점과 도착점의 좌표
		int sr = 0, sc = 0, er = 0, ec = 0;
		// 초기값 입력
		for(int r = 0; r < N; r++) {
			String[] str = br.readLine().split("");
			for(int c = 0; c < M; c++) {
				// 도착점은 1로 설정, 좌표값 저장
				if(str[c].equals("D")) {
					er = r;
					ec = c;
					board[r][c] = 1;
				}
				// 시작점 좌표값 저장
				else if(str[c].equals("S")) {
					sr = r;
					sc = c;
					board[r][c] = 0;
				}
				// 장애물은 -1로 저장
				else if(str[c].equals("X")) board[r][c] = -1;
				// 물길은 -1로 저장 후, 추후에 퍼질 수 있도록 리스트에 추가
				else if(str[c].equals("*")) {
					water.add(new int[] {r, c});
					board[r][c] = -1;
				}
				// 빈 공간은 0
				else {
					board[r][c] = 0;
				}
			}
		}
		// 첫 이동 설정 (시작점의 좌표, 이동 횟수 입력)
		move.offer(new int[] {sr, sc, 0});
		// 방문 설정
		visited[sr][sc] = true;
		// 물길을 이동시킨 카운트
		int w_cnt = 0;
		// bfs 시작
		while(!move.isEmpty()) {
			// 현재 위치
			int[] cur = move.poll();
			int r = cur[0], c = cur[1], cnt = cur[2];
			// 도착했다면 이동 횟수 출력 후 종료
			if(r == er && c == ec) {
				System.out.println(cnt);
				return;
			}
			// 현재 이동 횟수와 물길의 카운트가 같다면 
			if(cnt == w_cnt) {
				// 물길 카운트 증가
				w_cnt++;
				// 새로운 물길 좌표를 저장할 temp
				List<int[]> temp = new ArrayList<>();
				// 기존 물길의 4방향 탐색
				for(int i = 0; i < water.size(); i++) {
					int[] w_cur = water.get(i);
					int wr = w_cur[0], wc = w_cur[1];
					for(int d = 0; d < 4; d++) {
						// 새 물 추가 및 물이 찬 것으로 -1 표시 
						int wnr = wr + dd[d][0], wnc = wc + dd[d][1];
						boolean in_wrange = (0 <= wnr && wnr < N) && (0 <= wnc && wnc < M);
						// 범위 안이고, 빈 길일때만(도착지로는 물 안흐름) 물 추가
						if(in_wrange && (board[wnr][wnc] == 0 && board[wnr][wnc] != 1)) {
							temp.add(new int[] {wnr, wnc});
							board[wnr][wnc] = -1;
						}
					}
				}
				// 새로운 물 업데이트
				water = temp;
			}
			// 물길 진행 후 이동
			for(int d = 0; d < 4; d++) {
				int nr = r + dd[d][0], nc = c + dd[d][1];
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
				// 범위 안이고, 빈 길이거나 도착지이고, 아직 방문 전이라면
				if(in_range && (board[nr][nc] == 0 || board[nr][nc] == 1) && !visited[nr][nc]) {
					// 현재 이동 카운트에 1을 더해서 queue에 추가
					move.offer(new int[] {nr, nc, cnt + 1});
					// 방문 체크
					visited[nr][nc] = true;
				}
			}
		}
		// 더 이상 이동할 수 없고, 도착하지 못했다면 KAKTUS 출력
		System.out.println("KAKTUS");
		return;
	}
}
