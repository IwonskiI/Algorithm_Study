import java.util.*;
import java.io.*;

public class Main {

	// BOJ 1080 - 행렬
	public static int[][] board, ans_board;
	public static int row, col, ans;
	
	// 3*3 카드의 0 - 1 뒤집기
	public static void turn(int r, int c) {
		for(int i = r; i < r+3; i++) {
			for(int j = c; j < c+3; j++) {
				board[i][j] = board[i][j] == 1 ? 0 : 1;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = 0;
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		board = new int[row][col];
		ans_board = new int[row][col];
		
		// 바뀌기 전 배열 입력
		for(int i = 0; i < row; i++) {
			String[] line = br.readLine().split("");
			for(int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		// 바뀌어야 할 배열 입력
		for(int i = 0; i < row; i++) {
			String[] line = br.readLine().split("");
			for(int j = 0; j < col; j++) {
				ans_board[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		//만약 카드를 뒤집을 수 없다면
		if(row < 3 || col < 3) {
			// 현재 상태의 배열이 바뀌어야할 배열과 일치하는지 확인
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					// 배열이 일치하지 않는다면 -1 출력후 종료
					if(board[i][j] != ans_board[i][j]) {
						System.out.println(-1);
						return;
					}
				}
			}
			// 배열이 일치한다면 뒤집지 않았기 때문에 0 출력 후 종료
			System.out.println(0);
			return;
		}
		
		// 3*3 행렬의 가장 왼쪽 위를 비교한 뒤, 일치하지 않으면 뒤집기 실행, 횟수 추가
		for(int i = 0; i < row-2; i++) {
			for(int j = 0; j < col-2; j++) {
				if(ans_board[i][j] != board[i][j]) {
					ans++;
					turn(i, j);
				}
			}
		}
		
		
		// 뒤집기 완료 후 패턴이 일치하는지 확인
		// 오른쪽 끝 세로 두줄 (제일 아래 2줄 빼고) 확인
		for(int i = 0; i < row-2; i++) {
			for(int j = col -2; j < col; j++) {
				if(ans_board[i][j] != board[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		// 아래쪽 끝 가로 두줄 확인 
		for(int i = row - 2; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(ans_board[i][j] != board[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		// 모두 맞춰졌다면 뒤집은 횟수 출력
		System.out.println(ans);
	}
}
