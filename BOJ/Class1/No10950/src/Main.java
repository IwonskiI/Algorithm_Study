import java.util.*;
import java.io.*;

public class Main {

		public static void main(String args[]) throws Exception {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				StringBuilder sb = new StringBuilder();
				StringTokenizer st;
				int n = Integer.parseInt(br.readLine());
				for(int i = 0; i < n; i++) {
					st = new StringTokenizer(br.readLine());
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					sb.append(a+b).append("\n");
				}
				System.out.println(sb.toString());

		}

}