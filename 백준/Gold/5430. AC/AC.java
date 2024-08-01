import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 5430 - AC
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			String[] op = br.readLine().split("");
			int n = Integer.parseInt(br.readLine());
			Deque<Integer> dq = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine(), "[|]|,");
			for(int i = 0; i < n; i++) {
				dq.offer(Integer.parseInt(st.nextToken()));
			}
			boolean dir = true, err = false;
			for(int i = 0; i < op.length; i++) {
				String cur = op[i];
				if(cur.equals("R")) dir = !dir;
				else if(cur.equals("D")) {
					if(dq.size() == 0) {
						sb.append("error\n");
						err = true;
						break;
					}
					else {
						if(dir) dq.poll();
						else dq.pollLast();
					}
				}
			}
			if(!dq.isEmpty()) {
				sb.append("[");
				while(dq.size() != 1) {
					if(dir)sb.append(dq.poll()).append(",");
					else sb.append(dq.pollLast()).append(",");
				}
				sb.append(dq.poll()).append("]\n");
			}
			else if(!err) {
				sb.append("[]\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
