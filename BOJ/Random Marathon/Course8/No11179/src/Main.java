import java.io.*;

public class Main {
	
	// BOJ 11179 - 2진수 뒤집기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		// 2진수로 변환하기
		while(n != 0) {
			int div = n / 2;
			int mod = n % 2;
			sb.append(mod);
			n = div;
		}
		
		// 2진수를 뒤집어서 다시 10진수로 변환하기
		String[] num_lst = sb.reverse().toString().split("");
		int cnt = 1, ans = 0;
		for(int i = 0; i < num_lst.length; i++) {
			ans += Integer.parseInt(num_lst[i]) * cnt;
			cnt *= 2;
		}

		System.out.println(ans);
	}

}
