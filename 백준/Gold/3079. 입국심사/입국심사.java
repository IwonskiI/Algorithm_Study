import java.io.*;
import java.util.*;

public class Main {

    // BOJ 3079 - 입국심사
	public static int N, M, ans;
	public static long[] lst;
	
	// 주어진 제한 시간 안에 처리 가능한 최대 인원 수
	public static long count(long lim) {
		long cnt = 0;
		
		// 각 창구에서 제한 시간안에 처리 가능한 인원수 계산
		for(int i = 0; i < N; i++) {
			cnt += lim / lst[i];
			// 오버플로우 방지
			if(cnt >= M) break;
		}
		
		// 총 인원수 반환
		return cnt;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lst = new long[N];
        long max = -1;
        
        // 각 창구가 한 사람을 처리하는데 걸리는 시간 입력 및 한 창구에서 최대로 걸리는 시간 계산
        for(int i = 0; i < N; i++) {
        	lst[i] = Integer.parseInt(br.readLine());
        	max = Math.max(max, lst[i]);
        }
        
        // 이분 탐색
        // 끝 지점은 최대로 걸리는 창구에서 모든 사람을 처리했을 때 걸리는 시간으로 설정
        long start = 1, end = max * M, mid = -1;
        
        while(start <= end) {
        	mid = (start + end) / 2;
        	// 시작 지점과 끝지점이 같아지면 종료
        	if(start == end) break;
        	
        	// 현재 시간으로 M명을 처리하지 못한다면
        	if(count(mid) < M)
        		// 시작 지점을 현재 지점부터 해서 더 많은 시간 제공
        		start = mid + 1;
        	// 아니라면
        	else
        		// 끝지점을 현재 지점으로 설정
        		end = mid;
        }

        // 정답 출력
        System.out.println(mid);        
    }
}