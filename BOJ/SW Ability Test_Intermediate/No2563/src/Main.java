import java.util.*;
import java.io.*;


public class Main {

	static boolean[][] board;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		board = new boolean[100][100];
		int ans = 0;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken())-1;
			int cc = c + 10, rc = r + 10;
			
			for (int j = c; j < cc; j++) {
				for(int k = r; k < rc; k++) {
					if(!board[j][k]) {
						board[j][k] = true;
						ans++;
					}
				}
			}
		}
		System.out.println(ans);

	}

}
