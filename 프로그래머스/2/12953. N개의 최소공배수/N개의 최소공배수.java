import java.util.*;

class Solution {
    
    public int GCD(int a, int b) {
        if(b == 0) return a;
        else return GCD(b, a % b);
    }
    
    public int LCM(int a, int b) {
        return (a*b) / GCD(a, b);
    }
    
    public int solution(int[] arr) {
        int answer = 1;
        
        for(int num : arr) {
            answer = LCM(answer, num);
        }

        return answer;
    }
}