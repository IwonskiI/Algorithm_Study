import java.util.*;

class Solution {
    
    static int max_cnt = 0, size = 0;
    static boolean[] visited;
    
    public void dfs(int[][] dungeons, int depth, int cnt, int energy) {
        if(depth == size) {
            max_cnt = max_cnt < cnt ? cnt : max_cnt;
            return;
        }
        
        for(int i = 0; i < size; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            if(energy >= dungeons[i][0]) dfs(dungeons, depth+1, cnt+1, energy - dungeons[i][1]);
            else dfs(dungeons, depth+1, cnt, energy);
            visited[i] = false;
        }
        
    }
    
    public int solution(int k, int[][] dungeons) {
        size = dungeons.length;
        visited = new boolean[size];
        
        dfs(dungeons, 0, 0, k);
        
        return max_cnt;
    }
}