import java.util.Scanner;

public class Solution {

	static int N, M, ans;
	static int[][] num_arr;
	static int[] pdc = { -1, 0, 0, 1 };
	static int[] pdr = { 0, 1, -1, 0 };
	static int[] xdc = { -1, -1, 1, 1 };
	static int[] xdr = { -1, 1, -1, 1 };

	public static int dfs(int c, int r) {
		int p_ans = num_arr[c][r], x_ans = num_arr[c][r];
		for (int dd = 0; dd < 4; dd++) {
			int level = 1;
			int nc = c + pdc[dd], nr = r + pdr[dd];
			boolean in_range = ((0 <= nc) && (nc < N)) && ((0 <= nr) && (nr < N));
			while (in_range && level < M) {
				level += 1;
				p_ans += num_arr[nc][nr];
				nc += pdc[dd];
				nr += pdr[dd];
				in_range = ((0 <= nc) && (nc < N)) && ((0 <= nr) && (nr < N));
			}
		}
		for (int dd = 0; dd < 4; dd++) {
			int level = 1;
			int nc = c + xdc[dd], nr = r + xdr[dd];
			boolean in_range = ((0 <= nc) && (nc < N)) && ((0 <= nr) && (nr < N));
			while (in_range && level < M) {
				level += 1;
				x_ans += num_arr[nc][nr];
				nc += xdc[dd];
				nr += xdr[dd];
				in_range = ((0 <= nc) && (nc < N)) && ((0 <= nr) && (nr < N));
			}
		}
		return Math.max(p_ans, x_ans);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			ans = -1;
			N = sc.nextInt();
			M = sc.nextInt();
			sc.nextLine();
			num_arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					num_arr[i][j] = sc.nextInt();
				}
			}
			sc.nextLine();
			for (int col = 0; col < N; col++) {
				for (int row = 0; row < N; row++) {
					int temp = dfs(col, row);
					ans = temp > ans ? temp : ans;
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}

}
