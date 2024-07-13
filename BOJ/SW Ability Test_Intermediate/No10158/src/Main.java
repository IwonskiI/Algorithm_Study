import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(br.readLine());
        int x_dir = 1, y_dir = 1;
        int xt = t, yt = t;
        int x_cycle = 2 * C, y_cycle = 2 * R;
        
        xt %= x_cycle;
        for (int i = 0; i < xt; i++) {
            x += x_dir;
            if (x == C || x == 0) x_dir *= -1;
        }
        
        yt %= y_cycle;
        for (int i = 0; i < yt; i++) {
            y += y_dir;
            if (y == R || y == 0) y_dir *= -1;
        }

		sb.append(x).append(" ").append(y);
		System.out.println(sb.toString());
	}

}
