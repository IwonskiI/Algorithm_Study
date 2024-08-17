import java.util.*;
import java.io.*;

public class Main {

    // BOJ 16236 - 아기 상어 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        // 물고기 상태를 알려줄 보드
        int[][] board = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        // 상어의 사작 위치
        int sr = 0, sc = 0;
        // 초기값 입력
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                // 상어의 위치라면 좌표 저장
                if(board[r][c] == 9) {
                    sc = c;
                    sr = r;
                    board[r][c] = 0;
                }
            }
        }
        // 4방향 델타값
        int[][] d = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        // size : 현재 크기, eat : 먹은 물고기 수, ans : 물고기를 먹을 때 까지 이동한 거리
        int size = 2, eat = 0, ans = 0;
        // 이동을 관리할 pq (거리가 가까운 것 우선, 다음으로 r이 작은것 우선, 마지막으로 c가 작은 것 우선) 
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] != o2[2] ? o1[2] - o2[2] : o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        // 초기 좌표 및 이동거리 세팅
        pq.offer(new int[] {sr, sc, 0});
        // 초기 방문 처리
        visited[sr][sc] = true;
        
        // BFS 시작
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0], c = cur[1], dist = cur[2];
            // 만약 현재 칸에 먹을 수 있는 물고기가 있다면
            if(board[r][c] !=- 0 && board[r][c] < size) {
                // pq 초기화
                pq = new PriorityQueue<>((o1, o2) -> o1[2] != o2[2] ? o1[2] - o2[2] : o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
                // 방문 배열 초기화
                visited = new boolean[N][N];
                // 먹은 물고기 삭제
                board[r][c] = 0;
                // 먹은 물고기 수 증가
                eat++;
                // 현재까지 이동한 거리 저장
                ans = dist;
                // 먹은 뒤, 사이즈랑 같아진다면
                if(eat == size) {
                    // 먹은 물고기 수 초기화
                    eat = 0;
                    // 사이즈 증가
                    size++;
                }
            }
            // 4방향 탐색
            for(int dd = 0; dd < 4; dd++) {
                int nr = r + d[dd][0], nc = c + d[dd][1];
                boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
                // 이동 좌표가 범위 내이고, 이동하려는 칸이 현재 size보다 작거나 같으며, 아직 방문 전이라면
                if(in_range && board[nr][nc] <= size && !visited[nr][nc]) {
                    // 새로운 탐색 위치 추가
                    pq.offer(new int[] {nr, nc, dist+1});
                    // 방문 처리
                    visited[nr][nc] = true;
                }
            }
        }
        
        // 더 이상 먹을 물고기가 없으면, 마지막 물고기를 먹었을 때까지 움직인 거리 출력
        System.out.println(ans);
    }
}
