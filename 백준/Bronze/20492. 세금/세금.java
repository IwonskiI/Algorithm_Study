import java.io.*;

public class Main {
    
    // BOJ 20492 - 세금
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        // 제세공과금을 제외한 78%와 80%경비+나머지 20%의 78%(15.6%) 출력
        System.out.println((int)(0.78*N) + " " + (int)(0.956*N));
    }
}