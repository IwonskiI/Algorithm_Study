import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 각 정점까지의 거리 초기화
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;
        
        // 간선 정보 연결할 2차원 리스트 초기화
        ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
        for(int i = 0; i < n+1; i++) lst.add(new ArrayList<>());
        
        // 간선 정보 등록 (양방향)
        for(int i = 0; i < roads.length; i++) {
            lst.get(roads[i][0]).add(roads[i][1]);
            lst.get(roads[i][1]).add(roads[i][0]);
        }
        
        // 다익스트라 계산할 Deque
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(destination);
        
        while(!dq.isEmpty()) {
            int cur = dq.poll();
            int c_dist = dist[cur];
            for(int i : lst.get(cur)){
                if(c_dist+1 < dist[i]) {
                    dist[i] = c_dist+1;
                    dq.offer(i);
                }
            }
        }
        
        // 정답 계산
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
            if(answer[i] == Integer.MAX_VALUE) answer[i] = -1;
        }
        return answer;
    }
}