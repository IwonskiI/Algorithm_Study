import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 16435 - 스네이크 버드
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] num_lst = new int[N];
		// 과일의 높이 입력
		for(int i = 0; i < N; i++) {
			num_lst[i] = Integer.parseInt(st.nextToken());
		}
		// 과일을 낮은것 우선 먹을 수 있도록 정렬
		Arrays.sort(num_lst);
		
		// 낮은 과일부터 먹으면서 성장하다가 남은 과일 중 제일 낮은 것을 높아서 못먹게 되면 중단
		for(int i = 0; i < N; i++) {
			if(num_lst[i] > L) break;
			L++;
		}
		// 길이 출력
		System.out.println(L);
	}
}
