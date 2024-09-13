import java.util.*;
import java.io.*;
    
public class Main {
        
    // BOJ 1806 - 부분합
	public static int N, S;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        // 초기 입력
        int[] lst = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	lst[i] = Integer.parseInt(st.nextToken());
        }
        
        // 시작 위치 , 끝 위차
        int sp = 0, ep = 0;
        // 수열 합, 최소 길이
        int sum = lst[0], min_len = N+2;
        
        // 끝 지점이 N보다 작을 때까지
        while(ep < N) {
        	// 현재 상태에서 합이 S 이상이고, 현재 수열의 길이가 min_len보다 작다면 갱신
        	if(sum >= S && ep - sp + 1 < min_len) {
        		min_len = ep - sp + 1;
        		if(min_len == 1) break;
        	}
        	// 끝 지점이 N번째 숫자까지 확인했을 때, 
        	if(ep == N-1) {
        		// 이미 합이 S보다 작다면 종료
        		if(sum < S) break;
        		// 아니라면 시작 지점을 뒤로 밀며 최소 길이 계산
        		else {
        			sum -= lst[sp];
        			sp++;
        		}
        	}
        	// 아직 끝까지 도달하기 전이면서 합이 S보다 작다면,
        	else if(sum < S) {
        		// 끝 지점 뒤로 밀고
        		ep++;
        		// 그 지점 인덱스만큼 총합 더하기
        		sum += lst[ep];
        	}
        	// S보다 크거나 같다면,
        	else {
        		// 시작지점 위치의 값을 합에서 빼고,
        		sum -= lst[sp];
        		// 시작 지점을 뒤로 미루기
        		sp++;
        	}
        }
        
        // min_len이 N+2(초기값)라면, 정답이 없으므로 최소길이는 0으로 출력
        if(min_len == N + 2) min_len = 0;
        
        // 결과 출력
        System.out.println(min_len);
    }
    
}