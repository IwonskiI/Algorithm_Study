import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 5523 - 경기 결과
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int a = 0, b = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a_num = Integer.parseInt(st.nextToken());
			int b_num = Integer.parseInt(st.nextToken());
			if(a_num > b_num) a++;
			else if(b_num > a_num) b++;
		}
		
		sb.append(a).append(" ").append(b);
		
		System.out.println(sb.toString());
	}

}
