import java.io.*;
import java.util.*;


public class Main {

	// BOJ 15651 - N과 M(3)
	public static StringBuilder sb = new StringBuilder();
	// 1부터 N까지의 수 중 M개의 숫자를 중복을 허용해서 뽑기
	public static int N, M;
	// M개의 숫자를 뽑은 뒤 저장
	public static int[] number;
	
	// 순열 생성 함수
	public static void perm(int cnt, int prev) {
		// M개를 모두 뽑았다면
		if(cnt == M) {
			// 순열 저장
			for(int i = 0; i < M; i++) {
				sb.append(number[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 아직 M개를 뽑기 전이라면
		for(int i = 0; i < N; i++) {
			// 현재 추가하려는 숫자가 이전 숫자보다 작다면 스킵 (비내림차순)
			if(i < prev) continue;
			// i+1 저장
			number[cnt] = i+1;
			// 현재 숫자를 이전 숫자로 업데이트
			prev = i;
			// 다음 숫자 뽑기
			perm(cnt+1, prev);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// M개의 숫자를 저장할 배열 선언
		number = new int[M];
		// 중복 순열 생성 시작 (시작 위치와 이전 값 전달)
		perm(0, 0);
		// 최종 결과 출력
		System.out.println(sb.toString());
		
	}

}
