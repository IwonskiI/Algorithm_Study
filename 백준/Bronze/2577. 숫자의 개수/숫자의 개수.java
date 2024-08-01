import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		
		int res = A * B * C;
		String[] txt = Integer.toString(res).split("");
		int[] num_lst = new int[10];
		
		for(int i = 0; i < txt.length; i++) {
			int cur = Integer.parseInt(txt[i]);
			num_lst[cur]++;
		}
		
		for(int i = 0; i < 10; i++) {
			sb.append(num_lst[i]).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

}
