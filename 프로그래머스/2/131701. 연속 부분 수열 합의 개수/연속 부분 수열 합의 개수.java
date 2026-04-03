import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0, size = elements.length;
        int[] new_elem = new int[2*size];

        for(int i = 0; i < 2*size; i++) {
            int idx = i % size;
            new_elem[i] = elements[idx];
        }
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < size; i++) {
            int sum = 0;
            for(int j = i; j < size+i; j++) {
                sum += new_elem[j];
                set.add(sum);
            }
        }
        
        answer = set.size();
        
        return answer;
    }
}