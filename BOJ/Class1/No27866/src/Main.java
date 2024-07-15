import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String word = br.readLine();
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(word.charAt(n-1));

	}

}
