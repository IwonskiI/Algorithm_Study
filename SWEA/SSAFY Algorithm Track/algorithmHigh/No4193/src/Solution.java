import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static class Pos {
        int col, row, time;

        public Pos(int col, int row, int time) {
            this.col = col;
            this.row = row;
            this.time = time;
        }
    }

    static int[] dc = { -1, 0, 1, 0 };
    static int[] dr = { 0, -1, 0, 1 };
    static int[][] board, n_board;
    static boolean[][] visited;
    static int N, ans;
    static int SC, SR, EC, ER;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            ans = 0;
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            visited = new boolean[N][N];

            for (int c = 0; c < N; c++) {
                st = new StringTokenizer(br.readLine());
                for (int r = 0; r < N; r++) {
                    int cur = Integer.parseInt(st.nextToken());
                    board[c][r] = cur;
                    visited[c][r] = false;
                }
            }
            st = new StringTokenizer(br.readLine());
            SC = Integer.parseInt(st.nextToken());
            SR = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            EC = Integer.parseInt(st.nextToken());
            ER = Integer.parseInt(st.nextToken());

            if (SC == EC && SR == ER) {
                ans = 0;
            } else {
                ans = bfs();
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(SC, SR, 0));
        visited[SC][SR] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nc = p.col + dc[i];
                int nr = p.row + dr[i];
                if (nc < 0 || nc >= N || nr < 0 || nr >= N)
                    continue;
                if (nc == EC && nr == ER) {
                    ans = p.time + 1;
                    return ans;
                }
                if (board[nc][nr] == 1 || visited[nc][nr])
                    continue;
                if (board[nc][nr] == 2) {
                    if (p.time % 3 == 2) {
                        visited[nc][nr] = true;
                        q.add(new Pos(nc, nr, p.time + 1));
                    } else {
                        visited[p.col][p.row] = true;
                        q.add(new Pos(p.col, p.row, p.time + 1));
                    }
                } else {
                    visited[nc][nr] = true;
                    q.add(new Pos(nc, nr, p.time + 1));
                }
            }
        }

        return -1;
    }

}
