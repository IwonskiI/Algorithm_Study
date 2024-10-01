import java.io.*;
import java.util.*;

public class Main {
    
	// BOJ 17298 - 오큰수
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int[] lst = new int[N];
        int[] ans = new int[N];
        Arrays.fill(ans, -1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 초기 입력
        for(int i = 0; i < N; i++) {
        	lst[i] = Integer.parseInt(st.nextToken());
        }
        
        // 숫자를 관리할 stack
        Deque<int[]> dq = new ArrayDeque<>();
        
        // 초기 값 입력
        dq.offer(new int[] {0, lst[0]});
       
        // 두번째 수부터 비교
        for(int i = 1; i < N; i++) {
        	// stack이 빌 때까지 가장 마지막에 들어간 값이 현재 값보다 작으면
        	while(!dq.isEmpty() && dq.peekLast()[1] < lst[i]) {
        		// 그 값을 뽑아내고
        		int[] cur = dq.pollLast();
        		// 그 값이 저장 되어있던 순서에 현재 값을 채움
        		ans[cur[0]] = lst[i];
        	}
        	// stack에 현재 값 추가
        	dq.offer(new int[] {i, lst[i]});
        }
        
        // 저장된 정답 출력을 위해 sb에 저장
        for(int i = 0; i < N; i++) {
        	sb.append(ans[i]).append(" ");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}