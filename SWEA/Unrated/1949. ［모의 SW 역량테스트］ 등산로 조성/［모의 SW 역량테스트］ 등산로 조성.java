import java.io.*;
import java.util.*;
 
// SWEA 1949 - [모의 SW 역량테스트] 등산로 조성

// 좌표를 관리할 클래스
class Pos {
	int r, c;
	public Pos(int x, int y) {
		this.r = x; this.c = y;
	}
}

public class Solution {
	
	// 보드의 크기 N, 깎을 수 있는 깊이 K, 최대 등산로 길이 ans 
	public static int N, K, ans;
	// 봉우리의 높이가 저장된 board, 4방향 탐색을 위한 Delta배열 d
	public static int[][] board, d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	// 방문 배열 visited
	public static boolean[][] visited;
	
	// 각 코스를 끝까지 탐색할 dfs 함수
	/**
	 * @param p : 현재 위치의 좌표
	 * @param dist : 현재까지 이동한 등산로의 거리
	 * @param canCut : 봉우리를 깎을 수 있는지 (아직 안깎았으면 true / 이미 깎았으면 false)
	 */
	public static void dfs(Pos p, int dist, boolean canCut) {
		// 현재 칸에 도달했으니 거리 증가
		dist++;
		// 현재까지 거리가 저장된 최대 거리보다 크다면 최대거리 갱신
		if(ans < dist) ans = dist;
		// 현재 높이 cur 저장
		int cur = board[p.r][p.c];
		// 4방향 탐색
		for(int dd = 0; dd < 4; dd++) {
			// 이동 할 좌표
			int nr = p.r + d[dd][0], nc = p.c + d[dd][1];
			// 범위 체크
			boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
			// 범위 안이고, 아직 방문 전이라면
			if(in_range && !visited[nr][nc]) {
				// 이동하려는 위치의 봉우리가 현재 봉우리보다 낮다면
				if(board[nr][nc] < cur) {
					// 해당 봉우리로 이동 : 방문처리
					visited[nr][nc] = true;
					// 다음 좌표 탐색을 위해 dfs 함수 호출
					dfs(new Pos(nr, nc), dist, canCut);
					// 탐색 완료 후 방문 처리 해제
					visited[nr][nc] = false;
				}
				// 아직 봉우리를 깎아내지 않았고(깎아낼 수 있고)
				// 다음 이동 칸의 봉우리를 K만큼 깎아냈을 때 현재 봉우리보다 낮다면
				else if(canCut && board[nr][nc] - K < cur) {
					// 이동하려는 봉우리의 원래 높이 저장
					int temp = board[nr][nc];
					// 봉우리를 현재보다 1 낮게 설정
					// (최대 K만큼 깎을 수 있고, K만큼 깎았을 때 이미 현재 봉우리보다 낮아졌기 때문에
					//  1~K 사이의 어느 숫자로 봉우리를 깎으면 높이가 cur-1이 되게 깎을 수 있음)
					// -> 최대한 많은 칸을 탐색해야하므로 이동 할 수 있는 높이중 가장 높게 설정해야함
					board[nr][nc] = cur - 1;
					// 깎은 뒤 해당 봉우리로 이동 : 방문 처리
					visited[nr][nc] = true;
					// 다음 좌표 탐색을 위해 dfs 함수를 호출 -> 봉우리를 깎았으므로 canCut은 false로 전달
					dfs(new Pos(nr, nc), dist, false);
					// 탐색 완료 후 봉우리를 안 깎은 상태로 원상복구
					board[nr][nc] = temp;
					// 탐색 완료 후 방문 처리 해제
					visited[nr][nc] = false;
				}
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            // 봉우리와 방문 배열 초기화
            board = new int[N][N];
            visited = new boolean[N][N];
            // 각 테스트 케이스마다 최대 등산로 길이를 초기화
            ans = -1;
            // 가장 높은 봉우리의 높이를 저장할 변수 max
            int max = -1;
            // 초기 입력
            for(int r = 0; r < N; r++) {
            	st = new StringTokenizer(br.readLine());
            	for(int c = 0; c < N; c++) {
            		board[r][c] = Integer.parseInt(st.nextToken());
            		// 현재 높이가 최대 높이라면 저장
            		max = board[r][c] > max ? board[r][c] : max;
            	}
            }
            
            // 봉우리 탐색
            for(int r = 0; r < N; r++) {
            	for(int c = 0; c < N; c++) {
            		// 현재 위치의 높이가 가장 높은 곳이 아니라면 스킵
            		if(board[r][c] != max) continue;
            		// 가장 높은 곳이라면 탐색 시작 : 방문 처리
            		visited[r][c] = true;
            		// 현재 위치부터 dfs로 등산로 탐색 시작 (초기 거리는 0, canCut은 true)
            		dfs(new Pos(r, c), 0, true);
            		// 탐색 완료 후 방문 처리 해제
            		visited[r][c] = false;
            	}
            }
            // 모든 봉우리 탐색 후 가장 긴 길이를 저장
            sb.append(ans).append("\n");
            
        }
        // 전체 결과 출력
        System.out.println(sb.toString());
    }
}