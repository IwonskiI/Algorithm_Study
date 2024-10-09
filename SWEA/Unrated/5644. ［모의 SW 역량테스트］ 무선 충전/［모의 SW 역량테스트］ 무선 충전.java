import java.io.*;
import java.util.*;

public class Solution {

    // SWEA 5644. [모의 SW 역량테스트] 무선 충전
	public static int[][] d = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static int calc_dist(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int Test = Integer.parseInt(br.readLine().trim());
        for(int tc = 1; tc <= Test; tc++) {
        	sb.append("#").append(tc).append(" ");
        	st = new StringTokenizer(br.readLine());
        	
        	// 총 이동 시간
        	int M = Integer.parseInt(st.nextToken());
        	// BC의 개수
        	int A = Integer.parseInt(st.nextToken());
        	
        	// 이동경로와 BC 정보를 담을 배열 선언
        	int[] a_move = new int[M], b_move = new int[M];
        	int[][] bc_lst = new int[A][4];

        	// 첫 번째 사람 a 이동 경로 저장
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < M; i++) {
        		a_move[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	// 두 번째 사람 b 이동 경로 저장
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < M; i++) {
        		b_move[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	// BC 정보 저장 (col, row, dist, score)
        	for(int i = 0; i < A; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j = 0; j < 4; j++) {
        			bc_lst[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	// 최종 점수 ans
        	int ans = 0;
        	// a위치는 (1,1), b위치는 (10,10)으로 초기화
        	int[] a_pos = new int[] {1, 1}, b_pos = new int[] {10, 10};
        	
        	// M초 동안 시뮬레이션
        	for(int t = 0; t <= M; t++) {
        		// 이동 전 현재 시간에서 최대 충전량 계산
        		int score = 0;
        		int a_r = a_pos[0], a_c = a_pos[1];
        		int b_r = b_pos[0], b_c = b_pos[1];
        		// 모든 베터리 충전소 선택 조합 계산 (최대 8 * 8 = 64가지 경우)
        		for(int a = 0; a < A; a++) {
        			// 현재 a가 선택한 bc 정보
        			int[] a_cur = bc_lst[a];
        			// row, col, dist, score
        			int a_bc_r = a_cur[1], a_bc_c = a_cur[0], a_bc_d = a_cur[2], a_bc_s = a_cur[3];
        			// a가 현재 bc를 사용 가능한지
        			boolean using_a = false;
        			// 현재 a 위치에서 충전소까지 거리가 dist 이내라면 사용 가능
        			if(calc_dist(a_r, a_c, a_bc_r, a_bc_c) <= a_bc_d) using_a = true;
        			
        			for(int b = 0; b < A; b++) {
        				// 현재 b가 선택한 bc 정보
        				int[] b_cur = bc_lst[b];
        				// row, col, dist, score
            			int b_bc_r = b_cur[1], b_bc_c = b_cur[0], b_bc_d = b_cur[2], b_bc_s = b_cur[3];
            			// b가 현재 bc를 사용 가능한지
            			boolean using_b = false;
            			// 현재 b 위치에서 충전소까지 거리가 dist 이내라면 사용 가능
            			if(calc_dist(b_r, b_c, b_bc_r, b_bc_c) <= b_bc_d) using_b = true;
            			
            			// 두 사람의 위치에서 충전소 사용 가능 여부를 판단 후 점수 계산
            			int cur = 0;
            			// a랑 b랑 같은 충전소를 사용 중이라면
            			if(a == b) {
            				// 두사람 다 충전소를 사용한다면 해당 충전소의 값을 한 번만 더함
            				// -> 같은 충전소이기 때문에 a_bs_s / 2 == b_bs_s / 2이고
            				// a_bs_s / 2 + b_bs_s / 2 = a_bs_s = b_bs_s이다.
            				if(using_a && using_b) cur += b_bc_s;
            				// 두 사람 다 충전소를 사용하는 것이 아니라면 충전소를 사용하는 사람만 더해줌
            				else {
            					// a가 사용 중이라면 cur에 a 충전소 값 더하기
            					if(using_a) cur += a_bc_s;
            					// b가 사용 중이라면 cur에 b 충전소 값 더하기
            					else if(using_b) cur += b_bc_s;
            				}
            			}
            			// 두 사람이 다른 충전소를 사용 중이라면 각자 사용중일 경우 각 충전소의 값 더하기
            			else {
        					if(using_a) cur += a_bc_s;
        					if(using_b) cur += b_bc_s;
        				}
            			
            			// 계산이 끝난 후 현재 시간에서의 최대 점수 갱신
            			score = Math.max(score, cur);
        			}
        			
        		}
        		// 최대값을 구했다면 해당 값을 ans에 더하기
        		ans += score;
        		
        		// M초까지 이동이 끝났다면 더이상 이동하지 않고 종료
        		if(t == M) break;
        		
        		// 아직 끝나지 않았다면 다음 칸으로 이동 후 충전소 탐색
        		a_pos = new int[] {a_r + d[a_move[t]][0], a_c + d[a_move[t]][1]};
        		b_pos = new int[] {b_r + d[b_move[t]][0], b_c + d[b_move[t]][1]};
        	}
        	
        	// 최종 충전값 저장
            sb.append(ans).append("\n");
        
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
        

    }

}