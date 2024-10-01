import java.io.*;
import java.util.*;

public class Main {
    
	// BOJ 11054 - 가장 긴 바이토닉 부분 수열
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int[] lst = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	lst[i] = Integer.parseInt(st.nextToken());
        }
        
        // 증가하는 부분 수열 asc, 감소하는 부분 수열 dsc
        int[] asc = new int[N], dsc = new int[N];
        
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
        
        // 가장 긴 감소하는 부분 수열 찾기 (역으로 계산)
        for(int i  = N-1; i >= 0; i--) {
        	int cd = dsc[i];
        	for(int j = i-1; j >= 0; j--) {
        		if(lst[i] < lst[j] && dsc[j] < cd+1) dsc[j] = cd+1;
        	}
        }
        
        // 가장 긴 바이토닉 부분 수열 찾기
        int ans = 0;
        for(int i = 0; i < N; i++) {
        	// 두 수열의 길이의 합에 본인 한개를 더해서 가장 긴 부분 수열 찾기
        	ans = Math.max(ans, asc[i]+dsc[i]+1);
        }
        
        // 최종 정답 출력
        sb.append(ans);
        System.out.println(sb.toString());
    }
}