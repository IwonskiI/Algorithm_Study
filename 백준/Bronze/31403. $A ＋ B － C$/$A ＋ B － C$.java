import java.io.*;

public class Main {

		public static void main(String args[]) throws Exception {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				
				String A = br.readLine();
				String B = br.readLine();
				String C = br.readLine();
				
				int a = Integer.parseInt(A);
				int b = Integer.parseInt(B);
				int c = Integer.parseInt(C);
				
				int ans = a + b - c;
				int res = Integer.parseInt(A + B) - c;
				
				
				System.out.println(ans);
				System.out.println(res);
		}

}