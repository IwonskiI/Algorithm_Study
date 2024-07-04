import java.util.*;

public class Solution {

    static int N, M, ans;
    static int[] arr_a, arr_b;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            ans = -1;
            N = sc.nextInt();
            M = sc.nextInt();
            arr_a = new int[N];
            arr_b = new int[M];
            for (int i = 0; i < N; i++) {
                arr_a[i] = sc.nextInt();
            }
            for (int i = 0; i < M; i++) {
                arr_b[i] = sc.nextInt();
            }
            if (N > M) {
                int start_p = N - M + 1;
                for (int sp = 0; sp < start_p; sp++) {
                    int temp = 0;
                    for (int s = 0; s < M; s++) {
                        temp += (arr_a[sp + s] * arr_b[s]);
                    }
                    ans = temp > ans ? temp : ans;
                }
            } else if (N < M) {
                int start_p = M - N + 1;
                for (int sp = 0; sp < start_p; sp++) {
                    int temp = 0;
                    for (int s = 0; s < N; s++) {
                        temp += (arr_a[s] * arr_b[sp + s]);
                    }
                    ans = temp > ans ? temp : ans;
                }
            } else {
                ans = 0;
                for (int i = 0; i < N; i++) {
                    ans += (arr_a[i] * arr_b[i]);
                }
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
        sc.close();
    }

}
