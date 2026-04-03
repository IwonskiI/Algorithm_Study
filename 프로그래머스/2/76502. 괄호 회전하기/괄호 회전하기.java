import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        String new_s = s + s;
        int size = s.length(), sp = 0, ep = size;
        
        for(; sp < size; sp++, ep++) {
            String cur_s = new_s.substring(sp, ep);
            ArrayDeque<Character> dq = new ArrayDeque<>();
            dq.offerLast(cur_s.charAt(0));
            for(int i = 1; i < size; i++) {
                char cur = cur_s.charAt(i);
                if(!dq.isEmpty()) {
                    char top = dq.peekLast();
                    if((cur == ')' && top == '(') || (cur == ']' && top == '[') || (cur == '}' && top == '{')) dq.pollLast();
                    else dq.offerLast(cur);
                }
                else dq.offerLast(cur);
            }
            
            if(dq.isEmpty()) answer++;
        }
        
        
        
        return answer;
    }
}