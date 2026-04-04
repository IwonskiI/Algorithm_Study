import java.util.*;

class Solution {
    
    int size = 0, min_a = 9999, N, M;
    
    public void dfs(int[][] val, int a, int b, int depth) {
        if(depth == size) {
            min_a = a < min_a ? a : min_a;
            return;
        }
        if(a + val[depth][0] < N && a+val[depth][0] < min_a) dfs(val, a+val[depth][0], b, depth+1);
        if(b + val[depth][1] < M && a < min_a) dfs(val, a, b+val[depth][1], depth+1);
    }
    
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        N = n; M = m;
        size = info.length;
        Arrays.sort(info, (o1, o2) -> {
            if(o2[0] == o1[0]) return o2[1] - o1[1];
            else return o2[0] - o1[0];
            });
        dfs(info, 0, 0, 0);
        answer = min_a;
        if(answer == 9999) answer = -1;
        return answer;
    }
}