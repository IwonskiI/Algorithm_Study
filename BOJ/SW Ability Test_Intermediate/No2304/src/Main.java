import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int [][] bar_lst = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			bar_lst[i][0] = Integer.parseInt(st.nextToken());
			bar_lst[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(bar_lst, (o1, o2) -> {return o1[0] - o2[0];});
		
		int ans = 0;
		for(int i = 0; i < n;) {
			int j = i + 1, max = j;
			while(j < n && bar_lst[i][1] > bar_lst[j][1]) {
				if(bar_lst[max][1] < bar_lst[j++][1]) max = j-1;
			}
			
			if(j >= n) {
				ans += bar_lst[i][1];
				if(max < n) ans += bar_lst[max][1] * (bar_lst[max][0] - bar_lst[i][0]-1);
				i = max;
			}
			else {
				ans += bar_lst[i][1] * (bar_lst[j][0] - bar_lst[i][0]);
				i = j;
			}
		}
		
		System.out.println(ans);
		
	}

}
