import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[] num_lst = new int[3];
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int max_idx = -1, max = -1;
			for(int i = 0; i < 3; i++) {
				num_lst[i] = Integer.parseInt(st.nextToken());
				if(max < num_lst[i]) {
					max = num_lst[i];
					max_idx = i;
				}
			}
			
			if(num_lst[0] == 0 && num_lst[1] == 0 && num_lst[2] == 0) break;
			
			int side = max*max;
			int other = 0;
			for(int i = 0; i < 3; i++) {
				if(i == max_idx) continue;
				other += num_lst[i]*num_lst[i];
			}
			
			if(side == other) sb.append("right\n");
			else sb.append("wrong\n");
			
		}
		
		System.out.println(sb.toString());
	}

}
