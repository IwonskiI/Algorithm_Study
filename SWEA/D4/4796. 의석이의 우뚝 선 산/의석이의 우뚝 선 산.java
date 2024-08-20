import java.util.*;
import java.io.*;

public class Solution {
	// SWEA 4796 - 의석이의 우뚝 선 산
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 1024);
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
//		int T = Integer.parseInt(br.readLine());
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
//			int N = Integer.parseInt(br.readLine());
			int[] num_lst = new int[N];
//			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
//				num_lst[i] = Integer.parseInt(st.nextToken());
				num_lst[i] = sc.nextInt();
			}
			
			// 시작 지점
			int cur = num_lst[0];
			// ans : 우뚝솟은 산, high_cnt : 증가 하는 부분 길이, low_cnt : 감소하는 부분 길이
			int ans = 0, high_cnt = 0, low_cnt = 0;
			boolean low = false;
			
			// 배열의 다음 부분 확인
			for(int i = 1; i < N; i++) {
				// 다음 높이가 증가하는 높이라면
				if(cur < num_lst[i]) {
					// 이전에 감소해왔던 기록이 있다면
					if(low) {
						// 오르막 갯수 * 내리막 갯수
						ans += (high_cnt) * (low_cnt);
						// 변수 초기
						high_cnt = 0;
						low_cnt = 0;
						low = false;
					}
					// 오르막 길이 추가
					high_cnt++;
				}
				// 감소하는 높이라면
				else {
					// 내리막 갔던 표시
					low = true;
					// 내리막 길이 증가
					low_cnt++;
				}
				// 현재 높이 갱신
				cur = num_lst[i];
			}
			
			// 마지막이 감소를하고 끝났으면
			if(low)
				// 오르막 갯수 * 내리막 갯수
				ans += (high_cnt) * (low_cnt);
			
			// 전체 경우의 수 출력
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}