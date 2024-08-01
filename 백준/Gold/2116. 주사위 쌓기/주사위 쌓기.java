import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] res_lst = new int[6][2];
		for(int i = 0; i < 6; i++) {
			res_lst[i][0] = i+1;
		}
		for(int i = 0; i < n; i++) {
			Map<Integer, Integer> map = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int F = Integer.parseInt(st.nextToken());
			map.put(A, F);
			map.put(B, D);
			map.put(C, E);
			map.put(D, B);
			map.put(E, C);
			map.put(F, A);
			for(int num = 0; num < 6; num++) {
				if (res_lst[num][0] != 6 && map.get(res_lst[num][0]) != 6) res_lst[num][1] += 6;
				else {
					if((res_lst[num][0] == 6 && map.get(res_lst[num][0]) == 5) || (res_lst[num][0] == 5 && map.get(res_lst[num][0]) == 6))
					res_lst[num][1] += 4;
					else res_lst[num][1] += 5;
				}
				res_lst[num][0] = map.get(res_lst[num][0]);
			}
		}
		int ans = 0;
		for(int i = 0; i < 6; i++) {
			ans = res_lst[i][1] > ans ? res_lst[i][1] : ans;
		}
		
		System.out.println(ans);
	}

}