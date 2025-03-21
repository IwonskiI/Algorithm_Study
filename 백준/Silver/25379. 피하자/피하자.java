import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 25379 - 피하자
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] lst = new int[N];
        int start_odd = -1, start_even = -1;
        int last_odd = -1, last_even = -1;
        for(int i = 0; i < N; i++){
            lst[i] = Integer.parseInt(st.nextToken());
        }
        
        int odd_ans = 0, even_ans = 0;
        int odd_cnt = 0, even_cnt = 0;
        for(int i = 0; i < N; i++){
            // 짝수 계산
            if(lst[i] % 2 == 0) {
                even_ans += (i - even_cnt);
                even_cnt++;
            }
            else{
                odd_ans += (i - odd_cnt);
                odd_cnt++;
            }
        }
        
        int ans = even_ans < odd_ans ? even_ans : odd_ans;
        
        System.out.println(ans);
        
    }
}