import java.io.*;
import java.util.*;

public class Main {
    
    //BOJ 2110 - 공유기 설치
	public static int N, M;
	public static int[] dist;
	
	// 현재 거리에서 공유기를 설치 가능한 집 계산
	public static int calc(long lim) {
		// 현재 공유기 사이의 거리
		int sum = 0;
		// 설치된 공유기 수 (첫번째 집에 기본 1개 설치)
		int cnt = 1;
		// 거리 계산
		for(int i = 0; i < N-1; i++) {
			// 다음 집까지의 거리 증가
			sum += dist[i];
			// 거리가 lim이내라면
			if(sum > lim) {
				// 공유기 설치
				cnt++;
				// 공유기 사이 거리 초기화
				sum = 0;
			}
		}
		// 총 설치된 공유기 수 반환
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	int[] lst = new int[N];
    	
    	// 집 위치 입력
    	for(int i = 0; i < N; i++) {
    		lst[i] = Integer.parseInt(br.readLine());
    	}
    	
    	// 오름차순 정렬
    	Arrays.sort(lst);
    	
    	// 집과 집 사이의 거리 계산
    	dist = new int[N-1];
    	// 첫 집과 끝 집 사이의 거리
    	long total = 0;
    	// 거리 계산
    	for(int i = 0; i < N-1; i++) {
    		// 현재 집과 다음집 사이의 거리 저장
    		dist[i] = lst[i+1] - lst[i];
    		// 전체 거리 증가
    		total += dist[i];
    	}
    	
    	// 거리의 최솟값 1부터 최댓값 끝과 끝지점
    	long start = 1, end = total, mid;
    	
    	// 이분 탐색 시작
    	while(start <= end) {
    		// 중간 값 계산
    		mid = (start + end) / 2;
    		
    		// 현재 중간값을 최대 거리로 설정했을 때 설치할 수 있는 공유기 수 계산
    		// M개보다 적게 설치 할 수 있다면 최대 거리를 더 줄여서 설치할 수 있는 공유기 수 늘리기 
    		if(calc(mid) < M) {
    			end = mid - 1;
    		}
    		// M개랑 같거나 더 적게 설치할 수 있다면 최대거리를 늘려서 공유기 수 늘리기
    		else {
    			start = mid + 1;
    		}
    	}
    	// 최대 거리를 저장
    	sb.append(start).append("\n");
    	
    	
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}