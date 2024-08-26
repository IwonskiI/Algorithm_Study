import java.io.*;
import java.util.*;

public class Main{
	
	// BOJ 2931 - 가스관
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 4방향 탐색 (상 : 0 / 우 : 1 / 하 : 2 / 좌 : 3)
		int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] board = new char[R][C];
		// 지나가야하는 횟수를 기록
		int[][] visited = new int[R][C];
		// 시작점을 저장 (M위치)
		int sr = 0, sc = 0;
		for(int r = 0; r < R; r++) {
			String str = br.readLine();
			for(int c = 0; c < C; c++) {
				board[r][c] = str.charAt(c);
				// 시작점 저장
				if(board[r][c] == 'M') {
					sr = r;
					sc = c;
				}
				// .은 스킵
				else if(board[r][c] == '.') continue;
				// + 가스관은 2번 지나야함
				else if(board[r][c] == '+') visited[r][c] = 2;
				// 나머지 가스관은 1번만 지나야함
				else visited[r][c] = 1;
			}
		}
		
		// 시작 방향 찾기
		int sdir = -1;
		// 4방향 탐색
		for(int dd = 0; dd < 4; dd++) {
			int nr = sr + d[dd][0], nc = sc + d[dd][1];
			// 범위 체크
			boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
			// 범위 안이고, .이나 Z가 아니면(가스관이면)
			if(in_range && board[nr][nc] != '.' && board[nr][nc] != 'Z') {
				// 위로 가는 방향일 때 1, 4, |, + 가스관이거나, 오른쪽으로 갈 때, 3, 4, -, + 가스관이거나, 아래로 갈 때, 2, 3, |, + 가스관이거나, 왼쪽으로 갈 때 1, 2, -, + 관인지 체크
				if((dd == 0 && (board[nr][nc] == '1' || board[nr][nc] == '4' || board[nr][nc] == '|' || board[nr][nc] == '+')) 
						|| (dd == 1 && (board[nr][nc] == '3' || board[nr][nc] == '4' || board[nr][nc] == '-' || board[nr][nc] == '+')) 
						|| (dd == 2 && (board[nr][nc] == '2' || board[nr][nc] == '3' || board[nr][nc] == '|' || board[nr][nc] == '+')) 
						|| (dd == 3 && (board[nr][nc] == '1' || board[nr][nc] == '2' || board[nr][nc] == '-' || board[nr][nc] == '+'))) {
					// 시작점에서 출발하는 가스관은 반드시 한 개 연결 되어 있으므로 시작점에서 연결된 가스관을 찾았다면 해당 방향을 진행방향으로 설정하고 반복문 종료 
					sdir = dd;
					break;
				}
			}
		}
		
		// 이동 좌표 관리할 queue
		Deque<int[]> dq = new ArrayDeque<>();
		// 시작점과 방향 입력
		dq.offer(new int[] {sr, sc, sdir});
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			// 이동 방향대로 이동한 새로운 좌표
			int nr = cur[0] + d[cur[2]][0], nc = cur[1] + d[cur[2]][1], ndir = cur[2];
			// 이동한 칸 위치의 문자에 따라 case 처리
			switch(board[nr][nc]) {
			// |, -, +의 경우 현재 진행방향 그대로 진행
			case'|':
			case'-':
			case'+':
				// 한번 지나감을 표시
				visited[nr][nc]--;
				// 새 위치의 좌표와 기존 방향 그대로 추가
				dq.offer(new int[] {nr, nc, ndir});
				break;
			// 1, 2, 3, 4번 가스관은 한 번 지나감을 체크해주고, 들어온 방향에 따라 다음 방향 변경
			case'1':
				// 방문 체크
				visited[nr][nc]--;
				// 방향 체크 - 아래쪽에서 들어왔다면 오른쪽으로 나가기, 아니면(왼쪽에서 들어왔다면) 아래로 나가기
				ndir = cur[2] == 0 ? 1 : 2;
				// 새 위치의 좌표와 변경된 방향 추가
				dq.offer(new int[] {nr, nc, ndir});
				break;
			case'2':
				// 방문 체크
				visited[nr][nc]--;
				// 방향 체크 - 오른쪽에서 들어왔다면 위로 나가기, 아니면(위쪽에서 들어왔다면) 왼쪽으로 나가기 
				ndir = cur[2] == 3 ? 0 : 1;
				// 새 위치의 좌표와 변경된 방향 추가
				dq.offer(new int[] {nr, nc, ndir});
				break;
			case'3':
				// 방문 체크
				visited[nr][nc]--;
				// 방향 체크 - 위쪽에서 들어왔다면 왼쪽으로 나가기, 아니면(오른쪽에서 들어왔다면) 위쪽으로 나가기
				ndir = cur[2] == 2 ? 3 : 0;
				// 새 위치의 좌표와 변경된 방향 추가
				dq.offer(new int[] {nr, nc, ndir});
				break;
			case'4':
				// 방문 체크
				visited[nr][nc]--;
				// 방향 체크 - 왼쪽에서 들어왔다면 아래로 나가기, 아니면(아래쪽에서 들어왔다면) 오른쪽으로 나가기
				ndir = cur[2] == 1 ? 2 : 3;
				// 새 위치의 좌표와 변경된 방향 추가
				dq.offer(new int[] {nr, nc, ndir});
				break;
			// .을 만난다면 빈 가스관 도착 (맞는 가스관 찾기)
			case'.':
				// . 칸에서 연결되어야하는 가스관의 개수 체크
				int chk = 0;
				// chk가 1보다 크면 +가, 아니라면 각 방향에 맞는 가스관을 연결해야하므로 어느 위치에 연결되어야하는 가스관이 있는지 저장
				int chk_dir = -1;
				// 4방향 탐색
				for(int i = 0; i < 4; i++) {
					// .에서 상,우,하,좌 탐색 (rr, cc는 새로 이동한 좌표)
					int rr = nr + d[i][0], cc = nc + d[i][1];
					// 새 좌표가 범위 안인지 체크
					boolean in_range = (0 <= rr && rr < R) && (0 <= cc && cc < C);
					// 범위 안이지만, 지나갈 가스관이 없다면 continue
					if(in_range && visited[rr][cc] == 0) continue;
					// 범위 안이고, 지나갈 가스관이 있다면
					if(in_range) {
						// 현재 위로 진행중이고, 다음 칸의 가스관이 1, 4, |, +이거나, 오른쪽으로 진행중이고, 다음칸이 3, 4, -, +이거나, 아래로 진행중이고, 다음칸이 2, 3, |, +이거나, 왼쪽으로 진행중이고, 1, 2, -, +라면
						if((i == 0 && (board[rr][cc] == '1' || board[rr][cc] == '4' || board[rr][cc] == '|' || board[rr][cc] == '+')) 
								|| (i == 1 && (board[rr][cc] == '3' || board[rr][cc] == '4' || board[rr][cc] == '-' || board[rr][cc] == '+')) 
								|| (i == 2 && (board[rr][cc] == '2' || board[rr][cc] == '3' || board[rr][cc] == '|' || board[rr][cc] == '+')) 
								|| (i == 3 && (board[rr][cc] == '1' || board[rr][cc] == '2' || board[rr][cc] == '-' || board[rr][cc] == '+'))) {
							// 연결된 가스 방향 저장
							chk_dir = i;
							// 연결이 필요한 가스 수 추가
							chk++;
						}
					}
				}
				// 답을 저장할 변수
				char ans = '0';
				// chk가 1이라면(.에서 이어질 가스관이 하나밖에 없다면), 진행방향과 연결 방향에 따라 가스관 선택
				if(chk == 1) {
					// 진행방향이 위쪽일 때,
					if(cur[2] == 0) {
						// 나가야하는 방향이 오른쪽이라면
						if(chk_dir == 1) ans = '1';
						// 왼쪽이라면
						else if(chk_dir == 3) ans = '4';
						// 위쪽이라면
						else if(chk_dir == 0) ans = '|';
					}
					// 진행방향이 오른쪽일 때,
					else if(cur[2] == 1) {
						// 나가야하는 방향이 위쪽이라면
						if(chk_dir == 0) ans = '3';
						// 아래쪽이라면
						else if(chk_dir == 2) ans = '4';
						// 오른쪽이라면
						else if(chk_dir == 1) ans = '-';
					}
					// 진행방향이 아래쪽일 때,
					else if(cur[2] == 2) {
						// 나가야하는 방향이 오른쪽이라면
						if(chk_dir == 1) ans = '2';
						// 왼쪽이라면
						else if(chk_dir == 3) ans = '3';
						// 아래쪽이라면
						else if(chk_dir == 2) ans = '|';
					}
					// 진행방향이 왼쪽일 때,
					else if(cur[2] == 3) {
						// 나가야하는 방향이 위쪽이라면
						if(chk_dir == 0) ans = '2';
						// 아래쪽이라면
						else if(chk_dir == 2) ans = '1';
						// 왼쪽이라면
						else if(chk_dir == 3) ans = '-';
					}
					// 1,1에서 시작하므로 nr+1, nc+1 출력 후, ans값 출력 후 종료
					System.out.println((nr+1) + " " + (nc+1) + " " + ans);
					return;
				}
				// 연결해야하는 가스관이 1개가 아니라면, 3군데 다 연결해야함 (+ 가스관 연결)
				else {
					// 1,1에서 시작하므로 nr+1, nc+1 출력 후, + 출력 후 종료
					System.out.println((nr+1) + " " + (nc+1) + " +");
					return;
				}
			}
			
		}
		
	}
}