import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ4153 - 직가삼각형
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 3 변을 저장
		int[] num_lst = new int[3];
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			// 가장 긴 빗변의 인덱스와 그 값을 저장
			int max_idx = -1, max = -1;
			for(int i = 0; i < 3; i++) {
				num_lst[i] = Integer.parseInt(st.nextToken());
				if(max < num_lst[i]) {
					max = num_lst[i];
					max_idx = i;
				}
			}
			
			// 3가지 입력값이 모두 0 이라면 종료
			if(num_lst[0] == 0 && num_lst[1] == 0 && num_lst[2] == 0) break;
			
			// 빗변 제곱 계산
			int side = max*max;
			// 빗변을 제외한 나머지 변의 제곱 합
			int other = 0;
			for(int i = 0; i < 3; i++) {
				if(i == max_idx) continue;
				other += num_lst[i]*num_lst[i];
			}
			
			// 피타고라스 정리가 성립하면 right, 아니면 wrong 출력
			if(side == other) sb.append("right\n");
			else sb.append("wrong\n");
			
		}
		
		System.out.println(sb.toString());
	}
 
}
