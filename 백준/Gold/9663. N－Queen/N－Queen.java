import java.io.*;


public class Main {

	// BOJ 9663 - N-Queen
	public static int N, ans;
	// 체스가 놓여있는 보드판
	public static boolean[][] board;
	
	// 퀸이 공격받는 위치인지 체크
	public static boolean promise(int r, int c) {
		// 0번줄부터 N번 줄까지 진행
		for(int rr = 0; rr < N; rr++) {
			// 현재 칸이면 스킵
			if(rr == r) continue;
			// 현재 칸과 같은 세로줄에 퀸이 있으면 false
			if(board[rr][c]) return false;
			// 대각선 계산
			int diag = rr - r;
			// 양쪽 대각선에 퀸이 있으면 false
			if((0 <= c - diag && c - diag < N) && board[rr][c - diag]) return false;
			if((0 <= c + diag && c + diag < N) && board[rr][c + diag]) return false;
		}
		// 모든 줄 검사 후 통과하면 true
		return true;
	}
	
	public static void queen(int cnt) {
		// N개의 퀸을 다 놓으면 정답 추가
		if(cnt == N) {
			ans++;
			return;
		}
		// 아니라면 퀸 놓기
		for(int c = 0; c < N; c++) {
			// 현재 칸에 퀸을 놓을 수 있는지 검사
			if(promise(cnt, c)) {
				// 가능하면 퀸 놓고 다음 줄 탐색
				board[cnt][c] = true;
				queen(cnt + 1);
				// 탐색 완료 후 퀸 제거
				board[cnt][c] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	ans = 0;
	// 체스판의 크기 N 입력
	N = Integer.parseInt(br.readLine());
	board = new boolean[N][N];
	
	// 첫번째 줄부터 놓을 수 있는 퀸 위치 탐색
	queen(0);
	
	// 경우의 수 출력
	System.out.println(ans);
		
	}

}
