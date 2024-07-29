import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 1764 - 듣보잡
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 듣도 못한 사람의 명단 list
		HashMap<String, Boolean> list = new HashMap<>();
		// 보도 못한 사람의 명단 list2
		List<String> list2 = new LinkedList<>();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) {
			String name = br.readLine();
			// (이름, true)로 입력
			list.put(name, true);
		}
		
		for(int i = 0; i < M; i++) {
			String name = br.readLine();
			// list에서 값을 읽어오고 있으면 true, 없으면 false를 return
			// true를 리턴했다면 list2에 이름 추가
			if(list.getOrDefault(name, false)) list2.add(name);
		}
		
		// 사전 순서로 정렬
		Collections.sort(list2);
		
		// list2의 전체 크기 (듣도 보도 못한 사람의 수) 추가
		sb.append(list2.size()).append("\n");
		// 반복문을 돌며 듣도 보도 못한 사람들 이름 추가
		for(String n : list2) {
			sb.append(n).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}

