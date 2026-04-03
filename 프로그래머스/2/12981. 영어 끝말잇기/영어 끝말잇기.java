import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        HashSet<String> dict = new HashSet<>();
        int[] answer = {0, 0};
        
        char prev = ' ';
        
        for(int i = 0; i < words.length; i++) {
            if((dict.contains(words[i])) || (i!=0 && prev != words[i].charAt(0))){
                answer[0] = (i%n) + 1;
                answer[1] = (i/n) + 1;
                break;
            }
            else {
                prev = words[i].charAt(words[i].length()-1);
                dict.add(words[i]);
            }
        }

        return answer;
    }
}