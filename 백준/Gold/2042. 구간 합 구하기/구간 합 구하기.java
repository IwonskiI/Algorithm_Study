import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 2042 - 구간 합 구하기
	
	// 초기 수열 입력
	public static long[] lst;
	// 세그먼트 트리 구성
	public static long[] tree;
	
	// 세그먼트 트리 초기화
	public static long init(int start, int end, int node) {
		// 더 이상 좁힐 구간이 없으면 현재 트리 위치에 값 할당
		if(start == end) return tree[node] = lst[start];
		// 중간 값 계산
		int mid = (start + end) / 2;
		// 현재 위치에 하위 노드의 합을 할당
		return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2 + 1);
	}
	
	// 구간합 구하기
	public static long sum(int start, int end, int node, int left, int right) {
		// 구하려는 범위가 주어진 범위 밖이라면 0 반환
		if(left > end || right < start) return 0;
		// 구하려는 범위가 주어진 범위 안이라면 현재 값 반환
		if(left <= start && end <= right) return tree[node];
		// 중간 값 계산
		int mid = (start + end) / 2;
		// 좌측으로 구간합 + 우측으로 구간합 계산
		return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2 + 1, left, right);
	}
	
	// 값 수정하기
	public static void update(int start, int end, int node, int index, long diff) {
		// 수정하고자 하는 값이 범위 밖이면 수정 X
		if(index < start || end < index) return;
		// 기존 값에 차이만큼 더해줌 
		tree[node] += diff;
		// 더 이상 좁힐 범위가 없으면 종료
		if(start == end) return;
		// 중간 값 계산
		int mid = (start + end) / 2;
		// 세분화 된 구간합 갱신 (좌, 우)
		update(start, mid, node*2, index, diff);
		update(mid+1, end, node*2 + 1, index, diff);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		lst = new long[N];
		// 가장 가까운 제곱수의 2배 -> N의 4배로 설정하면 안전한 크기
		tree = new long[4*N];
		
		// 초기 수열 입력
		for(int i = 0; i < N; i++) {
			lst[i] = Long.parseLong(br.readLine());
		}
		
		// 세그먼트 트리 구성
		init(0, N-1, 1);
		
		// 입력값에 따른 수식 처리
		for(int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int mode = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			// 값 수정
			if(mode == 1) {
				long diff = b - lst[a-1];
				lst[a-1] = b;
				update(0, N-1, 1, a-1, diff);
			}
			// 누적 합 구하기
			else if(mode == 2) {
				sb.append(sum(0, N-1, 1, a-1, (int)b-1)).append("\n");
			}
		}
		
		System.out.println(sb.toString());
		return;
	}

}
