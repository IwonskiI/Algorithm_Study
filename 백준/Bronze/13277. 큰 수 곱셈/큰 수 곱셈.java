import java.io.*;
import java.math.*;
import java.util.*;


public class Main {
    
    // BOJ 13277 - 큰 수 곱셈
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        BigInteger A = new BigInteger(st.nextToken());
        BigInteger B = new BigInteger(st.nextToken());
        
        System.out.println(A.multiply(B));
    }
}