import java.util.*;
import java.io.*;

public class Main {

	// BOJ 14503 - 로봇 청소기
	public static int N, M, ans = 0;
	public static int[][] board, d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; 
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        
        // 현재 청소기 위치 저장
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int sd = Integer.parseInt(st.nextToken());
        
        // 초기 보드 상태 입력
        for(int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < M; c++) {
        		board[r][c] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 위치를 관리할 dq
        Deque<int[]> dq = new ArrayDeque<>();
        // 첫 위치 입력
        dq.offer(new int[] {sr, sc, sd});
        
        // 첫 위치는 무조건 빈 공간 -> 청소가 안된 상태
        // 청소 한 상태(2)로 갱신 후 ans 증가
        board[sr][sc] = 2;
        ans++;
        
        // dq가 빌 때까지 반복
        while(!dq.isEmpty()) {
        	// 현재 좌표 및 방향
        	int[] cur = dq.poll();
        	int r = cur[0], c = cur[1], dir = cur[2];
        	// 4방향에 청소가 필요한 공간이 있는지 체크
        	boolean clean = false;
        	// 청소할 공간이 있다면 반시계 방향으로 90도 회전 후 탐색을 시작하므로 방향 인덱스를 3부터 0으로 감소
        	for(int dd = 3; dd >= 0; dd--) {
        		// 현재 방향에서 방향 인덱스를 더한 뒤, 나머지 연산으로 반시계 방향으로 돌린 새 방향 계산
        		int nd = (dd + dir) % 4;
        		// 새 좌표 계산
        		int nr = r + d[nd][0], nc = c + d[nd][1];
        		// 범위 체크
        		boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
        		// 새 좌표가 범위 안에 있고, 청소가 필요한 공간이라면
        		if(in_range && board[nr][nc] == 0) {
        			// 진행 방향 갱신
        			dir = nd;
        			// 청소 필요한 공간 있음 체크
        			clean = true;
        			// 반복문 종료 -> 현재 방향으로 진행
        			break;
        		}
        	}
        	
        	// 4방향에서 청소할 공간을 찾지 못했다면
        	if(!clean) {
        		// 현재 방향과 반대 방향으로 이동하기 위해 반대 방향 인덱스 계산
        		int nd = (dir + 2) % 4;
        		// 한 칸 뒤쪽 좌표 계산
        		int nr = r + d[nd][0], nc = c + d[nd][1];
        		// 한 칸 뒤가 벽(1)이 아니라면 현재 방향 유지한 채 뒤쪽으로 이동
        		if(board[nr][nc] != 1) dq.offer(new int[] {nr, nc, dir});
        		// 만약 벽이라면 새로운 좌표를 추가를 안해줌으로써 dq가 비게 되어 반복문 종료
        	}
        	// 4방향에서 청소할 공간을 찾았다면
        	else {
        		// 바꾼 방향으로 새 좌표 계산
        		int nr = r + d[dir][0], nc = c + d[dir][1];
        		// 새 좌표에 청소를 했다고 표시(2)
        		board[nr][nc] = 2;
        		// 청소한 칸 수 증가
        		ans++;
        		// 새로 이동한 좌표 추가
        		dq.offer(new int[] {nr, nc, dir});
        	}
        }
        // 정답 출력
        System.out.println(ans);
    }
}