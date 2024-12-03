import java.util.*;
import java.io.*;

public class Main {

    // BOJ 19532 - 수학은 비대면강의입니다
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());
        
        // 모든 경우의 수 계산
        for(int x = -999; x <= 999; x++) {
        	for(int y = -999; y <= 999; y++) {
        		// 식을 만족하는 해를 찾았다면 저장 후 종료
        		if((a*x)+(b*y) == c && (d*x) + (e*y) == f) {
        			sb.append(x).append(" ").append(y);
        			break;
        		}
        	}
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
        
    }

}