import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 16137 - 견우와 직녀
	public static int N;
	public static int[][] board, d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};;
	
	// 절벽이 교차하는 곳인지 확인
	public static boolean isCross(int r, int c) {
		int cnt=0;
		// 좌상/ 좌하/ 우상/ 우하
		if((r-1>=0 && board[r-1][c]==0) || (r+1<N && board[r+1][c]==0)) cnt++;
		if((c-1>=0 && board[r][c-1]==0) || (c+1<N && board[r][c+1]==0)) cnt++;
		if(cnt==2) return true;
		else return false;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 지역의 크기 N, 오작교의 주기 M
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 지역 정보 입력
        board = new int[N][N];
        boolean[][][] visited = new boolean[N][N][2];
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 이동을 관리할 dq
        Deque<int[]> dq = new ArrayDeque<>();
        // 초기 위치 0,0에 다리를 아직 놓지 않았음을 표시하는 0과, 현재 시간 0, 정지했을 때 이전 이동 방향(초기 -1)을 담아서 삽입
        dq.offer(new int[] {0, 0, 0, 0});
        //방문 처리
        visited[0][0][0] = true;
        // 직녀에게 도달할 시간을 저장할 ans
        int ans = 0;
        // BFS 탐색
        while(!dq.isEmpty()) {
        	// 현재 상태 정보
            int[] cur = dq.poll();
            int r = cur[0], c = cur[1], bridge = cur[2], time = cur[3];
            // 현재 위치가 오작교인지 체크 (두 번 연속으로 오작교를 건널 수 없으므로)
            boolean isbridge = board[r][c] == 1 ? false : true;
            // 현재 위치가 도착 지점이라면 현재 시간을 저장하고 종료
            if(r == N-1 && c == N-1){
                ans = time;
                break;
            }
            
            // 탐색 시작
            for(int dd = 0; dd < 4; dd++){
            	// 새 좌표 계산
                int nr = r + d[dd][0], nc = c + d[dd][1];
                // 범위 체크
                boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
                // 범위 안이라면
                if(in_range && !visited[nr][nc][bridge]){
                	// 새로 이동할 지역이 땅이라면 시간 증가 후 이동
                    if(board[nr][nc] == 1){
                    	visited[nr][nc][bridge] = true;
                    	dq.offer(new int[] {nr, nc, bridge, time+1});
                    }
                    // 이동 지역이 절벽이지만, 아직 다리를 놓지 않았고, 현재 위치가 다리가 아니었으며(연속으로 다리를 건너지 않으며), 절벽이 교차하는 곳이 아니라면, 
                    else if(board[nr][nc] == 0 && bridge == 0 && !isbridge && !isCross(nr, nc)){
                    	// 다음 시간에 다리가 지어진다면,
                    	if((time+1) % M == 0) {
                    		dq.offer(new int[] {nr, nc, 1, time+1});
                    	}
                    	// 다리가 안지어진다면, 다리가 지어질 때까지 대기
                    	else {
                    		dq.offer(new int[] {r, c, bridge, time+1});
                    	}
                    }
                    // 이동 지역이 이미 지어진 오작교이고, 현재 위치가 다리가 아니라면(연속 다리 사용이 아니라면)
                    else if(board[nr][nc] != 0 && !isbridge){
                    	// 이동했을 시간에 다리가 지어졌다면,
                    	if((time+1) % board[nr][nc] == 0) {
                    		// 오작교를 활용해서 이동
                    		dq.offer(new int[] {nr, nc, bridge, time+1});
                    	}
                    	// 다리가 지어지지 않는다면, 다리가 지어질 때까지 대기
                        else {
                        	dq.offer(new int[] {r, c, bridge, time+1});
                        }
                    }                    
                }
            }
        }
        
        // 최종 결과 출력
        System.out.println(ans);
    }
}