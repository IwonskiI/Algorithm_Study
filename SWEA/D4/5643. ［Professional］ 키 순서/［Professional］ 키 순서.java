import java.io.*;
import java.util.*;


public class Solution {
    
    // SWEA 5643 - [Professional] 키 순서
    public static int N, M, ans, tcnt, scnt;
    public static boolean[][] board;
    
	static void taller(int from, boolean[] visited) {
		visited[from] = true;
		for(int i=1; i<N+1; i++) {
			if(!visited[i] && board[from][i]) {
				taller(i, visited);
				tcnt++;
			}
		}
	}
	static void shorter(int to, boolean[] visited) {
		visited[to] = true;
		for(int i=1; i<N+1; i++) {
			if(!visited[i] && board[i][to]) {
				shorter(i, visited);
				scnt++;
			}
		}
	}
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            board = new boolean[N+1][M+1];
            ans = 0;
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                board[s][e] = true;
            }
            
            for(int i = 1; i <= N; i++) {
            	tcnt = 0;
            	scnt = 0;
            	taller(i, new boolean[N+1]);
            	shorter(i, new boolean[N+1]);
            	if(tcnt + scnt == N-1) ans++;
            }
            
            sb.append(ans).append("\n");
            
        }
        System.out.println(sb.toString());
    }
     
}