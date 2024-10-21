import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 2512 - 예산
    public static int N, X, ans;
    public static int[] lst;
    
    // 현재 최대치로 편성되는 예산의 합
    public static int total(int lim) {
        int sum = 0;
        // 요구한 예산보다 lim이 더 크다면 lim을, 아니라면 요구한 예산을 더하기
        for(int i = 0; i < N; i++) {
            sum += Math.min(lst[i], lim);
        }
        // 총합 반환
        return sum;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        lst = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 요구되는 예산을 입력받으며 최댓값 같이 찾기
        int max = -1;
        for(int i = 0; i < N; i++) {
            lst[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, lst[i]);
        }
        
        // 전체 예산
        X = Integer.parseInt(br.readLine());
        
        // 이분 탐색
        int start = 0, end = max;
        int mid = 0;
        ans = -1;
        // 탐색 범위가 남아있다면 계속 진행
        while(start <= end){
        	// 중간 값
            mid = (start + end) / 2;
            // 현재 값으로 계산했을 때, 전체 예산보다 적게 나온다면 더 많은 예산 배분을 위해 시작점을 현재 중간값 + 1로 설정
            if(total(mid) < X) start = mid + 1;
            // 현재 값으로 계산했을 때, 전체 예산보다 많이 나온다면 더 낮은 한계 설정을 위해 끝점을 현재 중간값 -1로 설정
            else if(total(mid) > X) end = mid - 1;
            // 현재 값으로 계산했을 때, 전체 예산과 일치한다면 현재 중간 값이 최대치이므로 탐색 종료
            else break;
        }
        
        // 현재 중간 값이 X보다 작다면(조건을 만족한다면) 현재 값이 답
        if(total(mid) <= X) ans = mid;
        // 만약 초과한다면 1 뺀 값이 답
        else ans = mid-1;
        
        // 답 저장
        sb.append(ans);
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}