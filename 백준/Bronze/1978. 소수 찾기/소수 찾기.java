import java.io.*;
import java.util.*;


public class Main {
	
	//BOJ1978 - 소수 찾기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] num_lst = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num_lst[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num_lst);
		int max_num = num_lst[N-1];
		boolean[] prime = new boolean[max_num+1];
		int lim = (int)Math.sqrt(max_num);
		if(Math.sqrt(max_num) - (double)lim != 0) lim++;
		for(int i = 2; i <= lim; i++) {
			if(prime[i]) continue;
			for(int mul = i*2; mul <= max_num; mul += i) {
				prime[mul] = true;
			}
		}
		int ans = 0;
		prime[0] = true;
		prime[1] = true;
		for(int i = 0; i < N; i++) {
			if(!prime[num_lst[i]]) ans++;
		}
		
		System.out.println(ans);

	}

}
