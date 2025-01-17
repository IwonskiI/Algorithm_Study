import java.io.*;

public class Main {
    
    // BOJ 15000 - CAPS
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String str = br.readLine();
        
        for(int i = 0; i < str.length(); i++){
            if(97 <= str.charAt(i) && str.charAt(i) <= 122){
                sb.append((char)(str.charAt(i)-32));
            }
            else sb.append(str.charAt(i));
        }
        
        System.out.println(sb.toString());
    }
}