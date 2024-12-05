import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 정답을 저장할 변수
        long answer = 0;
        // 일의 갯수
        int size = works.length;
        
        // 일을 작업량이 많은 순서대로 담아둘 PQ
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        // PQ에 일 삽입
        for(int i = 0; i < size; i++){
            pq.offer(works[i]);
        }
        
        // 업무량이 많은 순서대로 작업량 감소시키기
        for(int i = 0; i < n; i++){
            // 업무를 완료했다면 종료
            if(size == 0) break;
            // 현재 업무량이 가장 많은 일 선택
            int high = pq.poll();
            // 현재 일을 1만큼 했을 때 완료 된다면 일 감소 (PQ에 삽입 X)
            if(high - 1 == 0) size--;
            // 아니라면 1만큼 일 한 뒤, 다시 pq에 삽입
            else pq.offer(high - 1);
        }
        
        // n시간동안 일한 뒤 남은 업무량으로 야근 피로도 계산
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            answer += cur * cur;
        }
        
        return answer;
    }
}