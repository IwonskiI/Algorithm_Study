import java.io.*;

public class Main {
    
    // BOJ 21598 - SciComLove
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < N; i++){
            sb.append("SciComLove\n");
        }
        
        System.out.println(sb.toString());
    }
}