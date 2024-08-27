import java.util.*;
import java.io.*;

public class Main {

	// BOJ 1759 - 암호 만들기
	
	// 모음 개수 vowel, 자음 개수 cons
	public static int L, C, vowel = 0, cons = 0;
	// 전체 단어 후보 word, 비밀번호 후보 password
	public static char[] word, password;
	public static StringBuilder sb = new StringBuilder();
	
	public static void combi(int cnt, int start) {
		// 길이가 L이라면
		if(cnt == L) {
			// 모음 1개 이상, 자음 2개 이상이라면 추가
			if(vowel >= 1 && cons >= 2)
				sb.append(password).append("\n");
			return;
		}
		// 아직 길이가 완성되지 않았다면
		else {
			// 조합 생성
			for(int i = start; i < C; i++) {
				password[cnt] = word[i];
				// 자음, 모음 개수 증가
				if(word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u') vowel++;
				else cons++;
				combi(cnt+1, i+1);
				// 자음, 모음 개수 감소
				if(word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u') vowel--;
				else cons--;
			}
		}
	}
	
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // 단어 후보 배열 초기화
        word = new char[C];
        // password 초기화
        password = new char[L];
        // 초기값 입력
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) {
        	word[i] = st.nextToken().charAt(0);
        }
        
        // 사전 순서대로 정렬
        Arrays.sort(word);
        // 조합 생성
        combi(0, 0);
        // 완성된 조합 출력
        System.out.println(sb.toString());
        
    }
}