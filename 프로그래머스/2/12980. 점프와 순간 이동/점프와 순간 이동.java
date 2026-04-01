import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        int cur = n;
        while(cur != 0) {
            if(cur % 2 == 0) cur /= 2;
            else {cur--;ans++;}
        }

        return ans;
    }
}