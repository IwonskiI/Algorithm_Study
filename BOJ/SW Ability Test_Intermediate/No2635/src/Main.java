import java.util.*;
import java.io.*;


public class Main {
	
	static List<Integer> num_lst = new LinkedList<>();
	static int N;
	
	public static void calc_num(int k) {
		List<Integer> temp_lst = new LinkedList<>();
		int prev = N, cur = k, res = 0;
		temp_lst.add(prev);
		temp_lst.add(cur);
		while(res >= 0) {
			res = prev - cur;
			if(res >= 0) temp_lst.add(res);
			prev = cur;
			cur = res;
		}
		if (num_lst.size() < temp_lst.size()) num_lst = temp_lst;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = N; i >= 0; i--) {
			calc_num(i);
		}
		
		sb.append(num_lst.size()).append("\n");
		
		for (int i = 0; i < num_lst.size(); i++) {
			sb.append(num_lst.get(i)).append(" ");
		}
		
		System.out.println(sb.toString());

	}

}
