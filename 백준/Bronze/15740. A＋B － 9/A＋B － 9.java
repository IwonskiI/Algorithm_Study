import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    
    // BOJ 15740 - A+B - 9
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        BigInteger A = new BigInteger(st.nextToken());
        BigInteger B = new BigInteger(st.nextToken());
        
        System.out.println(A.add(B));
    }
}