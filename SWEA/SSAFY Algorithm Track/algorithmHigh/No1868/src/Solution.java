import java.util.Scanner;

public class Solution {
	
	static int N, ans;
	static int[] num_arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}

}

