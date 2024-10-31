import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 16198 - 에너지 모으기
	public static int N, energy, ans;
	public static int[] lst;
	public static boolean[] deleted;
	
	// 조합 생성
	public static void combi(int cnt, int sum) {
		// 첫 번째와 마지막 구슬만 남아있다면
		if(energy == 2) {
			// 지금까지의 에너지 비교 후 최댓값 갱신 후 종료
			ans = Math.max(ans, sum);
			return;
		}
		
		// 첫 번째와 마지막 에너지 구슬을 제외한 나머지 구슬을 뽑는 경우의 수 계산
		for(int i = 1; i < N-1; i++) {
			// 뽑아서 제거된 구슬이라면 스킵
			if(deleted[i]) continue;
			// 현재 구슬의 왼쪽과 오른쪽 구슬 찾기
			int left = i -1, right = i + 1;
			// 제거되지 않은 왼쪽 구슬이 나올 동안 인덱스 감소
			while(deleted[left]) {
				left--;
			}
			// 제거되지 않은 오른쪽 구슬이 나올 때 까지 인덱스 증가 
			while(deleted[right]) {
				right++;
			}
			// 양 옆의 구슬의 곱
			int score = lst[left] * lst[right];
			// 현재 구슬 제거
			deleted[i] = true;
			// 구슬 수 감소
			energy--;
			// 다음 구슬 선택, 기존 점수에 현재 곱을 더해서 진행
			combi(cnt+1, sum + score);
			// 탐색 후 구슬 선택 해제(다시 추가)
			deleted[i] = false;
			// 구슬 수 증가
			energy++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		lst = new int[N];
		deleted = new boolean[N];
		energy = N;
		
		// 구슬 정보 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
		}
		
		// 조합 생성
		combi(0, 0);
		
		// 최종 결과 출력
		System.out.println(ans);
	}
}