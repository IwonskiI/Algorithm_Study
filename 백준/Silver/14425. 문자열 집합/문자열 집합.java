import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 14425 - 문자열 집합
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 정답을 저장할 변수 ans
        int ans = 0;
        // 입력되는 단어를 관리할 집합 set
        HashSet<String> set = new HashSet<>();
        
        // N, M 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // set에 문자열 추가
        for(int i = 0; i < N; i++) {
            set.add(br.readLine());
        }
        
        // set에 문자열이 포함되는지 확인
        for(int i = 0; i < M; i++) {
            // 포함된다면 ans 증가
            if(set.contains(br.readLine())) ans++;
        }
        
        // ans 저장
        sb.append(ans);
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }

}
