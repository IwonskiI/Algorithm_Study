import java.io.*;

public class Main {
	
	// BOJ 2751 - 수 정렬하기 2
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// N을 입력받고, N크기의 boolean 배열 선언
		int N = Integer.parseInt(br.readLine());
		boolean[] num_lst = new boolean[2000001];
		
		// 입력된 숫자를 각 인덱스에 저장. 음수도 있으므로, 1000000을 더해줌.
		for(int i = 0; i < N; i++) {
			num_lst[Integer.parseInt(br.readLine())+1000000] = true;
		}
		
		// 각 인덱스에 숫자가 있으면 순서대로 출력
		for(int i = 0; i < 2000001; i++) {
			if(num_lst[i]) sb.append(i-1000000).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
