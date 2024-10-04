import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 3020 - 개똥벌레
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()), H = Integer.parseInt(st.nextToken());
        
        // 석순과 종유석의 높이에 따른 개수 저장
        int[] bottom = new int[H+1], top = new int[H+1];
        
        // 홀수 번 째에는 석순, 짝수 번 째에는 종유석임
        // 각각의 높이에 따른 개수 증가
        for(int i = 1; i <= N; i++) {
        	int cur = Integer.parseInt(br.readLine());
        	if(i % 2 == 1) bottom[cur]++;
        	else top[cur]++;
        }
        
        // 누적합 구하기
        for(int i = H-2; i > 0; i--) {
        	top[i] += top[i+1];
        	bottom[i] += bottom[i+1];
        }
        
        // 최소 높이 및 개수 구하기
        int min = Integer.MAX_VALUE, ans = 0;
        
        // 각 구간별 깨야하는 블록수 계산 및 최소값 비교
        for(int i = 1; i <= H; i++) {
        	// 현재 구간에서 부수는 장애물 수
        	int cur = bottom[i] + top[H-i+1];
        	// 최소값보다 작다면 갱신
        	if(min > cur) {
        		min = cur;
        		ans = 1;
        	}
        	// 최소값과 같다면 개수 증가
        	else if(min == cur) ans++;
        }
        
        // 결과 저장
        sb.append(min).append(" ").append(ans);
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}