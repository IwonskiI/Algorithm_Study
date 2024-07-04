import java.util.*;

public class Solution {

	static int N;
	static String[][] num_arr;
	static String[][] deg90, deg180, deg270;

	public static String[][] rotate(String[][] arr) {
		String[][] ret = new String[N][N];

		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				ret[r][N - 1 - c] = arr[c][r];
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			num_arr = new String[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					num_arr[i][j] = sc.next();
				}
			}
			deg90 = rotate(num_arr);
			deg180 = rotate(deg90);
			deg270 = rotate(deg180);

			sb.append("#").append(tc).append("\n");

			for (int row = 0; row < N; row++) {
				String d90 = String.join("", deg90[row]);
				String d180 = String.join("", deg180[row]);
				String d270 = String.join("", deg270[row]);
				sb.append(d90).append(" ").append(d180).append(" ").append(d270).append("\n");
			}
		}
		System.out.println(sb.toString());
		sc.close();
	}

}