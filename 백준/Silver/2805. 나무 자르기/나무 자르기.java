import java.io.*;
import java.util.*;

public class Main {

	// BOJ 2805 - 나무 자르기
	public static int N, M, ans;
	public static int[] lst;
	
	// h로 잘랐을 때 남는 나무의 합 구하기
	public static long calc(int h) {
		long res = 0;
		for(int i = 0; i < N; i++) {
			int cut = lst[i] - h;
			// 잘라낸 나무의 높이가 음수라면 남는 나무가 없으므로 더하지 않음
			if(cut > 0) res += cut;
		}
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 초기 입력
		lst = new int[N];
		st = new StringTokenizer(br.readLine());
		// 가장 높은 나무의 높이를 저장
		int max = 0;
		for(int i = 0; i < N; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
			if(max < lst[i]) max = lst[i];
		}
		
		// 가져갈 나무는 최소 1부터 시작하기 때문에 max보다 1작은 높이에서는 잘라야함
		int start = 1, end = max - 1;
		
		// 탐색지점이 교차할 때까지 진행
		while(start < end) {
			// 중간 지점의 높이에서 가능한지 계산
			int mid = (start + end) / 2;
			long res = calc(mid);
			// 잘려진 통나무가 M보다 크다면 더 높은 높이에서 잘라보기 위해 시작점을 중간 지점에서 + 1
			if(res > M) start = mid + 1;
			// 잘려진 통나무가 M보다 작다면 더 낮은 높이에서 잘라보기 위해 끝점을 중간 지점에서 - 1			
			else if(res < M) end = mid - 1;
			// 정확하게 원하는 높이가 되었다면 중간 지점을 저장하고 반복문 종료
			else {
				end = mid;
				break;
			}
		}
		
		// 이분 탐색 종료 후 최종 테스트
		long fres = calc(end);
		// final result가 M보다 작다면 한칸 더 아래에서 절단 (클 경우는 상관 없음) 
		if(fres < M) end -= 1;
		
		// 탐색 종료 후 정답 출력
		System.out.println(end);
		
	}

}