import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] left_lst = new boolean[42];
		
		for(int i = 0; i < 10; i++) {
			int n = Integer.parseInt(br.readLine());
			left_lst[n%42] = true;
		}
		
		int ans = 0;
		for(int i = 0; i < 42; i++) {
			if(left_lst[i]) ans += 1;
		}
		
		System.out.println(ans);
		
	}

}
