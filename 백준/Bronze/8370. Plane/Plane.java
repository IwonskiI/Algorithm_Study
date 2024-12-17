import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 8370 - Plane
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // total number of seat = (n1(B class row num) * k1(num of seat in B class row)) + ((n2(E class row num) * k2(num of seat in E class row))
        System.out.println((Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken())) + (Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken())));
    }
}