import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 9012 - 괄호
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			String[] ps = br.readLine().split("");
			Deque<Integer> dq = new ArrayDeque<>();
			boolean flag = true;
			for(int j = 0; j < ps.length; j++) {
				if(ps[j].equals("(")) dq.offer(1);
				else if(ps[j].equals(")")) {
					if(dq.isEmpty()) {
						flag = false;
						break;
					}
					else dq.poll();
				}
			}
			if(dq.size() != 0) flag = false;
			if(flag) sb.append("YES\n");
			else sb.append("NO\n");
		}
		
		System.out.println(sb.toString());
	}

}
