import java.io.*;
import java.util.*;


public class Main {
    
    // BOJ 16946 - 벽 부수고 이동하기 4
	public static int N, M, space_cnt = 1;
	public static int [][] board, n_board, d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	// 해당 칸에서 접근 가능한 칸 수 세기
	public static void count(int row, int col) {
		// 현재 칸 포함이므로 1부터 시작
		int cnt = 1;
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {row, col});
		n_board[row][col] = space_cnt;
		
		// BFS 탐색
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1];
			// 4방향 탐색
			for(int dd = 0; dd < 4; dd++) {
				int nr = r + d[dd][0], nc = c + d[dd][1];
				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
				// 범위 안이고, 빈칸이면서 아직 방문 전이라면
				if(in_range && board[nr][nc] == 0 && n_board[nr][nc] == 0) {
					// 다음 탐색 추가
					dq.offer(new int[] {nr, nc});
					// 방문 수 증가
					cnt++;
					// 공간 번호 기록(방문 처리)
					n_board[nr][nc] = space_cnt;
				}
			}
		}
		// 현재 공간 번호와 접근 가능한 칸수 저장
		map.put(space_cnt, cnt);
		// 공간 번호 증가
		space_cnt++;
	}
	
	// 벽을 뚫었을 때 연결되는 칸 수 구하기
	public static int breakWall(int row, int col) {
		// 벽 포함이므로 1부터 시작
		int cnt = 1;
		// 중복된 공간 제거를 위한 set
		HashSet<Integer> set = new HashSet<Integer>();
		
		// 4방향 체크
		for(int dd = 0; dd < 4; dd++) {
			int nr = row + d[dd][0], nc = col + d[dd][1];
			boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
			// 범위 밖이거나 벽이라면 스킵
			if(!in_range || board[nr][nc] == 1) continue;
			// 아니라면 현재 공간 번호 set에 추가
			else set.add(n_board[nr][nc]);
		}
		
		// 연결된 공간의 칸 수만큼 cnt 증가
		for(int s_num : set) {
			cnt += map.get(s_num);
		}
		
		// 총 연결된 칸 수 반환
		return cnt;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        board = new int[N][M];
        n_board = new int[N][M];
        
        
        // board 입력
        for(int r = 0; r < N; r++) {
        	String[] line = br.readLine().split("");
        	for(int c = 0; c < M; c++) {
        		board[r][c] = Integer.parseInt(line[c]);
        		if(board[r][c] == 1) n_board[r][c] = -1;
        		else n_board[r][c] = 0;
        	}
        }
        
        // 벽을 뚫기 전 공간들의 넓이 미리 계산
        for(int r = 0; r < N; r++) {
        	for(int c = 0; c < M; c++) {
        		if(n_board[r][c] == 0) {
        			count(r, c);
        		}
        	}
        }
        
        // board 탐색
        for(int r = 0; r < N; r++) {
        	for(int c = 0; c < M; c++) {
        		// 빈 공간이라면 0 추가
        		if(board[r][c] == 0)
        			sb.append(0);
        		// 벽이라면 부수고 이동할 수 있는 전체 공간 계산
        		else
        			sb.append(breakWall(r, c) % 10);
        	}
        	sb.append("\n");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}