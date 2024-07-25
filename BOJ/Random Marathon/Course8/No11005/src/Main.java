import java.io.*;
import java.util.*;

public class Main {

	// BOJ 11005 - 진법 전환 2
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		// N진수 전환
		while(N >= B) {
			int div = N / B;
			int mod = N % B;
			// 나머지가 9보다 크다면 문자로 변환 (아스키 코드 활용)
			if(mod > 9) {
				mod += 55;
				sb.append((char)mod);
			}
			else sb.append(mod);
			N = div;
		}
		// 남은 수도 진법전환해서 추가
		if (N > 9) {
			N += 55;
			sb.append((char)N);
		}
		else sb.append(N);
		
		// 문자열을 뒤집은 뒤 출력
		System.out.println(sb.reverse().toString());

	}

}
