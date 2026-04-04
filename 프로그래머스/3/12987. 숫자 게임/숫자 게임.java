import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        for(int i = 0; i < A.length / 2; i++){
            int tempA = A[i], tempB = B[i];
            A[i] = A[A.length - i - 1];
            A[A.length - i - 1] = tempA;
            B[i] = B[A.length - i - 1];
            B[A.length - i - 1] = tempB;
        }
        int a_idx = 0, b_idx = 0;
        
        while(a_idx < A.length) {
            if(A[a_idx] < B[b_idx]){
                a_idx++; b_idx++;
                answer++;
            }
            else a_idx++;
        }
        
        
        return answer;
    }
}