import java.io.*;
import java.util.*;

public class Main {

    // BOJ 2343 - 기타 레슨
	public static int N, M, ans;
	public static int[] lst;
	
	// 현재 길이로 설정했을 때 만들어지는 블루레이 수
	public static int count(int len) {
		// 블루레이 수 cnt, 한 블루레이의 길이 sum
		int cnt = 1, sum = 0;
		for(int i = 0; i < N; i++) {
			sum += lst[i];
			// 블루레이 길이가 초과하면
			if(sum > len) {
				// 블루레이 개수 증가
				cnt++;
				// 새로운 블루레이에 길이 저장
				sum = lst[i];
			}
		}
		return cnt;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        lst = new int[N];
        int sum = 0, max = -1;
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	lst[i] = Integer.parseInt(st.nextToken());
        	sum += lst[i];
        	max = Math.max(max, lst[i]);
        }
        
        // 이분 탐색
        // 시작은 한개에 최대가 들어갈 때를 대비해서 max
        // 끝은 모든 영상이 1개에 들어갈 때를 대비해서 sum
        int start = max, end = sum, mid = -1;
        
        while(start <= end) {
        	mid = (start + end) / 2;
        	if(start == end) break;
        	
        	// 현재 중간 값을 길이로 설정했을 때, M개보다 적거나 같은 개수가 나온다면
        	if(count(mid) <= M)
        		// 길이를 더 짧게해서 M개까지 맞춰도 됨
        		// M개 중에서도 최소 크기를 구해야하므로 같을 때도 감소(M개를 만드는 더 작은 값이 있을 수도 있기 때문)
        		end = mid;
        	// M개보다 많다면
        	else
        		// 길이를 더 늘려서 M개 안으로 만들 수 있게 조정
        		start = mid+1;
        }

        // 정답 출력
        System.out.println(mid);        
    }
}