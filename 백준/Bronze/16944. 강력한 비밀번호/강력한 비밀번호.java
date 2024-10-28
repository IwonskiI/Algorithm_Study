import java.io.*;
import java.util.*;

public class Main {
	
	//BOJ 16944 - 강력한 비밀번호
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String word = br.readLine();
		
		// 숫자 / 소문자 / 대문자 / 특수기호
		boolean[] check = new boolean[4];
		
		for(int i = 0; i < N; i++) {
			if('0' <= word.charAt(i) && word.charAt(i) <= '9') check[0] = true;
			else if('a' <= word.charAt(i) && word.charAt(i) <= 'z') check[1] = true;
			else if('A' <= word.charAt(i) && word.charAt(i) <= 'Z') check[2] = true;
			else if('!' == word.charAt(i) || '@' == word.charAt(i) || '#' == word.charAt(i) || '$' == word.charAt(i) ||
					'%' == word.charAt(i) || '^' == word.charAt(i) || '&' == word.charAt(i) || '*' == word.charAt(i) ||
					'(' == word.charAt(i) || ')' == word.charAt(i) || '-' == word.charAt(i) || '+' == word.charAt(i)) check[3] = true;
		}
		
		
		int ans = 0;
		for(int i = 0; i < 4; i++) {
			if(!check[i]) {
				N++;
				ans++;
			}
		}
		
		if(N < 6) {
			ans += (6 - N);
		}
		
		// 최종 결과 출력
		System.out.println(ans);
	}
}