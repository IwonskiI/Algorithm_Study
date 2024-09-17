import java.io.*;

public class Main {

	// BOJ 2747 - 피보나치 수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 피보나치 수열 저장
		int[] fibo = new int[46];
		// 초기 값 저장
		fibo[0] = 0;
		fibo[1] = 1;
		// 구하려고 하는 n 입력 후 해당 n까지 피보나치 수 계산
		int n = Integer.parseInt(br.readLine());
		for(int i = 2; i <= n; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
		}
		
		sb.append(fibo[n]);
		
		// 최종 정답 출력
		System.out.println(sb.toString());
	}

}