import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 2003 - 수들의 합 2
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 총 경우의 수 저장
        int ans = 0;
        
        // 수열의 개수 N, 구해야하는 합 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 초기 입력
        int[] lst = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            lst[i] = Integer.parseInt(st.nextToken());
        }
        
        // 시작점 sp, 끝점 ep, 지금까지의 합 sum
        int sp = 0, ep = 0, sum = lst[0];
        
        while(true) {
            // 합이 구하려는 수와 같다면 정답 증가
            if(sum == M) ans++;
            
            // 합이 M보다 작거나 같다면
            if(sum <= M){
                // 수를 더 크게 만들기 위해 끝점 증가
                ep++;
                // 끝점이 배열의 끝에 도달했다면 종료
                if(ep == N) break;
                // 끝점을 한 칸 이동한 뒤, 해당 값을 합에 더함
                sum += lst[ep];
            }
            // 합이 M보다 크다면
            else{
                // 수를 작게 하기 위해 시작점 뒤로 땡김
                sum -= lst[sp];
                // 시작점 증가
                sp++;
                // 시작점이 범위 밖으로 나가면 종료
                if(sp == N) break;
            }
        }
        
        // ans 저장
        sb.append(ans);
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }

}
