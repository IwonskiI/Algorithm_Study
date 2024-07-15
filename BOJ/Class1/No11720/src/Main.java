import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int ans = 0;
		int n = Integer.parseInt(br.readLine());
		String[] st = br.readLine().split("");
		for(int i = 0; i < n; i++) {
			ans += Integer.parseInt(st[i]);
		}
		System.out.println(ans);

	}

}
