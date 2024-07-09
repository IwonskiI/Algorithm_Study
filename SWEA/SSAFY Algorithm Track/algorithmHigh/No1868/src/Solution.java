import java.util.*;

public class Solution {
	
	static int N, ans;
	static int[][] board;
	static boolean[][] visited;
	static boolean[][] near_mine;
	static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void find_mine() {
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				boolean flag = false;
				if (board[c][r] == 1) {
					flag = true;
					visited[c][r] = true;
				}
				else {
					for (int dd = 0; dd < 8; dd++) {
						int nc = c + dc[dd];
						int nr = r + dr[dd];
						boolean in_range = ((0<=nc)&&(nc<N)) && ((0<=nr)&&(nr<N));
						if(in_range && board[nc][nr] == 1) {
							flag = true;
							break;
						}
					}
				}
				near_mine[c][r] = flag;
			}
		}
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int c = cur[0];
			int r = cur[1];
			
			for(int dd = 0; dd < 8; dd++) {
				int nc = c + dc[dd];
				int nr = r + dr[dd];
				boolean in_range = ((0<=nc)&&(nc<N)) && ((0<=nr)&&(nr<N));
				if(!in_range || visited[nc][nr]) continue;
				if(!visited[nc][nr] && !near_mine[nc][nr]) q.add(new int[] {nc, nr});
				visited[nc][nr] = true;
			}
				
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = sc.nextInt();
			board = new int[N][N];
			near_mine = new boolean[N][N];
			visited = new boolean[N][N];
			for (int c = 0; c < N; c++) {
				String[] temp = sc.next().split("");
				for (int r = 0; r < N; r++) {
					if(temp[r].equals("*")) board[c][r] = 1;
					else board[c][r] = 0;
				}
			}
			find_mine();
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) {
					if(!near_mine[c][r] && board[c][r] == 0 && !visited[c][r]) {
						bfs(c, r);
						ans += 1;
					}
				}
			}
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) {
					if(!visited[c][r] && board[c][r] == 0) {
						ans += 1;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}

}

