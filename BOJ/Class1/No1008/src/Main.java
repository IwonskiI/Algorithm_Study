import java.util.*;
import java.io.*;

public class Main {

		public static void main(String args[]) throws Exception {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				double a = Double.parseDouble(st.nextToken()); 
				double b = Double.parseDouble(st.nextToken());
				
				double ans = a / b;
				
				System.out.println(ans);
		}

}