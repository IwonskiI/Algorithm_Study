import java.io.*;


public class Main {
	
	// BOJ 1992 - 쿼드트리
	public static StringBuilder sb = new StringBuilder();
	public static int[][] board;
	
	// 분할정복 알고리즘
	public static String divide(int r, int c, int x) {
		// 탐색 기준
		int cur = board[r][c];
		// 현재 범위가 한 가지로 통일되는지 확인
		boolean is_full = true;
		
		// 주어진 범위 전체 탐색
		for(int rr = r; rr < r + x; rr++) {
			// 이미 다른 색을 찾았다면 break
			if(!is_full) break;
			
			for(int cc = c; cc < c + x; cc++) {
				// 다른 색을 찾았다면 break
				if(board[rr][cc] != cur) {
					// 이중 for문 탈출을 위한 flag
					is_full = false;
					break;
				}
			}
		}
		
		// 한 가지 색으로만 이루어졌다면 현재 인덱스 추가
		if(is_full) sb.append(cur);
		// 아니라면 분할정복 시작
		else {
			// 괄호로 묶고 시작			
			sb.append("(");
			// 시작점 설정과 탐색 크기로 설정할 x 감소
			divide(r,  c,  x/2);
			divide(r,  c + x/2,  x/2);
			divide(r + x/2,  c,  x/2);
			divide(r + x/2,  c + x/2,  x/2);
			sb.append(")");
		}
		return "";
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		// board 입력받기
		for(int r = 0; r < N; r++) {
			String[] str = br.readLine().split("");
			for(int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(str[c]);
			}
		}
		// 결과 저장
		sb.append(divide(0,0, N));		
		
		// 정답 출력
		System.out.println(sb.toString());
	}
}
