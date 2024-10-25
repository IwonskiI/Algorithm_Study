import java.io.*;
import java.util.*;

public class Main {

    // BOJ 17503 - 맥주 축제
	public static int N, M, K;
	public static PriorityQueue<int[]> like_pq, level_pq;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        // 만족도가 높은 순서대로 정렬되는 PQ 선언 (도수는 낮은 순)
        like_pq = new PriorityQueue<>((a, b) -> {
        	if(a[0] == b[0]) return a[1] - b[1];
        	else return b[0] - a[0];
        });
        
        // 맥주 종류 삽입
        for(int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	like_pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        
        // 도수가 높은 순서대로 정렬되는 PQ(만족도는 낮은순)
        level_pq = new PriorityQueue<>((a, b)->{
        	if(b[1] == a[1]) return a[0] - b[1];
        	else return b[1] - a[1];
        });
        
        // 현재 만족도
        int total_like = 0;
        // 필요한 간 레벨업
        int ans = -1;
        // 가장 만족도가 높은 N개의 맥주 선택
        for(int i = 0; i < N; i++) {
        	int[] cur = like_pq.poll();
        	total_like += cur[0];
        	level_pq.offer(new int[] {cur[0], cur[1]});
        }
        
        // 만족도가 M 미만이라면 -1 출력
        if(total_like < M) {
        	System.out.println("-1");
        	return;
        }
        // 만족도가 M이상이라면 가장 도수가 높은 맥주를 제거하면서 M이상인지 체크
        else {
        	ans = level_pq.peek()[1];
        	while(!like_pq.isEmpty()) {
        		// 도수가 가장 높은 맥주 제거, 만족도가 가장 높은 맥주 추가
        		int[] cur = level_pq.poll(), cur1 = like_pq.poll();
        		// 현재 가장 높은 도수를 답으로 저장
        		ans = cur[1];
        		// 추가한 만족도 가장 높은 맥주가 기존 알코올 최대치보다 높다면 제거 후 새로 추가
        		while(cur1[1] >= ans && !like_pq.isEmpty()) {
        			cur1 = like_pq.poll();
        		}
        		// 모든 맥주를 탐색 후 현재 제거한 맥주의 도수보다 낮은 도수의 맥주가 없다면 종료
        		if(cur1[1] > ans) break;
        		else {
        			// 제거한 맥주의 만족도 감소
        			total_like -= cur[0];
        			// 추가한 맥주의 만족도 증가
            		total_like += cur1[0];
            		// 현재 가장 만족도가 높은 맥주를 추가 했을 때 M보다 작다면 더이상 비교하지않고 종료
            		if(total_like < M) break; 
            		// M 이상이라면
            		else {
            			// 새로운 맥주를 현재 선택한 맥주의 pq에 추가
            			level_pq.offer(cur1);
            			// 정답 갱신(현재 pq에서 가장 도수가 높은 술)
                		ans = level_pq.peek()[1];
            		}
        		}
        	}
        	// 최종 필요한 간 레벨 업그레이드 수치 출력
        	System.out.println(ans);
        	return;
        }
    }
}