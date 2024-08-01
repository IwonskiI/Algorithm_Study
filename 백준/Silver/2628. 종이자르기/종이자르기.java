import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken()), col = Integer.parseInt(st.nextToken());
		int max_r = 0, max_c = 0;
		
		int n = Integer.parseInt(br.readLine());
		List<Integer> r_lst = new ArrayList<>();
		List<Integer> c_lst = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken()), num = Integer.parseInt(st.nextToken());
			if(dir == 0) c_lst.add(num);
			else r_lst.add(num);
		}
		
		Collections.sort(c_lst);
		Collections.sort(r_lst);
		
		int r = 0, c = 0;
		for(int i = 0; i < c_lst.size(); i++) {
			if(c_lst.get(i) - c > max_c) max_c = c_lst.get(i) - c;
			c = c_lst.get(i);
		}
		if(col - c > max_c) max_c = col - c;
		for(int i = 0; i < r_lst.size(); i++) {
			if(r_lst.get(i) - r > max_r) max_r = r_lst.get(i) - r;
			r = r_lst.get(i);			
		}
		if(row - r > max_r) max_r = row - r;
		
		System.out.println(max_r * max_c);
		
	}

}
