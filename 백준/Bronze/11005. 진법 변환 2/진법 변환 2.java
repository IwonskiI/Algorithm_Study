import java.io.*;
import java.util.*;

public class Main {

	// BOJ 11005 - 진법 전환 2
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		while(N >= B) {
			int div = N / B;
			int mod = N % B;
			if(mod > 9) {
				mod += 55;
				sb.append((char)mod);
			}
			else sb.append(mod);
			N = div;
		}
		if (N > 9) {
			N += 55;
			sb.append((char)N);
		}
		else sb.append(N);
		
		System.out.println(sb.reverse().toString());

	}

}
