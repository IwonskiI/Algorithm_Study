import java.io.*;
import java.util.*;


//BOJ2630 - 색종이 만들기
public class Main {
	
	// 전역 변수 선언
	static int N;
	static int[] ans = new int[2];
	static int[][] board;
	
	// 분할정복 알고리즘
	public static void chk_color(int c, int r, int x) {
		// 탐색 시작 색(기준)
		int cur_color = board[c][r];
		// 현재 범위가 한가지 색인지 판별
		boolean is_full = true;
		
		// 주어진 범위 내 전체 탐색
		for(int cc = c; cc < c + x; cc++) {
			// 이미 다른 색을 찾았다면 break
			if(!is_full) break;
			
			for(int rr = r; rr < r + x; rr++) {
				// 다른 색을 찾았다면 break
				if(board[cc][rr] != cur_color) {
					// 이중 for문 탈출을 위한 flag
					is_full = false;
					break;
				}
			}
		}
		
		// 한 가지 색으로만 이루어졌다면
		if(is_full) {
			// ans 배열의 현재 색 (0 : 흰색 / 1 : 파란색) 인덱스 1 더하기
			ans[cur_color]++;
			return;
		}
		// 여러가지 색이라면 분할정복
		else {
			//시작점 설정과 탐색 크기를 설정할 x 감소
			chk_color(c, r, x/2);
			chk_color(c, r + x/2, x/2);
			chk_color(c + x/2, r, x/2);
			chk_color(c + x/2, r + x/2, x/2);
			return;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		// board 입력받기
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int col = 0; col < N; col++) {
			st = new StringTokenizer(br.readLine());
			for(int row = 0; row < N; row++) {
				board[col][row] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 분할정복 시작
		chk_color(0, 0, N);
		
		// 
		System.out.println(ans[0]);
		System.out.println(ans[1]);

	}

}
