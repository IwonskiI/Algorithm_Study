import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 27294 - 몇개고?
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 시간 T와 술의 여부 S 입력
        int T = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        
        // 밥알의 갯수
        int ans = 0;
        
        // 술과 함께 먹는다면 무조건 280개
        if(S == 1) ans = 280;
        // 아니랴면
        else{
            // 점심식사라면 320개
            if(12 <= T && T <= 16) ans = 320;
            // 아니라면 280개
            else ans = 280;
        }
        
        // 최종 정답 출력
        System.out.println(ans);
    }
}