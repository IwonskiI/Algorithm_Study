import java.util.*;

public class Solution {
    public int solution(int[] stones, int k) {
        int n = stones.length;
        // 각 슬라이딩 윈도우의 최댓값들을 저장할 배열
        int[] maxArr = new int[n];
        
        // 데크에는 stones의 '인덱스'를 저장
        Deque<Integer> q = new ArrayDeque<>();
        
        // 첫 번째 원소 초기화
        q.offerLast(0);
        maxArr[0] = stones[0];
        
        for (int i = 1; i < n; i++) {
            int tmp = stones[i];
            
            // 현재 값보다 작은 값을 가진 데크 뒤쪽의 인덱스들을 모두 제거
            while (!q.isEmpty() && stones[q.peekLast()] < tmp) {
                q.pollLast();
            }
            
            // 현재 인덱스 추가
            q.offerLast(i);
            
            // 윈도우 크기 k를 벗어난 오래된 인덱스는 데크 앞쪽에서 제거
            while (!q.isEmpty() && i - q.peekFirst() >= k) {
                q.pollFirst();
            }
            
            // 데크의 맨 앞에 있는 인덱스의 값이 현재 윈도우의 최댓값
            maxArr[i] = stones[q.peekFirst()];
        }
        
        // maxArr[k-1]부터 끝까지의 값 중 최솟값을 탐색하여 반환
        int answer = Integer.MAX_VALUE;
        for (int i = k - 1; i < n; i++) {
            if (maxArr[i] < answer) {
                answer = maxArr[i];
            }
        }
        
        return answer;
    }
}