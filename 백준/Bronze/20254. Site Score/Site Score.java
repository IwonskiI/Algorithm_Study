import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 20254 - Site Score
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int ans = Integer.parseInt(st.nextToken())*56 + Integer.parseInt(st.nextToken())*24 + Integer.parseInt(st.nextToken())*14 + Integer.parseInt(st.nextToken())*6;
        
        System.out.println(ans);
    }
}