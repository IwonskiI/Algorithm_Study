import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> count = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        
        for(int tan : tangerine) {
            Integer cnt = count.getOrDefault(tan, 0);
            count.put(tan, cnt+1);
        }
        
        Set<Integer> set = count.keySet();
        for(int size : set) {
            int cnt = count.get(size);
            pq.add(new int[] {size, cnt});
        }
        
        while(k > 0) {
            int[] cur = pq.poll();
            k -= cur[1];
            answer++;
        }
        
        return answer;
    }
}