import java.io.*;
import java.util.*;

public class Main {

    // BOJ 14729 - 빗물
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 높이 H, 가로 W
        int H = Integer.parseInt(st.nextToken()), W = Integer.parseInt(st.nextToken());
        // 쌓인 빗물의 양
        int ans = 0;
        
        // 블록의 높이 저장
        int[] lst = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++) {
        	lst[i] = Integer.parseInt(st.nextToken());
        }
        
        // 각 층별로 쌓여있는 물 양 계산
        for(int h = 1; h <= H; h++) {
        	// 현재 층에서 앞쪽이 막혀있는지 확인
        	boolean prev = false;
        	// 현재 쌓이는 중인 빗물의 양
        	int cnt = 0;
        	// 각 칸 확인
        	for(int w = 0; w < W; w++) {
        		// 앞쪽이 막혀있었다면,
        		if(prev) {
        			// 현재 칸이 빗물 높이보다 낮아서 빗물이 찰 수 있다면 빗물 양 증가
        			if(lst[w] < h) cnt++;
        			// 아니라면 지금까지 모인 빗물을 총 모인 빗물에 추가 후 현재 빗물 초기화
        			else {
        				ans += cnt;
        				cnt = 0;
        			}        			
        		}
        		// 블록의 높이가 현재 탐색중인 높이보다 높거나 같다면 앞쪽 블록 막힌 것으로 표시
        		if(lst[w] >= h) prev = true;
        	}
        }
        
        
        // 탐색 종료 후 정답 출력
        System.out.println(ans);
        
    }

}