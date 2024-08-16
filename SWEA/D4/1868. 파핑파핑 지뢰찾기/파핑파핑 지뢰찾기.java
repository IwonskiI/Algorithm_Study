import java.util.*;
import java.io.*;
 
public class Solution {
     
	// SWEA 1868 - 파핑파핑 지뢰찾기
    static int N, ans;
    static int[][] board;
    static boolean[][] visited;
    static boolean[][] near_mine;
    static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
     
    // 지뢰 찾기 함수
    public static void find_mine() {
        for (int c = 0; c < N; c++) {
            for (int r = 0; r < N; r++) {
            	// 지뢰가 있는지 여부를 확인할 변수
                boolean flag = false;
                // 해당 칸이 지뢰라면
                if (board[c][r] == 1) {
                	// 지뢰 여부 O
                    flag = true;
                    // 지뢰는 클릭하면 안되므로 미리 방문 처리
                    visited[c][r] = true;
                }
                // 지뢰가 아니라면 주변에 지뢰가 있는지 확인
                else {
                	// 8방향 탐색
                    for (int dd = 0; dd < 8; dd++) {
                        int nc = c + dc[dd];
                        int nr = r + dr[dd];
                        boolean in_range = ((0<=nc)&&(nc<N)) && ((0<=nr)&&(nr<N));
                        // 새 좌표가 보드판 안이고, 지뢰라면
                        if(in_range && board[nc][nr] == 1) {
                        	// 지뢰 여부 O
                            flag = true;
                            break;
                        }
                    }
                }
                // flag변수를 저장
                near_mine[c][r] = flag;
            }
        }
    }
     
    // 지뢰가 없는 주변 칸으로 확장
    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x,y});
        visited[x][y] = true;
         
        // 주변에 지뢰가 없는 칸으로 계속해서 확장
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int c = cur[0];
            int r = cur[1];
             
            // 8방향 탐색
            for(int dd = 0; dd < 8; dd++) {
                int nc = c + dc[dd];
                int nr = r + dr[dd];
                boolean in_range = ((0<=nc)&&(nc<N)) && ((0<=nr)&&(nr<N));
                // 범위에서 벗어나거나, 이미 방문했다면 스킵 
                if(!in_range || visited[nc][nr]) continue;
                // 방문 전이고, 근처에 지뢰가 없다면 해당칸도 추가 탐색을 위해 q에 추가
                if(!visited[nc][nr] && !near_mine[nc][nr]) q.offer(new int[] {nc, nr});
                // 방문 처리 해주기
                visited[nc][nr] = true;
            }
                 
        }
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            ans = 0;
            N = Integer.parseInt(br.readLine());
            // 지뢰를 저장할 보드판
            board = new int[N][N];
            // 근처에 지뢰가 있는지 여부를 저장할 보드
            near_mine = new boolean[N][N];
            // 방문 처리
            visited = new boolean[N][N];
            // 입력 처리 - 지뢰면 1, 아니면 0
            for (int c = 0; c < N; c++) {
                String[] temp = br.readLine().split("");
                for (int r = 0; r < N; r++) {
                    if(temp[r].equals("*")) board[c][r] = 1;
                    else board[c][r] = 0;
                }
            }
            // 근처에 지뢰 여부 확인 - near_mine 배열
            find_mine();
            
            for (int c = 0; c < N; c++) {
                for (int r = 0; r < N; r++) {
                	// 근처에 지뢰가 없고, 해당칸도 지뢰가 아니고, 아직 방문전인 칸이라면
                    if(!near_mine[c][r] && board[c][r] == 0 && !visited[c][r]) {
                    	// bfs 탐색으로 주변의 연쇄작용 확인
                        bfs(c, r);
                        // 클릭수 한번 증가
                        ans += 1;
                    }
                }
            }
            // 지뢰가 아닌 칸중에서 아직 방문 전인 칸들 클릭해주기 -> 주변에 지뢰가 있어서 연쇄작용이 일어나지 않는 칸들, 한칸씩 직접 클릭해줘야함.
            for (int c = 0; c < N; c++) {
                for (int r = 0; r < N; r++) {
                    if(!visited[c][r] && board[c][r] == 0) {
                        ans += 1;
                    }
                }
            }
             
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
 
}