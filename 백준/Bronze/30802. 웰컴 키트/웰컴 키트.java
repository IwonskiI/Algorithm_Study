import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		long[] size_lst = new long[6];
		
		long n = Long.parseLong(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 6; i++) {
			size_lst[i] = Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		long t = Long.parseLong(st.nextToken());
		long p = Long.parseLong(st.nextToken());
		
		
		long s_num = 0, p_num = n / p, sp_num = n % p;
		
		for(int i = 0; i < 6; i++) {
			s_num += (size_lst[i]/t);
			if(size_lst[i]%t != 0) s_num += 1;
		}
		
		sb.append(s_num).append("\n").append(p_num).append(" ").append(sp_num);
		
		System.out.println(sb.toString());

	}

}
