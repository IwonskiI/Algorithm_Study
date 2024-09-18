import java.io.*;
import java.util.*;

public class Main {
    
	// BOJ 25304 - 영수증
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        // 결제 금액 X 입력
        int X = Integer.parseInt(br.readLine());
        
        // 물건의 개수 n 입력
        int n = Integer.parseInt(br.readLine());
        // 물건의 총합을 계산할 sum 선언
        int sum = 0;
        
        // n개의 물건에 대한 합 더하기
        for(int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	// 물건의 금액 * 물건의 개수를 sum에 누적해서 더하기
        	sum += (Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken()));
        }
        
        // 총합이 일치하면 Yes
        if (sum == X) sb.append("Yes");
        // 아니라면 No 저장
        else sb.append("No");
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}