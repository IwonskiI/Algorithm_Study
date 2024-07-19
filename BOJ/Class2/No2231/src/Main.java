import java.io.*;


public class Main {
	
	//BOJ2231 - 분해합 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		
		// 1부터 N까지 분해합을 구하며 분해합이 N이 되는 i를 찾음
		for(int i = 1; i < N; i++) {
			int num = i;
			String s_num = Integer.toString(i);
			String[] num_lst = s_num.split("");
			
			for(int j = 0; j < num_lst.length; j++) {
				num += Integer.parseInt(num_lst[j]);
			}
			if(num == N) {
				ans = i;
				break;
			}
		}
		
		System.out.println(ans);

	}

}
