import java.util.*;
import java.io.*;
import java.math.*;
    
public class Main {
        
    // BOJ 1271 - 엄청난 부자2
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        BigDecimal n = new BigDecimal(st.nextToken());
        BigDecimal m = new BigDecimal(st.nextToken());
        
        BigDecimal arr[] = n.divideAndRemainder(m);
        sb.append(arr[0]).append("\n").append(arr[1]);
        
        
        // 결과 출력
        System.out.println(sb.toString());
    }
    
}