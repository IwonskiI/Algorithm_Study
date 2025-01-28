import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 1700 - 멀티탭 스케줄링
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int cnt = 0;
        int[] lst = new int[K];
        List<Deque<Integer>> order = new ArrayList<>();
        for(int i = 0; i < K; i++){
            order.add(new ArrayDeque<>());
        }
        boolean[] using = new boolean[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            int cur = Integer.parseInt(st.nextToken())-1;
            lst[i] = cur;
            order.get(cur).offer(i);
        }
        for(int i = 0; i < K; i++) {
        	order.get(i).offerLast(101);
        }
        
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for(int i = 0; i < K; i++){
        	order.get(lst[i]).poll();
            if(!using[lst[i]]){
                if(cnt < N) cnt++;
                else{
                    int[] last = pq.poll();
                    using[last[0]] = false;
                    ans++;
                }
                using[lst[i]] = true;
            }
            pq.offer(new int[] {lst[i], order.get(lst[i]).peek()});
        }
        System.out.println(ans);
    }
}