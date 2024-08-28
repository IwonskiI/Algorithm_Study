import java.util.*;
import java.io.*;
  
public class Solution {
      
    // SWEA 1238 - [S/W 문제해결 기본] 10일차 - Contact [인접 행렬]
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc = 1; tc <= 10; tc++) {
        	// 마지막 전달 받은 번호
        	int ans = 0;
        	// 전달 받았을 때 걸린 시간
        	int prev_lv = -1;
        	// 인접 행렬 - 연결 관리
        	boolean[][] board = new boolean[100][100];
        	// 방문 관리
        	boolean[] visited = new boolean[100];
        	st = new StringTokenizer(br.readLine());
        	// 입력의 개수
        	int N = Integer.parseInt(st.nextToken());
        	// 시작점
        	int start = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N/2; i++) {
        		// 배열의 인덱스를 0번부터 사용하기 위해 -1 처리
        		int from = Integer.parseInt(st.nextToken()) - 1;
        		int to  = Integer.parseInt(st.nextToken()) - 1;
        		// 간선 연결
        		board[from][to] = true;
        	}
        	// bfs탐색
        	Deque<int[]> dq = new ArrayDeque<>();
        	// 시작점 -1 번부터 시작, 첫 시간은 0
        	dq.offer(new int[] {start - 1, 0});
        	// 방문 처리
        	visited[start-1] = true;
        	while(!dq.isEmpty()) {
        		int[] cur = dq.poll();
        		int sp = cur[0], lv = cur[1];
        		// 현재 시간이 더 오래걸렸다면
        		if(lv > prev_lv) {
        			// 마지막 방문 노드 번호 입력 (-1로 입력받았으므로 + 1 처리)
        			ans = sp + 1;
        			// 최종 시간 갱신
        			prev_lv = lv;
        		}
        		// 시간은 같지만, 번호가 더 크다면
        		else if(lv == prev_lv && ans < sp + 1) {
        			// 번호만 갱신
        			ans = sp + 1;
        		}
        		// 간선 연결 확인
        		for(int i = 0; i < 100; i++) {
        			// 간선이 연결되어있고, 방문 전이라면
        			if(board[sp][i] && !visited[i]) {
        				// 다음 방문 추가
        				dq.offer(new int[] {i, lv+1});
        				// 방문 처리
        				visited[i] = true;
        			}
        		}
        	}
        	// 최종 정답 저장
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        // 결과 출력
        System.out.println(sb.toString());
    }
  
}