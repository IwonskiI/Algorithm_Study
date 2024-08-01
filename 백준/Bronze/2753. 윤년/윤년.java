import java.io.*;


public class Main {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int year = Integer.parseInt(br.readLine());
		int ans = 0;
		
		if(year % 4 == 0) {
			if(year % 100 != 0) {
				ans = 1;
			}
			else if(year % 400 == 0) {
				ans = 1;
			}
		}
		
		System.out.println(ans);
		
	}

}