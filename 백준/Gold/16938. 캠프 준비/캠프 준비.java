import java.io.*;
import java.util.*;

public class Main {

	// BOJ 16938 - 캠프 준비
	public static int N, L, R, X, ans;
	public static int[] num;
	
	// 모든 조합 구하는 함수
	public static void combi(int depth, int start, int mask, int cnt) {
		// 모든 문제를 다 탐색했다면
		if(depth == N) {
			// 고른 문제 수가 2문제 이상이라면
			if(cnt >= 2) {
				// 최대, 최소 난이도, 난이도의 합 계산
				int max = -1, min = Integer.MAX_VALUE, sum = 0, idx = 0;
				// 선택한 문제 체크
				while(mask != 0) {
					// 현재 비트가 1이라면(현재 인덱스의 문제를 선택했다면)
					if((1 & mask) == 1) {
						// 최댓값 갱신
						max = Math.max(max, num[idx]);
						// 최솟값 갱신
						min = Math.min(min, num[idx]);
						// 난이도 합 증가
						sum += num[idx];
					}
					// 다음 비트 확인
					mask >>= 1;
					// 인덱스 증가
					idx++;
				}
				// 조건에 만족한다면 경우의 수 증가
				if(max-min >= X && L <= sum && sum <= R) ans++;
			}
			return;
		}
		
		// 모든 조합 확인
		for(int i  = start; i < N; i++) {
			// 한 깊이 증가, 시작점 증가, 현재 비트 그대로, 문제수 그대로 다음 탐색
			combi(depth+1, i+1, mask, cnt);
			// 한 깊이 증가, 시작점 증가, 현재 인덱스의 문제 선택, 문제수 증가 후 다음 탐색
			combi(depth+1, i+1, mask|(1<<i), cnt+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		ans = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		// 조합 계산
		combi(0, 0, 0, 0);
		
		// ans 출력
		System.out.println(ans);		
	}
}