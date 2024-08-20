import java.util.*;
import java.io.*;

public class Solution {
	// SWEA 1486 - 장훈이의 높은 선반
	public static int N, B, ans;
	public static int[] num_lst;
	
	public static void backtrack(int start, int sum) {
		// 현재까지의 합이 B이상이고, 현재까지 저장된 값보다 작을 때, 값 갱신
		if(sum >= B && sum < ans) {
			ans = sum;
		}
		// 현재까지 합에 다음 값을 순서대로 더해보기
		for(int i = start; i < N; i++) {
			backtrack(i + 1, sum + num_lst[i]);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			num_lst = new int[N];
			// 초기값 정수 최댓값으로 설정
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				num_lst[i] = Integer.parseInt(st.nextToken());
			}
			// 백트래킹 시작
			backtrack(0, 0);
			// 최소 높이와 차이를 출력
			sb.append("#").append(tc).append(" ").append(ans - B).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}