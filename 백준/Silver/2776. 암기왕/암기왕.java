import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 2776 - 암기왕
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        // 테스트케이스만큼 반복
        for(int tc = 0; tc < T; tc++) {
        	// 수첩1에 적혀있는 수의 개수
        	int N = Integer.parseInt(br.readLine());
        	// 수첩 1에 적혀있는 수의 배열
        	int[] lst = new int[N];
        	// 수 입력
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) {
        		lst[i] = Integer.parseInt(st.nextToken());
        	}
        	// 이분 탐색을 위한 정렬
        	Arrays.sort(lst);
        	
        	// 수첩 2에 적혀있는 수의 개수
        	int M = Integer.parseInt(br.readLine());
        	// 수 입력
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < M; i++) {
        		// 수첩 2에 적혀있는 수
        		int cur = Integer.parseInt(st.nextToken());
        		
        		// 이진 탐색
        		int start = 0, end = N -1;
        		// 수를 찾았는지 표시할 flag변수
        		boolean found = false;
        		
        		// 이진탐색 시작
        		while(start <= end) {
        			int mid = (start + end)/2;
        			// 중간 값이 찾는 값이라면
        			if(lst[mid] == cur) {
        				// 1 추가 후 종료
        				sb.append("1\n");
        				// 수 찾았음을 표시
        				found = true;
        				break;
        			}
        			
        			// 찾으려는 수가 중간 값보다 작다면
        			else if(cur < lst[mid]) {
        				// 중간 값을 끝점으로 설정하고 재탐색
        				end = mid - 1;
        			}
        			
        			// 찾으려는 수가 중간 값보다 크다면
        			else {
        				// 중간 값을 시작점으로 설정하고 재탐색
        				start = mid + 1;
        			}
        		}
        		
        		// 못 찾았다면 0 추가
        		if(!found) sb.append("0\n");
        	}
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}