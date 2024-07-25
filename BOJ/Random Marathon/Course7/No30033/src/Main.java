import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 30033 - Rust Study
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] plan = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		int ans = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int real = Integer.parseInt(st.nextToken());
			if(real >= plan[i]) ans++;
		}
		
		System.out.println(ans);
		
	}

}
