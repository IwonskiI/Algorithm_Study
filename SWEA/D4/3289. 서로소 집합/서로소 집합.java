import java.util.*;
import java.io.*;
  
public class Solution {
      
    // SWEA 3289 - 서로소 집합
	public static int N, M;
	public static int[][] parents;
	
	// union 함수
	public static void union(int x, int y) {
		// x의 부모를 찾고, 부모의 크기와 높이 찾기
		int a = find(x);
		int a_h = parents[a][1];
		// y의 부모를 찾고, 부모의 크기와 높이 찾기
		int b = find(y);
		int b_h = parents[b][1];
		
		// 두 부모가 같다면 return
		if(a == b) return;
		
		// 높이가 높은 트리에 높이가 낮은 트리를 붙여줌
		if(a_h > b_h) {
			parents[a][0] = b;
		}
		else{
			parents[b][0] = a;
		}
		
		// 높이가 같았다면 한 트리의 루트 노드 하나 아래에 다른 한 트리를 붙여줬기 때문에 최대 높이 증가
		if(a_h == b_h) parents[a][1]++;
	}
	
	// find 함수
	public static int find(int x) {
		// 현재 값이 루트 노드라면 return
		if(parents[x][0] < 0) return x;
		// 아니라면 부모를 탐색 - path compression
		else return parents[x][0] = find(parents[x][0]);
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
        	parents = new int[N + 1][2];
        	// 부모를 자기 자신으로 초기화
        	for(int i = 1; i <= N; i++) {
        		parents[i][0] = -1;
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