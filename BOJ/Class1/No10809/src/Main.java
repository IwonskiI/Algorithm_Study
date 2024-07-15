import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] word = br.readLine().split("");
		int[] alpha = new int[26];
		for(int i = 0; i < 26; i++) {
			alpha[i] = -1;
		}
		
		for(int i = 0; i < word.length; i++) {
			int idx = (int)word[i].charAt(0) - 97;
			if (alpha[idx] == -1) alpha[idx] = i;
		}
		
		for(int i = 0; i < 26; i++) {
			sb.append(alpha[i]).append(" ");
		}
		
		System.out.println(sb.toString());
	}

}
