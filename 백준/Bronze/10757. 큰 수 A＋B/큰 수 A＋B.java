import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

	// BOJ 9086 - 문자열
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 큰 수 두개 입력
		BigInteger a = new BigInteger(st.nextToken());
		BigInteger b = new BigInteger(st.nextToken());
		sb.append(a.add(b));
		
		// 최종 결과 저장
		System.out.println(sb.toString());
	}

}