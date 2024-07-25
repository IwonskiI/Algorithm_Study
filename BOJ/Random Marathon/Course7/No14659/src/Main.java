import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 14659 - 한조서열정리하고옴ㅋㅋ
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] h_lst = new int[n]; 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			h_lst[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		for(int i = 0; i < n; i++) {
			int cur = h_lst[i], cnt = 0;
			for(int j = i+1; j < n; j++) {
				if(h_lst[j] < cur) cnt++;
				else break;
			}
			ans = ans > cnt ? ans : cnt;
		}
		
		System.out.println(ans);
	}

}
