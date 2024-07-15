import java.util.*;
import java.io.*;

public class Main {

		public static void main(String args[]) throws Exception {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				StringBuilder sb = new StringBuilder();
				StringTokenizer st;
				String input = "";
				while((input = br.readLine()) != null) {
					st = new StringTokenizer(input);
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					sb.append(a+b).append("\n");
				}
				System.out.println(sb.toString());

		}

}