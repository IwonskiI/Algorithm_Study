import java.io.*;

public class Main {
    
	// BOJ 25314 - 코딩은 체육과목 입니다
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 입력 받은 수를 4로 나눔
        int N = Integer.parseInt(br.readLine()) / 4;
        
        // 몫 만큼 long을 추가
        for(int i = 0; i < N; i++) {
        	sb.append("long ");
        }
        
        // 마지막에 int 추가
        sb.append("int");
                
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}