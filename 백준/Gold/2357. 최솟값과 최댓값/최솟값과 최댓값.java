import java.io.*;
import java.util.*;

public class Main {

	// BOJ 2357  - 최솟값과 최댓값
	public static int N, M;
	public static int[] lst;
	public static int[][] tree;
	
	// 세그먼트 트리 구축
	public static int[] init(int start, int end, int node) {
		// 리프 노드에 도달하면 최대, 최소 모두 자기 자신
		if(start == end) return tree[node] = new int[] {lst[start], lst[start]};
		
		// 중간 값 계산
		int mid = (start + end) / 2;
		
		// 왼쪽 트리와 오른쪽 트리 계산
		int[] left = init(start, mid, node*2), right = init(mid+1, end, node*2 + 1);
		
		// 현재 트리는 왼쪽과 오른쪽의 최댓값과 최솟값을 저장
		return tree[node] = new int[] {Math.min(left[0], right[0]), Math.max(left[1], right[1])};
	}
	
	// 범위 내 최대, 최소 구하기
	public static int[] get_value(int start, int end, int left, int right, int node) {
		// 범위를 이탈했으면 최소, 최대를 계산 X
		if(left > end || right < start) return new int[] {1000000001, -1};
		// 범위 안이면 현재 범위의 최대, 최소를 반환
		if(left <= start && end <= right) return tree[node];
		// 중간 값 계산
		int mid = (start + end) / 2;
		// 왼쪽 트리와 오른쪽 트리의 최대, 최소값 구하기
		int[] leftArr = get_value(start, mid, left, right, node*2),
				rightArr = get_value(mid+1, end, left, right, node*2 + 1);
		// 왼쪽과 오른쪽 범위의 값중 최소, 최대값 반환
		return new int[] {Math.min(leftArr[0], rightArr[0]), Math.max(leftArr[1], rightArr[1])};
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lst = new int[N];
        tree = new int[4*N][2];
        
        // 초기 수열 입력
        for(int i = 0; i < N; i++) {
        	lst[i] = Integer.parseInt(br.readLine());
        }
        
        // 세그먼트 트리 생성
        init(0, N - 1, 1);
        
        // 범위의 최소, 최대 구하기
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int[] res = get_value(0, N-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 1);
        	sb.append(res[0]).append(" ").append(res[1]).append("\n");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}