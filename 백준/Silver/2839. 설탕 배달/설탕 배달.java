import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 2839 - 설탕 배달
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 5kg 봉지를 최대한 많이 들고 시작
		int ans = n / 5;
		// 남은 무게를 3kg으로 채움
		// res가 가능한 값 => 0 1 2 3 4
		int res = n % 5;
		
		// 0이면 ans (5키로 봉지로 끝)
		// 1이면 ans가 1 이상이면 ans + 1 (5키로 봉지 하나 빼고 3키로 봉지 2개 더함)
		// 2면 ans가 2 이상이면 ans + 2 (5키로 봉지 두개 빼고 3키로 봉지 4개 더함)
		// 3이면 ans + 1 (3키로 봉지 하나 더함)
		// 4면 ans가 1 이상이면 ans + 2 (5키로 봉지 하나 빼고 3키로 봉지 3개 더함)
		
		if(res == 0) {
			System.out.println(ans);
		}
		else if ((res == 1 && ans >= 1) || res == 3) {
			System.out.println(ans + 1);
		}
		else if ((res == 2 && ans >= 2) || (res == 4 && ans >= 1)) {
			System.out.println(ans + 2);
		}
		else {
			System.out.println(-1);
		}
		
		

	}
}
