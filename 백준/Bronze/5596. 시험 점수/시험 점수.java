import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 5596 - 시험 점수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int S = 0, T = 0;
		
		// 민국의 총점 계산
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			S += Integer.parseInt(st.nextToken());
		}
		
		// 만세의 총점 계산
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			T += Integer.parseInt(st.nextToken());
		}
		
		// 만세의 점수가 더 크다면 만세 점수 출력 
		if(S < T) System.out.println(T);
		// 민국의 점수가 더 크거나 동점이면 민국 점수 출력
		else System.out.println(S);

	}

}
