import java.io.*;

public class Main {

    // BOJ 2744 - 대소문자 바꾸기
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String word = br.readLine();
        
        for(int i = 0; i < word.length(); i++) {
        	char cur = word.charAt(i);
        	if('a' <= cur && cur <= 'z') {
        		cur -= 'a';
        		cur += 'A';
        	}
        	else {
        		cur -= 'A';
        		cur += 'a';
        	}
        	sb.append(cur);
        }
        
        // 최종 정답 출력
        System.out.println(sb.toString());
        
    }

}