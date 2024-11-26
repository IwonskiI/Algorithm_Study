import java.io.*;

public class Main {

	// BOJ 5522 - 카드 게임
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sum = 0;
		
		for(int i = 0; i < 5; i++) {
			sum += Integer.parseInt(br.readLine());
		}
		
		// 최종 결과 출력
		System.out.println(sum);
	}

}