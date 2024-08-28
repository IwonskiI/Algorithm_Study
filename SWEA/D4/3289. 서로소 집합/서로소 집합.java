import java.util.*;
import java.io.*;
  
public class Solution {
      
    // SWEA 3289 - 서로소 집합
	public static int N, M;
	public static int[] parents;
	
	// union 함수
	public static void union(int x, int y) {
		// x의 부모 찾기
		x = find(x);
		// y의 부모 찾기
		y = find(y);
		// x의 부모가 더 작다면 y의 부모를 x의 부모로 설정
		if(x < y) parents[y] = x;
		// y의 부모가 더 작다면 x의 부모를 y의 부모로 설정
		else parents[x] = y;
	}
	
	// find 함수
	public static int find(int x) {
		// 현재 값이 루트 노드라면 return
		if(x == parents[x]) return x;
		// 아니라면 부모를 탐색 - path compression
		else return parents[x] = find(parents[x]);
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	String ans = "";
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	parents = new int[N + 1];
        	// 부모를 자기 자신으로 초기화
        	for(int i = 1; i <= N; i++) {
        		parents[i] = i;
        	}
        	
        	for(int i = 0; i < M; i++) {
        		st = new StringTokenizer(br.readLine());
        		// 연산 종류
        		int op = Integer.parseInt(st.nextToken());
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		switch(op) {
        		// 합집합 연산
        		case 0:
        			// a와 b를 합집합 연산
        			union(a, b);
        			break;
        		case 1:
        			// a와 b가 같은 집합에 있는지 확인
        			if(find(a) == find(b)) ans += "1";
        			else ans += "0";
        			break;
        		}
        	}
        	
        	// 최종 정답 저장
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        // 결과 출력
        System.out.println(sb.toString());
    }
  
}