import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 17070 - 파이프 옮기기 1
	
	// 옮길 파이프 자리에 벽이 있는지 확인하는 함수
	public static boolean chk_board(int r, int c, int[][] board, int type) {
		boolean flag = true;
		// 이동 되는 파이프의 형태에 따라 확인하는 벽의 갯수가 다름
		switch(type) {
			// 가로라면 왼쪽 한칸만 확인
			case 0:
				if(board[r][c-1] == 1) flag = false;
				break;
			// 대각선이라면 왼쪽 한칸, 위쪽 한칸, 대각선 위로 한칸까지 확인
			case 1:
				if(board[r][c-1] == 1 || board[r-1][c] == 1 || board[r-1][c-1] == 1) flag = false;
				break;
			// 세로라면 위쪽 한칸만 확인
			case 2:
				if(board[r-1][c] == 1) flag = false;
				break;
		}
		return flag;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ans = 0;
		int N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		// 벽 입력
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 도착 지점에 벽이 있어서 막힌다면 0 출력 후 종료
		if(board[N-1][N-1] == 1) {
			System.out.println(0);
			return;
		}
		
		// Type - 가로 : 0 / 대각선 : 1 / 세로 : 2
		Deque<int[]> dq = new ArrayDeque<>();
		// 끝점에 도착할 수 있는 경우에서 시작 (세가지 형태 모두 추가)
		if(chk_board(N-1, N-1, board, 0)) dq.offer(new int[] {N-1, N-1, 0});
		if(chk_board(N-1, N-1, board, 1)) dq.offer(new int[] {N-1, N-1, 1});
		if(chk_board(N-1, N-1, board, 2)) dq.offer(new int[] {N-1, N-1, 2});
		// 다음 파이프를 이동 시킬 방향 (가로 , 대각선, 세로 순서)
		int[][] d = {{0, -1}, {-1, -1}, {-1, 0}};
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1], t = cur[2];
			// r2, c2 = 파이프의 나머지 한칸 좌표
			int r2 = 0, c2 = 0;
			// 기존 파이프 타입에 따른 다음 이동가능한 타입 체크
			int ds = 0, de = 0;
			switch(t) {
				// 가로
				case 0:
					r2 = r; 
					c2 = c - 1;
					// 가로는 가로, 대각선으로만 이동 가능
					ds = 0;
					de = 2;
					break;
				// 대각선
				case 1:
					r2 = r - 1;
					c2 = c - 1;
					// 대각선은 가로, 대각선, 세로 모두 이동 가능
					ds = 0;
					de = 3;
					break;
				// 세로
				case 2:
					r2 = r - 1;
					c2 = c;
					// 세로는 대각선, 세로만 이동 가능
					ds = 1;
					de = 3;
					break;
			}
			// 새로운 좌표가 범위 내인지 확인
			if(0 > r2 || r2 >= N || 0 > c2 || c2 >= N) continue;
			// 시작 좌표에 가로로 도착했으면 답 증가
			if(r2 == 0 && c2 == 0 && r == 0 && c == 1 && t == 0) ans++;
			// 위에서 정한 이동 방향으로 다음 파이프 체크
			for(int dd = ds; dd < de; dd++) {
				int nr = r2 + d[dd][0], nc = c2 + d[dd][1];
				// 다음 파이프가 범위 안에 있는지 체크
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
				// 범위 안에 있고, 벽이 없다면 dq에 추가
				if(in_range && chk_board(r2, c2, board, dd)) {
					dq.offer(new int[] {r2, c2, dd});
				}
			}
		}
		
		System.out.println(ans);
	}
}
