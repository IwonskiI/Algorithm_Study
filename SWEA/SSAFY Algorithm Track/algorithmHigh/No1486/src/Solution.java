import java.util.*;

public class Solution {

    static int N, B, ans;
    static int[] num_arr;

    public static void backtrack(int start, int cur_sum) {
        if (cur_sum >= B && cur_sum < ans) {
            ans = cur_sum;
        }
        for (int i = start; i < N; i++) {
            backtrack(i + 1, cur_sum + num_arr[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            ans = 999999;
            N = sc.nextInt();
            B = sc.nextInt();
            num_arr = new int[N];
            for (int i = 0; i < N; i++) {
                num_arr[i] = sc.nextInt();
            }
            Arrays.sort(num_arr);
            backtrack(0, 0);
            sb.append("#").append(tc).append(" ").append(ans - B).append("\n");
        }
        System.out.println(sb.toString());
        sc.close();
    }

}
