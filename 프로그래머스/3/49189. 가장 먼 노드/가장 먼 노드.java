import java.util.*;

class Solution {
    
    // 간선 수 최대 50000개 이므로 INF 값 10만으로 설정
    static int INF = 100000;
    // 각 지점까지 최소 거리 저장할 배열
    int[] dist;
    // 각 지점간 연결된 간선을 저장할 2차원 리스트(그래프)
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    
    
    // 다익스트라 알고리즘
    public void dijkstra(int start) {
        // MINHEAP 구현 (거리가 짧을 수록 먼저 나오게)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            else return a[0] - b[0];
            });
        
        // 시작 점 추가
        pq.offer(new int[] {0, start});
        // 시작점 거리 0으로 설정 (자기 자신)
        dist[start] = 0;
        
        // 순회
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            // d는 현재 지점까지의 거리, idx는 현재 지점 번호
            int d = cur[0], idx = cur[1];
            // 기존 현재지점까지의 거리가 더 작아서 갱신할 필요가 없으면 스킵
            if(dist[idx] < d) continue;
            // 현재 지점에서 연결된 간선 확인
            ArrayList<Integer> eds = graph.get(idx);
            for(int ed : eds) {
                // 연결된 지점까지의 기존 거리가 현재지점에서 한칸 더 가는것보다 멀면 갱신
                if(dist[ed] > d + 1){
                    // 거리는 현재 지점까지의 거리 + 1
                    dist[ed] = d + 1;
                    // 해당 위치에서 더 뻗어나갈 수 있는 지점 찾기 위해 pq에 삽입
                    pq.offer(new int[] {d+1, ed});
                }
            }
        }
        
        
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        // 인덱스를 정점 번호로 쓰기 위해 n+1개의 배열 선언
        dist = new int[n+1];
        // 그래프도 n개까지 초기화
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            dist[i] = INF;
        }
        
        // 그래프 간선 연결
        for(int[] e : edge) {
            int v1 = e[0], v2 = e[1];
            // 양방향 그래프이기 때문에 양쪽 지점 둘 다 연결
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        
        // 다익스트라 알고리즘
        dijkstra(1);
        
        // 1번 정점으로부터 떨어진 최대거리 저장
        int len = 0;
        // 순회 (0번 인덱스는 INF로 초기화 되어있을 것이기 때문에 1부터 시작 ~ n까지 탐색)
        for(int i = 1; i <= n; i++) {
            // 현재 정점까지 거리가 기존 최댓값보다 크다면
            if(dist[i] > len) {
                // 최대 거리 갱신
                len = dist[i];
                // 현재 최대거리까지의 정점 1개 (현재 지점)
                answer = 1;
            }
            // 현재 최대거리와 같은 거리의 정점이라면 정점 수만 +1 처리
            else if (dist[i] == len) answer++;
        }
        
        return answer;
    }
}