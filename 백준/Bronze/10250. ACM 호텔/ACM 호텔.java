import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			st.nextToken();
			int n = Integer.parseInt(st.nextToken());
			int floor = (n % row);
			int room_num = (n / row) + 1;
			if (floor == 0) {
				floor = row;
				room_num -= 1;
			}
			
			sb.append(floor);
			if(room_num < 10) sb.append(0);
			sb.append(room_num).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
