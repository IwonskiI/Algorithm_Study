import java.util.*;
import java.io.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ans = 0;
		boolean[][] board = new boolean[100][100];
		
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			
			for(int c = sx; c < ex; c++) {
				for(int r = sy; r < ey; r++) {
					if(!board[c][r]) {
						board[c][r] = true;
						ans++;
					}
				}
			}
			
		}
		
		System.out.println(ans);

	}

}
