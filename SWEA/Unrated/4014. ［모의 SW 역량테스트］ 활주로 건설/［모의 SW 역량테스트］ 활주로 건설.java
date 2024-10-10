import java.util.Scanner;

public class Solution {

    public static boolean chkSlide(int[] r, int sp, int x, int[] v, int n) {
        for (int s = sp + 1; s < sp + x; s++) {
            if (s >= n) {
                return false;
            }
            if (r[s] == r[sp]) {
                continue;
            } else {
                return false;
            }
        }
        for (int s = sp + 1; s < sp + x; s++) {
            v[s] = 1;
        }
        return true;
    }

    public static boolean chkRoad(int[] r, int x, int n) {
        int curH = r[0];
        int curLength = 1;
        int[] v_r = new int[n];
        v_r[0] = 1;

        for (int r_n = 1; r_n < n; r_n++) {
            if (v_r[r_n] == 0) {
                v_r[r_n] = 1;
                if (r[r_n] == curH) {
                    curLength++;
                    continue;
                } else if (curH - r[r_n] == 1) {
                    if (chkSlide(r, r_n, x, v_r, n)) {
                        curH = r[r_n];
                        r_n += x - 1;
                        curLength = 0;
                        continue;
                    } else {
                        return false;
                    }
                } else if (r[r_n] - curH == 1) {
                    if (curLength >= x) {
                        curLength = 1;
                        curH = r[r_n];
                        continue;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int X = sc.nextInt();
            int[][] R = new int[N][N];
            int[][] n_R = new int[N][N];
            int answer = 0;

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    R[row][col] = sc.nextInt();
                }
            }

            // Check rows
            for (int row = 0; row < N; row++) {
                if (chkRoad(R[row], X, N)) {
                    answer++;
                }
            }

            // Transpose matrix and check columns
            for (int col = 0; col < N; col++) {
                for (int row = 0; row < N; row++) {
                    n_R[col][row] = R[row][col];
                }
            }

            for (int[] road : n_R) {
                if (chkRoad(road, X, N)) {
                    answer++;
                }
            }

            System.out.println("#" + (i + 1) + " " + answer);
        }

        sc.close();
    }
}