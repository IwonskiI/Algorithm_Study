import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 15963 - CASIO
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 송찬이와 선생님이 가져온 베터리의 종류 입력
        String N = st.nextToken();
        String M = st.nextToken();
        
        // 두 수가 같으면 1 출력
        if (N.equals(M)) System.out.println(1);
        // 아니면 0 출력
        else System.out.println(0);
    }
}