import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int min_num = 1000001, max_num = -1000001;
		for(int i = 0; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(temp > max_num) max_num = temp;
			if(temp < min_num) min_num = temp;
		}
		sb.append(min_num).append(" ").append(max_num);
		
		System.out.println(sb.toString());
	}

}
