import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 25496 - 장신구 명장 임스
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 현재 피로도 P
        int P = Integer.parseInt(st.nextToken());
        // 장신구의 개수 N
        int N = Integer.parseInt(st.nextToken());
        
        int[] lst = new int[N];
        st = new StringTokenizer(br.readLine());
        
        // 장신구 제작에 필요한 피로도 입력
        for(int i = 0; i < N; i++){
            lst[i] = Integer.parseInt(st.nextToken());
        }
        
        // 피로도를 오름차순으로 정렬
        Arrays.sort(lst);
        
        // 만들 수 있는 장신구의 수
        int ans = 0;
        // 피로도가 200 미만일동안 반복 (최대 N개까지만 확인)
        while(P < 200 && ans < N){
            // 현재 피로도에 가장 적은 피로도를 더해서 장신구 1개 제작
            P += lst[ans];
            // 만든 장신구 수 증가
            ans++;
            // 장신구 제작 후 피로도가 200 미만이면 다음 낮은 피로도가 필요한 장신구 제작 진행(반복)
        }
        
        // 최종 만들어 낸 장신구의 수 출력
        System.out.println(ans);
    }
}