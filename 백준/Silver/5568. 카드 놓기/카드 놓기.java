import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 5568 - 카드 놓기
	public static int N, K;
	public static int[] lst;
	public static boolean[] visited;
	public static HashSet<String> set = new HashSet<>();
	
	// 순열 생성
	public static void perm(int cnt, String res) {
		// K개를 뽑았다면
		if(cnt == K) {
			// 만든 숫자를 set에 추가
			set.add(res);
			return;
		}
		
		// K개를 뽑기
		for(int i = 0; i < N; i++) {
			// 이미 사용중인 카드라면 스킵
			if(visited[i]) continue;
			// 현재 카드 선택
			visited[i] = true;
			// 다음 카드 선택 진행, 지금까지의 결과에 현재 숫자 추가
			perm(cnt+1, res + lst[i]);
			// 카드 선택 해제
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 카드의 개수 N
		N = Integer.parseInt(br.readLine());
		// 뽑을 카드 수 K
		K = Integer.parseInt(br.readLine());
		
		// 카드의 종류
		lst = new int[N];
		// 카드의 사용여부
		visited = new boolean[N];
		
		// 카드 입력
		for(int i = 0; i < N; i++) {
			lst[i] = Integer.parseInt(br.readLine());
		}
		
		// 순열 생성
		perm(0, "");
		
		// 만들어진 수의 개수 저장
		sb.append(set.size());
		
		// 최종 결과 출력
		System.out.println(sb.toString());
	}
}