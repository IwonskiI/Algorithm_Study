import java.util.*;
import java.io.*;

public class Main {

	// BOJ 2636 - 치즈
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // 보드 정의
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] board = new int[R][C];
        // 현재 남은 치즈 수
        int left = 0;
        
        // 치즈 위치 입력
        // 치즈는 기본적으로 2의 강도를 가지고 공기중에 닿는 곳을 1로 관리
        // 치즈가 있는 곳은 시간이 지나면서 -1씩 해주면 공기중에 닿는 곳은 치즈가 사라지는 것으로 관리
        for(int r = 0; r < R; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < C; c++) {
        		board[r][c] = Integer.parseInt(st.nextToken());
        		// 치즈가 있는 곳은 강도 증가
        		if(board[r][c] == 1) {
        			board[r][c]++;
        			left++;
        		}
        	}
        }
        
        // 치즈가 녹는데 걸리는 시간
        int ans = 0;
        // 이전 시간에 남은 치즈 수
        int prev = -1;
        
        // 치즈가 모두 사라질 때 까지 반복
        while(left > 0) {
        	// 시간 증가
        	ans++;
        	// 공기중에 닿는 면적을 체크하기 위한 방문 배열
        	boolean[][] visited = new boolean[R][C];
        	// 0,0에서 빈 공간을 계속 탐색하고, 맞닿는 치즈를 찾으면 강도 1 감소
        	Deque<int[]> dq = new ArrayDeque<>();
        	dq.offer(new int[] {0, 0});
        	visited[0][0] = true;
        	while(!dq.isEmpty()) {
        		int[] cur = dq.poll();
        		int r = cur[0], c = cur[1];
        		for(int dd = 0; dd < 4; dd++) {
        			int nr = r + d[dd][0], nc = c + d[dd][1];
        			// 범위 체크
        			boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
        			// 범위 안이고 방문 전이면
        			if(in_range && !visited[nr][nc]) {
        				// 빈 공간이면 queue에 추가해서 계속 탐색
        				if(board[nr][nc] == 0) {
        					dq.offer(new int[] {nr, nc});
        				}
        				// 치즈가 있다면 강도 감소
        				else board[nr][nc]--;
        				// 해당 칸 방문 처리
        				visited[nr][nc] = true;
        			}
        		}
        	}
        	
        	// 이전 시간에 남은 치즈 저장
        	prev = left;
        	// 현재 치즈 초기화
        	left = 0;
        	// 보드 탐색 - 치즈가 있다면 치즈 강도 감소
        	for(int r = 1; r < R-1; r++) {
        		for(int c = 1; c < C-1; c++) {
        			// 치즈가 있다면 감소
        			if(board[r][c] > 0) {
        				board[r][c]--;
        			}
        			// 감소 한 뒤에 치즈가 남아있다면
        			if(board[r][c] > 0) {
        				// 치즈 강도 복구
        				board[r][c]++;
        				// 남은 치즈 수 증가
        				left++;
        			}
        		}
        	}
        }
        
        // 시간 및 남은 치즈 출력
        System.out.println(ans);
        System.out.println(prev);
        
    }
}