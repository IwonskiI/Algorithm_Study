import java.io.*;
import java.util.*;

public class Main {

    // BOJ 20520 - Оборона крепости
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        
        int ans = 0;
        // 한 명을 배치했을 때 가장 많은 사람을 없앨 수 있는 순서대로 정렬되는 PQ
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> {
        	int asum, bsum;
        	if(a[0] >= a[1]) asum = a[1];
        	else asum = a[0];
        	if(b[0] >= b[1]) bsum = b[1];
        	else bsum = b[0];
        	return bsum - asum;
        });
        // 공격자의 수와 공격가능한 사람의 수를 입력
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int attack = Integer.parseInt(st.nextToken());
        	int defend = Integer.parseInt(st.nextToken());
        	// 총 공격 인원수 증가
        	ans += attack;
        	// pq 에 추가
        	pq.offer(new int[] {attack, defend});
        }
        
        // 모든 사람을 배치할 때까지 진행
        for(int i = 0; i < S; i++) {
        	// 만약 이미 모든 공격자를 처리했으면 종료
        	if(ans == 0) break;
        	// 현재 가장 많이 제거할 수 있는 구역 출력
        	int[] cur = pq.poll();
        	// 이번 사람이 제거하는 인원을 제거한 후 남은 사람 저장
        	int left = cur[1] > cur[0] ? 0 : cur[0] - cur[1];
        	// 이번 턴에 제거될 사람의 수 계산
        	int remove;
        	if(cur[0] >= cur[1]) remove = cur[1];
        	else remove = cur[0];
        	// 전체 공격자 수 중 이번 턴에 제거 되는 사람 제거
        	ans -= remove;
        	// 제거되고 남은 구역의 공격자를 pq에 추가
        	pq.offer(new int[] {left, cur[1]});
        }
        
        
        // 남은 공격자 수 출력
        System.out.println(ans);        
    }
}