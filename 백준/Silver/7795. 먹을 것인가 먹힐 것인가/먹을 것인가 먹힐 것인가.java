import java.io.*;
import java.util.*;

public class Main {
    
    //BOJ 7795 - 먹을 것인가 먹힐 것인가
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        // T번 반복
        for(int tc = 0; tc < T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	
        	int[] a_lst = new int[A], b_lst = new int[B];
        	
        	// A 크기 입력
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < A; i++) {
        		a_lst[i] = Integer.parseInt(st.nextToken());
        	}
        	// B 크기 입력
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < B; i++) {
        		b_lst[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	// 두 리스트 모두 오름차순 정렬
        	Arrays.sort(a_lst);
        	Arrays.sort(b_lst);
        	
        	// 먹을 수 있는 수 저장
        	int ans = 0;
        	// A 인덱스, B 인덱스
        	int a_idx = 0, b_idx = 0;
        	// 모든 A물고기 탐색
        	while(a_idx < A) {
        		// B 리스트 끝까지 돌았다면 더이상 탐색 X
        		if(b_idx < B) {
        			// 아니라면 B 생명체가 A 생명체보다 커지는 순간까지 인덱스 증가 
        			while(a_lst[a_idx] > b_lst[b_idx]){
            			b_idx++;
            			// 끝까지 탐색했다면 종료
            			if(b_idx == B) break;
            		}
        		}
        		// A 생명체가 먹을 수 있는 수 저장
        		ans += b_idx;
        		// 다음 A 생명체 탐색
        		a_idx++;
        	}
        	// 반복문 종료 후 총 경우의 수 저장
        	sb.append(ans).append("\n");
        }
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}