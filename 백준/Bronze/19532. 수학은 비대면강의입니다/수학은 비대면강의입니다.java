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
        
        // 연립 방정식의 해 구하기
        sb.append(((c*e) - (f*b)) / ((a*e) - (d*b))).append(" ").append(((c*d) - (f*a)) / ((b*d) - (a*e)));
        
        // 최종 결과 출력
        System.out.println(sb.toString());
        
    }

}