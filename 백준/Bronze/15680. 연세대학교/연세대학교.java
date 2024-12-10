import java.io.*;

public class Main {
    
    // BOJ - 15680 연세대학교
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        if(N == 0) System.out.println("YONSEI");
        else if(N == 1) System.out.println("Leading the Way to the Future");
    }
}