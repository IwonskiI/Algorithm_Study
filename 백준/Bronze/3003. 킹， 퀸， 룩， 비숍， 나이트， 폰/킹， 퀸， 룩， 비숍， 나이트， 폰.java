import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 3003 - 킹, 퀸, 룩, 비숍, 나이트, 폰
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 전체 흰 피스의 개수 (킹, 퀸, 룩, 비숍, 나이트, 폰 순서)
        int[] full = new int[] {1, 1, 2, 2, 2, 8};
        
        for(int i = 0; i < 6; i++) {
        	// 현재 가지고 있는 피스의 수
        	int cur = Integer.parseInt(st.nextToken());
        	// 있어야할 피스 수와 현재 가지고 있는 수의 차이 계산 후 저장
        	sb.append(full[i] - cur).append(" ");
        }
		
        // 최종 결과 출력
        System.out.println(sb.toString());
	}

}