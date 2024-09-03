import java.io.*;
import java.util.*;

public class Main {

	// BOJ 4485 - 녹색 옷 입은 애가 젤다지?
	public static int N;
	public static int[][] board, score, d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	// 다익스트라 알고리즘
	public static void dijkstra() {
		// 비용이 적은 순서대로 뽑기 위한 우선순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> o1[2] - o2[2]);
		// 0,0 부터 출발
		pq.offer(new int[] {0, 0, board[0][0]});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0], c = cur[1], cost = cur[2];
			// 새로운 비용보다 현재까지의 비용이 더 적다면 스킵
			if(cost > score[r][c]) continue;
			// 4방향 탐색
			for(int dd = 0; dd < 4; dd++) {
				// 새 좌표
				int nr = r + d[dd][0], nc = c + d[dd][1];
				// 범위 체크
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
				// 범위 안이라면
				if(in_range) {
					// 새로운 비용 계산
					int new_cost = cost + board[nr][nc];
					// 새 비용이 기존 비용보다 더 적다면 갱신
					if(score[nr][nc] > new_cost) {
						// 비용 갱신
						score[nr][nc] = new_cost;
						// 다음 탐색 위치로 추가
						pq.offer(new int[] {nr, nc, new_cost});
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 테스트 케이스 번호 출력
		int tc = 0;
		while(true) {
			tc++;
			N = Integer.parseInt(br.readLine());
			// 0이 입력될 때 까지 입력 반복
			if(N == 0) break;
			// 출력 형식 맞추기
			sb.append("Problem ").append(tc).append(": ");
			// 입력을 위한 2차원 배열
			board = new int[N][N];
			// 초기 입력 
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 해당 위치까지의 점수를 계산할 배열
			score = new int[N][N];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					// 초기 값을 INF로 초기화
					score[r][c] = Integer.MAX_VALUE;
				}
			}
			
			// 다익스트라 알고리즘 적용해서 (0,0)부터 (N-1,N-1)까지의 최소 비용 거리 계산  
			dijkstra();
			
			// (N-1, N-1)까지의 비용 저장
			sb.append(score[N-1][N-1]).append("\n");
		}
		
		// 최종 결과 출력
		System.out.println(sb.toString());
		
	}

}