import java.util.*;
import java.io.*;

public class Main {

		public static void main(String args[]) throws Exception {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int total = 0;
				
				for(int i = 0; i < 5; i++) {
					int num = Integer.parseInt(st.nextToken());
					num *= num;
					total += num;
				}
				
				System.out.println(total % 10);

		}

}