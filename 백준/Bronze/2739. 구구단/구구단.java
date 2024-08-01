import java.io.*;


public class Main {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= 9; i++) {
			sb.append(n).append(" * ").append(i).append(" = ").append(n*i).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

}