import java.io.*;
import java.util.*;

//int total = (int) Math.pow(2, 9) - 1;
//System.out.println(Integer.toBinaryString(total));

public class Main {
	
	// BOJ 2580 - 스도쿠
	public static int[][] board = new int[9][9];
	public static boolean complete = false;
	
	// 스도쿠 가능 조건 확인
	public static boolean check(int r, int c, int target) {
		// 확인해야할 숫자의 비트
		int chk = (1 << (target-1));
		
		// row, col 방향으로 채워져있는 숫자 체크
		int r_mask = 0, c_mask = 0;
		
		// 마스킹 처리
		for(int i = 0; i < 9; i++) {
			// row, col방향 체크
			int row = board[r][i], col = board[i][c];
			// 빈칸이면 스킵, 아니라면 마킹
			if(row != 0)
				r_mask |= (1 << (board[r][i]-1));
			if(col != 0)
				c_mask |= (1 << (board[i][c]-1));
		}
		
		// 사각형 체크
		int rr = (r / 3) * 3, cc = (c / 3) * 3, box_mask = 0;
		for(int i = rr; i < rr+3; i++) {
			for(int j = cc; j < cc+3; j++) {
				// 빈칸이 아니면 마스킹
				if(board[i][j] != 0)
					box_mask |= (1 << (board[i][j]-1));
			}
		}
		
		// 목표 숫자가 이미 존재한다면(비트가 겹친다면) false return; 
		if((chk & r_mask) == chk || (chk & c_mask) == chk || (chk & box_mask) == chk) return false;
		else return true;
	}
	
	public static void fill(int R, int C) {
		// R, C부터 시작
		int r = R, c = C;
		// 모든 칸 탐색
		for(; r < 9; r++) {
			for(; c < 9; c++) {
				// 빈칸이라면
				if(board[r][c] == 0) {
					// 1~9까지 대입
					for(int i = 1; i <= 9; i++) {
						// 해당 칸에 i를 넣을 수 있는지 체크
						if(check(r, c, i)) {
							// 만족한다면 i를 대입
							board[r][c] = i;
							// 다음 칸 확인
							fill(r, c+1);
							// 돌아왔을 때 모든 칸을 만족했다면 바로 return
							if(complete) return;
							// 다시 원래 상태로 복귀
							board[r][c] = 0;
						}
					}
					// 1~9까지 만족하는 숫자가 없다면 return
					return;
				}
			}
			// column 초기화
			c = 0;
		}
		// 마지막 칸까지 다 채웠다면 complete flag true
		if(r == 9 && c == 0) complete = true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 초기 보드 입력
		for(int r = 0; r < 9; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < 9; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	
		// 스도쿠 채우기 시작
		fill(0, 0);
		
		// 결과 값 저장
		for(int r = 0 ; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				sb.append(board[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		// 최종 결과 출력
		System.out.println(sb.toString());
	}
}