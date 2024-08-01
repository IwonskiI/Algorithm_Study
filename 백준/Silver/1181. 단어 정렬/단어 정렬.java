import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 1181 - 단어 정렬
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		List<String> dict = new ArrayList<>();
		int minus = 0;
		for(int i = 0; i < n; i++) {
			String temp = br.readLine();
			if(!dict.contains(temp)) dict.add(temp);
			else minus++;
		}
		n -= minus;
		Collections.sort(dict, (o1, o2) -> {
			if(o1.length() != o2.length()) return o1.length() - o2.length();
			else return o1.compareTo(o2);
		});
		
		for(int i = 0; i < n; i++) {
			sb.append(dict.get(i)).append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
