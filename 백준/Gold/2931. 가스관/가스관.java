import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] board = new char[R][C];
		int[][] visited = new int[R][C];
		int sr = 0, sc = 0;
		for(int r = 0; r < R; r++) {
			String str = br.readLine();
			for(int c = 0; c < C; c++) {
				board[r][c] = str.charAt(c);
				if(board[r][c] == 'M') {
					sr = r;
					sc = c;
				}
				else if(board[r][c] == '.') continue;
				else if(board[r][c] == '+') visited[r][c] = 2;
				else visited[r][c] = 1;
			}
		}
		
		int sdir = -1;
		for(int dd = 0; dd < 4; dd++) {
			int nr = sr + d[dd][0], nc = sc + d[dd][1];
			boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
			if(in_range && board[nr][nc] != '.' && board[nr][nc] != 'Z') {
				if((dd == 0 && (board[nr][nc] == '1' || board[nr][nc] == '4' || board[nr][nc] == '|' || board[nr][nc] == '+')) 
						|| (dd == 1 && (board[nr][nc] == '3' || board[nr][nc] == '4' || board[nr][nc] == '-' || board[nr][nc] == '+')) 
						|| (dd == 2 && (board[nr][nc] == '2' || board[nr][nc] == '3' || board[nr][nc] == '|' || board[nr][nc] == '+')) 
						|| (dd == 3 && (board[nr][nc] == '1' || board[nr][nc] == '2' || board[nr][nc] == '-' || board[nr][nc] == '+'))) {
					sdir = dd;
					break;
				}
			}
		}
		
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {sr, sc, sdir});
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int nr = cur[0] + d[cur[2]][0], nc = cur[1] + d[cur[2]][1], ndir = cur[2];
			switch(board[nr][nc]) {
			case'|':
			case'-':
			case'+':
				visited[nr][nc]--;
				dq.offer(new int[] {nr, nc, ndir});
				break;
			case'1':
				visited[nr][nc]--;
				ndir = cur[2] == 0 ? 1 : 2;
				dq.offer(new int[] {nr, nc, ndir});
				break;
			case'2':
				visited[nr][nc]--;
				ndir = cur[2] == 3 ? 0 : 1;
				dq.offer(new int[] {nr, nc, ndir});
				break;
			case'3':
				visited[nr][nc]--;
				ndir = cur[2] == 2 ? 3 : 0;
				dq.offer(new int[] {nr, nc, ndir});
				break;
			case'4':
				visited[nr][nc]--;
				ndir = cur[2] == 1 ? 2 : 3;
				dq.offer(new int[] {nr, nc, ndir});
				break;
			case'.':
				int chk = 0;
				int chk_dir = -1;
				for(int i = 0; i < 4; i++) {
					int rr = nr + d[i][0], cc = nc + d[i][1];
					boolean in_range = (0 <= rr && rr < R) && (0 <= cc && cc < C);
					if(in_range && visited[rr][cc] == 0) continue;
					if(in_range) {
						if((i == 0 && (board[rr][cc] == '1' || board[rr][cc] == '4' || board[rr][cc] == '|' || board[rr][cc] == '+')) 
								|| (i == 1 && (board[rr][cc] == '3' || board[rr][cc] == '4' || board[rr][cc] == '-' || board[rr][cc] == '+')) 
								|| (i == 2 && (board[rr][cc] == '2' || board[rr][cc] == '3' || board[rr][cc] == '|' || board[rr][cc] == '+')) 
								|| (i == 3 && (board[rr][cc] == '1' || board[rr][cc] == '2' || board[rr][cc] == '-' || board[rr][cc] == '+'))) {
							chk_dir = i;
							chk++;
						}
					}
				}
				char ans = '0';
				if(chk == 1) {
					if(cur[2] == 0) {
						if(chk_dir == 1) ans = '1';
						else if(chk_dir == 3) ans = '4';
						else if(chk_dir == 0) ans = '|';
					}
					else if(cur[2] == 1) {
						if(chk_dir == 0) ans = '3';
						else if(chk_dir == 2) ans = '4';
						else if(chk_dir == 1) ans = '-';
					}
					else if(cur[2] == 2) {
						if(chk_dir == 1) ans = '2';
						else if(chk_dir == 3) ans = '3';
						else if(chk_dir == 2) ans = '|';
					}
					else if(cur[2] == 3) {
						if(chk_dir == 0) ans = '2';
						else if(chk_dir == 2) ans = '1';
						else if(chk_dir == 3) ans = '-';
					}
					System.out.println((nr+1) + " " + (nc+1) + " " + ans);
					return;
				}
				else {
					System.out.println((nr+1) + " " + (nc+1) + " +");
					return;
				}
			}
			
		}
		
	}
}