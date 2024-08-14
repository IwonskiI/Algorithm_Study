import java.io.*;
import java.util.*;


public class Solution {
	
	// SWEA 4013 - [모의 SW 역량테스트] 특이한 자석
	
	// 자석과 회전에 대한 정보를 저장할 배열 선언
	public static int[][] magnets, cases;
	
	// 자석 회전 함수
	public static void turn_mag(int num, int dir) {
		// 가장 끝 값을 임시 저장할 변수
		int temp = 0;
		// 시계방향 회전
		if(dir == 1) {
			// 마지막 값을 저장
			temp = magnets[num][7];
			for(int i = 7; i > 0; i--) {
				// 끝에서부터 하나 앞자리를 땡겨옴 (시계방향 회전)
				magnets[num][i] = magnets[num][i - 1];
			}
			// 가장 앞자리에 기존 마지막 값을 저장
			magnets[num][0] = temp;
		}
		// 반시계방향 회전
		else if(dir == -1) {
			// 첫번째 값을 저장
			temp = magnets[num][0];
			for(int i = 0; i < 7; i++) {
				// 처음부터 하나 뒷자리를 땡겨옴 (반시계방향 회전)
				magnets[num][i] = magnets[num][i+1];
			}
			// 가장 마지막에 기존 첫번째 값을 저장
			magnets[num][7] = temp;
		}
	}
	
	// 자석의 회전 여부 확인 함수
	public static void chk_mag(int num, int dir) {
		// 이번 차례에 회전했는지 확인하는 boolean 배열
		boolean[] visited = new boolean[4];
		// 회전할 자석을 관리할 queue
		Deque<int[]> q = new ArrayDeque<>();
		// 시작 자석 방문 처리 및 queue 삽입
		visited[num] = true;
		q.offer(new int[] {num, dir});
		// 모든 자석 탐색 반복
		while(!q.isEmpty()){
			// 현재 자석
			int[] cur = q.poll();
			// 현재 자석의 우측 값
			int right = magnets[cur[0]][2];
			// 현재 자석의 좌측 값
			int left = magnets[cur[0]][6];
			// 오른쪽으로 확인할 자석이 있고, 오른쪽 자석을 아직 회전시키지 않았고, 오른쪽 자석의 좌측값과 현재 자석의 우측값이 다른 극이라면,
			if(cur[0] + 1 < 4 && !visited[cur[0]+1] && magnets[cur[0]+1][6] != right) {
				// 다음 회전할 자석으로 추가(방향은 현재 자석의 반대로) 및 방문 처리
				q.offer(new int[] {cur[0]+1, -cur[1]});
				visited[cur[0]+1] = true;
			}
			// 왼쪽으로 확인할 자석이 있고, 왼쪽 자석을 아직 회전시키지 않았고, 왼쪽 자석의 우측값과 현재 자석의 좌측값이 다른 극이라면,
			if(cur[0] - 1 >= 0 && !visited[cur[0]-1] && magnets[cur[0]-1][2] != left) {
				// 다음 회전할 자석으로 추가(방향은 현재 자석의 반대로) 및 방문 처리
				q.offer(new int[] {cur[0]-1, -cur[1]});
				visited[cur[0]-1] = true;
			}
			// 회전 시킬 자석을 확인한 후, 현재 자석 회전
			turn_mag(cur[0], cur[1]);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 테스트 케이스 수 입력
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 점수를 계산할 변수
			int ans = 0;
			// 자석을 회전시킬 횟수 저장
			int K = Integer.parseInt(br.readLine());
			// 자석을 저장할 배열 선언
			magnets = new int[4][8];
			// 초기 값 입력
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			cases = new int[K][2];
			for(int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				cases[k][0] = Integer.parseInt(st.nextToken());
				cases[k][1] = Integer.parseInt(st.nextToken());
			}
			
			// 회전 실행
			for(int k = 0; k < K; k++) {
				chk_mag(cases[k][0] - 1, cases[k][1]);
			}
			
			// 점수 계산
			int score = 1;
			for(int i = 0; i < 4; i++) {
				ans += magnets[i][0]* score;
				score *= 2;
			}
			
			// 결과 저장
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		// 저장된 결과값 출력
		System.out.println(sb.toString());
	}
	
}