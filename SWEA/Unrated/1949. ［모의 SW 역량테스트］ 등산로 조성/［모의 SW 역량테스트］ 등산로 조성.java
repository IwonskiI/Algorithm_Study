import java.io.*;
import java.util.*;
 
public class Solution {
 
    // SWEA 1949 - [모의 SW 역량테스트] 등산로 조성
	public static int N, K, max, ans;
	public static int[][] board, d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static boolean[][] visited;
	
	public static void dfs(int r, int c, int dist, boolean canCut) {
		int cur = board[r][c];
		boolean fin = true;
		for(int dd = 0; dd < 4; dd++) {
			int nr = r + d[dd][0], nc = c + d[dd][1];
			boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
			if(in_range && !visited[nr][nc]) {
				if(board[nr][nc] < cur) {
					fin = false;
					visited[nr][nc] = true;
					dfs(nr, nc, dist+1, canCut);
					visited[nr][nc] = false;
				}
				else if(canCut && board[nr][nc] - K < cur) {
					fin = false;
					int temp = board[nr][nc];
					board[nr][nc] = cur - 1;
					visited[nr][nc] = true;
					dfs(nr, nc, dist+1, false);
					board[nr][nc] = temp;
					visited[nr][nc] = false;
				}
			}
		}
		if(fin) {
			ans = ans > dist ? ans : dist;
			return;
		}
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
            K = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            visited = new boolean[N][N];
            ans = -1;
            max = -1;
            for(int r = 0; r < N; r++) {
            	st = new StringTokenizer(br.readLine());
            	for(int c = 0; c < N; c++) {
            		board[r][c] = Integer.parseInt(st.nextToken());
            		max = board[r][c] > max ? board[r][c] : max;
            	}
            }
            
            for(int r = 0; r < N; r++) {
            	for(int c = 0; c < N; c++) {
            		if(board[r][c] != max) continue;
            		visited[r][c] = true;
            		dfs(r, c, 1, true);
            		visited[r][c] = false;
            	}
            }
            sb.append(ans).append("\n");
            
        }
        System.out.println(sb.toString());
    }
     
}