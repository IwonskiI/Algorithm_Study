import java.io.*;

public class Main {
    
    // BOJ 24883 - 자동완성
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char word = br.readLine().charAt(0);
        
        if(word == 'N' || word == 'n') {
            System.out.println("Naver D2");
        }
        else System.out.println("Naver Whale");
    }
}