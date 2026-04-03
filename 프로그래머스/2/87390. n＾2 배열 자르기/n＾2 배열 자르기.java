import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int)(right - left) + 1;
        int[] answer = new int[size];
        int row = (int)(left / (long)n), col = (int)(left % (long)n);
        
        for(int i = 0; i < size; i++) {
            long idx = left + i;
            answer[i] = (int) Math.max(idx/n, idx%n)+1;
        }
        return answer;
    }
}