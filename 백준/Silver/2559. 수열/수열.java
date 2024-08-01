import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int window = 0;
		int[] num_lst = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			num_lst[i] = Integer.parseInt(st.nextToken());
			if(i < k) window += num_lst[i]; 
		}
		
		int ans = window;
		for(int sp = 0, ep = k; ep < n; sp++, ep++) {
			window -= num_lst[sp];
			window += num_lst[ep];
			if(window > ans) ans = window;
		}

		System.out.println(ans);
		
	}

}
