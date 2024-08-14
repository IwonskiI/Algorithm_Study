import java.util.*;
import java.io.*;
 
 
class Solution
{
    // SWEA 2072 - 홀수만 더하기
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            // 합을 저장할 변수
            int ans = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 10; i++) {
                // 읽어온 숫자가 홀수라면 더하기
                int num = Integer.parseInt(st.nextToken());
                if(num%2 != 0) ans += num;
            }
            // 각 테스트케이스에 대한 출력값 저장
            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
         
        // 저장된 StringBuilder 출력
        System.out.println(sb.toString());
    }
}