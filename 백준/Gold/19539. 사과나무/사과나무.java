import java.util.*;
import java.io.*;

public class Main {

	// BOJ 19539 - 사과 나무
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int len2 = 0, len1 = 0;
		for(int i = 0; i < n; i++) {
			// 자라야하는 나무의 높이
			int cur = Integer.parseInt(st.nextToken());
			// 일단 최소로 물을 줘서 자랄 수 있는 방법을 계산

			// 2씩 자라기 위해서 받아야하는 횟수 계산
			len2 += cur / 2;
			
			// 홀수라면 1씩 무조건 한번은 받아야하므로 +1
			len1 += cur % 2;
		}
		
		// 2씩 받기로 한 길이를 1씩 두번 받으며 2씩 자라는 물을 준 횟수와 1씩 자라는 물을 준 횟수의 비율을 맞춤
		while(len2 > len1) {
			len2 -= 1;
			len1 += 2;
		}
		
		// 2씩 자라는 물을 준 횟수와 1씩 자라는 물을 준 횟수의 비율이 정확히 일치한다면 가능
		if(len2 == len1) System.out.println("YES");
		// 일치하지 않는다면 불가능
		else System.out.println("NO");
	}
}
