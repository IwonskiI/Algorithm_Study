import java.io.*;


public class Main {

	// BOJ 9663 - N-Queen
	public static int N, ans;
	// 체스가 놓여있는 보드판
	public static int[] board;
	
	// 퀸이 공격받는 위치인지 체크
	public static boolean promise(int c) {
		// 0번줄부터 N번 줄까지 진행
		for(int cc = 0; cc < c; cc++) {
			// 행이 같은 경우가 있다면 return false
			if(board[c] == board[cc]) return false;
			// 열의 차이와 행의 차이가 같으면 대각선에 놓여있기 때문에 return false
			else if(Math.abs(c - cc) == Math.abs(board[c] - board[cc])) return false;
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
			board[cnt] = c;
			// 현재 칸에 퀸을 놓을 수 있는지 검사
			if(promise(cnt)) {
				// 가능하면 퀸 놓고 다음 줄 탐색
				queen(cnt + 1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	ans = 0;
	// 체스판의 크기 N 입력
	N = Integer.parseInt(br.readLine());
	// row는 인덱스로 col은 값으로 표현
	board = new int[N];
	
	// 첫번째 줄부터 놓을 수 있는 퀸 위치 탐색
	queen(0);
	
	// 경우의 수 출력
	System.out.println(ans);
		
	}

}
