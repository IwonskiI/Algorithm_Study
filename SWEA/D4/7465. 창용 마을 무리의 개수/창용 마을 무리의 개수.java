import java.util.*;
import java.io.*;


public class Solution {
	
	// SWEA 7465 - 창용 마을 무리의 개수
	public static int N, M;
	public static int[] parents, rank;
	
	// 부모 찾기 함수
	public static int find(int x) {
		// path compression
		if(x != parents[x]) parents[x] = find(parents[x]);
		return parents[x];
	}
	
	// 부모 연결 함수
	public static void union(int a, int b) {
		// 각각의 부모를 찾기
		a = find(a); b = find(b);
		
		// 부모가 같다면 이미 연결 되었으므로 종료
		if(a == b) return;
		
		// 연결되지 않았다면 연결 진행
		
		// 더 높은 트리에 낮은 트리를 연결
		// 높이가 a가 더 높다면 b의 부모를 a로 설정
		if(rank[a] > rank[b]) parents[b] = a;
		else {
			// 아니라면 a의 부모를 b로 설정
			parents[a] = b;
			// 만약 높이가 같았다면 b아래에 붙었으므로 높이 1 증가 (a 위에 b한칸 증가)
			if(rank[a] == rank[b]) rank[b]++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			rank = new int[N+1];
			
			// 부모 및 높이 초기화
			for(int i = 1; i <= N; i++) {
				parents[i] = i;
				rank[i] = 1;
			}
			
			// 각각의 사람들을 연결하기
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// 그룹 수 세기
			int ans = 0;
			// 방문 배열
			boolean[] visited = new boolean[N+1];
			
			// 1번부터 N번까지의 사람의 부모를 검색
			for(int i = 1; i <= N; i++) {
				int cur = i;
				while(true) {
					if(cur == parents[cur]) break;
					cur = parents[cur];
				}
				// 부모를 처음 방문했다면 그룹 수 증가
				if(!visited[cur]) {
					visited[cur] = true;
					ans++;
				}
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());

	}

}