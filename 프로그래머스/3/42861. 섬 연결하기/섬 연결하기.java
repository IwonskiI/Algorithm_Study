import java.util.*;

class Solution {
    // 1. Path Compression(경로 압축)을 적용한 find 함수
    public int find(int[] par, int v) {
        if (par[v] == v) return v;
        return par[v] = find(par, par[v]); // 부모를 최상위 루트로 direct 업데이트
    }
    
    // 2. 두 집합을 하나로 합치는 union 함수
    public boolean union(int[] par, int u, int v) {
        int rootU = find(par, u);
        int rootV = find(par, v);
        
        // 이미 같은 집합이면 합칠 필요 없음 (사이클 방지)
        if (rootU == rootV) return false;
        
        // 한쪽 루트를 다른쪽 루트 밑으로 넣음
        par[rootU] = rootV;
        return true;
    }

    public int solution(int n, int[][] costs) {
        // 비용(cost) 기준 오름차순 정렬
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        // 부모 배열 초기화 (자신을 부모로 설정)
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int answer = 0;
        int edgesUsed = 0;

        for (int[] edge : costs) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            // 두 노드가 다른 집합이라면 연결 (비용 추가)
            if (union(parent, u, v)) {
                answer += cost;
                edgesUsed++;
                
                // MST의 간선 개수는 항상 (노드 수 - 1)개
                if (edgesUsed == n - 1) break;
            }
        }

        return answer;
    }
}