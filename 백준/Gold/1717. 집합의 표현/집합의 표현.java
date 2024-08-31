import java.util.*;
import java.io.*;
    
public class Main {
        
    // SWEA 3289 - 서로소 집합
    public static int N, M;
    public static int[] parents, rank;
      
    // union 함수
    public static void union(int x, int y) {
        // x, y의 부모 찾기
        x = find(x);
        y = find(y);
          
        // 두 부모가 같다면 return
        if(x == y) return;
          
        // 높이가 높은 트리에 높이가 낮은 트리를 붙여줌
        if(rank[x] > rank[y]) parents[y] = x;
        else {
            parents[x] = y;
            // 높이가 같았다면 한 트리의 루트 노드 하나 아래에 다른 한 트리를 붙여줬기 때문에 최대 높이 증가
            if(rank[x] == rank[y]) rank[x]++;
        }
    }
      
    // find 함수
    public static int find(int x) {
        // 현재 값이 루트 노드가 아니라면 부모를 탐색하며 저장 - path compression
        if(parents[x] != x) parents[x] = find(parents[x]);
        // 부모 노드 리턴
        return parents[x];
    }
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        rank = new int[N + 1];
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
            if(op == 0)
                // a와 b를 합집합 연산
                union(a, b);
            else if(op == 1) {
                // a와 b가 같은 집합에 있다면 1 저장
                if(find(a) == find(b)) sb.append("YES\n");
                // 아니라면 0 저장
                else sb.append("NO\n");
            }
        }
        // 결과 출력
        System.out.println(sb.toString());
    }
    
}