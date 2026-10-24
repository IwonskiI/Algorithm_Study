import java.util.*;

class Solution {
    
    // 간선 클래스
    class Edge {
        int to;
        long weight;

        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // 다익스트라용 노드 클래스
    class Node {
        int vertex;
        long cost;

        public Node(int vertex, long cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
    
    
    final long INF = Long.MAX_VALUE / 4;
    
    public long solveSteinerTree3(int n, List<List<Edge>> adj, int t0, int t1, int t2) {
        // 1. t0, t1, t2 각각을 시작점으로 하는 다익스트라 수행 (최단 거리 계산)
        long[] distFromT0 = dijkstra(n, adj, t0);
        long[] distFromT1 = dijkstra(n, adj, t1);
        long[] distFromT2 = dijkstra(n, adj, t2);

        // 2. 만남의 장소(중앙 거점 m)를 0부터 n-1까지 모두 시도하여 최소 비용 탐색
        long minCost = INF;

        for (int m = 1; m <= n; m++) {
            // 정점 m에서 세 터미널까지의 거리 합
            long totalCost = distFromT0[m] + distFromT1[m] + distFromT2[m];
            
            if (totalCost < minCost) {
                minCost = totalCost;
            }
        }

        return minCost;
    }
    
    // 단일 출발점 다익스트라 알고리즘
    public long[] dijkstra(int n, List<List<Edge>> adj, int start) {
        long[] dist = new long[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.vertex;

            if (curr.cost > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                if (dist[u] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[u] + edge.weight;
                    pq.offer(new Node(edge.to, dist[edge.to]));
                }
            }
        }

        return dist;
    }
    
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        
        for(int[] fare : fares) {
            int v1 = fare[0], v2 = fare[1], cost = fare[2];
            adj.get(v1).add(new Edge(v2, cost));
            adj.get(v2).add(new Edge(v1, cost));
        }
        
        long result = solveSteinerTree3(n, adj, s, a, b);
        
        answer = (int) result;
        
        return answer;
    }
}


