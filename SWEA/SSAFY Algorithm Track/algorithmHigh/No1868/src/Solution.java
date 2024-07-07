import java.util.*;

public class Solution {
	
	static int N, ans;
	static int[][] board;
	static boolean[][] visited;
	static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll(); 
			
			for(int dd = 0; dd < 8; dd++) {
				int nc = cur[0] + dc[dd];
				int nr = cur[1] + dr[dd];
				boolean in_range = ((0<=nc)&&(nc<N)) && ((0<=nr)&&(nr<N));
				
				if(in_range) {
					
				}
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			board = new int[N][N];
			for (int c = 0; c < N; c++) {
				String[] temp = sc.next().split("");
				for (int r = 0; r < N; r++) {
					if(temp[r].equals("*")) board[c][r] = 1;
					else board[c][r] = 0;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}

}

