import java.io.*;
import java.util.*;

public class Main {

	// BOJ 9465 - 스티커
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 테스트 케이스 수 입력
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 스티커 세로 줄 수 입력
			int N = Integer.parseInt(br.readLine());
			// 스티커의 점수를 저장할 배열 - 0번 인덱스를 버리고 1번부터 N번까지 저장
			int[][] stk = new int[2][N+1];
			// 윗줄 아랫줄 2번 반복
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				// 1번 인덱스부터 스티커 점수 저장
				for(int j = 1; j <= N; j++) {
					stk[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// dp 배열 생성 - 0 : 윗줄 선택, 1: 아랫줄 선택, 2: 선택X
			int[][] score = new int[3][N+1];
			for(int i = 1; i <= N; i++) {
				for(int j = 0; j < 3; j++) {
					// 윗줄을 선택했을 때, 이전 칸에서 아랫줄을 선택했을때와 선택하지 않았을때 중에서 더 큰 값과 현재 스티커의 점수 합을 저장
					if(j == 0) score[0][i] = Math.max(score[1][i-1], score[2][i-1]) + stk[0][i];
					// 아랫줄을 선택했을 때, 이전 칸에서 윗줄을 선택했을때와 선택하지 않았을 때 중에서 더 큰 값과 현재 스티커의 점수 합을 저장
					else if(j == 1) score[1][i] = Math.max(score[0][i-1], score[2][i-1]) + stk[1][i];
					// 둘 다 선택하지 않았을 때, 이전 칸에서 윗줄을 선택했을 때와 아랫줄을 선택 했을 때 중에서 더 큰 값을 저장
					else score[2][i] = Math.max(score[0][i-1], score[1][i-1]);
				}
			}
			
			// N번째 칸까지 확인했을 때 가장 큰 점수를 저장
			sb.append(Math.max(Math.max(score[0][N], score[1][N]), score[2][N])).append("\n");
		}
		// 최종 결과 출력
		System.out.println(sb.toString());
		
	}

}
