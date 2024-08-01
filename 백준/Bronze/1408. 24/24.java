import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 1408 - 24
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(),":");
		int cur_h = Integer.parseInt(st.nextToken());
		int cur_m = Integer.parseInt(st.nextToken());
		int cur_s = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(),":");
		int start_h = Integer.parseInt(st.nextToken());
		int start_m = Integer.parseInt(st.nextToken());
		int start_s = Integer.parseInt(st.nextToken());
		
		int left_s = start_s - cur_s;
		if(left_s < 0) {
			left_s += 60;
			start_m -= 1;
		}
		int left_m = start_m - cur_m;
		if(left_m < 0) {
			left_m += 60;
			start_h -= 1;
		}
		int left_h = start_h - cur_h;
		if(left_h < 0) {
			left_h += 24;
		}
		if(left_h < 10) sb.append(0);
		sb.append(left_h).append(":");
		if(left_m < 10) sb.append(0);
		sb.append(left_m).append(":");
		if(left_s < 10) sb.append(0);
		sb.append(left_s);
		
		System.out.println(sb.toString());
	}

}
