import java.util.*;
import java.io.*;

public class Main {

    // BOJ 15650 - N과 M(2)
	public static int N, M;
	public static int[] number;
	public static StringBuilder sb = new StringBuilder();
	
	public static void comb(int start, int cnt) {
		if(cnt == M) {
			for(int v : number) {
				sb.append(v).append(" ");
			}
			sb.append("\n");
			return;
		}
		else {
			for(int i = start; i <= N; i++) {
				number[cnt] = i;
				comb(i+1, cnt + 1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        number = new int[M];
        comb(1, 0);
        
        System.out.println(sb.toString());
    }
}
