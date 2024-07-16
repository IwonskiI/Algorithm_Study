import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] queue = new int[10001];
		int front = 0;
		int end = -1;
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			if(op.equals("push")) {
				int x = Integer.parseInt(st.nextToken());
				queue[++end] = x;
			}
			else if(op.equals("pop")) {
				if(end < front) System.out.println(-1);
				else System.out.println(queue[front++]);
			}
			else if(op.equals("size")) {
				System.out.println(end - front + 1);
			}
			else if(op.equals("empty")) {
				if(end < front) System.out.println(1);
				else System.out.println(0);
			}
			else if(op.equals("front")) {
				if(end < front) System.out.println(-1);
				else System.out.println(queue[front]);
			}
			else if(op.equals("back")) {
				if(end < front) System.out.println(-1);
				else System.out.println(queue[end]);
			}
		}
	}

}
