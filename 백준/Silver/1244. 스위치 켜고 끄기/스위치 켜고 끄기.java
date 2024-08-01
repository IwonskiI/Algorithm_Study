import java.util.*;
import java.io.*;

public class Main {
	
	static int[] s_lst;
	
	public static void change_status(int idx) {
		if(s_lst[idx] == 0) s_lst[idx] = 1;
		else s_lst[idx] = 0;
	}
	
	public static void male_calc(int num) {
		for(int i = num; i <= s_lst.length; i += num) {
			change_status(i-1);
		}
	}
	
	public static void female_calc(int num) {
		change_status(num-1);
		for(int i = 1; (num-1+i < s_lst.length)&&(num-1-i >= 0); i++) {
			if(s_lst[num-1+i] == s_lst[num-1-i]) {
				change_status(num-1+i);
				change_status(num-1-i);
			}
			else break;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		s_lst = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			s_lst[i] = Integer.parseInt(st.nextToken());
		}
		
		int s_num = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < s_num; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int get_num = Integer.parseInt(st.nextToken());
			if(gender == 1) male_calc(get_num);
			else female_calc(get_num);
		}
		
		for(int i = 0; i < s_lst.length; i++) {
			sb.append(s_lst[i]).append(" ");
			if((i+1)%20 == 0) sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
