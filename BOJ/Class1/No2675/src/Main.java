import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String[] txt = st.nextToken().split("");
			for(int j = 0; j < txt.length; j++) {
				for(int k = 0; k < x; k++) {
					sb.append(txt[j]);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
