import java.io.*;
import java.util.*;

public class Main {

	// BOJ 4101 - 크냐?
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 0, 0이 나올 때까지 무한 반복
		while(true) {
			// 두 수 입력
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			// 두 수가 0, 0이라면 종료
			if(N == 0 && M == 0) break;
			
			// 첫 번째 수가 더 크면 Yes
			if(N > M) sb.append("Yes\n");
			// 아니면(더 작거나 같으면) No 저장
			else sb.append("No\n");
		}
		
		// 최종 결과 저장
		System.out.println(sb.toString());
	}

}