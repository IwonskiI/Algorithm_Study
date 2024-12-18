import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 1202 - 보석 도둑
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 보석의 수 N, 가방의 수 K
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 보석의 정보 gems
		int[][] gems = new int[N][2];
		// 선택된 보석인지 나타내는 gem_in
		boolean[] gem_in = new boolean[N];
		// 가방의 정보 bags
		int[] bags = new int[K];
		
		// 보석과 가방의 정보 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			gems[i][0] = Integer.parseInt(st.nextToken());
			gems[i][1] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		
		// 보석과 가방을 무게 오름차 순으로 정렬
		Arrays.sort(gems, (a, b) -> a[0] - b[0]);
		Arrays.sort(bags);
		
		// 최대 가치를 저장할 ans
		Long ans = 0L;
		// 가치를 비교할 PQ
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		// 확인할 보석의 인덱스
		int i = 0;
		
		// 각 가방에 대해 담을 수 있는 최대 값 계산
		for(int bag : bags) {
			// 현재 가방에 담을 수 있는 보석은 모두 PQ에 추가 (가치가 높은 순서대로 정렬됨)
			while(i < N && gems[i][0] <= bag) {
				pq.offer(gems[i++][1]);
			}
			
			// 넣은 수 있는 모든 보석을 확인한 뒤, PQ에 값이 남아 있다면
			if(!pq.isEmpty()) {
				// 최대 가치에 가장 높은 가치만큼 증가
				ans += pq.poll();
			}
		}
		
		// 최종 가치 출력
		System.out.println(ans);
	}

}