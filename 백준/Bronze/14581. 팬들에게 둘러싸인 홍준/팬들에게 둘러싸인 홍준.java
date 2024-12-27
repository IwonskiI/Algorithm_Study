import java.io.*;

public class Main {
    
    // BOJ 14581 - 팬들에게 둘러싸인 홍준
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String id = br.readLine();
        
        sb.append(":fan::fan::fan:\n:fan::").append(id).append("::fan:\n:fan::fan::fan:");
        
        System.out.println(sb.toString());
    }
}