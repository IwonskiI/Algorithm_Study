import java.io.*;


public class Main {

    // BOJ 16394 - 홍익대학교
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 년도 입력
        int N = Integer.parseInt(br.readLine());
        
        // 정답 출력
        System.out.println(N - 1946);
        
    }

}