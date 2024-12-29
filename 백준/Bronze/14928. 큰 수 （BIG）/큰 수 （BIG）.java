import java.io.*;

public class Main {
    
    // BOJ 14928 - 큰 수 (BIG)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        long temp = 0;
        for (int i = 0; i < N.length(); i++) {
            temp = (temp * 10 + (N.charAt(i) - '0')) % 20000303;
        }
        
        System.out.println(temp);
    }
}