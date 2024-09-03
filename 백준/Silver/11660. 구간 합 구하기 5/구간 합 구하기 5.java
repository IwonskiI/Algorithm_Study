import java.io.*;
import java.util.*;


public class Main {

	// BOJ 11660 - 구간 합 구하기 5
	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder sb = new StringBuilder();
	StringTokenizer st = new StringTokenizer(br.readLine());
	
	// 배열 사이즈 N, 구간합 계산 횟수 M
	int N = Integer.parseInt(st.nextToken());
	int M = Integer.parseInt(st.nextToken());
	// N*N 배열 생성
	int[][] board = new int[N][N];
	// 가로로 구간합을 저장할 배열 생성
	int[][] sum = new int[N][N+1];
	for(int r = 0; r < N; r++) {
		st = new StringTokenizer(br.readLine());
		for(int c = 0; c < N; c++) {
			board[r][c] = Integer.parseInt(st.nextToken());
			// 이전까지의 합과 현재값을 더해서 sum배열에 저장
			sum[r][c+1] = board[r][c] + sum[r][c];
		}
	}
	
	// M번의 구간합 계산
	for(int i = 0; i < M; i++) {
		st = new StringTokenizer(br.readLine());
		// 시작 좌표 - (1,1)부터 시작이기 때문에 -1 처리
		int sr = Integer.parseInt(st.nextToken())-1, sc = Integer.parseInt(st.nextToken());
		// 끝 좌표 - (1,1)부터 시작이기 때문에 -1 처리
		int er = Integer.parseInt(st.nextToken())-1, ec = Integer.parseInt(st.nextToken());
		
		// 각 줄 별 합을 더할 total
		int total = 0;
		// 줄별로 누적합 계산
		for(int r = sr; r <= er; r++) {
			// ec까지의 합에서 sc 하나 앞칸 까지의 합을 빼면 sc부터 ec까지의 합을 구할 수 있음
			total += (sum[r][ec] - sum[r][sc-1]);
		}
		
		sb.append(total).append("\n");
	}
	
	System.out.println(sb.toString());
		
	}

}
