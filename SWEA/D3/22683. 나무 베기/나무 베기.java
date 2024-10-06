import java.io.*;
import java.util.*;

public class Solution {

    // SWEA 22683 - 나무 베기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int Test = Integer.parseInt(br.readLine().trim());
        for(int tc = 1; tc <= Test; tc++) {
        	st = new StringTokenizer(br.readLine());
        	// 보드의 크기
        	int N = Integer.parseInt(st.nextToken());
        	// 나무를 밸 수 있는 횟수
        	int T = Integer.parseInt(st.nextToken());
        	
        	// 도착지에 도달하는데 걸리는 시간
        	int ans = -1;
            sb.append("#").append(tc).append(" ");
            // 보드 입력
            char[][] board = new char[N][N];
            // 방문배열 [상, 우, 하, 좌]
            boolean[][][] visited = new boolean[N][N][4];
            // 시작점 좌표
            int sr = 0, sc = 0;
            for(int r = 0; r < N; r++) {
            	String[] str = br.readLine().split("");
            	for(int c = 0; c < N; c++) {
            		board[r][c] = str[c].charAt(0);
            		// 시작점 찾기
            		if(board[r][c] == 'X') {
            			sr = r;
            			sc = c;
            		}
            	}
            }
            
            // 전진, 좌회전, 우회전
            int[] dl = {0, -1, 1};
            // 4방향 전진
            int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            
            // BFS 이동을 관리할 queue
            Deque<int[]> dq = new ArrayDeque<>();
            dq.offer(new int[] {sr, sc, 0, 0, T});
            visited[sr][sc][0] = true;
            
            while(!dq.isEmpty()) {
            	int[] cur = dq.poll();
            	int r = cur[0], c = cur[1], time = cur[2], dir = cur[3], tree = cur[4];
            	// 현재 좌표가 도착지라면
            	if(board[r][c] == 'Y') {
            		// 가능 여부 표시 후 종료
            		ans = time;
            		break;
            	}
            	
            	// 3가지 경우 계산
            	for(int dd = 0; dd < 3; dd++) {
            		int nd = (dir + 4 + dl[dd]) % 4;
            		int nr = r, nc = c;
            		if(dd == 0) {
            			nr += d[nd][0];
            			nc += d[nd][1];
            		}
            		boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
            		// 범위 안이라면,
            		if(in_range) {
            			// 이동하려는 칸이 나무가 아니고, 해당 방향으로 방문한 적이 없다면
            			if(board[nr][nc] != 'T' && !visited[nr][nc][nd]) {
            				// 다음 탐색을 위해 추가
            				dq.offer(new int[] {nr, nc, time+1, nd, tree});
            				// 방문 처리
            				visited[nr][nc][nd] = true;
            			}
            			// 이동하려는 칸이 나무이고, 해당 방향으로 방문한 적이 없다면
            			else if(board[nr][nc] == 'T' && !visited[nr][nc][nd]) {
            				// 이미 자른 뒤에 회전하는 중이라면
            				if(dd != 0) {
            					// 회전 방문처리
            					dq.offer(new int[] {nr, nc, time+1, nd, tree});
            					visited[nr][nc][nd] = true;
            				}
            				// 아직 자를 수 있는 나무의 수가 남았다면
            				else if(tree > 0) {
            					// 나무를 자르고 해당 방향으로 전진
            					dq.offer(new int[] {nr, nc, time+1, nd, tree-1});
            					// 방문처리
            					visited[nr][nc][nd] = true;
            				}
            			}
            		}
            	}
            }
            
            // 도착지점 도달까지 걸린 조작 횟수 저장 (실패하면 -1)
            sb.append(ans).append("\n");
        
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
        

    }

}