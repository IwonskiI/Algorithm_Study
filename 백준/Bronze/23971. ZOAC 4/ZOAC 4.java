import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 23971 - ZOAC 4
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 각 행에 앉을 수 있는 최대 인원 계산
        int col = H / (N + 1);
        if(H % (N+1) != 0) col += 1;
        // 각 열에 앉을 수 있는 최대 인원 계산
        int row = W / (M + 1);
        if(W % (M+1) != 0) row += 1;
        
        // 전체 앉을 수 있는 최대 인원 계산
        System.out.println(col * row);
    }
}