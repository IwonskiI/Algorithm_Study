import java.io.*;
import java.util.*;

public class Main {

	// BOJ 10864 - 친구
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 친구 수를 저장할 map 선언
		HashMap<Integer, Integer> friend = new HashMap<>();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 번호 별 첫 친구 수는 0으로 초기화
		for(int i = 1; i <= N; i++) {
			friend.put(i, 0);
		}
		
		// 입력 값에 따라 친구 수를 양쪽 다 증가
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int aCnt = friend.get(a), bCnt = friend.get(b);
			friend.put(a, aCnt + 1);
			friend.put(b, bCnt + 1);
		}
		
		// 번호 순서대로 친구 수 출력
		for(int i = 1; i <= N; i++) {
			sb.append(friend.get(i)).append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
