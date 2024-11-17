import java.io.*;
import java.util.*;

public class Main {

	// BOJ 2420 - 사파리월드
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N, M 입력
		long N = Integer.parseInt(st.nextToken());
		long M = Integer.parseInt(st.nextToken());
		
		// 유명도의 차이
		System.out.println(Math.abs(N-M));
	}

}