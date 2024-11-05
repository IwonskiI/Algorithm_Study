import java.io.*;
import java.util.*;

public class Main {
    
	// BOJ 4179 - 불!
	public static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 사이즈에 맞는 보드 및 2차원 방문 배열 생성
        char[][] board = new char[R][C];
        boolean[][] visited = new boolean[R][C];

        // 불을 관리할 큐 생성
        Queue<int[]> fire = new LinkedList<>();

        // 상근이의 시작 위치 저장
        int sr = 0, sc = 0;

        // 보드 입력
        for(int r = 0; r < R; r++) {
            String[] line = br.readLine().split("");
            for(int c = 0; c < C; c++) {
                board[r][c] = line[c].charAt(0);
                // 입력 값이 J(지훈이의 위치)라면 초기 위치 저장 및 보드판 . 초기화
                if(board[r][c] == 'J') {
                    sr = r;
                    sc = c;
                    board[r][c] = '.';
                }
                // 입력값이 F(불)이라면 불 관리 큐에 좌표 추가
                else if(board[r][c] == 'F') {
                    fire.offer(new int[] {r, c});
                }
            }
        }

        // BFS 탐색
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {sr, sc, 0});
        visited[sr][sc] = true;
        // 불을 퍼뜨리기 위한 시간 관리 변수 선언
        int prev = -1;
        // 탈출 여부를 저장할 boolean 변수 선언
        boolean esc = false;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], t = cur[2];
            // 현재 위치가 테두리에 있다면 다음 시간에 무조건 탈출
            if(r == 0 || r == R-1 ||c == 0 || c == C-1) {
                // 현재 시간 + 1을 저장하고 반복문 종료 및 탈출 성공 표시
                sb.append(t+1).append("\n");
                esc = true;
                break;
            }
            // 현재 시간이 이전시간과 다르다면 -> 불을 확산
            if(t != prev) {
                // 이전 시간 업데이트
                prev = t;
                // 새로운 불을 임시 저장할 큐
                Queue<int[]> temp = new LinkedList<>();
                // 현재 불을 모두 확산
                while(!fire.isEmpty()) {
                    int[] f_cur = fire.poll();
                    int fr = f_cur[0], fc = f_cur[1];
                    // 4방향 탐색하며 벽이 아니고 이미 불이 아닌 곳에 불 옮김
                    for(int dd = 0; dd < 4; dd++) {
                        int nfr = fr + d[dd][0], nfc = fc + d[dd][1];
                        boolean fin_range = (0 <= nfr && nfr < R) && (0 <= nfc && nfc < C);
                        if(fin_range && board[nfr][nfc] == '.') {
                            board[nfr][nfc] = 'F';
                            temp.offer(new int[] {nfr, nfc});
                        }
                    }
                }
                // 임시 배열에 저장해둔 현재 시간에 붙은 불들을 다시 fire 큐에 저장
                fire = temp;
            }

            // 4방향 탐색
            for(int dd = 0; dd < 4; dd++) {
                int nr = r + d[dd][0], nc = c + d[dd][1];
                boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
                // 범위 안에 있고, 방문 전이며, 이동할 수 있는 공간이라면
                if(in_range && !visited[nr][nc] && board[nr][nc] == '.') {
                    // 이동
                    q.offer(new int[] {nr, nc , t+1});
                    visited[nr][nc] = true;
                }
            }
        }

        // 이동할 수 있는 공간에 최대한 이동 했을 때 탈출에 실패 했다면 불가능
        if(!esc) sb.append("IMPOSSIBLE\n");
        
        System.out.println(sb.toString());
    }
}