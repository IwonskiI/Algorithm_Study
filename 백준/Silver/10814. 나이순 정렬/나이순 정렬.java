import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 10814 - 나이순 정렬
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		String[][] user = new String[N][2];
		
		// 0번 인덱스에 나이를, 1번 인덱스에 이름을 저장
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			user[i][0] = st.nextToken();
			user[i][1] = st.nextToken();
		}
		
		// 나이가 같으면 들어온 순서대로(우선순위 동일), 나이가 다르면 오름차순으로 정렬
		Arrays.sort(user, (o1, o2)->{
			if(o1[0].equals(o2[0])) return 0;
			else return Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]);
		});
		
		for(int i = 0; i < N; i++) {
			sb.append(user[i][0]).append(" ").append(user[i][1]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
