import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 11050 - 이항 계수 1
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 조합 공식 사용
		k = k > n-k ? n-k : k;
		
		// up = 분자, down = 분모
		int up = 1, down = 1;
		
		// 조합의 수 구하기 공식
		for(int i = n; i > n-k; i--) {
			up *= i;
		}
		for(int i = k; i > 0; i--) {
			down *= i;
		}
		
		System.out.println(up/down);

	}

}
