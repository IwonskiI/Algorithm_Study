import java.io.*;
import java.util.*;

public class Solution {

    // SWEA 2115 - 벌꿀 채취
    public static int N, M, C, ans, i, j, max;
    public static int[][] board;
    
    // 부분집합 구하기 (cnt: 버킷에 담은 꿀 수 / sum : 버킷에 담은 꿀의 합 / score : 현재까지 담은 꿀의 가치)
    public static void dfs(int cnt, int sum, int score) {
    	// 합이 C를 넘어가면 return
    	if(sum > C) return;
    	// M개의 꿀을 모두 담았다면 max에 최댓값 저장
    	if(cnt == M) {
    		max = Math.max(max, score);
    		return;
    	}
    	
    	// M개의 꿀을 아직 다 담지 않았다면,
    	
    	// 다음 꿀을 선택하는 경우 계산
    	dfs(cnt + 1, sum + board[i][j+cnt], score + (board[i][j+cnt] * board[i][j+cnt]));
    	// 다음 꿀을 선택하지 않는 경우 계산
    	dfs(cnt + 1, sum, score);
    }
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
            // 꿀들의 정보 저장
            board = new int[N][N];
            for(int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < N; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 각 칸에서 시작할 때 수확 가능한 벌꿀 가치의 최댓값 계산
            int[][] memo = new int[N][N];
            for(int r = 0; r < N; r++) {
            	for(int c = 0; c < N-M+1; c++) {
            		// 최댓값 초기화
            		max = 0;
            		// 시작 위치 저장
            		i = r;
            		j = c;
            		// 탐색 시작
            		dfs(0, 0, 0);
            		// 최댓값 저장
            		memo[r][c] = max;
            	}
            }
            
            // 정답을 저장할 변수
            ans = 0;
            // 첫 번째 일꾼 시작점
            for(int r1 = 0; r1 < N; r1++) {
            	// 제일 끝 M칸은 확인하지 않아도 되므로 N-M+1만큼 탐색
            	for(int c1 = 0; c1 < N-M+1; c1++) {
            		// 두 번째 일꾼 시작점
            		for(int r2 = r1; r2 < N; r2++) {
            			// 첫번째 일꾼이랑 같은 줄이라면 시작점을 첫번째 일꾼의 시작점 + M부터 시작
            			int sc = r1 == r2 ? c1+M : 0;
            			for(int c2 = sc; c2 < N-M+1; c2++) {
            				// 각 칸에서의 합과 현재 저장된 값중 큰 값 저장
            				ans = Math.max(ans, memo[r1][c1] + memo[r2][c2]);
            			}
            		}
            	}
            }
            // 탐색 완료 후 정답 저장
            sb.append(ans).append("\n");
            
        }
        // 최종 정답 출력
        System.out.println(sb.toString());
    }
}
