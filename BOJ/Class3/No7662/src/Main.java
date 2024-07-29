import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 7662 - 이중 우선순위 큐
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			TreeMap<Integer, Integer> map = new TreeMap<>();
			int n = Integer.parseInt(br.readLine());
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String mod = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				switch(mod) {
				case "I":
					map.put(num, map.getOrDefault(num, 0)+1);
					break;
				case "D":
					if(map.size() == 0) continue;
					int temp;
					if(num == 1) temp = map.lastKey(); 
					else temp = map.firstKey();
					if(map.put(temp, map.get(temp)-1)==1) map.remove(temp);
					break;
				}
			}
			
			if(map.size() == 0) sb.append("EMPTY\n");
			else sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
		}
		System.out.println(sb.toString());
	}

}
