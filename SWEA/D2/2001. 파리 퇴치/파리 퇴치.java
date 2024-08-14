import java.io.*;
import java.util.*;


public class Solution {
	
	// SWEA 2001 - 파리 퇴치
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 테스트 케이스 수 입력
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 최대로 죽일 수 있는 파리의 수 저장할 변수
			int ans = 0;
			// 초기 값 입력
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
			// 파리 수를 저장할 보드
			int[][] board = new int[N][N];
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// N*N 배열에서 M*M만큼의 파리채로 파리를 잡으므로 왼쪽위를 기준으로 M*M크기를 탐색하면 됨
			// 가장 오른쪽 아래를 탐색할때는 board[N-M][N-M]를 확인해야하므로 N-M까지 탐색
			for(int r = 0; r <= N-M; r++) {
				for(int c = 0; c <= N-M; c++) {
					// M*M 크기에 들어있는 파리의 총 합 구하기
					int sum = 0;
					for(int i = r; i < M+r; i++) {
						for(int j = c; j < M+c; j++) {
							sum += board[i][j];
						}
					}
					// 이전의 값과 현재 합과 비교해서 더 큰 값 저장
					ans = ans > sum ? ans : sum;
				}
			}
			// 결과 저장
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		// 저장된 결과값 출력
		System.out.println(sb.toString());
	}
	
}