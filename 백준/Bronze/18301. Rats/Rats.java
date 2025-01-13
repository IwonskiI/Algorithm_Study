import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 18301 - Rats
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int n12 = Integer.parseInt(st.nextToken());
        
        System.out.println((int)((n1+1) * (n2+1) / (n12+1) - 1));
    }
}