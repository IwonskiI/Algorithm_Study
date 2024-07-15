import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		m -= 45;
		if(m < 0) {
			h -= 1;
			m += 60;
		}
		if(h < 0) h = 23;
		sb.append(h).append(" ").append(m);
		System.out.println(sb.toString());
	}

}
