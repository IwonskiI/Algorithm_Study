import java.io.*;

public class Main {
    
    // BOJ 25372 - 성택이의 은밀한 비밀번호
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 비밀번호의 개수 입력
        int N = Integer.parseInt(br.readLine());
        
        // N개의 비밀번호 테스트
        for(int i = 0; i < N; i++){
            String pw = br.readLine();
            // 비밀번호의 길이가 6 이상, 9 이하라면 yes를,
            if(6 <= pw.length() && pw.length() <= 9) sb.append("yes\n");
            // 아니라면 no를 저장
            else sb.append("no\n");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}