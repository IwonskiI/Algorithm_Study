import java.io.*;
import java.util.*;

public class Main {
    
	// BOJ 11053 - 가장 긴 증가하는 부분 수열
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int[] lst = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	lst[i] = Integer.parseInt(st.nextToken());
        }
        
        // 증가하는 부분 수열 asc
        int[] asc = new int[N];
        
        // 가장 긴 증가하는 부분 수열 찾기
        for(int i = 0; i < N; i++) {
        	// 현재 가장 긴 수열 길이 ca
        	int ca = asc[i];
        	// 다음 값 비교
        	for(int j = i+1; j < N; j++) {
        		// 현재 숫자가 비교 숫자보다 작아서 증가하는 수열이면서, 지금까지의 가장 긴 수열보다 더 길다면 갱신
        		if(lst[i] < lst[j] && asc[j] < ca+1) asc[j] = ca+1;
        	}
        }
        
        int ans = 0;
        for(int i = 0; i < N; i++) {
        	// 가장 긴 부분 수열 찾기
        	ans = Math.max(ans, asc[i]+1);
        }
        
        // 최종 정답 출력
        sb.append(ans);
        System.out.println(sb.toString());
    }
}