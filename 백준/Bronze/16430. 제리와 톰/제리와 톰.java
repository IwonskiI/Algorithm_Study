import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 16430 - 제리와 톰
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = B - A;
        
        System.out.println(C + " " + B);
    }
}