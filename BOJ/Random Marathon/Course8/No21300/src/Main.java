import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 21300 - Bottle Return
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 합을 구할 변수
		int sum = 0;
		
		// 전체 병 수를 더함
		for(int i = 0; i < 6; i++) {
			sum += Integer.parseInt(st.nextToken());
		}
		
		// 병수 * 5를 출력
		System.out.println(sum * 5);

	}

}
