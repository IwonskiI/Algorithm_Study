import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] s_lst = new int[n];
		int max = 0;
		for(int i = 0; i < n; i++) {
			s_lst[i] = Integer.parseInt(st.nextToken());
			if(max < s_lst[i]) max = s_lst[i];
		}
		
		float total = 0f;
		for(int i = 0; i < n; i++) {
			total += (float)s_lst[i] / (float)max * 100f;
		}
		
		total /= n;
		System.out.println(total);
		
	}

}
