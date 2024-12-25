import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 1300 - K번째 수
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        
        // 이분 탐색의 범위
        long s = 1, e = k;
        
        // 이분 탐색
        while(s < e) {
        	// 중간 값 계산
        	long mid = (s + e) / 2;
        	// mid보다 작은 수의 개수(k)
        	long cnt = 0;
        	
        	// N번째 열까지 mid보다 작거나 같은 수의 개수를 계산
        	for(int i = 1; i <= N; i++) {
        		// 각 열에서 N개를 초과한 값은 없으므로 최대는 N
        		cnt += Math.min(mid / i, N);
        	}
        	
        	// cnt가 k보다 크거나 같으면 끝점을 mid로 설정
        	if(k <= cnt) e = mid;
        	// 아니라면 시작점을 mid+1로 설정
        	else s = mid + 1;
        }
        
        // s(B[k]) 출력
        System.out.println(s);
        
    }
}