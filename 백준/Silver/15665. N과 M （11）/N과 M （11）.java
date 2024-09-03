import java.io.*;
import java.util.*;


public class Main {

	// BOJ 15651 - N과 M(3)
	public static StringBuilder sb = new StringBuilder();
	// 1부터 N까지의 수 중 M개의 숫자를 중복을 허용해서 뽑기
	public static int N, M;
	// M개의 숫자를 뽑은 뒤 저장
	public static int[] lst, number;
	
	// 순열 생성 함수
	public static void perm(int cnt) {
		// M개를 모두 뽑았다면
		if(cnt == M) {
			// 순열 저장
			for(int i = 0; i < M; i++) {
				sb.append(number[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		int temp = 0;
		// 아직 M개를 뽑기 전이라면
		for(int i = 0; i < N; i++) {
			// 이전 숫자보다 작거나, 직전에 사용한 숫자라면 스킵
			if(lst[i] == temp) continue;
			// lst[i]를 현재 위치에 저장
			number[cnt] = lst[i];
			// 이전 값 갱신
			temp = lst[i];
			// 다음 숫자 뽑기
			perm(cnt+1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// N개의 숫자를 입력받을 배열 선언
		lst = new int[N];
		// M개의 숫자를 저장할 배열 선언
		number = new int[M];
		
		// 수열 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
		}
		// 사전 순 출력을 위해 정렬
		Arrays.sort(lst);
		
		// 순열 생성 시작 (0번째 원소부터 시작)
		perm(0);
		// 최종 결과 출력
		System.out.println(sb.toString());
		
	}

}
