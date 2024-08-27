import java.util.*;
import java.io.*;

public class Main {

	// BOJ 10026 - 적록색약
    // 적록색약일 때, 같은색으로 판별하는 지 함수
	public static boolean chk_color(char base, char cur) {
        // 현재 탐색 색깔이 Blue일 떄는 현재 색이랑 같으면 true, 다르면 false
		if(base == 'B') return base == cur;
        // 현재 탐색 색깔이 Red, Green이라면, 현재 색이 Blue가 아니라면 true, Blue라면 false
		else return cur != 'B';
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 4방향 탐색 델타값
        int[][] d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // 초기 입력 처리
        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        boolean[][] visited = new boolean[N][N];
        boolean[][] visited2 = new boolean[N][N];
        for(int r = 0; r < N; r++) {
        	String str = br.readLine();
        	for(int c = 0; c < N; c++) {
        		board[r][c] = str.charAt(c);
        	}
        }
        
        // BFS 탐색
        Deque<int[]> dq = new ArrayDeque<>();
        // 정상인의 구분 수, 적록색약의 구분 수
        int all = 0, redgreen = 0;
        for(int row = 0; row < N; row++) {
        	for(int col = 0; col < N; col++) {
                // 현재 탐색 색 정의 
        		char color = board[row][col];
                // 정상인 기준 방문 전
        		if(!visited[row][col]) {
                    // 구분 수 증가
        			all++;
        			dq.offer(new int[] {row, col});
        	        visited[row][col] = true;
                    // bfs로 탐색
        	        while(!dq.isEmpty()) {
        	        	int[] cur = dq.poll();
        	        	int r = cur[0], c = cur[1];
        	        	for(int dd = 0; dd < 4; dd++) {
        	        		int nr = r + d[dd][0], nc = c + d[dd][1];
        	        		boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
                            // 범위 안이고, 현재 색이랑 같고, 방문전이라면 추가 탐색
        	        		if(in_range && board[nr][nc] == color && !visited[nr][nc]) {
        	        			visited[nr][nc] = true;
        	        			dq.offer(new int[] {nr, nc});
        	        		}
        	        	}
        	        }
        		}
                // 적록색약 기준 방문 전
        		if(!visited2[row][col]) {
                    // 구분 수 증가
        			redgreen++;
        			dq.offer(new int[] {row, col});
        	        visited2[row][col] = true;
                    // bfs로 탐색
        	        while(!dq.isEmpty()) {
        	        	int[] cur = dq.poll();
        	        	int r = cur[0], c = cur[1];
        	        	for(int dd = 0; dd < 4; dd++) {
        	        		int nr = r + d[dd][0], nc = c + d[dd][1];
        	        		boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
                            // 범위 안이고, 적록색약 기준 현재 색이랑 같고, 방문전이라면 추가 탐색
        	        		if(in_range && chk_color(color, board[nr][nc]) && !visited2[nr][nc]) {
        	        			visited2[nr][nc] = true;
        	        			dq.offer(new int[] {nr, nc});
        	        		}
        	        	}
        	        }
        		}
        	}
        }
        // 전체 답 저장 및 출력
        sb.append(all).append(" ").append(redgreen);
        System.out.println(sb.toString());
        
    }
}