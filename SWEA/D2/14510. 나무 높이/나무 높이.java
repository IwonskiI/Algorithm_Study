import java.util.*;
import java.io.*;

public class Solution {
	
	// SWEA 14510 - 나무 높이
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			// 최대 나무 높이
			int max = -1;
			// 나무 높이를 입력받으며 최댓값 저장
			int[] tree_lst = new int[n];
			for(int i = 0; i < n; i++) {
				tree_lst[i] = Integer.parseInt(st.nextToken());
				max = tree_lst[i] > max ? tree_lst[i] : max;
			}
			Arrays.sort(tree_lst);
			// 모든 나무가 자라는데 걸리는 일 수
			int ans = 0;
			// 2씩 자라는 횟수 , 1씩 자라는 횟수
			int len2 = 0, len1 = 0;
			for(int i = 0; i < n; i++) {
				// 높이가 최대 높이랑 같다면 pass
				if(tree_lst[i] == max) continue;
				// 최대 높이가 될 때까지 필요한 높이 계산
				int req = max - tree_lst[i];
				// 최소로 물을 줘도 되는 날짜로 계산
				// 2씩 자라는 횟수 계산
				len2 += req/2;
				// 1씩 자라는 횟수 계산
				len1 += req%2;
			}
			
			// 1한번 2 한번 자라므로 2씩 자라는 것을 1씩 자라는 것 2번으로 나눠서 두 수의 비율을 맞춤
			while(len2-1 > len1) {
				len2 -= 1;
				len1 += 2;
			}
			// 1 한번 2 한번씩 주기 때문에 같아진 비율 *2를 해주고 2씩 한번 더 줘야한다면, 2일이 더 필요하고,
			// 1씩 한번 더 줘야한다면 1일만 더 있으면 되고, 두 비율이 딱 떨아진다면 더 줄 필요가 없다.
			if(len2 > len1) ans = (len1*2) + 2;
			else if(len1 > len2) {
				if(len1 - len2 == 1) ans = (len2*2) + 1;
				else {
					ans = len1 * 2 - 1;
				}
			}
			else ans = len2 * 2; // ans = len1 * 2;
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
