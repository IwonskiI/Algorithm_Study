import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 1764 - 듣보잡
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		HashMap<String, Boolean> list = new HashMap<>();
		List<String> list2 = new LinkedList<>();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) {
			String name = br.readLine();
			list.put(name, true);
		}
		
		for(int i = 0; i < M; i++) {
			String name = br.readLine();
			if(list.getOrDefault(name, false)) list2.add(name);
		}
		Collections.sort(list2);
		sb.append(list2.size()).append("\n");
		for(String n : list2) {
			sb.append(n).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}

