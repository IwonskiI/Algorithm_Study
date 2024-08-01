import java.io.*;
import java.util.*;

public class Main {

	//BOJ 1654 - 랜선 자르기
	
	// l의 길이로 가지고 있는 랜선을 잘랐을 때, 나오는 랜선의 수 구하기
	public static int cnt_calc(long l, int[] lst) {
		int res = 0;
		for(int i = 0; i < lst.length; i++) {
			res += (lst[i] / l);
		}
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
		
		// 전체 랜선 길이 저장
		int[] lst = new int[k];
		// 랜선의 최대 길이 저장
		int max_len = 0;
		for(int i = 0; i < k; i++) {
			lst[i] = Integer.parseInt(br.readLine());
			max_len = max_len < lst[i] ? lst[i] : max_len;
		}
		
		// 이분탐색 시작 (최대치는 랜선의 최대길이)
		//*** 정수의 최대치 + 정수의 최대치가 일어날 수 있으므로 long 선언 ***
		//*** start를 0으로 두니 divisionbyzero 에러 발생 ***
		long start = 1, end = (long)max_len+1;
		
		while(start < end) {
			// 최대 길이의 절반의 길이로 잘랐을 때
			long mid = (start+end) / 2;
			
			// 랜선이 n개 이상 나온다면 더 크게 잘라지는지 확인하기 위해 시작점을 mid로 변경 
			if(cnt_calc(mid, lst) >= n) {
				start = mid + 1;
			}
			// 랜선이 n개보다 작다면 더 잘게 자르기 위해 끝점을 mid로 변경
			else if(cnt_calc(mid, lst) < n) {
				end = mid - 1;
			}
		}
		
		// *** 이진탐색 종료 후 마지막 확인 값이 조건을 만족하는지 한번 더 확인
		if(cnt_calc(start, lst) >= n) {
			System.out.println(start);
		}
		else {
			System.out.println(start - 1);			
		}
		
	}
}
