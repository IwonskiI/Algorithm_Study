import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[][] board = new int[1001][1001];
		int[] area = new int[101];
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= n; i++){
		    st = new StringTokenizer(br.readLine());
		    int x = Integer.parseInt(st.nextToken());
		    int y = Integer.parseInt(st.nextToken());
		    int x_len = Integer.parseInt(st.nextToken());
		    int y_len = Integer.parseInt(st.nextToken());
		    
		    for(int c = x; c < x+x_len; c++){
		        for (int r = y; r < y+y_len; r++){
		            board[c][r] = i;
		        }
		    }
		}
		
		for(int c = 0; c < 1001; c++){
	        for(int r = 0; r < 1001; r++){
	            if(board[c][r] == 0)continue;
	            area[board[c][r]]++;
	            
	        }
		    
		}
		for(int a = 1; a <= n; a++) {
		    sb.append(area[a]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
