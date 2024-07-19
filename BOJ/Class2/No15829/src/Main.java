import java.io.*;


public class Main {
	
	// BOJ15829 - Hashing
	
	// 상수 정의
	static final long r = 31, M = 1234567891;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split("");
		
		// 정답을 저장할 ans와 r의 거듭제곱을 담을 pow 선언. 초기값은 1로 설정
		long ans = 0L, pow = 1L;
		
		for(int i = 0; i < N; i++) {
			// 소문자 a의 아스키 코드인 97이 문제에서 1로 계산되므로 96을 빼고 pow를 곱해줌
			ans += ((long)str[i].charAt(0)-96) * pow;
			
			// 거듭제곱 진행
			pow *= r;
			// !!!pow가 long 범위 안에서 계산 될 수 있도록 M으로 나머지 연산을 해줌.!!!
			pow %= M;
		}
		
		// 마지막 정답에서도 M으로 나머지 연산을 진행해줌.
		System.out.println(ans % M);

	}

}
