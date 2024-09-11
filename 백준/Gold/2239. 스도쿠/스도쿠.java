import java.io.*;


public class Main {
	
	// BOJ 2239 - 스도쿠
	public static boolean fin;
	public static int[][] board;
	
	// r, c에 x를 놓을 수 있는지 검사
	public static boolean promise(int r, int c, int x) {
		// 가로 세로에 같은 숫자가 있는지 검사
		for(int i = 0; i < 9; i++) {
			if(board[r][i] == x) return false;
			if(board[i][c] == x) return false;
		}
		
		// 3*3 정사각형을 검사하기 위한 시작 좌표
		int sr = r - (r % 3), sc = c - (c % 3);
		
		// 3*3 배열 검사
		for(int nr = sr; nr < sr + 3; nr++) {
			for(int nc = sc; nc < sc + 3; nc++) {
				if(board[nr][nc] == x) return false;
			}
		}
		
		return true;
	}
	
	// dfs 함수
	public static void dfs(int row, int col) {
		// 마지막 칸에 도달했다면 종료 flag true로 설정하고 return
		if(row == 8 && col == 8) {
			fin = true;
			return;
		}
		
		// 시작점(이전에 숫자를 채운 칸)부터 탐색하며 빈칸이 아닐경우 1부터 차례로 넣어보며 탐색
		for(int r = row; r < 9; r++) {
			for(int c = col; c < 9; c++) {
				// 현재 칸이 빈 칸이 아니라면,
				if(board[r][c] != 0) {
					// 빈칸이 아니면서 마지막 칸이라면 탐색 종료
					if(r == 8 && c == 8) {
						fin = true;
						return;
					}
					// 마지막 칸이 아니라면 스킵 후 계속 진행
					else continue;
				}
				// 빈칸이라면 1부터 9까지 순서대로 입력
				for(int i = 1; i <= 9; i++) {
					// 만약 놓을 수 있는 숫자라면, 
					if(promise(r, c, i)) {
						// 해당 칸에 i를 넣고 다음 칸 탐색
						board[r][c] = i;
						dfs(r, c);
						// 탐색 종료 후 마지막까지 다 채워진 상태라면 종료
						if(fin) return;
						// 아니라면 0으로 돌려놓은 뒤 계속 진행
						board[r][c] = 0;
					}
				}
				// 1부터 9까지 다 탐색 후 해당 칸을 어떤 숫자로도 채우지 못했다면 백트래킹
				if(board[r][c] == 0)return;
			}
			// col에서 진행하다가 마지막까지 탐색 종료 후 다음 줄로 넘어가기 위해 col값 0으로 초기화
			col = 0;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 스도쿠를 저장할 보드판
		board = new int[9][9];
		// 스도쿠가 완성되었는지 나타낼 flag 변수
		fin = false;
		
		// 초기 입력
		for(int r = 0; r < 9; r++) {
			String[] str = br.readLine().split("");
			for(int c = 0; c < 9; c++) {
				board[r][c] = Integer.parseInt(str[c]);
			}
		}
		
		// 0,0부터 탐색 
		dfs(0, 0);
		
		// 스도쿠 입력 완료 후 정답 저장 후 출력
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				sb.append(board[r][c]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

}