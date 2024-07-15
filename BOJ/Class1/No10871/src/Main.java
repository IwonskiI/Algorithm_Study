import java.util.*;
import java.io.*;

public class Main {

		public static void main(String args[]) throws Exception {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				StringBuilder sb = new StringBuilder();
				StringTokenizer st;
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				st = new StringTokenizer(br.readLine());
				for(int i = 0; i < n; i++) {
					int cur = Integer.parseInt(st.nextToken());
					if(cur < k) sb.append(cur).append(" ");
				}
				System.out.println(sb.toString());

		}

}