import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 11650 - 좌표 정렬하기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[][] num_lst = new int[N][2];
		
		// 0번 인덱스에 x좌표를, 1번 인덱스에 y좌표를 저장
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			num_lst[i][0] = Integer.parseInt(st.nextToken());
			num_lst[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// x좌표를 오름차순으로, x좌표가 같으면 y좌표를 오름차순으로 정렬
		Arrays.sort(num_lst, (o1, o2)->{
			if(o1[0] != o2[0]) return o1[0] - o2[0];
			else return o1[1] - o2[1];
		});
		
		for(int i = 0; i < N; i++) {
			sb.append(num_lst[i][0]).append(" ").append(num_lst[i][1]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
