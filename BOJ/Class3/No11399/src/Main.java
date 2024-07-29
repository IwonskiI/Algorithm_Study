import java.io.*;
import java.util.*;

public class Main {

	//BOJ 11399 - ATM
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] num_lst = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num_lst[i] = Integer.parseInt(st.nextToken());
		}
		
		// 입력 받은 수를 오름차순으로 정렬
		Arrays.sort(num_lst);
		
		int total = 0;
		for(int i = 0; i < N; i++) {
			// 작은 수부터 N-i번 중첩 되므로 곱해서 더해줌 
			total += num_lst[i] * (N-i); 
		}
		
		System.out.println(total);
	}

}
