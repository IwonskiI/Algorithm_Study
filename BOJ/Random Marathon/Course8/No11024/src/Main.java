import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 11024 - 더하기 4
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			
			// st에 숫자가 남아있을때까지 반복하며 sum에 추가
			while(st.hasMoreTokens()) {
				sum += Integer.parseInt(st.nextToken());
			}
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
