import java.util.*;

class Solution {
    // 무한대를 나타낼 INF (OVERFLOW 방지용 나누기 3)
    final int INF = Integer.MAX_VALUE / 3;
    
    public int steinerTree(int n, List<List<int[]>> adj, int s, int a, int b) {
        // s, a, b 각각을 시작점으로 하는 다익스트라 수행 (최단 거리 계산)
        int[] distFromS = dijkstra(n, adj, s);
        int[] distFromA = dijkstra(n, adj, a);
        int[] distFromB = dijkstra(n, adj, b);

        // 중앙 거점 m을 0부터 n-1까지 모두 시도하여 최소 비용 탐색
        int minCost = INF;

        for (int m = 0; m < n; m++) {
            // 정점 m에서 세 터미널까지의 거리 합
            int totalCost = distFromS[m] + distFromA[m] + distFromB[m];
            
            if (totalCost < minCost) {
                minCost = totalCost;
            }
        }

        return minCost;
    }
    
    // 단일 출발점 다익스트라 알고리즘
    public int[] dijkstra(int n, List<List<int[]>> adj, int start) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> {
                if(a[0] == b[0]) return a[1] - b[1];
                else return a[0] - b[0];
            }
        );
        pq.offer(new int[] {0, start});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int idx = cur[1], cost = cur[0];

            if (cost > dist[idx]) continue;

            for (int[] edge : adj.get(idx)) {
                int to = edge[0], fare = edge[1];
                if (dist[idx] + fare < dist[to]) {
                    dist[to] = dist[idx] + fare;
                    pq.offer(new int[] {dist[to], to});
                }
            }
        }

        return dist;
    }
    
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        
        for(int[] fare : fares) {
            int v1 = fare[0] - 1, v2 = fare[1] - 1, cost = fare[2];
            adj.get(v1).add(new int[] {v2, cost});
            adj.get(v2).add(new int[] {v1, cost});
        }
        
        answer = steinerTree(n, adj, s - 1, a - 1, b - 1);
        
        return answer;
    }
}


