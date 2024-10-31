import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 15686 - 치킨 배달
	public static int N, M, ans = Integer.MAX_VALUE, h_cnt = 0, c_cnt = 0;
	public static int[] selected;
	public static int[][] board, home = new int[100][2], chicken = new int[13][2];
	
	
	public static int calc() {
		int sum = 0;
		
		// 모든 집의 모든 치킨집과의 거리를 계산 후 최솟값 저장
		for(int i = 0; i < h_cnt; i++) {
			int[] ch = home[i];
			int dist = Integer.MAX_VALUE;
			for(int j = 0; j < M; j++) {
				
				// 현재 선택된 치킨집
				int[] cc = chicken[selected[j]];
				
				// 치킨 거리 계산 후 최솟값 갱신
				dist = Math.min(dist, Math.abs(ch[0] - cc[0]) + Math.abs(ch[1] - cc[1]));
			}
			
			// 현재 집의 최소 치킨 거리 추가
			sum += dist;
		}
		
		// 현재 도시의 치킨 거리 return
		return sum;
	}
	
	public static void combi(int cnt, int start) {
		// M개의 치킨집을 뽑았다면
		if(cnt == M) {
			// 치킨 거리 계산 후 최솟값 갱신 후 종료
			ans = Math.min(ans, calc());
			return;
		}
		
		// M개의 치킨집 뽑기
		for(int i = start; i < c_cnt; i++) {
			selected[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		selected = new int[M];
		
		// 지도 입력
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				// 치킨 집 좌표 입력
				if(board[r][c] == 2) {
					chicken[c_cnt++] = new int[] {r, c};
				}
				// 집 좌표 입력
				else if(board[r][c] == 1) {
					home[h_cnt++] = new int[] {r, c};
				}
			}
		}
		
		// 치킨집 뽑는 조합 계산
		combi(0, 0);
		
		// 최소 치킨 거리 저장
		sb.append(ans);
		
		// 최종 결과 출력
		System.out.println(sb.toString());
	}
}