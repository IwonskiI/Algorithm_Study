import java.io.*;

public class Main {
	
	//BOJ2292 - 벌집
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int ans = 1, num = 1;
		
		for(int i = 6; num < N; i+=6) {
			num += i;
			ans++;
		}
		
		System.out.println(ans);

	}

}
