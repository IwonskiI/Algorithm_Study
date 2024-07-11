import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		LinkedList<Integer> num_lst = new LinkedList<>();
		int cnt = 1;
		
		while (st.hasMoreTokens()) {
			int cur = Integer.parseInt(st.nextToken());
			num_lst.add(num_lst.size() - cur ,cnt);
			cnt++;
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(num_lst.get(i)).append(" ");
		}
		System.out.println(sb.toString());
	}
}
