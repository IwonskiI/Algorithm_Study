import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ans = 0;
		// N = 1, S = 2, W = 3, E = 4
		// N, S => Left, W, E => Top
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int N = Integer.parseInt(br.readLine());
		int[][] pos_lst = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p_dir = Integer.parseInt(st.nextToken());
			int p_cnt = Integer.parseInt(st.nextToken());
			pos_lst[i][0] = p_dir;
			pos_lst[i][1] = p_cnt;
		}
		
		st = new StringTokenizer(br.readLine());
		int dir = Integer.parseInt(st.nextToken());
		int cnt = Integer.parseInt(st.nextToken());
		
		if (dir == 1 || dir == 2) { // dir == N, S
			for(int i = 0; i < N; i++) {
				if (pos_lst[i][0] == 1 || pos_lst[i][0] == 2) {
					if (pos_lst[i][0] == dir) {
						ans += (pos_lst[i][1] > cnt ? pos_lst[i][1]-cnt : cnt - pos_lst[i][1]);
					}
					else {
						ans += y;
						int temp1 = pos_lst[i][1] + cnt, temp2 = (x-pos_lst[i][1]) + (x-cnt);
						ans += (temp1 < temp2 ? temp1 : temp2);
					}
				}
				else {
					if(dir == 1) {
						ans += pos_lst[i][1];
						if(pos_lst[i][0] == 3) ans += cnt;
						else ans += x-cnt;
					}
					else {
						ans += y-pos_lst[i][1];
						if(pos_lst[i][0] == 3) ans += cnt;
						else ans += x-cnt;
					}
				}
			}
		}
		else { // dir == W, E
			for(int i = 0; i < N; i++) {
				if (pos_lst[i][0] == 3 || pos_lst[i][0] == 4) {
					if (pos_lst[i][0] == dir) {
						ans += (pos_lst[i][1] > cnt ? pos_lst[i][1]-cnt : cnt - pos_lst[i][1]);
					}
					else {
						ans += x;
						int temp1 = pos_lst[i][1] + cnt, temp2 = (y-pos_lst[i][1]) + (y-cnt);
						ans += (temp1 < temp2 ? temp1 : temp2);
					}
				}
				else {
					if(dir == 3) {
						ans += pos_lst[i][1];
						if(pos_lst[i][0] == 1) ans += cnt;
						else ans += y-cnt;
					}
					else {
						ans += x-pos_lst[i][1];
						if(pos_lst[i][0] == 1) ans += cnt;
						else ans += y-cnt;
					}
				}
			}
		}
		
		System.out.println(ans);
		
	}

}
