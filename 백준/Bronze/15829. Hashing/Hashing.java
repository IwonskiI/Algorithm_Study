import java.io.*;


public class Main {
	
	// BOJ15829 - Hashing
	
	static long r = 31, M = 1234567891;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split("");
		long ans = 0L;
		long pow = 1;
		
		for(int i = 0; i < N; i++) {
			ans += ((long)str[i].charAt(0)-96) * pow;
			pow *= r;
			pow %= M;
		}
		
		System.out.println(ans % M);

	}

}
