import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 15683 - 감시
	public static int N, M, ans, len;
	public static int[][] board, d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static List<int[]> cam;
	
	// 0 : 상 / 1 : 우 / 2: 하 / 3: 좌
	// 현재 위치에서 주어진 방향으로 1 더하기
	public static void fill(int r, int c, int dir) {
		int nr = r + d[dir][0], nc = c + d[dir][1];
		while(0 <= nr && nr < N && 0 <= nc && nc < M) {
			if(board[nr][nc] < 0) break;
			board[nr][nc] += 1;
			nr = nr + d[dir][0];
			nc = nc + d[dir][1];
		}
	}
	// 현재 위치에서 주어진 방향으로 1 빼기
	public static void remove(int r, int c, int dir) {
		int nr = r + d[dir][0], nc = c + d[dir][1];
		while(0 <= nr && nr < N && 0 <= nc && nc < M) {
			if(board[nr][nc] < 0) break;
			board[nr][nc] -= 1;
			nr = nr + d[dir][0];
			nc = nc + d[dir][1];
		}
	}
	// 감시카메라로 보지 못하는 부분(0)의 칸 수 체크
	public static int chk_blank() {
		int res = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(board[r][c] == 0) res++;
			}
		}
		return res;
	}
	
	//감시 카메라의 타입에 따라 칸수 증가
	public static void chk_cam(int start) {
		// 모든 카메라를 세팅 완료하면 사각지대 체크
		if(len == start) {
			// 현재 값과 이전값 중 최솟값 저장
			ans = Math.min(ans, chk_blank());
		}
		// 남은 카메라가 있다면 방향 세팅
		else {
			// 현재 카메라 가져오기
			int[] c_cam = cam.get(start);
			// r,c 좌표 / t 카메라 타임
			int r = c_cam[0], c = c_cam[1], t = c_cam[2];
			
			// 타입에 따라 감시 카메라 위치 체크
			switch(t) {
			// 1번 타입
			case 1:
				// 한 방향씩 감시하며 돌아가기
				for(int j = 0; j < 4; j++) {
					fill(r, c, j);
					chk_cam(start+1);
					remove(r, c, j);
				}
				break;
			// 2번 타입
			case 2:
				//세로 방향 아니면 가로방향 2번 체크
				for(int j = 0; j < 2; j++) {
					fill(r, c, j);
					fill(r, c, j+2);
					chk_cam(start+1);
					remove(r, c, j);
					remove(r, c, j+2);
				}
				break;
			// 3번 타입
			case 3:
				// 위쪽부터 채워놓고 시계방향으로 채우면서 이전 방향을 지움
				fill(r, c, 0);
				for(int j = 0; j < 4; j++) {
					fill(r, c, (j+1) % 4);
					chk_cam(start + 1);
					remove(r, c, j);
				}
				remove(r, c, 0);
				break;
			// 4번 타입
			case 4:
				// 위쪽, 오른쪽 채워놓고 시계방향으로 채우면서 이전 방향을 지움 
				fill(r, c, 0);
				fill(r, c, 1);
				for(int j = 0; j < 4; j++) {
					fill(r, c, (j+2) % 4);
					chk_cam(start+1);
					remove(r, c, j);
				}
				remove(r, c, 0);
				remove(r, c, 1);
				break;
			// 5번 타입
			case 5:
				// 모든 방향을 채움
				for(int j = 0; j < 4; j++) {
					fill(r, c, j);
				}
				chk_cam(start + 1);
				for(int j = 0; j < 4; j++) {
					remove(r, c, j);
				}
				break;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = Integer.MAX_VALUE;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		cam = new ArrayList<>();
		// 초기 값 입력
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				int num = Integer.parseInt(st.nextToken());
				// 빈칸이 아니라면
				if(num != 0) {
					// 벽이 아니라면
					if(num != 6) {
						// 카메라 체크
						board[r][c] = 1;
						// 카메라 추가
						cam.add(new int[] {r, c, num});
					}
					// 벽이라면 -1 표시 (나중에 감시 범위에서 막히게 하기 위해 음수로 설정)
					else board[r][c] = -1;
				}
			}
		}
		// 카메라 개수
		len = cam.size();
		// 카메라 감시 범위 확인
		chk_cam(0);
		// 정답 출력
		System.out.println(ans);
	}
}
