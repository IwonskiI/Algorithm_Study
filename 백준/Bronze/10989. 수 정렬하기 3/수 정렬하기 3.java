import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[10001];
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[Integer.parseInt(br.readLine())]++;
		}
		
		for(int i = 1; i <= 10000; i++) {
			if(arr[i] != 0) {
				for(int j = 0; j < arr[i]; j++) {
					sb.append(i).append("\n");
				}
			}
		}
		
		System.out.println(sb.toString());
	}

}
