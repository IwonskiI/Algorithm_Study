import java.io.*;
import java.math.*;

public class Main {
        
    // BOJ 2338 - 긴자리 계산	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        BigInteger A = new BigInteger(br.readLine());
        BigInteger B = new BigInteger(br.readLine());
        
        sb.append(A.add(B)).append("\n").append(A.subtract(B)).append("\n").append(A.multiply(B));
        
        // 결과 출력
        System.out.println(sb.toString());
    }
    
}