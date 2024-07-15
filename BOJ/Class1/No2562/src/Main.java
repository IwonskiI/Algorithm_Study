import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int max_num = 0, max_idx = 0;
		
		for(int i = 0; i < 9; i++) {
			int temp = Integer.parseInt(br.readLine());
			if (max_num < temp) {
				max_num = temp;
				max_idx = i + 1;
			}
		}
		
		System.out.println(max_num);
		System.out.println(max_idx);
	}

}
