import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int ans = 0, small = 1;
		int[] len_lst = new int[4];
		int[][] lst = new int[6][2];
		
		// 1 : E, 2: W, 3: S, 4: N
		
		for(int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int len = Integer.parseInt(st.nextToken());
			len_lst[dir] += len;
			lst[i][0] = dir;
			lst[i][1] = len;
		}
		
		for(int i = 0; i < 6; i++){
		    int prev = i==0?5:i-1;
		    int next = i==5?0:i+1;
		    if(lst[prev][0] == lst[next][0]) small *= lst[i][1];
		}
		
        ans = len_lst[0] * len_lst[2];
        ans -= small;
		ans *= N;
		System.out.println(ans);
	}

}
