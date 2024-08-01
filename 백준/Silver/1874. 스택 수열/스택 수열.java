import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 1874 - 스택 수열
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] num_lst = new int[N];
		
		for(int i = 0; i < N; i++) {
			num_lst[i] = Integer.parseInt(br.readLine());
		}
		
		Deque<Integer> q = new ArrayDeque<>();
		int cnt = 1, idx = 0;
		while(idx < N) {
			int cur = num_lst[idx];
			while(cnt <= cur) {
				q.offer(cnt);
				cnt++;
				sb.append("+\n");
			}
			if(q.peekLast() == cur) {
				q.pollLast();
				sb.append("-\n");
				idx++;
			}
			else {
				System.out.println("NO");
				return;
			}
		}
		
		
		System.out.println(sb.toString());
	}
}
