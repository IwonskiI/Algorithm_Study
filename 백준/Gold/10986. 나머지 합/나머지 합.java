import java.io.*;
import java.util.*;

public class Main {
    
	// BOJ 10986 - 나머지 합
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 나눠떨어지는 구간의 개수
        long ans = 0;
        
        // 수열의 길이 N, 나눌 수 M
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int sum = 0;
        
        // cnt 배열에는 지금까지 나머지가 idx인 경우가 몇번 나왔는지 저장
        int[] cnt = new int[M];
        
        st = new StringTokenizer(br.readLine());        
        for(int i = 0; i < N; i++) {
        	// 합을 계속 더하다보면 Integer 범위를 넘어감.
        	// (A+B) % M == ((A%M) + (B%M)) % M 이므로 미리 모듈러 연산을 해줌.
        	int cur = Integer.parseInt(st.nextToken());
        	// 누적합 계산
        	sum += cur;
        	// 모듈러 연산
        	sum %= M;
        	// 지금이랑 같은 나머지가 나왔던 수만큼 더해줌.
        	ans += cnt[sum];
        	// 현재 나머지 경우의 수 증가
        	cnt[sum]++;
        	// 현재 나머지가 0이면(나눠 떨어지면) ans 증가
        	if(sum == 0) ans++;
        }
        
        
        // 최종 결과 출력
        System.out.println(ans);
    }

}