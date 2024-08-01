import java.io.*;
import java.util.*;

public class Main {

	//BOJ 10816 - 숫자 카드 2
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		// 입력한 숫자와 그 카드의 개수를 키-값으로 입력하는 dictionary 정의 
		Map<Integer, Integer> map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			// num번 카드가 몇장이 있는지 map에서 가져오고 없으면 0
			int cnt = map.getOrDefault(num, 0);
			// 한장 더해서 map에 추가
			map.put(num, cnt+1);
		}
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			// 찾고자 하는 번호의 카드를 map에서 가져옴
			sb.append(map.getOrDefault(Integer.parseInt(st.nextToken()), 0)).append(" ");
		}
		
		System.out.println(sb.toString());
		
	}

}
