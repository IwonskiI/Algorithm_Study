import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

		public static void main(String args[]) throws Exception {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				int n = Integer.parseInt(br.readLine());
				
				for(int i = 1; i <= n; i++) {
					for(int j = 0; j < n-i; j++) System.out.print(" ");
					for(int k = 1; k <= i; k++)  System.out.print("*");
					System.out.println();
				}
		}

}