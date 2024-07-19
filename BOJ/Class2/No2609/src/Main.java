import java.io.*;
import java.util.*;


public class Main {
	
	//유클리드 호제법 - 최대공약수 구하기
	public static int gcd(int x, int y) {
		int r = 0;
		while(y != 0) {
			r = x % y;
			x = y;
			y = r;
		}
		return x;
	}
	
	// BOJ 2609 - 최대공약수와 최소공배수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		// 최소공배수는 두 수의 곱을 최대공약수로 나눈 값
		int gcd = gcd(a, b), lcm = (a*b)/gcd;
		
		sb.append(gcd).append("\n").append(lcm);
		System.out.println(sb.toString());

	}

}
