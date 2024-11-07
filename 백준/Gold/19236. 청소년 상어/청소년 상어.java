import java.io.*;
import java.util.*;


public class Main {
    
    // BOJ 19236 - 청소년 상어
	// 최대값 ans, 8방향 이동 배열 d
	public static int ans = 0;
	public static int[][] d = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
	
	// 물고기 이동
	public static void fishMove(int[][] board, int[] dir, boolean[] dead, HashMap<Integer, int[]> pos) {
		// 1번부터 순차적 이동
		for(int i = 0; i < 16; i++) {
			// 만약 이미 먹힌 물고기라면 스킵
			if(dead[i]) continue;
			// 현재 물고기의 좌표 가져오기
			int[] cur = pos.get(i);
			// 시작 방향 저장
			int origin = dir[i];
			// 이동 좌표 계산
			int nr = cur[0] + d[dir[i]][0], nc = cur[1] + d[dir[i]][1];
			// 범위 체크
			boolean in_range = (0 <= nr && nr < 4) && (0 <= nc && nc < 4);
			// 범위를 벗어나거나 상어가 있는 칸이라면
			while(!in_range || board[nr][nc] == -2) {
				// 반시계 45도 회전
				dir[i] += 1;
				dir[i] %= 8;
				// 새로운 좌표 계산
				nr = cur[0] + d[dir[i]][0];
				nc = cur[1] + d[dir[i]][1];
				in_range = (0 <= nr && nr < 4) && (0 <= nc && nc < 4);
				// 회전한 방향이 처음과 같으면
				if(dir[i] == origin) {
					// 이동 안하고 종료
					dir[i] = -1;
					break;
				}
			}
			// 이동 안하고 종료했다면 다음 물고기 탐색
			if(dir[i] == -1) {
				// 방향은 기존 방향으로 다시 설정
				dir[i] = origin;
				continue;
			}
			// 새로 이동하려는 칸의 상태 prev에 저장
			int prev = board[nr][nc];
			// prev가 0보다 크거나 같다면(물고기가 있다면)
			if(prev >= 0) {
				//현재 칸으로 이동 및 좌표 갱신
				board[cur[0]][cur[1]] = prev;
				pos.put(prev, new int[] {cur[0], cur[1]});
			}
			// 빈칸이라면 현재 칸을 빈칸으로 저장
			else board[cur[0]][cur[1]] = -1;
			// 새로운 좌표로 현재 물고기 이동 및 좌표 갱신
			board[nr][nc] = i;
			pos.put(i, new int[] {nr, nc});
		}
	}
	
	// 상어 이동
	public static boolean sharkMove(int r, int c, int[][] board, int[] dir, boolean[] dead, HashMap<Integer,int[]> pos, int sum) {
		
		// 백트래킹을 위해 기존 값 저장
		int[][] n_board = new int[4][4];
		int[] n_dir = new int[16];
		boolean[] n_dead = new boolean[16];
		HashMap<Integer, int[]> n_pos = new HashMap<>();
		
		// 기존 정보 복사
		for(int i = 0; i < 4; i ++) {
			System.arraycopy(board[i], 0, n_board[i], 0, board[i].length);
		}
		System.arraycopy(dir, 0, n_dir, 0, 16);
		System.arraycopy(dead, 0, n_dead, 0, 16);
		for(int key : pos.keySet()) {
			n_pos.put(key, pos.get(key));
		}
		
		// 현재 (r,c)에 있는 잡아 먹힐 물고기 번호
		int eat = n_board[r][c];
		// 현재 위치에 상어 배치
		n_board[r][c] = -2;
		// 상어 방향을 기존 물고기 방향으로 설정
		int shark_dir = dir[eat];
		// 지금까지의 물고기 합에 현재 물고기 추가(인덱스로 저장했으므로 + 1해서 증가)
		sum += (eat+1);
		// 현재 물고기 죽음 표시
		n_dead[eat] = true;
		// 상어 이동 후 물고기 이동 진행
		fishMove(n_board, n_dir, n_dead, n_pos);
		
		// 다음 상어 이동을 위해 현재 상어칸을 빈칸으로 저장
		n_board[r][c] = -1; 
		//새로운 좌표 계산
		int nr = r, nc = c;
		// 물고기를 먹었는지 확인(못먹으면 그상태로 종료)
		boolean isEat = false;
		// 최대 많이 탐색할 수 있는 칸이 해당 방향으로 3칸까지
		for(int dd = 0; dd < 3; dd++) {
			// 새로운 좌표 계산
			nr += d[shark_dir][0];
			nc += d[shark_dir][1];
			//범위 체크
			boolean in_range = (0 <= nr && nr < 4) && (0 <= nc && nc < 4);
			// 범위 안이고, 물고기가 있다면
			if(in_range && n_board[nr][nc] >= 0) {
				// 물고기 먹음 체크
				isEat = true;
				// 현재 상태에서의 다음 물고기 탐색
				sharkMove(nr, nc, n_board, n_dir, n_dead, n_pos, sum);
				// 종료 후에는 다음 칸을 골랐을 때의 경우를 다시 계산
			}
		}
		// 진행방향으로 3칸 앞까지 탐색했을 때 물고기를 먹지 못했다며ㅑㄴ
		if(!isEat) {
			// 현재까지 먹은 물고기 번호의 합과 지금까지의 최댓값을 비교 후 갱신
			ans = Math.max(ans, sum);
		}
		// 탐색 완료 후 종료
		return isEat;
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 보드 및 방향, 생존여부, 물고기 좌표 저장할 변수 선언
        int[][] board = new int[4][4];
        int[] dir = new int[16];
        boolean[] dead = new boolean[16];
        HashMap<Integer, int[]> pos = new HashMap<>();
        
        // 초기 값 입력
        for(int r = 0; r < 4; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < 4; c++) {
        		int idx = Integer.parseInt(st.nextToken()) - 1;
        		dir[idx] = Integer.parseInt(st.nextToken()) - 1;
        		board[r][c] = idx;
        		pos.put(idx, new int[] {r, c});
        	}
        }
        
        // 0,0에서 상어 이동 시작
        sharkMove(0, 0, board, dir, dead, pos, 0);
    
        // 최종 결과 출력
        System.out.println(ans);
    }
}