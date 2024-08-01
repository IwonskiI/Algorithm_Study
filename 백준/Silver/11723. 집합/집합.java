import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 11723 - 집합
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		boolean[] set = new boolean[21];
		
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			int num;
			switch(op) {
			case "add":
				num = Integer.parseInt(st.nextToken());
				set[num] = true;
				break;
			case "remove":
				num = Integer.parseInt(st.nextToken());
				set[num] = false; 
				break;
			case "check":
				num = Integer.parseInt(st.nextToken());
				if(set[num]) sb.append("1\n");
				else sb.append("0\n");
				break;
			case "toggle":
				num = Integer.parseInt(st.nextToken());
				set[num] = !set[num];
				break;
			case "all":
				for(int j = 1; j <= 20; j++) {
					set[j] = true; 
				}
				break;
			case "empty":
				for(int j = 1; j <= 20; j++) {
					set[j] = false; 
				}
				break;
			}
		}
		
		System.out.println(sb.toString());

	}

}
