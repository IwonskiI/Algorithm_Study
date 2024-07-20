import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 25641 - 균형 잡힌 소떡소떡
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		String[] st = br.readLine().split("");
		Deque<String> dq = new ArrayDeque<>();
		int s_cnt = 0, t_cnt = 0;
		for(int i = 0; i < n; i++) {
			dq.offer(st[i]);
			if(st[i].equals("s")) s_cnt++;
			else t_cnt++;
		}
		
		while(s_cnt != t_cnt) {
			String cur = dq.poll();
			if(cur.equals("s")) s_cnt--;
			else t_cnt--;
		}
		
		while(!dq.isEmpty())
			sb.append(dq.poll());
		
		System.out.println(sb.toString());
	}

}
