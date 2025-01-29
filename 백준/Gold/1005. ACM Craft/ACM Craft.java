import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] time = new int[N];
            List<List<Integer>> link = new ArrayList<>();
            int[] indegree = new int[N];  // 진입 차수 배열 추가
            int[] dp = new int[N];  // 각 건물의 최소 시간 저장
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
                link.add(new ArrayList<>());
            }
            
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int sp = Integer.parseInt(st.nextToken()) - 1;
                int ep = Integer.parseInt(st.nextToken()) - 1;
                link.get(sp).add(ep);
                indegree[ep]++;  // 진입 차수 증가
            }
            
            int X = Integer.parseInt(br.readLine()) - 1;
            
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                if (indegree[i] == 0) {
                    q.offer(i);
                    dp[i] = time[i];  // 초기 건설 시간 설정
                }
            }
            
            while (!q.isEmpty()) {
                int cur = q.poll();
                
                for (int next : link.get(cur)) {
                    dp[next] = Math.max(dp[next], dp[cur] + time[next]);  
                    indegree[next]--;  // 진입 차수 감소
                    
                    if (indegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
            
            sb.append(dp[X]).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}
