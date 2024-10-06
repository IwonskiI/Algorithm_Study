import java.io.*;
import java.util.*;

public class Solution {

    // SWEA 5650 - [S/W 문제해결 기본] 7일차 - 미로1
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 10번의 테스트케이스 진행
        for(int tc = 1; tc <= 10; tc++) {
        	// 테스트케이스 번호 입력 (사용 X)
        	Integer.parseInt(br.readLine().trim());
        	// 도착지 도달 가능 여부
        	int ans = 0;
            sb.append("#").append(tc).append(" ");
            // 보드 입력
            int[][] board = new int[16][16];
            boolean[][] visited = new boolean[16][16];
            // 시작점 좌표
            int sr = 0, sc = 0;
            for(int r = 0; r < 16; r++) {
            	String[] str = br.readLine().split("");
            	for(int c = 0; c < 16; c++) {
            		board[r][c] = Integer.parseInt(str[c]);
            		// 시작점 찾기
            		if(board[r][c] == 2) {
            			sr = r;
            			sc = c;
            		}
            	}
            }
            
            // 4방향 탐색
            int[][] d = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
            
            // DFS 이동을 관리할 stack
            Deque<int[]> stack = new ArrayDeque<>();
            stack.offer(new int[] {sr, sc});
            visited[sr][sc] = true;
            
            while(!stack.isEmpty()) {
            	// stack으로 사용하기 위해 pollLast 사용
            	int[] cur = stack.pollLast();
            	int r = cur[0], c = cur[1];
            	// 현재 좌표가 도착지라면
            	if(board[r][c] == 3) {
            		// 가능 여부 표시 후 종료
            		ans = 1;
            		break;
            	}
            	
            	// 4방향 탐색
            	for(int dd = 0; dd < 4; dd++) {
            		int nr = r + d[dd][0], nc = c + d[dd][1];
            		boolean in_range = (0 <= nr && nr < 16) && (0 <= nc && nc < 16);
            		// 범위 안이고, 벽이 아니고, 방문 전이라면
            		if(in_range && board[nr][nc] != 1 && !visited[nr][nc]) {
            			// 다음 탐색을 위해 추가
            			stack.offer(new int[] {nr, nc});
            			// 방문 처리
            			visited[nr][nc] = true;
            		}
            	}
            }
            
            // 도착지점 도달 가능 여부 저장
            sb.append(ans).append("\n");
        
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
        

    }

}