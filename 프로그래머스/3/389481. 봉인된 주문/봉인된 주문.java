// System.out.println();
import java.util.*;
import java.math.*;

class Solution {
    
    public BigInteger stringToLong(String str) {
        BigInteger ret = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(26);

        for(int i = 0; i < str.length(); i++) {
            int ch = str.charAt(i) - 'a' + 1;
            ret = ret.multiply(base).add(BigInteger.valueOf(ch));
        }
        return ret;
    }
    
    public String longToString(long num) {
        StringBuilder sb = new StringBuilder();
        while(num > 0) {
            long idx = (num % 26 == 0 ? 26 : num % 26);
            char ch = (char)((idx - 1) + 'a');
            sb.insert(0, ch);
            if(num % 26 == 0){
                num /= 26;
                num--;
            } 
            else num /= 26;
            
        }
        return sb.toString();
    }
    
    public String solution(long n, String[] bans) {
        String answer = "";
        Arrays.sort(bans, (o1, o2) -> {
            if(o1.length() == o2.length()) return o1.compareTo(o2);
            else return o1.length() - o2.length();
            });
        for(String ban : bans) {
            if(stringToLong(ban).compareTo(new BigInteger(Long.toString(n))) <= 0) n++;
        }
        answer = longToString(n);
        return answer;
    }
}