import java.util.*;
import java.io.*;
  
public class Solution {
      
    // SWEA 7733 - 치즈 도둑
    static int N, ans;
    static int[][] board;
    static boolean[][] visited;
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
      
    // bfs탐색 - 치즈가 연결된 곳 까지 진행
    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x,y});
        visited[x][y] = true;
          
        // 주변에 치즈가 있는 칸으로 계속해서 확장
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
              
            // 4방향 탐색
            for(int dd = 0; dd < 4; dd++) {
            	int nr = r + d[dd][0];
                int nc = c + d[dd][1];
                boolean in_range = ((0<=nr)&&(nr<N)) && ((0<=nc)&&(nc<N));
                // 범위에서 안이고, 아직 방문 전이고, 치즈가 있다면 연결 
                if(in_range && !visited[nr][nc] && board[nr][nc] > 0) {
                	// 새 좌표 추가
                	q.offer(new int[] {nr, nc});
                	// 방문 처리 해주기
                	visited[nr][nc] = true;
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
        	// 첫 치즈는 전체 한 덩어리(0 입력 없음)
            ans = 1;
            // 보드의 크기
            N = Integer.parseInt(br.readLine());
            // 치즈의 맛 정도 최대값 저장
            int maxDay = 0;
            // 치즈를 저장할 보드판
            board = new int[N][N];
            // 입력 처리
            for (int r = 0; r < N; r++) {
            	st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                	board[r][c] = Integer.parseInt(st.nextToken());
                	// 가장 마지막에 먹힐 치즈 부분 계산위해 최댓값 저장
                	if(board[r][c] > maxDay) maxDay = board[r][c];
                }
            }
            
            // 1일차부터 최대일수까지 반복
            for(int day = 1; day <= maxDay; day++) {
                // 현재 일자의 방문 처리
                visited = new boolean[N][N];
            	// 현재 일수의 조각 수
            	int curPiece = 0;
            	for(int r = 0; r < N; r++) {
            		for(int c = 0; c < N; c++) {
            			// 하루가 지나면 전체 값 감소 
            			// (해당 일수만큼 깎이면 0 이하로 떨어지면서 치즈가 없는 것으로 판단)
            			board[r][c]--;
            		}
            	}
            	for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        // 치즈가 있고, 아직 방문전인 칸이라면
                        if(board[r][c] > 0 && !visited[r][c]) {
                            // bfs 탐색으로 연결된 덩어리 확인
                            bfs(r, c);
                            // 덩어리 수 증가
                            curPiece += 1;
                        }
                    }
                }
            	// 치즈 탐색 후 해당 일자의 치즈 수가 더 크면 값 갱신
            	if(ans < curPiece) ans = curPiece;
            }
              
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
  
}