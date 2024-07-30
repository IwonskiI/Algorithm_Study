import java.util.*;
import java.io.*;

public class Main {

	// BOJ 1697 - 숨바꼭질
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
		Deque<int[]> dq = new ArrayDeque<>();
		int level = 0;
		boolean[] visited = new boolean[100001];
		dq.offer(new int[] {k,level});
		visited[k] = true;
		
		while(true) {
			int[] cur = dq.poll();
			int pos = cur[0], lv = cur[1];
			if(n == pos) {
				System.out.println(lv);
				return;
			}
			else {
				if(!visited[pos-1]) {
					dq.offer(new int[] {pos-1, lv+1});
					visited[pos-1] = true;
				}
				if(!visited[pos+1]) {
					dq.offer(new int[] {pos+1, lv+1});
					visited[pos+1] = true;
				}
				if(pos%2 == 0) {
					if(!visited[pos/2]) {
						dq.offer(new int[] {pos/2, lv+1});
						visited[pos/2] = true;
					}
				}
			}
		}
	}
}
