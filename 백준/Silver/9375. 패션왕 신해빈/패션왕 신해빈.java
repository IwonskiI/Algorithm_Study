import java.util.*;
import java.io.*;

public class Main {
    
    // BOJ 9375 - 패션왕 신해빈
    
    // 옷의 개수 n, 정답 ans
    public static int n, ans;
    // 각 종류별 옷의 개수 배열
    public static int[] cnts;
    // 각 옷의 종류에 해당하는 인덱스 매핑
    public static HashMap<String, Integer> map;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++){
            // 초기 값 1로 설정
            ans = 1;
            // 옷의 총 개수 입력
            n = Integer.parseInt(br.readLine());
            
            // 변수 초기화
            map = new HashMap<>();
            cnts = new int[n];
            
            // 옷의 종류 수
            int t_cnt = 0;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                // 옷의 이름 입력(스킵)
                st.nextToken();
                // 옷의 종류 입력
                String type = st.nextToken();
                // 옷 종류 인덱스 가져오기
                int type_num = map.getOrDefault(type, t_cnt);
                // 현재 인덱스가 기존 인덱스와 같다면 map에 인덱스 추가
                if(t_cnt == type_num) map.put(type, t_cnt++);
                // 현재 인덱스의 옷 개수 증가
                cnts[type_num]++;
            }
            
            for(int i = 0; i < t_cnt; i++){
                // 종류별로 가지수 곱해서 총 경우의 수 계산
                // 안 입는 경우도 있기 때문에 +1
                ans *= (cnts[i]+1);
            }
            
            // 전체 경우에서 아무것도 안입은 경우 -1
            System.out.println(ans - 1);
        }
    }
}