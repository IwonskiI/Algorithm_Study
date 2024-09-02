import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ans = 0;
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		ArrayList<Integer> areas = new ArrayList<>();
		boolean[][] board = new boolean[R][C], visited = new boolean[R][C];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			
			for(int r = sr; r < er; r++) {
				for(int c = sc; c < ec; c++) {
					board[r][c] = true;
				}
			}
		}
		
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(board[r][c]) visited[r][c] = true;
				else if(!visited[r][c] && !board[r][c]) {
					int area = 1;
					ans++;
					Deque<int[]> dq = new ArrayDeque<>();
					dq.offer(new int[] {r, c});
					visited[r][c] = true;
					while(!dq.isEmpty()) {
						int[] cur = dq.poll();
						int cr = cur[0], cc = cur[1];
						for(int dd = 0; dd < 4; dd++) {
							int nr = cr + d[dd][0], nc = cc + d[dd][1];
							boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
							if(in_range && !board[nr][nc] && !visited[nr][nc]) {
								area++;
								dq.offer(new int[] {nr, nc});
								visited[nr][nc] = true;
							}
						}
					}
					areas.add(area);
				}
			}			
		}
		sb.append(ans).append("\n");
		Collections.sort(areas);
		for(int i = 0; i < ans; i++) {
			sb.append(areas.get(i)).append(" ");
		}
		System.out.println(sb.toString());

	}

}
