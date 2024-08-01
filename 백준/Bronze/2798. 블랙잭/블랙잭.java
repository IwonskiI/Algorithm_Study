import java.io.*;
import java.util.*;


public class Main {
	
	//BOJ2798 - 블랙잭
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] card_lst = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			card_lst[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(card_lst);
		
		int ans = 0;
		
		for(int i = 0; i < N-2; i++) {
			int cur = card_lst[i];
			if(cur > M) break;
			for(int j = i+1; j < N-1; j++) {
				cur += card_lst[j];
				if(cur > M) {
					cur -= card_lst[j];
					break;
				}
				for(int k = j+1; k < N; k++) {
					cur += card_lst[k];
					if(cur > M) {
						cur -= card_lst[k];
						break;
					}
					ans = cur > ans ? cur : ans;
					cur -= card_lst[k];
				}
				cur -= card_lst[j];
			}
		}
		
		System.out.println(ans);

	}

}
