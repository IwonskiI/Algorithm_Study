import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 10214 - Baseball
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int Y = 0, K = 0;
			for(int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				Y += Integer.parseInt(st.nextToken());
				K += Integer.parseInt(st.nextToken());
			}
			if(Y > K) sb.append("Yonsei\n");
			else if(K > Y) sb.append("Korea\n");
			else sb.append("Draw\n");
		}
		
		System.out.println(sb.toString());
	}

}
